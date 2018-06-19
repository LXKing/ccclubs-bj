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

@namespace("oa/cooperate/solve")
public @caption("问题处理记录") @table("tb_solve") class TbSolve implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbs_id")   @primary  @note("  ") Long tbsId;// 主键 非空     
	private @caption("城市") @column("tbs_host")    @relate("$tbsHost") @RelateClass(SrvHost.class)  @note("  ") Long tbsHost;// 非空     
 	private SrvHost $tbsHost;//关联对象[运营城市]
	private @caption("处理描述") @column("tbs_desc")    @note("  ") String tbsDesc;// 非空     
	private @caption("所属问题") @column("tbs_proplem")    @relate("$tbsProplem") @RelateClass(TbProblem.class)  @note("  ") Long tbsProplem;// 非空     
 	private TbProblem $tbsProplem;//关联对象[问题故障报修]
	private @caption("处理人") @column("tbs_user")    @relate("$tbsUser") @RelateClass(SrvUser.class)  @note("  ") Long tbsUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbsUser;//关联对象[用户]
	private @caption("流转到") @column("tbs_redirect")    @note("  ") Long tbsRedirect;//     
	private @caption("状态") @column("tbs_status")    @note(" 0:未受理 1:在处理 2:已流转 3:已结案  ") Short tbsStatus;// 非空 0:未受理 1:在处理 2:已流转 3:已结案     
	
	//默认构造函数
	public TbSolve(){
	
	}
	
	//主键构造函数
	public TbSolve(Long id){
		this.tbsId = id;
	}
	
	/**所有字段构造函数 TbSolve(tbsHost,tbsDesc,tbsProplem,tbsUser,tbsRedirect,tbsStatus)
	 TbSolve(
	 	$.getLong("tbsHost")//城市 [非空]
	 	,$.getString("tbsDesc")//处理描述 [非空]
	 	,$.getLong("tbsProplem")//所属问题 [非空]
	 	,$.getLong("tbsUser")//处理人 [非空]
	 	,$.getLong("tbsRedirect")//流转到
	 	,$.getShort("tbsStatus")//状态 [非空]
	 )
	**/
	public TbSolve(Long tbsHost,String tbsDesc,Long tbsProplem,Long tbsUser,Long tbsRedirect,Short tbsStatus){
		this.tbsHost=tbsHost;
		this.tbsDesc=tbsDesc;
		this.tbsProplem=tbsProplem;
		this.tbsUser=tbsUser;
		this.tbsRedirect=tbsRedirect;
		this.tbsStatus=tbsStatus;
	}
	
	//设置非空字段
	public TbSolve setNotNull(Long tbsHost,String tbsDesc,Long tbsProplem,Long tbsUser,Short tbsStatus){
		this.tbsHost=tbsHost;
		this.tbsDesc=tbsDesc;
		this.tbsProplem=tbsProplem;
		this.tbsUser=tbsUser;
		this.tbsStatus=tbsStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbSolve tbsId(Long tbsId){
		this.tbsId = tbsId;
		this.setSeted(F.tbsId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbSolve tbsHost(Long tbsHost){
		this.tbsHost = tbsHost;
		this.setSeted(F.tbsHost);
		return this;
	}
	/** 处理描述 [非空]       **/
	public TbSolve tbsDesc(String tbsDesc){
		this.tbsDesc = tbsDesc;
		this.setSeted(F.tbsDesc);
		return this;
	}
	/** 所属问题 [非空] [TbProblem]      **/
	public TbSolve tbsProplem(Long tbsProplem){
		this.tbsProplem = tbsProplem;
		this.setSeted(F.tbsProplem);
		return this;
	}
	/** 处理人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSolve tbsUser(Long tbsUser){
		this.tbsUser = tbsUser;
		this.setSeted(F.tbsUser);
		return this;
	}
	/** 流转到        **/
	public TbSolve tbsRedirect(Long tbsRedirect){
		this.tbsRedirect = tbsRedirect;
		this.setSeted(F.tbsRedirect);
		return this;
	}
	/** 状态 [非空]   0:未受理 1:在处理 2:已流转 3:已结案     **/
	public TbSolve tbsStatus(Short tbsStatus){
		this.tbsStatus = tbsStatus;
		this.setSeted(F.tbsStatus);
		return this;
	}
	
	
	//克隆对象
	public TbSolve clone(){
		TbSolve clone = new TbSolve();
		clone.tbsHost=this.tbsHost;
		clone.tbsDesc=this.tbsDesc;
		clone.tbsProplem=this.tbsProplem;
		clone.tbsUser=this.tbsUser;
		clone.tbsStatus=this.tbsStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取问题处理记录
	 * @param id
	 * @return
	 */
	public static @api TbSolve get(Long id){		
		return getTbSolveById(id);
	}
	/**
	 * 获取所有问题处理记录
	 * @return
	 */
	public static @api List<TbSolve> list(Map params,Integer size){
		return getTbSolveList(params,size);
	}
	/**
	 * 获取问题处理记录分页
	 * @return
	 */
	public static @api Page<TbSolve> page(int page,int size,Map params){
		return getTbSolvePage(page, size , params);
	}
	/**
	 * 根据查询条件取问题处理记录
	 * @param params
	 * @return
	 */
	public static @api TbSolve Get(Map params){
		return getTbSolve(params);
	}
	/**
	 * 获取问题处理记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbSolveCount(params);
	}
	/**
	 * 获取问题处理记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbSolveEval(eval,params);
	}
	
	/**
	 * 根据ID取问题处理记录
	 * @param id
	 * @return
	 */
	public static @api TbSolve getTbSolveById(Long id){		
		TbSolve tbSolve = (TbSolve) $.GetRequest("TbSolve$"+id);
		if(tbSolve!=null)
			return tbSolve;
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");		
		return tbSolveService.getTbSolveById(id);
	}
	
	
	/**
	 * 根据ID取问题处理记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbSolve.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbSolve tbSolve = get(id);
		if(tbSolve!=null){
			String strValue = tbSolve.getTbsDesc$();
			if(!"TbsDesc".equals("TbsDesc"))
				strValue+="("+tbSolve.getTbsDesc$()+")";
			MemCache.setValue(TbSolve.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbsDesc$();
		if(!"TbsDesc".equals("TbsDesc"))
			keyValue+="("+this.getTbsDesc$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有问题处理记录
	 * @return
	 */
	public static @api List<TbSolve> getTbSolveList(Map params,Integer size){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		return tbSolveService.getTbSolveList(params, size);
	}
	
	/**
	 * 获取问题处理记录分页
	 * @return
	 */
	public static @api Page<TbSolve> getTbSolvePage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		return tbSolveService.getTbSolvePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取问题处理记录
	 * @param params
	 * @return
	 */
	public static @api TbSolve getTbSolve(Map params){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		return tbSolveService.getTbSolve(params);
	}
	
	/**
	 * 获取问题处理记录数
	 * @return
	 */
	public static @api Long getTbSolveCount(Map params){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		return tbSolveService.getTbSolveCount(params);
	}
		
		
	/**
	 * 获取问题处理记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbSolveEval(String eval,Map params){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		return tbSolveService.getTbSolveEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbSolve(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		tbSolveService.updateTbSolveByConfirm(set, where);
	}
	
	
	/**
	 * 保存问题处理记录对象
	 * @param params
	 * @return
	 */
	public TbSolve save(){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		if(this.getTbsId()!=null)
			tbSolveService.updateTbSolve(this);
		else
			return tbSolveService.saveTbSolve(this);
		return this;
	}
	
	
	/**
	 * 更新问题处理记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		tbSolveService.updateTbSolve$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		if($.equals($.config("logic_delete"),"true"))
			tbSolveService.removeTbSolveById(this.getTbsId());
		else
			tbSolveService.deleteTbSolveById(this.getTbsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbSolveService tbSolveService = $.GetSpringBean("tbSolveService");
		return tbSolveService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbsId(){
		return this.tbsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbsId$(){
		String strValue="";
		 strValue=$.str(this.getTbsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbsId(Long tbsId){
		this.tbsId = tbsId;
		this.setSeted(F.tbsId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbsHost(){
		return this.tbsHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbsHost$(){
		String strValue="";
		if(this.getTbsHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbsHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbsHost(Long tbsHost){
		this.tbsHost = tbsHost;
		this.setSeted(F.tbsHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbsHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbsHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbsHost()!=null){
 			srvHost = SrvHost.get(this.getTbsHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbsHost(), srvHost);
	 	return srvHost;
	}
	/*******************************处理描述**********************************/	
	/**
	* 处理描述 [非空]      
	**/
	public String getTbsDesc(){
		return this.tbsDesc;
	}
	/**
	* 获取处理描述格式化(toString)
	**/
	public String getTbsDesc$(){
		String strValue="";
		 strValue=$.str(this.getTbsDesc());
	 	 return strValue;
	}
	/**
	* 处理描述 [非空]      
	**/
	public void setTbsDesc(String tbsDesc){
		this.tbsDesc = tbsDesc;
		this.setSeted(F.tbsDesc);
	}
	/*******************************所属问题**********************************/	
	/**
	* 所属问题 [非空] [TbProblem]     
	**/
	public Long getTbsProplem(){
		return this.tbsProplem;
	}
	/**
	* 获取所属问题格式化(toString)
	**/
	public String getTbsProplem$(){
		String strValue="";
		if(this.getTbsProplem()!=null){
				strValue+=$.str(TbProblem.getKeyValue(this.getTbsProplem()));
		}			
	 	 return strValue;
	}
	/**
	* 所属问题 [非空] [TbProblem]     
	**/
	public void setTbsProplem(Long tbsProplem){
		this.tbsProplem = tbsProplem;
		this.setSeted(F.tbsProplem);
	}
	/**
	* 获取关联对象[问题故障报修]
	**/	 			
 	public TbProblem get$tbsProplem(){
 		com.ccclubs.model.TbProblem tbProblem = (com.ccclubs.model.TbProblem) $.GetRequest("TbProblem$"+this.getTbsProplem());
 		if(tbProblem!=null)
			return tbProblem;
		if(this.getTbsProplem()!=null){
 			tbProblem = TbProblem.get(this.getTbsProplem());
 		}
 		$.SetRequest("TbProblem$"+this.getTbsProplem(), tbProblem);
	 	return tbProblem;
	}
	/*******************************处理人**********************************/	
	/**
	* 处理人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbsUser(){
		return this.tbsUser;
	}
	/**
	* 获取处理人格式化(toString)
	**/
	public String getTbsUser$(){
		String strValue="";
		if(this.getTbsUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbsUser()));
		}			
	 	 return strValue;
	}
	/**
	* 处理人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbsUser(Long tbsUser){
		this.tbsUser = tbsUser;
		this.setSeted(F.tbsUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbsUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbsUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbsUser()!=null){
 			srvUser = SrvUser.get(this.getTbsUser());
 		}
 		$.SetRequest("SrvUser$"+this.getTbsUser(), srvUser);
	 	return srvUser;
	}
	/*******************************流转到**********************************/	
	/**
	* 流转到       
	**/
	public Long getTbsRedirect(){
		return this.tbsRedirect;
	}
	/**
	* 获取流转到格式化(toString)
	**/
	public String getTbsRedirect$(){
		String strValue="";
		 strValue=$.str(this.getTbsRedirect());
	 	 return strValue;
	}
	/**
	* 流转到       
	**/
	public void setTbsRedirect(Long tbsRedirect){
		this.tbsRedirect = tbsRedirect;
		this.setSeted(F.tbsRedirect);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:未受理 1:在处理 2:已流转 3:已结案    
	**/
	public Short getTbsStatus(){
		return this.tbsStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbsStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbsStatus()),"0"))
			strValue=$.str("未受理");		 
		 if($.equals($.str(this.getTbsStatus()),"1"))
			strValue=$.str("在处理");		 
		 if($.equals($.str(this.getTbsStatus()),"2"))
			strValue=$.str("已流转");		 
		 if($.equals($.str(this.getTbsStatus()),"3"))
			strValue=$.str("已结案");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:未受理 1:在处理 2:已流转 3:已结案    
	**/
	public void setTbsStatus(Short tbsStatus){
		this.tbsStatus = tbsStatus;
		this.setSeted(F.tbsStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbSolve.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSolve.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSolve.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbSolve.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbSolve.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSolve.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSolve.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbSolve.this);
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
		public M tbsId(Object tbsId){this.put("tbsId", tbsId);return this;};
	 	/** and tbs_id is null */
 		public M tbsIdNull(){if(this.get("tbsIdNot")==null)this.put("tbsIdNot", "");this.put("tbsId", null);return this;};
 		/** not .... */
 		public M tbsIdNot(){this.put("tbsIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbsHost(Object tbsHost){this.put("tbsHost", tbsHost);return this;};
	 	/** and tbs_host is null */
 		public M tbsHostNull(){if(this.get("tbsHostNot")==null)this.put("tbsHostNot", "");this.put("tbsHost", null);return this;};
 		/** not .... */
 		public M tbsHostNot(){this.put("tbsHostNot", "not");return this;};
		/** 处理描述 [非空]       **/
		public M tbsDesc(Object tbsDesc){this.put("tbsDesc", tbsDesc);return this;};
	 	/** and tbs_desc is null */
 		public M tbsDescNull(){if(this.get("tbsDescNot")==null)this.put("tbsDescNot", "");this.put("tbsDesc", null);return this;};
 		/** not .... */
 		public M tbsDescNot(){this.put("tbsDescNot", "not");return this;};
		/** 所属问题 [非空] [TbProblem]      **/
		public M tbsProplem(Object tbsProplem){this.put("tbsProplem", tbsProplem);return this;};
	 	/** and tbs_proplem is null */
 		public M tbsProplemNull(){if(this.get("tbsProplemNot")==null)this.put("tbsProplemNot", "");this.put("tbsProplem", null);return this;};
 		/** not .... */
 		public M tbsProplemNot(){this.put("tbsProplemNot", "not");return this;};
		public M tbsProplem$on(TbProblem.M value){
			this.put("TbProblem", value);
			this.put("tbsProplem$on", value);
			return this;
		};	
		/** 处理人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbsUser(Object tbsUser){this.put("tbsUser", tbsUser);return this;};
	 	/** and tbs_user is null */
 		public M tbsUserNull(){if(this.get("tbsUserNot")==null)this.put("tbsUserNot", "");this.put("tbsUser", null);return this;};
 		/** not .... */
 		public M tbsUserNot(){this.put("tbsUserNot", "not");return this;};
		/** 流转到        **/
		public M tbsRedirect(Object tbsRedirect){this.put("tbsRedirect", tbsRedirect);return this;};
	 	/** and tbs_redirect is null */
 		public M tbsRedirectNull(){if(this.get("tbsRedirectNot")==null)this.put("tbsRedirectNot", "");this.put("tbsRedirect", null);return this;};
 		/** not .... */
 		public M tbsRedirectNot(){this.put("tbsRedirectNot", "not");return this;};
		/** and tbs_redirect >= ? */
		public M tbsRedirectMin(Object min){this.put("tbsRedirectMin", min);return this;};
		/** and tbs_redirect <= ? */
		public M tbsRedirectMax(Object max){this.put("tbsRedirectMax", max);return this;};
		/** 状态 [非空]   0:未受理 1:在处理 2:已流转 3:已结案     **/
		public M tbsStatus(Object tbsStatus){this.put("tbsStatus", tbsStatus);return this;};
	 	/** and tbs_status is null */
 		public M tbsStatusNull(){if(this.get("tbsStatusNot")==null)this.put("tbsStatusNot", "");this.put("tbsStatus", null);return this;};
 		/** not .... */
 		public M tbsStatusNot(){this.put("tbsStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有问题处理记录 **/
		public @api List<TbSolve> list(Integer size){
			return getTbSolveList(this,size);
		}
		/** 获取问题处理记录分页 **/
		public @api Page<TbSolve> page(int page,int size){
			return getTbSolvePage(page, size , this);
		}
		/** 根据查询条件取问题处理记录 **/
		public @api TbSolve get(){
			return getTbSolve(this);
		}
		/** 获取问题处理记录数 **/
		public @api Long count(){
			return getTbSolveCount(this);
		}
		/** 获取问题处理记录表达式 **/
		public @api <T> T eval(String strEval){
			return getTbSolveEval(strEval,this);
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
			updateTbSolve(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbsId="tbsId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbsHost="tbsHost";
		/** 处理描述 [非空]       **/
		public final static @type(String.class) @like String tbsDesc="tbsDesc";
		/** 所属问题 [非空] [TbProblem]      **/
		public final static @type(Long.class)  String tbsProplem="tbsProplem";
		/** 处理人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbsUser="tbsUser";
		/** 流转到        **/
		public final static @type(Long.class)  String tbsRedirect="tbsRedirect";
		/** and tbs_redirect >= ? */
		public final static @type(Long.class) String tbsRedirectMin="tbsRedirectMin";
		/** and tbs_redirect <= ? */
		public final static @type(Long.class) String tbsRedirectMax="tbsRedirectMax";
		/** 状态 [非空]   0:未受理 1:在处理 2:已流转 3:已结案     **/
		public final static @type(Short.class)  String tbsStatus="tbsStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbsId="tbs_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbsHost="tbs_host";
		/** 处理描述 [非空]       **/
		public final static String tbsDesc="tbs_desc";
		/** 所属问题 [非空] [TbProblem]      **/
		public final static String tbsProplem="tbs_proplem";
		/** 处理人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbsUser="tbs_user";
		/** 流转到        **/
		public final static String tbsRedirect="tbs_redirect";
		/** 状态 [非空]   0:未受理 1:在处理 2:已流转 3:已结案     **/
		public final static String tbsStatus="tbs_status";
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
				$.Set(name,TbSolve.getTbSolve(params));
			else
				$.Set(name,TbSolve.getTbSolveList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取问题处理记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbSolve) $.GetRequest("TbSolve$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbSolve.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbSolve.getTbSolve(params);
			else
				value = TbSolve.getTbSolveList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbSolve.Get($.add(TbSolve.F.tbsId,param));
		else if(!$.empty(param.toString()))
			value = TbSolve.get(Long.valueOf(param.toString()));
		$.SetRequest("TbSolve$"+param.hashCode(), value);
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
	public void mergeSet(TbSolve old){
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