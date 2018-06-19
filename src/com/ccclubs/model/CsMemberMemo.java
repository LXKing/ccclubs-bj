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

@namespace("user/membermemo")
public @caption("会员备忘") @table("cs_member_memo") class CsMemberMemo implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csmm_id")   @primary  @note("  ") Long csmmId;// 主键 非空     
	private @caption("备忘内容") @column("csmm_content")    @note("  ") String csmmContent;// 非空     
	private @caption("所属会员") @column("csmm_member")    @relate("$csmmMember") @RelateClass(CsMember.class)  @note("  ") Long csmmMember;// 非空     
 	private CsMember $csmmMember;//关联对象[会员帐号]
	private @caption("操作人") @column("csmm_editor")    @relate("$csmmEditor") @RelateClass(SrvUser.class)  @note("  ") Long csmmEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csmmEditor;//关联对象[用户]
	private @caption("修改时间") @column("csmm_update_time")    @note("  ") Date csmmUpdateTime;// 非空     
	private @caption("添加时间") @column("csmm_add_time")    @note("  ") Date csmmAddTime;// 非空     
	private @caption("状态") @column("csmm_status")    @note(" 1:正常 0:无效  ") Short csmmStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsMemberMemo(){
	
	}
	
	//主键构造函数
	public CsMemberMemo(Long id){
		this.csmmId = id;
	}
	
	/**所有字段构造函数 CsMemberMemo(csmmContent,csmmMember,csmmEditor,csmmUpdateTime,csmmAddTime,csmmStatus)
	 CsMemberMemo(
	 	$.getString("csmmContent")//备忘内容 [非空]
	 	,$.getLong("csmmMember")//所属会员 [非空]
	 	,$.getLong("csmmEditor")//操作人 [非空]
	 	,$.getDate("csmmUpdateTime")//修改时间 [非空]
	 	,$.getDate("csmmAddTime")//添加时间 [非空]
	 	,$.getShort("csmmStatus")//状态 [非空]
	 )
	**/
	public CsMemberMemo(String csmmContent,Long csmmMember,Long csmmEditor,Date csmmUpdateTime,Date csmmAddTime,Short csmmStatus){
		this.csmmContent=csmmContent;
		this.csmmMember=csmmMember;
		this.csmmEditor=csmmEditor;
		this.csmmUpdateTime=csmmUpdateTime;
		this.csmmAddTime=csmmAddTime;
		this.csmmStatus=csmmStatus;
	}
	
	//设置非空字段
	public CsMemberMemo setNotNull(String csmmContent,Long csmmMember,Long csmmEditor,Date csmmUpdateTime,Date csmmAddTime,Short csmmStatus){
		this.csmmContent=csmmContent;
		this.csmmMember=csmmMember;
		this.csmmEditor=csmmEditor;
		this.csmmUpdateTime=csmmUpdateTime;
		this.csmmAddTime=csmmAddTime;
		this.csmmStatus=csmmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMemberMemo csmmId(Long csmmId){
		this.csmmId = csmmId;
		this.setSeted(F.csmmId);
		return this;
	}
	/** 备忘内容 [非空]       **/
	public CsMemberMemo csmmContent(String csmmContent){
		this.csmmContent = csmmContent;
		this.setSeted(F.csmmContent);
		return this;
	}
	/** 所属会员 [非空] [CsMember]      **/
	public CsMemberMemo csmmMember(Long csmmMember){
		this.csmmMember = csmmMember;
		this.setSeted(F.csmmMember);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsMemberMemo csmmEditor(Long csmmEditor){
		this.csmmEditor = csmmEditor;
		this.setSeted(F.csmmEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsMemberMemo csmmUpdateTime(Date csmmUpdateTime){
		this.csmmUpdateTime = csmmUpdateTime;
		this.setSeted(F.csmmUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMemberMemo csmmAddTime(Date csmmAddTime){
		this.csmmAddTime = csmmAddTime;
		this.setSeted(F.csmmAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsMemberMemo csmmStatus(Short csmmStatus){
		this.csmmStatus = csmmStatus;
		this.setSeted(F.csmmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMemberMemo clone(){
		CsMemberMemo clone = new CsMemberMemo();
		clone.csmmContent=this.csmmContent;
		clone.csmmMember=this.csmmMember;
		clone.csmmEditor=this.csmmEditor;
		clone.csmmUpdateTime=this.csmmUpdateTime;
		clone.csmmAddTime=this.csmmAddTime;
		clone.csmmStatus=this.csmmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员备忘
	 * @param id
	 * @return
	 */
	public static @api CsMemberMemo get(Long id){		
		return getCsMemberMemoById(id);
	}
	/**
	 * 获取所有会员备忘
	 * @return
	 */
	public static @api List<CsMemberMemo> list(Map params,Integer size){
		return getCsMemberMemoList(params,size);
	}
	/**
	 * 获取会员备忘分页
	 * @return
	 */
	public static @api Page<CsMemberMemo> page(int page,int size,Map params){
		return getCsMemberMemoPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员备忘
	 * @param params
	 * @return
	 */
	public static @api CsMemberMemo Get(Map params){
		return getCsMemberMemo(params);
	}
	/**
	 * 获取会员备忘数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMemberMemoCount(params);
	}
	/**
	 * 获取会员备忘数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMemberMemoEval(eval,params);
	}
	
	/**
	 * 根据ID取会员备忘
	 * @param id
	 * @return
	 */
	public static @api CsMemberMemo getCsMemberMemoById(Long id){		
		CsMemberMemo csMemberMemo = (CsMemberMemo) $.GetRequest("CsMemberMemo$"+id);
		if(csMemberMemo!=null)
			return csMemberMemo;
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");		
		return csMemberMemoService.getCsMemberMemoById(id);
	}
	
	
	/**
	 * 根据ID取会员备忘的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMemberMemo.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMemberMemo csMemberMemo = get(id);
		if(csMemberMemo!=null){
			String strValue = csMemberMemo.getCsmmContent$();
			if(!"CsmmContent".equals("CsmmContent"))
				strValue+="("+csMemberMemo.getCsmmContent$()+")";
			MemCache.setValue(CsMemberMemo.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmmContent$();
		if(!"CsmmContent".equals("CsmmContent"))
			keyValue+="("+this.getCsmmContent$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员备忘
	 * @return
	 */
	public static @api List<CsMemberMemo> getCsMemberMemoList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		return csMemberMemoService.getCsMemberMemoList(params, size);
	}
	
	/**
	 * 获取会员备忘分页
	 * @return
	 */
	public static @api Page<CsMemberMemo> getCsMemberMemoPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		return csMemberMemoService.getCsMemberMemoPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员备忘
	 * @param params
	 * @return
	 */
	public static @api CsMemberMemo getCsMemberMemo(Map params){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		return csMemberMemoService.getCsMemberMemo(params);
	}
	
	/**
	 * 获取会员备忘数
	 * @return
	 */
	public static @api Long getCsMemberMemoCount(Map params){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		return csMemberMemoService.getCsMemberMemoCount(params);
	}
		
		
	/**
	 * 获取会员备忘自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMemberMemoEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		return csMemberMemoService.getCsMemberMemoEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMemberMemo(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		csMemberMemoService.updateCsMemberMemoByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员备忘对象
	 * @param params
	 * @return
	 */
	public CsMemberMemo save(){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		if(this.getCsmmId()!=null)
			csMemberMemoService.updateCsMemberMemo(this);
		else
			return csMemberMemoService.saveCsMemberMemo(this);
		return this;
	}
	
	
	/**
	 * 更新会员备忘对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		csMemberMemoService.updateCsMemberMemo$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		if($.equals($.config("logic_delete"),"true"))
			csMemberMemoService.removeCsMemberMemoById(this.getCsmmId());
		else
			csMemberMemoService.deleteCsMemberMemoById(this.getCsmmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		return csMemberMemoService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmmId(){
		return this.csmmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmmId$(){
		String strValue="";
		 strValue=$.str(this.getCsmmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmmId(Long csmmId){
		this.csmmId = csmmId;
		this.setSeted(F.csmmId);
	}
	/*******************************备忘内容**********************************/	
	/**
	* 备忘内容 [非空]      
	**/
	public String getCsmmContent(){
		return this.csmmContent;
	}
	/**
	* 获取备忘内容格式化(toString)
	**/
	public String getCsmmContent$(){
		String strValue="";
		 strValue=$.str(this.getCsmmContent());
	 	 return strValue;
	}
	/**
	* 备忘内容 [非空]      
	**/
	public void setCsmmContent(String csmmContent){
		this.csmmContent = csmmContent;
		this.setSeted(F.csmmContent);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public Long getCsmmMember(){
		return this.csmmMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsmmMember$(){
		String strValue="";
		if(this.getCsmmMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmmMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public void setCsmmMember(Long csmmMember){
		this.csmmMember = csmmMember;
		this.setSeted(F.csmmMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csmmMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmmMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmmMember()!=null){
 			csMember = CsMember.get(this.getCsmmMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsmmMember(), csMember);
	 	return csMember;
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsmmEditor(){
		return this.csmmEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCsmmEditor$(){
		String strValue="";
		if(this.getCsmmEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsmmEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsmmEditor(Long csmmEditor){
		this.csmmEditor = csmmEditor;
		this.setSeted(F.csmmEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csmmEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsmmEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsmmEditor()!=null){
 			srvUser = SrvUser.get(this.getCsmmEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsmmEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsmmUpdateTime(){
		return this.csmmUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsmmUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmmUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsmmUpdateTime(Date csmmUpdateTime){
		this.csmmUpdateTime = csmmUpdateTime;
		this.setSeted(F.csmmUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmmAddTime(){
		return this.csmmAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsmmAddTime(Date csmmAddTime){
		this.csmmAddTime = csmmAddTime;
		this.setSeted(F.csmmAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsmmStatus(){
		return this.csmmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmmStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsmmStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsmmStatus(Short csmmStatus){
		this.csmmStatus = csmmStatus;
		this.setSeted(F.csmmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMemberMemo.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberMemo.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberMemo.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMemberMemo.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMemberMemo.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberMemo.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberMemo.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMemberMemo.this);
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
		public M csmmId(Object csmmId){this.put("csmmId", csmmId);return this;};
	 	/** and csmm_id is null */
 		public M csmmIdNull(){if(this.get("csmmIdNot")==null)this.put("csmmIdNot", "");this.put("csmmId", null);return this;};
 		/** not .... */
 		public M csmmIdNot(){this.put("csmmIdNot", "not");return this;};
		/** 备忘内容 [非空]       **/
		public M csmmContent(Object csmmContent){this.put("csmmContent", csmmContent);return this;};
	 	/** and csmm_content is null */
 		public M csmmContentNull(){if(this.get("csmmContentNot")==null)this.put("csmmContentNot", "");this.put("csmmContent", null);return this;};
 		/** not .... */
 		public M csmmContentNot(){this.put("csmmContentNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]      **/
		public M csmmMember(Object csmmMember){this.put("csmmMember", csmmMember);return this;};
	 	/** and csmm_member is null */
 		public M csmmMemberNull(){if(this.get("csmmMemberNot")==null)this.put("csmmMemberNot", "");this.put("csmmMember", null);return this;};
 		/** not .... */
 		public M csmmMemberNot(){this.put("csmmMemberNot", "not");return this;};
		public M csmmMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmmMember$on", value);
			return this;
		};	
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csmmEditor(Object csmmEditor){this.put("csmmEditor", csmmEditor);return this;};
	 	/** and csmm_editor is null */
 		public M csmmEditorNull(){if(this.get("csmmEditorNot")==null)this.put("csmmEditorNot", "");this.put("csmmEditor", null);return this;};
 		/** not .... */
 		public M csmmEditorNot(){this.put("csmmEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csmmUpdateTime(Object csmmUpdateTime){this.put("csmmUpdateTime", csmmUpdateTime);return this;};
	 	/** and csmm_update_time is null */
 		public M csmmUpdateTimeNull(){if(this.get("csmmUpdateTimeNot")==null)this.put("csmmUpdateTimeNot", "");this.put("csmmUpdateTime", null);return this;};
 		/** not .... */
 		public M csmmUpdateTimeNot(){this.put("csmmUpdateTimeNot", "not");return this;};
 		/** and csmm_update_time >= ? */
 		public M csmmUpdateTimeStart(Object start){this.put("csmmUpdateTimeStart", start);return this;};			
 		/** and csmm_update_time <= ? */
 		public M csmmUpdateTimeEnd(Object end){this.put("csmmUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csmmAddTime(Object csmmAddTime){this.put("csmmAddTime", csmmAddTime);return this;};
	 	/** and csmm_add_time is null */
 		public M csmmAddTimeNull(){if(this.get("csmmAddTimeNot")==null)this.put("csmmAddTimeNot", "");this.put("csmmAddTime", null);return this;};
 		/** not .... */
 		public M csmmAddTimeNot(){this.put("csmmAddTimeNot", "not");return this;};
 		/** and csmm_add_time >= ? */
 		public M csmmAddTimeStart(Object start){this.put("csmmAddTimeStart", start);return this;};			
 		/** and csmm_add_time <= ? */
 		public M csmmAddTimeEnd(Object end){this.put("csmmAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csmmStatus(Object csmmStatus){this.put("csmmStatus", csmmStatus);return this;};
	 	/** and csmm_status is null */
 		public M csmmStatusNull(){if(this.get("csmmStatusNot")==null)this.put("csmmStatusNot", "");this.put("csmmStatus", null);return this;};
 		/** not .... */
 		public M csmmStatusNot(){this.put("csmmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员备忘 **/
		public @api List<CsMemberMemo> list(Integer size){
			return getCsMemberMemoList(this,size);
		}
		/** 获取会员备忘分页 **/
		public @api Page<CsMemberMemo> page(int page,int size){
			return getCsMemberMemoPage(page, size , this);
		}
		/** 根据查询条件取会员备忘 **/
		public @api CsMemberMemo get(){
			return getCsMemberMemo(this);
		}
		/** 获取会员备忘数 **/
		public @api Long count(){
			return getCsMemberMemoCount(this);
		}
		/** 获取会员备忘表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMemberMemoEval(strEval,this);
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
			updateCsMemberMemo(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmmId="csmmId";
		/** 备忘内容 [非空]       **/
		public final static @type(String.class) @like String csmmContent="csmmContent";
		/** 所属会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csmmMember="csmmMember";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csmmEditor="csmmEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csmmUpdateTime="csmmUpdateTime";
	 	/** and csmm_update_time >= ? */
 		public final static @type(Date.class) String csmmUpdateTimeStart="csmmUpdateTimeStart";
 		/** and csmm_update_time <= ? */
 		public final static @type(Date.class) String csmmUpdateTimeEnd="csmmUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmmAddTime="csmmAddTime";
	 	/** and csmm_add_time >= ? */
 		public final static @type(Date.class) String csmmAddTimeStart="csmmAddTimeStart";
 		/** and csmm_add_time <= ? */
 		public final static @type(Date.class) String csmmAddTimeEnd="csmmAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csmmStatus="csmmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmmId="csmm_id";
		/** 备忘内容 [非空]       **/
		public final static String csmmContent="csmm_content";
		/** 所属会员 [非空] [CsMember]      **/
		public final static String csmmMember="csmm_member";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csmmEditor="csmm_editor";
		/** 修改时间 [非空]       **/
		public final static String csmmUpdateTime="csmm_update_time";
		/** 添加时间 [非空]       **/
		public final static String csmmAddTime="csmm_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csmmStatus="csmm_status";
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
				$.Set(name,CsMemberMemo.getCsMemberMemo(params));
			else
				$.Set(name,CsMemberMemo.getCsMemberMemoList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员备忘数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMemberMemo) $.GetRequest("CsMemberMemo$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMemberMemo.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMemberMemo.getCsMemberMemo(params);
			else
				value = CsMemberMemo.getCsMemberMemoList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMemberMemo.Get($.add(CsMemberMemo.F.csmmId,param));
		else if(!$.empty(param.toString()))
			value = CsMemberMemo.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMemberMemo$"+param.hashCode(), value);
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
	public void mergeSet(CsMemberMemo old){
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