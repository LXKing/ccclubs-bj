package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.CsSpecialCar.M;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("permissions/userexp")
public @caption("用户扩展信息") @table("srv_user_exp") class SrvUserExp implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sue_id")   @primary   @note("  ") Long sueId;
	private @caption("所属用户") @column("sue_user")    @relate("$sueUser") @RelateClass(SrvUser.class)  @note("  ") Long sueUser;
	private SrvUser $sueUser;//关联对象[所属用户]
	private @caption("微信标识") @column("sue_wx_openid")      @note("  ") String sueWxOpenid;
	private @caption("外勤作业") @column("sue_is_qr_op")      @note("  ") Boolean sueIsQrOp;
	private @caption("是否客服") @column("sue_is_servicer")      @note("  ") Boolean sueIsServicer;
	private @caption("客服昵称") @column("sue_chat_nick")      @note("  ") String sueChatNick;
	private @caption("客服头像") @column("sue_chat_header")      @note("  ") String sueChatHeader;
	private @caption("客服签名") @column("sue_chat_sign")      @note("  ") String sueChatSign;
	private @caption("所属网点") @column("sue_outlets")    @relate("$sueOutlets") @RelateClass(CsOutlets.class)  @note("") String sueOutlets;
	private CsOutlets $sueOutlets;//关联对象[所属网点]
	
	//默认构造函数
	public SrvUserExp(){
	
	}
	
	//主键构造函数
	public SrvUserExp(Long id){
		this.sueId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public SrvUserExp(Long sueUser,String sueWxOpenid,Boolean sueIsQrOp,Boolean sueIsServicer,String sueChatNick,String sueChatHeader,String sueChatSign,String sueOutlets){
		this.sueUser=sueUser;
		this.sueWxOpenid=sueWxOpenid;
		this.sueIsQrOp=sueIsQrOp;
		this.sueIsServicer=sueIsServicer;
		this.sueChatNick=sueChatNick;
		this.sueChatHeader=sueChatHeader;
		this.sueChatSign=sueChatSign;
		this.sueOutlets=sueOutlets;
	}
	
	//设置非空字段
	public SrvUserExp setNotNull(){
		return this;
	}
	
	/** 编号 [非空]       **/
	public SrvUserExp sueId(Long sueId){
		this.sueId = sueId;
		this.setSeted(F.sueId);
		return this;
	}
	/** 所属用户 [非空]       **/
	public SrvUserExp sueUser(Long sueUser){
		this.sueUser = sueUser;
		this.setSeted(F.sueUser);
		return this;
	}
	/** 微信标识 [非空]       **/
	public SrvUserExp sueWxOpenid(String sueWxOpenid){
		this.sueWxOpenid = sueWxOpenid;
		this.setSeted(F.sueWxOpenid);
		return this;
	}
	/** 外勤作业 [非空]       **/
	public SrvUserExp sueIsQrOp(Boolean sueIsQrOp){
		this.sueIsQrOp = sueIsQrOp;
		this.setSeted(F.sueIsQrOp);
		return this;
	}
	/** 是否客服 [非空]       **/
	public SrvUserExp sueIsServicer(Boolean sueIsServicer){
		this.sueIsServicer = sueIsServicer;
		this.setSeted(F.sueIsServicer);
		return this;
	}
	/** 客服昵称 [非空]       **/
	public SrvUserExp sueChatNick(String sueChatNick){
		this.sueChatNick = sueChatNick;
		this.setSeted(F.sueChatNick);
		return this;
	}
	/** 客服头像 [非空]       **/
	public SrvUserExp sueChatHeader(String sueChatHeader){
		this.sueChatHeader = sueChatHeader;
		this.setSeted(F.sueChatHeader);
		return this;
	}
	/** 客服签名 [非空]       **/
	public SrvUserExp sueChatSign(String sueChatSign){
		this.sueChatSign = sueChatSign;
		this.setSeted(F.sueChatSign);
		return this;
	}
	/** 所属网点 [非空]       **/
	public SrvUserExp sueOutlets(String sueOutlets){
		this.sueOutlets = sueOutlets;
		this.setSeted(F.sueOutlets);
		return this;
	}
	
	//克隆对象
	public SrvUserExp clone(){
		SrvUserExp clone = new SrvUserExp();
		clone.sueId=this.sueId;
		clone.sueUser=this.sueUser;
		clone.sueWxOpenid=this.sueWxOpenid;
		clone.sueIsQrOp=this.sueIsQrOp;
		clone.sueIsServicer=this.sueIsServicer;
		clone.sueChatNick=this.sueChatNick;
		clone.sueChatHeader=this.sueChatHeader;
		clone.sueChatSign=this.sueChatSign;
		clone.sueOutlets=this.sueOutlets;
		return clone;
	}
	
	
	/**
	 * 根据ID取用户扩展信息
	 * @param id
	 * @return
	 */
	public static @api SrvUserExp get(Long id){		
		return getSrvUserExpById(id);
	}
	/**
	 * 获取所有用户扩展信息
	 * @return
	 */
	public static @api List<SrvUserExp> list(Map params,Integer size){
		return getSrvUserExpList(params,size);
	}
	/**
	 * 获取用户扩展信息分页
	 * @return
	 */
	public static @api Page<SrvUserExp> page(int page,int size,Map params){
		return getSrvUserExpPage(page, size , params);
	}
	/**
	 * 根据查询条件取用户扩展信息
	 * @param params
	 * @return
	 */
	public static @api SrvUserExp Get(Map params){
		return getSrvUserExp(params);
	}
	/**
	 * 获取用户扩展信息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvUserExpCount(params);
	}
	/**
	 * 获取用户扩展信息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvUserExpEval(eval,params);
	}
	
	/**
	 * 根据ID取用户扩展信息
	 * @param id
	 * @return
	 */
	public static @api SrvUserExp getSrvUserExpById(Long id){		
		SrvUserExp srvUserExp = (SrvUserExp) $.GetRequest("SrvUserExp$"+id);
		if(srvUserExp!=null)
			return srvUserExp;
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");		
		return srvUserExpService.getSrvUserExpById(id);
	}
	
	
	/**
	 * 根据ID取用户扩展信息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvUserExp.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvUserExp srvUserExp = get(id);
		if(srvUserExp!=null){
			String strValue = srvUserExp.getSueId$();
			if(!"SueId".equals("SueId"))
				strValue+="("+srvUserExp.getSueId$()+")";
			MemCache.setValue(SrvUserExp.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSueId$();
		if(!"SueId".equals("SueId"))
			keyValue+="("+this.getSueId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有用户扩展信息
	 * @return
	 */
	public static @api List<SrvUserExp> getSrvUserExpList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		return srvUserExpService.getSrvUserExpList(params, size);
	}
	
	/**
	 * 获取用户扩展信息分页
	 * @return
	 */
	public static @api Page<SrvUserExp> getSrvUserExpPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		return srvUserExpService.getSrvUserExpPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取用户扩展信息
	 * @param params
	 * @return
	 */
	public static @api SrvUserExp getSrvUserExp(Map params){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		return srvUserExpService.getSrvUserExp(params);
	}
	
	/**
	 * 获取用户扩展信息数
	 * @return
	 */
	public static @api Long getSrvUserExpCount(Map params){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		return srvUserExpService.getSrvUserExpCount(params);
	}
		
		
	/**
	 * 获取用户扩展信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvUserExpEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		return srvUserExpService.getSrvUserExpEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvUserExp(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		srvUserExpService.updateSrvUserExpByConfirm(set, where);
	}
	
	
	/**
	 * 保存用户扩展信息对象
	 * @param params
	 * @return
	 */
	public SrvUserExp save(){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		if(this.getSueId()!=null)
			srvUserExpService.updateSrvUserExp(this);
		else
			return srvUserExpService.saveSrvUserExp(this);
		return this;
	}
	
	
	/**
	 * 更新用户扩展信息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		srvUserExpService.updateSrvUserExp$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		if($.equals($.config("logic_delete"),"true"))
			srvUserExpService.removeSrvUserExpById(this.getSueId());
		else
			srvUserExpService.deleteSrvUserExpById(this.getSueId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
		return srvUserExpService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSueId(){
		return this.sueId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSueId$(){
		String strValue="";
		 strValue=$.str(this.getSueId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setSueId(Long sueId){
		this.sueId = sueId;
		this.setSeted(F.sueId);
	}
	/*******************************所属用户**********************************/	
	/**
	* 所属用户 [非空]      
	**/
	public Long getSueUser(){
		return this.sueUser;
	}
	/**
	* 获取所属用户格式化(toString)
	**/
	public String getSueUser$(){
		String strValue="";
		if(this.getSueUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getSueUser()));
		}			
	 	 return strValue;
	}
	/**
	* 所属用户 [非空]     
	**/
	public void setSueUser(Long sueUser){
		this.sueUser = sueUser;
		this.setSeted(F.sueUser);
	}
	/**
	* 获取关联对象[所属用户]
	**/	 			
 	public SrvUser get$sueUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getSueUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getSueUser()!=null){
			srvUser = SrvUser.get(this.getSueUser());
 		}
 		$.SetRequest("SrvUser$"+this.getSueUser(), srvUser);
	 	return srvUser;
	}
	/*******************************微信标识**********************************/	
	/**
	* 微信标识 [非空]      
	**/
	public String getSueWxOpenid(){
		return this.sueWxOpenid;
	}
	/**
	* 获取微信标识格式化(toString)
	**/
	public String getSueWxOpenid$(){
		String strValue="";
		 strValue=$.str(this.getSueWxOpenid());
	 	 return strValue;
	}
	/**
	* 微信标识 [非空]     
	**/
	public void setSueWxOpenid(String sueWxOpenid){
		this.sueWxOpenid = sueWxOpenid;
		this.setSeted(F.sueWxOpenid);
	}
	/*******************************外勤作业**********************************/	
	/**
	* 外勤作业 [非空]      
	**/
	public Boolean getSueIsQrOp(){
		return this.sueIsQrOp;
	}
	/**
	* 获取外勤作业格式化(toString)
	**/
	public String getSueIsQrOp$(){
		String strValue="";
		 strValue=$.str(this.getSueIsQrOp());
	 	 return strValue;
	}
	/**
	* 外勤作业 [非空]     
	**/
	public void setSueIsQrOp(Boolean sueIsQrOp){
		this.sueIsQrOp = sueIsQrOp;
		this.setSeted(F.sueIsQrOp);
	}
	/*******************************是否客服**********************************/	
	/**
	* 是否客服 [非空]      
	**/
	public Boolean getSueIsServicer(){
		return this.sueIsServicer;
	}
	/**
	* 获取是否客服格式化(toString)
	**/
	public String getSueIsServicer$(){
		String strValue="";
		 strValue=$.str(this.getSueIsServicer());
	 	 return strValue;
	}
	/**
	* 是否客服 [非空]     
	**/
	public void setSueIsServicer(Boolean sueIsServicer){
		this.sueIsServicer = sueIsServicer;
		this.setSeted(F.sueIsServicer);
	}
	/*******************************客服昵称**********************************/	
	/**
	* 客服昵称 [非空]      
	**/
	public String getSueChatNick(){
		return this.sueChatNick;
	}
	/**
	* 获取客服昵称格式化(toString)
	**/
	public String getSueChatNick$(){
		String strValue="";
		 strValue=$.str(this.getSueChatNick());
	 	 return strValue;
	}
	/**
	* 客服昵称 [非空]     
	**/
	public void setSueChatNick(String sueChatNick){
		this.sueChatNick = sueChatNick;
		this.setSeted(F.sueChatNick);
	}
	/*******************************客服头像**********************************/	
	/**
	* 客服头像 [非空]      
	**/
	public String getSueChatHeader(){
		return this.sueChatHeader;
	}
	/**
	* 获取客服头像格式化(toString)
	**/
	public String getSueChatHeader$(){
		String strValue="";
		 strValue=$.str(this.getSueChatHeader());
	 	 return strValue;
	}
	/**
	* 客服头像 [非空]     
	**/
	public void setSueChatHeader(String sueChatHeader){
		this.sueChatHeader = sueChatHeader;
		this.setSeted(F.sueChatHeader);
	}
	/*******************************客服签名**********************************/	
	/**
	* 客服签名 [非空]      
	**/
	public String getSueChatSign(){
		return this.sueChatSign;
	}
	/**
	* 获取客服签名格式化(toString)
	**/
	public String getSueChatSign$(){
		String strValue="";
		 strValue=$.str(this.getSueChatSign());
	 	 return strValue;
	}
	/**
	* 客服签名 [非空]     
	**/
	public void setSueChatSign(String sueChatSign){
		this.sueChatSign = sueChatSign;
		this.setSeted(F.sueChatSign);
	}
	/*******************************所属网点**********************************/	
	/**
	* 所属网点 [非空]      
	**/
	public String getSueOutlets(){
		return this.sueOutlets;
	}
	/**
	* 获取所属网点格式化(toString)
	**/
	public String getSueOutlets$(){
		String strValue="";
		if(!$.empty(this.getSueOutlets())){
			List<com.ccclubs.model.CsOutlets> items = com.ccclubs.model.CsOutlets.getCsOutletsList($.add("definex","cso_id in ("+$.str(this.getSueOutlets()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsOutlets item1:items)
				strValue+="["+$.str(item1.getCsoName$())+"]";
		}
	 	 return strValue;
	}
	/**
	* 所属网点 [非空]     
	**/
	public void setSueOutlets(String sueOutlets){
		this.sueOutlets = sueOutlets;
		this.setSeted(F.sueOutlets);
	}
	/**
	* 获取关联对象[所属网点]
	**/	 			
 	public List<com.ccclubs.model.CsOutlets> get$sueOutlets(){
		if(!$.empty(this.getSueOutlets())){
			return com.ccclubs.model.CsOutlets.getCsOutletsList($.add("definex","cso_id in ("+$.str(this.getSueOutlets()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvUserExp.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvUserExp.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvUserExp.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvUserExp.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvUserExp.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvUserExp.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvUserExp.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvUserExp.this);
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
		public M sueId(Object sueId){this.put("sueId", sueId);return this;};
		/** and sue_id is null */
		public M sueIdNull(){if(this.get("sueIdNot")==null)this.put("sueIdNot", "");this.put("sueId", null);return this;};
		/** not .... */
		public M sueIdNot(){this.put("sueIdNot", "not");return this;};
		/** and sue_id >= ? */
		public M sueIdMin(Object min){this.put("sueIdMin", min);return this;};
		/** and sue_id <= ? */
		public M sueIdMax(Object max){this.put("sueIdMax", max);return this;};
		/** 所属用户 [非空]       **/
		public M sueUser(Object sueUser){this.put("sueUser", sueUser);return this;};
		/** and sue_user is null */
		public M sueUserNull(){if(this.get("sueUserNot")==null)this.put("sueUserNot", "");this.put("sueUser", null);return this;};
		/** not .... */
		public M sueUserNot(){this.put("sueUserNot", "not");return this;};
		public M sueUser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("sueUser$on", value);
			return this;
		};	
		/** and sue_user >= ? */
		public M sueUserMin(Object min){this.put("sueUserMin", min);return this;};
		/** and sue_user <= ? */
		public M sueUserMax(Object max){this.put("sueUserMax", max);return this;};
		/** 微信标识 [非空]       **/
		public M sueWxOpenid(Object sueWxOpenid){this.put("sueWxOpenid", sueWxOpenid);return this;};
		/** and sue_wx_openid is null */
		public M sueWxOpenidNull(){if(this.get("sueWxOpenidNot")==null)this.put("sueWxOpenidNot", "");this.put("sueWxOpenid", null);return this;};
		/** not .... */
		public M sueWxOpenidNot(){this.put("sueWxOpenidNot", "not");return this;};
		/** 外勤作业 [非空]       **/
		public M sueIsQrOp(Object sueIsQrOp){this.put("sueIsQrOp", sueIsQrOp);return this;};
		/** and sue_is_qr_op is null */
		public M sueIsQrOpNull(){if(this.get("sueIsQrOpNot")==null)this.put("sueIsQrOpNot", "");this.put("sueIsQrOp", null);return this;};
		/** not .... */
		public M sueIsQrOpNot(){this.put("sueIsQrOpNot", "not");return this;};
		/** 是否客服 [非空]       **/
		public M sueIsServicer(Object sueIsServicer){this.put("sueIsServicer", sueIsServicer);return this;};
		/** and sue_is_servicer is null */
		public M sueIsServicerNull(){if(this.get("sueIsServicerNot")==null)this.put("sueIsServicerNot", "");this.put("sueIsServicer", null);return this;};
		/** not .... */
		public M sueIsServicerNot(){this.put("sueIsServicerNot", "not");return this;};
		/** 客服昵称 [非空]       **/
		public M sueChatNick(Object sueChatNick){this.put("sueChatNick", sueChatNick);return this;};
		/** and sue_chat_nick is null */
		public M sueChatNickNull(){if(this.get("sueChatNickNot")==null)this.put("sueChatNickNot", "");this.put("sueChatNick", null);return this;};
		/** not .... */
		public M sueChatNickNot(){this.put("sueChatNickNot", "not");return this;};
		/** 客服头像 [非空]       **/
		public M sueChatHeader(Object sueChatHeader){this.put("sueChatHeader", sueChatHeader);return this;};
		/** and sue_chat_header is null */
		public M sueChatHeaderNull(){if(this.get("sueChatHeaderNot")==null)this.put("sueChatHeaderNot", "");this.put("sueChatHeader", null);return this;};
		/** not .... */
		public M sueChatHeaderNot(){this.put("sueChatHeaderNot", "not");return this;};
		/** 客服签名 [非空]       **/
		public M sueChatSign(Object sueChatSign){this.put("sueChatSign", sueChatSign);return this;};
		/** and sue_chat_sign is null */
		public M sueChatSignNull(){if(this.get("sueChatSignNot")==null)this.put("sueChatSignNot", "");this.put("sueChatSign", null);return this;};
		/** not .... */
		public M sueChatSignNot(){this.put("sueChatSignNot", "not");return this;};
		/** 所属网点 [非空]       **/
		public M sueOutlets(Object sueOutlets){this.put("sueOutlets", sueOutlets);return this;};
		/** and sue_outlets is null */
		public M sueOutletsNull(){if(this.get("sueOutletsNot")==null)this.put("sueOutletsNot", "");this.put("sueOutlets", null);return this;};
		/** not .... */
		public M sueOutletsNot(){this.put("sueOutletsNot", "not");return this;};
		public M sueOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("sueOutlets$on", value);
			return this;
		};	
		/** and sue_outlets >= ? */
		public M sueOutletsMin(Object min){this.put("sueOutletsMin", min);return this;};
		/** and sue_outlets <= ? */
		public M sueOutletsMax(Object max){this.put("sueOutletsMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有用户扩展信息 **/
		public @api List<SrvUserExp> list(Integer size){
			return getSrvUserExpList(this,size);
		}
		/** 获取用户扩展信息分页 **/
		public @api Page<SrvUserExp> page(int page,int size){
			return getSrvUserExpPage(page, size , this);
		}
		/** 根据查询条件取用户扩展信息 **/
		public @api SrvUserExp get(){
			return getSrvUserExp(this);
		}
		/** 获取用户扩展信息数 **/
		public @api Long count(){
			return getSrvUserExpCount(this);
		}
		/** 获取用户扩展信息表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvUserExpEval(strEval,this);
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
			updateSrvUserExp(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String sueId="sueId";
		/** 所属用户 [非空]       **/
		public final static @type(Long.class)  String sueUser="sueUser";
		/** 微信标识 [非空]       **/
		public final static @type(String.class)  String sueWxOpenid="sueWxOpenid";
		/** 外勤作业 [非空]       **/
		public final static @type(Boolean.class)  String sueIsQrOp="sueIsQrOp";
		/** 是否客服 [非空]       **/
		public final static @type(Boolean.class)  String sueIsServicer="sueIsServicer";
		/** 客服昵称 [非空]       **/
		public final static @type(String.class)  String sueChatNick="sueChatNick";
		/** 客服头像 [非空]       **/
		public final static @type(String.class)  String sueChatHeader="sueChatHeader";
		/** 客服签名 [非空]       **/
		public final static @type(String.class)  String sueChatSign="sueChatSign";
		/** 所属网点 [非空]       **/
		public final static @type(Long.class)  String sueOutlets="sueOutlets";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String sueId="sue_id";
		/** 所属用户 [非空]       **/
		public final static String sueUser="sue_user";
		/** 微信标识 [非空]       **/
		public final static String sueWxOpenid="sue_wx_openid";
		/** 外勤作业 [非空]       **/
		public final static String sueIsQrOp="sue_is_qr_op";
		/** 是否客服 [非空]       **/
		public final static String sueIsServicer="sue_is_servicer";
		/** 客服昵称 [非空]       **/
		public final static String sueChatNick="sue_chat_nick";
		/** 客服头像 [非空]       **/
		public final static String sueChatHeader="sue_chat_header";
		/** 客服签名 [非空]       **/
		public final static String sueChatSign="sue_chat_sign";
		/** 所属网点 [非空]       **/
		public final static String sueOutlets="sue_outlets";
		
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
				$.Set(name,SrvUserExp.getSrvUserExp(params));
			else
				$.Set(name,SrvUserExp.getSrvUserExpList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取用户扩展信息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvUserExp) $.GetRequest("SrvUserExp$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvUserExp.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvUserExp.getSrvUserExp(params);
			else
				value = SrvUserExp.getSrvUserExpList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvUserExp.Get($.add(SrvUserExp.F.sueId,param));
		else if(!$.empty(param.toString()))
			value = SrvUserExp.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvUserExp$"+param.hashCode(), value);
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
	public void mergeSet(SrvUserExp old){
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