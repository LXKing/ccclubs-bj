package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.lang.namespace;
import com.lazy3q.lang.note;
import com.lazy3q.lang.primary;
import com.lazy3q.lang.relate;
import com.lazy3q.lang.table;
import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.tags.BodyTag;
import com.lazy3q.web.util.Page;

@namespace("object/sysissue")
public @caption("系统车况报告") @table("cs_car_sys_issue") class CsCarSysIssue implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscsi_id")   @primary   @note("  ") Long cscsiId;
	private @caption("城市") @column("cscsi_host")    @relate("$cscsiHost") @RelateClass(SrvHost.class)  @note("  ") Long cscsiHost;
	private SrvHost $cscsiHost;//关联对象[城市]
	private @caption("车辆名称") @column("cscsi_car")    @relate("$cscsiCar") @RelateClass(CsCar.class)  @note("  ") Long cscsiCar;
	private CsCar $cscsiCar;//关联对象[车辆ID]
	private @caption("车部件ID") @column("cscsi_parts")    @relate("$cscsiParts") @RelateClass(CsCarParts.class)  @note("  ") Long cscsiParts;
	private CsCarParts $cscsiParts;//关联对象[车部件ID]
	private @caption("车辆部位") @column("cscsi_parts_str")      @note("  ") String cscsiPartsStr;
	private @caption("内容") @column("cscsi_content")      @note("  ") String cscsiContent;
	private @caption("车况标签") @column("cscsi_keywords")      @note("  ") String cscsiKeywords;
	private @caption("图片") @column("cscsi_image")      @note("  ") String cscsiImage;
	private @caption("类别") @column("cscsi_type")      @note(" 1:刮痕 2:凹痕 3:损坏 4:裂痕 5:非正常工作 6:缺失零件") Short cscsiType;
	private @caption("操作人") @column("cscsi_srvuser")    @relate("$cscsiSrvuser") @RelateClass(SrvUser.class)  @note("  ") Long cscsiSrvuser;
	private SrvUser $cscsiSrvuser;//关联对象[操作人]
	private @caption("添加时间") @column("cscsi_add_time")      @note("  ") Date cscsiAddTime;
	private @caption("修改时间") @column("cscsi_update_time")      @note("  ") Date cscsiUpdateTime;
	private @caption("数据状态") @column("cscsi_status")      @note(" 1:正常 0:禁用  ") Short cscsiStatus;
	private @caption("备注") @column("cscsi_remark")      @note("  ") String cscsiRemark;
	
	//默认构造函数
	public CsCarSysIssue(){
	
	}
	
	//主键构造函数
	public CsCarSysIssue(Long id){
		this.cscsiId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCarSysIssue(Long cscsiHost,Long cscsiCar,Long cscsiParts,String cscsiPartsStr,String cscsiContent,String cscsiKeywords,String cscsiImage,Short cscsiType,Long cscsiSrvuser,Date cscsiAddTime,Date cscsiUpdateTime,Short cscsiStatus,String cscsiRemark){
		this.cscsiHost=cscsiHost;
		this.cscsiCar=cscsiCar;
		this.cscsiParts=cscsiParts;
		this.cscsiPartsStr=cscsiPartsStr;
		this.cscsiContent=cscsiContent;
		this.cscsiKeywords=cscsiKeywords;
		this.cscsiImage=cscsiImage;
		this.cscsiType=cscsiType;
		this.cscsiSrvuser=cscsiSrvuser;
		this.cscsiAddTime=cscsiAddTime;
		this.cscsiUpdateTime=cscsiUpdateTime;
		this.cscsiStatus=cscsiStatus;
		this.cscsiRemark=cscsiRemark;
	}
	
	//设置非空字段
	public CsCarSysIssue setNotNull(String cscsiPartsStr,Short cscsiStatus){
		this.cscsiPartsStr=cscsiPartsStr;
		this.cscsiStatus=cscsiStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsCarSysIssue cscsiId(Long cscsiId){
		this.cscsiId = cscsiId;
		this.setSeted(F.cscsiId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsCarSysIssue cscsiHost(Long cscsiHost){
		this.cscsiHost = cscsiHost;
		this.setSeted(F.cscsiHost);
		return this;
	}
	/** 车辆ID [非空]       **/
	public CsCarSysIssue cscsiCar(Long cscsiCar){
		this.cscsiCar = cscsiCar;
		this.setSeted(F.cscsiCar);
		return this;
	}
	/** 车部件ID [非空]       **/
	public CsCarSysIssue cscsiParts(Long cscsiParts){
		this.cscsiParts = cscsiParts;
		this.setSeted(F.cscsiParts);
		return this;
	}
	/** 数据状态 [可空]       **/
	public CsCarSysIssue cscsiPartsStr(String cscsiPartsStr){
		this.cscsiPartsStr = cscsiPartsStr;
		this.setSeted(F.cscsiPartsStr);
		return this;
	}
	/** 内容 [非空]       **/
	public CsCarSysIssue cscsiContent(String cscsiContent){
		this.cscsiContent = cscsiContent;
		this.setSeted(F.cscsiContent);
		return this;
	}
	/** 关键字 [非空]       **/
	public CsCarSysIssue cscsiKeywords(String cscsiKeywords){
		this.cscsiKeywords = cscsiKeywords;
		this.setSeted(F.cscsiKeywords);
		return this;
	}
	/** 图片 [非空]       **/
	public CsCarSysIssue cscsiImage(String cscsiImage){
		this.cscsiImage = cscsiImage;
		this.setSeted(F.cscsiImage);
		return this;
	}
	/** 图片 [非空]       **/
	public CsCarSysIssue cscsiType(Short cscsiType){
		this.cscsiType = cscsiType;
		this.setSeted(F.cscsiType);
		return this;
	}
	/** 操作人 [非空]       **/
	public CsCarSysIssue cscsiSrvuser(Long cscsiSrvuser){
		this.cscsiSrvuser = cscsiSrvuser;
		this.setSeted(F.cscsiSrvuser);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCarSysIssue cscsiAddTime(Date cscsiAddTime){
		this.cscsiAddTime = cscsiAddTime;
		this.setSeted(F.cscsiAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCarSysIssue cscsiUpdateTime(Date cscsiUpdateTime){
		this.cscsiUpdateTime = cscsiUpdateTime;
		this.setSeted(F.cscsiUpdateTime);
		return this;
	}
	/** 数据状态 [可空]       **/
	public CsCarSysIssue cscsiStatus(Short cscsiStatus){
		this.cscsiStatus = cscsiStatus;
		this.setSeted(F.cscsiStatus);
		return this;
	}
	/** 备注 [非空]       **/
	public CsCarSysIssue cscsiRemark(String cscsiRemark){
		this.cscsiRemark = cscsiRemark;
		this.setSeted(F.cscsiRemark);
		return this;
	}
	
	//克隆对象
	public CsCarSysIssue clone(){
		CsCarSysIssue clone = new CsCarSysIssue();
		clone.cscsiId=this.cscsiId;
		clone.cscsiHost=this.cscsiHost;
		clone.cscsiCar=this.cscsiCar;
		clone.cscsiParts=this.cscsiParts;
		clone.cscsiPartsStr=this.cscsiPartsStr;
		clone.cscsiContent=this.cscsiContent;
		clone.cscsiKeywords=this.cscsiKeywords;
		clone.cscsiImage=this.cscsiImage;
		clone.cscsiType=this.cscsiType;
		clone.cscsiSrvuser=this.cscsiSrvuser;
		clone.cscsiAddTime=this.cscsiAddTime;
		clone.cscsiUpdateTime=this.cscsiUpdateTime;
		clone.cscsiStatus=this.cscsiStatus;
		clone.cscsiRemark=this.cscsiRemark;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统车况报告
	 * @param id
	 * @return
	 */
	public static @api CsCarSysIssue get(Long id){		
		return getCsCarSysIssueById(id);
	}
	/**
	 * 获取所有系统车况报告
	 * @return
	 */
	public static @api List<CsCarSysIssue> list(Map params,Integer size){
		return getCsCarSysIssueList(params,size);
	}
	/**
	 * 获取系统车况报告分页
	 * @return
	 */
	public static @api Page<CsCarSysIssue> page(int page,int size,Map params){
		return getCsCarSysIssuePage(page, size , params);
	}
	/**
	 * 根据查询条件取系统车况报告
	 * @param params
	 * @return
	 */
	public static @api CsCarSysIssue Get(Map params){
		return getCsCarSysIssue(params);
	}
	/**
	 * 获取系统车况报告数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCarSysIssueCount(params);
	}
	/**
	 * 获取系统车况报告数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCarSysIssueEval(eval,params);
	}
	
	/**
	 * 根据ID取系统车况报告
	 * @param id
	 * @return
	 */
	public static @api CsCarSysIssue getCsCarSysIssueById(Long id){		
		CsCarSysIssue csCarSysIssue = (CsCarSysIssue) $.GetRequest("CsCarSysIssue$"+id);
		if(csCarSysIssue!=null)
			return csCarSysIssue;
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");		
		return csCarSysIssueService.getCsCarSysIssueById(id);
	}
	
	
	/**
	 * 根据ID取系统车况报告的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCarSysIssue.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCarSysIssue csCarSysIssue = get(id);
		if(csCarSysIssue!=null){
			String strValue = csCarSysIssue.getCscsiId$();
			if(!"CscsiId".equals("CscsiId"))
				strValue+="("+csCarSysIssue.getCscsiId$()+")";
			MemCache.setValue(CsCarSysIssue.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscsiId$();
		if(!"CscsiId".equals("CscsiId"))
			keyValue+="("+this.getCscsiId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统车况报告
	 * @return
	 */
	public static @api List<CsCarSysIssue> getCsCarSysIssueList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		return csCarSysIssueService.getCsCarSysIssueList(params, size);
	}
	
	/**
	 * 获取系统车况报告分页
	 * @return
	 */
	public static @api Page<CsCarSysIssue> getCsCarSysIssuePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		return csCarSysIssueService.getCsCarSysIssuePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统车况报告
	 * @param params
	 * @return
	 */
	public static @api CsCarSysIssue getCsCarSysIssue(Map params){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		return csCarSysIssueService.getCsCarSysIssue(params);
	}
	
	/**
	 * 获取系统车况报告数
	 * @return
	 */
	public static @api Long getCsCarSysIssueCount(Map params){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		return csCarSysIssueService.getCsCarSysIssueCount(params);
	}
		
		
	/**
	 * 获取系统车况报告自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCarSysIssueEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		return csCarSysIssueService.getCsCarSysIssueEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCarSysIssue(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		csCarSysIssueService.updateCsCarSysIssueByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统车况报告对象
	 * @param params
	 * @return
	 */
	public CsCarSysIssue save(){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		if(this.getCscsiId()!=null)
			csCarSysIssueService.updateCsCarSysIssue(this);
		else
			return csCarSysIssueService.saveCsCarSysIssue(this);
		return this;
	}
	
	
	/**
	 * 更新系统车况报告对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		csCarSysIssueService.updateCsCarSysIssue$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		if($.equals($.config("logic_delete"),"true"))
			csCarSysIssueService.removeCsCarSysIssueById(this.getCscsiId());
		else
			csCarSysIssueService.deleteCsCarSysIssueById(this.getCscsiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
		return csCarSysIssueService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscsiId(){
		return this.cscsiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscsiId$(){
		String strValue="";
		 strValue=$.str(this.getCscsiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscsiId(Long cscsiId){
		this.cscsiId = cscsiId;
		this.setSeted(F.cscsiId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCscsiHost(){
		return this.cscsiHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscsiHost$(){
		String strValue="";
		if(this.getCscsiHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscsiHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCscsiHost(Long cscsiHost){
		this.cscsiHost = cscsiHost;
		this.setSeted(F.cscsiHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$cscsiHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscsiHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscsiHost()!=null){
			srvHost = SrvHost.get(this.getCscsiHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscsiHost(), srvHost);
	 	return srvHost;
	}
	/*******************************车辆ID**********************************/	
	/**
	* 车辆ID [非空]      
	**/
	public Long getCscsiCar(){
		return this.cscsiCar;
	}
	/**
	* 获取车辆ID格式化(toString)
	**/
	public String getCscsiCar$(){
		String strValue="";
		if(this.getCscsiCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCscsiCar()));
		}			
	 	 return strValue;
	}
	/**
	* 车辆ID [非空]     
	**/
	public void setCscsiCar(Long cscsiCar){
		this.cscsiCar = cscsiCar;
		this.setSeted(F.cscsiCar);
	}
	/**
	* 获取关联对象[车辆ID]
	**/	 			
 	public CsCar get$cscsiCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCscsiCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCscsiCar()!=null){
			csCar = CsCar.get(this.getCscsiCar());
 		}
 		$.SetRequest("CsCar$"+this.getCscsiCar(), csCar);
	 	return csCar;
	}
	/*******************************车部件ID**********************************/	
	/**
	* 车部件ID [非空]      
	**/
	public Long getCscsiParts(){
		return this.cscsiParts;
	}
	/**
	* 获取车部件ID格式化(toString)
	**/
	public String getCscsiParts$(){
		String strValue="";
		if(this.getCscsiParts()!=null){
				strValue+=$.str(CsCarParts.getKeyValue(this.getCscsiParts()));
		}			
	 	 return strValue;
	}
	/**
	* 车部件ID [非空]     
	**/
	public void setCscsiParts(Long cscsiParts){
		this.cscsiParts = cscsiParts;
		this.setSeted(F.cscsiParts);
	}
	/**
	* 获取关联对象[车部件ID]
	**/	 			
 	public CsCarParts get$cscsiParts(){
 		com.ccclubs.model.CsCarParts csCarParts = (com.ccclubs.model.CsCarParts) $.GetRequest("CsCarParts$"+this.getCscsiParts());
 		if(csCarParts!=null)
			return csCarParts;
		if(this.getCscsiParts()!=null){
			csCarParts = CsCarParts.get(this.getCscsiParts());
 		}
 		$.SetRequest("CsCarParts$"+this.getCscsiParts(), csCarParts);
	 	return csCarParts;
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [可空]      
	**/
	public String getCscsiPartsStr(){
		return this.cscsiPartsStr;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCscsiPartsStr$(){
		String strValue="";
		 strValue=$.str(this.getCscsiPartsStr());
	 	 return strValue;
	}
	/**
	* 数据状态 [可空]     
	**/
	public void setCscsiPartsStr(String cscsiPartsStr){
		this.cscsiPartsStr = cscsiPartsStr;
		this.setSeted(F.cscsiPartsStr);
	}
	/*******************************内容**********************************/	
	/**
	* 内容 [非空]      
	**/
	public String getCscsiContent(){
		return this.cscsiContent;
	}
	/**
	* 获取内容格式化(toString)
	**/
	public String getCscsiContent$(){
		String strValue="";
		 strValue=$.str(this.getCscsiContent());
	 	 return strValue;
	}
	/**
	* 内容 [非空]     
	**/
	public void setCscsiContent(String cscsiContent){
		this.cscsiContent = cscsiContent;
		this.setSeted(F.cscsiContent);
	}
	/*******************************关键字**********************************/	
	/**
	* 关键字 [非空]      
	**/
	public String getCscsiKeywords(){
		return this.cscsiKeywords;
	}
	/**
	* 获取关键字格式化(toString)
	**/
	public String getCscsiKeywords$(){
		String strValue="";
		 strValue=$.str(this.getCscsiKeywords());
	 	 return strValue;
	}
	/**
	* 关键字 [非空]     
	**/
	public void setCscsiKeywords(String cscsiKeywords){
		this.cscsiKeywords = cscsiKeywords;
		this.setSeted(F.cscsiKeywords);
	}
	/*******************************图片**********************************/	
	/**
	* 图片 [非空]      
	**/
	public String getCscsiImage(){
		return this.cscsiImage;
	}
	/**
	* 获取图片格式化(toString)
	**/
	public String getCscsiImage$(){
		String strValue="";
		 strValue=$.str(this.getCscsiImage());
	 	 return strValue;
	}
	/**
	* 图片 [非空]     
	**/
	public void setCscsiImage(String cscsiImage){
		this.cscsiImage = cscsiImage;
		this.setSeted(F.cscsiImage);
	}
	/*******************************图片**********************************/	
	/**
	* 图片 [非空]      
	**/
	public Short getCscsiType(){
		return this.cscsiType;
	}
	/**
	* 获取图片格式化(toString)1:刮痕 2:凹痕 3:损坏 4:裂痕 5:非正常工作 6:缺失零件
	**/
	public String getCscsiType$(){
		String strValue="";
		if($.equals($.str(this.getCscsiType()),"1"))
			 strValue=$.str("刮痕");
		 if($.equals($.str(this.getCscsiType()),"2"))
			 strValue=$.str("凹痕");
		 if($.equals($.str(this.getCscsiType()),"3"))
			 strValue=$.str("损坏");
		 if($.equals($.str(this.getCscsiType()),"4"))
			 strValue=$.str("裂痕");
		 if($.equals($.str(this.getCscsiType()),"5"))
			 strValue=$.str("非正常工作");
		 if($.equals($.str(this.getCscsiType()),"6"))
			 strValue=$.str("缺失零件");
	 	 return strValue;
	}
	/**
	* 图片 [非空]     
	**/
	public void setCscsiType(Short cscsiType){
		this.cscsiType = cscsiType;
		this.setSeted(F.cscsiType);
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空]      
	**/
	public Long getCscsiSrvuser(){
		return this.cscsiSrvuser;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCscsiSrvuser$(){
		String strValue="";
		if(this.getCscsiSrvuser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscsiSrvuser()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空]     
	**/
	public void setCscsiSrvuser(Long cscsiSrvuser){
		this.cscsiSrvuser = cscsiSrvuser;
		this.setSeted(F.cscsiSrvuser);
	}
	/**
	* 获取关联对象[操作人]
	**/	 			
 	public SrvUser get$cscsiSrvuser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscsiSrvuser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscsiSrvuser()!=null){
			srvUser = SrvUser.get(this.getCscsiSrvuser());
 		}
 		$.SetRequest("SrvUser$"+this.getCscsiSrvuser(), srvUser);
	 	return srvUser;
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscsiAddTime(){
		return this.cscsiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscsiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCscsiAddTime(Date cscsiAddTime){
		this.cscsiAddTime = cscsiAddTime;
		this.setSeted(F.cscsiAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscsiUpdateTime(){
		return this.cscsiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscsiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCscsiUpdateTime(Date cscsiUpdateTime){
		this.cscsiUpdateTime = cscsiUpdateTime;
		this.setSeted(F.cscsiUpdateTime);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [可空]      
	**/
	public Short getCscsiStatus(){
		return this.cscsiStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCscsiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscsiStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCscsiStatus()),"0"))
			 strValue=$.str("禁用");
	 	 return strValue;
	}
	/**
	* 数据状态 [可空]     
	**/
	public void setCscsiStatus(Short cscsiStatus){
		this.cscsiStatus = cscsiStatus;
		this.setSeted(F.cscsiStatus);
	}
	/*******************************备注**********************************/	
	/**
	* 备注 [非空]      
	**/
	public String getCscsiRemark(){
		return this.cscsiRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCscsiRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscsiRemark());
	 	 return strValue;
	}
	/**
	* 备注 [非空]     
	**/
	public void setCscsiRemark(String cscsiRemark){
		this.cscsiRemark = cscsiRemark;
		this.setSeted(F.cscsiRemark);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCarSysIssue.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarSysIssue.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarSysIssue.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCarSysIssue.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCarSysIssue.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarSysIssue.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarSysIssue.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCarSysIssue.this);
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
		public M cscsiId(Object cscsiId){this.put("cscsiId", cscsiId);return this;};
		/** and cscsi_id is null */
		public M cscsiIdNull(){if(this.get("cscsiIdNot")==null)this.put("cscsiIdNot", "");this.put("cscsiId", null);return this;};
		/** not .... */
		public M cscsiIdNot(){this.put("cscsiIdNot", "not");return this;};
		/** and cscsi_id >= ? */
		public M cscsiIdMin(Object min){this.put("cscsiIdMin", min);return this;};
		/** and cscsi_id <= ? */
		public M cscsiIdMax(Object max){this.put("cscsiIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M cscsiHost(Object cscsiHost){this.put("cscsiHost", cscsiHost);return this;};
		/** and cscsi_host is null */
		public M cscsiHostNull(){if(this.get("cscsiHostNot")==null)this.put("cscsiHostNot", "");this.put("cscsiHost", null);return this;};
		/** not .... */
		public M cscsiHostNot(){this.put("cscsiHostNot", "not");return this;};
		/** and cscsi_host >= ? */
		public M cscsiHostMin(Object min){this.put("cscsiHostMin", min);return this;};
		/** and cscsi_host <= ? */
		public M cscsiHostMax(Object max){this.put("cscsiHostMax", max);return this;};
		/** 车辆ID [非空]       **/
		public M cscsiCar(Object cscsiCar){this.put("cscsiCar", cscsiCar);return this;};
		/** and cscsi_car is null */
		public M cscsiCarNull(){if(this.get("cscsiCarNot")==null)this.put("cscsiCarNot", "");this.put("cscsiCar", null);return this;};
		/** not .... */
		public M cscsiCarNot(){this.put("cscsiCarNot", "not");return this;};
		public M cscsiCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cscsiCar$on", value);
			return this;
		};	
		/** and cscsi_car >= ? */
		public M cscsiCarMin(Object min){this.put("cscsiCarMin", min);return this;};
		/** and cscsi_car <= ? */
		public M cscsiCarMax(Object max){this.put("cscsiCarMax", max);return this;};
		/** 车部件ID [非空]       **/
		public M cscsiParts(Object cscsiParts){this.put("cscsiParts", cscsiParts);return this;};
		/** and cscsi_parts is null */
		public M cscsiPartsNull(){if(this.get("cscsiPartsNot")==null)this.put("cscsiPartsNot", "");this.put("cscsiParts", null);return this;};
		/** not .... */
		public M cscsiPartsNot(){this.put("cscsiPartsNot", "not");return this;};
		public M cscsiParts$on(CsCarParts.M value){
			this.put("CsCarParts", value);
			this.put("cscsiParts$on", value);
			return this;
		};	
		/** and cscsi_parts >= ? */
		public M cscsiPartsMin(Object min){this.put("cscsiPartsMin", min);return this;};
		/** and cscsi_parts <= ? */
		public M cscsiPartsMax(Object max){this.put("cscsiPartsMax", max);return this;};
		/** 数据状态 [可空]       **/
		public M cscsiPartsStr(Object cscsiPartsStr){this.put("cscsiPartsStr", cscsiPartsStr);return this;};
		/** and cscsi_parts_str is null */
		public M cscsiPartsStrNull(){if(this.get("cscsiPartsStrNot")==null)this.put("cscsiPartsStrNot", "");this.put("cscsiPartsStr", null);return this;};
		/** not .... */
		public M cscsiPartsStrNot(){this.put("cscsiPartsStrNot", "not");return this;};
		/** 内容 [非空]       **/
		public M cscsiContent(Object cscsiContent){this.put("cscsiContent", cscsiContent);return this;};
		/** and cscsi_content is null */
		public M cscsiContentNull(){if(this.get("cscsiContentNot")==null)this.put("cscsiContentNot", "");this.put("cscsiContent", null);return this;};
		/** not .... */
		public M cscsiContentNot(){this.put("cscsiContentNot", "not");return this;};
		/** 关键字 [非空]       **/
		public M cscsiKeywords(Object cscsiKeywords){this.put("cscsiKeywords", cscsiKeywords);return this;};
		/** and cscsi_keywords is null */
		public M cscsiKeywordsNull(){if(this.get("cscsiKeywordsNot")==null)this.put("cscsiKeywordsNot", "");this.put("cscsiKeywords", null);return this;};
		/** not .... */
		public M cscsiKeywordsNot(){this.put("cscsiKeywordsNot", "not");return this;};
		/** 图片 [非空]       **/
		public M cscsiImage(Object cscsiImage){this.put("cscsiImage", cscsiImage);return this;};
		/** and cscsi_image is null */
		public M cscsiImageNull(){if(this.get("cscsiImageNot")==null)this.put("cscsiImageNot", "");this.put("cscsiImage", null);return this;};
		/** not .... */
		public M cscsiImageNot(){this.put("cscsiImageNot", "not");return this;};
		/** 图片 [非空]       **/
		public M cscsiType(Object cscsiType){this.put("cscsiType", cscsiType);return this;};
		/** and cscsi_type is null */
		public M cscsiTypeNull(){if(this.get("cscsiTypeNot")==null)this.put("cscsiTypeNot", "");this.put("cscsiType", null);return this;};
		/** not .... */
		public M cscsiTypeNot(){this.put("cscsiTypeNot", "not");return this;};
		/** and cscsi_type >= ? */
		public M cscsiTypeMin(Object min){this.put("cscsiTypeMin", min);return this;};
		/** and cscsi_type <= ? */
		public M cscsiTypeMax(Object max){this.put("cscsiTypeMax", max);return this;};
		/** 操作人 [非空]       **/
		public M cscsiSrvuser(Object cscsiSrvuser){this.put("cscsiSrvuser", cscsiSrvuser);return this;};
		/** and cscsi_srvuser is null */
		public M cscsiSrvuserNull(){if(this.get("cscsiSrvuserNot")==null)this.put("cscsiSrvuserNot", "");this.put("cscsiSrvuser", null);return this;};
		/** not .... */
		public M cscsiSrvuserNot(){this.put("cscsiSrvuserNot", "not");return this;};
		public M cscsiSrvuser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("cscsiSrvuser$on", value);
			return this;
		};	
		/** and cscsi_srvuser >= ? */
		public M cscsiSrvuserMin(Object min){this.put("cscsiSrvuserMin", min);return this;};
		/** and cscsi_srvuser <= ? */
		public M cscsiSrvuserMax(Object max){this.put("cscsiSrvuserMax", max);return this;};
		/** 添加时间 [非空]       **/
		public M cscsiAddTime(Object cscsiAddTime){this.put("cscsiAddTime", cscsiAddTime);return this;};
		/** and cscsi_add_time is null */
		public M cscsiAddTimeNull(){if(this.get("cscsiAddTimeNot")==null)this.put("cscsiAddTimeNot", "");this.put("cscsiAddTime", null);return this;};
		/** not .... */
		public M cscsiAddTimeNot(){this.put("cscsiAddTimeNot", "not");return this;};
		/** and cscsi_add_time >= ? */
 		public M cscsiAddTimeStart(Object start){this.put("cscsiAddTimeStart", start);return this;};			
 		/** and cscsi_add_time <= ? */
 		public M cscsiAddTimeEnd(Object end){this.put("cscsiAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cscsiUpdateTime(Object cscsiUpdateTime){this.put("cscsiUpdateTime", cscsiUpdateTime);return this;};
		/** and cscsi_update_time is null */
		public M cscsiUpdateTimeNull(){if(this.get("cscsiUpdateTimeNot")==null)this.put("cscsiUpdateTimeNot", "");this.put("cscsiUpdateTime", null);return this;};
		/** not .... */
		public M cscsiUpdateTimeNot(){this.put("cscsiUpdateTimeNot", "not");return this;};
		/** and cscsi_update_time >= ? */
 		public M cscsiUpdateTimeStart(Object start){this.put("cscsiUpdateTimeStart", start);return this;};			
 		/** and cscsi_update_time <= ? */
 		public M cscsiUpdateTimeEnd(Object end){this.put("cscsiUpdateTimeEnd", end);return this;};
		/** 数据状态 [可空]       **/
		public M cscsiStatus(Object cscsiStatus){this.put("cscsiStatus", cscsiStatus);return this;};
		/** and cscsi_status is null */
		public M cscsiStatusNull(){if(this.get("cscsiStatusNot")==null)this.put("cscsiStatusNot", "");this.put("cscsiStatus", null);return this;};
		/** not .... */
		public M cscsiStatusNot(){this.put("cscsiStatusNot", "not");return this;};
		/** and cscsi_status >= ? */
		public M cscsiStatusMin(Object min){this.put("cscsiStatusMin", min);return this;};
		/** and cscsi_status <= ? */
		public M cscsiStatusMax(Object max){this.put("cscsiStatusMax", max);return this;};
		/** 备注 [非空]       **/
		public M cscsiRemark(Object cscsiRemark){this.put("cscsiRemark", cscsiRemark);return this;};
		/** and cscsi_remark is null */
		public M cscsiRemarkNull(){if(this.get("cscsiRemarkNot")==null)this.put("cscsiRemarkNot", "");this.put("cscsiRemark", null);return this;};
		/** not .... */
		public M cscsiRemarkNot(){this.put("cscsiRemarkNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统车况报告 **/
		public @api List<CsCarSysIssue> list(Integer size){
			return getCsCarSysIssueList(this,size);
		}
		/** 获取系统车况报告分页 **/
		public @api Page<CsCarSysIssue> page(int page,int size){
			return getCsCarSysIssuePage(page, size , this);
		}
		/** 根据查询条件取系统车况报告 **/
		public @api CsCarSysIssue get(){
			return getCsCarSysIssue(this);
		}
		/** 获取系统车况报告数 **/
		public @api Long count(){
			return getCsCarSysIssueCount(this);
		}
		/** 获取系统车况报告表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCarSysIssueEval(strEval,this);
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
			updateCsCarSysIssue(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscsiId="cscsiId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String cscsiHost="cscsiHost";
		/** 车辆ID [非空]       **/
		public final static @type(Long.class)  String cscsiCar="cscsiCar";
		/** 车部件ID [非空]       **/
		public final static @type(Long.class)  String cscsiParts="cscsiParts";
		/** 数据状态 [可空]       **/
		public final static @type(String.class)  String cscsiPartsStr="cscsiPartsStr";
		/** 内容 [非空]       **/
		public final static @type(String.class)  String cscsiContent="cscsiContent";
		/** 关键字 [非空]       **/
		public final static @type(String.class)  String cscsiKeywords="cscsiKeywords";
		/** 图片 [非空]       **/
		public final static @type(String.class)  String cscsiImage="cscsiImage";
		/** 图片 [非空]       **/
		public final static @type(Short.class)  String cscsiType="cscsiType";
		/** 操作人 [非空]       **/
		public final static @type(Long.class)  String cscsiSrvuser="cscsiSrvuser";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscsiAddTime="cscsiAddTime";
		/** and cscsi_add_time >= ? */
 		public final static @type(Date.class) String cscsiAddTimeStart="cscsiAddTimeStart";
 		/** and cscsi_add_time <= ? */
 		public final static @type(Date.class) String cscsiAddTimeEnd="cscsiAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscsiUpdateTime="cscsiUpdateTime";
		/** and cscsi_update_time >= ? */
 		public final static @type(Date.class) String cscsiUpdateTimeStart="cscsiUpdateTimeStart";
 		/** and cscsi_update_time <= ? */
 		public final static @type(Date.class) String cscsiUpdateTimeEnd="cscsiUpdateTimeEnd";
		/** 数据状态 [可空]       **/
		public final static @type(Short.class)  String cscsiStatus="cscsiStatus";
		/** 备注 [非空]       **/
		public final static @type(String.class)  String cscsiRemark="cscsiRemark";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscsiId="cscsi_id";
		/** 城市 [非空]       **/
		public final static String cscsiHost="cscsi_host";
		/** 车辆ID [非空]       **/
		public final static String cscsiCar="cscsi_car";
		/** 车部件ID [非空]       **/
		public final static String cscsiParts="cscsi_parts";
		/** 数据状态 [可空]       **/
		public final static String cscsiPartsStr="cscsi_parts_str";
		/** 内容 [非空]       **/
		public final static String cscsiContent="cscsi_content";
		/** 关键字 [非空]       **/
		public final static String cscsiKeywords="cscsi_keywords";
		/** 图片 [非空]       **/
		public final static String cscsiImage="cscsi_image";
		/** 图片 [非空]       **/
		public final static String cscsiType="cscsi_type";
		/** 操作人 [非空]       **/
		public final static String cscsiSrvuser="cscsi_srvuser";
		/** 添加时间 [非空]       **/
		public final static String cscsiAddTime="cscsi_add_time";
		/** 修改时间 [非空]       **/
		public final static String cscsiUpdateTime="cscsi_update_time";
		/** 数据状态 [可空]       **/
		public final static String cscsiStatus="cscsi_status";
		/** 备注 [非空]       **/
		public final static String cscsiRemark="cscsi_remark";
		
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
				$.Set(name,CsCarSysIssue.getCsCarSysIssue(params));
			else
				$.Set(name,CsCarSysIssue.getCsCarSysIssueList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统车况报告数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCarSysIssue) $.GetRequest("CsCarSysIssue$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCarSysIssue.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCarSysIssue.getCsCarSysIssue(params);
			else
				value = CsCarSysIssue.getCsCarSysIssueList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCarSysIssue.Get($.add(CsCarSysIssue.F.cscsiId,param));
		else if(!$.empty(param.toString()))
			value = CsCarSysIssue.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCarSysIssue$"+param.hashCode(), value);
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
	public void mergeSet(CsCarSysIssue old){
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