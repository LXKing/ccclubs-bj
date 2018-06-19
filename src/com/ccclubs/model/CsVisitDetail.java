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

@namespace("extension/detail")
public @caption("访问详情") @table("cs_visit_detail") class CsVisitDetail implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csvd_id")   @primary  @note("  ") Long csvdId;// 主键 非空     
	private @caption("城市") @column("csvd_host")    @relate("$csvdHost") @RelateClass(SrvHost.class)  @note("  ") Long csvdHost;// 非空     
 	private SrvHost $csvdHost;//关联对象[运营城市]
	private @caption("所属平台") @column("csvd_platform")    @note("  ") String csvdPlatform;//     
	private @caption("所属渠道") @column("csvd_channel")    @relate("$csvdChannel") @RelateClass(CsChannel.class)  @note("  ") Long csvdChannel;//     
 	private CsChannel $csvdChannel;//关联对象[访问渠道]
	private @caption("所属策划") @column("csvd_plan")    @relate("$csvdPlan") @RelateClass(CsMarketPlan.class)  @note("  ") Long csvdPlan;//     
 	private CsMarketPlan $csvdPlan;//关联对象[营销方案]
	private @caption("所属活动") @column("csvd_activity")    @relate("$csvdActivity") @RelateClass(CsActivity.class)  @note("  ") Long csvdActivity;//     
 	private CsActivity $csvdActivity;//关联对象[营销活动]
	private @caption("PV") @column("csvd_pv")    @note("  ") Integer csvdPv;// 非空     
	private @caption("UV") @column("csvd_uv")    @note("  ") Integer csvdUv;// 非空     
	private @caption("IP") @column("csvd_ip")    @note("  ") Integer csvdIp;// 非空     
	private @caption("访问时间") @column("csvd_add_time")    @note("  ") Date csvdAddTime;// 非空     
	private @caption("状态") @column("csvd_status")    @note(" 1:正常 0:无效  ") Short csvdStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsVisitDetail(){
	
	}
	
	//主键构造函数
	public CsVisitDetail(Long id){
		this.csvdId = id;
	}
	
	/**所有字段构造函数 CsVisitDetail(csvdHost,csvdPlatform,csvdChannel,csvdPlan,csvdActivity,csvdPv,csvdUv,csvdIp,csvdAddTime,csvdStatus)
	 CsVisitDetail(
	 	$.getLong("csvdHost")//城市 [非空]
	 	,$.getString("csvdPlatform")//所属平台
	 	,$.getLong("csvdChannel")//所属渠道
	 	,$.getLong("csvdPlan")//所属策划
	 	,$.getLong("csvdActivity")//所属活动
	 	,$.getInteger("csvdPv")//PV [非空]
	 	,$.getInteger("csvdUv")//UV [非空]
	 	,$.getInteger("csvdIp")//IP [非空]
	 	,$.getDate("csvdAddTime")//访问时间 [非空]
	 	,$.getShort("csvdStatus")//状态 [非空]
	 )
	**/
	public CsVisitDetail(Long csvdHost,String csvdPlatform,Long csvdChannel,Long csvdPlan,Long csvdActivity,Integer csvdPv,Integer csvdUv,Integer csvdIp,Date csvdAddTime,Short csvdStatus){
		this.csvdHost=csvdHost;
		this.csvdPlatform=csvdPlatform;
		this.csvdChannel=csvdChannel;
		this.csvdPlan=csvdPlan;
		this.csvdActivity=csvdActivity;
		this.csvdPv=csvdPv;
		this.csvdUv=csvdUv;
		this.csvdIp=csvdIp;
		this.csvdAddTime=csvdAddTime;
		this.csvdStatus=csvdStatus;
	}
	
	//设置非空字段
	public CsVisitDetail setNotNull(Long csvdHost,Integer csvdPv,Integer csvdUv,Integer csvdIp,Date csvdAddTime,Short csvdStatus){
		this.csvdHost=csvdHost;
		this.csvdPv=csvdPv;
		this.csvdUv=csvdUv;
		this.csvdIp=csvdIp;
		this.csvdAddTime=csvdAddTime;
		this.csvdStatus=csvdStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsVisitDetail csvdId(Long csvdId){
		this.csvdId = csvdId;
		this.setSeted(F.csvdId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsVisitDetail csvdHost(Long csvdHost){
		this.csvdHost = csvdHost;
		this.setSeted(F.csvdHost);
		return this;
	}
	/** 所属平台        **/
	public CsVisitDetail csvdPlatform(String csvdPlatform){
		this.csvdPlatform = csvdPlatform;
		this.setSeted(F.csvdPlatform);
		return this;
	}
	/** 所属渠道  [CsChannel]      **/
	public CsVisitDetail csvdChannel(Long csvdChannel){
		this.csvdChannel = csvdChannel;
		this.setSeted(F.csvdChannel);
		return this;
	}
	/** 所属策划  [CsMarketPlan]      **/
	public CsVisitDetail csvdPlan(Long csvdPlan){
		this.csvdPlan = csvdPlan;
		this.setSeted(F.csvdPlan);
		return this;
	}
	/** 所属活动  [CsActivity]      **/
	public CsVisitDetail csvdActivity(Long csvdActivity){
		this.csvdActivity = csvdActivity;
		this.setSeted(F.csvdActivity);
		return this;
	}
	/** PV [非空]       **/
	public CsVisitDetail csvdPv(Integer csvdPv){
		this.csvdPv = csvdPv;
		this.setSeted(F.csvdPv);
		return this;
	}
	/** UV [非空]       **/
	public CsVisitDetail csvdUv(Integer csvdUv){
		this.csvdUv = csvdUv;
		this.setSeted(F.csvdUv);
		return this;
	}
	/** IP [非空]       **/
	public CsVisitDetail csvdIp(Integer csvdIp){
		this.csvdIp = csvdIp;
		this.setSeted(F.csvdIp);
		return this;
	}
	/** 访问时间 [非空]       **/
	public CsVisitDetail csvdAddTime(Date csvdAddTime){
		this.csvdAddTime = csvdAddTime;
		this.setSeted(F.csvdAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsVisitDetail csvdStatus(Short csvdStatus){
		this.csvdStatus = csvdStatus;
		this.setSeted(F.csvdStatus);
		return this;
	}
	
	
	//克隆对象
	public CsVisitDetail clone(){
		CsVisitDetail clone = new CsVisitDetail();
		clone.csvdHost=this.csvdHost;
		clone.csvdPv=this.csvdPv;
		clone.csvdUv=this.csvdUv;
		clone.csvdIp=this.csvdIp;
		clone.csvdAddTime=this.csvdAddTime;
		clone.csvdStatus=this.csvdStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取访问详情
	 * @param id
	 * @return
	 */
	public static @api CsVisitDetail get(Long id){		
		return getCsVisitDetailById(id);
	}
	/**
	 * 获取所有访问详情
	 * @return
	 */
	public static @api List<CsVisitDetail> list(Map params,Integer size){
		return getCsVisitDetailList(params,size);
	}
	/**
	 * 获取访问详情分页
	 * @return
	 */
	public static @api Page<CsVisitDetail> page(int page,int size,Map params){
		return getCsVisitDetailPage(page, size , params);
	}
	/**
	 * 根据查询条件取访问详情
	 * @param params
	 * @return
	 */
	public static @api CsVisitDetail Get(Map params){
		return getCsVisitDetail(params);
	}
	/**
	 * 获取访问详情数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsVisitDetailCount(params);
	}
	/**
	 * 获取访问详情数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsVisitDetailEval(eval,params);
	}
	
	/**
	 * 根据ID取访问详情
	 * @param id
	 * @return
	 */
	public static @api CsVisitDetail getCsVisitDetailById(Long id){		
		CsVisitDetail csVisitDetail = (CsVisitDetail) $.GetRequest("CsVisitDetail$"+id);
		if(csVisitDetail!=null)
			return csVisitDetail;
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");		
		return csVisitDetailService.getCsVisitDetailById(id);
	}
	
	
	/**
	 * 根据ID取访问详情的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsVisitDetail.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsVisitDetail csVisitDetail = get(id);
		if(csVisitDetail!=null){
			String strValue = csVisitDetail.getCsvdId$();
			if(!"CsvdId".equals("CsvdId"))
				strValue+="("+csVisitDetail.getCsvdId$()+")";
			if(!"CsvdId".equals("CsvdPlatform"))
				strValue+="("+csVisitDetail.getCsvdPlatform$()+")";
			MemCache.setValue(CsVisitDetail.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsvdId$();
		if(!"CsvdId".equals("CsvdId"))
			keyValue+="("+this.getCsvdId$()+")";
		if(!"CsvdId".equals("CsvdPlatform"))
			keyValue+="("+this.getCsvdPlatform$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有访问详情
	 * @return
	 */
	public static @api List<CsVisitDetail> getCsVisitDetailList(Map params,Integer size){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		return csVisitDetailService.getCsVisitDetailList(params, size);
	}
	
	/**
	 * 获取访问详情分页
	 * @return
	 */
	public static @api Page<CsVisitDetail> getCsVisitDetailPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		return csVisitDetailService.getCsVisitDetailPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取访问详情
	 * @param params
	 * @return
	 */
	public static @api CsVisitDetail getCsVisitDetail(Map params){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		return csVisitDetailService.getCsVisitDetail(params);
	}
	
	/**
	 * 获取访问详情数
	 * @return
	 */
	public static @api Long getCsVisitDetailCount(Map params){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		return csVisitDetailService.getCsVisitDetailCount(params);
	}
		
		
	/**
	 * 获取访问详情自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsVisitDetailEval(String eval,Map params){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		return csVisitDetailService.getCsVisitDetailEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsVisitDetail(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		csVisitDetailService.updateCsVisitDetailByConfirm(set, where);
	}
	
	
	/**
	 * 保存访问详情对象
	 * @param params
	 * @return
	 */
	public CsVisitDetail save(){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		if(this.getCsvdId()!=null)
			csVisitDetailService.updateCsVisitDetail(this);
		else
			return csVisitDetailService.saveCsVisitDetail(this);
		return this;
	}
	
	
	/**
	 * 更新访问详情对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		csVisitDetailService.updateCsVisitDetail$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		if($.equals($.config("logic_delete"),"true"))
			csVisitDetailService.removeCsVisitDetailById(this.getCsvdId());
		else
			csVisitDetailService.deleteCsVisitDetailById(this.getCsvdId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsVisitDetailService csVisitDetailService = $.GetSpringBean("csVisitDetailService");
		return csVisitDetailService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsvdId(){
		return this.csvdId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsvdId$(){
		String strValue="";
		 strValue=$.str(this.getCsvdId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsvdId(Long csvdId){
		this.csvdId = csvdId;
		this.setSeted(F.csvdId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsvdHost(){
		return this.csvdHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsvdHost$(){
		String strValue="";
		if(this.getCsvdHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsvdHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsvdHost(Long csvdHost){
		this.csvdHost = csvdHost;
		this.setSeted(F.csvdHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csvdHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsvdHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsvdHost()!=null){
 			srvHost = SrvHost.get(this.getCsvdHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsvdHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属平台**********************************/	
	/**
	* 所属平台       
	**/
	public String getCsvdPlatform(){
		return this.csvdPlatform;
	}
	/**
	* 获取所属平台格式化(toString)
	**/
	public String getCsvdPlatform$(){
		String strValue="";
		 strValue=$.str(this.getCsvdPlatform());
	 	 return strValue;
	}
	/**
	* 所属平台       
	**/
	public void setCsvdPlatform(String csvdPlatform){
		this.csvdPlatform = csvdPlatform;
		this.setSeted(F.csvdPlatform);
	}
	/*******************************所属渠道**********************************/	
	/**
	* 所属渠道  [CsChannel]     
	**/
	public Long getCsvdChannel(){
		return this.csvdChannel;
	}
	/**
	* 获取所属渠道格式化(toString)
	**/
	public String getCsvdChannel$(){
		String strValue="";
		if(this.getCsvdChannel()!=null){
				strValue+=$.str(CsChannel.getKeyValue(this.getCsvdChannel()));
		}			
	 	 return strValue;
	}
	/**
	* 所属渠道  [CsChannel]     
	**/
	public void setCsvdChannel(Long csvdChannel){
		this.csvdChannel = csvdChannel;
		this.setSeted(F.csvdChannel);
	}
	/**
	* 获取关联对象[访问渠道]
	**/	 			
 	public CsChannel get$csvdChannel(){
 		com.ccclubs.model.CsChannel csChannel = (com.ccclubs.model.CsChannel) $.GetRequest("CsChannel$"+this.getCsvdChannel());
 		if(csChannel!=null)
			return csChannel;
		if(this.getCsvdChannel()!=null){
 			csChannel = CsChannel.get(this.getCsvdChannel());
 		}
 		$.SetRequest("CsChannel$"+this.getCsvdChannel(), csChannel);
	 	return csChannel;
	}
	/*******************************所属策划**********************************/	
	/**
	* 所属策划  [CsMarketPlan]     
	**/
	public Long getCsvdPlan(){
		return this.csvdPlan;
	}
	/**
	* 获取所属策划格式化(toString)
	**/
	public String getCsvdPlan$(){
		String strValue="";
		if(this.getCsvdPlan()!=null){
				strValue+=$.str(CsMarketPlan.getKeyValue(this.getCsvdPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 所属策划  [CsMarketPlan]     
	**/
	public void setCsvdPlan(Long csvdPlan){
		this.csvdPlan = csvdPlan;
		this.setSeted(F.csvdPlan);
	}
	/**
	* 获取关联对象[营销方案]
	**/	 			
 	public CsMarketPlan get$csvdPlan(){
 		com.ccclubs.model.CsMarketPlan csMarketPlan = (com.ccclubs.model.CsMarketPlan) $.GetRequest("CsMarketPlan$"+this.getCsvdPlan());
 		if(csMarketPlan!=null)
			return csMarketPlan;
		if(this.getCsvdPlan()!=null){
 			csMarketPlan = CsMarketPlan.get(this.getCsvdPlan());
 		}
 		$.SetRequest("CsMarketPlan$"+this.getCsvdPlan(), csMarketPlan);
	 	return csMarketPlan;
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动  [CsActivity]     
	**/
	public Long getCsvdActivity(){
		return this.csvdActivity;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getCsvdActivity$(){
		String strValue="";
		if(this.getCsvdActivity()!=null){
				strValue+=$.str(CsActivity.getKeyValue(this.getCsvdActivity()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动  [CsActivity]     
	**/
	public void setCsvdActivity(Long csvdActivity){
		this.csvdActivity = csvdActivity;
		this.setSeted(F.csvdActivity);
	}
	/**
	* 获取关联对象[营销活动]
	**/	 			
 	public CsActivity get$csvdActivity(){
 		com.ccclubs.model.CsActivity csActivity = (com.ccclubs.model.CsActivity) $.GetRequest("CsActivity$"+this.getCsvdActivity());
 		if(csActivity!=null)
			return csActivity;
		if(this.getCsvdActivity()!=null){
 			csActivity = CsActivity.get(this.getCsvdActivity());
 		}
 		$.SetRequest("CsActivity$"+this.getCsvdActivity(), csActivity);
	 	return csActivity;
	}
	/*******************************PV**********************************/	
	/**
	* PV [非空]      
	**/
	public Integer getCsvdPv(){
		return this.csvdPv;
	}
	/**
	* 获取PV格式化(toString)
	**/
	public String getCsvdPv$(){
		String strValue="";
		 strValue=$.str(this.getCsvdPv());
	 	 return strValue;
	}
	/**
	* PV [非空]      
	**/
	public void setCsvdPv(Integer csvdPv){
		this.csvdPv = csvdPv;
		this.setSeted(F.csvdPv);
	}
	/*******************************UV**********************************/	
	/**
	* UV [非空]      
	**/
	public Integer getCsvdUv(){
		return this.csvdUv;
	}
	/**
	* 获取UV格式化(toString)
	**/
	public String getCsvdUv$(){
		String strValue="";
		 strValue=$.str(this.getCsvdUv());
	 	 return strValue;
	}
	/**
	* UV [非空]      
	**/
	public void setCsvdUv(Integer csvdUv){
		this.csvdUv = csvdUv;
		this.setSeted(F.csvdUv);
	}
	/*******************************IP**********************************/	
	/**
	* IP [非空]      
	**/
	public Integer getCsvdIp(){
		return this.csvdIp;
	}
	/**
	* 获取IP格式化(toString)
	**/
	public String getCsvdIp$(){
		String strValue="";
		 strValue=$.str(this.getCsvdIp());
	 	 return strValue;
	}
	/**
	* IP [非空]      
	**/
	public void setCsvdIp(Integer csvdIp){
		this.csvdIp = csvdIp;
		this.setSeted(F.csvdIp);
	}
	/*******************************访问时间**********************************/	
	/**
	* 访问时间 [非空]      
	**/
	public Date getCsvdAddTime(){
		return this.csvdAddTime;
	}
	/**
	* 获取访问时间格式化(toString)
	**/
	public String getCsvdAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsvdAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 访问时间 [非空]      
	**/
	public void setCsvdAddTime(Date csvdAddTime){
		this.csvdAddTime = csvdAddTime;
		this.setSeted(F.csvdAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsvdStatus(){
		return this.csvdStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsvdStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsvdStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsvdStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsvdStatus(Short csvdStatus){
		this.csvdStatus = csvdStatus;
		this.setSeted(F.csvdStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsVisitDetail.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsVisitDetail.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsVisitDetail.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsVisitDetail.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsVisitDetail.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsVisitDetail.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsVisitDetail.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsVisitDetail.this);
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
		public M csvdId(Object csvdId){this.put("csvdId", csvdId);return this;};
	 	/** and csvd_id is null */
 		public M csvdIdNull(){if(this.get("csvdIdNot")==null)this.put("csvdIdNot", "");this.put("csvdId", null);return this;};
 		/** not .... */
 		public M csvdIdNot(){this.put("csvdIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csvdHost(Object csvdHost){this.put("csvdHost", csvdHost);return this;};
	 	/** and csvd_host is null */
 		public M csvdHostNull(){if(this.get("csvdHostNot")==null)this.put("csvdHostNot", "");this.put("csvdHost", null);return this;};
 		/** not .... */
 		public M csvdHostNot(){this.put("csvdHostNot", "not");return this;};
		/** 所属平台        **/
		public M csvdPlatform(Object csvdPlatform){this.put("csvdPlatform", csvdPlatform);return this;};
	 	/** and csvd_platform is null */
 		public M csvdPlatformNull(){if(this.get("csvdPlatformNot")==null)this.put("csvdPlatformNot", "");this.put("csvdPlatform", null);return this;};
 		/** not .... */
 		public M csvdPlatformNot(){this.put("csvdPlatformNot", "not");return this;};
		/** 所属渠道  [CsChannel]      **/
		public M csvdChannel(Object csvdChannel){this.put("csvdChannel", csvdChannel);return this;};
	 	/** and csvd_channel is null */
 		public M csvdChannelNull(){if(this.get("csvdChannelNot")==null)this.put("csvdChannelNot", "");this.put("csvdChannel", null);return this;};
 		/** not .... */
 		public M csvdChannelNot(){this.put("csvdChannelNot", "not");return this;};
		/** 所属策划  [CsMarketPlan]      **/
		public M csvdPlan(Object csvdPlan){this.put("csvdPlan", csvdPlan);return this;};
	 	/** and csvd_plan is null */
 		public M csvdPlanNull(){if(this.get("csvdPlanNot")==null)this.put("csvdPlanNot", "");this.put("csvdPlan", null);return this;};
 		/** not .... */
 		public M csvdPlanNot(){this.put("csvdPlanNot", "not");return this;};
		/** 所属活动  [CsActivity]      **/
		public M csvdActivity(Object csvdActivity){this.put("csvdActivity", csvdActivity);return this;};
	 	/** and csvd_activity is null */
 		public M csvdActivityNull(){if(this.get("csvdActivityNot")==null)this.put("csvdActivityNot", "");this.put("csvdActivity", null);return this;};
 		/** not .... */
 		public M csvdActivityNot(){this.put("csvdActivityNot", "not");return this;};
		/** PV [非空]       **/
		public M csvdPv(Object csvdPv){this.put("csvdPv", csvdPv);return this;};
	 	/** and csvd_pv is null */
 		public M csvdPvNull(){if(this.get("csvdPvNot")==null)this.put("csvdPvNot", "");this.put("csvdPv", null);return this;};
 		/** not .... */
 		public M csvdPvNot(){this.put("csvdPvNot", "not");return this;};
		/** and csvd_pv >= ? */
		public M csvdPvMin(Object min){this.put("csvdPvMin", min);return this;};
		/** and csvd_pv <= ? */
		public M csvdPvMax(Object max){this.put("csvdPvMax", max);return this;};
		/** UV [非空]       **/
		public M csvdUv(Object csvdUv){this.put("csvdUv", csvdUv);return this;};
	 	/** and csvd_uv is null */
 		public M csvdUvNull(){if(this.get("csvdUvNot")==null)this.put("csvdUvNot", "");this.put("csvdUv", null);return this;};
 		/** not .... */
 		public M csvdUvNot(){this.put("csvdUvNot", "not");return this;};
		/** and csvd_uv >= ? */
		public M csvdUvMin(Object min){this.put("csvdUvMin", min);return this;};
		/** and csvd_uv <= ? */
		public M csvdUvMax(Object max){this.put("csvdUvMax", max);return this;};
		/** IP [非空]       **/
		public M csvdIp(Object csvdIp){this.put("csvdIp", csvdIp);return this;};
	 	/** and csvd_ip is null */
 		public M csvdIpNull(){if(this.get("csvdIpNot")==null)this.put("csvdIpNot", "");this.put("csvdIp", null);return this;};
 		/** not .... */
 		public M csvdIpNot(){this.put("csvdIpNot", "not");return this;};
		/** and csvd_ip >= ? */
		public M csvdIpMin(Object min){this.put("csvdIpMin", min);return this;};
		/** and csvd_ip <= ? */
		public M csvdIpMax(Object max){this.put("csvdIpMax", max);return this;};
		/** 访问时间 [非空]       **/
		public M csvdAddTime(Object csvdAddTime){this.put("csvdAddTime", csvdAddTime);return this;};
	 	/** and csvd_add_time is null */
 		public M csvdAddTimeNull(){if(this.get("csvdAddTimeNot")==null)this.put("csvdAddTimeNot", "");this.put("csvdAddTime", null);return this;};
 		/** not .... */
 		public M csvdAddTimeNot(){this.put("csvdAddTimeNot", "not");return this;};
 		/** and csvd_add_time >= ? */
 		public M csvdAddTimeStart(Object start){this.put("csvdAddTimeStart", start);return this;};			
 		/** and csvd_add_time <= ? */
 		public M csvdAddTimeEnd(Object end){this.put("csvdAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csvdStatus(Object csvdStatus){this.put("csvdStatus", csvdStatus);return this;};
	 	/** and csvd_status is null */
 		public M csvdStatusNull(){if(this.get("csvdStatusNot")==null)this.put("csvdStatusNot", "");this.put("csvdStatus", null);return this;};
 		/** not .... */
 		public M csvdStatusNot(){this.put("csvdStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有访问详情 **/
		public @api List<CsVisitDetail> list(Integer size){
			return getCsVisitDetailList(this,size);
		}
		/** 获取访问详情分页 **/
		public @api Page<CsVisitDetail> page(int page,int size){
			return getCsVisitDetailPage(page, size , this);
		}
		/** 根据查询条件取访问详情 **/
		public @api CsVisitDetail get(){
			return getCsVisitDetail(this);
		}
		/** 获取访问详情数 **/
		public @api Long count(){
			return getCsVisitDetailCount(this);
		}
		/** 获取访问详情表达式 **/
		public @api <T> T eval(String strEval){
			return getCsVisitDetailEval(strEval,this);
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
			updateCsVisitDetail(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csvdId="csvdId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csvdHost="csvdHost";
		/** 所属平台        **/
		public final static @type(String.class) @like String csvdPlatform="csvdPlatform";
		/** 所属渠道  [CsChannel]      **/
		public final static @type(Long.class)  String csvdChannel="csvdChannel";
		/** 所属策划  [CsMarketPlan]      **/
		public final static @type(Long.class)  String csvdPlan="csvdPlan";
		/** 所属活动  [CsActivity]      **/
		public final static @type(Long.class)  String csvdActivity="csvdActivity";
		/** PV [非空]       **/
		public final static @type(Integer.class)  String csvdPv="csvdPv";
		/** and csvd_pv >= ? */
		public final static @type(Integer.class) String csvdPvMin="csvdPvMin";
		/** and csvd_pv <= ? */
		public final static @type(Integer.class) String csvdPvMax="csvdPvMax";
		/** UV [非空]       **/
		public final static @type(Integer.class)  String csvdUv="csvdUv";
		/** and csvd_uv >= ? */
		public final static @type(Integer.class) String csvdUvMin="csvdUvMin";
		/** and csvd_uv <= ? */
		public final static @type(Integer.class) String csvdUvMax="csvdUvMax";
		/** IP [非空]       **/
		public final static @type(Integer.class)  String csvdIp="csvdIp";
		/** and csvd_ip >= ? */
		public final static @type(Integer.class) String csvdIpMin="csvdIpMin";
		/** and csvd_ip <= ? */
		public final static @type(Integer.class) String csvdIpMax="csvdIpMax";
		/** 访问时间 [非空]       **/
		public final static @type(Date.class)  String csvdAddTime="csvdAddTime";
	 	/** and csvd_add_time >= ? */
 		public final static @type(Date.class) String csvdAddTimeStart="csvdAddTimeStart";
 		/** and csvd_add_time <= ? */
 		public final static @type(Date.class) String csvdAddTimeEnd="csvdAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csvdStatus="csvdStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csvdId="csvd_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csvdHost="csvd_host";
		/** 所属平台        **/
		public final static String csvdPlatform="csvd_platform";
		/** 所属渠道  [CsChannel]      **/
		public final static String csvdChannel="csvd_channel";
		/** 所属策划  [CsMarketPlan]      **/
		public final static String csvdPlan="csvd_plan";
		/** 所属活动  [CsActivity]      **/
		public final static String csvdActivity="csvd_activity";
		/** PV [非空]       **/
		public final static String csvdPv="csvd_pv";
		/** UV [非空]       **/
		public final static String csvdUv="csvd_uv";
		/** IP [非空]       **/
		public final static String csvdIp="csvd_ip";
		/** 访问时间 [非空]       **/
		public final static String csvdAddTime="csvd_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csvdStatus="csvd_status";
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
				$.Set(name,CsVisitDetail.getCsVisitDetail(params));
			else
				$.Set(name,CsVisitDetail.getCsVisitDetailList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取访问详情数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsVisitDetail) $.GetRequest("CsVisitDetail$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsVisitDetail.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsVisitDetail.getCsVisitDetail(params);
			else
				value = CsVisitDetail.getCsVisitDetailList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsVisitDetail.Get($.add(CsVisitDetail.F.csvdId,param));
		else if(!$.empty(param.toString()))
			value = CsVisitDetail.get(Long.valueOf(param.toString()));
		$.SetRequest("CsVisitDetail$"+param.hashCode(), value);
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
	public void mergeSet(CsVisitDetail old){
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