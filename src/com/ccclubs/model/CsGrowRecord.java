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

@namespace("user/growrecord")
public @caption("成长记录") @table("cs_grow_record") class CsGrowRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csgr_id")   @primary  @note("  ") Long csgrId;// 主键 非空     
	private @caption("城市") @column("csgr_host")    @relate("$csgrHost") @RelateClass(SrvHost.class)  @note("  ") Long csgrHost;// 非空     
 	private SrvHost $csgrHost;//关联对象[运营城市]
	private @caption("会员") @column("csgr_member")    @relate("$csgrMember") @RelateClass(CsMember.class)  @note("  ") Long csgrMember;// 非空     
 	private CsMember $csgrMember;//关联对象[会员帐号]
	private @caption("描述") @column("csgr_desc")    @note("  ") String csgrDesc;// 非空     
	private @caption("类型") @column("csgr_type")    @note("  ") String csgrType;// 非空     
	private @caption("变化值") @column("csgr_amount")    @note("  ") Integer csgrAmount;// 非空     
	private @caption("关联数据") @column("csgr_relate")    @note("  数据格式：对象类名@对象ID") String csgrRelate;//  数据格式：对象类名@对象ID  CsTrouble,CsViolat,CsOrder 存储格式:类名@ID,其中类名包括CsTrouble或CsViolat或CsOrder
	private Object $csgrRelate;//泛关联对象，对象类名由csgrRelate字段中标明
	private @caption("修改时间") @column("csgr_update_time")    @note("  ") Date csgrUpdateTime;// 非空     
	private @caption("添加时间") @column("csgr_add_time")    @note("  ") Date csgrAddTime;// 非空     
	private @caption("状态") @column("csgr_status")    @note(" 1:正常 0:无效  ") Short csgrStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsGrowRecord(){
	
	}
	
	//主键构造函数
	public CsGrowRecord(Long id){
		this.csgrId = id;
	}
	
	/**所有字段构造函数 CsGrowRecord(csgrHost,csgrMember,csgrDesc,csgrType,csgrAmount,csgrRelate,csgrUpdateTime,csgrAddTime,csgrStatus)
	 CsGrowRecord(
	 	$.getLong("csgrHost")//城市 [非空]
	 	,$.getLong("csgrMember")//会员 [非空]
	 	,$.getString("csgrDesc")//描述 [非空]
	 	,$.getString("csgrType")//类型 [非空]
	 	,$.getInteger("csgrAmount")//变化值 [非空]
	 	,$.getString("csgrRelate")//关联数据
	 	,$.getDate("csgrUpdateTime")//修改时间 [非空]
	 	,$.getDate("csgrAddTime")//添加时间 [非空]
	 	,$.getShort("csgrStatus")//状态 [非空]
	 )
	**/
	public CsGrowRecord(Long csgrHost,Long csgrMember,String csgrDesc,String csgrType,Integer csgrAmount,String csgrRelate,Date csgrUpdateTime,Date csgrAddTime,Short csgrStatus){
		this.csgrHost=csgrHost;
		this.csgrMember=csgrMember;
		this.csgrDesc=csgrDesc;
		this.csgrType=csgrType;
		this.csgrAmount=csgrAmount;
		this.csgrRelate=csgrRelate;
		this.csgrUpdateTime=csgrUpdateTime;
		this.csgrAddTime=csgrAddTime;
		this.csgrStatus=csgrStatus;
	}
	
	//设置非空字段
	public CsGrowRecord setNotNull(Long csgrHost,Long csgrMember,String csgrDesc,String csgrType,Integer csgrAmount,Date csgrUpdateTime,Date csgrAddTime,Short csgrStatus){
		this.csgrHost=csgrHost;
		this.csgrMember=csgrMember;
		this.csgrDesc=csgrDesc;
		this.csgrType=csgrType;
		this.csgrAmount=csgrAmount;
		this.csgrUpdateTime=csgrUpdateTime;
		this.csgrAddTime=csgrAddTime;
		this.csgrStatus=csgrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsGrowRecord csgrId(Long csgrId){
		this.csgrId = csgrId;
		this.setSeted(F.csgrId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsGrowRecord csgrHost(Long csgrHost){
		this.csgrHost = csgrHost;
		this.setSeted(F.csgrHost);
		return this;
	}
	/** 会员 [非空] [CsMember]      **/
	public CsGrowRecord csgrMember(Long csgrMember){
		this.csgrMember = csgrMember;
		this.setSeted(F.csgrMember);
		return this;
	}
	/** 描述 [非空]       **/
	public CsGrowRecord csgrDesc(String csgrDesc){
		this.csgrDesc = csgrDesc;
		this.setSeted(F.csgrDesc);
		return this;
	}
	/** 类型 [非空]       **/
	public CsGrowRecord csgrType(String csgrType){
		this.csgrType = csgrType;
		this.setSeted(F.csgrType);
		return this;
	}
	/** 变化值 [非空]       **/
	public CsGrowRecord csgrAmount(Integer csgrAmount){
		this.csgrAmount = csgrAmount;
		this.setSeted(F.csgrAmount);
		return this;
	}
	/** 关联数据     数据格式：对象类名@对象ID  CsTrouble,CsViolat,CsOrder **/
	public CsGrowRecord csgrRelate(String csgrRelate){
		this.csgrRelate = csgrRelate;
		this.setSeted(F.csgrRelate);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsGrowRecord csgrUpdateTime(Date csgrUpdateTime){
		this.csgrUpdateTime = csgrUpdateTime;
		this.setSeted(F.csgrUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsGrowRecord csgrAddTime(Date csgrAddTime){
		this.csgrAddTime = csgrAddTime;
		this.setSeted(F.csgrAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsGrowRecord csgrStatus(Short csgrStatus){
		this.csgrStatus = csgrStatus;
		this.setSeted(F.csgrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsGrowRecord clone(){
		CsGrowRecord clone = new CsGrowRecord();
		clone.csgrHost=this.csgrHost;
		clone.csgrMember=this.csgrMember;
		clone.csgrDesc=this.csgrDesc;
		clone.csgrType=this.csgrType;
		clone.csgrAmount=this.csgrAmount;
		clone.csgrUpdateTime=this.csgrUpdateTime;
		clone.csgrAddTime=this.csgrAddTime;
		clone.csgrStatus=this.csgrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取成长记录
	 * @param id
	 * @return
	 */
	public static @api CsGrowRecord get(Long id){		
		return getCsGrowRecordById(id);
	}
	/**
	 * 获取所有成长记录
	 * @return
	 */
	public static @api List<CsGrowRecord> list(Map params,Integer size){
		return getCsGrowRecordList(params,size);
	}
	/**
	 * 获取成长记录分页
	 * @return
	 */
	public static @api Page<CsGrowRecord> page(int page,int size,Map params){
		return getCsGrowRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取成长记录
	 * @param params
	 * @return
	 */
	public static @api CsGrowRecord Get(Map params){
		return getCsGrowRecord(params);
	}
	/**
	 * 获取成长记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsGrowRecordCount(params);
	}
	/**
	 * 获取成长记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsGrowRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取成长记录
	 * @param id
	 * @return
	 */
	public static @api CsGrowRecord getCsGrowRecordById(Long id){		
		CsGrowRecord csGrowRecord = (CsGrowRecord) $.GetRequest("CsGrowRecord$"+id);
		if(csGrowRecord!=null)
			return csGrowRecord;
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");		
		return csGrowRecordService.getCsGrowRecordById(id);
	}
	
	
	/**
	 * 根据ID取成长记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsGrowRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsGrowRecord csGrowRecord = get(id);
		if(csGrowRecord!=null){
			String strValue = csGrowRecord.getCsgrDesc$();
			if(!"CsgrDesc".equals("CsgrDesc"))
				strValue+="("+csGrowRecord.getCsgrDesc$()+")";
			MemCache.setValue(CsGrowRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsgrDesc$();
		if(!"CsgrDesc".equals("CsgrDesc"))
			keyValue+="("+this.getCsgrDesc$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有成长记录
	 * @return
	 */
	public static @api List<CsGrowRecord> getCsGrowRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		return csGrowRecordService.getCsGrowRecordList(params, size);
	}
	
	/**
	 * 获取成长记录分页
	 * @return
	 */
	public static @api Page<CsGrowRecord> getCsGrowRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		return csGrowRecordService.getCsGrowRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取成长记录
	 * @param params
	 * @return
	 */
	public static @api CsGrowRecord getCsGrowRecord(Map params){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		return csGrowRecordService.getCsGrowRecord(params);
	}
	
	/**
	 * 获取成长记录数
	 * @return
	 */
	public static @api Long getCsGrowRecordCount(Map params){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		return csGrowRecordService.getCsGrowRecordCount(params);
	}
		
		
	/**
	 * 获取成长记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsGrowRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		return csGrowRecordService.getCsGrowRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsGrowRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		csGrowRecordService.updateCsGrowRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存成长记录对象
	 * @param params
	 * @return
	 */
	public CsGrowRecord save(){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		if(this.getCsgrId()!=null)
			csGrowRecordService.updateCsGrowRecord(this);
		else
			return csGrowRecordService.saveCsGrowRecord(this);
		return this;
	}
	
	
	/**
	 * 更新成长记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		csGrowRecordService.updateCsGrowRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csGrowRecordService.removeCsGrowRecordById(this.getCsgrId());
		else
			csGrowRecordService.deleteCsGrowRecordById(this.getCsgrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		return csGrowRecordService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsgrId(){
		return this.csgrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsgrId$(){
		String strValue="";
		 strValue=$.str(this.getCsgrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsgrId(Long csgrId){
		this.csgrId = csgrId;
		this.setSeted(F.csgrId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsgrHost(){
		return this.csgrHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsgrHost$(){
		String strValue="";
		if(this.getCsgrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsgrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsgrHost(Long csgrHost){
		this.csgrHost = csgrHost;
		this.setSeted(F.csgrHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csgrHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsgrHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsgrHost()!=null){
 			srvHost = SrvHost.get(this.getCsgrHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsgrHost(), srvHost);
	 	return srvHost;
	}
	/*******************************会员**********************************/	
	/**
	* 会员 [非空] [CsMember]     
	**/
	public Long getCsgrMember(){
		return this.csgrMember;
	}
	/**
	* 获取会员格式化(toString)
	**/
	public String getCsgrMember$(){
		String strValue="";
		if(this.getCsgrMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsgrMember()));
		}			
	 	 return strValue;
	}
	/**
	* 会员 [非空] [CsMember]     
	**/
	public void setCsgrMember(Long csgrMember){
		this.csgrMember = csgrMember;
		this.setSeted(F.csgrMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csgrMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsgrMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsgrMember()!=null){
 			csMember = CsMember.get(this.getCsgrMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsgrMember(), csMember);
	 	return csMember;
	}
	/*******************************描述**********************************/	
	/**
	* 描述 [非空]      
	**/
	public String getCsgrDesc(){
		return this.csgrDesc;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsgrDesc$(){
		String strValue="";
		 strValue=$.str(this.getCsgrDesc());
	 	 return strValue;
	}
	/**
	* 描述 [非空]      
	**/
	public void setCsgrDesc(String csgrDesc){
		this.csgrDesc = csgrDesc;
		this.setSeted(F.csgrDesc);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]      
	**/
	public String getCsgrType(){
		return this.csgrType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsgrType$(){
		String strValue="";
		 strValue=$.str(this.getCsgrType());
	 	 return strValue;
	}
	/**
	* 类型 [非空]      
	**/
	public void setCsgrType(String csgrType){
		this.csgrType = csgrType;
		this.setSeted(F.csgrType);
	}
	/*******************************变化值**********************************/	
	/**
	* 变化值 [非空]      
	**/
	public Integer getCsgrAmount(){
		return this.csgrAmount;
	}
	/**
	* 获取变化值格式化(toString)
	**/
	public String getCsgrAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsgrAmount());
	 	 return strValue;
	}
	/**
	* 变化值 [非空]      
	**/
	public void setCsgrAmount(Integer csgrAmount){
		this.csgrAmount = csgrAmount;
		this.setSeted(F.csgrAmount);
	}
	/*******************************关联数据**********************************/	
	/**
	* 关联数据     数据格式：对象类名@对象ID  CsTrouble,CsViolat,CsOrder
	**/
	public String getCsgrRelate(){
		return this.csgrRelate;
	}
	/**
	* 获取关联数据格式化(toString)
	**/
	public String getCsgrRelate$(){
		String strValue="";
	 	 Object object = this.get$csgrRelate();
	 	 if(object!=null){
	 		caption caption = object.getClass().getAnnotation(caption.class);
	 		if(caption!=null)strValue+="["+caption.value()+"]";
	 		try {
				strValue+=object.getClass().getMethod("getKeyValue").invoke(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	 }
	 	 return strValue;
	}
	/**
	* 关联数据     数据格式：对象类名@对象ID  CsTrouble,CsViolat,CsOrder
	**/
	public void setCsgrRelate(String csgrRelate){
		this.csgrRelate = csgrRelate;
		this.setSeted(F.csgrRelate);
	}
	/**
	* 泛关联对象，对象类名由csgrRelate字段中标明
	**/	 			
 	public <T> T get$csgrRelate(){
		if($.empty(this.csgrRelate))
 			return null;
 		try{
	 		String ClassName = this.csgrRelate.split("@")[0];
	 		Long id = Long.parseLong(this.csgrRelate.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsgrUpdateTime(){
		return this.csgrUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsgrUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgrUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsgrUpdateTime(Date csgrUpdateTime){
		this.csgrUpdateTime = csgrUpdateTime;
		this.setSeted(F.csgrUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsgrAddTime(){
		return this.csgrAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsgrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsgrAddTime(Date csgrAddTime){
		this.csgrAddTime = csgrAddTime;
		this.setSeted(F.csgrAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsgrStatus(){
		return this.csgrStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsgrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsgrStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsgrStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsgrStatus(Short csgrStatus){
		this.csgrStatus = csgrStatus;
		this.setSeted(F.csgrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsGrowRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGrowRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGrowRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsGrowRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsGrowRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGrowRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGrowRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsGrowRecord.this);
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
		public M csgrId(Object csgrId){this.put("csgrId", csgrId);return this;};
	 	/** and csgr_id is null */
 		public M csgrIdNull(){if(this.get("csgrIdNot")==null)this.put("csgrIdNot", "");this.put("csgrId", null);return this;};
 		/** not .... */
 		public M csgrIdNot(){this.put("csgrIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csgrHost(Object csgrHost){this.put("csgrHost", csgrHost);return this;};
	 	/** and csgr_host is null */
 		public M csgrHostNull(){if(this.get("csgrHostNot")==null)this.put("csgrHostNot", "");this.put("csgrHost", null);return this;};
 		/** not .... */
 		public M csgrHostNot(){this.put("csgrHostNot", "not");return this;};
		/** 会员 [非空] [CsMember]      **/
		public M csgrMember(Object csgrMember){this.put("csgrMember", csgrMember);return this;};
	 	/** and csgr_member is null */
 		public M csgrMemberNull(){if(this.get("csgrMemberNot")==null)this.put("csgrMemberNot", "");this.put("csgrMember", null);return this;};
 		/** not .... */
 		public M csgrMemberNot(){this.put("csgrMemberNot", "not");return this;};
		public M csgrMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csgrMember$on", value);
			return this;
		};	
		/** 描述 [非空]       **/
		public M csgrDesc(Object csgrDesc){this.put("csgrDesc", csgrDesc);return this;};
	 	/** and csgr_desc is null */
 		public M csgrDescNull(){if(this.get("csgrDescNot")==null)this.put("csgrDescNot", "");this.put("csgrDesc", null);return this;};
 		/** not .... */
 		public M csgrDescNot(){this.put("csgrDescNot", "not");return this;};
		/** 类型 [非空]       **/
		public M csgrType(Object csgrType){this.put("csgrType", csgrType);return this;};
	 	/** and csgr_type is null */
 		public M csgrTypeNull(){if(this.get("csgrTypeNot")==null)this.put("csgrTypeNot", "");this.put("csgrType", null);return this;};
 		/** not .... */
 		public M csgrTypeNot(){this.put("csgrTypeNot", "not");return this;};
		/** 变化值 [非空]       **/
		public M csgrAmount(Object csgrAmount){this.put("csgrAmount", csgrAmount);return this;};
	 	/** and csgr_amount is null */
 		public M csgrAmountNull(){if(this.get("csgrAmountNot")==null)this.put("csgrAmountNot", "");this.put("csgrAmount", null);return this;};
 		/** not .... */
 		public M csgrAmountNot(){this.put("csgrAmountNot", "not");return this;};
		/** and csgr_amount >= ? */
		public M csgrAmountMin(Object min){this.put("csgrAmountMin", min);return this;};
		/** and csgr_amount <= ? */
		public M csgrAmountMax(Object max){this.put("csgrAmountMax", max);return this;};
		/** 关联数据     数据格式：对象类名@对象ID  CsTrouble,CsViolat,CsOrder **/
		public M csgrRelate(Object csgrRelate){this.put("csgrRelate", csgrRelate);return this;};
	 	/** and csgr_relate is null */
 		public M csgrRelateNull(){if(this.get("csgrRelateNot")==null)this.put("csgrRelateNot", "");this.put("csgrRelate", null);return this;};
 		/** not .... */
 		public M csgrRelateNot(){this.put("csgrRelateNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csgrUpdateTime(Object csgrUpdateTime){this.put("csgrUpdateTime", csgrUpdateTime);return this;};
	 	/** and csgr_update_time is null */
 		public M csgrUpdateTimeNull(){if(this.get("csgrUpdateTimeNot")==null)this.put("csgrUpdateTimeNot", "");this.put("csgrUpdateTime", null);return this;};
 		/** not .... */
 		public M csgrUpdateTimeNot(){this.put("csgrUpdateTimeNot", "not");return this;};
 		/** and csgr_update_time >= ? */
 		public M csgrUpdateTimeStart(Object start){this.put("csgrUpdateTimeStart", start);return this;};			
 		/** and csgr_update_time <= ? */
 		public M csgrUpdateTimeEnd(Object end){this.put("csgrUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csgrAddTime(Object csgrAddTime){this.put("csgrAddTime", csgrAddTime);return this;};
	 	/** and csgr_add_time is null */
 		public M csgrAddTimeNull(){if(this.get("csgrAddTimeNot")==null)this.put("csgrAddTimeNot", "");this.put("csgrAddTime", null);return this;};
 		/** not .... */
 		public M csgrAddTimeNot(){this.put("csgrAddTimeNot", "not");return this;};
 		/** and csgr_add_time >= ? */
 		public M csgrAddTimeStart(Object start){this.put("csgrAddTimeStart", start);return this;};			
 		/** and csgr_add_time <= ? */
 		public M csgrAddTimeEnd(Object end){this.put("csgrAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csgrStatus(Object csgrStatus){this.put("csgrStatus", csgrStatus);return this;};
	 	/** and csgr_status is null */
 		public M csgrStatusNull(){if(this.get("csgrStatusNot")==null)this.put("csgrStatusNot", "");this.put("csgrStatus", null);return this;};
 		/** not .... */
 		public M csgrStatusNot(){this.put("csgrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有成长记录 **/
		public @api List<CsGrowRecord> list(Integer size){
			return getCsGrowRecordList(this,size);
		}
		/** 获取成长记录分页 **/
		public @api Page<CsGrowRecord> page(int page,int size){
			return getCsGrowRecordPage(page, size , this);
		}
		/** 根据查询条件取成长记录 **/
		public @api CsGrowRecord get(){
			return getCsGrowRecord(this);
		}
		/** 获取成长记录数 **/
		public @api Long count(){
			return getCsGrowRecordCount(this);
		}
		/** 获取成长记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsGrowRecordEval(strEval,this);
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
			updateCsGrowRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csgrId="csgrId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csgrHost="csgrHost";
		/** 会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csgrMember="csgrMember";
		/** 描述 [非空]       **/
		public final static @type(String.class) @like String csgrDesc="csgrDesc";
		/** 类型 [非空]       **/
		public final static @type(String.class) @like String csgrType="csgrType";
		/** 变化值 [非空]       **/
		public final static @type(Integer.class)  String csgrAmount="csgrAmount";
		/** and csgr_amount >= ? */
		public final static @type(Integer.class) String csgrAmountMin="csgrAmountMin";
		/** and csgr_amount <= ? */
		public final static @type(Integer.class) String csgrAmountMax="csgrAmountMax";
		/** 关联数据     数据格式：对象类名@对象ID  CsTrouble,CsViolat,CsOrder **/
		public final static @type(String.class)  String csgrRelate="csgrRelate";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csgrUpdateTime="csgrUpdateTime";
	 	/** and csgr_update_time >= ? */
 		public final static @type(Date.class) String csgrUpdateTimeStart="csgrUpdateTimeStart";
 		/** and csgr_update_time <= ? */
 		public final static @type(Date.class) String csgrUpdateTimeEnd="csgrUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csgrAddTime="csgrAddTime";
	 	/** and csgr_add_time >= ? */
 		public final static @type(Date.class) String csgrAddTimeStart="csgrAddTimeStart";
 		/** and csgr_add_time <= ? */
 		public final static @type(Date.class) String csgrAddTimeEnd="csgrAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csgrStatus="csgrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csgrId="csgr_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csgrHost="csgr_host";
		/** 会员 [非空] [CsMember]      **/
		public final static String csgrMember="csgr_member";
		/** 描述 [非空]       **/
		public final static String csgrDesc="csgr_desc";
		/** 类型 [非空]       **/
		public final static String csgrType="csgr_type";
		/** 变化值 [非空]       **/
		public final static String csgrAmount="csgr_amount";
		/** 关联数据     数据格式：对象类名@对象ID  CsTrouble,CsViolat,CsOrder **/
		public final static String csgrRelate="csgr_relate";
		/** 修改时间 [非空]       **/
		public final static String csgrUpdateTime="csgr_update_time";
		/** 添加时间 [非空]       **/
		public final static String csgrAddTime="csgr_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csgrStatus="csgr_status";
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
				$.Set(name,CsGrowRecord.getCsGrowRecord(params));
			else
				$.Set(name,CsGrowRecord.getCsGrowRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取成长记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsGrowRecord) $.GetRequest("CsGrowRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsGrowRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsGrowRecord.getCsGrowRecord(params);
			else
				value = CsGrowRecord.getCsGrowRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsGrowRecord.Get($.add(CsGrowRecord.F.csgrId,param));
		else if(!$.empty(param.toString()))
			value = CsGrowRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsGrowRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsGrowRecord old){
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