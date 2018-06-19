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

@namespace("operate/shareindex")
public @caption("分享指数") @table("cs_share_index") class CsShareIndex implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cssi_id")   @primary  @note("  ") Long cssiId;// 主键 非空     
	private @caption("城市") @column("cssi_host")    @relate("$cssiHost") @RelateClass(SrvHost.class)  @note("  ") Long cssiHost;// 非空     
 	private SrvHost $cssiHost;//关联对象[运营城市]
	private @caption("分享人") @column("cssi_source")    @relate("$cssiSource") @RelateClass(CsMember.class)  @note("  ") Long cssiSource;// 非空     
 	private CsMember $cssiSource;//关联对象[会员帐号]
	private @caption("网站次数") @column("cssi_web_s")    @note("  通过网站分享方式") Long cssiWebS;// 非空  通过网站分享方式   
	private @caption("微博次数") @column("cssi_weibo_s")    @note("  通过微博分享方式") Long cssiWeiboS;// 非空  通过微博分享方式   
	private @caption("微信次数") @column("cssi_weixin_s")    @note("  通过微信分享方式") Long cssiWeixinS;// 非空  通过微信分享方式   
	private @caption("修改时间") @column("cssi_update_time")    @note("  ") Date cssiUpdateTime;// 非空     
	private @caption("添加时间") @column("cssi_add_time")    @note("  ") Date cssiAddTime;// 非空     
	private @caption("状态") @column("cssi_status")    @note(" 1:正常 0:无效  ") Short cssiStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsShareIndex(){
	
	}
	
	//主键构造函数
	public CsShareIndex(Long id){
		this.cssiId = id;
	}
	
	/**所有字段构造函数 CsShareIndex(cssiHost,cssiSource,cssiWebS,cssiWeiboS,cssiWeixinS,cssiUpdateTime,cssiAddTime,cssiStatus)
	 CsShareIndex(
	 	$.getLong("cssiHost")//城市 [非空]
	 	,$.getLong("cssiSource")//分享人 [非空]
	 	,$.getLong("cssiWebS")//网站次数 [非空]
	 	,$.getLong("cssiWeiboS")//微博次数 [非空]
	 	,$.getLong("cssiWeixinS")//微信次数 [非空]
	 	,$.getDate("cssiUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssiAddTime")//添加时间 [非空]
	 	,$.getShort("cssiStatus")//状态 [非空]
	 )
	**/
	public CsShareIndex(Long cssiHost,Long cssiSource,Long cssiWebS,Long cssiWeiboS,Long cssiWeixinS,Date cssiUpdateTime,Date cssiAddTime,Short cssiStatus){
		this.cssiHost=cssiHost;
		this.cssiSource=cssiSource;
		this.cssiWebS=cssiWebS;
		this.cssiWeiboS=cssiWeiboS;
		this.cssiWeixinS=cssiWeixinS;
		this.cssiUpdateTime=cssiUpdateTime;
		this.cssiAddTime=cssiAddTime;
		this.cssiStatus=cssiStatus;
	}
	
	//设置非空字段
	public CsShareIndex setNotNull(Long cssiHost,Long cssiSource,Long cssiWebS,Long cssiWeiboS,Long cssiWeixinS,Date cssiUpdateTime,Date cssiAddTime,Short cssiStatus){
		this.cssiHost=cssiHost;
		this.cssiSource=cssiSource;
		this.cssiWebS=cssiWebS;
		this.cssiWeiboS=cssiWeiboS;
		this.cssiWeixinS=cssiWeixinS;
		this.cssiUpdateTime=cssiUpdateTime;
		this.cssiAddTime=cssiAddTime;
		this.cssiStatus=cssiStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsShareIndex cssiId(Long cssiId){
		this.cssiId = cssiId;
		this.setSeted(F.cssiId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsShareIndex cssiHost(Long cssiHost){
		this.cssiHost = cssiHost;
		this.setSeted(F.cssiHost);
		return this;
	}
	/** 分享人 [非空] [CsMember]      **/
	public CsShareIndex cssiSource(Long cssiSource){
		this.cssiSource = cssiSource;
		this.setSeted(F.cssiSource);
		return this;
	}
	/** 网站次数 [非空]    通过网站分享方式   **/
	public CsShareIndex cssiWebS(Long cssiWebS){
		this.cssiWebS = cssiWebS;
		this.setSeted(F.cssiWebS);
		return this;
	}
	/** 微博次数 [非空]    通过微博分享方式   **/
	public CsShareIndex cssiWeiboS(Long cssiWeiboS){
		this.cssiWeiboS = cssiWeiboS;
		this.setSeted(F.cssiWeiboS);
		return this;
	}
	/** 微信次数 [非空]    通过微信分享方式   **/
	public CsShareIndex cssiWeixinS(Long cssiWeixinS){
		this.cssiWeixinS = cssiWeixinS;
		this.setSeted(F.cssiWeixinS);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsShareIndex cssiUpdateTime(Date cssiUpdateTime){
		this.cssiUpdateTime = cssiUpdateTime;
		this.setSeted(F.cssiUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsShareIndex cssiAddTime(Date cssiAddTime){
		this.cssiAddTime = cssiAddTime;
		this.setSeted(F.cssiAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsShareIndex cssiStatus(Short cssiStatus){
		this.cssiStatus = cssiStatus;
		this.setSeted(F.cssiStatus);
		return this;
	}
	
	
	//克隆对象
	public CsShareIndex clone(){
		CsShareIndex clone = new CsShareIndex();
		clone.cssiHost=this.cssiHost;
		clone.cssiSource=this.cssiSource;
		clone.cssiWebS=this.cssiWebS;
		clone.cssiWeiboS=this.cssiWeiboS;
		clone.cssiWeixinS=this.cssiWeixinS;
		clone.cssiUpdateTime=this.cssiUpdateTime;
		clone.cssiAddTime=this.cssiAddTime;
		clone.cssiStatus=this.cssiStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取分享指数
	 * @param id
	 * @return
	 */
	public static @api CsShareIndex get(Long id){		
		return getCsShareIndexById(id);
	}
	/**
	 * 获取所有分享指数
	 * @return
	 */
	public static @api List<CsShareIndex> list(Map params,Integer size){
		return getCsShareIndexList(params,size);
	}
	/**
	 * 获取分享指数分页
	 * @return
	 */
	public static @api Page<CsShareIndex> page(int page,int size,Map params){
		return getCsShareIndexPage(page, size , params);
	}
	/**
	 * 根据查询条件取分享指数
	 * @param params
	 * @return
	 */
	public static @api CsShareIndex Get(Map params){
		return getCsShareIndex(params);
	}
	/**
	 * 获取分享指数数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsShareIndexCount(params);
	}
	/**
	 * 获取分享指数数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsShareIndexEval(eval,params);
	}
	
	/**
	 * 根据ID取分享指数
	 * @param id
	 * @return
	 */
	public static @api CsShareIndex getCsShareIndexById(Long id){		
		CsShareIndex csShareIndex = (CsShareIndex) $.GetRequest("CsShareIndex$"+id);
		if(csShareIndex!=null)
			return csShareIndex;
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");		
		return csShareIndexService.getCsShareIndexById(id);
	}
	
	
	/**
	 * 根据ID取分享指数的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsShareIndex.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsShareIndex csShareIndex = get(id);
		if(csShareIndex!=null){
			String strValue = csShareIndex.getCssiId$();
			if(!"CssiId".equals("CssiId"))
				strValue+="("+csShareIndex.getCssiId$()+")";
			MemCache.setValue(CsShareIndex.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssiId$();
		if(!"CssiId".equals("CssiId"))
			keyValue+="("+this.getCssiId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有分享指数
	 * @return
	 */
	public static @api List<CsShareIndex> getCsShareIndexList(Map params,Integer size){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		return csShareIndexService.getCsShareIndexList(params, size);
	}
	
	/**
	 * 获取分享指数分页
	 * @return
	 */
	public static @api Page<CsShareIndex> getCsShareIndexPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		return csShareIndexService.getCsShareIndexPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取分享指数
	 * @param params
	 * @return
	 */
	public static @api CsShareIndex getCsShareIndex(Map params){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		return csShareIndexService.getCsShareIndex(params);
	}
	
	/**
	 * 获取分享指数数
	 * @return
	 */
	public static @api Long getCsShareIndexCount(Map params){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		return csShareIndexService.getCsShareIndexCount(params);
	}
		
		
	/**
	 * 获取分享指数自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsShareIndexEval(String eval,Map params){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		return csShareIndexService.getCsShareIndexEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsShareIndex(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		csShareIndexService.updateCsShareIndexByConfirm(set, where);
	}
	
	
	/**
	 * 保存分享指数对象
	 * @param params
	 * @return
	 */
	public CsShareIndex save(){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		if(this.getCssiId()!=null)
			csShareIndexService.updateCsShareIndex(this);
		else
			return csShareIndexService.saveCsShareIndex(this);
		return this;
	}
	
	
	/**
	 * 更新分享指数对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		csShareIndexService.updateCsShareIndex$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		if($.equals($.config("logic_delete"),"true"))
			csShareIndexService.removeCsShareIndexById(this.getCssiId());
		else
			csShareIndexService.deleteCsShareIndexById(this.getCssiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsShareIndexService csShareIndexService = $.GetSpringBean("csShareIndexService");
		return csShareIndexService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssiId(){
		return this.cssiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssiId$(){
		String strValue="";
		 strValue=$.str(this.getCssiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssiId(Long cssiId){
		this.cssiId = cssiId;
		this.setSeted(F.cssiId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCssiHost(){
		return this.cssiHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCssiHost$(){
		String strValue="";
		if(this.getCssiHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCssiHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCssiHost(Long cssiHost){
		this.cssiHost = cssiHost;
		this.setSeted(F.cssiHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cssiHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCssiHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCssiHost()!=null){
 			srvHost = SrvHost.get(this.getCssiHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCssiHost(), srvHost);
	 	return srvHost;
	}
	/*******************************分享人**********************************/	
	/**
	* 分享人 [非空] [CsMember]     
	**/
	public Long getCssiSource(){
		return this.cssiSource;
	}
	/**
	* 获取分享人格式化(toString)
	**/
	public String getCssiSource$(){
		String strValue="";
		if(this.getCssiSource()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCssiSource()));
		}			
	 	 return strValue;
	}
	/**
	* 分享人 [非空] [CsMember]     
	**/
	public void setCssiSource(Long cssiSource){
		this.cssiSource = cssiSource;
		this.setSeted(F.cssiSource);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cssiSource(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCssiSource());
 		if(csMember!=null)
			return csMember;
		if(this.getCssiSource()!=null){
 			csMember = CsMember.get(this.getCssiSource());
 		}
 		$.SetRequest("CsMember$"+this.getCssiSource(), csMember);
	 	return csMember;
	}
	/*******************************网站次数**********************************/	
	/**
	* 网站次数 [非空]    通过网站分享方式  
	**/
	public Long getCssiWebS(){
		return this.cssiWebS;
	}
	/**
	* 获取网站次数格式化(toString)
	**/
	public String getCssiWebS$(){
		String strValue="";
		 strValue=$.str(this.getCssiWebS());
	 	 return strValue;
	}
	/**
	* 网站次数 [非空]    通过网站分享方式  
	**/
	public void setCssiWebS(Long cssiWebS){
		this.cssiWebS = cssiWebS;
		this.setSeted(F.cssiWebS);
	}
	/*******************************微博次数**********************************/	
	/**
	* 微博次数 [非空]    通过微博分享方式  
	**/
	public Long getCssiWeiboS(){
		return this.cssiWeiboS;
	}
	/**
	* 获取微博次数格式化(toString)
	**/
	public String getCssiWeiboS$(){
		String strValue="";
		 strValue=$.str(this.getCssiWeiboS());
	 	 return strValue;
	}
	/**
	* 微博次数 [非空]    通过微博分享方式  
	**/
	public void setCssiWeiboS(Long cssiWeiboS){
		this.cssiWeiboS = cssiWeiboS;
		this.setSeted(F.cssiWeiboS);
	}
	/*******************************微信次数**********************************/	
	/**
	* 微信次数 [非空]    通过微信分享方式  
	**/
	public Long getCssiWeixinS(){
		return this.cssiWeixinS;
	}
	/**
	* 获取微信次数格式化(toString)
	**/
	public String getCssiWeixinS$(){
		String strValue="";
		 strValue=$.str(this.getCssiWeixinS());
	 	 return strValue;
	}
	/**
	* 微信次数 [非空]    通过微信分享方式  
	**/
	public void setCssiWeixinS(Long cssiWeixinS){
		this.cssiWeixinS = cssiWeixinS;
		this.setSeted(F.cssiWeixinS);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssiUpdateTime(){
		return this.cssiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssiUpdateTime(Date cssiUpdateTime){
		this.cssiUpdateTime = cssiUpdateTime;
		this.setSeted(F.cssiUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssiAddTime(){
		return this.cssiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssiAddTime(Date cssiAddTime){
		this.cssiAddTime = cssiAddTime;
		this.setSeted(F.cssiAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCssiStatus(){
		return this.cssiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssiStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCssiStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCssiStatus(Short cssiStatus){
		this.cssiStatus = cssiStatus;
		this.setSeted(F.cssiStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsShareIndex.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsShareIndex.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsShareIndex.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsShareIndex.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsShareIndex.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsShareIndex.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsShareIndex.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsShareIndex.this);
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
		public M cssiId(Object cssiId){this.put("cssiId", cssiId);return this;};
	 	/** and cssi_id is null */
 		public M cssiIdNull(){if(this.get("cssiIdNot")==null)this.put("cssiIdNot", "");this.put("cssiId", null);return this;};
 		/** not .... */
 		public M cssiIdNot(){this.put("cssiIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cssiHost(Object cssiHost){this.put("cssiHost", cssiHost);return this;};
	 	/** and cssi_host is null */
 		public M cssiHostNull(){if(this.get("cssiHostNot")==null)this.put("cssiHostNot", "");this.put("cssiHost", null);return this;};
 		/** not .... */
 		public M cssiHostNot(){this.put("cssiHostNot", "not");return this;};
		/** 分享人 [非空] [CsMember]      **/
		public M cssiSource(Object cssiSource){this.put("cssiSource", cssiSource);return this;};
	 	/** and cssi_source is null */
 		public M cssiSourceNull(){if(this.get("cssiSourceNot")==null)this.put("cssiSourceNot", "");this.put("cssiSource", null);return this;};
 		/** not .... */
 		public M cssiSourceNot(){this.put("cssiSourceNot", "not");return this;};
		public M cssiSource$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cssiSource$on", value);
			return this;
		};	
		/** 网站次数 [非空]    通过网站分享方式   **/
		public M cssiWebS(Object cssiWebS){this.put("cssiWebS", cssiWebS);return this;};
	 	/** and cssi_web_s is null */
 		public M cssiWebSNull(){if(this.get("cssiWebSNot")==null)this.put("cssiWebSNot", "");this.put("cssiWebS", null);return this;};
 		/** not .... */
 		public M cssiWebSNot(){this.put("cssiWebSNot", "not");return this;};
		/** and cssi_web_s >= ? */
		public M cssiWebSMin(Object min){this.put("cssiWebSMin", min);return this;};
		/** and cssi_web_s <= ? */
		public M cssiWebSMax(Object max){this.put("cssiWebSMax", max);return this;};
		/** 微博次数 [非空]    通过微博分享方式   **/
		public M cssiWeiboS(Object cssiWeiboS){this.put("cssiWeiboS", cssiWeiboS);return this;};
	 	/** and cssi_weibo_s is null */
 		public M cssiWeiboSNull(){if(this.get("cssiWeiboSNot")==null)this.put("cssiWeiboSNot", "");this.put("cssiWeiboS", null);return this;};
 		/** not .... */
 		public M cssiWeiboSNot(){this.put("cssiWeiboSNot", "not");return this;};
		/** and cssi_weibo_s >= ? */
		public M cssiWeiboSMin(Object min){this.put("cssiWeiboSMin", min);return this;};
		/** and cssi_weibo_s <= ? */
		public M cssiWeiboSMax(Object max){this.put("cssiWeiboSMax", max);return this;};
		/** 微信次数 [非空]    通过微信分享方式   **/
		public M cssiWeixinS(Object cssiWeixinS){this.put("cssiWeixinS", cssiWeixinS);return this;};
	 	/** and cssi_weixin_s is null */
 		public M cssiWeixinSNull(){if(this.get("cssiWeixinSNot")==null)this.put("cssiWeixinSNot", "");this.put("cssiWeixinS", null);return this;};
 		/** not .... */
 		public M cssiWeixinSNot(){this.put("cssiWeixinSNot", "not");return this;};
		/** and cssi_weixin_s >= ? */
		public M cssiWeixinSMin(Object min){this.put("cssiWeixinSMin", min);return this;};
		/** and cssi_weixin_s <= ? */
		public M cssiWeixinSMax(Object max){this.put("cssiWeixinSMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M cssiUpdateTime(Object cssiUpdateTime){this.put("cssiUpdateTime", cssiUpdateTime);return this;};
	 	/** and cssi_update_time is null */
 		public M cssiUpdateTimeNull(){if(this.get("cssiUpdateTimeNot")==null)this.put("cssiUpdateTimeNot", "");this.put("cssiUpdateTime", null);return this;};
 		/** not .... */
 		public M cssiUpdateTimeNot(){this.put("cssiUpdateTimeNot", "not");return this;};
 		/** and cssi_update_time >= ? */
 		public M cssiUpdateTimeStart(Object start){this.put("cssiUpdateTimeStart", start);return this;};			
 		/** and cssi_update_time <= ? */
 		public M cssiUpdateTimeEnd(Object end){this.put("cssiUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssiAddTime(Object cssiAddTime){this.put("cssiAddTime", cssiAddTime);return this;};
	 	/** and cssi_add_time is null */
 		public M cssiAddTimeNull(){if(this.get("cssiAddTimeNot")==null)this.put("cssiAddTimeNot", "");this.put("cssiAddTime", null);return this;};
 		/** not .... */
 		public M cssiAddTimeNot(){this.put("cssiAddTimeNot", "not");return this;};
 		/** and cssi_add_time >= ? */
 		public M cssiAddTimeStart(Object start){this.put("cssiAddTimeStart", start);return this;};			
 		/** and cssi_add_time <= ? */
 		public M cssiAddTimeEnd(Object end){this.put("cssiAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cssiStatus(Object cssiStatus){this.put("cssiStatus", cssiStatus);return this;};
	 	/** and cssi_status is null */
 		public M cssiStatusNull(){if(this.get("cssiStatusNot")==null)this.put("cssiStatusNot", "");this.put("cssiStatus", null);return this;};
 		/** not .... */
 		public M cssiStatusNot(){this.put("cssiStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有分享指数 **/
		public @api List<CsShareIndex> list(Integer size){
			return getCsShareIndexList(this,size);
		}
		/** 获取分享指数分页 **/
		public @api Page<CsShareIndex> page(int page,int size){
			return getCsShareIndexPage(page, size , this);
		}
		/** 根据查询条件取分享指数 **/
		public @api CsShareIndex get(){
			return getCsShareIndex(this);
		}
		/** 获取分享指数数 **/
		public @api Long count(){
			return getCsShareIndexCount(this);
		}
		/** 获取分享指数表达式 **/
		public @api <T> T eval(String strEval){
			return getCsShareIndexEval(strEval,this);
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
			updateCsShareIndex(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssiId="cssiId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cssiHost="cssiHost";
		/** 分享人 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cssiSource="cssiSource";
		/** 网站次数 [非空]    通过网站分享方式   **/
		public final static @type(Long.class)  String cssiWebS="cssiWebS";
		/** and cssi_web_s >= ? */
		public final static @type(Long.class) String cssiWebSMin="cssiWebSMin";
		/** and cssi_web_s <= ? */
		public final static @type(Long.class) String cssiWebSMax="cssiWebSMax";
		/** 微博次数 [非空]    通过微博分享方式   **/
		public final static @type(Long.class)  String cssiWeiboS="cssiWeiboS";
		/** and cssi_weibo_s >= ? */
		public final static @type(Long.class) String cssiWeiboSMin="cssiWeiboSMin";
		/** and cssi_weibo_s <= ? */
		public final static @type(Long.class) String cssiWeiboSMax="cssiWeiboSMax";
		/** 微信次数 [非空]    通过微信分享方式   **/
		public final static @type(Long.class)  String cssiWeixinS="cssiWeixinS";
		/** and cssi_weixin_s >= ? */
		public final static @type(Long.class) String cssiWeixinSMin="cssiWeixinSMin";
		/** and cssi_weixin_s <= ? */
		public final static @type(Long.class) String cssiWeixinSMax="cssiWeixinSMax";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssiUpdateTime="cssiUpdateTime";
	 	/** and cssi_update_time >= ? */
 		public final static @type(Date.class) String cssiUpdateTimeStart="cssiUpdateTimeStart";
 		/** and cssi_update_time <= ? */
 		public final static @type(Date.class) String cssiUpdateTimeEnd="cssiUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssiAddTime="cssiAddTime";
	 	/** and cssi_add_time >= ? */
 		public final static @type(Date.class) String cssiAddTimeStart="cssiAddTimeStart";
 		/** and cssi_add_time <= ? */
 		public final static @type(Date.class) String cssiAddTimeEnd="cssiAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cssiStatus="cssiStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssiId="cssi_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cssiHost="cssi_host";
		/** 分享人 [非空] [CsMember]      **/
		public final static String cssiSource="cssi_source";
		/** 网站次数 [非空]    通过网站分享方式   **/
		public final static String cssiWebS="cssi_web_s";
		/** 微博次数 [非空]    通过微博分享方式   **/
		public final static String cssiWeiboS="cssi_weibo_s";
		/** 微信次数 [非空]    通过微信分享方式   **/
		public final static String cssiWeixinS="cssi_weixin_s";
		/** 修改时间 [非空]       **/
		public final static String cssiUpdateTime="cssi_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssiAddTime="cssi_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cssiStatus="cssi_status";
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
				$.Set(name,CsShareIndex.getCsShareIndex(params));
			else
				$.Set(name,CsShareIndex.getCsShareIndexList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取分享指数数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsShareIndex) $.GetRequest("CsShareIndex$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsShareIndex.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsShareIndex.getCsShareIndex(params);
			else
				value = CsShareIndex.getCsShareIndexList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsShareIndex.Get($.add(CsShareIndex.F.cssiId,param));
		else if(!$.empty(param.toString()))
			value = CsShareIndex.get(Long.valueOf(param.toString()));
		$.SetRequest("CsShareIndex$"+param.hashCode(), value);
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
	public void mergeSet(CsShareIndex old){
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