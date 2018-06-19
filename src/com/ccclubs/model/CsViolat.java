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

@namespace("service/violat")
public @caption("车辆违章") @table("cs_violat") class CsViolat implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csv_id")   @primary  @note("  ") Long csvId;// 主键 非空     
	private @caption("城市") @column("csv_host")    @relate("$csvHost") @RelateClass(SrvHost.class)  @note("  ") Long csvHost;// 非空     
 	private SrvHost $csvHost;//关联对象[运营城市]
	private @caption("违章订单") @column("csv_order")    @relate("$csvOrder") @RelateClass(CsOrder.class)  @note("  ") Long csvOrder;// 非空     
 	private CsOrder $csvOrder;//关联对象[系统订单]
	private @caption("违章会员") @column("csv_member")    @relate("$csvMember") @RelateClass(CsMember.class)  @note("  ") Long csvMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csvMember;//关联对象[会员帐号]
	private @caption("违章车辆") @column("csv_car")    @relate("$csvCar") @RelateClass(CsCar.class)  @note("  ") Long csvCar;// 非空     
 	private CsCar $csvCar;//关联对象[车辆]
	private @caption("违章罚款") @column("csv_money")    @note("  ") Double csvMoney;//     
	private @caption("违章扣分") @column("csv_score")    @note("  ") Integer csvScore;//     
	private @caption("发生时间") @column("csv_happen_time")    @note("  ") Date csvHappenTime;// 非空     
	private @caption("违章描述") @column("csv_decipt")    @note("  ") String csvDecipt;//     
	private @caption("通知时间") @column("csv_notice_time")    @note("  ") Date csvNoticeTime;//     
	private @caption("截止时间") @column("csv_cutoff_time")    @note("  ") Date csvCutoffTime;//     
	private @caption("处理方式") @column("csv_type")    @note(" 0:自己处理 1:协商委托 2:强制委托  ") Short csvType;// 非空 0:自己处理 1:协商委托 2:强制委托     
	private @caption("委托金额") @column("csv_price")    @note("  ") Double csvPrice;// 非空     
	private @caption("处理部门") @column("csv_branch")    @note(" 0:交警 1:城管 2:铁路 3:高速  ") Short csvBranch;// 0:交警 1:城管 2:铁路 3:高速     
	private @caption("寄送方式") @column("csv_post_type")    @note(" 0:邮寄 1:人工  ") Short csvPostType;// 0:邮寄 1:人工     
	private @caption("送寄地址") @column("csv_address")    @note("  ") String csvAddress;//     
	private @caption("备注") @column("csv_remark")    @note("  ") String csvRemark;//     
	private @caption("受理人员") @column("csv_accepter")    @relate("$csvAccepter") @RelateClass(SrvUser.class)  @note("  ") Long csvAccepter;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csvAccepter;//关联对象[用户]
	private @caption("添加人员") @column("csv_adder")    @relate("$csvAdder") @RelateClass(SrvUser.class)  @note("  ") Long csvAdder;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csvAdder;//关联对象[用户]
	private @caption("处理时间") @column("csv_update_time")    @note("  ") Date csvUpdateTime;//     
	private @caption("添加时间") @column("csv_add_time")    @note("  ") Date csvAddTime;// 非空     
	private @caption("受理标记") @column("csv_deal_flag")    @note(" 0:正常处理 1:会员不配合 2:交警不配合  ") Short csvDealFlag;// 0:正常处理 1:会员不配合 2:交警不配合     
	private @caption("查询次数") @column("csv_22")  @hidden   @note("  ") Integer csv22;//     
	private @caption("业务状态") @column("csv_status")    @note(" 0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询  ") Short csvStatus;// 非空 0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询     
	
	//默认构造函数
	public CsViolat(){
	
	}
	
	//主键构造函数
	public CsViolat(Long id){
		this.csvId = id;
	}
	
	/**所有字段构造函数 CsViolat(csvHost,csvOrder,csvMember,csvCar,csvMoney,csvScore,csvHappenTime,csvDecipt,csvNoticeTime,csvCutoffTime,csvType,csvPrice,csvBranch,csvPostType,csvAddress,csvRemark,csvAccepter,csvAdder,csvUpdateTime,csvAddTime,csvDealFlag,csv22,csvStatus)
	 CsViolat(
	 	$.getLong("csvHost")//城市 [非空]
	 	,$.getLong("csvOrder")//违章订单 [非空]
	 	,$.getLong("csvMember")//违章会员 [非空]
	 	,$.getLong("csvCar")//违章车辆 [非空]
	 	,$.getDouble("csvMoney")//违章罚款
	 	,$.getInteger("csvScore")//违章扣分
	 	,$.getDate("csvHappenTime")//发生时间 [非空]
	 	,$.getString("csvDecipt")//违章描述
	 	,$.getDate("csvNoticeTime")//通知时间
	 	,$.getDate("csvCutoffTime")//截止时间
	 	,$.getShort("csvType")//处理方式 [非空]
	 	,$.getDouble("csvPrice")//委托金额 [非空]
	 	,$.getShort("csvBranch")//处理部门
	 	,$.getShort("csvPostType")//寄送方式
	 	,$.getString("csvAddress")//送寄地址
	 	,$.getString("csvRemark")//备注
	 	,$.getLong("csvAccepter")//受理人员
	 	,$.getLong("csvAdder")//添加人员
	 	,$.getDate("csvUpdateTime")//处理时间
	 	,$.getDate("csvAddTime")//添加时间 [非空]
	 	,$.getShort("csvDealFlag")//受理标记
	 	,$.getInteger("csv22")//查询次数
	 	,$.getShort("csvStatus")//业务状态 [非空]
	 )
	**/
	public CsViolat(Long csvHost,Long csvOrder,Long csvMember,Long csvCar,Double csvMoney,Integer csvScore,Date csvHappenTime,String csvDecipt,Date csvNoticeTime,Date csvCutoffTime,Short csvType,Double csvPrice,Short csvBranch,Short csvPostType,String csvAddress,String csvRemark,Long csvAccepter,Long csvAdder,Date csvUpdateTime,Date csvAddTime,Short csvDealFlag,Integer csv22,Short csvStatus){
		this.csvHost=csvHost;
		this.csvOrder=csvOrder;
		this.csvMember=csvMember;
		this.csvCar=csvCar;
		this.csvMoney=csvMoney;
		this.csvScore=csvScore;
		this.csvHappenTime=csvHappenTime;
		this.csvDecipt=csvDecipt;
		this.csvNoticeTime=csvNoticeTime;
		this.csvCutoffTime=csvCutoffTime;
		this.csvType=csvType;
		this.csvPrice=csvPrice;
		this.csvBranch=csvBranch;
		this.csvPostType=csvPostType;
		this.csvAddress=csvAddress;
		this.csvRemark=csvRemark;
		this.csvAccepter=csvAccepter;
		this.csvAdder=csvAdder;
		this.csvUpdateTime=csvUpdateTime;
		this.csvAddTime=csvAddTime;
		this.csvDealFlag=csvDealFlag;
		this.csv22=csv22;
		this.csvStatus=csvStatus;
	}
	
	//设置非空字段
	public CsViolat setNotNull(Long csvHost,Long csvOrder,Long csvMember,Long csvCar,Date csvHappenTime,Short csvType,Double csvPrice,Date csvAddTime,Short csvStatus){
		this.csvHost=csvHost;
		this.csvOrder=csvOrder;
		this.csvMember=csvMember;
		this.csvCar=csvCar;
		this.csvHappenTime=csvHappenTime;
		this.csvType=csvType;
		this.csvPrice=csvPrice;
		this.csvAddTime=csvAddTime;
		this.csvStatus=csvStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsViolat csvId(Long csvId){
		this.csvId = csvId;
		this.setSeted(F.csvId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsViolat csvHost(Long csvHost){
		this.csvHost = csvHost;
		this.setSeted(F.csvHost);
		return this;
	}
	/** 违章订单 [非空] [CsOrder]      **/
	public CsViolat csvOrder(Long csvOrder){
		this.csvOrder = csvOrder;
		this.setSeted(F.csvOrder);
		return this;
	}
	/** 违章会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsViolat csvMember(Long csvMember){
		this.csvMember = csvMember;
		this.setSeted(F.csvMember);
		return this;
	}
	/** 违章车辆 [非空] [CsCar]      **/
	public CsViolat csvCar(Long csvCar){
		this.csvCar = csvCar;
		this.setSeted(F.csvCar);
		return this;
	}
	/** 违章罚款        **/
	public CsViolat csvMoney(Double csvMoney){
		this.csvMoney = csvMoney;
		this.setSeted(F.csvMoney);
		return this;
	}
	/** 违章扣分        **/
	public CsViolat csvScore(Integer csvScore){
		this.csvScore = csvScore;
		this.setSeted(F.csvScore);
		return this;
	}
	/** 发生时间 [非空]       **/
	public CsViolat csvHappenTime(Date csvHappenTime){
		this.csvHappenTime = csvHappenTime;
		this.setSeted(F.csvHappenTime);
		return this;
	}
	/** 违章描述        **/
	public CsViolat csvDecipt(String csvDecipt){
		this.csvDecipt = csvDecipt;
		this.setSeted(F.csvDecipt);
		return this;
	}
	/** 通知时间        **/
	public CsViolat csvNoticeTime(Date csvNoticeTime){
		this.csvNoticeTime = csvNoticeTime;
		this.setSeted(F.csvNoticeTime);
		return this;
	}
	/** 截止时间        **/
	public CsViolat csvCutoffTime(Date csvCutoffTime){
		this.csvCutoffTime = csvCutoffTime;
		this.setSeted(F.csvCutoffTime);
		return this;
	}
	/** 处理方式 [非空]   0:自己处理 1:协商委托 2:强制委托     **/
	public CsViolat csvType(Short csvType){
		this.csvType = csvType;
		this.setSeted(F.csvType);
		return this;
	}
	/** 委托金额 [非空]       **/
	public CsViolat csvPrice(Double csvPrice){
		this.csvPrice = csvPrice;
		this.setSeted(F.csvPrice);
		return this;
	}
	/** 处理部门    0:交警 1:城管 2:铁路 3:高速     **/
	public CsViolat csvBranch(Short csvBranch){
		this.csvBranch = csvBranch;
		this.setSeted(F.csvBranch);
		return this;
	}
	/** 寄送方式    0:邮寄 1:人工     **/
	public CsViolat csvPostType(Short csvPostType){
		this.csvPostType = csvPostType;
		this.setSeted(F.csvPostType);
		return this;
	}
	/** 送寄地址        **/
	public CsViolat csvAddress(String csvAddress){
		this.csvAddress = csvAddress;
		this.setSeted(F.csvAddress);
		return this;
	}
	/** 备注        **/
	public CsViolat csvRemark(String csvRemark){
		this.csvRemark = csvRemark;
		this.setSeted(F.csvRemark);
		return this;
	}
	/** 受理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsViolat csvAccepter(Long csvAccepter){
		this.csvAccepter = csvAccepter;
		this.setSeted(F.csvAccepter);
		return this;
	}
	/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsViolat csvAdder(Long csvAdder){
		this.csvAdder = csvAdder;
		this.setSeted(F.csvAdder);
		return this;
	}
	/** 处理时间        **/
	public CsViolat csvUpdateTime(Date csvUpdateTime){
		this.csvUpdateTime = csvUpdateTime;
		this.setSeted(F.csvUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsViolat csvAddTime(Date csvAddTime){
		this.csvAddTime = csvAddTime;
		this.setSeted(F.csvAddTime);
		return this;
	}
	/** 受理标记    0:正常处理 1:会员不配合 2:交警不配合     **/
	public CsViolat csvDealFlag(Short csvDealFlag){
		this.csvDealFlag = csvDealFlag;
		this.setSeted(F.csvDealFlag);
		return this;
	}
	/** 查询次数        **/
	public CsViolat csv22(Integer csv22){
		this.csv22 = csv22;
		this.setSeted(F.csv22);
		return this;
	}
	/** 业务状态 [非空]   0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询     **/
	public CsViolat csvStatus(Short csvStatus){
		this.csvStatus = csvStatus;
		this.setSeted(F.csvStatus);
		return this;
	}
	
	
	//克隆对象
	public CsViolat clone(){
		CsViolat clone = new CsViolat();
		clone.csvHost=this.csvHost;
		clone.csvOrder=this.csvOrder;
		clone.csvMember=this.csvMember;
		clone.csvCar=this.csvCar;
		clone.csvHappenTime=this.csvHappenTime;
		clone.csvType=this.csvType;
		clone.csvPrice=this.csvPrice;
		clone.csvAddTime=this.csvAddTime;
		clone.csvStatus=this.csvStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆违章
	 * @param id
	 * @return
	 */
	public static @api CsViolat get(Long id){		
		return getCsViolatById(id);
	}
	/**
	 * 获取所有车辆违章
	 * @return
	 */
	public static @api List<CsViolat> list(Map params,Integer size){
		return getCsViolatList(params,size);
	}
	/**
	 * 获取车辆违章分页
	 * @return
	 */
	public static @api Page<CsViolat> page(int page,int size,Map params){
		return getCsViolatPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆违章
	 * @param params
	 * @return
	 */
	public static @api CsViolat Get(Map params){
		return getCsViolat(params);
	}
	/**
	 * 获取车辆违章数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsViolatCount(params);
	}
	/**
	 * 获取车辆违章数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsViolatEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆违章
	 * @param id
	 * @return
	 */
	public static @api CsViolat getCsViolatById(Long id){		
		CsViolat csViolat = (CsViolat) $.GetRequest("CsViolat$"+id);
		if(csViolat!=null)
			return csViolat;
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");		
		return csViolatService.getCsViolatById(id);
	}
	
	
	/**
	 * 根据ID取车辆违章的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsViolat.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsViolat csViolat = get(id);
		if(csViolat!=null){
			String strValue = csViolat.getCsvId$();
			if(!"CsvId".equals("CsvId"))
				strValue+="("+csViolat.getCsvId$()+")";
			MemCache.setValue(CsViolat.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsvId$();
		if(!"CsvId".equals("CsvId"))
			keyValue+="("+this.getCsvId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆违章
	 * @return
	 */
	public static @api List<CsViolat> getCsViolatList(Map params,Integer size){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		return csViolatService.getCsViolatList(params, size);
	}
	
	/**
	 * 获取车辆违章分页
	 * @return
	 */
	public static @api Page<CsViolat> getCsViolatPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		return csViolatService.getCsViolatPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆违章
	 * @param params
	 * @return
	 */
	public static @api CsViolat getCsViolat(Map params){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		return csViolatService.getCsViolat(params);
	}
	
	/**
	 * 获取车辆违章数
	 * @return
	 */
	public static @api Long getCsViolatCount(Map params){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		return csViolatService.getCsViolatCount(params);
	}
		
		
	/**
	 * 获取车辆违章自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsViolatEval(String eval,Map params){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		return csViolatService.getCsViolatEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsViolat(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		csViolatService.updateCsViolatByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆违章对象
	 * @param params
	 * @return
	 */
	public CsViolat save(){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		if(this.getCsvId()!=null)
			csViolatService.updateCsViolat(this);
		else
			return csViolatService.saveCsViolat(this);
		return this;
	}
	
	
	/**
	 * 更新车辆违章对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		csViolatService.updateCsViolat$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		if($.equals($.config("logic_delete"),"true"))
			csViolatService.removeCsViolatById(this.getCsvId());
		else
			csViolatService.deleteCsViolatById(this.getCsvId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		return csViolatService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsvId(){
		return this.csvId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsvId$(){
		String strValue="";
		 strValue=$.str(this.getCsvId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsvId(Long csvId){
		this.csvId = csvId;
		this.setSeted(F.csvId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsvHost(){
		return this.csvHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsvHost$(){
		String strValue="";
		if(this.getCsvHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsvHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsvHost(Long csvHost){
		this.csvHost = csvHost;
		this.setSeted(F.csvHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csvHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsvHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsvHost()!=null){
 			srvHost = SrvHost.get(this.getCsvHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsvHost(), srvHost);
	 	return srvHost;
	}
	/*******************************违章订单**********************************/	
	/**
	* 违章订单 [非空] [CsOrder]     
	**/
	public Long getCsvOrder(){
		return this.csvOrder;
	}
	/**
	* 获取违章订单格式化(toString)
	**/
	public String getCsvOrder$(){
		String strValue="";
		if(this.getCsvOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsvOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 违章订单 [非空] [CsOrder]     
	**/
	public void setCsvOrder(Long csvOrder){
		this.csvOrder = csvOrder;
		this.setSeted(F.csvOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csvOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsvOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsvOrder()!=null){
 			csOrder = CsOrder.get(this.getCsvOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsvOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************违章会员**********************************/	
	/**
	* 违章会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsvMember(){
		return this.csvMember;
	}
	/**
	* 获取违章会员格式化(toString)
	**/
	public String getCsvMember$(){
		String strValue="";
		if(this.getCsvMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsvMember()));
		}			
	 	 return strValue;
	}
	/**
	* 违章会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsvMember(Long csvMember){
		this.csvMember = csvMember;
		this.setSeted(F.csvMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csvMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsvMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsvMember()!=null){
 			csMember = CsMember.get(this.getCsvMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsvMember(), csMember);
	 	return csMember;
	}
	/*******************************违章车辆**********************************/	
	/**
	* 违章车辆 [非空] [CsCar]     
	**/
	public Long getCsvCar(){
		return this.csvCar;
	}
	/**
	* 获取违章车辆格式化(toString)
	**/
	public String getCsvCar$(){
		String strValue="";
		if(this.getCsvCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsvCar()));
		}			
	 	 return strValue;
	}
	/**
	* 违章车辆 [非空] [CsCar]     
	**/
	public void setCsvCar(Long csvCar){
		this.csvCar = csvCar;
		this.setSeted(F.csvCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$csvCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsvCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsvCar()!=null){
 			csCar = CsCar.get(this.getCsvCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsvCar(), csCar);
	 	return csCar;
	}
	/*******************************违章罚款**********************************/	
	/**
	* 违章罚款       
	**/
	public Double getCsvMoney(){
		return this.csvMoney;
	}
	/**
	* 获取违章罚款格式化(toString)
	**/
	public String getCsvMoney$(){
		String strValue="";
		 strValue=$.str(this.getCsvMoney());	
	 	 return strValue;
	}
	/**
	* 违章罚款       
	**/
	public void setCsvMoney(Double csvMoney){
		this.csvMoney = csvMoney;
		this.setSeted(F.csvMoney);
	}
	/*******************************违章扣分**********************************/	
	/**
	* 违章扣分       
	**/
	public Integer getCsvScore(){
		return this.csvScore;
	}
	/**
	* 获取违章扣分格式化(toString)
	**/
	public String getCsvScore$(){
		String strValue="";
		 strValue=$.str(this.getCsvScore());
	 	 return strValue;
	}
	/**
	* 违章扣分       
	**/
	public void setCsvScore(Integer csvScore){
		this.csvScore = csvScore;
		this.setSeted(F.csvScore);
	}
	/*******************************发生时间**********************************/	
	/**
	* 发生时间 [非空]      
	**/
	public Date getCsvHappenTime(){
		return this.csvHappenTime;
	}
	/**
	* 获取发生时间格式化(toString)
	**/
	public String getCsvHappenTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsvHappenTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 发生时间 [非空]      
	**/
	public void setCsvHappenTime(Date csvHappenTime){
		this.csvHappenTime = csvHappenTime;
		this.setSeted(F.csvHappenTime);
	}
	/*******************************违章描述**********************************/	
	/**
	* 违章描述       
	**/
	public String getCsvDecipt(){
		return this.csvDecipt;
	}
	/**
	* 获取违章描述格式化(toString)
	**/
	public String getCsvDecipt$(){
		String strValue="";
		 strValue=$.str(this.getCsvDecipt());
	 	 return strValue;
	}
	/**
	* 违章描述       
	**/
	public void setCsvDecipt(String csvDecipt){
		this.csvDecipt = csvDecipt;
		this.setSeted(F.csvDecipt);
	}
	/*******************************通知时间**********************************/	
	/**
	* 通知时间       
	**/
	public Date getCsvNoticeTime(){
		return this.csvNoticeTime;
	}
	/**
	* 获取通知时间格式化(toString)
	**/
	public String getCsvNoticeTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsvNoticeTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 通知时间       
	**/
	public void setCsvNoticeTime(Date csvNoticeTime){
		this.csvNoticeTime = csvNoticeTime;
		this.setSeted(F.csvNoticeTime);
	}
	/*******************************截止时间**********************************/	
	/**
	* 截止时间       
	**/
	public Date getCsvCutoffTime(){
		return this.csvCutoffTime;
	}
	/**
	* 获取截止时间格式化(toString)
	**/
	public String getCsvCutoffTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsvCutoffTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 截止时间       
	**/
	public void setCsvCutoffTime(Date csvCutoffTime){
		this.csvCutoffTime = csvCutoffTime;
		this.setSeted(F.csvCutoffTime);
	}
	/*******************************处理方式**********************************/	
	/**
	* 处理方式 [非空]   0:自己处理 1:协商委托 2:强制委托    
	**/
	public Short getCsvType(){
		return this.csvType;
	}
	/**
	* 获取处理方式格式化(toString)
	**/
	public String getCsvType$(){
		String strValue="";
		 if($.equals($.str(this.getCsvType()),"0"))
			strValue=$.str("自己处理");		 
		 if($.equals($.str(this.getCsvType()),"1"))
			strValue=$.str("协商委托");		 
		 if($.equals($.str(this.getCsvType()),"2"))
			strValue=$.str("强制委托");		 
	 	 return strValue;
	}
	/**
	* 处理方式 [非空]   0:自己处理 1:协商委托 2:强制委托    
	**/
	public void setCsvType(Short csvType){
		this.csvType = csvType;
		this.setSeted(F.csvType);
	}
	/*******************************委托金额**********************************/	
	/**
	* 委托金额 [非空]      
	**/
	public Double getCsvPrice(){
		return this.csvPrice;
	}
	/**
	* 获取委托金额格式化(toString)
	**/
	public String getCsvPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsvPrice());	
	 	 return strValue;
	}
	/**
	* 委托金额 [非空]      
	**/
	public void setCsvPrice(Double csvPrice){
		this.csvPrice = csvPrice;
		this.setSeted(F.csvPrice);
	}
	/*******************************处理部门**********************************/	
	/**
	* 处理部门    0:交警 1:城管 2:铁路 3:高速    
	**/
	public Short getCsvBranch(){
		return this.csvBranch;
	}
	/**
	* 获取处理部门格式化(toString)
	**/
	public String getCsvBranch$(){
		String strValue="";
		 if($.equals($.str(this.getCsvBranch()),"0"))
			strValue=$.str("交警");		 
		 if($.equals($.str(this.getCsvBranch()),"1"))
			strValue=$.str("城管");		 
		 if($.equals($.str(this.getCsvBranch()),"2"))
			strValue=$.str("铁路");		 
		 if($.equals($.str(this.getCsvBranch()),"3"))
			strValue=$.str("高速");		 
	 	 return strValue;
	}
	/**
	* 处理部门    0:交警 1:城管 2:铁路 3:高速    
	**/
	public void setCsvBranch(Short csvBranch){
		this.csvBranch = csvBranch;
		this.setSeted(F.csvBranch);
	}
	/*******************************寄送方式**********************************/	
	/**
	* 寄送方式    0:邮寄 1:人工    
	**/
	public Short getCsvPostType(){
		return this.csvPostType;
	}
	/**
	* 获取寄送方式格式化(toString)
	**/
	public String getCsvPostType$(){
		String strValue="";
		 if($.equals($.str(this.getCsvPostType()),"0"))
			strValue=$.str("邮寄");		 
		 if($.equals($.str(this.getCsvPostType()),"1"))
			strValue=$.str("人工");		 
	 	 return strValue;
	}
	/**
	* 寄送方式    0:邮寄 1:人工    
	**/
	public void setCsvPostType(Short csvPostType){
		this.csvPostType = csvPostType;
		this.setSeted(F.csvPostType);
	}
	/*******************************送寄地址**********************************/	
	/**
	* 送寄地址       
	**/
	public String getCsvAddress(){
		return this.csvAddress;
	}
	/**
	* 获取送寄地址格式化(toString)
	**/
	public String getCsvAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsvAddress());
	 	 return strValue;
	}
	/**
	* 送寄地址       
	**/
	public void setCsvAddress(String csvAddress){
		this.csvAddress = csvAddress;
		this.setSeted(F.csvAddress);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsvRemark(){
		return this.csvRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsvRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsvRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsvRemark(String csvRemark){
		this.csvRemark = csvRemark;
		this.setSeted(F.csvRemark);
	}
	/*******************************受理人员**********************************/	
	/**
	* 受理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsvAccepter(){
		return this.csvAccepter;
	}
	/**
	* 获取受理人员格式化(toString)
	**/
	public String getCsvAccepter$(){
		String strValue="";
		if(this.getCsvAccepter()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsvAccepter()));
		}			
	 	 return strValue;
	}
	/**
	* 受理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsvAccepter(Long csvAccepter){
		this.csvAccepter = csvAccepter;
		this.setSeted(F.csvAccepter);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csvAccepter(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsvAccepter());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsvAccepter()!=null){
 			srvUser = SrvUser.get(this.getCsvAccepter());
 		}
 		$.SetRequest("SrvUser$"+this.getCsvAccepter(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人员**********************************/	
	/**
	* 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsvAdder(){
		return this.csvAdder;
	}
	/**
	* 获取添加人员格式化(toString)
	**/
	public String getCsvAdder$(){
		String strValue="";
		if(this.getCsvAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsvAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsvAdder(Long csvAdder){
		this.csvAdder = csvAdder;
		this.setSeted(F.csvAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csvAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsvAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsvAdder()!=null){
 			srvUser = SrvUser.get(this.getCsvAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsvAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************处理时间**********************************/	
	/**
	* 处理时间       
	**/
	public Date getCsvUpdateTime(){
		return this.csvUpdateTime;
	}
	/**
	* 获取处理时间格式化(toString)
	**/
	public String getCsvUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsvUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 处理时间       
	**/
	public void setCsvUpdateTime(Date csvUpdateTime){
		this.csvUpdateTime = csvUpdateTime;
		this.setSeted(F.csvUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsvAddTime(){
		return this.csvAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsvAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsvAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsvAddTime(Date csvAddTime){
		this.csvAddTime = csvAddTime;
		this.setSeted(F.csvAddTime);
	}
	/*******************************受理标记**********************************/	
	/**
	* 受理标记    0:正常处理 1:会员不配合 2:交警不配合    
	**/
	public Short getCsvDealFlag(){
		return this.csvDealFlag;
	}
	/**
	* 获取受理标记格式化(toString)
	**/
	public String getCsvDealFlag$(){
		String strValue="";
		 if($.equals($.str(this.getCsvDealFlag()),"0"))
			strValue=$.str("正常处理");		 
		 if($.equals($.str(this.getCsvDealFlag()),"1"))
			strValue=$.str("会员不配合");		 
		 if($.equals($.str(this.getCsvDealFlag()),"2"))
			strValue=$.str("交警不配合");		 
	 	 return strValue;
	}
	/**
	* 受理标记    0:正常处理 1:会员不配合 2:交警不配合    
	**/
	public void setCsvDealFlag(Short csvDealFlag){
		this.csvDealFlag = csvDealFlag;
		this.setSeted(F.csvDealFlag);
	}
	/*******************************查询次数**********************************/	
	/**
	* 查询次数       
	**/
	public Integer getCsv22(){
		return this.csv22;
	}
	/**
	* 获取查询次数格式化(toString)
	**/
	public String getCsv22$(){
		String strValue="";
		 strValue=$.str(this.getCsv22());
	 	 return strValue;
	}
	/**
	* 查询次数       
	**/
	public void setCsv22(Integer csv22){
		this.csv22 = csv22;
		this.setSeted(F.csv22);
	}
	/*******************************业务状态**********************************/	
	/**
	* 业务状态 [非空]   0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询    
	**/
	public Short getCsvStatus(){
		return this.csvStatus;
	}
	/**
	* 获取业务状态格式化(toString)
	**/
	public String getCsvStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsvStatus()),"0"))
			strValue=$.str("未交款，未处理");		 
		 if($.equals($.str(this.getCsvStatus()),"1"))
			strValue=$.str("未交款，已处理");		 
		 if($.equals($.str(this.getCsvStatus()),"2"))
			strValue=$.str("已交款，已处理");		 
		 if($.equals($.str(this.getCsvStatus()),"3"))
			strValue=$.str("等待查询");		 
	 	 return strValue;
	}
	/**
	* 业务状态 [非空]   0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询    
	**/
	public void setCsvStatus(Short csvStatus){
		this.csvStatus = csvStatus;
		this.setSeted(F.csvStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsViolat.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsViolat.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsViolat.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsViolat.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsViolat.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsViolat.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsViolat.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsViolat.this);
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
		public M csvId(Object csvId){this.put("csvId", csvId);return this;};
	 	/** and csv_id is null */
 		public M csvIdNull(){if(this.get("csvIdNot")==null)this.put("csvIdNot", "");this.put("csvId", null);return this;};
 		/** not .... */
 		public M csvIdNot(){this.put("csvIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csvHost(Object csvHost){this.put("csvHost", csvHost);return this;};
	 	/** and csv_host is null */
 		public M csvHostNull(){if(this.get("csvHostNot")==null)this.put("csvHostNot", "");this.put("csvHost", null);return this;};
 		/** not .... */
 		public M csvHostNot(){this.put("csvHostNot", "not");return this;};
		/** 违章订单 [非空] [CsOrder]      **/
		public M csvOrder(Object csvOrder){this.put("csvOrder", csvOrder);return this;};
	 	/** and csv_order is null */
 		public M csvOrderNull(){if(this.get("csvOrderNot")==null)this.put("csvOrderNot", "");this.put("csvOrder", null);return this;};
 		/** not .... */
 		public M csvOrderNot(){this.put("csvOrderNot", "not");return this;};
		public M csvOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csvOrder$on", value);
			return this;
		};	
		/** 违章会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csvMember(Object csvMember){this.put("csvMember", csvMember);return this;};
	 	/** and csv_member is null */
 		public M csvMemberNull(){if(this.get("csvMemberNot")==null)this.put("csvMemberNot", "");this.put("csvMember", null);return this;};
 		/** not .... */
 		public M csvMemberNot(){this.put("csvMemberNot", "not");return this;};
		public M csvMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csvMember$on", value);
			return this;
		};	
		/** 违章车辆 [非空] [CsCar]      **/
		public M csvCar(Object csvCar){this.put("csvCar", csvCar);return this;};
	 	/** and csv_car is null */
 		public M csvCarNull(){if(this.get("csvCarNot")==null)this.put("csvCarNot", "");this.put("csvCar", null);return this;};
 		/** not .... */
 		public M csvCarNot(){this.put("csvCarNot", "not");return this;};
		public M csvCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csvCar$on", value);
			return this;
		};	
		/** 违章罚款        **/
		public M csvMoney(Object csvMoney){this.put("csvMoney", csvMoney);return this;};
	 	/** and csv_money is null */
 		public M csvMoneyNull(){if(this.get("csvMoneyNot")==null)this.put("csvMoneyNot", "");this.put("csvMoney", null);return this;};
 		/** not .... */
 		public M csvMoneyNot(){this.put("csvMoneyNot", "not");return this;};
		/** and csv_money >= ? */
		public M csvMoneyMin(Object min){this.put("csvMoneyMin", min);return this;};
		/** and csv_money <= ? */
		public M csvMoneyMax(Object max){this.put("csvMoneyMax", max);return this;};
		/** 违章扣分        **/
		public M csvScore(Object csvScore){this.put("csvScore", csvScore);return this;};
	 	/** and csv_score is null */
 		public M csvScoreNull(){if(this.get("csvScoreNot")==null)this.put("csvScoreNot", "");this.put("csvScore", null);return this;};
 		/** not .... */
 		public M csvScoreNot(){this.put("csvScoreNot", "not");return this;};
		/** and csv_score >= ? */
		public M csvScoreMin(Object min){this.put("csvScoreMin", min);return this;};
		/** and csv_score <= ? */
		public M csvScoreMax(Object max){this.put("csvScoreMax", max);return this;};
		/** 发生时间 [非空]       **/
		public M csvHappenTime(Object csvHappenTime){this.put("csvHappenTime", csvHappenTime);return this;};
	 	/** and csv_happen_time is null */
 		public M csvHappenTimeNull(){if(this.get("csvHappenTimeNot")==null)this.put("csvHappenTimeNot", "");this.put("csvHappenTime", null);return this;};
 		/** not .... */
 		public M csvHappenTimeNot(){this.put("csvHappenTimeNot", "not");return this;};
 		/** and csv_happen_time >= ? */
 		public M csvHappenTimeStart(Object start){this.put("csvHappenTimeStart", start);return this;};			
 		/** and csv_happen_time <= ? */
 		public M csvHappenTimeEnd(Object end){this.put("csvHappenTimeEnd", end);return this;};
		/** 违章描述        **/
		public M csvDecipt(Object csvDecipt){this.put("csvDecipt", csvDecipt);return this;};
	 	/** and csv_decipt is null */
 		public M csvDeciptNull(){if(this.get("csvDeciptNot")==null)this.put("csvDeciptNot", "");this.put("csvDecipt", null);return this;};
 		/** not .... */
 		public M csvDeciptNot(){this.put("csvDeciptNot", "not");return this;};
		/** 通知时间        **/
		public M csvNoticeTime(Object csvNoticeTime){this.put("csvNoticeTime", csvNoticeTime);return this;};
	 	/** and csv_notice_time is null */
 		public M csvNoticeTimeNull(){if(this.get("csvNoticeTimeNot")==null)this.put("csvNoticeTimeNot", "");this.put("csvNoticeTime", null);return this;};
 		/** not .... */
 		public M csvNoticeTimeNot(){this.put("csvNoticeTimeNot", "not");return this;};
 		/** and csv_notice_time >= ? */
 		public M csvNoticeTimeStart(Object start){this.put("csvNoticeTimeStart", start);return this;};			
 		/** and csv_notice_time <= ? */
 		public M csvNoticeTimeEnd(Object end){this.put("csvNoticeTimeEnd", end);return this;};
		/** 截止时间        **/
		public M csvCutoffTime(Object csvCutoffTime){this.put("csvCutoffTime", csvCutoffTime);return this;};
	 	/** and csv_cutoff_time is null */
 		public M csvCutoffTimeNull(){if(this.get("csvCutoffTimeNot")==null)this.put("csvCutoffTimeNot", "");this.put("csvCutoffTime", null);return this;};
 		/** not .... */
 		public M csvCutoffTimeNot(){this.put("csvCutoffTimeNot", "not");return this;};
 		/** and csv_cutoff_time >= ? */
 		public M csvCutoffTimeStart(Object start){this.put("csvCutoffTimeStart", start);return this;};			
 		/** and csv_cutoff_time <= ? */
 		public M csvCutoffTimeEnd(Object end){this.put("csvCutoffTimeEnd", end);return this;};
		/** 处理方式 [非空]   0:自己处理 1:协商委托 2:强制委托     **/
		public M csvType(Object csvType){this.put("csvType", csvType);return this;};
	 	/** and csv_type is null */
 		public M csvTypeNull(){if(this.get("csvTypeNot")==null)this.put("csvTypeNot", "");this.put("csvType", null);return this;};
 		/** not .... */
 		public M csvTypeNot(){this.put("csvTypeNot", "not");return this;};
		/** 委托金额 [非空]       **/
		public M csvPrice(Object csvPrice){this.put("csvPrice", csvPrice);return this;};
	 	/** and csv_price is null */
 		public M csvPriceNull(){if(this.get("csvPriceNot")==null)this.put("csvPriceNot", "");this.put("csvPrice", null);return this;};
 		/** not .... */
 		public M csvPriceNot(){this.put("csvPriceNot", "not");return this;};
		/** and csv_price >= ? */
		public M csvPriceMin(Object min){this.put("csvPriceMin", min);return this;};
		/** and csv_price <= ? */
		public M csvPriceMax(Object max){this.put("csvPriceMax", max);return this;};
		/** 处理部门    0:交警 1:城管 2:铁路 3:高速     **/
		public M csvBranch(Object csvBranch){this.put("csvBranch", csvBranch);return this;};
	 	/** and csv_branch is null */
 		public M csvBranchNull(){if(this.get("csvBranchNot")==null)this.put("csvBranchNot", "");this.put("csvBranch", null);return this;};
 		/** not .... */
 		public M csvBranchNot(){this.put("csvBranchNot", "not");return this;};
		/** 寄送方式    0:邮寄 1:人工     **/
		public M csvPostType(Object csvPostType){this.put("csvPostType", csvPostType);return this;};
	 	/** and csv_post_type is null */
 		public M csvPostTypeNull(){if(this.get("csvPostTypeNot")==null)this.put("csvPostTypeNot", "");this.put("csvPostType", null);return this;};
 		/** not .... */
 		public M csvPostTypeNot(){this.put("csvPostTypeNot", "not");return this;};
		/** 送寄地址        **/
		public M csvAddress(Object csvAddress){this.put("csvAddress", csvAddress);return this;};
	 	/** and csv_address is null */
 		public M csvAddressNull(){if(this.get("csvAddressNot")==null)this.put("csvAddressNot", "");this.put("csvAddress", null);return this;};
 		/** not .... */
 		public M csvAddressNot(){this.put("csvAddressNot", "not");return this;};
		/** 备注        **/
		public M csvRemark(Object csvRemark){this.put("csvRemark", csvRemark);return this;};
	 	/** and csv_remark is null */
 		public M csvRemarkNull(){if(this.get("csvRemarkNot")==null)this.put("csvRemarkNot", "");this.put("csvRemark", null);return this;};
 		/** not .... */
 		public M csvRemarkNot(){this.put("csvRemarkNot", "not");return this;};
		/** 受理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csvAccepter(Object csvAccepter){this.put("csvAccepter", csvAccepter);return this;};
	 	/** and csv_accepter is null */
 		public M csvAccepterNull(){if(this.get("csvAccepterNot")==null)this.put("csvAccepterNot", "");this.put("csvAccepter", null);return this;};
 		/** not .... */
 		public M csvAccepterNot(){this.put("csvAccepterNot", "not");return this;};
		/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csvAdder(Object csvAdder){this.put("csvAdder", csvAdder);return this;};
	 	/** and csv_adder is null */
 		public M csvAdderNull(){if(this.get("csvAdderNot")==null)this.put("csvAdderNot", "");this.put("csvAdder", null);return this;};
 		/** not .... */
 		public M csvAdderNot(){this.put("csvAdderNot", "not");return this;};
		/** 处理时间        **/
		public M csvUpdateTime(Object csvUpdateTime){this.put("csvUpdateTime", csvUpdateTime);return this;};
	 	/** and csv_update_time is null */
 		public M csvUpdateTimeNull(){if(this.get("csvUpdateTimeNot")==null)this.put("csvUpdateTimeNot", "");this.put("csvUpdateTime", null);return this;};
 		/** not .... */
 		public M csvUpdateTimeNot(){this.put("csvUpdateTimeNot", "not");return this;};
 		/** and csv_update_time >= ? */
 		public M csvUpdateTimeStart(Object start){this.put("csvUpdateTimeStart", start);return this;};			
 		/** and csv_update_time <= ? */
 		public M csvUpdateTimeEnd(Object end){this.put("csvUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csvAddTime(Object csvAddTime){this.put("csvAddTime", csvAddTime);return this;};
	 	/** and csv_add_time is null */
 		public M csvAddTimeNull(){if(this.get("csvAddTimeNot")==null)this.put("csvAddTimeNot", "");this.put("csvAddTime", null);return this;};
 		/** not .... */
 		public M csvAddTimeNot(){this.put("csvAddTimeNot", "not");return this;};
 		/** and csv_add_time >= ? */
 		public M csvAddTimeStart(Object start){this.put("csvAddTimeStart", start);return this;};			
 		/** and csv_add_time <= ? */
 		public M csvAddTimeEnd(Object end){this.put("csvAddTimeEnd", end);return this;};
		/** 受理标记    0:正常处理 1:会员不配合 2:交警不配合     **/
		public M csvDealFlag(Object csvDealFlag){this.put("csvDealFlag", csvDealFlag);return this;};
	 	/** and csv_deal_flag is null */
 		public M csvDealFlagNull(){if(this.get("csvDealFlagNot")==null)this.put("csvDealFlagNot", "");this.put("csvDealFlag", null);return this;};
 		/** not .... */
 		public M csvDealFlagNot(){this.put("csvDealFlagNot", "not");return this;};
		/** 查询次数        **/
		public M csv22(Object csv22){this.put("csv22", csv22);return this;};
	 	/** and csv_22 is null */
 		public M csv22Null(){if(this.get("csv22Not")==null)this.put("csv22Not", "");this.put("csv22", null);return this;};
 		/** not .... */
 		public M csv22Not(){this.put("csv22Not", "not");return this;};
		/** and csv_22 >= ? */
		public M csv22Min(Object min){this.put("csv22Min", min);return this;};
		/** and csv_22 <= ? */
		public M csv22Max(Object max){this.put("csv22Max", max);return this;};
		/** 业务状态 [非空]   0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询     **/
		public M csvStatus(Object csvStatus){this.put("csvStatus", csvStatus);return this;};
	 	/** and csv_status is null */
 		public M csvStatusNull(){if(this.get("csvStatusNot")==null)this.put("csvStatusNot", "");this.put("csvStatus", null);return this;};
 		/** not .... */
 		public M csvStatusNot(){this.put("csvStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆违章 **/
		public @api List<CsViolat> list(Integer size){
			return getCsViolatList(this,size);
		}
		/** 获取车辆违章分页 **/
		public @api Page<CsViolat> page(int page,int size){
			return getCsViolatPage(page, size , this);
		}
		/** 根据查询条件取车辆违章 **/
		public @api CsViolat get(){
			return getCsViolat(this);
		}
		/** 获取车辆违章数 **/
		public @api Long count(){
			return getCsViolatCount(this);
		}
		/** 获取车辆违章表达式 **/
		public @api <T> T eval(String strEval){
			return getCsViolatEval(strEval,this);
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
			updateCsViolat(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csvId="csvId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csvHost="csvHost";
		/** 违章订单 [非空] [CsOrder]      **/
		public final static @type(Long.class)  String csvOrder="csvOrder";
		/** 违章会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csvMember="csvMember";
		/** 违章车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String csvCar="csvCar";
		/** 违章罚款        **/
		public final static @type(Double.class)  String csvMoney="csvMoney";
		/** and csv_money >= ? */
		public final static @type(Double.class) String csvMoneyMin="csvMoneyMin";
		/** and csv_money <= ? */
		public final static @type(Double.class) String csvMoneyMax="csvMoneyMax";
		/** 违章扣分        **/
		public final static @type(Integer.class)  String csvScore="csvScore";
		/** and csv_score >= ? */
		public final static @type(Integer.class) String csvScoreMin="csvScoreMin";
		/** and csv_score <= ? */
		public final static @type(Integer.class) String csvScoreMax="csvScoreMax";
		/** 发生时间 [非空]       **/
		public final static @type(Date.class)  String csvHappenTime="csvHappenTime";
	 	/** and csv_happen_time >= ? */
 		public final static @type(Date.class) String csvHappenTimeStart="csvHappenTimeStart";
 		/** and csv_happen_time <= ? */
 		public final static @type(Date.class) String csvHappenTimeEnd="csvHappenTimeEnd";
		/** 违章描述        **/
		public final static @type(String.class) @like String csvDecipt="csvDecipt";
		/** 通知时间        **/
		public final static @type(Date.class)  String csvNoticeTime="csvNoticeTime";
	 	/** and csv_notice_time >= ? */
 		public final static @type(Date.class) String csvNoticeTimeStart="csvNoticeTimeStart";
 		/** and csv_notice_time <= ? */
 		public final static @type(Date.class) String csvNoticeTimeEnd="csvNoticeTimeEnd";
		/** 截止时间        **/
		public final static @type(Date.class)  String csvCutoffTime="csvCutoffTime";
	 	/** and csv_cutoff_time >= ? */
 		public final static @type(Date.class) String csvCutoffTimeStart="csvCutoffTimeStart";
 		/** and csv_cutoff_time <= ? */
 		public final static @type(Date.class) String csvCutoffTimeEnd="csvCutoffTimeEnd";
		/** 处理方式 [非空]   0:自己处理 1:协商委托 2:强制委托     **/
		public final static @type(Short.class)  String csvType="csvType";
		/** 委托金额 [非空]       **/
		public final static @type(Double.class)  String csvPrice="csvPrice";
		/** and csv_price >= ? */
		public final static @type(Double.class) String csvPriceMin="csvPriceMin";
		/** and csv_price <= ? */
		public final static @type(Double.class) String csvPriceMax="csvPriceMax";
		/** 处理部门    0:交警 1:城管 2:铁路 3:高速     **/
		public final static @type(Short.class)  String csvBranch="csvBranch";
		/** 寄送方式    0:邮寄 1:人工     **/
		public final static @type(Short.class)  String csvPostType="csvPostType";
		/** 送寄地址        **/
		public final static @type(String.class) @like String csvAddress="csvAddress";
		/** 备注        **/
		public final static @type(String.class) @like String csvRemark="csvRemark";
		/** 受理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csvAccepter="csvAccepter";
		/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csvAdder="csvAdder";
		/** 处理时间        **/
		public final static @type(Date.class)  String csvUpdateTime="csvUpdateTime";
	 	/** and csv_update_time >= ? */
 		public final static @type(Date.class) String csvUpdateTimeStart="csvUpdateTimeStart";
 		/** and csv_update_time <= ? */
 		public final static @type(Date.class) String csvUpdateTimeEnd="csvUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csvAddTime="csvAddTime";
	 	/** and csv_add_time >= ? */
 		public final static @type(Date.class) String csvAddTimeStart="csvAddTimeStart";
 		/** and csv_add_time <= ? */
 		public final static @type(Date.class) String csvAddTimeEnd="csvAddTimeEnd";
		/** 受理标记    0:正常处理 1:会员不配合 2:交警不配合     **/
		public final static @type(Short.class)  String csvDealFlag="csvDealFlag";
		/** 查询次数        **/
		public final static @type(Integer.class)  String csv22="csv22";
		/** and csv_22 >= ? */
		public final static @type(Integer.class) String csv22Min="csv22Min";
		/** and csv_22 <= ? */
		public final static @type(Integer.class) String csv22Max="csv22Max";
		/** 业务状态 [非空]   0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询     **/
		public final static @type(Short.class)  String csvStatus="csvStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csvId="csv_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csvHost="csv_host";
		/** 违章订单 [非空] [CsOrder]      **/
		public final static String csvOrder="csv_order";
		/** 违章会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csvMember="csv_member";
		/** 违章车辆 [非空] [CsCar]      **/
		public final static String csvCar="csv_car";
		/** 违章罚款        **/
		public final static String csvMoney="csv_money";
		/** 违章扣分        **/
		public final static String csvScore="csv_score";
		/** 发生时间 [非空]       **/
		public final static String csvHappenTime="csv_happen_time";
		/** 违章描述        **/
		public final static String csvDecipt="csv_decipt";
		/** 通知时间        **/
		public final static String csvNoticeTime="csv_notice_time";
		/** 截止时间        **/
		public final static String csvCutoffTime="csv_cutoff_time";
		/** 处理方式 [非空]   0:自己处理 1:协商委托 2:强制委托     **/
		public final static String csvType="csv_type";
		/** 委托金额 [非空]       **/
		public final static String csvPrice="csv_price";
		/** 处理部门    0:交警 1:城管 2:铁路 3:高速     **/
		public final static String csvBranch="csv_branch";
		/** 寄送方式    0:邮寄 1:人工     **/
		public final static String csvPostType="csv_post_type";
		/** 送寄地址        **/
		public final static String csvAddress="csv_address";
		/** 备注        **/
		public final static String csvRemark="csv_remark";
		/** 受理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csvAccepter="csv_accepter";
		/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csvAdder="csv_adder";
		/** 处理时间        **/
		public final static String csvUpdateTime="csv_update_time";
		/** 添加时间 [非空]       **/
		public final static String csvAddTime="csv_add_time";
		/** 受理标记    0:正常处理 1:会员不配合 2:交警不配合     **/
		public final static String csvDealFlag="csv_deal_flag";
		/** 查询次数        **/
		public final static String csv22="csv_22";
		/** 业务状态 [非空]   0:未交款，未处理 1:未交款，已处理 2:已交款，已处理 3:等待查询     **/
		public final static String csvStatus="csv_status";
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
				$.Set(name,CsViolat.getCsViolat(params));
			else
				$.Set(name,CsViolat.getCsViolatList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆违章数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsViolat) $.GetRequest("CsViolat$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsViolat.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsViolat.getCsViolat(params);
			else
				value = CsViolat.getCsViolatList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsViolat.Get($.add(CsViolat.F.csvId,param));
		else if(!$.empty(param.toString()))
			value = CsViolat.get(Long.valueOf(param.toString()));
		$.SetRequest("CsViolat$"+param.hashCode(), value);
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
	public void mergeSet(CsViolat old){
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