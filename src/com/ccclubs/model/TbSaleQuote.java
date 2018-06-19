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

@namespace("sale/salequote")
public @caption("跟单报价") @table("tb_sale_quote") class TbSaleQuote implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbsq_id")   @primary  @note("  ") Long tbsqId;// 主键 非空     
	private @caption("城市") @column("tbsq_host")    @relate("$tbsqHost") @RelateClass(SrvHost.class)  @note("  ") Long tbsqHost;// 非空     
 	private SrvHost $tbsqHost;//关联对象[运营城市]
	private @caption("报价标题") @column("tbsq_title")    @note("  ") String tbsqTitle;// 非空     
	private @caption("所属机会") @column("tbsq_opport")    @relate("$tbsqOpport") @RelateClass(CsSaleOpport.class)  @note("  ") Long tbsqOpport;// 非空     
 	private CsSaleOpport $tbsqOpport;//关联对象[销售机会]
	private @caption("报价详情") @column("tbsq_detail")  @hidden   @note(" text:name text:unit money:price number:count money:money  ") String tbsqDetail;// 非空 text:name text:unit money:price number:count money:money     
	private @caption("报价附件") @column("tbsq_file")    @note("  ") String tbsqFile;//     
	private @caption("所有人") @column("tbsq_onwer")    @relate("$tbsqOnwer") @RelateClass(SrvUser.class)  @note("  ") String tbsqOnwer;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbsqOnwer;//关联对象[用户]
	private @caption("添加人") @column("tbsq_adder")    @relate("$tbsqAdder") @RelateClass(SrvUser.class)  @note("  ") Long tbsqAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbsqAdder;//关联对象[用户]
	private @caption("修改人") @column("tbsq_editor")    @relate("$tbsqEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbsqEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbsqEditor;//关联对象[用户]
	private @caption("修改时间") @column("tbsq_update_time")    @note("  ") Date tbsqUpdateTime;// 非空     
	private @caption("添加时间") @column("tbsq_add_time")    @note("  ") Date tbsqAddTime;// 非空     
	private @caption("状态") @column("tbsq_status")    @note(" 1:正常 0:无效  ") Short tbsqStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public TbSaleQuote(){
	
	}
	
	//主键构造函数
	public TbSaleQuote(Long id){
		this.tbsqId = id;
	}
	
	/**所有字段构造函数 TbSaleQuote(tbsqHost,tbsqTitle,tbsqOpport,tbsqDetail,tbsqFile,tbsqOnwer,tbsqAdder,tbsqEditor,tbsqUpdateTime,tbsqAddTime,tbsqStatus)
	 TbSaleQuote(
	 	$.getLong("tbsqHost")//城市 [非空]
	 	,$.getString("tbsqTitle")//报价标题 [非空]
	 	,$.getLong("tbsqOpport")//所属机会 [非空]
	 	,$.getString("tbsqDetail")//报价详情 [非空]
	 	,$.getString("tbsqFile")//报价附件
	 	,$.getString("tbsqOnwer")//所有人 [非空]
	 	,$.getLong("tbsqAdder")//添加人 [非空]
	 	,$.getLong("tbsqEditor")//修改人 [非空]
	 	,$.getDate("tbsqUpdateTime")//修改时间 [非空]
	 	,$.getDate("tbsqAddTime")//添加时间 [非空]
	 	,$.getShort("tbsqStatus")//状态 [非空]
	 )
	**/
	public TbSaleQuote(Long tbsqHost,String tbsqTitle,Long tbsqOpport,String tbsqDetail,String tbsqFile,String tbsqOnwer,Long tbsqAdder,Long tbsqEditor,Date tbsqUpdateTime,Date tbsqAddTime,Short tbsqStatus){
		this.tbsqHost=tbsqHost;
		this.tbsqTitle=tbsqTitle;
		this.tbsqOpport=tbsqOpport;
		this.tbsqDetail=tbsqDetail;
		this.tbsqFile=tbsqFile;
		this.tbsqOnwer=tbsqOnwer;
		this.tbsqAdder=tbsqAdder;
		this.tbsqEditor=tbsqEditor;
		this.tbsqUpdateTime=tbsqUpdateTime;
		this.tbsqAddTime=tbsqAddTime;
		this.tbsqStatus=tbsqStatus;
	}
	
	//设置非空字段
	public TbSaleQuote setNotNull(Long tbsqHost,String tbsqTitle,Long tbsqOpport,String tbsqDetail,String tbsqOnwer,Long tbsqAdder,Long tbsqEditor,Date tbsqUpdateTime,Date tbsqAddTime,Short tbsqStatus){
		this.tbsqHost=tbsqHost;
		this.tbsqTitle=tbsqTitle;
		this.tbsqOpport=tbsqOpport;
		this.tbsqDetail=tbsqDetail;
		this.tbsqOnwer=tbsqOnwer;
		this.tbsqAdder=tbsqAdder;
		this.tbsqEditor=tbsqEditor;
		this.tbsqUpdateTime=tbsqUpdateTime;
		this.tbsqAddTime=tbsqAddTime;
		this.tbsqStatus=tbsqStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbSaleQuote tbsqId(Long tbsqId){
		this.tbsqId = tbsqId;
		this.setSeted(F.tbsqId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbSaleQuote tbsqHost(Long tbsqHost){
		this.tbsqHost = tbsqHost;
		this.setSeted(F.tbsqHost);
		return this;
	}
	/** 报价标题 [非空]       **/
	public TbSaleQuote tbsqTitle(String tbsqTitle){
		this.tbsqTitle = tbsqTitle;
		this.setSeted(F.tbsqTitle);
		return this;
	}
	/** 所属机会 [非空] [CsSaleOpport]      **/
	public TbSaleQuote tbsqOpport(Long tbsqOpport){
		this.tbsqOpport = tbsqOpport;
		this.setSeted(F.tbsqOpport);
		return this;
	}
	/** 报价详情 [非空]   text:name text:unit money:price number:count money:money     **/
	public TbSaleQuote tbsqDetail(String tbsqDetail){
		this.tbsqDetail = tbsqDetail;
		this.setSeted(F.tbsqDetail);
		return this;
	}
	/** 报价附件        **/
	public TbSaleQuote tbsqFile(String tbsqFile){
		this.tbsqFile = tbsqFile;
		this.setSeted(F.tbsqFile);
		return this;
	}
	/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleQuote tbsqOnwer(String tbsqOnwer){
		this.tbsqOnwer = tbsqOnwer;
		this.setSeted(F.tbsqOnwer);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleQuote tbsqAdder(Long tbsqAdder){
		this.tbsqAdder = tbsqAdder;
		this.setSeted(F.tbsqAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleQuote tbsqEditor(Long tbsqEditor){
		this.tbsqEditor = tbsqEditor;
		this.setSeted(F.tbsqEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbSaleQuote tbsqUpdateTime(Date tbsqUpdateTime){
		this.tbsqUpdateTime = tbsqUpdateTime;
		this.setSeted(F.tbsqUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbSaleQuote tbsqAddTime(Date tbsqAddTime){
		this.tbsqAddTime = tbsqAddTime;
		this.setSeted(F.tbsqAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public TbSaleQuote tbsqStatus(Short tbsqStatus){
		this.tbsqStatus = tbsqStatus;
		this.setSeted(F.tbsqStatus);
		return this;
	}
	
	
	//克隆对象
	public TbSaleQuote clone(){
		TbSaleQuote clone = new TbSaleQuote();
		clone.tbsqHost=this.tbsqHost;
		clone.tbsqTitle=this.tbsqTitle;
		clone.tbsqOpport=this.tbsqOpport;
		clone.tbsqDetail=this.tbsqDetail;
		clone.tbsqOnwer=this.tbsqOnwer;
		clone.tbsqAdder=this.tbsqAdder;
		clone.tbsqEditor=this.tbsqEditor;
		clone.tbsqUpdateTime=this.tbsqUpdateTime;
		clone.tbsqAddTime=this.tbsqAddTime;
		clone.tbsqStatus=this.tbsqStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取跟单报价
	 * @param id
	 * @return
	 */
	public static @api TbSaleQuote get(Long id){		
		return getTbSaleQuoteById(id);
	}
	/**
	 * 获取所有跟单报价
	 * @return
	 */
	public static @api List<TbSaleQuote> list(Map params,Integer size){
		return getTbSaleQuoteList(params,size);
	}
	/**
	 * 获取跟单报价分页
	 * @return
	 */
	public static @api Page<TbSaleQuote> page(int page,int size,Map params){
		return getTbSaleQuotePage(page, size , params);
	}
	/**
	 * 根据查询条件取跟单报价
	 * @param params
	 * @return
	 */
	public static @api TbSaleQuote Get(Map params){
		return getTbSaleQuote(params);
	}
	/**
	 * 获取跟单报价数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbSaleQuoteCount(params);
	}
	/**
	 * 获取跟单报价数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbSaleQuoteEval(eval,params);
	}
	
	/**
	 * 根据ID取跟单报价
	 * @param id
	 * @return
	 */
	public static @api TbSaleQuote getTbSaleQuoteById(Long id){		
		TbSaleQuote tbSaleQuote = (TbSaleQuote) $.GetRequest("TbSaleQuote$"+id);
		if(tbSaleQuote!=null)
			return tbSaleQuote;
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");		
		return tbSaleQuoteService.getTbSaleQuoteById(id);
	}
	
	
	/**
	 * 根据ID取跟单报价的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbSaleQuote.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbSaleQuote tbSaleQuote = get(id);
		if(tbSaleQuote!=null){
			String strValue = tbSaleQuote.getTbsqTitle$();
			if(!"TbsqTitle".equals("TbsqTitle"))
				strValue+="("+tbSaleQuote.getTbsqTitle$()+")";
			MemCache.setValue(TbSaleQuote.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbsqTitle$();
		if(!"TbsqTitle".equals("TbsqTitle"))
			keyValue+="("+this.getTbsqTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有跟单报价
	 * @return
	 */
	public static @api List<TbSaleQuote> getTbSaleQuoteList(Map params,Integer size){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		return tbSaleQuoteService.getTbSaleQuoteList(params, size);
	}
	
	/**
	 * 获取跟单报价分页
	 * @return
	 */
	public static @api Page<TbSaleQuote> getTbSaleQuotePage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		return tbSaleQuoteService.getTbSaleQuotePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取跟单报价
	 * @param params
	 * @return
	 */
	public static @api TbSaleQuote getTbSaleQuote(Map params){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		return tbSaleQuoteService.getTbSaleQuote(params);
	}
	
	/**
	 * 获取跟单报价数
	 * @return
	 */
	public static @api Long getTbSaleQuoteCount(Map params){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		return tbSaleQuoteService.getTbSaleQuoteCount(params);
	}
		
		
	/**
	 * 获取跟单报价自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbSaleQuoteEval(String eval,Map params){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		return tbSaleQuoteService.getTbSaleQuoteEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbSaleQuote(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		tbSaleQuoteService.updateTbSaleQuoteByConfirm(set, where);
	}
	
	
	/**
	 * 保存跟单报价对象
	 * @param params
	 * @return
	 */
	public TbSaleQuote save(){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		if(this.getTbsqId()!=null)
			tbSaleQuoteService.updateTbSaleQuote(this);
		else
			return tbSaleQuoteService.saveTbSaleQuote(this);
		return this;
	}
	
	
	/**
	 * 更新跟单报价对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		tbSaleQuoteService.updateTbSaleQuote$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		if($.equals($.config("logic_delete"),"true"))
			tbSaleQuoteService.removeTbSaleQuoteById(this.getTbsqId());
		else
			tbSaleQuoteService.deleteTbSaleQuoteById(this.getTbsqId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbSaleQuoteService tbSaleQuoteService = $.GetSpringBean("tbSaleQuoteService");
		return tbSaleQuoteService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbsqId(){
		return this.tbsqId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbsqId$(){
		String strValue="";
		 strValue=$.str(this.getTbsqId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbsqId(Long tbsqId){
		this.tbsqId = tbsqId;
		this.setSeted(F.tbsqId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbsqHost(){
		return this.tbsqHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbsqHost$(){
		String strValue="";
		if(this.getTbsqHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbsqHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbsqHost(Long tbsqHost){
		this.tbsqHost = tbsqHost;
		this.setSeted(F.tbsqHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbsqHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbsqHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbsqHost()!=null){
 			srvHost = SrvHost.get(this.getTbsqHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbsqHost(), srvHost);
	 	return srvHost;
	}
	/*******************************报价标题**********************************/	
	/**
	* 报价标题 [非空]      
	**/
	public String getTbsqTitle(){
		return this.tbsqTitle;
	}
	/**
	* 获取报价标题格式化(toString)
	**/
	public String getTbsqTitle$(){
		String strValue="";
		 strValue=$.str(this.getTbsqTitle());
	 	 return strValue;
	}
	/**
	* 报价标题 [非空]      
	**/
	public void setTbsqTitle(String tbsqTitle){
		this.tbsqTitle = tbsqTitle;
		this.setSeted(F.tbsqTitle);
	}
	/*******************************所属机会**********************************/	
	/**
	* 所属机会 [非空] [CsSaleOpport]     
	**/
	public Long getTbsqOpport(){
		return this.tbsqOpport;
	}
	/**
	* 获取所属机会格式化(toString)
	**/
	public String getTbsqOpport$(){
		String strValue="";
		if(this.getTbsqOpport()!=null){
				strValue+=$.str(CsSaleOpport.getKeyValue(this.getTbsqOpport()));
		}			
	 	 return strValue;
	}
	/**
	* 所属机会 [非空] [CsSaleOpport]     
	**/
	public void setTbsqOpport(Long tbsqOpport){
		this.tbsqOpport = tbsqOpport;
		this.setSeted(F.tbsqOpport);
	}
	/**
	* 获取关联对象[销售机会]
	**/	 			
 	public CsSaleOpport get$tbsqOpport(){
 		com.ccclubs.model.CsSaleOpport csSaleOpport = (com.ccclubs.model.CsSaleOpport) $.GetRequest("CsSaleOpport$"+this.getTbsqOpport());
 		if(csSaleOpport!=null)
			return csSaleOpport;
		if(this.getTbsqOpport()!=null){
 			csSaleOpport = CsSaleOpport.get(this.getTbsqOpport());
 		}
 		$.SetRequest("CsSaleOpport$"+this.getTbsqOpport(), csSaleOpport);
	 	return csSaleOpport;
	}
	/*******************************报价详情**********************************/	
	/**
	* 报价详情 [非空]   text:name text:unit money:price number:count money:money    
	**/
	public String getTbsqDetail(){
		return this.tbsqDetail;
	}
	/**
	* 获取报价详情格式化(toString)
	**/
	public String getTbsqDetail$(){
		String strValue="";
		 strValue=$.str(this.getTbsqDetail());
	 	 return strValue;
	}
	/**
	* 报价详情 [非空]   text:name text:unit money:price number:count money:money    
	**/
	public void setTbsqDetail(String tbsqDetail){
		this.tbsqDetail = tbsqDetail;
		this.setSeted(F.tbsqDetail);
	}
	/**
	* 报价详情对应内部类
	**/
	class TbsqDetail{
		String name;//产品名称
		String unit;//单位
		String price;//价格
		String count;//数量
		String money;//金额
		public void setName(String name){
			this.name=name;
		}
		public String getName(){
			return this.name;
		}
		public void setUnit(String unit){
			this.unit=unit;
		}
		public String getUnit(){
			return this.unit;
		}
		public void setPrice(String price){
			this.price=price;
		}
		public String getPrice(){
			return this.price;
		}
		public void setCount(String count){
			this.count=count;
		}
		public String getCount(){
			return this.count;
		}
		public void setMoney(String money){
			this.money=money;
		}
		public String getMoney(){
			return this.money;
		}
	}
	/**
	* 获取报价详情格式化列表
	**/ 			
 	public List<TbsqDetail> get$tbsqDetail(){
 		List<TbsqDetail> array = new ArrayList();
 		List<Map> list = $.eval(this.tbsqDetail);
 		for(Map map:list){
 			TbsqDetail tbsqDetail = new TbsqDetail();
 			tbsqDetail.setName($.parseString(map.get("name")));
 			tbsqDetail.setUnit($.parseString(map.get("unit")));
 			tbsqDetail.setPrice($.parseString(map.get("price")));
 			tbsqDetail.setCount($.parseString(map.get("count")));
 			tbsqDetail.setMoney($.parseString(map.get("money")));
 			array.add(tbsqDetail);
 		}
 		return array;
 	}
	/*******************************报价附件**********************************/	
	/**
	* 报价附件       
	**/
	public String getTbsqFile(){
		return this.tbsqFile;
	}
	/**
	* 获取报价附件格式化(toString)
	**/
	public String getTbsqFile$(){
		String strValue="";
		 strValue=$.str(this.getTbsqFile());
	 	 return strValue;
	}
	/**
	* 报价附件       
	**/
	public void setTbsqFile(String tbsqFile){
		this.tbsqFile = tbsqFile;
		this.setSeted(F.tbsqFile);
	}
	/*******************************所有人**********************************/	
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public String getTbsqOnwer(){
		return this.tbsqOnwer;
	}
	/**
	* 获取所有人格式化(toString)
	**/
	public String getTbsqOnwer$(){
		String strValue="";
			com.ccclubs.model.SrvUser srvUser = com.ccclubs.model.SrvUser.getSrvUser($.add("suFamily",this.getTbsqOnwer()));
		if(srvUser!=null)
			strValue+="["+$.str(srvUser.getSuRealName())+"]";
	 	 return strValue;
	}
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbsqOnwer(String tbsqOnwer){
		this.tbsqOnwer = tbsqOnwer;
		this.setSeted(F.tbsqOnwer);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbsqOnwer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbsqOnwer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbsqOnwer()!=null){
 			srvUser = SrvUser.Get($.add(SrvUser.F.suId,this.getTbsqOnwer()));
 		}
 		$.SetRequest("SrvUser$"+this.getTbsqOnwer(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbsqAdder(){
		return this.tbsqAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getTbsqAdder$(){
		String strValue="";
		if(this.getTbsqAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbsqAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbsqAdder(Long tbsqAdder){
		this.tbsqAdder = tbsqAdder;
		this.setSeted(F.tbsqAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbsqAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbsqAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbsqAdder()!=null){
 			srvUser = SrvUser.get(this.getTbsqAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getTbsqAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbsqEditor(){
		return this.tbsqEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getTbsqEditor$(){
		String strValue="";
		if(this.getTbsqEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbsqEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbsqEditor(Long tbsqEditor){
		this.tbsqEditor = tbsqEditor;
		this.setSeted(F.tbsqEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbsqEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbsqEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbsqEditor()!=null){
 			srvUser = SrvUser.get(this.getTbsqEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbsqEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbsqUpdateTime(){
		return this.tbsqUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbsqUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbsqUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setTbsqUpdateTime(Date tbsqUpdateTime){
		this.tbsqUpdateTime = tbsqUpdateTime;
		this.setSeted(F.tbsqUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbsqAddTime(){
		return this.tbsqAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbsqAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbsqAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setTbsqAddTime(Date tbsqAddTime){
		this.tbsqAddTime = tbsqAddTime;
		this.setSeted(F.tbsqAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getTbsqStatus(){
		return this.tbsqStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbsqStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbsqStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getTbsqStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setTbsqStatus(Short tbsqStatus){
		this.tbsqStatus = tbsqStatus;
		this.setSeted(F.tbsqStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbSaleQuote.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleQuote.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleQuote.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbSaleQuote.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbSaleQuote.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleQuote.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleQuote.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbSaleQuote.this);
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
		public M tbsqId(Object tbsqId){this.put("tbsqId", tbsqId);return this;};
	 	/** and tbsq_id is null */
 		public M tbsqIdNull(){if(this.get("tbsqIdNot")==null)this.put("tbsqIdNot", "");this.put("tbsqId", null);return this;};
 		/** not .... */
 		public M tbsqIdNot(){this.put("tbsqIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbsqHost(Object tbsqHost){this.put("tbsqHost", tbsqHost);return this;};
	 	/** and tbsq_host is null */
 		public M tbsqHostNull(){if(this.get("tbsqHostNot")==null)this.put("tbsqHostNot", "");this.put("tbsqHost", null);return this;};
 		/** not .... */
 		public M tbsqHostNot(){this.put("tbsqHostNot", "not");return this;};
		/** 报价标题 [非空]       **/
		public M tbsqTitle(Object tbsqTitle){this.put("tbsqTitle", tbsqTitle);return this;};
	 	/** and tbsq_title is null */
 		public M tbsqTitleNull(){if(this.get("tbsqTitleNot")==null)this.put("tbsqTitleNot", "");this.put("tbsqTitle", null);return this;};
 		/** not .... */
 		public M tbsqTitleNot(){this.put("tbsqTitleNot", "not");return this;};
		/** 所属机会 [非空] [CsSaleOpport]      **/
		public M tbsqOpport(Object tbsqOpport){this.put("tbsqOpport", tbsqOpport);return this;};
	 	/** and tbsq_opport is null */
 		public M tbsqOpportNull(){if(this.get("tbsqOpportNot")==null)this.put("tbsqOpportNot", "");this.put("tbsqOpport", null);return this;};
 		/** not .... */
 		public M tbsqOpportNot(){this.put("tbsqOpportNot", "not");return this;};
		public M tbsqOpport$on(CsSaleOpport.M value){
			this.put("CsSaleOpport", value);
			this.put("tbsqOpport$on", value);
			return this;
		};	
		/** 报价详情 [非空]   text:name text:unit money:price number:count money:money     **/
		public M tbsqDetail(Object tbsqDetail){this.put("tbsqDetail", tbsqDetail);return this;};
	 	/** and tbsq_detail is null */
 		public M tbsqDetailNull(){if(this.get("tbsqDetailNot")==null)this.put("tbsqDetailNot", "");this.put("tbsqDetail", null);return this;};
 		/** not .... */
 		public M tbsqDetailNot(){this.put("tbsqDetailNot", "not");return this;};
		/** 报价附件        **/
		public M tbsqFile(Object tbsqFile){this.put("tbsqFile", tbsqFile);return this;};
	 	/** and tbsq_file is null */
 		public M tbsqFileNull(){if(this.get("tbsqFileNot")==null)this.put("tbsqFileNot", "");this.put("tbsqFile", null);return this;};
 		/** not .... */
 		public M tbsqFileNot(){this.put("tbsqFileNot", "not");return this;};
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbsqOnwer(Object tbsqOnwer){this.put("tbsqOnwer", tbsqOnwer);return this;};
	 	/** and tbsq_onwer is null */
 		public M tbsqOnwerNull(){if(this.get("tbsqOnwerNot")==null)this.put("tbsqOnwerNot", "");this.put("tbsqOnwer", null);return this;};
 		/** not .... */
 		public M tbsqOnwerNot(){this.put("tbsqOnwerNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbsqAdder(Object tbsqAdder){this.put("tbsqAdder", tbsqAdder);return this;};
	 	/** and tbsq_adder is null */
 		public M tbsqAdderNull(){if(this.get("tbsqAdderNot")==null)this.put("tbsqAdderNot", "");this.put("tbsqAdder", null);return this;};
 		/** not .... */
 		public M tbsqAdderNot(){this.put("tbsqAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbsqEditor(Object tbsqEditor){this.put("tbsqEditor", tbsqEditor);return this;};
	 	/** and tbsq_editor is null */
 		public M tbsqEditorNull(){if(this.get("tbsqEditorNot")==null)this.put("tbsqEditorNot", "");this.put("tbsqEditor", null);return this;};
 		/** not .... */
 		public M tbsqEditorNot(){this.put("tbsqEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M tbsqUpdateTime(Object tbsqUpdateTime){this.put("tbsqUpdateTime", tbsqUpdateTime);return this;};
	 	/** and tbsq_update_time is null */
 		public M tbsqUpdateTimeNull(){if(this.get("tbsqUpdateTimeNot")==null)this.put("tbsqUpdateTimeNot", "");this.put("tbsqUpdateTime", null);return this;};
 		/** not .... */
 		public M tbsqUpdateTimeNot(){this.put("tbsqUpdateTimeNot", "not");return this;};
 		/** and tbsq_update_time >= ? */
 		public M tbsqUpdateTimeStart(Object start){this.put("tbsqUpdateTimeStart", start);return this;};			
 		/** and tbsq_update_time <= ? */
 		public M tbsqUpdateTimeEnd(Object end){this.put("tbsqUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbsqAddTime(Object tbsqAddTime){this.put("tbsqAddTime", tbsqAddTime);return this;};
	 	/** and tbsq_add_time is null */
 		public M tbsqAddTimeNull(){if(this.get("tbsqAddTimeNot")==null)this.put("tbsqAddTimeNot", "");this.put("tbsqAddTime", null);return this;};
 		/** not .... */
 		public M tbsqAddTimeNot(){this.put("tbsqAddTimeNot", "not");return this;};
 		/** and tbsq_add_time >= ? */
 		public M tbsqAddTimeStart(Object start){this.put("tbsqAddTimeStart", start);return this;};			
 		/** and tbsq_add_time <= ? */
 		public M tbsqAddTimeEnd(Object end){this.put("tbsqAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M tbsqStatus(Object tbsqStatus){this.put("tbsqStatus", tbsqStatus);return this;};
	 	/** and tbsq_status is null */
 		public M tbsqStatusNull(){if(this.get("tbsqStatusNot")==null)this.put("tbsqStatusNot", "");this.put("tbsqStatus", null);return this;};
 		/** not .... */
 		public M tbsqStatusNot(){this.put("tbsqStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有跟单报价 **/
		public @api List<TbSaleQuote> list(Integer size){
			return getTbSaleQuoteList(this,size);
		}
		/** 获取跟单报价分页 **/
		public @api Page<TbSaleQuote> page(int page,int size){
			return getTbSaleQuotePage(page, size , this);
		}
		/** 根据查询条件取跟单报价 **/
		public @api TbSaleQuote get(){
			return getTbSaleQuote(this);
		}
		/** 获取跟单报价数 **/
		public @api Long count(){
			return getTbSaleQuoteCount(this);
		}
		/** 获取跟单报价表达式 **/
		public @api <T> T eval(String strEval){
			return getTbSaleQuoteEval(strEval,this);
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
			updateTbSaleQuote(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbsqId="tbsqId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbsqHost="tbsqHost";
		/** 报价标题 [非空]       **/
		public final static @type(String.class) @like String tbsqTitle="tbsqTitle";
		/** 所属机会 [非空] [CsSaleOpport]      **/
		public final static @type(Long.class)  String tbsqOpport="tbsqOpport";
		/** 报价详情 [非空]   text:name text:unit money:price number:count money:money     **/
		public final static @type(String.class)  String tbsqDetail="tbsqDetail";
		/** 报价附件        **/
		public final static @type(String.class) @like String tbsqFile="tbsqFile";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(String.class)  String tbsqOnwer="tbsqOnwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbsqAdder="tbsqAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbsqEditor="tbsqEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbsqUpdateTime="tbsqUpdateTime";
	 	/** and tbsq_update_time >= ? */
 		public final static @type(Date.class) String tbsqUpdateTimeStart="tbsqUpdateTimeStart";
 		/** and tbsq_update_time <= ? */
 		public final static @type(Date.class) String tbsqUpdateTimeEnd="tbsqUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbsqAddTime="tbsqAddTime";
	 	/** and tbsq_add_time >= ? */
 		public final static @type(Date.class) String tbsqAddTimeStart="tbsqAddTimeStart";
 		/** and tbsq_add_time <= ? */
 		public final static @type(Date.class) String tbsqAddTimeEnd="tbsqAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String tbsqStatus="tbsqStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbsqId="tbsq_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbsqHost="tbsq_host";
		/** 报价标题 [非空]       **/
		public final static String tbsqTitle="tbsq_title";
		/** 所属机会 [非空] [CsSaleOpport]      **/
		public final static String tbsqOpport="tbsq_opport";
		/** 报价详情 [非空]   text:name text:unit money:price number:count money:money     **/
		public final static String tbsqDetail="tbsq_detail";
		/** 报价附件        **/
		public final static String tbsqFile="tbsq_file";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbsqOnwer="tbsq_onwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbsqAdder="tbsq_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbsqEditor="tbsq_editor";
		/** 修改时间 [非空]       **/
		public final static String tbsqUpdateTime="tbsq_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbsqAddTime="tbsq_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String tbsqStatus="tbsq_status";
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
				$.Set(name,TbSaleQuote.getTbSaleQuote(params));
			else
				$.Set(name,TbSaleQuote.getTbSaleQuoteList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取跟单报价数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbSaleQuote) $.GetRequest("TbSaleQuote$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbSaleQuote.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbSaleQuote.getTbSaleQuote(params);
			else
				value = TbSaleQuote.getTbSaleQuoteList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbSaleQuote.Get($.add(TbSaleQuote.F.tbsqId,param));
		else if(!$.empty(param.toString()))
			value = TbSaleQuote.get(Long.valueOf(param.toString()));
		$.SetRequest("TbSaleQuote$"+param.hashCode(), value);
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
	public void mergeSet(TbSaleQuote old){
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