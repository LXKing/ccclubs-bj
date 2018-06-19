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

@namespace("operate/lucky/luckyresult")
public @caption("中奖结果") @table("cs_lucky_result") class CsLuckyResult implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cslr_id")   @primary  @note("  ") Long cslrId;// 主键 非空     
	private @caption("中奖标题") @column("cslr_name")    @note("  ") String cslrName;// 非空     
	private @caption("中奖会员") @column("cslr_member")    @relate("$cslrMember") @RelateClass(CsMember.class)  @note("  ") Long cslrMember;// 非空     
 	private CsMember $cslrMember;//关联对象[会员帐号]
	private @caption("所属活动") @column("cslr_lucky")    @relate("$cslrLucky") @RelateClass(CsLucky.class)  @note("  ") Long cslrLucky;// 非空     
 	private CsLucky $cslrLucky;//关联对象[抽奖活动]
	private @caption("所属奖项") @column("cslr_lucky_item")    @relate("$cslrLuckyItem") @RelateClass(CsLuckyItem.class)  @note("  ") Long cslrLuckyItem;// 非空     
 	private CsLuckyItem $cslrLuckyItem;//关联对象[抽奖奖项]
	private @caption("奖项类型") @column("cslr_item_type")    @note(" 0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放") Short cslrItemType;// 非空 0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放   
	private @caption("奖项等级") @column("cslr_item_index")    @note("  技术参数，用来判断几等奖") Integer cslrItemIndex;// 非空  技术参数，用来判断几等奖   
	private @caption("中奖数额") @column("cslr_amount")    @note("  积分、免费小时、现金券为对应的数量，其它为1") Integer cslrAmount;// 非空  积分、免费小时、现金券为对应的数量，其它为1   
	private @caption("抽奖来源") @column("cslr_from")    @note(" 0:网站 1:微信 2:app 3:其它  ") Short cslrFrom;// 非空 0:网站 1:微信 2:app 3:其它     
	private @caption("奖品发放时间") @column("cslr_issue_time")    @note("  ") Date cslrIssueTime;//     
	private @caption("修改时间") @column("cslr_update_time")    @note("  ") Date cslrUpdateTime;// 非空     
	private @caption("添加时间") @column("cslr_add_time")    @note("  ") Date cslrAddTime;// 非空     
	private @caption("备注") @column("cslr_remark")    @note("  ") String cslrRemark;// 非空     
	private @caption("是否已收到") @column("cslr_state")    @note(" 0:未收到 1:已收到  ") Short cslrState;// 非空 0:未收到 1:已收到     
	private @caption("状态") @column("cslr_status")    @note(" 0:未领取 1:已发放 2:弃奖  ") Short cslrStatus;// 非空 0:未领取 1:已发放 2:弃奖     
	
	//默认构造函数
	public CsLuckyResult(){
	
	}
	
	//主键构造函数
	public CsLuckyResult(Long id){
		this.cslrId = id;
	}
	
	/**所有字段构造函数 CsLuckyResult(cslrName,cslrMember,cslrLucky,cslrLuckyItem,cslrItemType,cslrItemIndex,cslrAmount,cslrFrom,cslrIssueTime,cslrUpdateTime,cslrAddTime,cslrRemark,cslrState,cslrStatus)
	 CsLuckyResult(
	 	$.getString("cslrName")//中奖标题 [非空]
	 	,$.getLong("cslrMember")//中奖会员 [非空]
	 	,$.getLong("cslrLucky")//所属活动 [非空]
	 	,$.getLong("cslrLuckyItem")//所属奖项 [非空]
	 	,$.getShort("cslrItemType")//奖项类型 [非空]
	 	,$.getInteger("cslrItemIndex")//奖项等级 [非空]
	 	,$.getInteger("cslrAmount")//中奖数额 [非空]
	 	,$.getShort("cslrFrom")//抽奖来源 [非空]
	 	,$.getDate("cslrIssueTime")//奖品发放时间
	 	,$.getDate("cslrUpdateTime")//修改时间 [非空]
	 	,$.getDate("cslrAddTime")//添加时间 [非空]
	 	,$.getString("cslrRemark")//备注 [非空]
	 	,$.getShort("cslrState")//是否已收到 [非空]
	 	,$.getShort("cslrStatus")//状态 [非空]
	 )
	**/
	public CsLuckyResult(String cslrName,Long cslrMember,Long cslrLucky,Long cslrLuckyItem,Short cslrItemType,Integer cslrItemIndex,Integer cslrAmount,Short cslrFrom,Date cslrIssueTime,Date cslrUpdateTime,Date cslrAddTime,String cslrRemark,Short cslrState,Short cslrStatus){
		this.cslrName=cslrName;
		this.cslrMember=cslrMember;
		this.cslrLucky=cslrLucky;
		this.cslrLuckyItem=cslrLuckyItem;
		this.cslrItemType=cslrItemType;
		this.cslrItemIndex=cslrItemIndex;
		this.cslrAmount=cslrAmount;
		this.cslrFrom=cslrFrom;
		this.cslrIssueTime=cslrIssueTime;
		this.cslrUpdateTime=cslrUpdateTime;
		this.cslrAddTime=cslrAddTime;
		this.cslrRemark=cslrRemark;
		this.cslrState=cslrState;
		this.cslrStatus=cslrStatus;
	}
	
	//设置非空字段
	public CsLuckyResult setNotNull(String cslrName,Long cslrMember,Long cslrLucky,Long cslrLuckyItem,Short cslrItemType,Integer cslrItemIndex,Integer cslrAmount,Short cslrFrom,Date cslrUpdateTime,Date cslrAddTime,String cslrRemark,Short cslrState,Short cslrStatus){
		this.cslrName=cslrName;
		this.cslrMember=cslrMember;
		this.cslrLucky=cslrLucky;
		this.cslrLuckyItem=cslrLuckyItem;
		this.cslrItemType=cslrItemType;
		this.cslrItemIndex=cslrItemIndex;
		this.cslrAmount=cslrAmount;
		this.cslrFrom=cslrFrom;
		this.cslrUpdateTime=cslrUpdateTime;
		this.cslrAddTime=cslrAddTime;
		this.cslrRemark=cslrRemark;
		this.cslrState=cslrState;
		this.cslrStatus=cslrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsLuckyResult cslrId(Long cslrId){
		this.cslrId = cslrId;
		this.setSeted(F.cslrId);
		return this;
	}
	/** 中奖标题 [非空]       **/
	public CsLuckyResult cslrName(String cslrName){
		this.cslrName = cslrName;
		this.setSeted(F.cslrName);
		return this;
	}
	/** 中奖会员 [非空] [CsMember]      **/
	public CsLuckyResult cslrMember(Long cslrMember){
		this.cslrMember = cslrMember;
		this.setSeted(F.cslrMember);
		return this;
	}
	/** 所属活动 [非空] [CsLucky]      **/
	public CsLuckyResult cslrLucky(Long cslrLucky){
		this.cslrLucky = cslrLucky;
		this.setSeted(F.cslrLucky);
		return this;
	}
	/** 所属奖项 [非空] [CsLuckyItem]      **/
	public CsLuckyResult cslrLuckyItem(Long cslrLuckyItem){
		this.cslrLuckyItem = cslrLuckyItem;
		this.setSeted(F.cslrLuckyItem);
		return this;
	}
	/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放   **/
	public CsLuckyResult cslrItemType(Short cslrItemType){
		this.cslrItemType = cslrItemType;
		this.setSeted(F.cslrItemType);
		return this;
	}
	/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
	public CsLuckyResult cslrItemIndex(Integer cslrItemIndex){
		this.cslrItemIndex = cslrItemIndex;
		this.setSeted(F.cslrItemIndex);
		return this;
	}
	/** 中奖数额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
	public CsLuckyResult cslrAmount(Integer cslrAmount){
		this.cslrAmount = cslrAmount;
		this.setSeted(F.cslrAmount);
		return this;
	}
	/** 抽奖来源 [非空]   0:网站 1:微信 2:app 3:其它     **/
	public CsLuckyResult cslrFrom(Short cslrFrom){
		this.cslrFrom = cslrFrom;
		this.setSeted(F.cslrFrom);
		return this;
	}
	/** 奖品发放时间        **/
	public CsLuckyResult cslrIssueTime(Date cslrIssueTime){
		this.cslrIssueTime = cslrIssueTime;
		this.setSeted(F.cslrIssueTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsLuckyResult cslrUpdateTime(Date cslrUpdateTime){
		this.cslrUpdateTime = cslrUpdateTime;
		this.setSeted(F.cslrUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsLuckyResult cslrAddTime(Date cslrAddTime){
		this.cslrAddTime = cslrAddTime;
		this.setSeted(F.cslrAddTime);
		return this;
	}
	/** 备注 [非空]       **/
	public CsLuckyResult cslrRemark(String cslrRemark){
		this.cslrRemark = cslrRemark;
		this.setSeted(F.cslrRemark);
		return this;
	}
	/** 是否已收到 [非空]   0:未收到 1:已收到     **/
	public CsLuckyResult cslrState(Short cslrState){
		this.cslrState = cslrState;
		this.setSeted(F.cslrState);
		return this;
	}
	/** 状态 [非空]   0:未领取 1:已发放 2:弃奖     **/
	public CsLuckyResult cslrStatus(Short cslrStatus){
		this.cslrStatus = cslrStatus;
		this.setSeted(F.cslrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsLuckyResult clone(){
		CsLuckyResult clone = new CsLuckyResult();
		clone.cslrName=this.cslrName;
		clone.cslrMember=this.cslrMember;
		clone.cslrLucky=this.cslrLucky;
		clone.cslrLuckyItem=this.cslrLuckyItem;
		clone.cslrItemType=this.cslrItemType;
		clone.cslrItemIndex=this.cslrItemIndex;
		clone.cslrAmount=this.cslrAmount;
		clone.cslrFrom=this.cslrFrom;
		clone.cslrUpdateTime=this.cslrUpdateTime;
		clone.cslrAddTime=this.cslrAddTime;
		clone.cslrRemark=this.cslrRemark;
		clone.cslrState=this.cslrState;
		clone.cslrStatus=this.cslrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取中奖结果
	 * @param id
	 * @return
	 */
	public static @api CsLuckyResult get(Long id){		
		return getCsLuckyResultById(id);
	}
	/**
	 * 获取所有中奖结果
	 * @return
	 */
	public static @api List<CsLuckyResult> list(Map params,Integer size){
		return getCsLuckyResultList(params,size);
	}
	/**
	 * 获取中奖结果分页
	 * @return
	 */
	public static @api Page<CsLuckyResult> page(int page,int size,Map params){
		return getCsLuckyResultPage(page, size , params);
	}
	/**
	 * 根据查询条件取中奖结果
	 * @param params
	 * @return
	 */
	public static @api CsLuckyResult Get(Map params){
		return getCsLuckyResult(params);
	}
	/**
	 * 获取中奖结果数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLuckyResultCount(params);
	}
	/**
	 * 获取中奖结果数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLuckyResultEval(eval,params);
	}
	
	/**
	 * 根据ID取中奖结果
	 * @param id
	 * @return
	 */
	public static @api CsLuckyResult getCsLuckyResultById(Long id){		
		CsLuckyResult csLuckyResult = (CsLuckyResult) $.GetRequest("CsLuckyResult$"+id);
		if(csLuckyResult!=null)
			return csLuckyResult;
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");		
		return csLuckyResultService.getCsLuckyResultById(id);
	}
	
	
	/**
	 * 根据ID取中奖结果的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsLuckyResult.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsLuckyResult csLuckyResult = get(id);
		if(csLuckyResult!=null){
			String strValue = csLuckyResult.getCslrName$();
			if(!"CslrName".equals("CslrName"))
				strValue+="("+csLuckyResult.getCslrName$()+")";
			MemCache.setValue(CsLuckyResult.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCslrName$();
		if(!"CslrName".equals("CslrName"))
			keyValue+="("+this.getCslrName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有中奖结果
	 * @return
	 */
	public static @api List<CsLuckyResult> getCsLuckyResultList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		return csLuckyResultService.getCsLuckyResultList(params, size);
	}
	
	/**
	 * 获取中奖结果分页
	 * @return
	 */
	public static @api Page<CsLuckyResult> getCsLuckyResultPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		return csLuckyResultService.getCsLuckyResultPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取中奖结果
	 * @param params
	 * @return
	 */
	public static @api CsLuckyResult getCsLuckyResult(Map params){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		return csLuckyResultService.getCsLuckyResult(params);
	}
	
	/**
	 * 获取中奖结果数
	 * @return
	 */
	public static @api Long getCsLuckyResultCount(Map params){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		return csLuckyResultService.getCsLuckyResultCount(params);
	}
		
		
	/**
	 * 获取中奖结果自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLuckyResultEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		return csLuckyResultService.getCsLuckyResultEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLuckyResult(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		csLuckyResultService.updateCsLuckyResultByConfirm(set, where);
	}
	
	
	/**
	 * 保存中奖结果对象
	 * @param params
	 * @return
	 */
	public CsLuckyResult save(){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		if(this.getCslrId()!=null)
			csLuckyResultService.updateCsLuckyResult(this);
		else
			return csLuckyResultService.saveCsLuckyResult(this);
		return this;
	}
	
	
	/**
	 * 更新中奖结果对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		csLuckyResultService.updateCsLuckyResult$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		if($.equals($.config("logic_delete"),"true"))
			csLuckyResultService.removeCsLuckyResultById(this.getCslrId());
		else
			csLuckyResultService.deleteCsLuckyResultById(this.getCslrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLuckyResultService csLuckyResultService = $.GetSpringBean("csLuckyResultService");
		return csLuckyResultService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCslrId(){
		return this.cslrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCslrId$(){
		String strValue="";
		 strValue=$.str(this.getCslrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCslrId(Long cslrId){
		this.cslrId = cslrId;
		this.setSeted(F.cslrId);
	}
	/*******************************中奖标题**********************************/	
	/**
	* 中奖标题 [非空]      
	**/
	public String getCslrName(){
		return this.cslrName;
	}
	/**
	* 获取中奖标题格式化(toString)
	**/
	public String getCslrName$(){
		String strValue="";
		 strValue=$.str(this.getCslrName());
	 	 return strValue;
	}
	/**
	* 中奖标题 [非空]      
	**/
	public void setCslrName(String cslrName){
		this.cslrName = cslrName;
		this.setSeted(F.cslrName);
	}
	/*******************************中奖会员**********************************/	
	/**
	* 中奖会员 [非空] [CsMember]     
	**/
	public Long getCslrMember(){
		return this.cslrMember;
	}
	/**
	* 获取中奖会员格式化(toString)
	**/
	public String getCslrMember$(){
		String strValue="";
		if(this.getCslrMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCslrMember()));
		}			
	 	 return strValue;
	}
	/**
	* 中奖会员 [非空] [CsMember]     
	**/
	public void setCslrMember(Long cslrMember){
		this.cslrMember = cslrMember;
		this.setSeted(F.cslrMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cslrMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCslrMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCslrMember()!=null){
 			csMember = CsMember.get(this.getCslrMember());
 		}
 		$.SetRequest("CsMember$"+this.getCslrMember(), csMember);
	 	return csMember;
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动 [非空] [CsLucky]     
	**/
	public Long getCslrLucky(){
		return this.cslrLucky;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getCslrLucky$(){
		String strValue="";
		if(this.getCslrLucky()!=null){
				strValue+=$.str(CsLucky.getKeyValue(this.getCslrLucky()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动 [非空] [CsLucky]     
	**/
	public void setCslrLucky(Long cslrLucky){
		this.cslrLucky = cslrLucky;
		this.setSeted(F.cslrLucky);
	}
	/**
	* 获取关联对象[抽奖活动]
	**/	 			
 	public CsLucky get$cslrLucky(){
 		com.ccclubs.model.CsLucky csLucky = (com.ccclubs.model.CsLucky) $.GetRequest("CsLucky$"+this.getCslrLucky());
 		if(csLucky!=null)
			return csLucky;
		if(this.getCslrLucky()!=null){
 			csLucky = CsLucky.get(this.getCslrLucky());
 		}
 		$.SetRequest("CsLucky$"+this.getCslrLucky(), csLucky);
	 	return csLucky;
	}
	/*******************************所属奖项**********************************/	
	/**
	* 所属奖项 [非空] [CsLuckyItem]     
	**/
	public Long getCslrLuckyItem(){
		return this.cslrLuckyItem;
	}
	/**
	* 获取所属奖项格式化(toString)
	**/
	public String getCslrLuckyItem$(){
		String strValue="";
		if(this.getCslrLuckyItem()!=null){
				strValue+=$.str(CsLuckyItem.getKeyValue(this.getCslrLuckyItem()));
		}			
	 	 return strValue;
	}
	/**
	* 所属奖项 [非空] [CsLuckyItem]     
	**/
	public void setCslrLuckyItem(Long cslrLuckyItem){
		this.cslrLuckyItem = cslrLuckyItem;
		this.setSeted(F.cslrLuckyItem);
	}
	/**
	* 获取关联对象[抽奖奖项]
	**/	 			
 	public CsLuckyItem get$cslrLuckyItem(){
 		com.ccclubs.model.CsLuckyItem csLuckyItem = (com.ccclubs.model.CsLuckyItem) $.GetRequest("CsLuckyItem$"+this.getCslrLuckyItem());
 		if(csLuckyItem!=null)
			return csLuckyItem;
		if(this.getCslrLuckyItem()!=null){
 			csLuckyItem = CsLuckyItem.get(this.getCslrLuckyItem());
 		}
 		$.SetRequest("CsLuckyItem$"+this.getCslrLuckyItem(), csLuckyItem);
	 	return csLuckyItem;
	}
	/*******************************奖项类型**********************************/	
	/**
	* 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放  
	**/
	public Short getCslrItemType(){
		return this.cslrItemType;
	}
	/**
	* 获取奖项类型格式化(toString)
	**/
	public String getCslrItemType$(){
		String strValue="";
		 if($.equals($.str(this.getCslrItemType()),"0"))
			strValue=$.str("谢谢参与");		 
		 if($.equals($.str(this.getCslrItemType()),"1"))
			strValue=$.str("积分");		 
		 if($.equals($.str(this.getCslrItemType()),"2"))
			strValue=$.str("现金券");		 
		 if($.equals($.str(this.getCslrItemType()),"3"))
			strValue=$.str("免费小时");		 
		 if($.equals($.str(this.getCslrItemType()),"4"))
			strValue=$.str("实物");		 
		 if($.equals($.str(this.getCslrItemType()),"5"))
			strValue=$.str("其它方式");		 
	 	 return strValue;
	}
	/**
	* 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放  
	**/
	public void setCslrItemType(Short cslrItemType){
		this.cslrItemType = cslrItemType;
		this.setSeted(F.cslrItemType);
	}
	/*******************************奖项等级**********************************/	
	/**
	* 奖项等级 [非空]    技术参数，用来判断几等奖  
	**/
	public Integer getCslrItemIndex(){
		return this.cslrItemIndex;
	}
	/**
	* 获取奖项等级格式化(toString)
	**/
	public String getCslrItemIndex$(){
		String strValue="";
		 strValue=$.str(this.getCslrItemIndex());
	 	 return strValue;
	}
	/**
	* 奖项等级 [非空]    技术参数，用来判断几等奖  
	**/
	public void setCslrItemIndex(Integer cslrItemIndex){
		this.cslrItemIndex = cslrItemIndex;
		this.setSeted(F.cslrItemIndex);
	}
	/*******************************中奖数额**********************************/	
	/**
	* 中奖数额 [非空]    积分、免费小时、现金券为对应的数量，其它为1  
	**/
	public Integer getCslrAmount(){
		return this.cslrAmount;
	}
	/**
	* 获取中奖数额格式化(toString)
	**/
	public String getCslrAmount$(){
		String strValue="";
		 strValue=$.str(this.getCslrAmount());
	 	 return strValue;
	}
	/**
	* 中奖数额 [非空]    积分、免费小时、现金券为对应的数量，其它为1  
	**/
	public void setCslrAmount(Integer cslrAmount){
		this.cslrAmount = cslrAmount;
		this.setSeted(F.cslrAmount);
	}
	/*******************************抽奖来源**********************************/	
	/**
	* 抽奖来源 [非空]   0:网站 1:微信 2:app 3:其它    
	**/
	public Short getCslrFrom(){
		return this.cslrFrom;
	}
	/**
	* 获取抽奖来源格式化(toString)
	**/
	public String getCslrFrom$(){
		String strValue="";
		 if($.equals($.str(this.getCslrFrom()),"0"))
			strValue=$.str("网站");		 
		 if($.equals($.str(this.getCslrFrom()),"1"))
			strValue=$.str("微信");		 
		 if($.equals($.str(this.getCslrFrom()),"2"))
			strValue=$.str("app");		 
		 if($.equals($.str(this.getCslrFrom()),"3"))
			strValue=$.str("其它");		 
	 	 return strValue;
	}
	/**
	* 抽奖来源 [非空]   0:网站 1:微信 2:app 3:其它    
	**/
	public void setCslrFrom(Short cslrFrom){
		this.cslrFrom = cslrFrom;
		this.setSeted(F.cslrFrom);
	}
	/*******************************奖品发放时间**********************************/	
	/**
	* 奖品发放时间       
	**/
	public Date getCslrIssueTime(){
		return this.cslrIssueTime;
	}
	/**
	* 获取奖品发放时间格式化(toString)
	**/
	public String getCslrIssueTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslrIssueTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 奖品发放时间       
	**/
	public void setCslrIssueTime(Date cslrIssueTime){
		this.cslrIssueTime = cslrIssueTime;
		this.setSeted(F.cslrIssueTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCslrUpdateTime(){
		return this.cslrUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCslrUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslrUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCslrUpdateTime(Date cslrUpdateTime){
		this.cslrUpdateTime = cslrUpdateTime;
		this.setSeted(F.cslrUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCslrAddTime(){
		return this.cslrAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCslrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCslrAddTime(Date cslrAddTime){
		this.cslrAddTime = cslrAddTime;
		this.setSeted(F.cslrAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注 [非空]      
	**/
	public String getCslrRemark(){
		return this.cslrRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCslrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCslrRemark());
	 	 return strValue;
	}
	/**
	* 备注 [非空]      
	**/
	public void setCslrRemark(String cslrRemark){
		this.cslrRemark = cslrRemark;
		this.setSeted(F.cslrRemark);
	}
	/*******************************是否已收到**********************************/	
	/**
	* 是否已收到 [非空]   0:未收到 1:已收到    
	**/
	public Short getCslrState(){
		return this.cslrState;
	}
	/**
	* 获取是否已收到格式化(toString)
	**/
	public String getCslrState$(){
		String strValue="";
		 if($.equals($.str(this.getCslrState()),"0"))
			strValue=$.str("未收到");		 
		 if($.equals($.str(this.getCslrState()),"1"))
			strValue=$.str("已收到");		 
	 	 return strValue;
	}
	/**
	* 是否已收到 [非空]   0:未收到 1:已收到    
	**/
	public void setCslrState(Short cslrState){
		this.cslrState = cslrState;
		this.setSeted(F.cslrState);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:未领取 1:已发放 2:弃奖    
	**/
	public Short getCslrStatus(){
		return this.cslrStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCslrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCslrStatus()),"0"))
			strValue=$.str("未领取");		 
		 if($.equals($.str(this.getCslrStatus()),"1"))
			strValue=$.str("已发放");		 
		 if($.equals($.str(this.getCslrStatus()),"2"))
			strValue=$.str("弃奖");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:未领取 1:已发放 2:弃奖    
	**/
	public void setCslrStatus(Short cslrStatus){
		this.cslrStatus = cslrStatus;
		this.setSeted(F.cslrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLuckyResult.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLuckyResult.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLuckyResult.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLuckyResult.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLuckyResult.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLuckyResult.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLuckyResult.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLuckyResult.this);
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
		public M cslrId(Object cslrId){this.put("cslrId", cslrId);return this;};
	 	/** and cslr_id is null */
 		public M cslrIdNull(){if(this.get("cslrIdNot")==null)this.put("cslrIdNot", "");this.put("cslrId", null);return this;};
 		/** not .... */
 		public M cslrIdNot(){this.put("cslrIdNot", "not");return this;};
		/** 中奖标题 [非空]       **/
		public M cslrName(Object cslrName){this.put("cslrName", cslrName);return this;};
	 	/** and cslr_name is null */
 		public M cslrNameNull(){if(this.get("cslrNameNot")==null)this.put("cslrNameNot", "");this.put("cslrName", null);return this;};
 		/** not .... */
 		public M cslrNameNot(){this.put("cslrNameNot", "not");return this;};
		/** 中奖会员 [非空] [CsMember]      **/
		public M cslrMember(Object cslrMember){this.put("cslrMember", cslrMember);return this;};
	 	/** and cslr_member is null */
 		public M cslrMemberNull(){if(this.get("cslrMemberNot")==null)this.put("cslrMemberNot", "");this.put("cslrMember", null);return this;};
 		/** not .... */
 		public M cslrMemberNot(){this.put("cslrMemberNot", "not");return this;};
		/** and cslr_member >= ? */
		public M cslrMemberMin(Object min){this.put("cslrMemberMin", min);return this;};
		/** and cslr_member <= ? */
		public M cslrMemberMax(Object max){this.put("cslrMemberMax", max);return this;};
		public M cslrMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cslrMember$on", value);
			return this;
		};	
		/** 所属活动 [非空] [CsLucky]      **/
		public M cslrLucky(Object cslrLucky){this.put("cslrLucky", cslrLucky);return this;};
	 	/** and cslr_lucky is null */
 		public M cslrLuckyNull(){if(this.get("cslrLuckyNot")==null)this.put("cslrLuckyNot", "");this.put("cslrLucky", null);return this;};
 		/** not .... */
 		public M cslrLuckyNot(){this.put("cslrLuckyNot", "not");return this;};
		/** and cslr_lucky >= ? */
		public M cslrLuckyMin(Object min){this.put("cslrLuckyMin", min);return this;};
		/** and cslr_lucky <= ? */
		public M cslrLuckyMax(Object max){this.put("cslrLuckyMax", max);return this;};
		public M cslrLucky$on(CsLucky.M value){
			this.put("CsLucky", value);
			this.put("cslrLucky$on", value);
			return this;
		};	
		/** 所属奖项 [非空] [CsLuckyItem]      **/
		public M cslrLuckyItem(Object cslrLuckyItem){this.put("cslrLuckyItem", cslrLuckyItem);return this;};
	 	/** and cslr_lucky_item is null */
 		public M cslrLuckyItemNull(){if(this.get("cslrLuckyItemNot")==null)this.put("cslrLuckyItemNot", "");this.put("cslrLuckyItem", null);return this;};
 		/** not .... */
 		public M cslrLuckyItemNot(){this.put("cslrLuckyItemNot", "not");return this;};
		/** and cslr_lucky_item >= ? */
		public M cslrLuckyItemMin(Object min){this.put("cslrLuckyItemMin", min);return this;};
		/** and cslr_lucky_item <= ? */
		public M cslrLuckyItemMax(Object max){this.put("cslrLuckyItemMax", max);return this;};
		public M cslrLuckyItem$on(CsLuckyItem.M value){
			this.put("CsLuckyItem", value);
			this.put("cslrLuckyItem$on", value);
			return this;
		};	
		/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放   **/
		public M cslrItemType(Object cslrItemType){this.put("cslrItemType", cslrItemType);return this;};
	 	/** and cslr_item_type is null */
 		public M cslrItemTypeNull(){if(this.get("cslrItemTypeNot")==null)this.put("cslrItemTypeNot", "");this.put("cslrItemType", null);return this;};
 		/** not .... */
 		public M cslrItemTypeNot(){this.put("cslrItemTypeNot", "not");return this;};
		/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
		public M cslrItemIndex(Object cslrItemIndex){this.put("cslrItemIndex", cslrItemIndex);return this;};
	 	/** and cslr_item_index is null */
 		public M cslrItemIndexNull(){if(this.get("cslrItemIndexNot")==null)this.put("cslrItemIndexNot", "");this.put("cslrItemIndex", null);return this;};
 		/** not .... */
 		public M cslrItemIndexNot(){this.put("cslrItemIndexNot", "not");return this;};
		/** and cslr_item_index >= ? */
		public M cslrItemIndexMin(Object min){this.put("cslrItemIndexMin", min);return this;};
		/** and cslr_item_index <= ? */
		public M cslrItemIndexMax(Object max){this.put("cslrItemIndexMax", max);return this;};
		/** 中奖数额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
		public M cslrAmount(Object cslrAmount){this.put("cslrAmount", cslrAmount);return this;};
	 	/** and cslr_amount is null */
 		public M cslrAmountNull(){if(this.get("cslrAmountNot")==null)this.put("cslrAmountNot", "");this.put("cslrAmount", null);return this;};
 		/** not .... */
 		public M cslrAmountNot(){this.put("cslrAmountNot", "not");return this;};
		/** and cslr_amount >= ? */
		public M cslrAmountMin(Object min){this.put("cslrAmountMin", min);return this;};
		/** and cslr_amount <= ? */
		public M cslrAmountMax(Object max){this.put("cslrAmountMax", max);return this;};
		/** 抽奖来源 [非空]   0:网站 1:微信 2:app 3:其它     **/
		public M cslrFrom(Object cslrFrom){this.put("cslrFrom", cslrFrom);return this;};
	 	/** and cslr_from is null */
 		public M cslrFromNull(){if(this.get("cslrFromNot")==null)this.put("cslrFromNot", "");this.put("cslrFrom", null);return this;};
 		/** not .... */
 		public M cslrFromNot(){this.put("cslrFromNot", "not");return this;};
		/** 奖品发放时间        **/
		public M cslrIssueTime(Object cslrIssueTime){this.put("cslrIssueTime", cslrIssueTime);return this;};
	 	/** and cslr_issue_time is null */
 		public M cslrIssueTimeNull(){if(this.get("cslrIssueTimeNot")==null)this.put("cslrIssueTimeNot", "");this.put("cslrIssueTime", null);return this;};
 		/** not .... */
 		public M cslrIssueTimeNot(){this.put("cslrIssueTimeNot", "not");return this;};
 		/** and cslr_issue_time >= ? */
 		public M cslrIssueTimeStart(Object start){this.put("cslrIssueTimeStart", start);return this;};			
 		/** and cslr_issue_time <= ? */
 		public M cslrIssueTimeEnd(Object end){this.put("cslrIssueTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cslrUpdateTime(Object cslrUpdateTime){this.put("cslrUpdateTime", cslrUpdateTime);return this;};
	 	/** and cslr_update_time is null */
 		public M cslrUpdateTimeNull(){if(this.get("cslrUpdateTimeNot")==null)this.put("cslrUpdateTimeNot", "");this.put("cslrUpdateTime", null);return this;};
 		/** not .... */
 		public M cslrUpdateTimeNot(){this.put("cslrUpdateTimeNot", "not");return this;};
 		/** and cslr_update_time >= ? */
 		public M cslrUpdateTimeStart(Object start){this.put("cslrUpdateTimeStart", start);return this;};			
 		/** and cslr_update_time <= ? */
 		public M cslrUpdateTimeEnd(Object end){this.put("cslrUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cslrAddTime(Object cslrAddTime){this.put("cslrAddTime", cslrAddTime);return this;};
	 	/** and cslr_add_time is null */
 		public M cslrAddTimeNull(){if(this.get("cslrAddTimeNot")==null)this.put("cslrAddTimeNot", "");this.put("cslrAddTime", null);return this;};
 		/** not .... */
 		public M cslrAddTimeNot(){this.put("cslrAddTimeNot", "not");return this;};
 		/** and cslr_add_time >= ? */
 		public M cslrAddTimeStart(Object start){this.put("cslrAddTimeStart", start);return this;};			
 		/** and cslr_add_time <= ? */
 		public M cslrAddTimeEnd(Object end){this.put("cslrAddTimeEnd", end);return this;};
		/** 备注 [非空]       **/
		public M cslrRemark(Object cslrRemark){this.put("cslrRemark", cslrRemark);return this;};
	 	/** and cslr_remark is null */
 		public M cslrRemarkNull(){if(this.get("cslrRemarkNot")==null)this.put("cslrRemarkNot", "");this.put("cslrRemark", null);return this;};
 		/** not .... */
 		public M cslrRemarkNot(){this.put("cslrRemarkNot", "not");return this;};
		/** 是否已收到 [非空]   0:未收到 1:已收到     **/
		public M cslrState(Object cslrState){this.put("cslrState", cslrState);return this;};
	 	/** and cslr_state is null */
 		public M cslrStateNull(){if(this.get("cslrStateNot")==null)this.put("cslrStateNot", "");this.put("cslrState", null);return this;};
 		/** not .... */
 		public M cslrStateNot(){this.put("cslrStateNot", "not");return this;};
		/** 状态 [非空]   0:未领取 1:已发放 2:弃奖     **/
		public M cslrStatus(Object cslrStatus){this.put("cslrStatus", cslrStatus);return this;};
	 	/** and cslr_status is null */
 		public M cslrStatusNull(){if(this.get("cslrStatusNot")==null)this.put("cslrStatusNot", "");this.put("cslrStatus", null);return this;};
 		/** not .... */
 		public M cslrStatusNot(){this.put("cslrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有中奖结果 **/
		public @api List<CsLuckyResult> list(Integer size){
			return getCsLuckyResultList(this,size);
		}
		/** 获取中奖结果分页 **/
		public @api Page<CsLuckyResult> page(int page,int size){
			return getCsLuckyResultPage(page, size , this);
		}
		/** 根据查询条件取中奖结果 **/
		public @api CsLuckyResult get(){
			return getCsLuckyResult(this);
		}
		/** 获取中奖结果数 **/
		public @api Long count(){
			return getCsLuckyResultCount(this);
		}
		/** 获取中奖结果表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLuckyResultEval(strEval,this);
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
			updateCsLuckyResult(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cslrId="cslrId";
		/** 中奖标题 [非空]       **/
		public final static @type(String.class) @like String cslrName="cslrName";
		/** 中奖会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cslrMember="cslrMember";
		/** and cslr_member >= ? */
		public final static @type(Long.class) String cslrMemberMin="cslrMemberMin";
		/** and cslr_member <= ? */
		public final static @type(Long.class) String cslrMemberMax="cslrMemberMax";
		/** 所属活动 [非空] [CsLucky]      **/
		public final static @type(Long.class)  String cslrLucky="cslrLucky";
		/** and cslr_lucky >= ? */
		public final static @type(Long.class) String cslrLuckyMin="cslrLuckyMin";
		/** and cslr_lucky <= ? */
		public final static @type(Long.class) String cslrLuckyMax="cslrLuckyMax";
		/** 所属奖项 [非空] [CsLuckyItem]      **/
		public final static @type(Long.class)  String cslrLuckyItem="cslrLuckyItem";
		/** and cslr_lucky_item >= ? */
		public final static @type(Long.class) String cslrLuckyItemMin="cslrLuckyItemMin";
		/** and cslr_lucky_item <= ? */
		public final static @type(Long.class) String cslrLuckyItemMax="cslrLuckyItemMax";
		/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放   **/
		public final static @type(Short.class)  String cslrItemType="cslrItemType";
		/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
		public final static @type(Integer.class)  String cslrItemIndex="cslrItemIndex";
		/** and cslr_item_index >= ? */
		public final static @type(Integer.class) String cslrItemIndexMin="cslrItemIndexMin";
		/** and cslr_item_index <= ? */
		public final static @type(Integer.class) String cslrItemIndexMax="cslrItemIndexMax";
		/** 中奖数额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
		public final static @type(Integer.class)  String cslrAmount="cslrAmount";
		/** and cslr_amount >= ? */
		public final static @type(Integer.class) String cslrAmountMin="cslrAmountMin";
		/** and cslr_amount <= ? */
		public final static @type(Integer.class) String cslrAmountMax="cslrAmountMax";
		/** 抽奖来源 [非空]   0:网站 1:微信 2:app 3:其它     **/
		public final static @type(Short.class)  String cslrFrom="cslrFrom";
		/** 奖品发放时间        **/
		public final static @type(Date.class)  String cslrIssueTime="cslrIssueTime";
	 	/** and cslr_issue_time >= ? */
 		public final static @type(Date.class) String cslrIssueTimeStart="cslrIssueTimeStart";
 		/** and cslr_issue_time <= ? */
 		public final static @type(Date.class) String cslrIssueTimeEnd="cslrIssueTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cslrUpdateTime="cslrUpdateTime";
	 	/** and cslr_update_time >= ? */
 		public final static @type(Date.class) String cslrUpdateTimeStart="cslrUpdateTimeStart";
 		/** and cslr_update_time <= ? */
 		public final static @type(Date.class) String cslrUpdateTimeEnd="cslrUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cslrAddTime="cslrAddTime";
	 	/** and cslr_add_time >= ? */
 		public final static @type(Date.class) String cslrAddTimeStart="cslrAddTimeStart";
 		/** and cslr_add_time <= ? */
 		public final static @type(Date.class) String cslrAddTimeEnd="cslrAddTimeEnd";
		/** 备注 [非空]       **/
		public final static @type(String.class) @like String cslrRemark="cslrRemark";
		/** 是否已收到 [非空]   0:未收到 1:已收到     **/
		public final static @type(Short.class)  String cslrState="cslrState";
		/** 状态 [非空]   0:未领取 1:已发放 2:弃奖     **/
		public final static @type(Short.class)  String cslrStatus="cslrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cslrId="cslr_id";
		/** 中奖标题 [非空]       **/
		public final static String cslrName="cslr_name";
		/** 中奖会员 [非空] [CsMember]      **/
		public final static String cslrMember="cslr_member";
		/** 所属活动 [非空] [CsLucky]      **/
		public final static String cslrLucky="cslr_lucky";
		/** 所属奖项 [非空] [CsLuckyItem]      **/
		public final static String cslrLuckyItem="cslr_lucky_item";
		/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  如果是免费小时和现金券则自动发放   **/
		public final static String cslrItemType="cslr_item_type";
		/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
		public final static String cslrItemIndex="cslr_item_index";
		/** 中奖数额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
		public final static String cslrAmount="cslr_amount";
		/** 抽奖来源 [非空]   0:网站 1:微信 2:app 3:其它     **/
		public final static String cslrFrom="cslr_from";
		/** 奖品发放时间        **/
		public final static String cslrIssueTime="cslr_issue_time";
		/** 修改时间 [非空]       **/
		public final static String cslrUpdateTime="cslr_update_time";
		/** 添加时间 [非空]       **/
		public final static String cslrAddTime="cslr_add_time";
		/** 备注 [非空]       **/
		public final static String cslrRemark="cslr_remark";
		/** 是否已收到 [非空]   0:未收到 1:已收到     **/
		public final static String cslrState="cslr_state";
		/** 状态 [非空]   0:未领取 1:已发放 2:弃奖     **/
		public final static String cslrStatus="cslr_status";
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
				$.Set(name,CsLuckyResult.getCsLuckyResult(params));
			else
				$.Set(name,CsLuckyResult.getCsLuckyResultList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取中奖结果数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLuckyResult) $.GetRequest("CsLuckyResult$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLuckyResult.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLuckyResult.getCsLuckyResult(params);
			else
				value = CsLuckyResult.getCsLuckyResultList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLuckyResult.Get($.add(CsLuckyResult.F.cslrId,param));
		else if(!$.empty(param.toString()))
			value = CsLuckyResult.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLuckyResult$"+param.hashCode(), value);
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
	public void mergeSet(CsLuckyResult old){
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