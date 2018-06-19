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

@namespace("sale/customgroup")
public @caption("客户组织") @table("tb_custom_group") class TbCustomGroup implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbcg_id")   @primary  @note("  ") Long tbcgId;// 主键 非空     
	private @caption("城市") @column("tbcg_host")    @relate("$tbcgHost") @RelateClass(SrvHost.class)  @note("  ") Long tbcgHost;// 非空     
 	private SrvHost $tbcgHost;//关联对象[运营城市]
	private @caption("归属战役") @column("tbcg_battle")    @relate("$tbcgBattle") @RelateClass(TbSaleBattle.class)  @note("  ") Long tbcgBattle;// 非空     
 	private TbSaleBattle $tbcgBattle;//关联对象[销售战役]
	private @caption("归属活动") @column("tbcg_event")    @relate("$tbcgEvent") @RelateClass(CsSaleEvent.class)  @note("  ") Long tbcgEvent;// 非空     
 	private CsSaleEvent $tbcgEvent;//关联对象[销售活动]
	private @caption("组织名称") @column("tbcg_name")    @note("  ") String tbcgName;// 非空     
	private @caption("组织全称") @column("tbcg_full_name")    @note("  ") String tbcgFullName;// 非空     
	private @caption("组织类型") @column("tbcg_type")    @note(" 1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它  ") Short tbcgType;// 非空 1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它     
	private @caption("行业") @column("tbcg_trade")    @relate("$tbcgTrade") @RelateClass(SrvProperty.class)  @note(" customgroup_trade:客户组织行业  ") Long tbcgTrade;// 非空 customgroup_trade:客户组织行业     
 	private SrvProperty $tbcgTrade;//关联对象[系统属性]
	private @caption("主营业务") @column("tbcg_service")    @note("  ") String tbcgService;//     
	private @caption("人员规模") @column("tbcg_scale")    @note(" 0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上  ") Short tbcgScale;// 0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上     
	private @caption("详细地址") @column("tbcg_address")    @note("  ") String tbcgAddress;//     
	private @caption("联系人") @column("tbcg_concact")    @note("  ") String tbcgConcact;//     
	private @caption("联系电话") @column("tbcg_number")    @note("  ") String tbcgNumber;//     
	private @caption("备注信息") @column("tbcg_detail")    @note("  ") String tbcgDetail;//     
	private @caption("所有人") @column("tbcg_onwer")    @relate("$tbcgOnwer") @RelateClass(SrvUser.class)  @note("  ") String tbcgOnwer;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbcgOnwer;//关联对象[用户]
	private @caption("添加人") @column("tbcg_adder")    @relate("$tbcgAdder") @RelateClass(SrvUser.class)  @note("  ") Long tbcgAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbcgAdder;//关联对象[用户]
	private @caption("修改人") @column("tbcg_editor")  @hidden   @relate("$tbcgEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbcgEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbcgEditor;//关联对象[用户]
	private @caption("备注") @column("tbcg_remark")    @note("  ") String tbcgRemark;//     
	private @caption("备忘信息") @column("tbcg_memo")    @note(" textarea:content text:sign  ") String tbcgMemo;// textarea:content text:sign     
	private @caption("程序数据") @column("tbcg_data")    @note("  ") String tbcgData;//     
	private @caption("修改时间") @column("tbcg_update_time")    @note("  ") Date tbcgUpdateTime;// 非空     
	private @caption("添加时间") @column("tbcg_add_time")    @note("  ") Date tbcgAddTime;// 非空     
	private @caption("状态") @column("tbcg_status")    @note(" 1:正常 0:无效  ") Short tbcgStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public TbCustomGroup(){
	
	}
	
	//主键构造函数
	public TbCustomGroup(Long id){
		this.tbcgId = id;
	}
	
	/**所有字段构造函数 TbCustomGroup(tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgFullName,tbcgType,tbcgTrade,tbcgService,tbcgScale,tbcgAddress,tbcgConcact,tbcgNumber,tbcgDetail,tbcgOnwer,tbcgAdder,tbcgEditor,tbcgRemark,tbcgMemo,tbcgData,tbcgUpdateTime,tbcgAddTime,tbcgStatus)
	 TbCustomGroup(
	 	$.getLong("tbcgHost")//城市 [非空]
	 	,$.getLong("tbcgBattle")//归属战役 [非空]
	 	,$.getLong("tbcgEvent")//归属活动 [非空]
	 	,$.getString("tbcgName")//组织名称 [非空]
	 	,$.getString("tbcgFullName")//组织全称 [非空]
	 	,$.getShort("tbcgType")//组织类型 [非空]
	 	,$.getLong("tbcgTrade")//行业 [非空]
	 	,$.getString("tbcgService")//主营业务
	 	,$.getShort("tbcgScale")//人员规模
	 	,$.getString("tbcgAddress")//详细地址
	 	,$.getString("tbcgConcact")//联系人
	 	,$.getString("tbcgNumber")//联系电话
	 	,$.getString("tbcgDetail")//备注信息
	 	,$.getString("tbcgOnwer")//所有人 [非空]
	 	,$.getLong("tbcgAdder")//添加人 [非空]
	 	,$.getLong("tbcgEditor")//修改人 [非空]
	 	,$.getString("tbcgRemark")//备注
	 	,$.getString("tbcgMemo")//备忘信息
	 	,$.getString("tbcgData")//程序数据
	 	,$.getDate("tbcgUpdateTime")//修改时间 [非空]
	 	,$.getDate("tbcgAddTime")//添加时间 [非空]
	 	,$.getShort("tbcgStatus")//状态 [非空]
	 )
	**/
	public TbCustomGroup(Long tbcgHost,Long tbcgBattle,Long tbcgEvent,String tbcgName,String tbcgFullName,Short tbcgType,Long tbcgTrade,String tbcgService,Short tbcgScale,String tbcgAddress,String tbcgConcact,String tbcgNumber,String tbcgDetail,String tbcgOnwer,Long tbcgAdder,Long tbcgEditor,String tbcgRemark,String tbcgMemo,String tbcgData,Date tbcgUpdateTime,Date tbcgAddTime,Short tbcgStatus){
		this.tbcgHost=tbcgHost;
		this.tbcgBattle=tbcgBattle;
		this.tbcgEvent=tbcgEvent;
		this.tbcgName=tbcgName;
		this.tbcgFullName=tbcgFullName;
		this.tbcgType=tbcgType;
		this.tbcgTrade=tbcgTrade;
		this.tbcgService=tbcgService;
		this.tbcgScale=tbcgScale;
		this.tbcgAddress=tbcgAddress;
		this.tbcgConcact=tbcgConcact;
		this.tbcgNumber=tbcgNumber;
		this.tbcgDetail=tbcgDetail;
		this.tbcgOnwer=tbcgOnwer;
		this.tbcgAdder=tbcgAdder;
		this.tbcgEditor=tbcgEditor;
		this.tbcgRemark=tbcgRemark;
		this.tbcgMemo=tbcgMemo;
		this.tbcgData=tbcgData;
		this.tbcgUpdateTime=tbcgUpdateTime;
		this.tbcgAddTime=tbcgAddTime;
		this.tbcgStatus=tbcgStatus;
	}
	
	//设置非空字段
	public TbCustomGroup setNotNull(Long tbcgHost,Long tbcgBattle,Long tbcgEvent,String tbcgName,String tbcgFullName,Short tbcgType,Long tbcgTrade,String tbcgOnwer,Long tbcgAdder,Long tbcgEditor,Date tbcgUpdateTime,Date tbcgAddTime,Short tbcgStatus){
		this.tbcgHost=tbcgHost;
		this.tbcgBattle=tbcgBattle;
		this.tbcgEvent=tbcgEvent;
		this.tbcgName=tbcgName;
		this.tbcgFullName=tbcgFullName;
		this.tbcgType=tbcgType;
		this.tbcgTrade=tbcgTrade;
		this.tbcgOnwer=tbcgOnwer;
		this.tbcgAdder=tbcgAdder;
		this.tbcgEditor=tbcgEditor;
		this.tbcgUpdateTime=tbcgUpdateTime;
		this.tbcgAddTime=tbcgAddTime;
		this.tbcgStatus=tbcgStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbCustomGroup tbcgId(Long tbcgId){
		this.tbcgId = tbcgId;
		this.setSeted(F.tbcgId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbCustomGroup tbcgHost(Long tbcgHost){
		this.tbcgHost = tbcgHost;
		this.setSeted(F.tbcgHost);
		return this;
	}
	/** 归属战役 [非空] [TbSaleBattle]      **/
	public TbCustomGroup tbcgBattle(Long tbcgBattle){
		this.tbcgBattle = tbcgBattle;
		this.setSeted(F.tbcgBattle);
		return this;
	}
	/** 归属活动 [非空] [CsSaleEvent]      **/
	public TbCustomGroup tbcgEvent(Long tbcgEvent){
		this.tbcgEvent = tbcgEvent;
		this.setSeted(F.tbcgEvent);
		return this;
	}
	/** 组织名称 [非空]       **/
	public TbCustomGroup tbcgName(String tbcgName){
		this.tbcgName = tbcgName;
		this.setSeted(F.tbcgName);
		return this;
	}
	/** 组织全称 [非空]       **/
	public TbCustomGroup tbcgFullName(String tbcgFullName){
		this.tbcgFullName = tbcgFullName;
		this.setSeted(F.tbcgFullName);
		return this;
	}
	/** 组织类型 [非空]   1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它     **/
	public TbCustomGroup tbcgType(Short tbcgType){
		this.tbcgType = tbcgType;
		this.setSeted(F.tbcgType);
		return this;
	}
	/** 行业 [非空] [SrvProperty]  customgroup_trade:客户组织行业     **/
	public TbCustomGroup tbcgTrade(Long tbcgTrade){
		this.tbcgTrade = tbcgTrade;
		this.setSeted(F.tbcgTrade);
		return this;
	}
	/** 主营业务        **/
	public TbCustomGroup tbcgService(String tbcgService){
		this.tbcgService = tbcgService;
		this.setSeted(F.tbcgService);
		return this;
	}
	/** 人员规模    0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上     **/
	public TbCustomGroup tbcgScale(Short tbcgScale){
		this.tbcgScale = tbcgScale;
		this.setSeted(F.tbcgScale);
		return this;
	}
	/** 详细地址        **/
	public TbCustomGroup tbcgAddress(String tbcgAddress){
		this.tbcgAddress = tbcgAddress;
		this.setSeted(F.tbcgAddress);
		return this;
	}
	/** 联系人        **/
	public TbCustomGroup tbcgConcact(String tbcgConcact){
		this.tbcgConcact = tbcgConcact;
		this.setSeted(F.tbcgConcact);
		return this;
	}
	/** 联系电话        **/
	public TbCustomGroup tbcgNumber(String tbcgNumber){
		this.tbcgNumber = tbcgNumber;
		this.setSeted(F.tbcgNumber);
		return this;
	}
	/** 备注信息        **/
	public TbCustomGroup tbcgDetail(String tbcgDetail){
		this.tbcgDetail = tbcgDetail;
		this.setSeted(F.tbcgDetail);
		return this;
	}
	/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbCustomGroup tbcgOnwer(String tbcgOnwer){
		this.tbcgOnwer = tbcgOnwer;
		this.setSeted(F.tbcgOnwer);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbCustomGroup tbcgAdder(Long tbcgAdder){
		this.tbcgAdder = tbcgAdder;
		this.setSeted(F.tbcgAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbCustomGroup tbcgEditor(Long tbcgEditor){
		this.tbcgEditor = tbcgEditor;
		this.setSeted(F.tbcgEditor);
		return this;
	}
	/** 备注        **/
	public TbCustomGroup tbcgRemark(String tbcgRemark){
		this.tbcgRemark = tbcgRemark;
		this.setSeted(F.tbcgRemark);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public TbCustomGroup tbcgMemo(String tbcgMemo){
		this.tbcgMemo = tbcgMemo;
		this.setSeted(F.tbcgMemo);
		return this;
	}
	/** 程序数据        **/
	public TbCustomGroup tbcgData(String tbcgData){
		this.tbcgData = tbcgData;
		this.setSeted(F.tbcgData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbCustomGroup tbcgUpdateTime(Date tbcgUpdateTime){
		this.tbcgUpdateTime = tbcgUpdateTime;
		this.setSeted(F.tbcgUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbCustomGroup tbcgAddTime(Date tbcgAddTime){
		this.tbcgAddTime = tbcgAddTime;
		this.setSeted(F.tbcgAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public TbCustomGroup tbcgStatus(Short tbcgStatus){
		this.tbcgStatus = tbcgStatus;
		this.setSeted(F.tbcgStatus);
		return this;
	}
	
	
	//克隆对象
	public TbCustomGroup clone(){
		TbCustomGroup clone = new TbCustomGroup();
		clone.tbcgHost=this.tbcgHost;
		clone.tbcgBattle=this.tbcgBattle;
		clone.tbcgEvent=this.tbcgEvent;
		clone.tbcgName=this.tbcgName;
		clone.tbcgFullName=this.tbcgFullName;
		clone.tbcgType=this.tbcgType;
		clone.tbcgTrade=this.tbcgTrade;
		clone.tbcgOnwer=this.tbcgOnwer;
		clone.tbcgAdder=this.tbcgAdder;
		clone.tbcgEditor=this.tbcgEditor;
		clone.tbcgUpdateTime=this.tbcgUpdateTime;
		clone.tbcgAddTime=this.tbcgAddTime;
		clone.tbcgStatus=this.tbcgStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取客户组织
	 * @param id
	 * @return
	 */
	public static @api TbCustomGroup get(Long id){		
		return getTbCustomGroupById(id);
	}
	/**
	 * 获取所有客户组织
	 * @return
	 */
	public static @api List<TbCustomGroup> list(Map params,Integer size){
		return getTbCustomGroupList(params,size);
	}
	/**
	 * 获取客户组织分页
	 * @return
	 */
	public static @api Page<TbCustomGroup> page(int page,int size,Map params){
		return getTbCustomGroupPage(page, size , params);
	}
	/**
	 * 根据查询条件取客户组织
	 * @param params
	 * @return
	 */
	public static @api TbCustomGroup Get(Map params){
		return getTbCustomGroup(params);
	}
	/**
	 * 获取客户组织数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbCustomGroupCount(params);
	}
	/**
	 * 获取客户组织数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbCustomGroupEval(eval,params);
	}
	
	/**
	 * 根据ID取客户组织
	 * @param id
	 * @return
	 */
	public static @api TbCustomGroup getTbCustomGroupById(Long id){		
		TbCustomGroup tbCustomGroup = (TbCustomGroup) $.GetRequest("TbCustomGroup$"+id);
		if(tbCustomGroup!=null)
			return tbCustomGroup;
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");		
		return tbCustomGroupService.getTbCustomGroupById(id);
	}
	
	
	/**
	 * 根据ID取客户组织的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbCustomGroup.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbCustomGroup tbCustomGroup = get(id);
		if(tbCustomGroup!=null){
			String strValue = tbCustomGroup.getTbcgName$();
			if(!"TbcgName".equals("TbcgName"))
				strValue+="("+tbCustomGroup.getTbcgName$()+")";
			MemCache.setValue(TbCustomGroup.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbcgName$();
		if(!"TbcgName".equals("TbcgName"))
			keyValue+="("+this.getTbcgName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有客户组织
	 * @return
	 */
	public static @api List<TbCustomGroup> getTbCustomGroupList(Map params,Integer size){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		return tbCustomGroupService.getTbCustomGroupList(params, size);
	}
	
	/**
	 * 获取客户组织分页
	 * @return
	 */
	public static @api Page<TbCustomGroup> getTbCustomGroupPage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		return tbCustomGroupService.getTbCustomGroupPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取客户组织
	 * @param params
	 * @return
	 */
	public static @api TbCustomGroup getTbCustomGroup(Map params){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		return tbCustomGroupService.getTbCustomGroup(params);
	}
	
	/**
	 * 获取客户组织数
	 * @return
	 */
	public static @api Long getTbCustomGroupCount(Map params){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		return tbCustomGroupService.getTbCustomGroupCount(params);
	}
		
		
	/**
	 * 获取客户组织自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbCustomGroupEval(String eval,Map params){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		return tbCustomGroupService.getTbCustomGroupEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbCustomGroup(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		tbCustomGroupService.updateTbCustomGroupByConfirm(set, where);
	}
	
	
	/**
	 * 保存客户组织对象
	 * @param params
	 * @return
	 */
	public TbCustomGroup save(){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		if(this.getTbcgId()!=null)
			tbCustomGroupService.updateTbCustomGroup(this);
		else
			return tbCustomGroupService.saveTbCustomGroup(this);
		return this;
	}
	
	
	/**
	 * 更新客户组织对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		tbCustomGroupService.updateTbCustomGroup$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		if($.equals($.config("logic_delete"),"true"))
			tbCustomGroupService.removeTbCustomGroupById(this.getTbcgId());
		else
			tbCustomGroupService.deleteTbCustomGroupById(this.getTbcgId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbCustomGroupService tbCustomGroupService = $.GetSpringBean("tbCustomGroupService");
		return tbCustomGroupService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbcgId(){
		return this.tbcgId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbcgId$(){
		String strValue="";
		 strValue=$.str(this.getTbcgId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbcgId(Long tbcgId){
		this.tbcgId = tbcgId;
		this.setSeted(F.tbcgId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbcgHost(){
		return this.tbcgHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbcgHost$(){
		String strValue="";
		if(this.getTbcgHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbcgHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbcgHost(Long tbcgHost){
		this.tbcgHost = tbcgHost;
		this.setSeted(F.tbcgHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbcgHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbcgHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbcgHost()!=null){
 			srvHost = SrvHost.get(this.getTbcgHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbcgHost(), srvHost);
	 	return srvHost;
	}
	/*******************************归属战役**********************************/	
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public Long getTbcgBattle(){
		return this.tbcgBattle;
	}
	/**
	* 获取归属战役格式化(toString)
	**/
	public String getTbcgBattle$(){
		String strValue="";
		if(this.getTbcgBattle()!=null){
				strValue+=$.str(TbSaleBattle.getKeyValue(this.getTbcgBattle()));
		}			
	 	 return strValue;
	}
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public void setTbcgBattle(Long tbcgBattle){
		this.tbcgBattle = tbcgBattle;
		this.setSeted(F.tbcgBattle);
	}
	/**
	* 获取关联对象[销售战役]
	**/	 			
 	public TbSaleBattle get$tbcgBattle(){
 		com.ccclubs.model.TbSaleBattle tbSaleBattle = (com.ccclubs.model.TbSaleBattle) $.GetRequest("TbSaleBattle$"+this.getTbcgBattle());
 		if(tbSaleBattle!=null)
			return tbSaleBattle;
		if(this.getTbcgBattle()!=null){
 			tbSaleBattle = TbSaleBattle.get(this.getTbcgBattle());
 		}
 		$.SetRequest("TbSaleBattle$"+this.getTbcgBattle(), tbSaleBattle);
	 	return tbSaleBattle;
	}
	/*******************************归属活动**********************************/	
	/**
	* 归属活动 [非空] [CsSaleEvent]     
	**/
	public Long getTbcgEvent(){
		return this.tbcgEvent;
	}
	/**
	* 获取归属活动格式化(toString)
	**/
	public String getTbcgEvent$(){
		String strValue="";
		if(this.getTbcgEvent()!=null){
				strValue+=$.str(CsSaleEvent.getKeyValue(this.getTbcgEvent()));
		}			
	 	 return strValue;
	}
	/**
	* 归属活动 [非空] [CsSaleEvent]     
	**/
	public void setTbcgEvent(Long tbcgEvent){
		this.tbcgEvent = tbcgEvent;
		this.setSeted(F.tbcgEvent);
	}
	/**
	* 获取关联对象[销售活动]
	**/	 			
 	public CsSaleEvent get$tbcgEvent(){
 		com.ccclubs.model.CsSaleEvent csSaleEvent = (com.ccclubs.model.CsSaleEvent) $.GetRequest("CsSaleEvent$"+this.getTbcgEvent());
 		if(csSaleEvent!=null)
			return csSaleEvent;
		if(this.getTbcgEvent()!=null){
 			csSaleEvent = CsSaleEvent.get(this.getTbcgEvent());
 		}
 		$.SetRequest("CsSaleEvent$"+this.getTbcgEvent(), csSaleEvent);
	 	return csSaleEvent;
	}
	/*******************************组织名称**********************************/	
	/**
	* 组织名称 [非空]      
	**/
	public String getTbcgName(){
		return this.tbcgName;
	}
	/**
	* 获取组织名称格式化(toString)
	**/
	public String getTbcgName$(){
		String strValue="";
		 strValue=$.str(this.getTbcgName());
	 	 return strValue;
	}
	/**
	* 组织名称 [非空]      
	**/
	public void setTbcgName(String tbcgName){
		this.tbcgName = tbcgName;
		this.setSeted(F.tbcgName);
	}
	/*******************************组织全称**********************************/	
	/**
	* 组织全称 [非空]      
	**/
	public String getTbcgFullName(){
		return this.tbcgFullName;
	}
	/**
	* 获取组织全称格式化(toString)
	**/
	public String getTbcgFullName$(){
		String strValue="";
		 strValue=$.str(this.getTbcgFullName());
	 	 return strValue;
	}
	/**
	* 组织全称 [非空]      
	**/
	public void setTbcgFullName(String tbcgFullName){
		this.tbcgFullName = tbcgFullName;
		this.setSeted(F.tbcgFullName);
	}
	/*******************************组织类型**********************************/	
	/**
	* 组织类型 [非空]   1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它    
	**/
	public Short getTbcgType(){
		return this.tbcgType;
	}
	/**
	* 获取组织类型格式化(toString)
	**/
	public String getTbcgType$(){
		String strValue="";
		 if($.equals($.str(this.getTbcgType()),"1"))
			strValue=$.str("公司企业");		 
		 if($.equals($.str(this.getTbcgType()),"2"))
			strValue=$.str("事业单位");		 
		 if($.equals($.str(this.getTbcgType()),"3"))
			strValue=$.str("政府机构");		 
		 if($.equals($.str(this.getTbcgType()),"4"))
			strValue=$.str("社会团体");		 
		 if($.equals($.str(this.getTbcgType()),"0"))
			strValue=$.str("其它");		 
	 	 return strValue;
	}
	/**
	* 组织类型 [非空]   1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它    
	**/
	public void setTbcgType(Short tbcgType){
		this.tbcgType = tbcgType;
		this.setSeted(F.tbcgType);
	}
	/*******************************行业**********************************/	
	/**
	* 行业 [非空] [SrvProperty]  customgroup_trade:客户组织行业    
	**/
	public Long getTbcgTrade(){
		return this.tbcgTrade;
	}
	/**
	* 获取行业格式化(toString)
	**/
	public String getTbcgTrade$(){
		String strValue="";
		if(this.getTbcgTrade()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getTbcgTrade()));
		}			
	 	 return strValue;
	}
	/**
	* 行业 [非空] [SrvProperty]  customgroup_trade:客户组织行业    
	**/
	public void setTbcgTrade(Long tbcgTrade){
		this.tbcgTrade = tbcgTrade;
		this.setSeted(F.tbcgTrade);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$tbcgTrade(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getTbcgTrade());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getTbcgTrade()!=null){
 			srvProperty = SrvProperty.get(this.getTbcgTrade());
 		}
 		$.SetRequest("SrvProperty$"+this.getTbcgTrade(), srvProperty);
	 	return srvProperty;
	}
	/*******************************主营业务**********************************/	
	/**
	* 主营业务       
	**/
	public String getTbcgService(){
		return this.tbcgService;
	}
	/**
	* 获取主营业务格式化(toString)
	**/
	public String getTbcgService$(){
		String strValue="";
		 strValue=$.str(this.getTbcgService());
	 	 return strValue;
	}
	/**
	* 主营业务       
	**/
	public void setTbcgService(String tbcgService){
		this.tbcgService = tbcgService;
		this.setSeted(F.tbcgService);
	}
	/*******************************人员规模**********************************/	
	/**
	* 人员规模    0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上    
	**/
	public Short getTbcgScale(){
		return this.tbcgScale;
	}
	/**
	* 获取人员规模格式化(toString)
	**/
	public String getTbcgScale$(){
		String strValue="";
		 if($.equals($.str(this.getTbcgScale()),"0"))
			strValue=$.str("20人以内");		 
		 if($.equals($.str(this.getTbcgScale()),"1"))
			strValue=$.str("20到50人");		 
		 if($.equals($.str(this.getTbcgScale()),"2"))
			strValue=$.str("50到100人");		 
		 if($.equals($.str(this.getTbcgScale()),"3"))
			strValue=$.str("100到200人");		 
		 if($.equals($.str(this.getTbcgScale()),"4"))
			strValue=$.str("200人以上");		 
	 	 return strValue;
	}
	/**
	* 人员规模    0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上    
	**/
	public void setTbcgScale(Short tbcgScale){
		this.tbcgScale = tbcgScale;
		this.setSeted(F.tbcgScale);
	}
	/*******************************详细地址**********************************/	
	/**
	* 详细地址       
	**/
	public String getTbcgAddress(){
		return this.tbcgAddress;
	}
	/**
	* 获取详细地址格式化(toString)
	**/
	public String getTbcgAddress$(){
		String strValue="";
		 strValue=$.str(this.getTbcgAddress());
	 	 return strValue;
	}
	/**
	* 详细地址       
	**/
	public void setTbcgAddress(String tbcgAddress){
		this.tbcgAddress = tbcgAddress;
		this.setSeted(F.tbcgAddress);
	}
	/*******************************联系人**********************************/	
	/**
	* 联系人       
	**/
	public String getTbcgConcact(){
		return this.tbcgConcact;
	}
	/**
	* 获取联系人格式化(toString)
	**/
	public String getTbcgConcact$(){
		String strValue="";
		 strValue=$.str(this.getTbcgConcact());
	 	 return strValue;
	}
	/**
	* 联系人       
	**/
	public void setTbcgConcact(String tbcgConcact){
		this.tbcgConcact = tbcgConcact;
		this.setSeted(F.tbcgConcact);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话       
	**/
	public String getTbcgNumber(){
		return this.tbcgNumber;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getTbcgNumber$(){
		String strValue="";
		 strValue=$.str(this.getTbcgNumber());
	 	 return strValue;
	}
	/**
	* 联系电话       
	**/
	public void setTbcgNumber(String tbcgNumber){
		this.tbcgNumber = tbcgNumber;
		this.setSeted(F.tbcgNumber);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getTbcgDetail(){
		return this.tbcgDetail;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getTbcgDetail$(){
		String strValue="";
		 strValue=$.str(this.getTbcgDetail());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setTbcgDetail(String tbcgDetail){
		this.tbcgDetail = tbcgDetail;
		this.setSeted(F.tbcgDetail);
	}
	/*******************************所有人**********************************/	
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public String getTbcgOnwer(){
		return this.tbcgOnwer;
	}
	/**
	* 获取所有人格式化(toString)
	**/
	public String getTbcgOnwer$(){
		String strValue="";
			com.ccclubs.model.SrvUser srvUser = com.ccclubs.model.SrvUser.getSrvUser($.add("suFamily",this.getTbcgOnwer()));
		if(srvUser!=null)
			strValue+="["+$.str(srvUser.getSuRealName())+"]";
	 	 return strValue;
	}
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbcgOnwer(String tbcgOnwer){
		this.tbcgOnwer = tbcgOnwer;
		this.setSeted(F.tbcgOnwer);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbcgOnwer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbcgOnwer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbcgOnwer()!=null){
 			srvUser = SrvUser.Get($.add(SrvUser.F.suId,this.getTbcgOnwer()));
 		}
 		$.SetRequest("SrvUser$"+this.getTbcgOnwer(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbcgAdder(){
		return this.tbcgAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getTbcgAdder$(){
		String strValue="";
		if(this.getTbcgAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbcgAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbcgAdder(Long tbcgAdder){
		this.tbcgAdder = tbcgAdder;
		this.setSeted(F.tbcgAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbcgAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbcgAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbcgAdder()!=null){
 			srvUser = SrvUser.get(this.getTbcgAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getTbcgAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbcgEditor(){
		return this.tbcgEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getTbcgEditor$(){
		String strValue="";
		if(this.getTbcgEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbcgEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbcgEditor(Long tbcgEditor){
		this.tbcgEditor = tbcgEditor;
		this.setSeted(F.tbcgEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbcgEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbcgEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbcgEditor()!=null){
 			srvUser = SrvUser.get(this.getTbcgEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbcgEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getTbcgRemark(){
		return this.tbcgRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getTbcgRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbcgRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setTbcgRemark(String tbcgRemark){
		this.tbcgRemark = tbcgRemark;
		this.setSeted(F.tbcgRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getTbcgMemo(){
		return this.tbcgMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getTbcgMemo$(){
		String strValue="";
		 strValue=$.str(this.getTbcgMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setTbcgMemo(String tbcgMemo){
		this.tbcgMemo = tbcgMemo;
		this.setSeted(F.tbcgMemo);
	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据       
	**/
	public String getTbcgData(){
		return this.tbcgData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getTbcgData$(){
		String strValue="";
		 strValue=$.str(this.getTbcgData());
	 	 return strValue;
	}
	/**
	* 程序数据       
	**/
	public void setTbcgData(String tbcgData){
		this.tbcgData = tbcgData;
		this.setSeted(F.tbcgData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbcgUpdateTime(){
		return this.tbcgUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbcgUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbcgUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setTbcgUpdateTime(Date tbcgUpdateTime){
		this.tbcgUpdateTime = tbcgUpdateTime;
		this.setSeted(F.tbcgUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbcgAddTime(){
		return this.tbcgAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbcgAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbcgAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setTbcgAddTime(Date tbcgAddTime){
		this.tbcgAddTime = tbcgAddTime;
		this.setSeted(F.tbcgAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getTbcgStatus(){
		return this.tbcgStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbcgStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbcgStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getTbcgStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setTbcgStatus(Short tbcgStatus){
		this.tbcgStatus = tbcgStatus;
		this.setSeted(F.tbcgStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbCustomGroup.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbCustomGroup.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbCustomGroup.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbCustomGroup.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbCustomGroup.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbCustomGroup.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbCustomGroup.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbCustomGroup.this);
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
		public M tbcgId(Object tbcgId){this.put("tbcgId", tbcgId);return this;};
	 	/** and tbcg_id is null */
 		public M tbcgIdNull(){if(this.get("tbcgIdNot")==null)this.put("tbcgIdNot", "");this.put("tbcgId", null);return this;};
 		/** not .... */
 		public M tbcgIdNot(){this.put("tbcgIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbcgHost(Object tbcgHost){this.put("tbcgHost", tbcgHost);return this;};
	 	/** and tbcg_host is null */
 		public M tbcgHostNull(){if(this.get("tbcgHostNot")==null)this.put("tbcgHostNot", "");this.put("tbcgHost", null);return this;};
 		/** not .... */
 		public M tbcgHostNot(){this.put("tbcgHostNot", "not");return this;};
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public M tbcgBattle(Object tbcgBattle){this.put("tbcgBattle", tbcgBattle);return this;};
	 	/** and tbcg_battle is null */
 		public M tbcgBattleNull(){if(this.get("tbcgBattleNot")==null)this.put("tbcgBattleNot", "");this.put("tbcgBattle", null);return this;};
 		/** not .... */
 		public M tbcgBattleNot(){this.put("tbcgBattleNot", "not");return this;};
		public M tbcgBattle$on(TbSaleBattle.M value){
			this.put("TbSaleBattle", value);
			this.put("tbcgBattle$on", value);
			return this;
		};	
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public M tbcgEvent(Object tbcgEvent){this.put("tbcgEvent", tbcgEvent);return this;};
	 	/** and tbcg_event is null */
 		public M tbcgEventNull(){if(this.get("tbcgEventNot")==null)this.put("tbcgEventNot", "");this.put("tbcgEvent", null);return this;};
 		/** not .... */
 		public M tbcgEventNot(){this.put("tbcgEventNot", "not");return this;};
		public M tbcgEvent$on(CsSaleEvent.M value){
			this.put("CsSaleEvent", value);
			this.put("tbcgEvent$on", value);
			return this;
		};	
		/** 组织名称 [非空]       **/
		public M tbcgName(Object tbcgName){this.put("tbcgName", tbcgName);return this;};
	 	/** and tbcg_name is null */
 		public M tbcgNameNull(){if(this.get("tbcgNameNot")==null)this.put("tbcgNameNot", "");this.put("tbcgName", null);return this;};
 		/** not .... */
 		public M tbcgNameNot(){this.put("tbcgNameNot", "not");return this;};
		/** 组织全称 [非空]       **/
		public M tbcgFullName(Object tbcgFullName){this.put("tbcgFullName", tbcgFullName);return this;};
	 	/** and tbcg_full_name is null */
 		public M tbcgFullNameNull(){if(this.get("tbcgFullNameNot")==null)this.put("tbcgFullNameNot", "");this.put("tbcgFullName", null);return this;};
 		/** not .... */
 		public M tbcgFullNameNot(){this.put("tbcgFullNameNot", "not");return this;};
		/** 组织类型 [非空]   1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它     **/
		public M tbcgType(Object tbcgType){this.put("tbcgType", tbcgType);return this;};
	 	/** and tbcg_type is null */
 		public M tbcgTypeNull(){if(this.get("tbcgTypeNot")==null)this.put("tbcgTypeNot", "");this.put("tbcgType", null);return this;};
 		/** not .... */
 		public M tbcgTypeNot(){this.put("tbcgTypeNot", "not");return this;};
		/** 行业 [非空] [SrvProperty]  customgroup_trade:客户组织行业     **/
		public M tbcgTrade(Object tbcgTrade){this.put("tbcgTrade", tbcgTrade);return this;};
	 	/** and tbcg_trade is null */
 		public M tbcgTradeNull(){if(this.get("tbcgTradeNot")==null)this.put("tbcgTradeNot", "");this.put("tbcgTrade", null);return this;};
 		/** not .... */
 		public M tbcgTradeNot(){this.put("tbcgTradeNot", "not");return this;};
		public M tbcgTrade$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("tbcgTrade$on", value);
			return this;
		};	
		/** 主营业务        **/
		public M tbcgService(Object tbcgService){this.put("tbcgService", tbcgService);return this;};
	 	/** and tbcg_service is null */
 		public M tbcgServiceNull(){if(this.get("tbcgServiceNot")==null)this.put("tbcgServiceNot", "");this.put("tbcgService", null);return this;};
 		/** not .... */
 		public M tbcgServiceNot(){this.put("tbcgServiceNot", "not");return this;};
		/** 人员规模    0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上     **/
		public M tbcgScale(Object tbcgScale){this.put("tbcgScale", tbcgScale);return this;};
	 	/** and tbcg_scale is null */
 		public M tbcgScaleNull(){if(this.get("tbcgScaleNot")==null)this.put("tbcgScaleNot", "");this.put("tbcgScale", null);return this;};
 		/** not .... */
 		public M tbcgScaleNot(){this.put("tbcgScaleNot", "not");return this;};
		/** 详细地址        **/
		public M tbcgAddress(Object tbcgAddress){this.put("tbcgAddress", tbcgAddress);return this;};
	 	/** and tbcg_address is null */
 		public M tbcgAddressNull(){if(this.get("tbcgAddressNot")==null)this.put("tbcgAddressNot", "");this.put("tbcgAddress", null);return this;};
 		/** not .... */
 		public M tbcgAddressNot(){this.put("tbcgAddressNot", "not");return this;};
		/** 联系人        **/
		public M tbcgConcact(Object tbcgConcact){this.put("tbcgConcact", tbcgConcact);return this;};
	 	/** and tbcg_concact is null */
 		public M tbcgConcactNull(){if(this.get("tbcgConcactNot")==null)this.put("tbcgConcactNot", "");this.put("tbcgConcact", null);return this;};
 		/** not .... */
 		public M tbcgConcactNot(){this.put("tbcgConcactNot", "not");return this;};
		/** 联系电话        **/
		public M tbcgNumber(Object tbcgNumber){this.put("tbcgNumber", tbcgNumber);return this;};
	 	/** and tbcg_number is null */
 		public M tbcgNumberNull(){if(this.get("tbcgNumberNot")==null)this.put("tbcgNumberNot", "");this.put("tbcgNumber", null);return this;};
 		/** not .... */
 		public M tbcgNumberNot(){this.put("tbcgNumberNot", "not");return this;};
		/** 备注信息        **/
		public M tbcgDetail(Object tbcgDetail){this.put("tbcgDetail", tbcgDetail);return this;};
	 	/** and tbcg_detail is null */
 		public M tbcgDetailNull(){if(this.get("tbcgDetailNot")==null)this.put("tbcgDetailNot", "");this.put("tbcgDetail", null);return this;};
 		/** not .... */
 		public M tbcgDetailNot(){this.put("tbcgDetailNot", "not");return this;};
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbcgOnwer(Object tbcgOnwer){this.put("tbcgOnwer", tbcgOnwer);return this;};
	 	/** and tbcg_onwer is null */
 		public M tbcgOnwerNull(){if(this.get("tbcgOnwerNot")==null)this.put("tbcgOnwerNot", "");this.put("tbcgOnwer", null);return this;};
 		/** not .... */
 		public M tbcgOnwerNot(){this.put("tbcgOnwerNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbcgAdder(Object tbcgAdder){this.put("tbcgAdder", tbcgAdder);return this;};
	 	/** and tbcg_adder is null */
 		public M tbcgAdderNull(){if(this.get("tbcgAdderNot")==null)this.put("tbcgAdderNot", "");this.put("tbcgAdder", null);return this;};
 		/** not .... */
 		public M tbcgAdderNot(){this.put("tbcgAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbcgEditor(Object tbcgEditor){this.put("tbcgEditor", tbcgEditor);return this;};
	 	/** and tbcg_editor is null */
 		public M tbcgEditorNull(){if(this.get("tbcgEditorNot")==null)this.put("tbcgEditorNot", "");this.put("tbcgEditor", null);return this;};
 		/** not .... */
 		public M tbcgEditorNot(){this.put("tbcgEditorNot", "not");return this;};
		/** 备注        **/
		public M tbcgRemark(Object tbcgRemark){this.put("tbcgRemark", tbcgRemark);return this;};
	 	/** and tbcg_remark is null */
 		public M tbcgRemarkNull(){if(this.get("tbcgRemarkNot")==null)this.put("tbcgRemarkNot", "");this.put("tbcgRemark", null);return this;};
 		/** not .... */
 		public M tbcgRemarkNot(){this.put("tbcgRemarkNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M tbcgMemo(Object tbcgMemo){this.put("tbcgMemo", tbcgMemo);return this;};
	 	/** and tbcg_memo is null */
 		public M tbcgMemoNull(){if(this.get("tbcgMemoNot")==null)this.put("tbcgMemoNot", "");this.put("tbcgMemo", null);return this;};
 		/** not .... */
 		public M tbcgMemoNot(){this.put("tbcgMemoNot", "not");return this;};
		/** 程序数据        **/
		public M tbcgData(Object tbcgData){this.put("tbcgData", tbcgData);return this;};
	 	/** and tbcg_data is null */
 		public M tbcgDataNull(){if(this.get("tbcgDataNot")==null)this.put("tbcgDataNot", "");this.put("tbcgData", null);return this;};
 		/** not .... */
 		public M tbcgDataNot(){this.put("tbcgDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M tbcgUpdateTime(Object tbcgUpdateTime){this.put("tbcgUpdateTime", tbcgUpdateTime);return this;};
	 	/** and tbcg_update_time is null */
 		public M tbcgUpdateTimeNull(){if(this.get("tbcgUpdateTimeNot")==null)this.put("tbcgUpdateTimeNot", "");this.put("tbcgUpdateTime", null);return this;};
 		/** not .... */
 		public M tbcgUpdateTimeNot(){this.put("tbcgUpdateTimeNot", "not");return this;};
 		/** and tbcg_update_time >= ? */
 		public M tbcgUpdateTimeStart(Object start){this.put("tbcgUpdateTimeStart", start);return this;};			
 		/** and tbcg_update_time <= ? */
 		public M tbcgUpdateTimeEnd(Object end){this.put("tbcgUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbcgAddTime(Object tbcgAddTime){this.put("tbcgAddTime", tbcgAddTime);return this;};
	 	/** and tbcg_add_time is null */
 		public M tbcgAddTimeNull(){if(this.get("tbcgAddTimeNot")==null)this.put("tbcgAddTimeNot", "");this.put("tbcgAddTime", null);return this;};
 		/** not .... */
 		public M tbcgAddTimeNot(){this.put("tbcgAddTimeNot", "not");return this;};
 		/** and tbcg_add_time >= ? */
 		public M tbcgAddTimeStart(Object start){this.put("tbcgAddTimeStart", start);return this;};			
 		/** and tbcg_add_time <= ? */
 		public M tbcgAddTimeEnd(Object end){this.put("tbcgAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M tbcgStatus(Object tbcgStatus){this.put("tbcgStatus", tbcgStatus);return this;};
	 	/** and tbcg_status is null */
 		public M tbcgStatusNull(){if(this.get("tbcgStatusNot")==null)this.put("tbcgStatusNot", "");this.put("tbcgStatus", null);return this;};
 		/** not .... */
 		public M tbcgStatusNot(){this.put("tbcgStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有客户组织 **/
		public @api List<TbCustomGroup> list(Integer size){
			return getTbCustomGroupList(this,size);
		}
		/** 获取客户组织分页 **/
		public @api Page<TbCustomGroup> page(int page,int size){
			return getTbCustomGroupPage(page, size , this);
		}
		/** 根据查询条件取客户组织 **/
		public @api TbCustomGroup get(){
			return getTbCustomGroup(this);
		}
		/** 获取客户组织数 **/
		public @api Long count(){
			return getTbCustomGroupCount(this);
		}
		/** 获取客户组织表达式 **/
		public @api <T> T eval(String strEval){
			return getTbCustomGroupEval(strEval,this);
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
			updateTbCustomGroup(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbcgId="tbcgId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbcgHost="tbcgHost";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static @type(Long.class)  String tbcgBattle="tbcgBattle";
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public final static @type(Long.class)  String tbcgEvent="tbcgEvent";
		/** 组织名称 [非空]       **/
		public final static @type(String.class) @like String tbcgName="tbcgName";
		/** 组织全称 [非空]       **/
		public final static @type(String.class) @like String tbcgFullName="tbcgFullName";
		/** 组织类型 [非空]   1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它     **/
		public final static @type(Short.class)  String tbcgType="tbcgType";
		/** 行业 [非空] [SrvProperty]  customgroup_trade:客户组织行业     **/
		public final static @type(Long.class)  String tbcgTrade="tbcgTrade";
		/** 主营业务        **/
		public final static @type(String.class) @like String tbcgService="tbcgService";
		/** 人员规模    0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上     **/
		public final static @type(Short.class)  String tbcgScale="tbcgScale";
		/** 详细地址        **/
		public final static @type(String.class) @like String tbcgAddress="tbcgAddress";
		/** 联系人        **/
		public final static @type(String.class) @like String tbcgConcact="tbcgConcact";
		/** 联系电话        **/
		public final static @type(String.class) @like String tbcgNumber="tbcgNumber";
		/** 备注信息        **/
		public final static @type(String.class) @like String tbcgDetail="tbcgDetail";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(String.class)  String tbcgOnwer="tbcgOnwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbcgAdder="tbcgAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbcgEditor="tbcgEditor";
		/** 备注        **/
		public final static @type(String.class) @like String tbcgRemark="tbcgRemark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class) @like String tbcgMemo="tbcgMemo";
		/** 程序数据        **/
		public final static @type(String.class) @like String tbcgData="tbcgData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbcgUpdateTime="tbcgUpdateTime";
	 	/** and tbcg_update_time >= ? */
 		public final static @type(Date.class) String tbcgUpdateTimeStart="tbcgUpdateTimeStart";
 		/** and tbcg_update_time <= ? */
 		public final static @type(Date.class) String tbcgUpdateTimeEnd="tbcgUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbcgAddTime="tbcgAddTime";
	 	/** and tbcg_add_time >= ? */
 		public final static @type(Date.class) String tbcgAddTimeStart="tbcgAddTimeStart";
 		/** and tbcg_add_time <= ? */
 		public final static @type(Date.class) String tbcgAddTimeEnd="tbcgAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String tbcgStatus="tbcgStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbcgId="tbcg_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbcgHost="tbcg_host";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static String tbcgBattle="tbcg_battle";
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public final static String tbcgEvent="tbcg_event";
		/** 组织名称 [非空]       **/
		public final static String tbcgName="tbcg_name";
		/** 组织全称 [非空]       **/
		public final static String tbcgFullName="tbcg_full_name";
		/** 组织类型 [非空]   1:公司企业 2:事业单位 3:政府机构 4:社会团体 0:其它     **/
		public final static String tbcgType="tbcg_type";
		/** 行业 [非空] [SrvProperty]  customgroup_trade:客户组织行业     **/
		public final static String tbcgTrade="tbcg_trade";
		/** 主营业务        **/
		public final static String tbcgService="tbcg_service";
		/** 人员规模    0:20人以内 1:20到50人 2:50到100人 3:100到200人 4:200人以上     **/
		public final static String tbcgScale="tbcg_scale";
		/** 详细地址        **/
		public final static String tbcgAddress="tbcg_address";
		/** 联系人        **/
		public final static String tbcgConcact="tbcg_concact";
		/** 联系电话        **/
		public final static String tbcgNumber="tbcg_number";
		/** 备注信息        **/
		public final static String tbcgDetail="tbcg_detail";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbcgOnwer="tbcg_onwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbcgAdder="tbcg_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbcgEditor="tbcg_editor";
		/** 备注        **/
		public final static String tbcgRemark="tbcg_remark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String tbcgMemo="tbcg_memo";
		/** 程序数据        **/
		public final static String tbcgData="tbcg_data";
		/** 修改时间 [非空]       **/
		public final static String tbcgUpdateTime="tbcg_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbcgAddTime="tbcg_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String tbcgStatus="tbcg_status";
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
				$.Set(name,TbCustomGroup.getTbCustomGroup(params));
			else
				$.Set(name,TbCustomGroup.getTbCustomGroupList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取客户组织数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbCustomGroup) $.GetRequest("TbCustomGroup$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbCustomGroup.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbCustomGroup.getTbCustomGroup(params);
			else
				value = TbCustomGroup.getTbCustomGroupList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbCustomGroup.Get($.add(TbCustomGroup.F.tbcgId,param));
		else if(!$.empty(param.toString()))
			value = TbCustomGroup.get(Long.valueOf(param.toString()));
		$.SetRequest("TbCustomGroup$"+param.hashCode(), value);
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
	public void mergeSet(TbCustomGroup old){
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