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

@namespace("operate/lucky/luckyitem")
public @caption("抽奖奖项") @table("cs_lucky_item") class CsLuckyItem implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csli_id")   @primary  @note("  ") Long csliId;// 主键 非空     
	private @caption("奖品名称") @column("csli_name")    @note("  ") String csliName;// 非空     
	private @caption("所属活动") @column("csli_lucky_id")    @relate("$csliLuckyId") @RelateClass(CsLucky.class)  @note("  ") Long csliLuckyId;// 非空     
 	private CsLucky $csliLuckyId;//关联对象[抽奖活动]
	private @caption("奖项类型") @column("csli_type")    @note(" 0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式  ") Short csliType;// 非空 0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式     
	private @caption("奖项等级") @column("csli_index")    @note("  技术参数，用来判断几等奖") Integer csliIndex;// 非空  技术参数，用来判断几等奖   
	private @caption("奖额") @column("csli_amount")    @note("  积分、免费小时、现金券为对应的数量，其它为1") Integer csliAmount;// 非空  积分、免费小时、现金券为对应的数量，其它为1   
	private @caption("投放数量") @column("csli_count")    @note("  ") Integer csliCount;// 非空     
	private @caption("剩余数量") @column("csli_remain")    @note("  ") Integer csliRemain;// 非空     
	private @caption("中奖概率") @column("csli_ratio")    @note("  0到1之间的小数，默认应为1") Double csliRatio;// 非空  0到1之间的小数，默认应为1   
	private @caption("修改时间") @column("csli_update_time")    @note("  ") Date csliUpdateTime;// 非空     
	private @caption("添加时间") @column("csli_add_time")    @note("  ") Date csliAddTime;// 非空     
	private @caption("状态") @column("csli_status")    @note(" 1:正常 0:无效  ") Short csliStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsLuckyItem(){
	
	}
	
	//主键构造函数
	public CsLuckyItem(Long id){
		this.csliId = id;
	}
	
	/**所有字段构造函数 CsLuckyItem(csliName,csliLuckyId,csliType,csliIndex,csliAmount,csliCount,csliRemain,csliRatio,csliUpdateTime,csliAddTime,csliStatus)
	 CsLuckyItem(
	 	$.getString("csliName")//奖品名称 [非空]
	 	,$.getLong("csliLuckyId")//所属活动 [非空]
	 	,$.getShort("csliType")//奖项类型 [非空]
	 	,$.getInteger("csliIndex")//奖项等级 [非空]
	 	,$.getInteger("csliAmount")//奖额 [非空]
	 	,$.getInteger("csliCount")//投放数量 [非空]
	 	,$.getInteger("csliRemain")//剩余数量 [非空]
	 	,$.getDouble("csliRatio")//中奖概率 [非空]
	 	,$.getDate("csliUpdateTime")//修改时间 [非空]
	 	,$.getDate("csliAddTime")//添加时间 [非空]
	 	,$.getShort("csliStatus")//状态 [非空]
	 )
	**/
	public CsLuckyItem(String csliName,Long csliLuckyId,Short csliType,Integer csliIndex,Integer csliAmount,Integer csliCount,Integer csliRemain,Double csliRatio,Date csliUpdateTime,Date csliAddTime,Short csliStatus){
		this.csliName=csliName;
		this.csliLuckyId=csliLuckyId;
		this.csliType=csliType;
		this.csliIndex=csliIndex;
		this.csliAmount=csliAmount;
		this.csliCount=csliCount;
		this.csliRemain=csliRemain;
		this.csliRatio=csliRatio;
		this.csliUpdateTime=csliUpdateTime;
		this.csliAddTime=csliAddTime;
		this.csliStatus=csliStatus;
	}
	
	//设置非空字段
	public CsLuckyItem setNotNull(String csliName,Long csliLuckyId,Short csliType,Integer csliIndex,Integer csliAmount,Integer csliCount,Integer csliRemain,Double csliRatio,Date csliUpdateTime,Date csliAddTime,Short csliStatus){
		this.csliName=csliName;
		this.csliLuckyId=csliLuckyId;
		this.csliType=csliType;
		this.csliIndex=csliIndex;
		this.csliAmount=csliAmount;
		this.csliCount=csliCount;
		this.csliRemain=csliRemain;
		this.csliRatio=csliRatio;
		this.csliUpdateTime=csliUpdateTime;
		this.csliAddTime=csliAddTime;
		this.csliStatus=csliStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsLuckyItem csliId(Long csliId){
		this.csliId = csliId;
		this.setSeted(F.csliId);
		return this;
	}
	/** 奖品名称 [非空]       **/
	public CsLuckyItem csliName(String csliName){
		this.csliName = csliName;
		this.setSeted(F.csliName);
		return this;
	}
	/** 所属活动 [非空] [CsLucky]      **/
	public CsLuckyItem csliLuckyId(Long csliLuckyId){
		this.csliLuckyId = csliLuckyId;
		this.setSeted(F.csliLuckyId);
		return this;
	}
	/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式     **/
	public CsLuckyItem csliType(Short csliType){
		this.csliType = csliType;
		this.setSeted(F.csliType);
		return this;
	}
	/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
	public CsLuckyItem csliIndex(Integer csliIndex){
		this.csliIndex = csliIndex;
		this.setSeted(F.csliIndex);
		return this;
	}
	/** 奖额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
	public CsLuckyItem csliAmount(Integer csliAmount){
		this.csliAmount = csliAmount;
		this.setSeted(F.csliAmount);
		return this;
	}
	/** 投放数量 [非空]       **/
	public CsLuckyItem csliCount(Integer csliCount){
		this.csliCount = csliCount;
		this.setSeted(F.csliCount);
		return this;
	}
	/** 剩余数量 [非空]       **/
	public CsLuckyItem csliRemain(Integer csliRemain){
		this.csliRemain = csliRemain;
		this.setSeted(F.csliRemain);
		return this;
	}
	/** 中奖概率 [非空]    0到1之间的小数，默认应为1   **/
	public CsLuckyItem csliRatio(Double csliRatio){
		this.csliRatio = csliRatio;
		this.setSeted(F.csliRatio);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsLuckyItem csliUpdateTime(Date csliUpdateTime){
		this.csliUpdateTime = csliUpdateTime;
		this.setSeted(F.csliUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsLuckyItem csliAddTime(Date csliAddTime){
		this.csliAddTime = csliAddTime;
		this.setSeted(F.csliAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsLuckyItem csliStatus(Short csliStatus){
		this.csliStatus = csliStatus;
		this.setSeted(F.csliStatus);
		return this;
	}
	
	
	//克隆对象
	public CsLuckyItem clone(){
		CsLuckyItem clone = new CsLuckyItem();
		clone.csliName=this.csliName;
		clone.csliLuckyId=this.csliLuckyId;
		clone.csliType=this.csliType;
		clone.csliIndex=this.csliIndex;
		clone.csliAmount=this.csliAmount;
		clone.csliCount=this.csliCount;
		clone.csliRemain=this.csliRemain;
		clone.csliRatio=this.csliRatio;
		clone.csliUpdateTime=this.csliUpdateTime;
		clone.csliAddTime=this.csliAddTime;
		clone.csliStatus=this.csliStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取抽奖奖项
	 * @param id
	 * @return
	 */
	public static @api CsLuckyItem get(Long id){		
		return getCsLuckyItemById(id);
	}
	/**
	 * 获取所有抽奖奖项
	 * @return
	 */
	public static @api List<CsLuckyItem> list(Map params,Integer size){
		return getCsLuckyItemList(params,size);
	}
	/**
	 * 获取抽奖奖项分页
	 * @return
	 */
	public static @api Page<CsLuckyItem> page(int page,int size,Map params){
		return getCsLuckyItemPage(page, size , params);
	}
	/**
	 * 根据查询条件取抽奖奖项
	 * @param params
	 * @return
	 */
	public static @api CsLuckyItem Get(Map params){
		return getCsLuckyItem(params);
	}
	/**
	 * 获取抽奖奖项数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLuckyItemCount(params);
	}
	/**
	 * 获取抽奖奖项数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLuckyItemEval(eval,params);
	}
	
	/**
	 * 根据ID取抽奖奖项
	 * @param id
	 * @return
	 */
	public static @api CsLuckyItem getCsLuckyItemById(Long id){		
		CsLuckyItem csLuckyItem = (CsLuckyItem) $.GetRequest("CsLuckyItem$"+id);
		if(csLuckyItem!=null)
			return csLuckyItem;
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");		
		return csLuckyItemService.getCsLuckyItemById(id);
	}
	
	
	/**
	 * 根据ID取抽奖奖项的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsLuckyItem.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsLuckyItem csLuckyItem = get(id);
		if(csLuckyItem!=null){
			String strValue = csLuckyItem.getCsliName$();
			if(!"CsliName".equals("CsliName"))
				strValue+="("+csLuckyItem.getCsliName$()+")";
			MemCache.setValue(CsLuckyItem.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsliName$();
		if(!"CsliName".equals("CsliName"))
			keyValue+="("+this.getCsliName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有抽奖奖项
	 * @return
	 */
	public static @api List<CsLuckyItem> getCsLuckyItemList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		return csLuckyItemService.getCsLuckyItemList(params, size);
	}
	
	/**
	 * 获取抽奖奖项分页
	 * @return
	 */
	public static @api Page<CsLuckyItem> getCsLuckyItemPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		return csLuckyItemService.getCsLuckyItemPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取抽奖奖项
	 * @param params
	 * @return
	 */
	public static @api CsLuckyItem getCsLuckyItem(Map params){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		return csLuckyItemService.getCsLuckyItem(params);
	}
	
	/**
	 * 获取抽奖奖项数
	 * @return
	 */
	public static @api Long getCsLuckyItemCount(Map params){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		return csLuckyItemService.getCsLuckyItemCount(params);
	}
		
		
	/**
	 * 获取抽奖奖项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLuckyItemEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		return csLuckyItemService.getCsLuckyItemEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLuckyItem(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		csLuckyItemService.updateCsLuckyItemByConfirm(set, where);
	}
	
	
	/**
	 * 保存抽奖奖项对象
	 * @param params
	 * @return
	 */
	public CsLuckyItem save(){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		if(this.getCsliId()!=null)
			csLuckyItemService.updateCsLuckyItem(this);
		else
			return csLuckyItemService.saveCsLuckyItem(this);
		return this;
	}
	
	
	/**
	 * 更新抽奖奖项对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		csLuckyItemService.updateCsLuckyItem$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		if($.equals($.config("logic_delete"),"true"))
			csLuckyItemService.removeCsLuckyItemById(this.getCsliId());
		else
			csLuckyItemService.deleteCsLuckyItemById(this.getCsliId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		return csLuckyItemService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsliId(){
		return this.csliId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsliId$(){
		String strValue="";
		 strValue=$.str(this.getCsliId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsliId(Long csliId){
		this.csliId = csliId;
		this.setSeted(F.csliId);
	}
	/*******************************奖品名称**********************************/	
	/**
	* 奖品名称 [非空]      
	**/
	public String getCsliName(){
		return this.csliName;
	}
	/**
	* 获取奖品名称格式化(toString)
	**/
	public String getCsliName$(){
		String strValue="";
		 strValue=$.str(this.getCsliName());
	 	 return strValue;
	}
	/**
	* 奖品名称 [非空]      
	**/
	public void setCsliName(String csliName){
		this.csliName = csliName;
		this.setSeted(F.csliName);
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动 [非空] [CsLucky]     
	**/
	public Long getCsliLuckyId(){
		return this.csliLuckyId;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getCsliLuckyId$(){
		String strValue="";
		if(this.getCsliLuckyId()!=null){
				strValue+=$.str(CsLucky.getKeyValue(this.getCsliLuckyId()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动 [非空] [CsLucky]     
	**/
	public void setCsliLuckyId(Long csliLuckyId){
		this.csliLuckyId = csliLuckyId;
		this.setSeted(F.csliLuckyId);
	}
	/**
	* 获取关联对象[抽奖活动]
	**/	 			
 	public CsLucky get$csliLuckyId(){
 		com.ccclubs.model.CsLucky csLucky = (com.ccclubs.model.CsLucky) $.GetRequest("CsLucky$"+this.getCsliLuckyId());
 		if(csLucky!=null)
			return csLucky;
		if(this.getCsliLuckyId()!=null){
 			csLucky = CsLucky.get(this.getCsliLuckyId());
 		}
 		$.SetRequest("CsLucky$"+this.getCsliLuckyId(), csLucky);
	 	return csLucky;
	}
	/*******************************奖项类型**********************************/	
	/**
	* 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式    
	**/
	public Short getCsliType(){
		return this.csliType;
	}
	/**
	* 获取奖项类型格式化(toString)
	**/
	public String getCsliType$(){
		String strValue="";
		 if($.equals($.str(this.getCsliType()),"0"))
			strValue=$.str("谢谢参与");		 
		 if($.equals($.str(this.getCsliType()),"1"))
			strValue=$.str("积分");		 
		 if($.equals($.str(this.getCsliType()),"2"))
			strValue=$.str("现金券");		 
		 if($.equals($.str(this.getCsliType()),"3"))
			strValue=$.str("免费小时");		 
		 if($.equals($.str(this.getCsliType()),"4"))
			strValue=$.str("实物");		 
		 if($.equals($.str(this.getCsliType()),"5"))
			strValue=$.str("其它方式");		 
	 	 return strValue;
	}
	/**
	* 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式    
	**/
	public void setCsliType(Short csliType){
		this.csliType = csliType;
		this.setSeted(F.csliType);
	}
	/*******************************奖项等级**********************************/	
	/**
	* 奖项等级 [非空]    技术参数，用来判断几等奖  
	**/
	public Integer getCsliIndex(){
		return this.csliIndex;
	}
	/**
	* 获取奖项等级格式化(toString)
	**/
	public String getCsliIndex$(){
		String strValue="";
		 strValue=$.str(this.getCsliIndex());
	 	 return strValue;
	}
	/**
	* 奖项等级 [非空]    技术参数，用来判断几等奖  
	**/
	public void setCsliIndex(Integer csliIndex){
		this.csliIndex = csliIndex;
		this.setSeted(F.csliIndex);
	}
	/*******************************奖额**********************************/	
	/**
	* 奖额 [非空]    积分、免费小时、现金券为对应的数量，其它为1  
	**/
	public Integer getCsliAmount(){
		return this.csliAmount;
	}
	/**
	* 获取奖额格式化(toString)
	**/
	public String getCsliAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsliAmount());
	 	 return strValue;
	}
	/**
	* 奖额 [非空]    积分、免费小时、现金券为对应的数量，其它为1  
	**/
	public void setCsliAmount(Integer csliAmount){
		this.csliAmount = csliAmount;
		this.setSeted(F.csliAmount);
	}
	/*******************************投放数量**********************************/	
	/**
	* 投放数量 [非空]      
	**/
	public Integer getCsliCount(){
		return this.csliCount;
	}
	/**
	* 获取投放数量格式化(toString)
	**/
	public String getCsliCount$(){
		String strValue="";
		 strValue=$.str(this.getCsliCount());
	 	 return strValue;
	}
	/**
	* 投放数量 [非空]      
	**/
	public void setCsliCount(Integer csliCount){
		this.csliCount = csliCount;
		this.setSeted(F.csliCount);
	}
	/*******************************剩余数量**********************************/	
	/**
	* 剩余数量 [非空]      
	**/
	public Integer getCsliRemain(){
		return this.csliRemain;
	}
	/**
	* 获取剩余数量格式化(toString)
	**/
	public String getCsliRemain$(){
		String strValue="";
		 strValue=$.str(this.getCsliRemain());
	 	 return strValue;
	}
	/**
	* 剩余数量 [非空]      
	**/
	public void setCsliRemain(Integer csliRemain){
		this.csliRemain = csliRemain;
		this.setSeted(F.csliRemain);
	}
	/*******************************中奖概率**********************************/	
	/**
	* 中奖概率 [非空]    0到1之间的小数，默认应为1  
	**/
	public Double getCsliRatio(){
		return this.csliRatio;
	}
	/**
	* 获取中奖概率格式化(toString)
	**/
	public String getCsliRatio$(){
		String strValue="";
		 strValue=$.str(this.getCsliRatio());	
	 	 return strValue;
	}
	/**
	* 中奖概率 [非空]    0到1之间的小数，默认应为1  
	**/
	public void setCsliRatio(Double csliRatio){
		this.csliRatio = csliRatio;
		this.setSeted(F.csliRatio);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsliUpdateTime(){
		return this.csliUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsliUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsliUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsliUpdateTime(Date csliUpdateTime){
		this.csliUpdateTime = csliUpdateTime;
		this.setSeted(F.csliUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsliAddTime(){
		return this.csliAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsliAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsliAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsliAddTime(Date csliAddTime){
		this.csliAddTime = csliAddTime;
		this.setSeted(F.csliAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsliStatus(){
		return this.csliStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsliStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsliStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsliStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsliStatus(Short csliStatus){
		this.csliStatus = csliStatus;
		this.setSeted(F.csliStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLuckyItem.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLuckyItem.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLuckyItem.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLuckyItem.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLuckyItem.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLuckyItem.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLuckyItem.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLuckyItem.this);
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
		public M csliId(Object csliId){this.put("csliId", csliId);return this;};
	 	/** and csli_id is null */
 		public M csliIdNull(){if(this.get("csliIdNot")==null)this.put("csliIdNot", "");this.put("csliId", null);return this;};
 		/** not .... */
 		public M csliIdNot(){this.put("csliIdNot", "not");return this;};
		/** 奖品名称 [非空]       **/
		public M csliName(Object csliName){this.put("csliName", csliName);return this;};
	 	/** and csli_name is null */
 		public M csliNameNull(){if(this.get("csliNameNot")==null)this.put("csliNameNot", "");this.put("csliName", null);return this;};
 		/** not .... */
 		public M csliNameNot(){this.put("csliNameNot", "not");return this;};
		/** 所属活动 [非空] [CsLucky]      **/
		public M csliLuckyId(Object csliLuckyId){this.put("csliLuckyId", csliLuckyId);return this;};
	 	/** and csli_lucky_id is null */
 		public M csliLuckyIdNull(){if(this.get("csliLuckyIdNot")==null)this.put("csliLuckyIdNot", "");this.put("csliLuckyId", null);return this;};
 		/** not .... */
 		public M csliLuckyIdNot(){this.put("csliLuckyIdNot", "not");return this;};
		public M csliLuckyId$on(CsLucky.M value){
			this.put("CsLucky", value);
			this.put("csliLuckyId$on", value);
			return this;
		};	
		/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式     **/
		public M csliType(Object csliType){this.put("csliType", csliType);return this;};
	 	/** and csli_type is null */
 		public M csliTypeNull(){if(this.get("csliTypeNot")==null)this.put("csliTypeNot", "");this.put("csliType", null);return this;};
 		/** not .... */
 		public M csliTypeNot(){this.put("csliTypeNot", "not");return this;};
		/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
		public M csliIndex(Object csliIndex){this.put("csliIndex", csliIndex);return this;};
	 	/** and csli_index is null */
 		public M csliIndexNull(){if(this.get("csliIndexNot")==null)this.put("csliIndexNot", "");this.put("csliIndex", null);return this;};
 		/** not .... */
 		public M csliIndexNot(){this.put("csliIndexNot", "not");return this;};
		/** and csli_index >= ? */
		public M csliIndexMin(Object min){this.put("csliIndexMin", min);return this;};
		/** and csli_index <= ? */
		public M csliIndexMax(Object max){this.put("csliIndexMax", max);return this;};
		/** 奖额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
		public M csliAmount(Object csliAmount){this.put("csliAmount", csliAmount);return this;};
	 	/** and csli_amount is null */
 		public M csliAmountNull(){if(this.get("csliAmountNot")==null)this.put("csliAmountNot", "");this.put("csliAmount", null);return this;};
 		/** not .... */
 		public M csliAmountNot(){this.put("csliAmountNot", "not");return this;};
		/** and csli_amount >= ? */
		public M csliAmountMin(Object min){this.put("csliAmountMin", min);return this;};
		/** and csli_amount <= ? */
		public M csliAmountMax(Object max){this.put("csliAmountMax", max);return this;};
		/** 投放数量 [非空]       **/
		public M csliCount(Object csliCount){this.put("csliCount", csliCount);return this;};
	 	/** and csli_count is null */
 		public M csliCountNull(){if(this.get("csliCountNot")==null)this.put("csliCountNot", "");this.put("csliCount", null);return this;};
 		/** not .... */
 		public M csliCountNot(){this.put("csliCountNot", "not");return this;};
		/** and csli_count >= ? */
		public M csliCountMin(Object min){this.put("csliCountMin", min);return this;};
		/** and csli_count <= ? */
		public M csliCountMax(Object max){this.put("csliCountMax", max);return this;};
		/** 剩余数量 [非空]       **/
		public M csliRemain(Object csliRemain){this.put("csliRemain", csliRemain);return this;};
	 	/** and csli_remain is null */
 		public M csliRemainNull(){if(this.get("csliRemainNot")==null)this.put("csliRemainNot", "");this.put("csliRemain", null);return this;};
 		/** not .... */
 		public M csliRemainNot(){this.put("csliRemainNot", "not");return this;};
		/** and csli_remain >= ? */
		public M csliRemainMin(Object min){this.put("csliRemainMin", min);return this;};
		/** and csli_remain <= ? */
		public M csliRemainMax(Object max){this.put("csliRemainMax", max);return this;};
		/** 中奖概率 [非空]    0到1之间的小数，默认应为1   **/
		public M csliRatio(Object csliRatio){this.put("csliRatio", csliRatio);return this;};
	 	/** and csli_ratio is null */
 		public M csliRatioNull(){if(this.get("csliRatioNot")==null)this.put("csliRatioNot", "");this.put("csliRatio", null);return this;};
 		/** not .... */
 		public M csliRatioNot(){this.put("csliRatioNot", "not");return this;};
		/** and csli_ratio >= ? */
		public M csliRatioMin(Object min){this.put("csliRatioMin", min);return this;};
		/** and csli_ratio <= ? */
		public M csliRatioMax(Object max){this.put("csliRatioMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M csliUpdateTime(Object csliUpdateTime){this.put("csliUpdateTime", csliUpdateTime);return this;};
	 	/** and csli_update_time is null */
 		public M csliUpdateTimeNull(){if(this.get("csliUpdateTimeNot")==null)this.put("csliUpdateTimeNot", "");this.put("csliUpdateTime", null);return this;};
 		/** not .... */
 		public M csliUpdateTimeNot(){this.put("csliUpdateTimeNot", "not");return this;};
 		/** and csli_update_time >= ? */
 		public M csliUpdateTimeStart(Object start){this.put("csliUpdateTimeStart", start);return this;};			
 		/** and csli_update_time <= ? */
 		public M csliUpdateTimeEnd(Object end){this.put("csliUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csliAddTime(Object csliAddTime){this.put("csliAddTime", csliAddTime);return this;};
	 	/** and csli_add_time is null */
 		public M csliAddTimeNull(){if(this.get("csliAddTimeNot")==null)this.put("csliAddTimeNot", "");this.put("csliAddTime", null);return this;};
 		/** not .... */
 		public M csliAddTimeNot(){this.put("csliAddTimeNot", "not");return this;};
 		/** and csli_add_time >= ? */
 		public M csliAddTimeStart(Object start){this.put("csliAddTimeStart", start);return this;};			
 		/** and csli_add_time <= ? */
 		public M csliAddTimeEnd(Object end){this.put("csliAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csliStatus(Object csliStatus){this.put("csliStatus", csliStatus);return this;};
	 	/** and csli_status is null */
 		public M csliStatusNull(){if(this.get("csliStatusNot")==null)this.put("csliStatusNot", "");this.put("csliStatus", null);return this;};
 		/** not .... */
 		public M csliStatusNot(){this.put("csliStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有抽奖奖项 **/
		public @api List<CsLuckyItem> list(Integer size){
			return getCsLuckyItemList(this,size);
		}
		/** 获取抽奖奖项分页 **/
		public @api Page<CsLuckyItem> page(int page,int size){
			return getCsLuckyItemPage(page, size , this);
		}
		/** 根据查询条件取抽奖奖项 **/
		public @api CsLuckyItem get(){
			return getCsLuckyItem(this);
		}
		/** 获取抽奖奖项数 **/
		public @api Long count(){
			return getCsLuckyItemCount(this);
		}
		/** 获取抽奖奖项表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLuckyItemEval(strEval,this);
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
			updateCsLuckyItem(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csliId="csliId";
		/** 奖品名称 [非空]       **/
		public final static @type(String.class) @like String csliName="csliName";
		/** 所属活动 [非空] [CsLucky]      **/
		public final static @type(Long.class)  String csliLuckyId="csliLuckyId";
		/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式     **/
		public final static @type(Short.class)  String csliType="csliType";
		/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
		public final static @type(Integer.class)  String csliIndex="csliIndex";
		/** and csli_index >= ? */
		public final static @type(Integer.class) String csliIndexMin="csliIndexMin";
		/** and csli_index <= ? */
		public final static @type(Integer.class) String csliIndexMax="csliIndexMax";
		/** 奖额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
		public final static @type(Integer.class)  String csliAmount="csliAmount";
		/** and csli_amount >= ? */
		public final static @type(Integer.class) String csliAmountMin="csliAmountMin";
		/** and csli_amount <= ? */
		public final static @type(Integer.class) String csliAmountMax="csliAmountMax";
		/** 投放数量 [非空]       **/
		public final static @type(Integer.class)  String csliCount="csliCount";
		/** and csli_count >= ? */
		public final static @type(Integer.class) String csliCountMin="csliCountMin";
		/** and csli_count <= ? */
		public final static @type(Integer.class) String csliCountMax="csliCountMax";
		/** 剩余数量 [非空]       **/
		public final static @type(Integer.class)  String csliRemain="csliRemain";
		/** and csli_remain >= ? */
		public final static @type(Integer.class) String csliRemainMin="csliRemainMin";
		/** and csli_remain <= ? */
		public final static @type(Integer.class) String csliRemainMax="csliRemainMax";
		/** 中奖概率 [非空]    0到1之间的小数，默认应为1   **/
		public final static @type(Double.class)  String csliRatio="csliRatio";
		/** and csli_ratio >= ? */
		public final static @type(Double.class) String csliRatioMin="csliRatioMin";
		/** and csli_ratio <= ? */
		public final static @type(Double.class) String csliRatioMax="csliRatioMax";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csliUpdateTime="csliUpdateTime";
	 	/** and csli_update_time >= ? */
 		public final static @type(Date.class) String csliUpdateTimeStart="csliUpdateTimeStart";
 		/** and csli_update_time <= ? */
 		public final static @type(Date.class) String csliUpdateTimeEnd="csliUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csliAddTime="csliAddTime";
	 	/** and csli_add_time >= ? */
 		public final static @type(Date.class) String csliAddTimeStart="csliAddTimeStart";
 		/** and csli_add_time <= ? */
 		public final static @type(Date.class) String csliAddTimeEnd="csliAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csliStatus="csliStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csliId="csli_id";
		/** 奖品名称 [非空]       **/
		public final static String csliName="csli_name";
		/** 所属活动 [非空] [CsLucky]      **/
		public final static String csliLuckyId="csli_lucky_id";
		/** 奖项类型 [非空]   0:谢谢参与 1:积分 2:现金券 3:免费小时 4:实物 5:其它方式     **/
		public final static String csliType="csli_type";
		/** 奖项等级 [非空]    技术参数，用来判断几等奖   **/
		public final static String csliIndex="csli_index";
		/** 奖额 [非空]    积分、免费小时、现金券为对应的数量，其它为1   **/
		public final static String csliAmount="csli_amount";
		/** 投放数量 [非空]       **/
		public final static String csliCount="csli_count";
		/** 剩余数量 [非空]       **/
		public final static String csliRemain="csli_remain";
		/** 中奖概率 [非空]    0到1之间的小数，默认应为1   **/
		public final static String csliRatio="csli_ratio";
		/** 修改时间 [非空]       **/
		public final static String csliUpdateTime="csli_update_time";
		/** 添加时间 [非空]       **/
		public final static String csliAddTime="csli_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csliStatus="csli_status";
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
				$.Set(name,CsLuckyItem.getCsLuckyItem(params));
			else
				$.Set(name,CsLuckyItem.getCsLuckyItemList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取抽奖奖项数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLuckyItem) $.GetRequest("CsLuckyItem$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLuckyItem.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLuckyItem.getCsLuckyItem(params);
			else
				value = CsLuckyItem.getCsLuckyItemList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLuckyItem.Get($.add(CsLuckyItem.F.csliId,param));
		else if(!$.empty(param.toString()))
			value = CsLuckyItem.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLuckyItem$"+param.hashCode(), value);
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
	public void mergeSet(CsLuckyItem old){
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