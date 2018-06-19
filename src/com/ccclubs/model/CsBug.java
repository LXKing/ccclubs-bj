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

@namespace("systematic/bug")
public @caption("系统BUG") @table("cs_bug") class CsBug implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csb_id")   @primary  @note("  ") Long csbId;// 主键 非空     
	private @caption("BUG标题") @column("csb_title")    @note("  ") String csbTitle;// 非空     
	private @caption("BUG描述") @column("csb_descript")    @note("  ") String csbDescript;//     
	private @caption("BUG截图") @column("csb_images")    @note("  ") String csbImages;//     
	private @caption("BUG级别") @column("csb_level")    @note(" 0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大") Short csbLevel;// 非空 0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大   
	private @caption("提交人") @column("csb_tester")    @relate("$csbTester") @RelateClass(SrvUser.class)  @note("  ") Long csbTester;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csbTester;//关联对象[用户]
	private @caption("处理人") @column("csb_techer")    @relate("$csbTecher") @RelateClass(SrvUser.class)  @note("  ") Long csbTecher;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csbTecher;//关联对象[用户]
	private @caption("期望时间") @column("csb_expect_time")    @note("  期望问题解决的时间") Date csbExpectTime;//  期望问题解决的时间   
	private @caption("修改时间") @column("csb_update_time")    @note("  ") Date csbUpdateTime;// 非空     
	private @caption("添加时间") @column("csb_add_time")    @note("  ") Date csbAddTime;// 非空     
	private @caption("备注信息") @column("csb_remark")    @note("  ") String csbRemark;//     
	private @caption("状态") @column("csb_status")    @note(" 0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决  ") Short csbStatus;// 非空 0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决     
	
	//默认构造函数
	public CsBug(){
	
	}
	
	//主键构造函数
	public CsBug(Long id){
		this.csbId = id;
	}
	
	/**所有字段构造函数 CsBug(csbTitle,csbDescript,csbImages,csbLevel,csbTester,csbTecher,csbExpectTime,csbUpdateTime,csbAddTime,csbRemark,csbStatus)
	 CsBug(
	 	$.getString("csbTitle")//BUG标题 [非空]
	 	,$.getString("csbDescript")//BUG描述
	 	,$.getString("csbImages")//BUG截图
	 	,$.getShort("csbLevel")//BUG级别 [非空]
	 	,$.getLong("csbTester")//提交人 [非空]
	 	,$.getLong("csbTecher")//处理人
	 	,$.getDate("csbExpectTime")//期望时间
	 	,$.getDate("csbUpdateTime")//修改时间 [非空]
	 	,$.getDate("csbAddTime")//添加时间 [非空]
	 	,$.getString("csbRemark")//备注信息
	 	,$.getShort("csbStatus")//状态 [非空]
	 )
	**/
	public CsBug(String csbTitle,String csbDescript,String csbImages,Short csbLevel,Long csbTester,Long csbTecher,Date csbExpectTime,Date csbUpdateTime,Date csbAddTime,String csbRemark,Short csbStatus){
		this.csbTitle=csbTitle;
		this.csbDescript=csbDescript;
		this.csbImages=csbImages;
		this.csbLevel=csbLevel;
		this.csbTester=csbTester;
		this.csbTecher=csbTecher;
		this.csbExpectTime=csbExpectTime;
		this.csbUpdateTime=csbUpdateTime;
		this.csbAddTime=csbAddTime;
		this.csbRemark=csbRemark;
		this.csbStatus=csbStatus;
	}
	
	//设置非空字段
	public CsBug setNotNull(String csbTitle,Short csbLevel,Long csbTester,Date csbUpdateTime,Date csbAddTime,Short csbStatus){
		this.csbTitle=csbTitle;
		this.csbLevel=csbLevel;
		this.csbTester=csbTester;
		this.csbUpdateTime=csbUpdateTime;
		this.csbAddTime=csbAddTime;
		this.csbStatus=csbStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsBug csbId(Long csbId){
		this.csbId = csbId;
		this.setSeted(F.csbId);
		return this;
	}
	/** BUG标题 [非空]       **/
	public CsBug csbTitle(String csbTitle){
		this.csbTitle = csbTitle;
		this.setSeted(F.csbTitle);
		return this;
	}
	/** BUG描述        **/
	public CsBug csbDescript(String csbDescript){
		this.csbDescript = csbDescript;
		this.setSeted(F.csbDescript);
		return this;
	}
	/** BUG截图        **/
	public CsBug csbImages(String csbImages){
		this.csbImages = csbImages;
		this.setSeted(F.csbImages);
		return this;
	}
	/** BUG级别 [非空]   0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大   **/
	public CsBug csbLevel(Short csbLevel){
		this.csbLevel = csbLevel;
		this.setSeted(F.csbLevel);
		return this;
	}
	/** 提交人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsBug csbTester(Long csbTester){
		this.csbTester = csbTester;
		this.setSeted(F.csbTester);
		return this;
	}
	/** 处理人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsBug csbTecher(Long csbTecher){
		this.csbTecher = csbTecher;
		this.setSeted(F.csbTecher);
		return this;
	}
	/** 期望时间     期望问题解决的时间   **/
	public CsBug csbExpectTime(Date csbExpectTime){
		this.csbExpectTime = csbExpectTime;
		this.setSeted(F.csbExpectTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsBug csbUpdateTime(Date csbUpdateTime){
		this.csbUpdateTime = csbUpdateTime;
		this.setSeted(F.csbUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsBug csbAddTime(Date csbAddTime){
		this.csbAddTime = csbAddTime;
		this.setSeted(F.csbAddTime);
		return this;
	}
	/** 备注信息        **/
	public CsBug csbRemark(String csbRemark){
		this.csbRemark = csbRemark;
		this.setSeted(F.csbRemark);
		return this;
	}
	/** 状态 [非空]   0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决     **/
	public CsBug csbStatus(Short csbStatus){
		this.csbStatus = csbStatus;
		this.setSeted(F.csbStatus);
		return this;
	}
	
	
	//克隆对象
	public CsBug clone(){
		CsBug clone = new CsBug();
		clone.csbTitle=this.csbTitle;
		clone.csbLevel=this.csbLevel;
		clone.csbTester=this.csbTester;
		clone.csbUpdateTime=this.csbUpdateTime;
		clone.csbAddTime=this.csbAddTime;
		clone.csbStatus=this.csbStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统BUG
	 * @param id
	 * @return
	 */
	public static @api CsBug get(Long id){		
		return getCsBugById(id);
	}
	/**
	 * 获取所有系统BUG
	 * @return
	 */
	public static @api List<CsBug> list(Map params,Integer size){
		return getCsBugList(params,size);
	}
	/**
	 * 获取系统BUG分页
	 * @return
	 */
	public static @api Page<CsBug> page(int page,int size,Map params){
		return getCsBugPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统BUG
	 * @param params
	 * @return
	 */
	public static @api CsBug Get(Map params){
		return getCsBug(params);
	}
	/**
	 * 获取系统BUG数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsBugCount(params);
	}
	/**
	 * 获取系统BUG数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsBugEval(eval,params);
	}
	
	/**
	 * 根据ID取系统BUG
	 * @param id
	 * @return
	 */
	public static @api CsBug getCsBugById(Long id){		
		CsBug csBug = (CsBug) $.GetRequest("CsBug$"+id);
		if(csBug!=null)
			return csBug;
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");		
		return csBugService.getCsBugById(id);
	}
	
	
	/**
	 * 根据ID取系统BUG的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsBug.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsBug csBug = get(id);
		if(csBug!=null){
			String strValue = csBug.getCsbTitle$();
			if(!"CsbTitle".equals("CsbTitle"))
				strValue+="("+csBug.getCsbTitle$()+")";
			MemCache.setValue(CsBug.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsbTitle$();
		if(!"CsbTitle".equals("CsbTitle"))
			keyValue+="("+this.getCsbTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统BUG
	 * @return
	 */
	public static @api List<CsBug> getCsBugList(Map params,Integer size){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		return csBugService.getCsBugList(params, size);
	}
	
	/**
	 * 获取系统BUG分页
	 * @return
	 */
	public static @api Page<CsBug> getCsBugPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		return csBugService.getCsBugPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统BUG
	 * @param params
	 * @return
	 */
	public static @api CsBug getCsBug(Map params){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		return csBugService.getCsBug(params);
	}
	
	/**
	 * 获取系统BUG数
	 * @return
	 */
	public static @api Long getCsBugCount(Map params){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		return csBugService.getCsBugCount(params);
	}
		
		
	/**
	 * 获取系统BUG自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsBugEval(String eval,Map params){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		return csBugService.getCsBugEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsBug(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		csBugService.updateCsBugByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统BUG对象
	 * @param params
	 * @return
	 */
	public CsBug save(){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		if(this.getCsbId()!=null)
			csBugService.updateCsBug(this);
		else
			return csBugService.saveCsBug(this);
		return this;
	}
	
	
	/**
	 * 更新系统BUG对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		csBugService.updateCsBug$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		if($.equals($.config("logic_delete"),"true"))
			csBugService.removeCsBugById(this.getCsbId());
		else
			csBugService.deleteCsBugById(this.getCsbId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsBugService csBugService = $.GetSpringBean("csBugService");
		return csBugService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsbId(){
		return this.csbId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsbId$(){
		String strValue="";
		 strValue=$.str(this.getCsbId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsbId(Long csbId){
		this.csbId = csbId;
		this.setSeted(F.csbId);
	}
	/*******************************BUG标题**********************************/	
	/**
	* BUG标题 [非空]      
	**/
	public String getCsbTitle(){
		return this.csbTitle;
	}
	/**
	* 获取BUG标题格式化(toString)
	**/
	public String getCsbTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsbTitle());
	 	 return strValue;
	}
	/**
	* BUG标题 [非空]      
	**/
	public void setCsbTitle(String csbTitle){
		this.csbTitle = csbTitle;
		this.setSeted(F.csbTitle);
	}
	/*******************************BUG描述**********************************/	
	/**
	* BUG描述       
	**/
	public String getCsbDescript(){
		return this.csbDescript;
	}
	/**
	* 获取BUG描述格式化(toString)
	**/
	public String getCsbDescript$(){
		String strValue="";
		 strValue=$.str(this.getCsbDescript());
	 	 return strValue;
	}
	/**
	* BUG描述       
	**/
	public void setCsbDescript(String csbDescript){
		this.csbDescript = csbDescript;
		this.setSeted(F.csbDescript);
	}
	/*******************************BUG截图**********************************/	
	/**
	* BUG截图       
	**/
	public String getCsbImages(){
		return this.csbImages;
	}
	/**
	* 获取BUG截图格式化(toString)
	**/
	public String getCsbImages$(){
		String strValue="";
		 strValue=$.str(this.getCsbImages());
	 	 return strValue;
	}
	/**
	* BUG截图       
	**/
	public void setCsbImages(String csbImages){
		this.csbImages = csbImages;
		this.setSeted(F.csbImages);
	}
	/*******************************BUG级别**********************************/	
	/**
	* BUG级别 [非空]   0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大  
	**/
	public Short getCsbLevel(){
		return this.csbLevel;
	}
	/**
	* 获取BUG级别格式化(toString)
	**/
	public String getCsbLevel$(){
		String strValue="";
		 if($.equals($.str(this.getCsbLevel()),"0"))
			strValue=$.str("未设置");		 
		 if($.equals($.str(this.getCsbLevel()),"1"))
			strValue=$.str("一级");		 
		 if($.equals($.str(this.getCsbLevel()),"2"))
			strValue=$.str("二级");		 
		 if($.equals($.str(this.getCsbLevel()),"3"))
			strValue=$.str("三级");		 
		 if($.equals($.str(this.getCsbLevel()),"4"))
			strValue=$.str("四级");		 
		 if($.equals($.str(this.getCsbLevel()),"5"))
			strValue=$.str("五级");		 
		 if($.equals($.str(this.getCsbLevel()),"6"))
			strValue=$.str("六级");		 
		 if($.equals($.str(this.getCsbLevel()),"7"))
			strValue=$.str("七级");		 
	 	 return strValue;
	}
	/**
	* BUG级别 [非空]   0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大  
	**/
	public void setCsbLevel(Short csbLevel){
		this.csbLevel = csbLevel;
		this.setSeted(F.csbLevel);
	}
	/*******************************提交人**********************************/	
	/**
	* 提交人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsbTester(){
		return this.csbTester;
	}
	/**
	* 获取提交人格式化(toString)
	**/
	public String getCsbTester$(){
		String strValue="";
		if(this.getCsbTester()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsbTester()));
		}			
	 	 return strValue;
	}
	/**
	* 提交人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsbTester(Long csbTester){
		this.csbTester = csbTester;
		this.setSeted(F.csbTester);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csbTester(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsbTester());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsbTester()!=null){
 			srvUser = SrvUser.get(this.getCsbTester());
 		}
 		$.SetRequest("SrvUser$"+this.getCsbTester(), srvUser);
	 	return srvUser;
	}
	/*******************************处理人**********************************/	
	/**
	* 处理人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsbTecher(){
		return this.csbTecher;
	}
	/**
	* 获取处理人格式化(toString)
	**/
	public String getCsbTecher$(){
		String strValue="";
		if(this.getCsbTecher()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsbTecher()));
		}			
	 	 return strValue;
	}
	/**
	* 处理人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsbTecher(Long csbTecher){
		this.csbTecher = csbTecher;
		this.setSeted(F.csbTecher);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csbTecher(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsbTecher());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsbTecher()!=null){
 			srvUser = SrvUser.get(this.getCsbTecher());
 		}
 		$.SetRequest("SrvUser$"+this.getCsbTecher(), srvUser);
	 	return srvUser;
	}
	/*******************************期望时间**********************************/	
	/**
	* 期望时间     期望问题解决的时间  
	**/
	public Date getCsbExpectTime(){
		return this.csbExpectTime;
	}
	/**
	* 获取期望时间格式化(toString)
	**/
	public String getCsbExpectTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsbExpectTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 期望时间     期望问题解决的时间  
	**/
	public void setCsbExpectTime(Date csbExpectTime){
		this.csbExpectTime = csbExpectTime;
		this.setSeted(F.csbExpectTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsbUpdateTime(){
		return this.csbUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsbUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsbUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsbUpdateTime(Date csbUpdateTime){
		this.csbUpdateTime = csbUpdateTime;
		this.setSeted(F.csbUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsbAddTime(){
		return this.csbAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsbAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsbAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsbAddTime(Date csbAddTime){
		this.csbAddTime = csbAddTime;
		this.setSeted(F.csbAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsbRemark(){
		return this.csbRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsbRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsbRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsbRemark(String csbRemark){
		this.csbRemark = csbRemark;
		this.setSeted(F.csbRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决    
	**/
	public Short getCsbStatus(){
		return this.csbStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsbStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsbStatus()),"0"))
			strValue=$.str("已提交");		 
		 if($.equals($.str(this.getCsbStatus()),"1"))
			strValue=$.str("待处理");		 
		 if($.equals($.str(this.getCsbStatus()),"2"))
			strValue=$.str("已处理");		 
		 if($.equals($.str(this.getCsbStatus()),"3"))
			strValue=$.str("待商榷");		 
		 if($.equals($.str(this.getCsbStatus()),"4"))
			strValue=$.str("已否决");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决    
	**/
	public void setCsbStatus(Short csbStatus){
		this.csbStatus = csbStatus;
		this.setSeted(F.csbStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsBug.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsBug.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsBug.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsBug.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsBug.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsBug.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsBug.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsBug.this);
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
		public M csbId(Object csbId){this.put("csbId", csbId);return this;};
	 	/** and csb_id is null */
 		public M csbIdNull(){if(this.get("csbIdNot")==null)this.put("csbIdNot", "");this.put("csbId", null);return this;};
 		/** not .... */
 		public M csbIdNot(){this.put("csbIdNot", "not");return this;};
		/** BUG标题 [非空]       **/
		public M csbTitle(Object csbTitle){this.put("csbTitle", csbTitle);return this;};
	 	/** and csb_title is null */
 		public M csbTitleNull(){if(this.get("csbTitleNot")==null)this.put("csbTitleNot", "");this.put("csbTitle", null);return this;};
 		/** not .... */
 		public M csbTitleNot(){this.put("csbTitleNot", "not");return this;};
		/** BUG描述        **/
		public M csbDescript(Object csbDescript){this.put("csbDescript", csbDescript);return this;};
	 	/** and csb_descript is null */
 		public M csbDescriptNull(){if(this.get("csbDescriptNot")==null)this.put("csbDescriptNot", "");this.put("csbDescript", null);return this;};
 		/** not .... */
 		public M csbDescriptNot(){this.put("csbDescriptNot", "not");return this;};
		/** BUG截图        **/
		public M csbImages(Object csbImages){this.put("csbImages", csbImages);return this;};
	 	/** and csb_images is null */
 		public M csbImagesNull(){if(this.get("csbImagesNot")==null)this.put("csbImagesNot", "");this.put("csbImages", null);return this;};
 		/** not .... */
 		public M csbImagesNot(){this.put("csbImagesNot", "not");return this;};
		/** BUG级别 [非空]   0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大   **/
		public M csbLevel(Object csbLevel){this.put("csbLevel", csbLevel);return this;};
	 	/** and csb_level is null */
 		public M csbLevelNull(){if(this.get("csbLevelNot")==null)this.put("csbLevelNot", "");this.put("csbLevel", null);return this;};
 		/** not .... */
 		public M csbLevelNot(){this.put("csbLevelNot", "not");return this;};
		/** 提交人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csbTester(Object csbTester){this.put("csbTester", csbTester);return this;};
	 	/** and csb_tester is null */
 		public M csbTesterNull(){if(this.get("csbTesterNot")==null)this.put("csbTesterNot", "");this.put("csbTester", null);return this;};
 		/** not .... */
 		public M csbTesterNot(){this.put("csbTesterNot", "not");return this;};
		/** 处理人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csbTecher(Object csbTecher){this.put("csbTecher", csbTecher);return this;};
	 	/** and csb_techer is null */
 		public M csbTecherNull(){if(this.get("csbTecherNot")==null)this.put("csbTecherNot", "");this.put("csbTecher", null);return this;};
 		/** not .... */
 		public M csbTecherNot(){this.put("csbTecherNot", "not");return this;};
		/** 期望时间     期望问题解决的时间   **/
		public M csbExpectTime(Object csbExpectTime){this.put("csbExpectTime", csbExpectTime);return this;};
	 	/** and csb_expect_time is null */
 		public M csbExpectTimeNull(){if(this.get("csbExpectTimeNot")==null)this.put("csbExpectTimeNot", "");this.put("csbExpectTime", null);return this;};
 		/** not .... */
 		public M csbExpectTimeNot(){this.put("csbExpectTimeNot", "not");return this;};
 		/** and csb_expect_time >= ? */
 		public M csbExpectTimeStart(Object start){this.put("csbExpectTimeStart", start);return this;};			
 		/** and csb_expect_time <= ? */
 		public M csbExpectTimeEnd(Object end){this.put("csbExpectTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csbUpdateTime(Object csbUpdateTime){this.put("csbUpdateTime", csbUpdateTime);return this;};
	 	/** and csb_update_time is null */
 		public M csbUpdateTimeNull(){if(this.get("csbUpdateTimeNot")==null)this.put("csbUpdateTimeNot", "");this.put("csbUpdateTime", null);return this;};
 		/** not .... */
 		public M csbUpdateTimeNot(){this.put("csbUpdateTimeNot", "not");return this;};
 		/** and csb_update_time >= ? */
 		public M csbUpdateTimeStart(Object start){this.put("csbUpdateTimeStart", start);return this;};			
 		/** and csb_update_time <= ? */
 		public M csbUpdateTimeEnd(Object end){this.put("csbUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csbAddTime(Object csbAddTime){this.put("csbAddTime", csbAddTime);return this;};
	 	/** and csb_add_time is null */
 		public M csbAddTimeNull(){if(this.get("csbAddTimeNot")==null)this.put("csbAddTimeNot", "");this.put("csbAddTime", null);return this;};
 		/** not .... */
 		public M csbAddTimeNot(){this.put("csbAddTimeNot", "not");return this;};
 		/** and csb_add_time >= ? */
 		public M csbAddTimeStart(Object start){this.put("csbAddTimeStart", start);return this;};			
 		/** and csb_add_time <= ? */
 		public M csbAddTimeEnd(Object end){this.put("csbAddTimeEnd", end);return this;};
		/** 备注信息        **/
		public M csbRemark(Object csbRemark){this.put("csbRemark", csbRemark);return this;};
	 	/** and csb_remark is null */
 		public M csbRemarkNull(){if(this.get("csbRemarkNot")==null)this.put("csbRemarkNot", "");this.put("csbRemark", null);return this;};
 		/** not .... */
 		public M csbRemarkNot(){this.put("csbRemarkNot", "not");return this;};
		/** 状态 [非空]   0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决     **/
		public M csbStatus(Object csbStatus){this.put("csbStatus", csbStatus);return this;};
	 	/** and csb_status is null */
 		public M csbStatusNull(){if(this.get("csbStatusNot")==null)this.put("csbStatusNot", "");this.put("csbStatus", null);return this;};
 		/** not .... */
 		public M csbStatusNot(){this.put("csbStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统BUG **/
		public @api List<CsBug> list(Integer size){
			return getCsBugList(this,size);
		}
		/** 获取系统BUG分页 **/
		public @api Page<CsBug> page(int page,int size){
			return getCsBugPage(page, size , this);
		}
		/** 根据查询条件取系统BUG **/
		public @api CsBug get(){
			return getCsBug(this);
		}
		/** 获取系统BUG数 **/
		public @api Long count(){
			return getCsBugCount(this);
		}
		/** 获取系统BUG表达式 **/
		public @api <T> T eval(String strEval){
			return getCsBugEval(strEval,this);
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
			updateCsBug(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csbId="csbId";
		/** BUG标题 [非空]       **/
		public final static @type(String.class) @like String csbTitle="csbTitle";
		/** BUG描述        **/
		public final static @type(String.class) @like String csbDescript="csbDescript";
		/** BUG截图        **/
		public final static @type(String.class)  String csbImages="csbImages";
		/** BUG级别 [非空]   0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大   **/
		public final static @type(Short.class)  String csbLevel="csbLevel";
		/** 提交人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csbTester="csbTester";
		/** 处理人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csbTecher="csbTecher";
		/** 期望时间     期望问题解决的时间   **/
		public final static @type(Date.class)  String csbExpectTime="csbExpectTime";
	 	/** and csb_expect_time >= ? */
 		public final static @type(Date.class) String csbExpectTimeStart="csbExpectTimeStart";
 		/** and csb_expect_time <= ? */
 		public final static @type(Date.class) String csbExpectTimeEnd="csbExpectTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csbUpdateTime="csbUpdateTime";
	 	/** and csb_update_time >= ? */
 		public final static @type(Date.class) String csbUpdateTimeStart="csbUpdateTimeStart";
 		/** and csb_update_time <= ? */
 		public final static @type(Date.class) String csbUpdateTimeEnd="csbUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csbAddTime="csbAddTime";
	 	/** and csb_add_time >= ? */
 		public final static @type(Date.class) String csbAddTimeStart="csbAddTimeStart";
 		/** and csb_add_time <= ? */
 		public final static @type(Date.class) String csbAddTimeEnd="csbAddTimeEnd";
		/** 备注信息        **/
		public final static @type(String.class) @like String csbRemark="csbRemark";
		/** 状态 [非空]   0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决     **/
		public final static @type(Short.class)  String csbStatus="csbStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csbId="csb_id";
		/** BUG标题 [非空]       **/
		public final static String csbTitle="csb_title";
		/** BUG描述        **/
		public final static String csbDescript="csb_descript";
		/** BUG截图        **/
		public final static String csbImages="csb_images";
		/** BUG级别 [非空]   0:未设置 1:一级 2:二级 3:三级 4:四级 5:五级 6:六级 7:七级  级别越大，问题越大   **/
		public final static String csbLevel="csb_level";
		/** 提交人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csbTester="csb_tester";
		/** 处理人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csbTecher="csb_techer";
		/** 期望时间     期望问题解决的时间   **/
		public final static String csbExpectTime="csb_expect_time";
		/** 修改时间 [非空]       **/
		public final static String csbUpdateTime="csb_update_time";
		/** 添加时间 [非空]       **/
		public final static String csbAddTime="csb_add_time";
		/** 备注信息        **/
		public final static String csbRemark="csb_remark";
		/** 状态 [非空]   0:已提交 1:待处理 2:已处理 3:待商榷 4:已否决     **/
		public final static String csbStatus="csb_status";
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
				$.Set(name,CsBug.getCsBug(params));
			else
				$.Set(name,CsBug.getCsBugList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统BUG数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsBug) $.GetRequest("CsBug$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsBug.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsBug.getCsBug(params);
			else
				value = CsBug.getCsBugList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsBug.Get($.add(CsBug.F.csbId,param));
		else if(!$.empty(param.toString()))
			value = CsBug.get(Long.valueOf(param.toString()));
		$.SetRequest("CsBug$"+param.hashCode(), value);
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
	public void mergeSet(CsBug old){
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