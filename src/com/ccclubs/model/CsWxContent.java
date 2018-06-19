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

@namespace("configurator/wx/wxcontent")
public @caption("微信素材") @table("cs_wx_content") class CsWxContent implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("cswc_id")   @primary  @note("  ") Long cswcId;// 主键 非空     
	private @caption("标题") @column("cswc_title")    @note("  ") String cswcTitle;// 非空     
	private @caption("内容") @column("cswc_contnet")    @note("  ") String cswcContnet;//     
	private @caption("缩略图") @column("cswc_thumb")    @note(" 80:width 80:height  ") String cswcThumb;// 80:width 80:height     
	private @caption("图片") @column("cswc_image")    @note(" 640:width 320:height  ") String cswcImage;// 640:width 320:height     
	private @caption("链接地址") @column("cswc_link")    @note("  ") String cswcLink;//     
	private @caption("所属指令") @column("cswc_order")    @relate("$cswcOrder") @RelateClass(CsWxOrder.class)  @note("  ") Long cswcOrder;// 非空     
 	private CsWxOrder $cswcOrder;//关联对象[微信指令]
	private @caption("添加时间") @column("cswc_add_time")    @note("  ") Date cswcAddTime;// 非空     
	
	//默认构造函数
	public CsWxContent(){
	
	}
	
	//主键构造函数
	public CsWxContent(Long id){
		this.cswcId = id;
	}
	
	/**所有字段构造函数 CsWxContent(cswcTitle,cswcContnet,cswcThumb,cswcImage,cswcLink,cswcOrder,cswcAddTime)
	 CsWxContent(
	 	$.getString("cswcTitle")//标题 [非空]
	 	,$.getString("cswcContnet")//内容
	 	,$.getString("cswcThumb")//缩略图
	 	,$.getString("cswcImage")//图片
	 	,$.getString("cswcLink")//链接地址
	 	,$.getLong("cswcOrder")//所属指令 [非空]
	 	,$.getDate("cswcAddTime")//添加时间 [非空]
	 )
	**/
	public CsWxContent(String cswcTitle,String cswcContnet,String cswcThumb,String cswcImage,String cswcLink,Long cswcOrder,Date cswcAddTime){
		this.cswcTitle=cswcTitle;
		this.cswcContnet=cswcContnet;
		this.cswcThumb=cswcThumb;
		this.cswcImage=cswcImage;
		this.cswcLink=cswcLink;
		this.cswcOrder=cswcOrder;
		this.cswcAddTime=cswcAddTime;
	}
	
	//设置非空字段
	public CsWxContent setNotNull(String cswcTitle,Long cswcOrder,Date cswcAddTime){
		this.cswcTitle=cswcTitle;
		this.cswcOrder=cswcOrder;
		this.cswcAddTime=cswcAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsWxContent cswcId(Long cswcId){
		this.cswcId = cswcId;
		this.setSeted(F.cswcId);
		return this;
	}
	/** 标题 [非空]       **/
	public CsWxContent cswcTitle(String cswcTitle){
		this.cswcTitle = cswcTitle;
		this.setSeted(F.cswcTitle);
		return this;
	}
	/** 内容        **/
	public CsWxContent cswcContnet(String cswcContnet){
		this.cswcContnet = cswcContnet;
		this.setSeted(F.cswcContnet);
		return this;
	}
	/** 缩略图    80:width 80:height     **/
	public CsWxContent cswcThumb(String cswcThumb){
		this.cswcThumb = cswcThumb;
		this.setSeted(F.cswcThumb);
		return this;
	}
	/** 图片    640:width 320:height     **/
	public CsWxContent cswcImage(String cswcImage){
		this.cswcImage = cswcImage;
		this.setSeted(F.cswcImage);
		return this;
	}
	/** 链接地址        **/
	public CsWxContent cswcLink(String cswcLink){
		this.cswcLink = cswcLink;
		this.setSeted(F.cswcLink);
		return this;
	}
	/** 所属指令 [非空] [CsWxOrder]      **/
	public CsWxContent cswcOrder(Long cswcOrder){
		this.cswcOrder = cswcOrder;
		this.setSeted(F.cswcOrder);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsWxContent cswcAddTime(Date cswcAddTime){
		this.cswcAddTime = cswcAddTime;
		this.setSeted(F.cswcAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsWxContent clone(){
		CsWxContent clone = new CsWxContent();
		clone.cswcTitle=this.cswcTitle;
		clone.cswcOrder=this.cswcOrder;
		clone.cswcAddTime=this.cswcAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取微信素材
	 * @param id
	 * @return
	 */
	public static @api CsWxContent get(Long id){		
		return getCsWxContentById(id);
	}
	/**
	 * 获取所有微信素材
	 * @return
	 */
	public static @api List<CsWxContent> list(Map params,Integer size){
		return getCsWxContentList(params,size);
	}
	/**
	 * 获取微信素材分页
	 * @return
	 */
	public static @api Page<CsWxContent> page(int page,int size,Map params){
		return getCsWxContentPage(page, size , params);
	}
	/**
	 * 根据查询条件取微信素材
	 * @param params
	 * @return
	 */
	public static @api CsWxContent Get(Map params){
		return getCsWxContent(params);
	}
	/**
	 * 获取微信素材数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsWxContentCount(params);
	}
	/**
	 * 获取微信素材数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsWxContentEval(eval,params);
	}
	
	/**
	 * 根据ID取微信素材
	 * @param id
	 * @return
	 */
	public static @api CsWxContent getCsWxContentById(Long id){		
		CsWxContent csWxContent = (CsWxContent) $.GetRequest("CsWxContent$"+id);
		if(csWxContent!=null)
			return csWxContent;
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");		
		return csWxContentService.getCsWxContentById(id);
	}
	
	
	/**
	 * 根据ID取微信素材的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsWxContent.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsWxContent csWxContent = get(id);
		if(csWxContent!=null){
			String strValue = csWxContent.getCswcTitle$();
			if(!"CswcTitle".equals("CswcTitle"))
				strValue+="("+csWxContent.getCswcTitle$()+")";
			MemCache.setValue(CsWxContent.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCswcTitle$();
		if(!"CswcTitle".equals("CswcTitle"))
			keyValue+="("+this.getCswcTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有微信素材
	 * @return
	 */
	public static @api List<CsWxContent> getCsWxContentList(Map params,Integer size){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		return csWxContentService.getCsWxContentList(params, size);
	}
	
	/**
	 * 获取微信素材分页
	 * @return
	 */
	public static @api Page<CsWxContent> getCsWxContentPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		return csWxContentService.getCsWxContentPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取微信素材
	 * @param params
	 * @return
	 */
	public static @api CsWxContent getCsWxContent(Map params){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		return csWxContentService.getCsWxContent(params);
	}
	
	/**
	 * 获取微信素材数
	 * @return
	 */
	public static @api Long getCsWxContentCount(Map params){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		return csWxContentService.getCsWxContentCount(params);
	}
		
		
	/**
	 * 获取微信素材自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsWxContentEval(String eval,Map params){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		return csWxContentService.getCsWxContentEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsWxContent(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		csWxContentService.updateCsWxContentByConfirm(set, where);
	}
	
	
	/**
	 * 保存微信素材对象
	 * @param params
	 * @return
	 */
	public CsWxContent save(){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		if(this.getCswcId()!=null)
			csWxContentService.updateCsWxContent(this);
		else
			return csWxContentService.saveCsWxContent(this);
		return this;
	}
	
	
	/**
	 * 更新微信素材对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		csWxContentService.updateCsWxContent$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
			csWxContentService.deleteCsWxContentById(this.getCswcId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		return csWxContentService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCswcId(){
		return this.cswcId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCswcId$(){
		String strValue="";
		 strValue=$.str(this.getCswcId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCswcId(Long cswcId){
		this.cswcId = cswcId;
		this.setSeted(F.cswcId);
	}
	/*******************************标题**********************************/	
	/**
	* 标题 [非空]      
	**/
	public String getCswcTitle(){
		return this.cswcTitle;
	}
	/**
	* 获取标题格式化(toString)
	**/
	public String getCswcTitle$(){
		String strValue="";
		 strValue=$.str(this.getCswcTitle());
	 	 return strValue;
	}
	/**
	* 标题 [非空]      
	**/
	public void setCswcTitle(String cswcTitle){
		this.cswcTitle = cswcTitle;
		this.setSeted(F.cswcTitle);
	}
	/*******************************内容**********************************/	
	/**
	* 内容       
	**/
	public String getCswcContnet(){
		return this.cswcContnet;
	}
	/**
	* 获取内容格式化(toString)
	**/
	public String getCswcContnet$(){
		String strValue="";
		 strValue=$.str(this.getCswcContnet());
	 	 return strValue;
	}
	/**
	* 内容       
	**/
	public void setCswcContnet(String cswcContnet){
		this.cswcContnet = cswcContnet;
		this.setSeted(F.cswcContnet);
	}
	/*******************************缩略图**********************************/	
	/**
	* 缩略图    80:width 80:height    
	**/
	public String getCswcThumb(){
		return this.cswcThumb;
	}
	/**
	* 获取缩略图格式化(toString)
	**/
	public String getCswcThumb$(){
		String strValue="";
		 strValue=$.str(this.getCswcThumb());
	 	 return strValue;
	}
	/**
	* 缩略图    80:width 80:height    
	**/
	public void setCswcThumb(String cswcThumb){
		this.cswcThumb = cswcThumb;
		this.setSeted(F.cswcThumb);
	}
	/*******************************图片**********************************/	
	/**
	* 图片    640:width 320:height    
	**/
	public String getCswcImage(){
		return this.cswcImage;
	}
	/**
	* 获取图片格式化(toString)
	**/
	public String getCswcImage$(){
		String strValue="";
		 strValue=$.str(this.getCswcImage());
	 	 return strValue;
	}
	/**
	* 图片    640:width 320:height    
	**/
	public void setCswcImage(String cswcImage){
		this.cswcImage = cswcImage;
		this.setSeted(F.cswcImage);
	}
	/*******************************链接地址**********************************/	
	/**
	* 链接地址       
	**/
	public String getCswcLink(){
		return this.cswcLink;
	}
	/**
	* 获取链接地址格式化(toString)
	**/
	public String getCswcLink$(){
		String strValue="";
		 strValue=$.str(this.getCswcLink());
	 	 return strValue;
	}
	/**
	* 链接地址       
	**/
	public void setCswcLink(String cswcLink){
		this.cswcLink = cswcLink;
		this.setSeted(F.cswcLink);
	}
	/*******************************所属指令**********************************/	
	/**
	* 所属指令 [非空] [CsWxOrder]     
	**/
	public Long getCswcOrder(){
		return this.cswcOrder;
	}
	/**
	* 获取所属指令格式化(toString)
	**/
	public String getCswcOrder$(){
		String strValue="";
		if(this.getCswcOrder()!=null){
				strValue+=$.str(CsWxOrder.getKeyValue(this.getCswcOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属指令 [非空] [CsWxOrder]     
	**/
	public void setCswcOrder(Long cswcOrder){
		this.cswcOrder = cswcOrder;
		this.setSeted(F.cswcOrder);
	}
	/**
	* 获取关联对象[微信指令]
	**/	 			
 	public CsWxOrder get$cswcOrder(){
 		com.ccclubs.model.CsWxOrder csWxOrder = (com.ccclubs.model.CsWxOrder) $.GetRequest("CsWxOrder$"+this.getCswcOrder());
 		if(csWxOrder!=null)
			return csWxOrder;
		if(this.getCswcOrder()!=null){
 			csWxOrder = CsWxOrder.get(this.getCswcOrder());
 		}
 		$.SetRequest("CsWxOrder$"+this.getCswcOrder(), csWxOrder);
	 	return csWxOrder;
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCswcAddTime(){
		return this.cswcAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCswcAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCswcAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCswcAddTime(Date cswcAddTime){
		this.cswcAddTime = cswcAddTime;
		this.setSeted(F.cswcAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsWxContent.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxContent.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxContent.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsWxContent.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsWxContent.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxContent.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxContent.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsWxContent.this);
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
		public M cswcId(Object cswcId){this.put("cswcId", cswcId);return this;};
	 	/** and cswc_id is null */
 		public M cswcIdNull(){if(this.get("cswcIdNot")==null)this.put("cswcIdNot", "");this.put("cswcId", null);return this;};
 		/** not .... */
 		public M cswcIdNot(){this.put("cswcIdNot", "not");return this;};
		/** 标题 [非空]       **/
		public M cswcTitle(Object cswcTitle){this.put("cswcTitle", cswcTitle);return this;};
	 	/** and cswc_title is null */
 		public M cswcTitleNull(){if(this.get("cswcTitleNot")==null)this.put("cswcTitleNot", "");this.put("cswcTitle", null);return this;};
 		/** not .... */
 		public M cswcTitleNot(){this.put("cswcTitleNot", "not");return this;};
		/** 内容        **/
		public M cswcContnet(Object cswcContnet){this.put("cswcContnet", cswcContnet);return this;};
	 	/** and cswc_contnet is null */
 		public M cswcContnetNull(){if(this.get("cswcContnetNot")==null)this.put("cswcContnetNot", "");this.put("cswcContnet", null);return this;};
 		/** not .... */
 		public M cswcContnetNot(){this.put("cswcContnetNot", "not");return this;};
		/** 缩略图    80:width 80:height     **/
		public M cswcThumb(Object cswcThumb){this.put("cswcThumb", cswcThumb);return this;};
	 	/** and cswc_thumb is null */
 		public M cswcThumbNull(){if(this.get("cswcThumbNot")==null)this.put("cswcThumbNot", "");this.put("cswcThumb", null);return this;};
 		/** not .... */
 		public M cswcThumbNot(){this.put("cswcThumbNot", "not");return this;};
		/** 图片    640:width 320:height     **/
		public M cswcImage(Object cswcImage){this.put("cswcImage", cswcImage);return this;};
	 	/** and cswc_image is null */
 		public M cswcImageNull(){if(this.get("cswcImageNot")==null)this.put("cswcImageNot", "");this.put("cswcImage", null);return this;};
 		/** not .... */
 		public M cswcImageNot(){this.put("cswcImageNot", "not");return this;};
		/** 链接地址        **/
		public M cswcLink(Object cswcLink){this.put("cswcLink", cswcLink);return this;};
	 	/** and cswc_link is null */
 		public M cswcLinkNull(){if(this.get("cswcLinkNot")==null)this.put("cswcLinkNot", "");this.put("cswcLink", null);return this;};
 		/** not .... */
 		public M cswcLinkNot(){this.put("cswcLinkNot", "not");return this;};
		/** 所属指令 [非空] [CsWxOrder]      **/
		public M cswcOrder(Object cswcOrder){this.put("cswcOrder", cswcOrder);return this;};
	 	/** and cswc_order is null */
 		public M cswcOrderNull(){if(this.get("cswcOrderNot")==null)this.put("cswcOrderNot", "");this.put("cswcOrder", null);return this;};
 		/** not .... */
 		public M cswcOrderNot(){this.put("cswcOrderNot", "not");return this;};
		public M cswcOrder$on(CsWxOrder.M value){
			this.put("CsWxOrder", value);
			this.put("cswcOrder$on", value);
			return this;
		};	
		/** 添加时间 [非空]       **/
		public M cswcAddTime(Object cswcAddTime){this.put("cswcAddTime", cswcAddTime);return this;};
	 	/** and cswc_add_time is null */
 		public M cswcAddTimeNull(){if(this.get("cswcAddTimeNot")==null)this.put("cswcAddTimeNot", "");this.put("cswcAddTime", null);return this;};
 		/** not .... */
 		public M cswcAddTimeNot(){this.put("cswcAddTimeNot", "not");return this;};
 		/** and cswc_add_time >= ? */
 		public M cswcAddTimeStart(Object start){this.put("cswcAddTimeStart", start);return this;};			
 		/** and cswc_add_time <= ? */
 		public M cswcAddTimeEnd(Object end){this.put("cswcAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有微信素材 **/
		public @api List<CsWxContent> list(Integer size){
			return getCsWxContentList(this,size);
		}
		/** 获取微信素材分页 **/
		public @api Page<CsWxContent> page(int page,int size){
			return getCsWxContentPage(page, size , this);
		}
		/** 根据查询条件取微信素材 **/
		public @api CsWxContent get(){
			return getCsWxContent(this);
		}
		/** 获取微信素材数 **/
		public @api Long count(){
			return getCsWxContentCount(this);
		}
		/** 获取微信素材表达式 **/
		public @api <T> T eval(String strEval){
			return getCsWxContentEval(strEval,this);
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
			updateCsWxContent(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cswcId="cswcId";
		/** 标题 [非空]       **/
		public final static @type(String.class) @like String cswcTitle="cswcTitle";
		/** 内容        **/
		public final static @type(String.class) @like String cswcContnet="cswcContnet";
		/** 缩略图    80:width 80:height     **/
		public final static @type(String.class)  String cswcThumb="cswcThumb";
		/** 图片    640:width 320:height     **/
		public final static @type(String.class)  String cswcImage="cswcImage";
		/** 链接地址        **/
		public final static @type(String.class) @like String cswcLink="cswcLink";
		/** 所属指令 [非空] [CsWxOrder]      **/
		public final static @type(Long.class)  String cswcOrder="cswcOrder";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cswcAddTime="cswcAddTime";
	 	/** and cswc_add_time >= ? */
 		public final static @type(Date.class) String cswcAddTimeStart="cswcAddTimeStart";
 		/** and cswc_add_time <= ? */
 		public final static @type(Date.class) String cswcAddTimeEnd="cswcAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cswcId="cswc_id";
		/** 标题 [非空]       **/
		public final static String cswcTitle="cswc_title";
		/** 内容        **/
		public final static String cswcContnet="cswc_contnet";
		/** 缩略图    80:width 80:height     **/
		public final static String cswcThumb="cswc_thumb";
		/** 图片    640:width 320:height     **/
		public final static String cswcImage="cswc_image";
		/** 链接地址        **/
		public final static String cswcLink="cswc_link";
		/** 所属指令 [非空] [CsWxOrder]      **/
		public final static String cswcOrder="cswc_order";
		/** 添加时间 [非空]       **/
		public final static String cswcAddTime="cswc_add_time";
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
				$.Set(name,CsWxContent.getCsWxContent(params));
			else
				$.Set(name,CsWxContent.getCsWxContentList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取微信素材数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsWxContent) $.GetRequest("CsWxContent$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsWxContent.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsWxContent.getCsWxContent(params);
			else
				value = CsWxContent.getCsWxContentList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsWxContent.Get($.add(CsWxContent.F.cswcId,param));
		else if(!$.empty(param.toString()))
			value = CsWxContent.get(Long.valueOf(param.toString()));
		$.SetRequest("CsWxContent$"+param.hashCode(), value);
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
	public void mergeSet(CsWxContent old){
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