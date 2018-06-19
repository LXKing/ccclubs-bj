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

@namespace("operate/activitys/activity/in")
public @caption("活动参与") @table("cs_activity_in") class CsActivityIn implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csai_id")   @primary  @note("  ") Long csaiId;// 主键 非空     
	private @caption("城市") @column("csai_host")    @relate("$csaiHost") @RelateClass(SrvHost.class)  @note("  ") Long csaiHost;// 非空     
 	private SrvHost $csaiHost;//关联对象[运营城市]
	private @caption("所属活动") @column("csai_activity")    @relate("$csaiActivity") @RelateClass(CsActivity.class)  @note("  ") Long csaiActivity;// 非空     
 	private CsActivity $csaiActivity;//关联对象[营销活动]
	private @caption("参与人") @column("csai_member")    @relate("$csaiMember") @RelateClass(CsMember.class)  @note("  ") Long csaiMember;// 非空     
 	private CsMember $csaiMember;//关联对象[会员帐号]
	private @caption("修改时间") @column("csai_update_time")    @note("  ") Date csaiUpdateTime;// 非空     
	private @caption("添加时间") @column("csai_add_time")    @note("  ") Date csaiAddTime;// 非空     
	private @caption("备注信息") @column("csai_remark")    @note("  ") String csaiRemark;//     
	private @caption("JSON数据") @column("csai_data")  @hidden   @note("  每个活动根据自己情况自定义数据存储") String csaiData;//  每个活动根据自己情况自定义数据存储   
	private @caption("状态码") @column("csai_state")    @note("  每个活动根据自己情况自定义状态码") String csaiState;//  每个活动根据自己情况自定义状态码   
	private @caption("状态") @column("csai_status")    @note(" 1:正常 0:无效  ") Short csaiStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsActivityIn(){
	
	}
	
	//主键构造函数
	public CsActivityIn(Long id){
		this.csaiId = id;
	}
	
	/**所有字段构造函数 CsActivityIn(csaiHost,csaiActivity,csaiMember,csaiUpdateTime,csaiAddTime,csaiRemark,csaiData,csaiState,csaiStatus)
	 CsActivityIn(
	 	$.getLong("csaiHost")//城市 [非空]
	 	,$.getLong("csaiActivity")//所属活动 [非空]
	 	,$.getLong("csaiMember")//参与人 [非空]
	 	,$.getDate("csaiUpdateTime")//修改时间 [非空]
	 	,$.getDate("csaiAddTime")//添加时间 [非空]
	 	,$.getString("csaiRemark")//备注信息
	 	,$.getString("csaiData")//JSON数据
	 	,$.getString("csaiState")//状态码
	 	,$.getShort("csaiStatus")//状态 [非空]
	 )
	**/
	public CsActivityIn(Long csaiHost,Long csaiActivity,Long csaiMember,Date csaiUpdateTime,Date csaiAddTime,String csaiRemark,String csaiData,String csaiState,Short csaiStatus){
		this.csaiHost=csaiHost;
		this.csaiActivity=csaiActivity;
		this.csaiMember=csaiMember;
		this.csaiUpdateTime=csaiUpdateTime;
		this.csaiAddTime=csaiAddTime;
		this.csaiRemark=csaiRemark;
		this.csaiData=csaiData;
		this.csaiState=csaiState;
		this.csaiStatus=csaiStatus;
	}
	
	//设置非空字段
	public CsActivityIn setNotNull(Long csaiHost,Long csaiActivity,Long csaiMember,Date csaiUpdateTime,Date csaiAddTime,Short csaiStatus){
		this.csaiHost=csaiHost;
		this.csaiActivity=csaiActivity;
		this.csaiMember=csaiMember;
		this.csaiUpdateTime=csaiUpdateTime;
		this.csaiAddTime=csaiAddTime;
		this.csaiStatus=csaiStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsActivityIn csaiId(Long csaiId){
		this.csaiId = csaiId;
		this.setSeted(F.csaiId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsActivityIn csaiHost(Long csaiHost){
		this.csaiHost = csaiHost;
		this.setSeted(F.csaiHost);
		return this;
	}
	/** 所属活动 [非空] [CsActivity]      **/
	public CsActivityIn csaiActivity(Long csaiActivity){
		this.csaiActivity = csaiActivity;
		this.setSeted(F.csaiActivity);
		return this;
	}
	/** 参与人 [非空] [CsMember]      **/
	public CsActivityIn csaiMember(Long csaiMember){
		this.csaiMember = csaiMember;
		this.setSeted(F.csaiMember);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsActivityIn csaiUpdateTime(Date csaiUpdateTime){
		this.csaiUpdateTime = csaiUpdateTime;
		this.setSeted(F.csaiUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsActivityIn csaiAddTime(Date csaiAddTime){
		this.csaiAddTime = csaiAddTime;
		this.setSeted(F.csaiAddTime);
		return this;
	}
	/** 备注信息        **/
	public CsActivityIn csaiRemark(String csaiRemark){
		this.csaiRemark = csaiRemark;
		this.setSeted(F.csaiRemark);
		return this;
	}
	/** JSON数据     每个活动根据自己情况自定义数据存储   **/
	public CsActivityIn csaiData(String csaiData){
		this.csaiData = csaiData;
		this.setSeted(F.csaiData);
		return this;
	}
	/** 状态码     每个活动根据自己情况自定义状态码   **/
	public CsActivityIn csaiState(String csaiState){
		this.csaiState = csaiState;
		this.setSeted(F.csaiState);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsActivityIn csaiStatus(Short csaiStatus){
		this.csaiStatus = csaiStatus;
		this.setSeted(F.csaiStatus);
		return this;
	}
	
	
	//克隆对象
	public CsActivityIn clone(){
		CsActivityIn clone = new CsActivityIn();
		clone.csaiHost=this.csaiHost;
		clone.csaiActivity=this.csaiActivity;
		clone.csaiMember=this.csaiMember;
		clone.csaiUpdateTime=this.csaiUpdateTime;
		clone.csaiAddTime=this.csaiAddTime;
		clone.csaiStatus=this.csaiStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取活动参与
	 * @param id
	 * @return
	 */
	public static @api CsActivityIn get(Long id){		
		return getCsActivityInById(id);
	}
	/**
	 * 获取所有活动参与
	 * @return
	 */
	public static @api List<CsActivityIn> list(Map params,Integer size){
		return getCsActivityInList(params,size);
	}
	/**
	 * 获取活动参与分页
	 * @return
	 */
	public static @api Page<CsActivityIn> page(int page,int size,Map params){
		return getCsActivityInPage(page, size , params);
	}
	/**
	 * 根据查询条件取活动参与
	 * @param params
	 * @return
	 */
	public static @api CsActivityIn Get(Map params){
		return getCsActivityIn(params);
	}
	/**
	 * 获取活动参与数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsActivityInCount(params);
	}
	/**
	 * 获取活动参与数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsActivityInEval(eval,params);
	}
	
	/**
	 * 根据ID取活动参与
	 * @param id
	 * @return
	 */
	public static @api CsActivityIn getCsActivityInById(Long id){		
		CsActivityIn csActivityIn = (CsActivityIn) $.GetRequest("CsActivityIn$"+id);
		if(csActivityIn!=null)
			return csActivityIn;
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");		
		return csActivityInService.getCsActivityInById(id);
	}
	
	
	/**
	 * 根据ID取活动参与的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsActivityIn.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsActivityIn csActivityIn = get(id);
		if(csActivityIn!=null){
			String strValue = csActivityIn.getCsaiActivity$();
			if(!"CsaiActivity".equals("CsaiActivity"))
				strValue+="("+csActivityIn.getCsaiActivity$()+")";
			MemCache.setValue(CsActivityIn.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsaiActivity$();
		if(!"CsaiActivity".equals("CsaiActivity"))
			keyValue+="("+this.getCsaiActivity$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有活动参与
	 * @return
	 */
	public static @api List<CsActivityIn> getCsActivityInList(Map params,Integer size){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		return csActivityInService.getCsActivityInList(params, size);
	}
	
	/**
	 * 获取活动参与分页
	 * @return
	 */
	public static @api Page<CsActivityIn> getCsActivityInPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		return csActivityInService.getCsActivityInPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取活动参与
	 * @param params
	 * @return
	 */
	public static @api CsActivityIn getCsActivityIn(Map params){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		return csActivityInService.getCsActivityIn(params);
	}
	
	/**
	 * 获取活动参与数
	 * @return
	 */
	public static @api Long getCsActivityInCount(Map params){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		return csActivityInService.getCsActivityInCount(params);
	}
		
		
	/**
	 * 获取活动参与自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsActivityInEval(String eval,Map params){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		return csActivityInService.getCsActivityInEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsActivityIn(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		csActivityInService.updateCsActivityInByConfirm(set, where);
	}
	
	
	/**
	 * 保存活动参与对象
	 * @param params
	 * @return
	 */
	public CsActivityIn save(){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		if(this.getCsaiId()!=null)
			csActivityInService.updateCsActivityIn(this);
		else
			return csActivityInService.saveCsActivityIn(this);
		return this;
	}
	
	
	/**
	 * 更新活动参与对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		csActivityInService.updateCsActivityIn$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		if($.equals($.config("logic_delete"),"true"))
			csActivityInService.removeCsActivityInById(this.getCsaiId());
		else
			csActivityInService.deleteCsActivityInById(this.getCsaiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		return csActivityInService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsaiId(){
		return this.csaiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsaiId$(){
		String strValue="";
		 strValue=$.str(this.getCsaiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsaiId(Long csaiId){
		this.csaiId = csaiId;
		this.setSeted(F.csaiId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsaiHost(){
		return this.csaiHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsaiHost$(){
		String strValue="";
		if(this.getCsaiHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsaiHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsaiHost(Long csaiHost){
		this.csaiHost = csaiHost;
		this.setSeted(F.csaiHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csaiHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsaiHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsaiHost()!=null){
 			srvHost = SrvHost.get(this.getCsaiHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsaiHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动 [非空] [CsActivity]     
	**/
	public Long getCsaiActivity(){
		return this.csaiActivity;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getCsaiActivity$(){
		String strValue="";
		if(this.getCsaiActivity()!=null){
				strValue+=$.str(CsActivity.getKeyValue(this.getCsaiActivity()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动 [非空] [CsActivity]     
	**/
	public void setCsaiActivity(Long csaiActivity){
		this.csaiActivity = csaiActivity;
		this.setSeted(F.csaiActivity);
	}
	/**
	* 获取关联对象[营销活动]
	**/	 			
 	public CsActivity get$csaiActivity(){
 		com.ccclubs.model.CsActivity csActivity = (com.ccclubs.model.CsActivity) $.GetRequest("CsActivity$"+this.getCsaiActivity());
 		if(csActivity!=null)
			return csActivity;
		if(this.getCsaiActivity()!=null){
 			csActivity = CsActivity.get(this.getCsaiActivity());
 		}
 		$.SetRequest("CsActivity$"+this.getCsaiActivity(), csActivity);
	 	return csActivity;
	}
	/*******************************参与人**********************************/	
	/**
	* 参与人 [非空] [CsMember]     
	**/
	public Long getCsaiMember(){
		return this.csaiMember;
	}
	/**
	* 获取参与人格式化(toString)
	**/
	public String getCsaiMember$(){
		String strValue="";
		if(this.getCsaiMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsaiMember()));
		}			
	 	 return strValue;
	}
	/**
	* 参与人 [非空] [CsMember]     
	**/
	public void setCsaiMember(Long csaiMember){
		this.csaiMember = csaiMember;
		this.setSeted(F.csaiMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csaiMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsaiMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsaiMember()!=null){
 			csMember = CsMember.get(this.getCsaiMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsaiMember(), csMember);
	 	return csMember;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsaiUpdateTime(){
		return this.csaiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsaiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsaiUpdateTime(Date csaiUpdateTime){
		this.csaiUpdateTime = csaiUpdateTime;
		this.setSeted(F.csaiUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsaiAddTime(){
		return this.csaiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsaiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsaiAddTime(Date csaiAddTime){
		this.csaiAddTime = csaiAddTime;
		this.setSeted(F.csaiAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsaiRemark(){
		return this.csaiRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsaiRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsaiRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsaiRemark(String csaiRemark){
		this.csaiRemark = csaiRemark;
		this.setSeted(F.csaiRemark);
	}
	/*******************************JSON数据**********************************/	
	/**
	* JSON数据     每个活动根据自己情况自定义数据存储  
	**/
	public String getCsaiData(){
		return this.csaiData;
	}
	/**
	* 获取JSON数据格式化(toString)
	**/
	public String getCsaiData$(){
		String strValue="";
		 strValue=$.str(this.getCsaiData());
	 	 return strValue;
	}
	/**
	* JSON数据     每个活动根据自己情况自定义数据存储  
	**/
	public void setCsaiData(String csaiData){
		this.csaiData = csaiData;
		this.setSeted(F.csaiData);
	}
	/*******************************状态码**********************************/	
	/**
	* 状态码     每个活动根据自己情况自定义状态码  
	**/
	public String getCsaiState(){
		return this.csaiState;
	}
	/**
	* 获取状态码格式化(toString)
	**/
	public String getCsaiState$(){
		String strValue="";
		 strValue=$.str(this.getCsaiState());
	 	 return strValue;
	}
	/**
	* 状态码     每个活动根据自己情况自定义状态码  
	**/
	public void setCsaiState(String csaiState){
		this.csaiState = csaiState;
		this.setSeted(F.csaiState);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsaiStatus(){
		return this.csaiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsaiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsaiStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsaiStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsaiStatus(Short csaiStatus){
		this.csaiStatus = csaiStatus;
		this.setSeted(F.csaiStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsActivityIn.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsActivityIn.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsActivityIn.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsActivityIn.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsActivityIn.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsActivityIn.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsActivityIn.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsActivityIn.this);
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
		public M csaiId(Object csaiId){this.put("csaiId", csaiId);return this;};
	 	/** and csai_id is null */
 		public M csaiIdNull(){if(this.get("csaiIdNot")==null)this.put("csaiIdNot", "");this.put("csaiId", null);return this;};
 		/** not .... */
 		public M csaiIdNot(){this.put("csaiIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csaiHost(Object csaiHost){this.put("csaiHost", csaiHost);return this;};
	 	/** and csai_host is null */
 		public M csaiHostNull(){if(this.get("csaiHostNot")==null)this.put("csaiHostNot", "");this.put("csaiHost", null);return this;};
 		/** not .... */
 		public M csaiHostNot(){this.put("csaiHostNot", "not");return this;};
		/** 所属活动 [非空] [CsActivity]      **/
		public M csaiActivity(Object csaiActivity){this.put("csaiActivity", csaiActivity);return this;};
	 	/** and csai_activity is null */
 		public M csaiActivityNull(){if(this.get("csaiActivityNot")==null)this.put("csaiActivityNot", "");this.put("csaiActivity", null);return this;};
 		/** not .... */
 		public M csaiActivityNot(){this.put("csaiActivityNot", "not");return this;};
		public M csaiActivity$on(CsActivity.M value){
			this.put("CsActivity", value);
			this.put("csaiActivity$on", value);
			return this;
		};	
		/** 参与人 [非空] [CsMember]      **/
		public M csaiMember(Object csaiMember){this.put("csaiMember", csaiMember);return this;};
	 	/** and csai_member is null */
 		public M csaiMemberNull(){if(this.get("csaiMemberNot")==null)this.put("csaiMemberNot", "");this.put("csaiMember", null);return this;};
 		/** not .... */
 		public M csaiMemberNot(){this.put("csaiMemberNot", "not");return this;};
		public M csaiMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csaiMember$on", value);
			return this;
		};	
		/** 修改时间 [非空]       **/
		public M csaiUpdateTime(Object csaiUpdateTime){this.put("csaiUpdateTime", csaiUpdateTime);return this;};
	 	/** and csai_update_time is null */
 		public M csaiUpdateTimeNull(){if(this.get("csaiUpdateTimeNot")==null)this.put("csaiUpdateTimeNot", "");this.put("csaiUpdateTime", null);return this;};
 		/** not .... */
 		public M csaiUpdateTimeNot(){this.put("csaiUpdateTimeNot", "not");return this;};
 		/** and csai_update_time >= ? */
 		public M csaiUpdateTimeStart(Object start){this.put("csaiUpdateTimeStart", start);return this;};			
 		/** and csai_update_time <= ? */
 		public M csaiUpdateTimeEnd(Object end){this.put("csaiUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csaiAddTime(Object csaiAddTime){this.put("csaiAddTime", csaiAddTime);return this;};
	 	/** and csai_add_time is null */
 		public M csaiAddTimeNull(){if(this.get("csaiAddTimeNot")==null)this.put("csaiAddTimeNot", "");this.put("csaiAddTime", null);return this;};
 		/** not .... */
 		public M csaiAddTimeNot(){this.put("csaiAddTimeNot", "not");return this;};
 		/** and csai_add_time >= ? */
 		public M csaiAddTimeStart(Object start){this.put("csaiAddTimeStart", start);return this;};			
 		/** and csai_add_time <= ? */
 		public M csaiAddTimeEnd(Object end){this.put("csaiAddTimeEnd", end);return this;};
		/** 备注信息        **/
		public M csaiRemark(Object csaiRemark){this.put("csaiRemark", csaiRemark);return this;};
	 	/** and csai_remark is null */
 		public M csaiRemarkNull(){if(this.get("csaiRemarkNot")==null)this.put("csaiRemarkNot", "");this.put("csaiRemark", null);return this;};
 		/** not .... */
 		public M csaiRemarkNot(){this.put("csaiRemarkNot", "not");return this;};
		/** JSON数据     每个活动根据自己情况自定义数据存储   **/
		public M csaiData(Object csaiData){this.put("csaiData", csaiData);return this;};
	 	/** and csai_data is null */
 		public M csaiDataNull(){if(this.get("csaiDataNot")==null)this.put("csaiDataNot", "");this.put("csaiData", null);return this;};
 		/** not .... */
 		public M csaiDataNot(){this.put("csaiDataNot", "not");return this;};
		/** 状态码     每个活动根据自己情况自定义状态码   **/
		public M csaiState(Object csaiState){this.put("csaiState", csaiState);return this;};
	 	/** and csai_state is null */
 		public M csaiStateNull(){if(this.get("csaiStateNot")==null)this.put("csaiStateNot", "");this.put("csaiState", null);return this;};
 		/** not .... */
 		public M csaiStateNot(){this.put("csaiStateNot", "not");return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csaiStatus(Object csaiStatus){this.put("csaiStatus", csaiStatus);return this;};
	 	/** and csai_status is null */
 		public M csaiStatusNull(){if(this.get("csaiStatusNot")==null)this.put("csaiStatusNot", "");this.put("csaiStatus", null);return this;};
 		/** not .... */
 		public M csaiStatusNot(){this.put("csaiStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有活动参与 **/
		public @api List<CsActivityIn> list(Integer size){
			return getCsActivityInList(this,size);
		}
		/** 获取活动参与分页 **/
		public @api Page<CsActivityIn> page(int page,int size){
			return getCsActivityInPage(page, size , this);
		}
		/** 根据查询条件取活动参与 **/
		public @api CsActivityIn get(){
			return getCsActivityIn(this);
		}
		/** 获取活动参与数 **/
		public @api Long count(){
			return getCsActivityInCount(this);
		}
		/** 获取活动参与表达式 **/
		public @api <T> T eval(String strEval){
			return getCsActivityInEval(strEval,this);
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
			updateCsActivityIn(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csaiId="csaiId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csaiHost="csaiHost";
		/** 所属活动 [非空] [CsActivity]      **/
		public final static @type(Long.class)  String csaiActivity="csaiActivity";
		/** 参与人 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csaiMember="csaiMember";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csaiUpdateTime="csaiUpdateTime";
	 	/** and csai_update_time >= ? */
 		public final static @type(Date.class) String csaiUpdateTimeStart="csaiUpdateTimeStart";
 		/** and csai_update_time <= ? */
 		public final static @type(Date.class) String csaiUpdateTimeEnd="csaiUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csaiAddTime="csaiAddTime";
	 	/** and csai_add_time >= ? */
 		public final static @type(Date.class) String csaiAddTimeStart="csaiAddTimeStart";
 		/** and csai_add_time <= ? */
 		public final static @type(Date.class) String csaiAddTimeEnd="csaiAddTimeEnd";
		/** 备注信息        **/
		public final static @type(String.class) @like String csaiRemark="csaiRemark";
		/** JSON数据     每个活动根据自己情况自定义数据存储   **/
		public final static @type(String.class) @like String csaiData="csaiData";
		/** 状态码     每个活动根据自己情况自定义状态码   **/
		public final static @type(String.class) @like String csaiState="csaiState";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csaiStatus="csaiStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csaiId="csai_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csaiHost="csai_host";
		/** 所属活动 [非空] [CsActivity]      **/
		public final static String csaiActivity="csai_activity";
		/** 参与人 [非空] [CsMember]      **/
		public final static String csaiMember="csai_member";
		/** 修改时间 [非空]       **/
		public final static String csaiUpdateTime="csai_update_time";
		/** 添加时间 [非空]       **/
		public final static String csaiAddTime="csai_add_time";
		/** 备注信息        **/
		public final static String csaiRemark="csai_remark";
		/** JSON数据     每个活动根据自己情况自定义数据存储   **/
		public final static String csaiData="csai_data";
		/** 状态码     每个活动根据自己情况自定义状态码   **/
		public final static String csaiState="csai_state";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csaiStatus="csai_status";
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
				$.Set(name,CsActivityIn.getCsActivityIn(params));
			else
				$.Set(name,CsActivityIn.getCsActivityInList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取活动参与数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsActivityIn) $.GetRequest("CsActivityIn$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsActivityIn.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsActivityIn.getCsActivityIn(params);
			else
				value = CsActivityIn.getCsActivityInList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsActivityIn.Get($.add(CsActivityIn.F.csaiId,param));
		else if(!$.empty(param.toString()))
			value = CsActivityIn.get(Long.valueOf(param.toString()));
		$.SetRequest("CsActivityIn$"+param.hashCode(), value);
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
	public void mergeSet(CsActivityIn old){
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