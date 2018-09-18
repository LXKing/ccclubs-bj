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

@namespace("cms/content")
public @caption("静态内容") @table("cs_content") class CsContent implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csc_id")   @primary  @note("  ") Long cscId;// 主键 非空     
	private @caption("标题") @column("csc_title")    @note("  标题（32个字以内）") String cscTitle;// 非空  标题（32个字以内）   
	private @caption("上级节点") @column("csc_parent")    @note("  ") Long cscParent;//     
	private @caption("系统标识") @column("csc_flag")    @note("  ") String cscFlag;//     
	private @caption("优先级") @column("csc_order")    @note("  数值越大，排序越靠前") Integer cscRecommend;// 非空  数值越大，排序越靠前   
	private @caption("略缩图") @column("csc_thum")    @note(" 210:width 140:height  在分类列表中显示的略缩图") String cscThum;// 210:width 140:height  在分类列表中显示的略缩图   
	private @caption("简介") @column("csc_introduction")  @hidden   @note("  ") String cscIntroduction;//     
	private @caption("关键字") @column("csc_keywords")    @note("  百度搜索此关键字，多个关键字之前逗号分隔") String cscKeywords;//  百度搜索此关键字，多个关键字之前逗号分隔   
	private @caption("预览图") @column("csc_images")    @note("  在详情显示的时候显示的一些效果图") String cscImages;//  在详情显示的时候显示的一些效果图   
	private @caption("内容") @column("csc_content")  @hidden   @note("  ") String cscContent;//     
	private @caption("文件") @column("csc_file")    @note("  下载的文件，多个文件请压缩") String cscFile;//  下载的文件，多个文件请压缩   
	private @caption("添加时间") @column("csc_add_time")    @note("  ") Date cscAddTime;// 非空     
	private @caption("更新时间") @column("csc_update_time")    @note("  ") Date cscUpdateTime;// 非空     
	private @caption("浏览次数") @column("csc_view_s")    @note("  ") Long cscViewS;// 非空     
	private @caption("评论次数") @column("csc_reply_s")    @note("  ") Long cscReplyS;// 非空     
	private @caption("收藏次数") @column("csc_collect_s")    @note("  ") Long cscCollectS;// 非空     
	private @caption("好评数") @column("csc_praise_s")    @note("  ") Long cscPraiseS;// 非空     
	private @caption("状态") @column("csc_status")    @note(" 1:正常 0:无效  ") Short cscStatus;// 非空 1:正常 0:无效     
	private @caption("活动盒子标识") @column("csc_is_activity")    @note(" 1:是 0:否  ") Short cscIsActivity;// 活动盒子标识 1:是 0:否
	private @caption("活动事件") @column("csc_activity_event")    @note("   ") String cscActivityEvent;// 活动事件
	
    //默认构造函数
	public CsContent(){
	
	}
	
	//主键构造函数
	public CsContent(Long id){
		this.cscId = id;
	}
	
	/**所有字段构造函数 CsContent(cscTitle,cscParent,cscFlag,cscRecommend,cscThum,cscIntroduction,cscKeywords,cscImages,cscContent,cscFile,cscAddTime,cscUpdateTime,cscViewS,cscReplyS,cscCollectS,cscPraiseS,cscStatus)
	 CsContent(
	 	$.getString("cscTitle")//标题 [非空]
	 	,$.getLong("cscParent")//上级节点
	 	,$.getString("cscFlag")//系统标识
	 	,$.getInteger("cscRecommend")//优先级 [非空]
	 	,$.getString("cscThum")//略缩图
	 	,$.getString("cscIntroduction")//简介
	 	,$.getString("cscKeywords")//关键字
	 	,$.getString("cscImages")//预览图
	 	,$.getString("cscContent")//内容
	 	,$.getString("cscFile")//文件
	 	,$.getDate("cscAddTime")//添加时间 [非空]
	 	,$.getDate("cscUpdateTime")//更新时间 [非空]
	 	,$.getLong("cscViewS")//浏览次数 [非空]
	 	,$.getLong("cscReplyS")//评论次数 [非空]
	 	,$.getLong("cscCollectS")//收藏次数 [非空]
	 	,$.getLong("cscPraiseS")//好评数 [非空]
	 	,$.getShort("cscStatus")//状态 [非空]
	 )
	**/
	public CsContent(String cscTitle,Long cscParent,String cscFlag,Integer cscRecommend,String cscThum,String cscIntroduction,String cscKeywords,String cscImages,String cscContent,String cscFile,Date cscAddTime,Date cscUpdateTime,Long cscViewS,Long cscReplyS,Long cscCollectS,Long cscPraiseS,Short cscStatus){
		this.cscTitle=cscTitle;
		this.cscParent=cscParent;
		this.cscFlag=cscFlag;
		this.cscRecommend=cscRecommend;
		this.cscThum=cscThum;
		this.cscIntroduction=cscIntroduction;
		this.cscKeywords=cscKeywords;
		this.cscImages=cscImages;
		this.cscContent=cscContent;
		this.cscFile=cscFile;
		this.cscAddTime=cscAddTime;
		this.cscUpdateTime=cscUpdateTime;
		this.cscViewS=cscViewS;
		this.cscReplyS=cscReplyS;
		this.cscCollectS=cscCollectS;
		this.cscPraiseS=cscPraiseS;
		this.cscStatus=cscStatus;
	}
	
	//设置非空字段
	public CsContent setNotNull(String cscTitle,Integer cscRecommend,Date cscAddTime,Date cscUpdateTime,Long cscViewS,Long cscReplyS,Long cscCollectS,Long cscPraiseS,Short cscStatus){
		this.cscTitle=cscTitle;
		this.cscRecommend=cscRecommend;
		this.cscAddTime=cscAddTime;
		this.cscUpdateTime=cscUpdateTime;
		this.cscViewS=cscViewS;
		this.cscReplyS=cscReplyS;
		this.cscCollectS=cscCollectS;
		this.cscPraiseS=cscPraiseS;
		this.cscStatus=cscStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsContent cscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
		return this;
	}
	/** 标题 [非空]    标题（32个字以内）   **/
	public CsContent cscTitle(String cscTitle){
		this.cscTitle = cscTitle;
		this.setSeted(F.cscTitle);
		return this;
	}
	/** 上级节点        **/
	public CsContent cscParent(Long cscParent){
		this.cscParent = cscParent;
		this.setSeted(F.cscParent);
		return this;
	}
	/** 系统标识        **/
	public CsContent cscFlag(String cscFlag){
		this.cscFlag = cscFlag;
		this.setSeted(F.cscFlag);
		return this;
	}
	/** 优先级 [非空]    数值越大，排序越靠前   **/
	public CsContent cscRecommend(Integer cscRecommend){
		this.cscRecommend = cscRecommend;
		this.setSeted(F.cscRecommend);
		return this;
	}
	/** 略缩图    210:width 140:height  在分类列表中显示的略缩图   **/
	public CsContent cscThum(String cscThum){
		this.cscThum = cscThum;
		this.setSeted(F.cscThum);
		return this;
	}
	/** 简介        **/
	public CsContent cscIntroduction(String cscIntroduction){
		this.cscIntroduction = cscIntroduction;
		this.setSeted(F.cscIntroduction);
		return this;
	}
	/** 关键字     百度搜索此关键字，多个关键字之前逗号分隔   **/
	public CsContent cscKeywords(String cscKeywords){
		this.cscKeywords = cscKeywords;
		this.setSeted(F.cscKeywords);
		return this;
	}
	/** 预览图     在详情显示的时候显示的一些效果图   **/
	public CsContent cscImages(String cscImages){
		this.cscImages = cscImages;
		this.setSeted(F.cscImages);
		return this;
	}
	/** 内容        **/
	public CsContent cscContent(String cscContent){
		this.cscContent = cscContent;
		this.setSeted(F.cscContent);
		return this;
	}
	/** 文件     下载的文件，多个文件请压缩   **/
	public CsContent cscFile(String cscFile){
		this.cscFile = cscFile;
		this.setSeted(F.cscFile);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsContent cscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
		return this;
	}
	/** 更新时间 [非空]       **/
	public CsContent cscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
		return this;
	}
	/** 浏览次数 [非空]       **/
	public CsContent cscViewS(Long cscViewS){
		this.cscViewS = cscViewS;
		this.setSeted(F.cscViewS);
		return this;
	}
	/** 评论次数 [非空]       **/
	public CsContent cscReplyS(Long cscReplyS){
		this.cscReplyS = cscReplyS;
		this.setSeted(F.cscReplyS);
		return this;
	}
	/** 收藏次数 [非空]       **/
	public CsContent cscCollectS(Long cscCollectS){
		this.cscCollectS = cscCollectS;
		this.setSeted(F.cscCollectS);
		return this;
	}
	/** 好评数 [非空]       **/
	public CsContent cscPraiseS(Long cscPraiseS){
		this.cscPraiseS = cscPraiseS;
		this.setSeted(F.cscPraiseS);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsContent cscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
		return this;
	}
	/** 活动盒子标识    **/
    public CsContent cscIsActivity(Short cscIsActivity){
        this.cscIsActivity = cscIsActivity;
        this.setSeted(F.cscIsActivity);
        return this;
    }
    /** 活动事件     **/
    public CsContent cscActivityEvent(String cscActivityEvent){
        this.cscActivityEvent = cscActivityEvent;
        this.setSeted(F.cscActivityEvent);
        return this;
    }
	
	
	//克隆对象
	public CsContent clone(){
		CsContent clone = new CsContent();
		clone.cscTitle=this.cscTitle;
		clone.cscRecommend=this.cscRecommend;
		clone.cscAddTime=this.cscAddTime;
		clone.cscUpdateTime=this.cscUpdateTime;
		clone.cscViewS=this.cscViewS;
		clone.cscReplyS=this.cscReplyS;
		clone.cscCollectS=this.cscCollectS;
		clone.cscPraiseS=this.cscPraiseS;
		clone.cscStatus=this.cscStatus;
		clone.cscIsActivity=this.cscIsActivity;
        clone.cscActivityEvent=this.cscActivityEvent;
		return clone;
	}
	
	
	/**
	 * 根据ID取静态内容
	 * @param id
	 * @return
	 */
	public static @api CsContent get(Long id){		
		return getCsContentById(id);
	}
	/**
	 * 获取所有静态内容
	 * @return
	 */
	public static @api List<CsContent> list(Map params,Integer size){
		return getCsContentList(params,size);
	}
	/**
	 * 获取静态内容分页
	 * @return
	 */
	public static @api Page<CsContent> page(int page,int size,Map params){
		return getCsContentPage(page, size , params);
	}
	/**
	 * 根据查询条件取静态内容
	 * @param params
	 * @return
	 */
	public static @api CsContent Get(Map params){
		return getCsContent(params);
	}
	/**
	 * 获取静态内容数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsContentCount(params);
	}
	/**
	 * 获取静态内容数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsContentEval(eval,params);
	}
	
	/**
	 * 根据ID取静态内容
	 * @param id
	 * @return
	 */
	public static @api CsContent getCsContentById(Long id){		
		CsContent csContent = (CsContent) $.GetRequest("CsContent$"+id);
		if(csContent!=null)
			return csContent;
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");		
		return csContentService.getCsContentById(id);
	}
	
	
	/**
	 * 根据ID取静态内容的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsContent.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsContent csContent = get(id);
		if(csContent!=null){
			String strValue = csContent.getCscTitle$();
			if(!"CscTitle".equals("CscTitle"))
				strValue+="("+csContent.getCscTitle$()+")";
			MemCache.setValue(CsContent.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscTitle$();
		if(!"CscTitle".equals("CscTitle"))
			keyValue+="("+this.getCscTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有静态内容
	 * @return
	 */
	public static @api List<CsContent> getCsContentList(Map params,Integer size){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		return csContentService.getCsContentList(params, size);
	}
	
	/**
	 * 获取静态内容分页
	 * @return
	 */
	public static @api Page<CsContent> getCsContentPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		return csContentService.getCsContentPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取静态内容
	 * @param params
	 * @return
	 */
	public static @api CsContent getCsContent(Map params){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		return csContentService.getCsContent(params);
	}
	
	/**
	 * 获取静态内容数
	 * @return
	 */
	public static @api Long getCsContentCount(Map params){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		return csContentService.getCsContentCount(params);
	}
		
		
	/**
	 * 获取静态内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsContentEval(String eval,Map params){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		return csContentService.getCsContentEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsContent(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		csContentService.updateCsContentByConfirm(set, where);
	}
	
	
	/**
	 * 保存静态内容对象
	 * @param params
	 * @return
	 */
	public CsContent save(){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		if(this.getCscId()!=null)
			csContentService.updateCsContent(this);
		else
			return csContentService.saveCsContent(this);
		return this;
	}
	
	
	/**
	 * 更新静态内容对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		csContentService.updateCsContent$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		if($.equals($.config("logic_delete"),"true"))
			csContentService.removeCsContentById(this.getCscId());
		else
			csContentService.deleteCsContentById(this.getCscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsContentService csContentService = $.GetSpringBean("csContentService");
		return csContentService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscId(){
		return this.cscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscId$(){
		String strValue="";
		 strValue=$.str(this.getCscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
	}
	/*******************************标题**********************************/	
	/**
	* 标题 [非空]    标题（32个字以内）  
	**/
	public String getCscTitle(){
		return this.cscTitle;
	}
	/**
	* 获取标题格式化(toString)
	**/
	public String getCscTitle$(){
		String strValue="";
		 strValue=$.str(this.getCscTitle());
	 	 return strValue;
	}
	/**
	* 标题 [非空]    标题（32个字以内）  
	**/
	public void setCscTitle(String cscTitle){
		this.cscTitle = cscTitle;
		this.setSeted(F.cscTitle);
	}
	/*******************************上级节点**********************************/	
	/**
	* 上级节点       
	**/
	public Long getCscParent(){
		return this.cscParent;
	}
	/**
	* 获取上级节点格式化(toString)
	**/
	public String getCscParent$(){
		String strValue="";
		 strValue=$.str(this.getCscParent());	
	 	 return strValue;
	}
	/**
	* 上级节点       
	**/
	public void setCscParent(Long cscParent){
		this.cscParent = cscParent;
		this.setSeted(F.cscParent);
	}
	/*******************************系统标识**********************************/	
	/**
	* 系统标识       
	**/
	public String getCscFlag(){
		return this.cscFlag;
	}
	/**
	* 获取系统标识格式化(toString)
	**/
	public String getCscFlag$(){
		String strValue="";
		 strValue=$.str(this.getCscFlag());
	 	 return strValue;
	}
	/**
	* 系统标识       
	**/
	public void setCscFlag(String cscFlag){
		this.cscFlag = cscFlag;
		this.setSeted(F.cscFlag);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级 [非空]    数值越大，排序越靠前  
	**/
	public Integer getCscRecommend(){
		return this.cscRecommend;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getCscRecommend$(){
		String strValue="";
		 strValue=$.str(this.getCscRecommend());
	 	 return strValue;
	}
	/**
	* 优先级 [非空]    数值越大，排序越靠前  
	**/
	public void setCscRecommend(Integer cscRecommend){
		this.cscRecommend = cscRecommend;
		this.setSeted(F.cscRecommend);
	}
	/*******************************略缩图**********************************/	
	/**
	* 略缩图    210:width 140:height  在分类列表中显示的略缩图  
	**/
	public String getCscThum(){
		return this.cscThum;
	}
	/**
	* 获取略缩图格式化(toString)
	**/
	public String getCscThum$(){
		String strValue="";
		 strValue=$.str(this.getCscThum());
	 	 return strValue;
	}
	/**
	* 略缩图    210:width 140:height  在分类列表中显示的略缩图  
	**/
	public void setCscThum(String cscThum){
		this.cscThum = cscThum;
		this.setSeted(F.cscThum);
	}
	/*******************************简介**********************************/	
	/**
	* 简介       
	**/
	public String getCscIntroduction(){
		return this.cscIntroduction;
	}
	/**
	* 获取简介格式化(toString)
	**/
	public String getCscIntroduction$(){
		String strValue="";
		 strValue=$.str(this.getCscIntroduction());
	 	 return strValue;
	}
	/**
	* 简介       
	**/
	public void setCscIntroduction(String cscIntroduction){
		this.cscIntroduction = cscIntroduction;
		this.setSeted(F.cscIntroduction);
	}
	/*******************************关键字**********************************/	
	/**
	* 关键字     百度搜索此关键字，多个关键字之前逗号分隔  
	**/
	public String getCscKeywords(){
		return this.cscKeywords;
	}
	/**
	* 获取关键字格式化(toString)
	**/
	public String getCscKeywords$(){
		String strValue="";
		 strValue=$.str(this.getCscKeywords());
	 	 return strValue;
	}
	/**
	* 关键字     百度搜索此关键字，多个关键字之前逗号分隔  
	**/
	public void setCscKeywords(String cscKeywords){
		this.cscKeywords = cscKeywords;
		this.setSeted(F.cscKeywords);
	}
	/*******************************预览图**********************************/	
	/**
	* 预览图     在详情显示的时候显示的一些效果图  
	**/
	public String getCscImages(){
		return this.cscImages;
	}
	/**
	* 获取预览图格式化(toString)
	**/
	public String getCscImages$(){
		String strValue="";
		 strValue=$.str(this.getCscImages());
	 	 return strValue;
	}
	/**
	* 预览图     在详情显示的时候显示的一些效果图  
	**/
	public void setCscImages(String cscImages){
		this.cscImages = cscImages;
		this.setSeted(F.cscImages);
	}
	/*******************************内容**********************************/	
	/**
	* 内容       
	**/
	public String getCscContent(){
		return this.cscContent;
	}
	/**
	* 获取内容格式化(toString)
	**/
	public String getCscContent$(){
		String strValue="";
		 strValue=$.str(this.getCscContent());
	 	 return strValue;
	}
	/**
	* 内容       
	**/
	public void setCscContent(String cscContent){
		this.cscContent = cscContent;
		this.setSeted(F.cscContent);
	}
	/*******************************文件**********************************/	
	/**
	* 文件     下载的文件，多个文件请压缩  
	**/
	public String getCscFile(){
		return this.cscFile;
	}
	/**
	* 获取文件格式化(toString)
	**/
	public String getCscFile$(){
		String strValue="";
		 strValue=$.str(this.getCscFile());
	 	 return strValue;
	}
	/**
	* 文件     下载的文件，多个文件请压缩  
	**/
	public void setCscFile(String cscFile){
		this.cscFile = cscFile;
		this.setSeted(F.cscFile);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscAddTime(){
		return this.cscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
	}
	/*******************************更新时间**********************************/	
	/**
	* 更新时间 [非空]      
	**/
	public Date getCscUpdateTime(){
		return this.cscUpdateTime;
	}
	/**
	* 获取更新时间格式化(toString)
	**/
	public String getCscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 更新时间 [非空]      
	**/
	public void setCscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
	}
	/*******************************浏览次数**********************************/	
	/**
	* 浏览次数 [非空]      
	**/
	public Long getCscViewS(){
		return this.cscViewS;
	}
	/**
	* 获取浏览次数格式化(toString)
	**/
	public String getCscViewS$(){
		String strValue="";
		 strValue=$.str(this.getCscViewS());
	 	 return strValue;
	}
	/**
	* 浏览次数 [非空]      
	**/
	public void setCscViewS(Long cscViewS){
		this.cscViewS = cscViewS;
		this.setSeted(F.cscViewS);
	}
	/*******************************评论次数**********************************/	
	/**
	* 评论次数 [非空]      
	**/
	public Long getCscReplyS(){
		return this.cscReplyS;
	}
	/**
	* 获取评论次数格式化(toString)
	**/
	public String getCscReplyS$(){
		String strValue="";
		 strValue=$.str(this.getCscReplyS());
	 	 return strValue;
	}
	/**
	* 评论次数 [非空]      
	**/
	public void setCscReplyS(Long cscReplyS){
		this.cscReplyS = cscReplyS;
		this.setSeted(F.cscReplyS);
	}
	/*******************************收藏次数**********************************/	
	/**
	* 收藏次数 [非空]      
	**/
	public Long getCscCollectS(){
		return this.cscCollectS;
	}
	/**
	* 获取收藏次数格式化(toString)
	**/
	public String getCscCollectS$(){
		String strValue="";
		 strValue=$.str(this.getCscCollectS());
	 	 return strValue;
	}
	/**
	* 收藏次数 [非空]      
	**/
	public void setCscCollectS(Long cscCollectS){
		this.cscCollectS = cscCollectS;
		this.setSeted(F.cscCollectS);
	}
	/*******************************好评数**********************************/	
	/**
	* 好评数 [非空]      
	**/
	public Long getCscPraiseS(){
		return this.cscPraiseS;
	}
	/**
	* 获取好评数格式化(toString)
	**/
	public String getCscPraiseS$(){
		String strValue="";
		 strValue=$.str(this.getCscPraiseS());
	 	 return strValue;
	}
	/**
	* 好评数 [非空]      
	**/
	public void setCscPraiseS(Long cscPraiseS){
		this.cscPraiseS = cscPraiseS;
		this.setSeted(F.cscPraiseS);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCscStatus(){
		return this.cscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCscStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	public Short getCscIsActivity() {
        return cscIsActivity;
    }

    public void setCscIsActivity(Short cscIsActivity) {
        this.cscIsActivity = cscIsActivity;
        this.setSeted(F.cscIsActivity);
    }
    
    public String getCscIsActivity$(){
        String strValue="";
         if($.equals($.str(this.getCscIsActivity()),"1"))
            strValue=$.str("是");        
         if($.equals($.str(this.getCscIsActivity()),"0"))
            strValue=$.str("否");        
         return strValue;
    }
    
    /***活动事件***/
    public String getCscActivityEvent() {
        return cscActivityEvent;
    }

    public void setCscActivityEvent(String cscActivityEvent) {
        this.cscActivityEvent = cscActivityEvent;
        this.setSeted(F.cscActivityEvent);
    }
    
    public String getCscActivityEvent$(){
        String strValue="";
         strValue=$.str(this.getCscActivityEvent());
         return strValue;
    }

    /**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsContent.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsContent.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsContent.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsContent.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsContent.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsContent.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsContent.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsContent.this);
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
		public M cscId(Object cscId){this.put("cscId", cscId);return this;};
	 	/** and csc_id is null */
 		public M cscIdNull(){if(this.get("cscIdNot")==null)this.put("cscIdNot", "");this.put("cscId", null);return this;};
 		/** not .... */
 		public M cscIdNot(){this.put("cscIdNot", "not");return this;};
		/** 标题 [非空]    标题（32个字以内）   **/
		public M cscTitle(Object cscTitle){this.put("cscTitle", cscTitle);return this;};
	 	/** and csc_title is null */
 		public M cscTitleNull(){if(this.get("cscTitleNot")==null)this.put("cscTitleNot", "");this.put("cscTitle", null);return this;};
 		/** not .... */
 		public M cscTitleNot(){this.put("cscTitleNot", "not");return this;};
		/** 上级节点        **/
		public M cscParent(Object cscParent){this.put("cscParent", cscParent);return this;};
	 	/** and csc_parent is null */
 		public M cscParentNull(){if(this.get("cscParentNot")==null)this.put("cscParentNot", "");this.put("cscParent", null);return this;};
 		/** not .... */
 		public M cscParentNot(){this.put("cscParentNot", "not");return this;};
		/** 系统标识        **/
		public M cscFlag(Object cscFlag){this.put("cscFlag", cscFlag);return this;};
	 	/** and csc_flag is null */
 		public M cscFlagNull(){if(this.get("cscFlagNot")==null)this.put("cscFlagNot", "");this.put("cscFlag", null);return this;};
 		/** not .... */
 		public M cscFlagNot(){this.put("cscFlagNot", "not");return this;};
		/** 优先级 [非空]    数值越大，排序越靠前   **/
		public M cscRecommend(Object cscRecommend){this.put("cscRecommend", cscRecommend);return this;};
	 	/** and csc_order is null */
 		public M cscRecommendNull(){if(this.get("cscRecommendNot")==null)this.put("cscRecommendNot", "");this.put("cscRecommend", null);return this;};
 		/** not .... */
 		public M cscRecommendNot(){this.put("cscRecommendNot", "not");return this;};
		/** and csc_order >= ? */
		public M cscRecommendMin(Object min){this.put("cscRecommendMin", min);return this;};
		/** and csc_order <= ? */
		public M cscRecommendMax(Object max){this.put("cscRecommendMax", max);return this;};
		/** 略缩图    210:width 140:height  在分类列表中显示的略缩图   **/
		public M cscThum(Object cscThum){this.put("cscThum", cscThum);return this;};
	 	/** and csc_thum is null */
 		public M cscThumNull(){if(this.get("cscThumNot")==null)this.put("cscThumNot", "");this.put("cscThum", null);return this;};
 		/** not .... */
 		public M cscThumNot(){this.put("cscThumNot", "not");return this;};
		/** 简介        **/
		public M cscIntroduction(Object cscIntroduction){this.put("cscIntroduction", cscIntroduction);return this;};
	 	/** and csc_introduction is null */
 		public M cscIntroductionNull(){if(this.get("cscIntroductionNot")==null)this.put("cscIntroductionNot", "");this.put("cscIntroduction", null);return this;};
 		/** not .... */
 		public M cscIntroductionNot(){this.put("cscIntroductionNot", "not");return this;};
		/** 关键字     百度搜索此关键字，多个关键字之前逗号分隔   **/
		public M cscKeywords(Object cscKeywords){this.put("cscKeywords", cscKeywords);return this;};
	 	/** and csc_keywords is null */
 		public M cscKeywordsNull(){if(this.get("cscKeywordsNot")==null)this.put("cscKeywordsNot", "");this.put("cscKeywords", null);return this;};
 		/** not .... */
 		public M cscKeywordsNot(){this.put("cscKeywordsNot", "not");return this;};
		/** 预览图     在详情显示的时候显示的一些效果图   **/
		public M cscImages(Object cscImages){this.put("cscImages", cscImages);return this;};
	 	/** and csc_images is null */
 		public M cscImagesNull(){if(this.get("cscImagesNot")==null)this.put("cscImagesNot", "");this.put("cscImages", null);return this;};
 		/** not .... */
 		public M cscImagesNot(){this.put("cscImagesNot", "not");return this;};
		/** 内容        **/
		public M cscContent(Object cscContent){this.put("cscContent", cscContent);return this;};
	 	/** and csc_content is null */
 		public M cscContentNull(){if(this.get("cscContentNot")==null)this.put("cscContentNot", "");this.put("cscContent", null);return this;};
 		/** not .... */
 		public M cscContentNot(){this.put("cscContentNot", "not");return this;};
		/** 文件     下载的文件，多个文件请压缩   **/
		public M cscFile(Object cscFile){this.put("cscFile", cscFile);return this;};
	 	/** and csc_file is null */
 		public M cscFileNull(){if(this.get("cscFileNot")==null)this.put("cscFileNot", "");this.put("cscFile", null);return this;};
 		/** not .... */
 		public M cscFileNot(){this.put("cscFileNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M cscAddTime(Object cscAddTime){this.put("cscAddTime", cscAddTime);return this;};
	 	/** and csc_add_time is null */
 		public M cscAddTimeNull(){if(this.get("cscAddTimeNot")==null)this.put("cscAddTimeNot", "");this.put("cscAddTime", null);return this;};
 		/** not .... */
 		public M cscAddTimeNot(){this.put("cscAddTimeNot", "not");return this;};
 		/** and csc_add_time >= ? */
 		public M cscAddTimeStart(Object start){this.put("cscAddTimeStart", start);return this;};			
 		/** and csc_add_time <= ? */
 		public M cscAddTimeEnd(Object end){this.put("cscAddTimeEnd", end);return this;};
		/** 更新时间 [非空]       **/
		public M cscUpdateTime(Object cscUpdateTime){this.put("cscUpdateTime", cscUpdateTime);return this;};
	 	/** and csc_update_time is null */
 		public M cscUpdateTimeNull(){if(this.get("cscUpdateTimeNot")==null)this.put("cscUpdateTimeNot", "");this.put("cscUpdateTime", null);return this;};
 		/** not .... */
 		public M cscUpdateTimeNot(){this.put("cscUpdateTimeNot", "not");return this;};
 		/** and csc_update_time >= ? */
 		public M cscUpdateTimeStart(Object start){this.put("cscUpdateTimeStart", start);return this;};			
 		/** and csc_update_time <= ? */
 		public M cscUpdateTimeEnd(Object end){this.put("cscUpdateTimeEnd", end);return this;};
		/** 浏览次数 [非空]       **/
		public M cscViewS(Object cscViewS){this.put("cscViewS", cscViewS);return this;};
	 	/** and csc_view_s is null */
 		public M cscViewSNull(){if(this.get("cscViewSNot")==null)this.put("cscViewSNot", "");this.put("cscViewS", null);return this;};
 		/** not .... */
 		public M cscViewSNot(){this.put("cscViewSNot", "not");return this;};
		/** and csc_view_s >= ? */
		public M cscViewSMin(Object min){this.put("cscViewSMin", min);return this;};
		/** and csc_view_s <= ? */
		public M cscViewSMax(Object max){this.put("cscViewSMax", max);return this;};
		/** 评论次数 [非空]       **/
		public M cscReplyS(Object cscReplyS){this.put("cscReplyS", cscReplyS);return this;};
	 	/** and csc_reply_s is null */
 		public M cscReplySNull(){if(this.get("cscReplySNot")==null)this.put("cscReplySNot", "");this.put("cscReplyS", null);return this;};
 		/** not .... */
 		public M cscReplySNot(){this.put("cscReplySNot", "not");return this;};
		/** and csc_reply_s >= ? */
		public M cscReplySMin(Object min){this.put("cscReplySMin", min);return this;};
		/** and csc_reply_s <= ? */
		public M cscReplySMax(Object max){this.put("cscReplySMax", max);return this;};
		/** 收藏次数 [非空]       **/
		public M cscCollectS(Object cscCollectS){this.put("cscCollectS", cscCollectS);return this;};
	 	/** and csc_collect_s is null */
 		public M cscCollectSNull(){if(this.get("cscCollectSNot")==null)this.put("cscCollectSNot", "");this.put("cscCollectS", null);return this;};
 		/** not .... */
 		public M cscCollectSNot(){this.put("cscCollectSNot", "not");return this;};
		/** and csc_collect_s >= ? */
		public M cscCollectSMin(Object min){this.put("cscCollectSMin", min);return this;};
		/** and csc_collect_s <= ? */
		public M cscCollectSMax(Object max){this.put("cscCollectSMax", max);return this;};
		/** 好评数 [非空]       **/
		public M cscPraiseS(Object cscPraiseS){this.put("cscPraiseS", cscPraiseS);return this;};
	 	/** and csc_praise_s is null */
 		public M cscPraiseSNull(){if(this.get("cscPraiseSNot")==null)this.put("cscPraiseSNot", "");this.put("cscPraiseS", null);return this;};
 		/** not .... */
 		public M cscPraiseSNot(){this.put("cscPraiseSNot", "not");return this;};
		/** and csc_praise_s >= ? */
		public M cscPraiseSMin(Object min){this.put("cscPraiseSMin", min);return this;};
		/** and csc_praise_s <= ? */
		public M cscPraiseSMax(Object max){this.put("cscPraiseSMax", max);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cscStatus(Object cscStatus){this.put("cscStatus", cscStatus);return this;};
	 	/** and csc_status is null */
 		public M cscStatusNull(){if(this.get("cscStatusNot")==null)this.put("cscStatusNot", "");this.put("cscStatus", null);return this;};
 		/** not .... */
 		public M cscStatusNot(){this.put("cscStatusNot", "not");return this;};
 		
 		/** 活动盒子标识    **/
        public M cscIsActivity(Object cscIsActivity){this.put("cscIsActivity", cscIsActivity);return this;};
        /** and csc_is_activity is null */
        public M cscIsActivityNull(){if(this.get("cscIsActivityNot")==null)this.put("cscIsActivityNot", "");this.put("cscIsActivity", null);return this;};
        /** not .... */
        public M cscIsActivityNot(){this.put("cscIsActivityNot", "not");return this;};
        
        /** 活动事件    **/
        public M cscActivityEvent(Object cscActivityEvent){this.put("cscActivityEvent", cscActivityEvent);return this;};
        /** and csc_activity_name is null */
        public M cscActivityEventNull(){if(this.get("cscActivityEventNot")==null)this.put("cscActivityEventNot", "");this.put("cscActivityEvent", null);return this;};
        /** not .... */
        public M cscActivityEventNot(){this.put("cscActivityEventNot", "not");return this;};
        
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有静态内容 **/
		public @api List<CsContent> list(Integer size){
			return getCsContentList(this,size);
		}
		/** 获取静态内容分页 **/
		public @api Page<CsContent> page(int page,int size){
			return getCsContentPage(page, size , this);
		}
		/** 根据查询条件取静态内容 **/
		public @api CsContent get(){
			return getCsContent(this);
		}
		/** 获取静态内容数 **/
		public @api Long count(){
			return getCsContentCount(this);
		}
		/** 获取静态内容表达式 **/
		public @api <T> T eval(String strEval){
			return getCsContentEval(strEval,this);
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
			updateCsContent(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		/** 标题 [非空]    标题（32个字以内）   **/
		public final static @type(String.class) @like String cscTitle="cscTitle";
		/** 上级节点        **/
		public final static @type(Long.class)  String cscParent="cscParent";
		/** 系统标识        **/
		public final static @type(String.class) @like String cscFlag="cscFlag";
		/** 优先级 [非空]    数值越大，排序越靠前   **/
		public final static @type(Integer.class)  String cscRecommend="cscRecommend";
		/** and csc_order >= ? */
		public final static @type(Integer.class) String cscRecommendMin="cscRecommendMin";
		/** and csc_order <= ? */
		public final static @type(Integer.class) String cscRecommendMax="cscRecommendMax";
		/** 略缩图    210:width 140:height  在分类列表中显示的略缩图   **/
		public final static @type(String.class)  String cscThum="cscThum";
		/** 简介        **/
		public final static @type(String.class) @like String cscIntroduction="cscIntroduction";
		/** 关键字     百度搜索此关键字，多个关键字之前逗号分隔   **/
		public final static @type(String.class) @like String cscKeywords="cscKeywords";
		/** 预览图     在详情显示的时候显示的一些效果图   **/
		public final static @type(String.class)  String cscImages="cscImages";
		/** 内容        **/
		public final static @type(String.class) @like String cscContent="cscContent";
		/** 文件     下载的文件，多个文件请压缩   **/
		public final static @type(String.class)  String cscFile="cscFile";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscAddTime="cscAddTime";
	 	/** and csc_add_time >= ? */
 		public final static @type(Date.class) String cscAddTimeStart="cscAddTimeStart";
 		/** and csc_add_time <= ? */
 		public final static @type(Date.class) String cscAddTimeEnd="cscAddTimeEnd";
		/** 更新时间 [非空]       **/
		public final static @type(Date.class)  String cscUpdateTime="cscUpdateTime";
	 	/** and csc_update_time >= ? */
 		public final static @type(Date.class) String cscUpdateTimeStart="cscUpdateTimeStart";
 		/** and csc_update_time <= ? */
 		public final static @type(Date.class) String cscUpdateTimeEnd="cscUpdateTimeEnd";
		/** 浏览次数 [非空]       **/
		public final static @type(Long.class)  String cscViewS="cscViewS";
		/** and csc_view_s >= ? */
		public final static @type(Long.class) String cscViewSMin="cscViewSMin";
		/** and csc_view_s <= ? */
		public final static @type(Long.class) String cscViewSMax="cscViewSMax";
		/** 评论次数 [非空]       **/
		public final static @type(Long.class)  String cscReplyS="cscReplyS";
		/** and csc_reply_s >= ? */
		public final static @type(Long.class) String cscReplySMin="cscReplySMin";
		/** and csc_reply_s <= ? */
		public final static @type(Long.class) String cscReplySMax="cscReplySMax";
		/** 收藏次数 [非空]       **/
		public final static @type(Long.class)  String cscCollectS="cscCollectS";
		/** and csc_collect_s >= ? */
		public final static @type(Long.class) String cscCollectSMin="cscCollectSMin";
		/** and csc_collect_s <= ? */
		public final static @type(Long.class) String cscCollectSMax="cscCollectSMax";
		/** 好评数 [非空]       **/
		public final static @type(Long.class)  String cscPraiseS="cscPraiseS";
		/** and csc_praise_s >= ? */
		public final static @type(Long.class) String cscPraiseSMin="cscPraiseSMin";
		/** and csc_praise_s <= ? */
		public final static @type(Long.class) String cscPraiseSMax="cscPraiseSMax";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cscStatus="cscStatus";
		/***活动盒子标识 1:是 0:否 ***/
		public final static @type(Short.class)  String cscIsActivity="cscIsActivity";
		/***活动事件 ***/
        public final static @type(String.class)  String cscActivityEvent="cscActivityEvent"; 
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscId="csc_id";
		/** 标题 [非空]    标题（32个字以内）   **/
		public final static String cscTitle="csc_title";
		/** 上级节点        **/
		public final static String cscParent="csc_parent";
		/** 系统标识        **/
		public final static String cscFlag="csc_flag";
		/** 优先级 [非空]    数值越大，排序越靠前   **/
		public final static String cscRecommend="csc_order";
		/** 略缩图    210:width 140:height  在分类列表中显示的略缩图   **/
		public final static String cscThum="csc_thum";
		/** 简介        **/
		public final static String cscIntroduction="csc_introduction";
		/** 关键字     百度搜索此关键字，多个关键字之前逗号分隔   **/
		public final static String cscKeywords="csc_keywords";
		/** 预览图     在详情显示的时候显示的一些效果图   **/
		public final static String cscImages="csc_images";
		/** 内容        **/
		public final static String cscContent="csc_content";
		/** 文件     下载的文件，多个文件请压缩   **/
		public final static String cscFile="csc_file";
		/** 添加时间 [非空]       **/
		public final static String cscAddTime="csc_add_time";
		/** 更新时间 [非空]       **/
		public final static String cscUpdateTime="csc_update_time";
		/** 浏览次数 [非空]       **/
		public final static String cscViewS="csc_view_s";
		/** 评论次数 [非空]       **/
		public final static String cscReplyS="csc_reply_s";
		/** 收藏次数 [非空]       **/
		public final static String cscCollectS="csc_collect_s";
		/** 好评数 [非空]       **/
		public final static String cscPraiseS="csc_praise_s";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cscStatus="csc_status";
		/** 活动盒子标识   1:是 0:否     **/
		public final static String cscIsActivity="csc_is_activity";
		/***活动事件 ***/
        public final static @type(String.class)  String cscActivityEvent="csc_activity_event";
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
				$.Set(name,CsContent.getCsContent(params));
			else
				$.Set(name,CsContent.getCsContentList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取静态内容数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsContent) $.GetRequest("CsContent$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsContent.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsContent.getCsContent(params);
			else
				value = CsContent.getCsContentList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsContent.Get($.add(CsContent.F.cscId,param));
		else if(!$.empty(param.toString()))
			value = CsContent.get(Long.valueOf(param.toString()));
		$.SetRequest("CsContent$"+param.hashCode(), value);
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
	public void mergeSet(CsContent old){
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