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

@namespace("csm/revisit")
public @caption("用户回访") @table("cs_revisit") class CsRevisit implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csr_id")   @primary  @note("  ") Long csrId;// 主键 非空     
	private @caption("城市") @column("csr_host")    @relate("$csrHost") @RelateClass(SrvHost.class)  @note("  ") Long csrHost;// 非空     
 	private SrvHost $csrHost;//关联对象[运营城市]
	private @caption("被访会员") @column("csr_member")    @relate("$csrMember") @RelateClass(CsMember.class)  @note("  ") Long csrMember;// 非空     
 	private CsMember $csrMember;//关联对象[会员帐号]
	private @caption("回访人") @column("csr_visitor")    @relate("$csrVisitor") @RelateClass(SrvUser.class)  @note("  系统客服人员") Long csrVisitor;// 非空  系统客服人员  ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csrVisitor;//关联对象[用户]
	private @caption("回访类型") @column("csr_type")    @relate("$csrType") @RelateClass(CsQuestTemplate.class)  @note("  ") Long csrType;// 非空    select * from cs_quest_template where csqt_type=1 
 	private CsQuestTemplate $csrType;//关联对象[问卷模板]
	private @caption("回访结果") @column("csr_response")  @hidden   @note("  ") String csrResponse;// 非空     
	private @caption("备注信息") @column("csr_remark")    @note("  ") String csrRemark;//     
	private @caption("修改时间") @column("csr_update_time")    @note("  ") Date csrUpdateTime;// 非空     
	private @caption("回访时间") @column("csr_add_time")    @note("  ") Date csrAddTime;// 非空     
	private @caption("回访状态") @column("csr_status")    @note(" 1:回访完成 0:待处理  ") Short csrStatus;// 非空 1:回访完成 0:待处理     
	
	//默认构造函数
	public CsRevisit(){
	
	}
	
	//主键构造函数
	public CsRevisit(Long id){
		this.csrId = id;
	}
	
	/**所有字段构造函数 CsRevisit(csrHost,csrMember,csrVisitor,csrType,csrResponse,csrRemark,csrUpdateTime,csrAddTime,csrStatus)
	 CsRevisit(
	 	$.getLong("csrHost")//城市 [非空]
	 	,$.getLong("csrMember")//被访会员 [非空]
	 	,$.getLong("csrVisitor")//回访人 [非空]
	 	,$.getLong("csrType")//回访类型 [非空]
	 	,$.getString("csrResponse")//回访结果 [非空]
	 	,$.getString("csrRemark")//备注信息
	 	,$.getDate("csrUpdateTime")//修改时间 [非空]
	 	,$.getDate("csrAddTime")//回访时间 [非空]
	 	,$.getShort("csrStatus")//回访状态 [非空]
	 )
	**/
	public CsRevisit(Long csrHost,Long csrMember,Long csrVisitor,Long csrType,String csrResponse,String csrRemark,Date csrUpdateTime,Date csrAddTime,Short csrStatus){
		this.csrHost=csrHost;
		this.csrMember=csrMember;
		this.csrVisitor=csrVisitor;
		this.csrType=csrType;
		this.csrResponse=csrResponse;
		this.csrRemark=csrRemark;
		this.csrUpdateTime=csrUpdateTime;
		this.csrAddTime=csrAddTime;
		this.csrStatus=csrStatus;
	}
	
	//设置非空字段
	public CsRevisit setNotNull(Long csrHost,Long csrMember,Long csrVisitor,Long csrType,String csrResponse,Date csrUpdateTime,Date csrAddTime,Short csrStatus){
		this.csrHost=csrHost;
		this.csrMember=csrMember;
		this.csrVisitor=csrVisitor;
		this.csrType=csrType;
		this.csrResponse=csrResponse;
		this.csrUpdateTime=csrUpdateTime;
		this.csrAddTime=csrAddTime;
		this.csrStatus=csrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRevisit csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsRevisit csrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
		return this;
	}
	/** 被访会员 [非空] [CsMember]      **/
	public CsRevisit csrMember(Long csrMember){
		this.csrMember = csrMember;
		this.setSeted(F.csrMember);
		return this;
	}
	/** 回访人 [非空] [SrvUser]   系统客服人员  ${basePath}${proname}/permissions/user_tree.do **/
	public CsRevisit csrVisitor(Long csrVisitor){
		this.csrVisitor = csrVisitor;
		this.setSeted(F.csrVisitor);
		return this;
	}
	/** 回访类型 [非空] [CsQuestTemplate]     select * from cs_quest_template where csqt_type=1 **/
	public CsRevisit csrType(Long csrType){
		this.csrType = csrType;
		this.setSeted(F.csrType);
		return this;
	}
	/** 回访结果 [非空]       **/
	public CsRevisit csrResponse(String csrResponse){
		this.csrResponse = csrResponse;
		this.setSeted(F.csrResponse);
		return this;
	}
	/** 备注信息        **/
	public CsRevisit csrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsRevisit csrUpdateTime(Date csrUpdateTime){
		this.csrUpdateTime = csrUpdateTime;
		this.setSeted(F.csrUpdateTime);
		return this;
	}
	/** 回访时间 [非空]       **/
	public CsRevisit csrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
		return this;
	}
	/** 回访状态 [非空]   1:回访完成 0:待处理     **/
	public CsRevisit csrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsRevisit clone(){
		CsRevisit clone = new CsRevisit();
		clone.csrHost=this.csrHost;
		clone.csrMember=this.csrMember;
		clone.csrVisitor=this.csrVisitor;
		clone.csrType=this.csrType;
		clone.csrResponse=this.csrResponse;
		clone.csrUpdateTime=this.csrUpdateTime;
		clone.csrAddTime=this.csrAddTime;
		clone.csrStatus=this.csrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取用户回访
	 * @param id
	 * @return
	 */
	public static @api CsRevisit get(Long id){		
		return getCsRevisitById(id);
	}
	/**
	 * 获取所有用户回访
	 * @return
	 */
	public static @api List<CsRevisit> list(Map params,Integer size){
		return getCsRevisitList(params,size);
	}
	/**
	 * 获取用户回访分页
	 * @return
	 */
	public static @api Page<CsRevisit> page(int page,int size,Map params){
		return getCsRevisitPage(page, size , params);
	}
	/**
	 * 根据查询条件取用户回访
	 * @param params
	 * @return
	 */
	public static @api CsRevisit Get(Map params){
		return getCsRevisit(params);
	}
	/**
	 * 获取用户回访数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRevisitCount(params);
	}
	/**
	 * 获取用户回访数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRevisitEval(eval,params);
	}
	
	/**
	 * 根据ID取用户回访
	 * @param id
	 * @return
	 */
	public static @api CsRevisit getCsRevisitById(Long id){		
		CsRevisit csRevisit = (CsRevisit) $.GetRequest("CsRevisit$"+id);
		if(csRevisit!=null)
			return csRevisit;
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");		
		return csRevisitService.getCsRevisitById(id);
	}
	
	
	/**
	 * 根据ID取用户回访的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRevisit.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRevisit csRevisit = get(id);
		if(csRevisit!=null){
			String strValue = csRevisit.getCsrId$();
			if(!"CsrId".equals("CsrId"))
				strValue+="("+csRevisit.getCsrId$()+")";
			MemCache.setValue(CsRevisit.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrId$();
		if(!"CsrId".equals("CsrId"))
			keyValue+="("+this.getCsrId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有用户回访
	 * @return
	 */
	public static @api List<CsRevisit> getCsRevisitList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		return csRevisitService.getCsRevisitList(params, size);
	}
	
	/**
	 * 获取用户回访分页
	 * @return
	 */
	public static @api Page<CsRevisit> getCsRevisitPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		return csRevisitService.getCsRevisitPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取用户回访
	 * @param params
	 * @return
	 */
	public static @api CsRevisit getCsRevisit(Map params){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		return csRevisitService.getCsRevisit(params);
	}
	
	/**
	 * 获取用户回访数
	 * @return
	 */
	public static @api Long getCsRevisitCount(Map params){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		return csRevisitService.getCsRevisitCount(params);
	}
		
		
	/**
	 * 获取用户回访自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRevisitEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		return csRevisitService.getCsRevisitEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRevisit(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		csRevisitService.updateCsRevisitByConfirm(set, where);
	}
	
	
	/**
	 * 保存用户回访对象
	 * @param params
	 * @return
	 */
	public CsRevisit save(){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		if(this.getCsrId()!=null)
			csRevisitService.updateCsRevisit(this);
		else
			return csRevisitService.saveCsRevisit(this);
		return this;
	}
	
	
	/**
	 * 更新用户回访对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		csRevisitService.updateCsRevisit$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		if($.equals($.config("logic_delete"),"true"))
			csRevisitService.removeCsRevisitById(this.getCsrId());
		else
			csRevisitService.deleteCsRevisitById(this.getCsrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRevisitService csRevisitService = $.GetSpringBean("csRevisitService");
		return csRevisitService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrId(){
		return this.csrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrId$(){
		String strValue="";
		 strValue=$.str(this.getCsrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsrHost(){
		return this.csrHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsrHost$(){
		String strValue="";
		if(this.getCsrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csrHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsrHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsrHost()!=null){
 			srvHost = SrvHost.get(this.getCsrHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsrHost(), srvHost);
	 	return srvHost;
	}
	/*******************************被访会员**********************************/	
	/**
	* 被访会员 [非空] [CsMember]     
	**/
	public Long getCsrMember(){
		return this.csrMember;
	}
	/**
	* 获取被访会员格式化(toString)
	**/
	public String getCsrMember$(){
		String strValue="";
		if(this.getCsrMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsrMember()));
		}			
	 	 return strValue;
	}
	/**
	* 被访会员 [非空] [CsMember]     
	**/
	public void setCsrMember(Long csrMember){
		this.csrMember = csrMember;
		this.setSeted(F.csrMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csrMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsrMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsrMember()!=null){
 			csMember = CsMember.get(this.getCsrMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsrMember(), csMember);
	 	return csMember;
	}
	/*******************************回访人**********************************/	
	/**
	* 回访人 [非空] [SrvUser]   系统客服人员  ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsrVisitor(){
		return this.csrVisitor;
	}
	/**
	* 获取回访人格式化(toString)
	**/
	public String getCsrVisitor$(){
		String strValue="";
		if(this.getCsrVisitor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrVisitor()));
		}			
	 	 return strValue;
	}
	/**
	* 回访人 [非空] [SrvUser]   系统客服人员  ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsrVisitor(Long csrVisitor){
		this.csrVisitor = csrVisitor;
		this.setSeted(F.csrVisitor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csrVisitor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrVisitor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrVisitor()!=null){
 			srvUser = SrvUser.get(this.getCsrVisitor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrVisitor(), srvUser);
	 	return srvUser;
	}
	/*******************************回访类型**********************************/	
	/**
	* 回访类型 [非空] [CsQuestTemplate]     select * from cs_quest_template where csqt_type=1
	**/
	public Long getCsrType(){
		return this.csrType;
	}
	/**
	* 获取回访类型格式化(toString)
	**/
	public String getCsrType$(){
		String strValue="";
		if(this.getCsrType()!=null){
				strValue+=$.str(CsQuestTemplate.getKeyValue(this.getCsrType()));
		}			
	 	 return strValue;
	}
	/**
	* 回访类型 [非空] [CsQuestTemplate]     select * from cs_quest_template where csqt_type=1
	**/
	public void setCsrType(Long csrType){
		this.csrType = csrType;
		this.setSeted(F.csrType);
	}
	/**
	* 获取关联对象[问卷模板]
	**/	 			
 	public CsQuestTemplate get$csrType(){
 		com.ccclubs.model.CsQuestTemplate csQuestTemplate = (com.ccclubs.model.CsQuestTemplate) $.GetRequest("CsQuestTemplate$"+this.getCsrType());
 		if(csQuestTemplate!=null)
			return csQuestTemplate;
		if(this.getCsrType()!=null){
 			csQuestTemplate = CsQuestTemplate.get(this.getCsrType());
 		}
 		$.SetRequest("CsQuestTemplate$"+this.getCsrType(), csQuestTemplate);
	 	return csQuestTemplate;
	}
	/*******************************回访结果**********************************/	
	/**
	* 回访结果 [非空]      
	**/
	public String getCsrResponse(){
		return this.csrResponse;
	}
	/**
	* 获取回访结果格式化(toString)
	**/
	public String getCsrResponse$(){
		String strValue="";
		 strValue=$.str(this.getCsrResponse());
	 	 return strValue;
	}
	/**
	* 回访结果 [非空]      
	**/
	public void setCsrResponse(String csrResponse){
		this.csrResponse = csrResponse;
		this.setSeted(F.csrResponse);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsrRemark(){
		return this.csrRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsrUpdateTime(){
		return this.csrUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsrUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsrUpdateTime(Date csrUpdateTime){
		this.csrUpdateTime = csrUpdateTime;
		this.setSeted(F.csrUpdateTime);
	}
	/*******************************回访时间**********************************/	
	/**
	* 回访时间 [非空]      
	**/
	public Date getCsrAddTime(){
		return this.csrAddTime;
	}
	/**
	* 获取回访时间格式化(toString)
	**/
	public String getCsrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 回访时间 [非空]      
	**/
	public void setCsrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
	}
	/*******************************回访状态**********************************/	
	/**
	* 回访状态 [非空]   1:回访完成 0:待处理    
	**/
	public Short getCsrStatus(){
		return this.csrStatus;
	}
	/**
	* 获取回访状态格式化(toString)
	**/
	public String getCsrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsrStatus()),"1"))
			strValue=$.str("回访完成");		 
		 if($.equals($.str(this.getCsrStatus()),"0"))
			strValue=$.str("待处理");		 
	 	 return strValue;
	}
	/**
	* 回访状态 [非空]   1:回访完成 0:待处理    
	**/
	public void setCsrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRevisit.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRevisit.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRevisit.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRevisit.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRevisit.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRevisit.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRevisit.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRevisit.this);
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
		public M csrId(Object csrId){this.put("csrId", csrId);return this;};
	 	/** and csr_id is null */
 		public M csrIdNull(){if(this.get("csrIdNot")==null)this.put("csrIdNot", "");this.put("csrId", null);return this;};
 		/** not .... */
 		public M csrIdNot(){this.put("csrIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csrHost(Object csrHost){this.put("csrHost", csrHost);return this;};
	 	/** and csr_host is null */
 		public M csrHostNull(){if(this.get("csrHostNot")==null)this.put("csrHostNot", "");this.put("csrHost", null);return this;};
 		/** not .... */
 		public M csrHostNot(){this.put("csrHostNot", "not");return this;};
		/** 被访会员 [非空] [CsMember]      **/
		public M csrMember(Object csrMember){this.put("csrMember", csrMember);return this;};
	 	/** and csr_member is null */
 		public M csrMemberNull(){if(this.get("csrMemberNot")==null)this.put("csrMemberNot", "");this.put("csrMember", null);return this;};
 		/** not .... */
 		public M csrMemberNot(){this.put("csrMemberNot", "not");return this;};
		public M csrMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csrMember$on", value);
			return this;
		};	
		/** 回访人 [非空] [SrvUser]   系统客服人员  ${basePath}${proname}/permissions/user_tree.do **/
		public M csrVisitor(Object csrVisitor){this.put("csrVisitor", csrVisitor);return this;};
	 	/** and csr_visitor is null */
 		public M csrVisitorNull(){if(this.get("csrVisitorNot")==null)this.put("csrVisitorNot", "");this.put("csrVisitor", null);return this;};
 		/** not .... */
 		public M csrVisitorNot(){this.put("csrVisitorNot", "not");return this;};
		/** 回访类型 [非空] [CsQuestTemplate]     select * from cs_quest_template where csqt_type=1 **/
		public M csrType(Object csrType){this.put("csrType", csrType);return this;};
	 	/** and csr_type is null */
 		public M csrTypeNull(){if(this.get("csrTypeNot")==null)this.put("csrTypeNot", "");this.put("csrType", null);return this;};
 		/** not .... */
 		public M csrTypeNot(){this.put("csrTypeNot", "not");return this;};
		public M csrType$on(CsQuestTemplate.M value){
			this.put("CsQuestTemplate", value);
			this.put("csrType$on", value);
			return this;
		};	
		/** 回访结果 [非空]       **/
		public M csrResponse(Object csrResponse){this.put("csrResponse", csrResponse);return this;};
	 	/** and csr_response is null */
 		public M csrResponseNull(){if(this.get("csrResponseNot")==null)this.put("csrResponseNot", "");this.put("csrResponse", null);return this;};
 		/** not .... */
 		public M csrResponseNot(){this.put("csrResponseNot", "not");return this;};
		/** 备注信息        **/
		public M csrRemark(Object csrRemark){this.put("csrRemark", csrRemark);return this;};
	 	/** and csr_remark is null */
 		public M csrRemarkNull(){if(this.get("csrRemarkNot")==null)this.put("csrRemarkNot", "");this.put("csrRemark", null);return this;};
 		/** not .... */
 		public M csrRemarkNot(){this.put("csrRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csrUpdateTime(Object csrUpdateTime){this.put("csrUpdateTime", csrUpdateTime);return this;};
	 	/** and csr_update_time is null */
 		public M csrUpdateTimeNull(){if(this.get("csrUpdateTimeNot")==null)this.put("csrUpdateTimeNot", "");this.put("csrUpdateTime", null);return this;};
 		/** not .... */
 		public M csrUpdateTimeNot(){this.put("csrUpdateTimeNot", "not");return this;};
 		/** and csr_update_time >= ? */
 		public M csrUpdateTimeStart(Object start){this.put("csrUpdateTimeStart", start);return this;};			
 		/** and csr_update_time <= ? */
 		public M csrUpdateTimeEnd(Object end){this.put("csrUpdateTimeEnd", end);return this;};
		/** 回访时间 [非空]       **/
		public M csrAddTime(Object csrAddTime){this.put("csrAddTime", csrAddTime);return this;};
	 	/** and csr_add_time is null */
 		public M csrAddTimeNull(){if(this.get("csrAddTimeNot")==null)this.put("csrAddTimeNot", "");this.put("csrAddTime", null);return this;};
 		/** not .... */
 		public M csrAddTimeNot(){this.put("csrAddTimeNot", "not");return this;};
 		/** and csr_add_time >= ? */
 		public M csrAddTimeStart(Object start){this.put("csrAddTimeStart", start);return this;};			
 		/** and csr_add_time <= ? */
 		public M csrAddTimeEnd(Object end){this.put("csrAddTimeEnd", end);return this;};
		/** 回访状态 [非空]   1:回访完成 0:待处理     **/
		public M csrStatus(Object csrStatus){this.put("csrStatus", csrStatus);return this;};
	 	/** and csr_status is null */
 		public M csrStatusNull(){if(this.get("csrStatusNot")==null)this.put("csrStatusNot", "");this.put("csrStatus", null);return this;};
 		/** not .... */
 		public M csrStatusNot(){this.put("csrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有用户回访 **/
		public @api List<CsRevisit> list(Integer size){
			return getCsRevisitList(this,size);
		}
		/** 获取用户回访分页 **/
		public @api Page<CsRevisit> page(int page,int size){
			return getCsRevisitPage(page, size , this);
		}
		/** 根据查询条件取用户回访 **/
		public @api CsRevisit get(){
			return getCsRevisit(this);
		}
		/** 获取用户回访数 **/
		public @api Long count(){
			return getCsRevisitCount(this);
		}
		/** 获取用户回访表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRevisitEval(strEval,this);
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
			updateCsRevisit(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csrHost="csrHost";
		/** 被访会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csrMember="csrMember";
		/** 回访人 [非空] [SrvUser]   系统客服人员  ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csrVisitor="csrVisitor";
		/** 回访类型 [非空] [CsQuestTemplate]     select * from cs_quest_template where csqt_type=1 **/
		public final static @type(Long.class)  String csrType="csrType";
		/** 回访结果 [非空]       **/
		public final static @type(String.class)  String csrResponse="csrResponse";
		/** 备注信息        **/
		public final static @type(String.class) @like String csrRemark="csrRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csrUpdateTime="csrUpdateTime";
	 	/** and csr_update_time >= ? */
 		public final static @type(Date.class) String csrUpdateTimeStart="csrUpdateTimeStart";
 		/** and csr_update_time <= ? */
 		public final static @type(Date.class) String csrUpdateTimeEnd="csrUpdateTimeEnd";
		/** 回访时间 [非空]       **/
		public final static @type(Date.class)  String csrAddTime="csrAddTime";
	 	/** and csr_add_time >= ? */
 		public final static @type(Date.class) String csrAddTimeStart="csrAddTimeStart";
 		/** and csr_add_time <= ? */
 		public final static @type(Date.class) String csrAddTimeEnd="csrAddTimeEnd";
		/** 回访状态 [非空]   1:回访完成 0:待处理     **/
		public final static @type(Short.class)  String csrStatus="csrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csrHost="csr_host";
		/** 被访会员 [非空] [CsMember]      **/
		public final static String csrMember="csr_member";
		/** 回访人 [非空] [SrvUser]   系统客服人员  ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csrVisitor="csr_visitor";
		/** 回访类型 [非空] [CsQuestTemplate]     select * from cs_quest_template where csqt_type=1 **/
		public final static String csrType="csr_type";
		/** 回访结果 [非空]       **/
		public final static String csrResponse="csr_response";
		/** 备注信息        **/
		public final static String csrRemark="csr_remark";
		/** 修改时间 [非空]       **/
		public final static String csrUpdateTime="csr_update_time";
		/** 回访时间 [非空]       **/
		public final static String csrAddTime="csr_add_time";
		/** 回访状态 [非空]   1:回访完成 0:待处理     **/
		public final static String csrStatus="csr_status";
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
				$.Set(name,CsRevisit.getCsRevisit(params));
			else
				$.Set(name,CsRevisit.getCsRevisitList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取用户回访数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRevisit) $.GetRequest("CsRevisit$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRevisit.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRevisit.getCsRevisit(params);
			else
				value = CsRevisit.getCsRevisitList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRevisit.Get($.add(CsRevisit.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsRevisit.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRevisit$"+param.hashCode(), value);
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
	public void mergeSet(CsRevisit old){
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