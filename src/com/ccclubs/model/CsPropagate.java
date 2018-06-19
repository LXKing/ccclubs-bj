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

@namespace("operate/cms/propagate")
public @caption("传播内容") @table("cs_propagate") class CsPropagate implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("标题") @column("csp_title")    @note("  标题（32个字以内）") String cspTitle;// 非空  标题（32个字以内）   
	private @caption("平台类型") @column("csp_type")    @note(" 0:PC端 1:移动端  ") Short cspType;// 非空 0:PC端 1:移动端     
	private @caption("优先级") @column("csp_order")    @note("  数值越大，排序越靠前") Integer cspRecommend;// 非空  数值越大，排序越靠前   
	private @caption("程序标识") @column("csp_flag")    @note("  ") String cspFlag;//     
	private @caption("略缩图") @column("csp_thum")    @note("  在分类列表中显示的略缩图") String cspThum;//  在分类列表中显示的略缩图   
	private @caption("内容类型") @column("csp_content_type")    @note(" 0:编辑内容 1:html代码 2:转跳地址  ") Short cspContentType;// 非空 0:编辑内容 1:html代码 2:转跳地址     
	private @caption("编辑内容") @column("csp_content")  @hidden   @note("  ") String cspContent;//     
	private @caption("HTML代码") @column("csp_html")  @hidden   @note("  ") String cspHtml;//     
	private @caption("转到地址") @column("csp_redirect")  @hidden   @note("  转跳到哪个地址") String cspRedirect;//  转跳到哪个地址   
	private @caption("添加时间") @column("csp_add_time")    @note("  ") Date cspAddTime;// 非空     
	private @caption("更新时间") @column("csp_update_time")    @note("  ") Date cspUpdateTime;// 非空     
	private @caption("浏览次数") @column("csp_view_s")    @note("  ") Long cspViewS;// 非空     
	private @caption("评论次数") @column("csp_reply_s")  @hidden   @note("  ") Long cspReplyS;// 非空     
	private @caption("收藏次数") @column("csp_collect_s")  @hidden   @note("  ") Long cspCollectS;// 非空     
	private @caption("好评数") @column("csp_praise_s")  @hidden   @note("  ") Long cspPraiseS;// 非空     
	private @caption("状态") @column("csp_status")    @note(" 1:正常 0:无效  ") Short cspStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsPropagate(){
	
	}
	
	//主键构造函数
	public CsPropagate(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsPropagate(cspTitle,cspType,cspRecommend,cspFlag,cspThum,cspContentType,cspContent,cspHtml,cspRedirect,cspAddTime,cspUpdateTime,cspViewS,cspReplyS,cspCollectS,cspPraiseS,cspStatus)
	 CsPropagate(
	 	$.getString("cspTitle")//标题 [非空]
	 	,$.getShort("cspType")//平台类型 [非空]
	 	,$.getInteger("cspRecommend")//优先级 [非空]
	 	,$.getString("cspFlag")//程序标识
	 	,$.getString("cspThum")//略缩图
	 	,$.getShort("cspContentType")//内容类型 [非空]
	 	,$.getString("cspContent")//编辑内容
	 	,$.getString("cspHtml")//HTML代码
	 	,$.getString("cspRedirect")//转到地址
	 	,$.getDate("cspAddTime")//添加时间 [非空]
	 	,$.getDate("cspUpdateTime")//更新时间 [非空]
	 	,$.getLong("cspViewS")//浏览次数 [非空]
	 	,$.getLong("cspReplyS")//评论次数 [非空]
	 	,$.getLong("cspCollectS")//收藏次数 [非空]
	 	,$.getLong("cspPraiseS")//好评数 [非空]
	 	,$.getShort("cspStatus")//状态 [非空]
	 )
	**/
	public CsPropagate(String cspTitle,Short cspType,Integer cspRecommend,String cspFlag,String cspThum,Short cspContentType,String cspContent,String cspHtml,String cspRedirect,Date cspAddTime,Date cspUpdateTime,Long cspViewS,Long cspReplyS,Long cspCollectS,Long cspPraiseS,Short cspStatus){
		this.cspTitle=cspTitle;
		this.cspType=cspType;
		this.cspRecommend=cspRecommend;
		this.cspFlag=cspFlag;
		this.cspThum=cspThum;
		this.cspContentType=cspContentType;
		this.cspContent=cspContent;
		this.cspHtml=cspHtml;
		this.cspRedirect=cspRedirect;
		this.cspAddTime=cspAddTime;
		this.cspUpdateTime=cspUpdateTime;
		this.cspViewS=cspViewS;
		this.cspReplyS=cspReplyS;
		this.cspCollectS=cspCollectS;
		this.cspPraiseS=cspPraiseS;
		this.cspStatus=cspStatus;
	}
	
	//设置非空字段
	public CsPropagate setNotNull(String cspTitle,Short cspType,Integer cspRecommend,Short cspContentType,Date cspAddTime,Date cspUpdateTime,Long cspViewS,Long cspReplyS,Long cspCollectS,Long cspPraiseS,Short cspStatus){
		this.cspTitle=cspTitle;
		this.cspType=cspType;
		this.cspRecommend=cspRecommend;
		this.cspContentType=cspContentType;
		this.cspAddTime=cspAddTime;
		this.cspUpdateTime=cspUpdateTime;
		this.cspViewS=cspViewS;
		this.cspReplyS=cspReplyS;
		this.cspCollectS=cspCollectS;
		this.cspPraiseS=cspPraiseS;
		this.cspStatus=cspStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPropagate cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 标题 [非空]    标题（32个字以内）   **/
	public CsPropagate cspTitle(String cspTitle){
		this.cspTitle = cspTitle;
		this.setSeted(F.cspTitle);
		return this;
	}
	/** 平台类型 [非空]   0:PC端 1:移动端     **/
	public CsPropagate cspType(Short cspType){
		this.cspType = cspType;
		this.setSeted(F.cspType);
		return this;
	}
	/** 优先级 [非空]    数值越大，排序越靠前   **/
	public CsPropagate cspRecommend(Integer cspRecommend){
		this.cspRecommend = cspRecommend;
		this.setSeted(F.cspRecommend);
		return this;
	}
	/** 程序标识        **/
	public CsPropagate cspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
		return this;
	}
	/** 略缩图     在分类列表中显示的略缩图   **/
	public CsPropagate cspThum(String cspThum){
		this.cspThum = cspThum;
		this.setSeted(F.cspThum);
		return this;
	}
	/** 内容类型 [非空]   0:编辑内容 1:html代码 2:转跳地址     **/
	public CsPropagate cspContentType(Short cspContentType){
		this.cspContentType = cspContentType;
		this.setSeted(F.cspContentType);
		return this;
	}
	/** 编辑内容        **/
	public CsPropagate cspContent(String cspContent){
		this.cspContent = cspContent;
		this.setSeted(F.cspContent);
		return this;
	}
	/** HTML代码        **/
	public CsPropagate cspHtml(String cspHtml){
		this.cspHtml = cspHtml;
		this.setSeted(F.cspHtml);
		return this;
	}
	/** 转到地址     转跳到哪个地址   **/
	public CsPropagate cspRedirect(String cspRedirect){
		this.cspRedirect = cspRedirect;
		this.setSeted(F.cspRedirect);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsPropagate cspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
		return this;
	}
	/** 更新时间 [非空]       **/
	public CsPropagate cspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
		return this;
	}
	/** 浏览次数 [非空]       **/
	public CsPropagate cspViewS(Long cspViewS){
		this.cspViewS = cspViewS;
		this.setSeted(F.cspViewS);
		return this;
	}
	/** 评论次数 [非空]       **/
	public CsPropagate cspReplyS(Long cspReplyS){
		this.cspReplyS = cspReplyS;
		this.setSeted(F.cspReplyS);
		return this;
	}
	/** 收藏次数 [非空]       **/
	public CsPropagate cspCollectS(Long cspCollectS){
		this.cspCollectS = cspCollectS;
		this.setSeted(F.cspCollectS);
		return this;
	}
	/** 好评数 [非空]       **/
	public CsPropagate cspPraiseS(Long cspPraiseS){
		this.cspPraiseS = cspPraiseS;
		this.setSeted(F.cspPraiseS);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsPropagate cspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
		return this;
	}
	
	
	//克隆对象
	public CsPropagate clone(){
		CsPropagate clone = new CsPropagate();
		clone.cspTitle=this.cspTitle;
		clone.cspType=this.cspType;
		clone.cspRecommend=this.cspRecommend;
		clone.cspContentType=this.cspContentType;
		clone.cspAddTime=this.cspAddTime;
		clone.cspUpdateTime=this.cspUpdateTime;
		clone.cspViewS=this.cspViewS;
		clone.cspReplyS=this.cspReplyS;
		clone.cspCollectS=this.cspCollectS;
		clone.cspPraiseS=this.cspPraiseS;
		clone.cspStatus=this.cspStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取传播内容
	 * @param id
	 * @return
	 */
	public static @api CsPropagate get(Long id){		
		return getCsPropagateById(id);
	}
	/**
	 * 获取所有传播内容
	 * @return
	 */
	public static @api List<CsPropagate> list(Map params,Integer size){
		return getCsPropagateList(params,size);
	}
	/**
	 * 获取传播内容分页
	 * @return
	 */
	public static @api Page<CsPropagate> page(int page,int size,Map params){
		return getCsPropagatePage(page, size , params);
	}
	/**
	 * 根据查询条件取传播内容
	 * @param params
	 * @return
	 */
	public static @api CsPropagate Get(Map params){
		return getCsPropagate(params);
	}
	/**
	 * 获取传播内容数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPropagateCount(params);
	}
	/**
	 * 获取传播内容数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPropagateEval(eval,params);
	}
	
	/**
	 * 根据ID取传播内容
	 * @param id
	 * @return
	 */
	public static @api CsPropagate getCsPropagateById(Long id){		
		CsPropagate csPropagate = (CsPropagate) $.GetRequest("CsPropagate$"+id);
		if(csPropagate!=null)
			return csPropagate;
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");		
		return csPropagateService.getCsPropagateById(id);
	}
	
	
	/**
	 * 根据ID取传播内容的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPropagate.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPropagate csPropagate = get(id);
		if(csPropagate!=null){
			String strValue = csPropagate.getCspTitle$();
			if(!"CspTitle".equals("CspTitle"))
				strValue+="("+csPropagate.getCspTitle$()+")";
			MemCache.setValue(CsPropagate.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspTitle$();
		if(!"CspTitle".equals("CspTitle"))
			keyValue+="("+this.getCspTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有传播内容
	 * @return
	 */
	public static @api List<CsPropagate> getCsPropagateList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		return csPropagateService.getCsPropagateList(params, size);
	}
	
	/**
	 * 获取传播内容分页
	 * @return
	 */
	public static @api Page<CsPropagate> getCsPropagatePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		return csPropagateService.getCsPropagatePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取传播内容
	 * @param params
	 * @return
	 */
	public static @api CsPropagate getCsPropagate(Map params){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		return csPropagateService.getCsPropagate(params);
	}
	
	/**
	 * 获取传播内容数
	 * @return
	 */
	public static @api Long getCsPropagateCount(Map params){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		return csPropagateService.getCsPropagateCount(params);
	}
		
		
	/**
	 * 获取传播内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPropagateEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		return csPropagateService.getCsPropagateEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPropagate(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		csPropagateService.updateCsPropagateByConfirm(set, where);
	}
	
	
	/**
	 * 保存传播内容对象
	 * @param params
	 * @return
	 */
	public CsPropagate save(){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		if(this.getCspId()!=null)
			csPropagateService.updateCsPropagate(this);
		else
			return csPropagateService.saveCsPropagate(this);
		return this;
	}
	
	
	/**
	 * 更新传播内容对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		csPropagateService.updateCsPropagate$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		if($.equals($.config("logic_delete"),"true"))
			csPropagateService.removeCsPropagateById(this.getCspId());
		else
			csPropagateService.deleteCsPropagateById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPropagateService csPropagateService = $.GetSpringBean("csPropagateService");
		return csPropagateService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCspId(){
		return this.cspId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCspId$(){
		String strValue="";
		 strValue=$.str(this.getCspId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
	}
	/*******************************标题**********************************/	
	/**
	* 标题 [非空]    标题（32个字以内）  
	**/
	public String getCspTitle(){
		return this.cspTitle;
	}
	/**
	* 获取标题格式化(toString)
	**/
	public String getCspTitle$(){
		String strValue="";
		 strValue=$.str(this.getCspTitle());
	 	 return strValue;
	}
	/**
	* 标题 [非空]    标题（32个字以内）  
	**/
	public void setCspTitle(String cspTitle){
		this.cspTitle = cspTitle;
		this.setSeted(F.cspTitle);
	}
	/*******************************平台类型**********************************/	
	/**
	* 平台类型 [非空]   0:PC端 1:移动端    
	**/
	public Short getCspType(){
		return this.cspType;
	}
	/**
	* 获取平台类型格式化(toString)
	**/
	public String getCspType$(){
		String strValue="";
		 if($.equals($.str(this.getCspType()),"0"))
			strValue=$.str("PC端");		 
		 if($.equals($.str(this.getCspType()),"1"))
			strValue=$.str("移动端");		 
	 	 return strValue;
	}
	/**
	* 平台类型 [非空]   0:PC端 1:移动端    
	**/
	public void setCspType(Short cspType){
		this.cspType = cspType;
		this.setSeted(F.cspType);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级 [非空]    数值越大，排序越靠前  
	**/
	public Integer getCspRecommend(){
		return this.cspRecommend;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getCspRecommend$(){
		String strValue="";
		 strValue=$.str(this.getCspRecommend());
	 	 return strValue;
	}
	/**
	* 优先级 [非空]    数值越大，排序越靠前  
	**/
	public void setCspRecommend(Integer cspRecommend){
		this.cspRecommend = cspRecommend;
		this.setSeted(F.cspRecommend);
	}
	/*******************************程序标识**********************************/	
	/**
	* 程序标识       
	**/
	public String getCspFlag(){
		return this.cspFlag;
	}
	/**
	* 获取程序标识格式化(toString)
	**/
	public String getCspFlag$(){
		String strValue="";
		 strValue=$.str(this.getCspFlag());
	 	 return strValue;
	}
	/**
	* 程序标识       
	**/
	public void setCspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
	}
	/*******************************略缩图**********************************/	
	/**
	* 略缩图     在分类列表中显示的略缩图  
	**/
	public String getCspThum(){
		return this.cspThum;
	}
	/**
	* 获取略缩图格式化(toString)
	**/
	public String getCspThum$(){
		String strValue="";
		 strValue=$.str(this.getCspThum());
	 	 return strValue;
	}
	/**
	* 略缩图     在分类列表中显示的略缩图  
	**/
	public void setCspThum(String cspThum){
		this.cspThum = cspThum;
		this.setSeted(F.cspThum);
	}
	/*******************************内容类型**********************************/	
	/**
	* 内容类型 [非空]   0:编辑内容 1:html代码 2:转跳地址    
	**/
	public Short getCspContentType(){
		return this.cspContentType;
	}
	/**
	* 获取内容类型格式化(toString)
	**/
	public String getCspContentType$(){
		String strValue="";
		 if($.equals($.str(this.getCspContentType()),"0"))
			strValue=$.str("编辑内容");		 
		 if($.equals($.str(this.getCspContentType()),"1"))
			strValue=$.str("html代码");		 
		 if($.equals($.str(this.getCspContentType()),"2"))
			strValue=$.str("转跳地址");		 
	 	 return strValue;
	}
	/**
	* 内容类型 [非空]   0:编辑内容 1:html代码 2:转跳地址    
	**/
	public void setCspContentType(Short cspContentType){
		this.cspContentType = cspContentType;
		this.setSeted(F.cspContentType);
	}
	/*******************************编辑内容**********************************/	
	/**
	* 编辑内容       
	**/
	public String getCspContent(){
		return this.cspContent;
	}
	/**
	* 获取编辑内容格式化(toString)
	**/
	public String getCspContent$(){
		String strValue="";
		 strValue=$.str(this.getCspContent());
	 	 return strValue;
	}
	/**
	* 编辑内容       
	**/
	public void setCspContent(String cspContent){
		this.cspContent = cspContent;
		this.setSeted(F.cspContent);
	}
	/*******************************HTML代码**********************************/	
	/**
	* HTML代码       
	**/
	public String getCspHtml(){
		return this.cspHtml;
	}
	/**
	* 获取HTML代码格式化(toString)
	**/
	public String getCspHtml$(){
		String strValue="";
		 strValue=$.str(this.getCspHtml());
	 	 return strValue;
	}
	/**
	* HTML代码       
	**/
	public void setCspHtml(String cspHtml){
		this.cspHtml = cspHtml;
		this.setSeted(F.cspHtml);
	}
	/*******************************转到地址**********************************/	
	/**
	* 转到地址     转跳到哪个地址  
	**/
	public String getCspRedirect(){
		return this.cspRedirect;
	}
	/**
	* 获取转到地址格式化(toString)
	**/
	public String getCspRedirect$(){
		String strValue="";
		 strValue=$.str(this.getCspRedirect());
	 	 return strValue;
	}
	/**
	* 转到地址     转跳到哪个地址  
	**/
	public void setCspRedirect(String cspRedirect){
		this.cspRedirect = cspRedirect;
		this.setSeted(F.cspRedirect);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCspAddTime(){
		return this.cspAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCspAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
	}
	/*******************************更新时间**********************************/	
	/**
	* 更新时间 [非空]      
	**/
	public Date getCspUpdateTime(){
		return this.cspUpdateTime;
	}
	/**
	* 获取更新时间格式化(toString)
	**/
	public String getCspUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 更新时间 [非空]      
	**/
	public void setCspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
	}
	/*******************************浏览次数**********************************/	
	/**
	* 浏览次数 [非空]      
	**/
	public Long getCspViewS(){
		return this.cspViewS;
	}
	/**
	* 获取浏览次数格式化(toString)
	**/
	public String getCspViewS$(){
		String strValue="";
		 strValue=$.str(this.getCspViewS());
	 	 return strValue;
	}
	/**
	* 浏览次数 [非空]      
	**/
	public void setCspViewS(Long cspViewS){
		this.cspViewS = cspViewS;
		this.setSeted(F.cspViewS);
	}
	/*******************************评论次数**********************************/	
	/**
	* 评论次数 [非空]      
	**/
	public Long getCspReplyS(){
		return this.cspReplyS;
	}
	/**
	* 获取评论次数格式化(toString)
	**/
	public String getCspReplyS$(){
		String strValue="";
		 strValue=$.str(this.getCspReplyS());
	 	 return strValue;
	}
	/**
	* 评论次数 [非空]      
	**/
	public void setCspReplyS(Long cspReplyS){
		this.cspReplyS = cspReplyS;
		this.setSeted(F.cspReplyS);
	}
	/*******************************收藏次数**********************************/	
	/**
	* 收藏次数 [非空]      
	**/
	public Long getCspCollectS(){
		return this.cspCollectS;
	}
	/**
	* 获取收藏次数格式化(toString)
	**/
	public String getCspCollectS$(){
		String strValue="";
		 strValue=$.str(this.getCspCollectS());
	 	 return strValue;
	}
	/**
	* 收藏次数 [非空]      
	**/
	public void setCspCollectS(Long cspCollectS){
		this.cspCollectS = cspCollectS;
		this.setSeted(F.cspCollectS);
	}
	/*******************************好评数**********************************/	
	/**
	* 好评数 [非空]      
	**/
	public Long getCspPraiseS(){
		return this.cspPraiseS;
	}
	/**
	* 获取好评数格式化(toString)
	**/
	public String getCspPraiseS$(){
		String strValue="";
		 strValue=$.str(this.getCspPraiseS());
	 	 return strValue;
	}
	/**
	* 好评数 [非空]      
	**/
	public void setCspPraiseS(Long cspPraiseS){
		this.cspPraiseS = cspPraiseS;
		this.setSeted(F.cspPraiseS);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCspStatus(){
		return this.cspStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCspStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCspStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCspStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPropagate.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPropagate.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPropagate.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPropagate.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPropagate.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPropagate.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPropagate.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPropagate.this);
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
		public M cspId(Object cspId){this.put("cspId", cspId);return this;};
	 	/** and csp_id is null */
 		public M cspIdNull(){if(this.get("cspIdNot")==null)this.put("cspIdNot", "");this.put("cspId", null);return this;};
 		/** not .... */
 		public M cspIdNot(){this.put("cspIdNot", "not");return this;};
		/** 标题 [非空]    标题（32个字以内）   **/
		public M cspTitle(Object cspTitle){this.put("cspTitle", cspTitle);return this;};
	 	/** and csp_title is null */
 		public M cspTitleNull(){if(this.get("cspTitleNot")==null)this.put("cspTitleNot", "");this.put("cspTitle", null);return this;};
 		/** not .... */
 		public M cspTitleNot(){this.put("cspTitleNot", "not");return this;};
		/** 平台类型 [非空]   0:PC端 1:移动端     **/
		public M cspType(Object cspType){this.put("cspType", cspType);return this;};
	 	/** and csp_type is null */
 		public M cspTypeNull(){if(this.get("cspTypeNot")==null)this.put("cspTypeNot", "");this.put("cspType", null);return this;};
 		/** not .... */
 		public M cspTypeNot(){this.put("cspTypeNot", "not");return this;};
		/** 优先级 [非空]    数值越大，排序越靠前   **/
		public M cspRecommend(Object cspRecommend){this.put("cspRecommend", cspRecommend);return this;};
	 	/** and csp_order is null */
 		public M cspRecommendNull(){if(this.get("cspRecommendNot")==null)this.put("cspRecommendNot", "");this.put("cspRecommend", null);return this;};
 		/** not .... */
 		public M cspRecommendNot(){this.put("cspRecommendNot", "not");return this;};
		/** and csp_order >= ? */
		public M cspRecommendMin(Object min){this.put("cspRecommendMin", min);return this;};
		/** and csp_order <= ? */
		public M cspRecommendMax(Object max){this.put("cspRecommendMax", max);return this;};
		/** 程序标识        **/
		public M cspFlag(Object cspFlag){this.put("cspFlag", cspFlag);return this;};
	 	/** and csp_flag is null */
 		public M cspFlagNull(){if(this.get("cspFlagNot")==null)this.put("cspFlagNot", "");this.put("cspFlag", null);return this;};
 		/** not .... */
 		public M cspFlagNot(){this.put("cspFlagNot", "not");return this;};
		/** 略缩图     在分类列表中显示的略缩图   **/
		public M cspThum(Object cspThum){this.put("cspThum", cspThum);return this;};
	 	/** and csp_thum is null */
 		public M cspThumNull(){if(this.get("cspThumNot")==null)this.put("cspThumNot", "");this.put("cspThum", null);return this;};
 		/** not .... */
 		public M cspThumNot(){this.put("cspThumNot", "not");return this;};
		/** 内容类型 [非空]   0:编辑内容 1:html代码 2:转跳地址     **/
		public M cspContentType(Object cspContentType){this.put("cspContentType", cspContentType);return this;};
	 	/** and csp_content_type is null */
 		public M cspContentTypeNull(){if(this.get("cspContentTypeNot")==null)this.put("cspContentTypeNot", "");this.put("cspContentType", null);return this;};
 		/** not .... */
 		public M cspContentTypeNot(){this.put("cspContentTypeNot", "not");return this;};
		/** 编辑内容        **/
		public M cspContent(Object cspContent){this.put("cspContent", cspContent);return this;};
	 	/** and csp_content is null */
 		public M cspContentNull(){if(this.get("cspContentNot")==null)this.put("cspContentNot", "");this.put("cspContent", null);return this;};
 		/** not .... */
 		public M cspContentNot(){this.put("cspContentNot", "not");return this;};
		/** HTML代码        **/
		public M cspHtml(Object cspHtml){this.put("cspHtml", cspHtml);return this;};
	 	/** and csp_html is null */
 		public M cspHtmlNull(){if(this.get("cspHtmlNot")==null)this.put("cspHtmlNot", "");this.put("cspHtml", null);return this;};
 		/** not .... */
 		public M cspHtmlNot(){this.put("cspHtmlNot", "not");return this;};
		/** 转到地址     转跳到哪个地址   **/
		public M cspRedirect(Object cspRedirect){this.put("cspRedirect", cspRedirect);return this;};
	 	/** and csp_redirect is null */
 		public M cspRedirectNull(){if(this.get("cspRedirectNot")==null)this.put("cspRedirectNot", "");this.put("cspRedirect", null);return this;};
 		/** not .... */
 		public M cspRedirectNot(){this.put("cspRedirectNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M cspAddTime(Object cspAddTime){this.put("cspAddTime", cspAddTime);return this;};
	 	/** and csp_add_time is null */
 		public M cspAddTimeNull(){if(this.get("cspAddTimeNot")==null)this.put("cspAddTimeNot", "");this.put("cspAddTime", null);return this;};
 		/** not .... */
 		public M cspAddTimeNot(){this.put("cspAddTimeNot", "not");return this;};
 		/** and csp_add_time >= ? */
 		public M cspAddTimeStart(Object start){this.put("cspAddTimeStart", start);return this;};			
 		/** and csp_add_time <= ? */
 		public M cspAddTimeEnd(Object end){this.put("cspAddTimeEnd", end);return this;};
		/** 更新时间 [非空]       **/
		public M cspUpdateTime(Object cspUpdateTime){this.put("cspUpdateTime", cspUpdateTime);return this;};
	 	/** and csp_update_time is null */
 		public M cspUpdateTimeNull(){if(this.get("cspUpdateTimeNot")==null)this.put("cspUpdateTimeNot", "");this.put("cspUpdateTime", null);return this;};
 		/** not .... */
 		public M cspUpdateTimeNot(){this.put("cspUpdateTimeNot", "not");return this;};
 		/** and csp_update_time >= ? */
 		public M cspUpdateTimeStart(Object start){this.put("cspUpdateTimeStart", start);return this;};			
 		/** and csp_update_time <= ? */
 		public M cspUpdateTimeEnd(Object end){this.put("cspUpdateTimeEnd", end);return this;};
		/** 浏览次数 [非空]       **/
		public M cspViewS(Object cspViewS){this.put("cspViewS", cspViewS);return this;};
	 	/** and csp_view_s is null */
 		public M cspViewSNull(){if(this.get("cspViewSNot")==null)this.put("cspViewSNot", "");this.put("cspViewS", null);return this;};
 		/** not .... */
 		public M cspViewSNot(){this.put("cspViewSNot", "not");return this;};
		/** and csp_view_s >= ? */
		public M cspViewSMin(Object min){this.put("cspViewSMin", min);return this;};
		/** and csp_view_s <= ? */
		public M cspViewSMax(Object max){this.put("cspViewSMax", max);return this;};
		/** 评论次数 [非空]       **/
		public M cspReplyS(Object cspReplyS){this.put("cspReplyS", cspReplyS);return this;};
	 	/** and csp_reply_s is null */
 		public M cspReplySNull(){if(this.get("cspReplySNot")==null)this.put("cspReplySNot", "");this.put("cspReplyS", null);return this;};
 		/** not .... */
 		public M cspReplySNot(){this.put("cspReplySNot", "not");return this;};
		/** and csp_reply_s >= ? */
		public M cspReplySMin(Object min){this.put("cspReplySMin", min);return this;};
		/** and csp_reply_s <= ? */
		public M cspReplySMax(Object max){this.put("cspReplySMax", max);return this;};
		/** 收藏次数 [非空]       **/
		public M cspCollectS(Object cspCollectS){this.put("cspCollectS", cspCollectS);return this;};
	 	/** and csp_collect_s is null */
 		public M cspCollectSNull(){if(this.get("cspCollectSNot")==null)this.put("cspCollectSNot", "");this.put("cspCollectS", null);return this;};
 		/** not .... */
 		public M cspCollectSNot(){this.put("cspCollectSNot", "not");return this;};
		/** and csp_collect_s >= ? */
		public M cspCollectSMin(Object min){this.put("cspCollectSMin", min);return this;};
		/** and csp_collect_s <= ? */
		public M cspCollectSMax(Object max){this.put("cspCollectSMax", max);return this;};
		/** 好评数 [非空]       **/
		public M cspPraiseS(Object cspPraiseS){this.put("cspPraiseS", cspPraiseS);return this;};
	 	/** and csp_praise_s is null */
 		public M cspPraiseSNull(){if(this.get("cspPraiseSNot")==null)this.put("cspPraiseSNot", "");this.put("cspPraiseS", null);return this;};
 		/** not .... */
 		public M cspPraiseSNot(){this.put("cspPraiseSNot", "not");return this;};
		/** and csp_praise_s >= ? */
		public M cspPraiseSMin(Object min){this.put("cspPraiseSMin", min);return this;};
		/** and csp_praise_s <= ? */
		public M cspPraiseSMax(Object max){this.put("cspPraiseSMax", max);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cspStatus(Object cspStatus){this.put("cspStatus", cspStatus);return this;};
	 	/** and csp_status is null */
 		public M cspStatusNull(){if(this.get("cspStatusNot")==null)this.put("cspStatusNot", "");this.put("cspStatus", null);return this;};
 		/** not .... */
 		public M cspStatusNot(){this.put("cspStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有传播内容 **/
		public @api List<CsPropagate> list(Integer size){
			return getCsPropagateList(this,size);
		}
		/** 获取传播内容分页 **/
		public @api Page<CsPropagate> page(int page,int size){
			return getCsPropagatePage(page, size , this);
		}
		/** 根据查询条件取传播内容 **/
		public @api CsPropagate get(){
			return getCsPropagate(this);
		}
		/** 获取传播内容数 **/
		public @api Long count(){
			return getCsPropagateCount(this);
		}
		/** 获取传播内容表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPropagateEval(strEval,this);
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
			updateCsPropagate(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 标题 [非空]    标题（32个字以内）   **/
		public final static @type(String.class) @like String cspTitle="cspTitle";
		/** 平台类型 [非空]   0:PC端 1:移动端     **/
		public final static @type(Short.class)  String cspType="cspType";
		/** 优先级 [非空]    数值越大，排序越靠前   **/
		public final static @type(Integer.class)  String cspRecommend="cspRecommend";
		/** and csp_order >= ? */
		public final static @type(Integer.class) String cspRecommendMin="cspRecommendMin";
		/** and csp_order <= ? */
		public final static @type(Integer.class) String cspRecommendMax="cspRecommendMax";
		/** 程序标识        **/
		public final static @type(String.class) @like String cspFlag="cspFlag";
		/** 略缩图     在分类列表中显示的略缩图   **/
		public final static @type(String.class)  String cspThum="cspThum";
		/** 内容类型 [非空]   0:编辑内容 1:html代码 2:转跳地址     **/
		public final static @type(Short.class)  String cspContentType="cspContentType";
		/** 编辑内容        **/
		public final static @type(String.class) @like String cspContent="cspContent";
		/** HTML代码        **/
		public final static @type(String.class) @like String cspHtml="cspHtml";
		/** 转到地址     转跳到哪个地址   **/
		public final static @type(String.class) @like String cspRedirect="cspRedirect";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cspAddTime="cspAddTime";
	 	/** and csp_add_time >= ? */
 		public final static @type(Date.class) String cspAddTimeStart="cspAddTimeStart";
 		/** and csp_add_time <= ? */
 		public final static @type(Date.class) String cspAddTimeEnd="cspAddTimeEnd";
		/** 更新时间 [非空]       **/
		public final static @type(Date.class)  String cspUpdateTime="cspUpdateTime";
	 	/** and csp_update_time >= ? */
 		public final static @type(Date.class) String cspUpdateTimeStart="cspUpdateTimeStart";
 		/** and csp_update_time <= ? */
 		public final static @type(Date.class) String cspUpdateTimeEnd="cspUpdateTimeEnd";
		/** 浏览次数 [非空]       **/
		public final static @type(Long.class)  String cspViewS="cspViewS";
		/** and csp_view_s >= ? */
		public final static @type(Long.class) String cspViewSMin="cspViewSMin";
		/** and csp_view_s <= ? */
		public final static @type(Long.class) String cspViewSMax="cspViewSMax";
		/** 评论次数 [非空]       **/
		public final static @type(Long.class)  String cspReplyS="cspReplyS";
		/** and csp_reply_s >= ? */
		public final static @type(Long.class) String cspReplySMin="cspReplySMin";
		/** and csp_reply_s <= ? */
		public final static @type(Long.class) String cspReplySMax="cspReplySMax";
		/** 收藏次数 [非空]       **/
		public final static @type(Long.class)  String cspCollectS="cspCollectS";
		/** and csp_collect_s >= ? */
		public final static @type(Long.class) String cspCollectSMin="cspCollectSMin";
		/** and csp_collect_s <= ? */
		public final static @type(Long.class) String cspCollectSMax="cspCollectSMax";
		/** 好评数 [非空]       **/
		public final static @type(Long.class)  String cspPraiseS="cspPraiseS";
		/** and csp_praise_s >= ? */
		public final static @type(Long.class) String cspPraiseSMin="cspPraiseSMin";
		/** and csp_praise_s <= ? */
		public final static @type(Long.class) String cspPraiseSMax="cspPraiseSMax";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cspStatus="cspStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 标题 [非空]    标题（32个字以内）   **/
		public final static String cspTitle="csp_title";
		/** 平台类型 [非空]   0:PC端 1:移动端     **/
		public final static String cspType="csp_type";
		/** 优先级 [非空]    数值越大，排序越靠前   **/
		public final static String cspRecommend="csp_order";
		/** 程序标识        **/
		public final static String cspFlag="csp_flag";
		/** 略缩图     在分类列表中显示的略缩图   **/
		public final static String cspThum="csp_thum";
		/** 内容类型 [非空]   0:编辑内容 1:html代码 2:转跳地址     **/
		public final static String cspContentType="csp_content_type";
		/** 编辑内容        **/
		public final static String cspContent="csp_content";
		/** HTML代码        **/
		public final static String cspHtml="csp_html";
		/** 转到地址     转跳到哪个地址   **/
		public final static String cspRedirect="csp_redirect";
		/** 添加时间 [非空]       **/
		public final static String cspAddTime="csp_add_time";
		/** 更新时间 [非空]       **/
		public final static String cspUpdateTime="csp_update_time";
		/** 浏览次数 [非空]       **/
		public final static String cspViewS="csp_view_s";
		/** 评论次数 [非空]       **/
		public final static String cspReplyS="csp_reply_s";
		/** 收藏次数 [非空]       **/
		public final static String cspCollectS="csp_collect_s";
		/** 好评数 [非空]       **/
		public final static String cspPraiseS="csp_praise_s";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cspStatus="csp_status";
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
				$.Set(name,CsPropagate.getCsPropagate(params));
			else
				$.Set(name,CsPropagate.getCsPropagateList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取传播内容数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPropagate) $.GetRequest("CsPropagate$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPropagate.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPropagate.getCsPropagate(params);
			else
				value = CsPropagate.getCsPropagateList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPropagate.Get($.add(CsPropagate.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsPropagate.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPropagate$"+param.hashCode(), value);
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
	public void mergeSet(CsPropagate old){
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