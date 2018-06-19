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

@namespace("sale/custom")
public @caption("客户资料") @table("tb_sale_custom") class TbSaleCustom implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbsc_id")   @primary  @note("  ") Long tbscId;// 主键 非空     
	private @caption("城市") @column("tbsc_host")    @relate("$tbscHost") @RelateClass(SrvHost.class)  @note("  ") Long tbscHost;// 非空     
 	private SrvHost $tbscHost;//关联对象[运营城市]
	private @caption("客户名称") @column("tbsc_name")    @note("  ") String tbscName;// 非空     
	private @caption("归属战役") @column("tbsc_battle")    @relate("$tbscBattle") @RelateClass(TbSaleBattle.class)  @note("  ") Long tbscBattle;// 非空     
 	private TbSaleBattle $tbscBattle;//关联对象[销售战役]
	private @caption("归属活动") @column("tbsc_event")    @relate("$tbscEvent") @RelateClass(CsSaleEvent.class)  @note("  ") Long tbscEvent;// 非空     
 	private CsSaleEvent $tbscEvent;//关联对象[销售活动]
	private @caption("所属组织") @column("tbsc_group")    @relate("$tbscGroup") @RelateClass(TbCustomGroup.class)  @note("  ") Long tbscGroup;// 非空     
 	private TbCustomGroup $tbscGroup;//关联对象[客户组织]
	private @caption("联系手机") @column("tbsc_mobile")    @note("  ") String tbscMobile;// 非空     
	private @caption("联系电话") @column("tbsc_number")    @note("  ") String tbscNumber;//     
	private @caption("联系邮箱") @column("tbsc_email")    @note("  ") String tbscEmail;//     
	private @caption("联系QQ") @column("tbsc_qq")    @note("  ") String tbscQq;//     
	private @caption("其它联系方式") @column("tbsc_othrer")    @note("  ") String tbscOthrer;//     
	private @caption("业务方向") @column("tbsc_profile")    @note("  描述将会和此客户将来会有什么业务方面的合作") String tbscProfile;// 非空  描述将会和此客户将来会有什么业务方面的合作   
	private @caption("会员级别") @column("tbsc_level")    @note(" 0:普通客户 1:重要客户 2:集团大客户 3:政府大客户  ") Short tbscLevel;// 非空 0:普通客户 1:重要客户 2:集团大客户 3:政府大客户     
	private @caption("意向阶段") @column("tbsc_formal")    @note(" 0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效  ") Short tbscFormal;// 非空 0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效     
	private @caption("服务阶段") @column("tbsc_server")    @note("  ") Short tbscServer;// 非空     
	private @caption("所有人") @column("tbsc_onwer")    @relate("$tbscOnwer") @RelateClass(SrvUser.class)  @note("  ") String tbscOnwer;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbscOnwer;//关联对象[用户]
	private @caption("添加人") @column("tbsc_adder")    @relate("$tbscAdder") @RelateClass(SrvUser.class)  @note("  ") Long tbscAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbscAdder;//关联对象[用户]
	private @caption("修改人") @column("tbsc_editor")  @hidden   @relate("$tbscEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbscEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbscEditor;//关联对象[用户]
	private @caption("备注") @column("tbsc_remark")    @note("  ") String tbscRemark;//     
	private @caption("备忘信息") @column("tbsc_memo")    @note(" textarea:content text:sign  ") String tbscMemo;// textarea:content text:sign     
	private @caption("程序数据") @column("tbsc_data")    @note("  ") String tbscData;//     
	private @caption("修改时间") @column("tbsc_update_time")    @note("  ") Date tbscUpdateTime;// 非空     
	private @caption("添加时间") @column("tbsc_add_time")    @note("  ") Date tbscAddTime;// 非空     
	private @caption("状态") @column("tbsc_status")    @note(" 1:正常 0:无效  ") Short tbscStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public TbSaleCustom(){
	
	}
	
	//主键构造函数
	public TbSaleCustom(Long id){
		this.tbscId = id;
	}
	
	/**所有字段构造函数 TbSaleCustom(tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscNumber,tbscEmail,tbscQq,tbscOthrer,tbscProfile,tbscLevel,tbscFormal,tbscServer,tbscOnwer,tbscAdder,tbscEditor,tbscRemark,tbscMemo,tbscData,tbscUpdateTime,tbscAddTime,tbscStatus)
	 TbSaleCustom(
	 	$.getLong("tbscHost")//城市 [非空]
	 	,$.getString("tbscName")//客户名称 [非空]
	 	,$.getLong("tbscBattle")//归属战役 [非空]
	 	,$.getLong("tbscEvent")//归属活动 [非空]
	 	,$.getLong("tbscGroup")//所属组织 [非空]
	 	,$.getString("tbscMobile")//联系手机 [非空]
	 	,$.getString("tbscNumber")//联系电话
	 	,$.getString("tbscEmail")//联系邮箱
	 	,$.getString("tbscQq")//联系QQ
	 	,$.getString("tbscOthrer")//其它联系方式
	 	,$.getString("tbscProfile")//业务方向 [非空]
	 	,$.getShort("tbscLevel")//会员级别 [非空]
	 	,$.getShort("tbscFormal")//意向阶段 [非空]
	 	,$.getShort("tbscServer")//服务阶段 [非空]
	 	,$.getString("tbscOnwer")//所有人 [非空]
	 	,$.getLong("tbscAdder")//添加人 [非空]
	 	,$.getLong("tbscEditor")//修改人 [非空]
	 	,$.getString("tbscRemark")//备注
	 	,$.getString("tbscMemo")//备忘信息
	 	,$.getString("tbscData")//程序数据
	 	,$.getDate("tbscUpdateTime")//修改时间 [非空]
	 	,$.getDate("tbscAddTime")//添加时间 [非空]
	 	,$.getShort("tbscStatus")//状态 [非空]
	 )
	**/
	public TbSaleCustom(Long tbscHost,String tbscName,Long tbscBattle,Long tbscEvent,Long tbscGroup,String tbscMobile,String tbscNumber,String tbscEmail,String tbscQq,String tbscOthrer,String tbscProfile,Short tbscLevel,Short tbscFormal,Short tbscServer,String tbscOnwer,Long tbscAdder,Long tbscEditor,String tbscRemark,String tbscMemo,String tbscData,Date tbscUpdateTime,Date tbscAddTime,Short tbscStatus){
		this.tbscHost=tbscHost;
		this.tbscName=tbscName;
		this.tbscBattle=tbscBattle;
		this.tbscEvent=tbscEvent;
		this.tbscGroup=tbscGroup;
		this.tbscMobile=tbscMobile;
		this.tbscNumber=tbscNumber;
		this.tbscEmail=tbscEmail;
		this.tbscQq=tbscQq;
		this.tbscOthrer=tbscOthrer;
		this.tbscProfile=tbscProfile;
		this.tbscLevel=tbscLevel;
		this.tbscFormal=tbscFormal;
		this.tbscServer=tbscServer;
		this.tbscOnwer=tbscOnwer;
		this.tbscAdder=tbscAdder;
		this.tbscEditor=tbscEditor;
		this.tbscRemark=tbscRemark;
		this.tbscMemo=tbscMemo;
		this.tbscData=tbscData;
		this.tbscUpdateTime=tbscUpdateTime;
		this.tbscAddTime=tbscAddTime;
		this.tbscStatus=tbscStatus;
	}
	
	//设置非空字段
	public TbSaleCustom setNotNull(Long tbscHost,String tbscName,Long tbscBattle,Long tbscEvent,Long tbscGroup,String tbscMobile,String tbscProfile,Short tbscLevel,Short tbscFormal,Short tbscServer,String tbscOnwer,Long tbscAdder,Long tbscEditor,Date tbscUpdateTime,Date tbscAddTime,Short tbscStatus){
		this.tbscHost=tbscHost;
		this.tbscName=tbscName;
		this.tbscBattle=tbscBattle;
		this.tbscEvent=tbscEvent;
		this.tbscGroup=tbscGroup;
		this.tbscMobile=tbscMobile;
		this.tbscProfile=tbscProfile;
		this.tbscLevel=tbscLevel;
		this.tbscFormal=tbscFormal;
		this.tbscServer=tbscServer;
		this.tbscOnwer=tbscOnwer;
		this.tbscAdder=tbscAdder;
		this.tbscEditor=tbscEditor;
		this.tbscUpdateTime=tbscUpdateTime;
		this.tbscAddTime=tbscAddTime;
		this.tbscStatus=tbscStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbSaleCustom tbscId(Long tbscId){
		this.tbscId = tbscId;
		this.setSeted(F.tbscId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbSaleCustom tbscHost(Long tbscHost){
		this.tbscHost = tbscHost;
		this.setSeted(F.tbscHost);
		return this;
	}
	/** 客户名称 [非空]       **/
	public TbSaleCustom tbscName(String tbscName){
		this.tbscName = tbscName;
		this.setSeted(F.tbscName);
		return this;
	}
	/** 归属战役 [非空] [TbSaleBattle]      **/
	public TbSaleCustom tbscBattle(Long tbscBattle){
		this.tbscBattle = tbscBattle;
		this.setSeted(F.tbscBattle);
		return this;
	}
	/** 归属活动 [非空] [CsSaleEvent]      **/
	public TbSaleCustom tbscEvent(Long tbscEvent){
		this.tbscEvent = tbscEvent;
		this.setSeted(F.tbscEvent);
		return this;
	}
	/** 所属组织 [非空] [TbCustomGroup]      **/
	public TbSaleCustom tbscGroup(Long tbscGroup){
		this.tbscGroup = tbscGroup;
		this.setSeted(F.tbscGroup);
		return this;
	}
	/** 联系手机 [非空]       **/
	public TbSaleCustom tbscMobile(String tbscMobile){
		this.tbscMobile = tbscMobile;
		this.setSeted(F.tbscMobile);
		return this;
	}
	/** 联系电话        **/
	public TbSaleCustom tbscNumber(String tbscNumber){
		this.tbscNumber = tbscNumber;
		this.setSeted(F.tbscNumber);
		return this;
	}
	/** 联系邮箱        **/
	public TbSaleCustom tbscEmail(String tbscEmail){
		this.tbscEmail = tbscEmail;
		this.setSeted(F.tbscEmail);
		return this;
	}
	/** 联系QQ        **/
	public TbSaleCustom tbscQq(String tbscQq){
		this.tbscQq = tbscQq;
		this.setSeted(F.tbscQq);
		return this;
	}
	/** 其它联系方式        **/
	public TbSaleCustom tbscOthrer(String tbscOthrer){
		this.tbscOthrer = tbscOthrer;
		this.setSeted(F.tbscOthrer);
		return this;
	}
	/** 业务方向 [非空]    描述将会和此客户将来会有什么业务方面的合作   **/
	public TbSaleCustom tbscProfile(String tbscProfile){
		this.tbscProfile = tbscProfile;
		this.setSeted(F.tbscProfile);
		return this;
	}
	/** 会员级别 [非空]   0:普通客户 1:重要客户 2:集团大客户 3:政府大客户     **/
	public TbSaleCustom tbscLevel(Short tbscLevel){
		this.tbscLevel = tbscLevel;
		this.setSeted(F.tbscLevel);
		return this;
	}
	/** 意向阶段 [非空]   0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效     **/
	public TbSaleCustom tbscFormal(Short tbscFormal){
		this.tbscFormal = tbscFormal;
		this.setSeted(F.tbscFormal);
		return this;
	}
	/** 服务阶段 [非空]       **/
	public TbSaleCustom tbscServer(Short tbscServer){
		this.tbscServer = tbscServer;
		this.setSeted(F.tbscServer);
		return this;
	}
	/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleCustom tbscOnwer(String tbscOnwer){
		this.tbscOnwer = tbscOnwer;
		this.setSeted(F.tbscOnwer);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleCustom tbscAdder(Long tbscAdder){
		this.tbscAdder = tbscAdder;
		this.setSeted(F.tbscAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleCustom tbscEditor(Long tbscEditor){
		this.tbscEditor = tbscEditor;
		this.setSeted(F.tbscEditor);
		return this;
	}
	/** 备注        **/
	public TbSaleCustom tbscRemark(String tbscRemark){
		this.tbscRemark = tbscRemark;
		this.setSeted(F.tbscRemark);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public TbSaleCustom tbscMemo(String tbscMemo){
		this.tbscMemo = tbscMemo;
		this.setSeted(F.tbscMemo);
		return this;
	}
	/** 程序数据        **/
	public TbSaleCustom tbscData(String tbscData){
		this.tbscData = tbscData;
		this.setSeted(F.tbscData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbSaleCustom tbscUpdateTime(Date tbscUpdateTime){
		this.tbscUpdateTime = tbscUpdateTime;
		this.setSeted(F.tbscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbSaleCustom tbscAddTime(Date tbscAddTime){
		this.tbscAddTime = tbscAddTime;
		this.setSeted(F.tbscAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public TbSaleCustom tbscStatus(Short tbscStatus){
		this.tbscStatus = tbscStatus;
		this.setSeted(F.tbscStatus);
		return this;
	}
	
	
	//克隆对象
	public TbSaleCustom clone(){
		TbSaleCustom clone = new TbSaleCustom();
		clone.tbscHost=this.tbscHost;
		clone.tbscName=this.tbscName;
		clone.tbscBattle=this.tbscBattle;
		clone.tbscEvent=this.tbscEvent;
		clone.tbscGroup=this.tbscGroup;
		clone.tbscMobile=this.tbscMobile;
		clone.tbscProfile=this.tbscProfile;
		clone.tbscLevel=this.tbscLevel;
		clone.tbscFormal=this.tbscFormal;
		clone.tbscServer=this.tbscServer;
		clone.tbscOnwer=this.tbscOnwer;
		clone.tbscAdder=this.tbscAdder;
		clone.tbscEditor=this.tbscEditor;
		clone.tbscUpdateTime=this.tbscUpdateTime;
		clone.tbscAddTime=this.tbscAddTime;
		clone.tbscStatus=this.tbscStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取客户资料
	 * @param id
	 * @return
	 */
	public static @api TbSaleCustom get(Long id){		
		return getTbSaleCustomById(id);
	}
	/**
	 * 获取所有客户资料
	 * @return
	 */
	public static @api List<TbSaleCustom> list(Map params,Integer size){
		return getTbSaleCustomList(params,size);
	}
	/**
	 * 获取客户资料分页
	 * @return
	 */
	public static @api Page<TbSaleCustom> page(int page,int size,Map params){
		return getTbSaleCustomPage(page, size , params);
	}
	/**
	 * 根据查询条件取客户资料
	 * @param params
	 * @return
	 */
	public static @api TbSaleCustom Get(Map params){
		return getTbSaleCustom(params);
	}
	/**
	 * 获取客户资料数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbSaleCustomCount(params);
	}
	/**
	 * 获取客户资料数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbSaleCustomEval(eval,params);
	}
	
	/**
	 * 根据ID取客户资料
	 * @param id
	 * @return
	 */
	public static @api TbSaleCustom getTbSaleCustomById(Long id){		
		TbSaleCustom tbSaleCustom = (TbSaleCustom) $.GetRequest("TbSaleCustom$"+id);
		if(tbSaleCustom!=null)
			return tbSaleCustom;
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");		
		return tbSaleCustomService.getTbSaleCustomById(id);
	}
	
	
	/**
	 * 根据ID取客户资料的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbSaleCustom.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbSaleCustom tbSaleCustom = get(id);
		if(tbSaleCustom!=null){
			String strValue = tbSaleCustom.getTbscName$();
			if(!"TbscName".equals("TbscName"))
				strValue+="("+tbSaleCustom.getTbscName$()+")";
			MemCache.setValue(TbSaleCustom.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbscName$();
		if(!"TbscName".equals("TbscName"))
			keyValue+="("+this.getTbscName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有客户资料
	 * @return
	 */
	public static @api List<TbSaleCustom> getTbSaleCustomList(Map params,Integer size){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		return tbSaleCustomService.getTbSaleCustomList(params, size);
	}
	
	/**
	 * 获取客户资料分页
	 * @return
	 */
	public static @api Page<TbSaleCustom> getTbSaleCustomPage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		return tbSaleCustomService.getTbSaleCustomPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取客户资料
	 * @param params
	 * @return
	 */
	public static @api TbSaleCustom getTbSaleCustom(Map params){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		return tbSaleCustomService.getTbSaleCustom(params);
	}
	
	/**
	 * 获取客户资料数
	 * @return
	 */
	public static @api Long getTbSaleCustomCount(Map params){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		return tbSaleCustomService.getTbSaleCustomCount(params);
	}
		
		
	/**
	 * 获取客户资料自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbSaleCustomEval(String eval,Map params){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		return tbSaleCustomService.getTbSaleCustomEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbSaleCustom(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		tbSaleCustomService.updateTbSaleCustomByConfirm(set, where);
	}
	
	
	/**
	 * 保存客户资料对象
	 * @param params
	 * @return
	 */
	public TbSaleCustom save(){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		if(this.getTbscId()!=null)
			tbSaleCustomService.updateTbSaleCustom(this);
		else
			return tbSaleCustomService.saveTbSaleCustom(this);
		return this;
	}
	
	
	/**
	 * 更新客户资料对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		tbSaleCustomService.updateTbSaleCustom$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		if($.equals($.config("logic_delete"),"true"))
			tbSaleCustomService.removeTbSaleCustomById(this.getTbscId());
		else
			tbSaleCustomService.deleteTbSaleCustomById(this.getTbscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbSaleCustomService tbSaleCustomService = $.GetSpringBean("tbSaleCustomService");
		return tbSaleCustomService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbscId(){
		return this.tbscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbscId$(){
		String strValue="";
		 strValue=$.str(this.getTbscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbscId(Long tbscId){
		this.tbscId = tbscId;
		this.setSeted(F.tbscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbscHost(){
		return this.tbscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbscHost$(){
		String strValue="";
		if(this.getTbscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbscHost(Long tbscHost){
		this.tbscHost = tbscHost;
		this.setSeted(F.tbscHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbscHost()!=null){
 			srvHost = SrvHost.get(this.getTbscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************客户名称**********************************/	
	/**
	* 客户名称 [非空]      
	**/
	public String getTbscName(){
		return this.tbscName;
	}
	/**
	* 获取客户名称格式化(toString)
	**/
	public String getTbscName$(){
		String strValue="";
		 strValue=$.str(this.getTbscName());
	 	 return strValue;
	}
	/**
	* 客户名称 [非空]      
	**/
	public void setTbscName(String tbscName){
		this.tbscName = tbscName;
		this.setSeted(F.tbscName);
	}
	/*******************************归属战役**********************************/	
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public Long getTbscBattle(){
		return this.tbscBattle;
	}
	/**
	* 获取归属战役格式化(toString)
	**/
	public String getTbscBattle$(){
		String strValue="";
		if(this.getTbscBattle()!=null){
				strValue+=$.str(TbSaleBattle.getKeyValue(this.getTbscBattle()));
		}			
	 	 return strValue;
	}
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public void setTbscBattle(Long tbscBattle){
		this.tbscBattle = tbscBattle;
		this.setSeted(F.tbscBattle);
	}
	/**
	* 获取关联对象[销售战役]
	**/	 			
 	public TbSaleBattle get$tbscBattle(){
 		com.ccclubs.model.TbSaleBattle tbSaleBattle = (com.ccclubs.model.TbSaleBattle) $.GetRequest("TbSaleBattle$"+this.getTbscBattle());
 		if(tbSaleBattle!=null)
			return tbSaleBattle;
		if(this.getTbscBattle()!=null){
 			tbSaleBattle = TbSaleBattle.get(this.getTbscBattle());
 		}
 		$.SetRequest("TbSaleBattle$"+this.getTbscBattle(), tbSaleBattle);
	 	return tbSaleBattle;
	}
	/*******************************归属活动**********************************/	
	/**
	* 归属活动 [非空] [CsSaleEvent]     
	**/
	public Long getTbscEvent(){
		return this.tbscEvent;
	}
	/**
	* 获取归属活动格式化(toString)
	**/
	public String getTbscEvent$(){
		String strValue="";
		if(this.getTbscEvent()!=null){
				strValue+=$.str(CsSaleEvent.getKeyValue(this.getTbscEvent()));
		}			
	 	 return strValue;
	}
	/**
	* 归属活动 [非空] [CsSaleEvent]     
	**/
	public void setTbscEvent(Long tbscEvent){
		this.tbscEvent = tbscEvent;
		this.setSeted(F.tbscEvent);
	}
	/**
	* 获取关联对象[销售活动]
	**/	 			
 	public CsSaleEvent get$tbscEvent(){
 		com.ccclubs.model.CsSaleEvent csSaleEvent = (com.ccclubs.model.CsSaleEvent) $.GetRequest("CsSaleEvent$"+this.getTbscEvent());
 		if(csSaleEvent!=null)
			return csSaleEvent;
		if(this.getTbscEvent()!=null){
 			csSaleEvent = CsSaleEvent.get(this.getTbscEvent());
 		}
 		$.SetRequest("CsSaleEvent$"+this.getTbscEvent(), csSaleEvent);
	 	return csSaleEvent;
	}
	/*******************************所属组织**********************************/	
	/**
	* 所属组织 [非空] [TbCustomGroup]     
	**/
	public Long getTbscGroup(){
		return this.tbscGroup;
	}
	/**
	* 获取所属组织格式化(toString)
	**/
	public String getTbscGroup$(){
		String strValue="";
		if(this.getTbscGroup()!=null){
				strValue+=$.str(TbCustomGroup.getKeyValue(this.getTbscGroup()));
		}			
	 	 return strValue;
	}
	/**
	* 所属组织 [非空] [TbCustomGroup]     
	**/
	public void setTbscGroup(Long tbscGroup){
		this.tbscGroup = tbscGroup;
		this.setSeted(F.tbscGroup);
	}
	/**
	* 获取关联对象[客户组织]
	**/	 			
 	public TbCustomGroup get$tbscGroup(){
 		com.ccclubs.model.TbCustomGroup tbCustomGroup = (com.ccclubs.model.TbCustomGroup) $.GetRequest("TbCustomGroup$"+this.getTbscGroup());
 		if(tbCustomGroup!=null)
			return tbCustomGroup;
		if(this.getTbscGroup()!=null){
 			tbCustomGroup = TbCustomGroup.get(this.getTbscGroup());
 		}
 		$.SetRequest("TbCustomGroup$"+this.getTbscGroup(), tbCustomGroup);
	 	return tbCustomGroup;
	}
	/*******************************联系手机**********************************/	
	/**
	* 联系手机 [非空]      
	**/
	public String getTbscMobile(){
		return this.tbscMobile;
	}
	/**
	* 获取联系手机格式化(toString)
	**/
	public String getTbscMobile$(){
		String strValue="";
		 strValue=$.str(this.getTbscMobile());
	 	 return strValue;
	}
	/**
	* 联系手机 [非空]      
	**/
	public void setTbscMobile(String tbscMobile){
		this.tbscMobile = tbscMobile;
		this.setSeted(F.tbscMobile);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话       
	**/
	public String getTbscNumber(){
		return this.tbscNumber;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getTbscNumber$(){
		String strValue="";
		 strValue=$.str(this.getTbscNumber());
	 	 return strValue;
	}
	/**
	* 联系电话       
	**/
	public void setTbscNumber(String tbscNumber){
		this.tbscNumber = tbscNumber;
		this.setSeted(F.tbscNumber);
	}
	/*******************************联系邮箱**********************************/	
	/**
	* 联系邮箱       
	**/
	public String getTbscEmail(){
		return this.tbscEmail;
	}
	/**
	* 获取联系邮箱格式化(toString)
	**/
	public String getTbscEmail$(){
		String strValue="";
		 strValue=$.str(this.getTbscEmail());
	 	 return strValue;
	}
	/**
	* 联系邮箱       
	**/
	public void setTbscEmail(String tbscEmail){
		this.tbscEmail = tbscEmail;
		this.setSeted(F.tbscEmail);
	}
	/*******************************联系QQ**********************************/	
	/**
	* 联系QQ       
	**/
	public String getTbscQq(){
		return this.tbscQq;
	}
	/**
	* 获取联系QQ格式化(toString)
	**/
	public String getTbscQq$(){
		String strValue="";
		 strValue=$.str(this.getTbscQq());
	 	 return strValue;
	}
	/**
	* 联系QQ       
	**/
	public void setTbscQq(String tbscQq){
		this.tbscQq = tbscQq;
		this.setSeted(F.tbscQq);
	}
	/*******************************其它联系方式**********************************/	
	/**
	* 其它联系方式       
	**/
	public String getTbscOthrer(){
		return this.tbscOthrer;
	}
	/**
	* 获取其它联系方式格式化(toString)
	**/
	public String getTbscOthrer$(){
		String strValue="";
		 strValue=$.str(this.getTbscOthrer());
	 	 return strValue;
	}
	/**
	* 其它联系方式       
	**/
	public void setTbscOthrer(String tbscOthrer){
		this.tbscOthrer = tbscOthrer;
		this.setSeted(F.tbscOthrer);
	}
	/*******************************业务方向**********************************/	
	/**
	* 业务方向 [非空]    描述将会和此客户将来会有什么业务方面的合作  
	**/
	public String getTbscProfile(){
		return this.tbscProfile;
	}
	/**
	* 获取业务方向格式化(toString)
	**/
	public String getTbscProfile$(){
		String strValue="";
		 strValue=$.str(this.getTbscProfile());
	 	 return strValue;
	}
	/**
	* 业务方向 [非空]    描述将会和此客户将来会有什么业务方面的合作  
	**/
	public void setTbscProfile(String tbscProfile){
		this.tbscProfile = tbscProfile;
		this.setSeted(F.tbscProfile);
	}
	/*******************************会员级别**********************************/	
	/**
	* 会员级别 [非空]   0:普通客户 1:重要客户 2:集团大客户 3:政府大客户    
	**/
	public Short getTbscLevel(){
		return this.tbscLevel;
	}
	/**
	* 获取会员级别格式化(toString)
	**/
	public String getTbscLevel$(){
		String strValue="";
		 if($.equals($.str(this.getTbscLevel()),"0"))
			strValue=$.str("普通客户");		 
		 if($.equals($.str(this.getTbscLevel()),"1"))
			strValue=$.str("重要客户");		 
		 if($.equals($.str(this.getTbscLevel()),"2"))
			strValue=$.str("集团大客户");		 
		 if($.equals($.str(this.getTbscLevel()),"3"))
			strValue=$.str("政府大客户");		 
	 	 return strValue;
	}
	/**
	* 会员级别 [非空]   0:普通客户 1:重要客户 2:集团大客户 3:政府大客户    
	**/
	public void setTbscLevel(Short tbscLevel){
		this.tbscLevel = tbscLevel;
		this.setSeted(F.tbscLevel);
	}
	/*******************************意向阶段**********************************/	
	/**
	* 意向阶段 [非空]   0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效    
	**/
	public Short getTbscFormal(){
		return this.tbscFormal;
	}
	/**
	* 获取意向阶段格式化(toString)
	**/
	public String getTbscFormal$(){
		String strValue="";
		 if($.equals($.str(this.getTbscFormal()),"0"))
			strValue=$.str("暂无意向");		 
		 if($.equals($.str(this.getTbscFormal()),"1"))
			strValue=$.str("意向一般");		 
		 if($.equals($.str(this.getTbscFormal()),"2"))
			strValue=$.str("意向强烈");		 
		 if($.equals($.str(this.getTbscFormal()),"3"))
			strValue=$.str("售前跟踪");		 
		 if($.equals($.str(this.getTbscFormal()),"4"))
			strValue=$.str("合同执行");		 
		 if($.equals($.str(this.getTbscFormal()),"5"))
			strValue=$.str("售后服务");		 
		 if($.equals($.str(this.getTbscFormal()),"6"))
			strValue=$.str("合同期满");		 
		 if($.equals($.str(this.getTbscFormal()),"7"))
			strValue=$.str("失败");		 
		 if($.equals($.str(this.getTbscFormal()),"8"))
			strValue=$.str("搁置");		 
		 if($.equals($.str(this.getTbscFormal()),"9"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 意向阶段 [非空]   0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效    
	**/
	public void setTbscFormal(Short tbscFormal){
		this.tbscFormal = tbscFormal;
		this.setSeted(F.tbscFormal);
	}
	/*******************************服务阶段**********************************/	
	/**
	* 服务阶段 [非空]      
	**/
	public Short getTbscServer(){
		return this.tbscServer;
	}
	/**
	* 获取服务阶段格式化(toString)
	**/
	public String getTbscServer$(){
		String strValue="";
		 strValue=$.str(this.getTbscServer());
	 	 return strValue;
	}
	/**
	* 服务阶段 [非空]      
	**/
	public void setTbscServer(Short tbscServer){
		this.tbscServer = tbscServer;
		this.setSeted(F.tbscServer);
	}
	/*******************************所有人**********************************/	
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public String getTbscOnwer(){
		return this.tbscOnwer;
	}
	/**
	* 获取所有人格式化(toString)
	**/
	public String getTbscOnwer$(){
		String strValue="";
			com.ccclubs.model.SrvUser srvUser = com.ccclubs.model.SrvUser.getSrvUser($.add("suFamily",this.getTbscOnwer()));
		if(srvUser!=null)
			strValue+="["+$.str(srvUser.getSuRealName())+"]";
	 	 return strValue;
	}
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbscOnwer(String tbscOnwer){
		this.tbscOnwer = tbscOnwer;
		this.setSeted(F.tbscOnwer);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbscOnwer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbscOnwer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbscOnwer()!=null){
 			srvUser = SrvUser.Get($.add(SrvUser.F.suId,this.getTbscOnwer()));
 		}
 		$.SetRequest("SrvUser$"+this.getTbscOnwer(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbscAdder(){
		return this.tbscAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getTbscAdder$(){
		String strValue="";
		if(this.getTbscAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbscAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbscAdder(Long tbscAdder){
		this.tbscAdder = tbscAdder;
		this.setSeted(F.tbscAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbscAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbscAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbscAdder()!=null){
 			srvUser = SrvUser.get(this.getTbscAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getTbscAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbscEditor(){
		return this.tbscEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getTbscEditor$(){
		String strValue="";
		if(this.getTbscEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbscEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbscEditor(Long tbscEditor){
		this.tbscEditor = tbscEditor;
		this.setSeted(F.tbscEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbscEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbscEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbscEditor()!=null){
 			srvUser = SrvUser.get(this.getTbscEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbscEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getTbscRemark(){
		return this.tbscRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getTbscRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbscRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setTbscRemark(String tbscRemark){
		this.tbscRemark = tbscRemark;
		this.setSeted(F.tbscRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getTbscMemo(){
		return this.tbscMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getTbscMemo$(){
		String strValue="";
		 strValue=$.str(this.getTbscMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setTbscMemo(String tbscMemo){
		this.tbscMemo = tbscMemo;
		this.setSeted(F.tbscMemo);
	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据       
	**/
	public String getTbscData(){
		return this.tbscData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getTbscData$(){
		String strValue="";
		 strValue=$.str(this.getTbscData());
	 	 return strValue;
	}
	/**
	* 程序数据       
	**/
	public void setTbscData(String tbscData){
		this.tbscData = tbscData;
		this.setSeted(F.tbscData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbscUpdateTime(){
		return this.tbscUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setTbscUpdateTime(Date tbscUpdateTime){
		this.tbscUpdateTime = tbscUpdateTime;
		this.setSeted(F.tbscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbscAddTime(){
		return this.tbscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setTbscAddTime(Date tbscAddTime){
		this.tbscAddTime = tbscAddTime;
		this.setSeted(F.tbscAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getTbscStatus(){
		return this.tbscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbscStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getTbscStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setTbscStatus(Short tbscStatus){
		this.tbscStatus = tbscStatus;
		this.setSeted(F.tbscStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbSaleCustom.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleCustom.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleCustom.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbSaleCustom.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbSaleCustom.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleCustom.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleCustom.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbSaleCustom.this);
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
		public M tbscId(Object tbscId){this.put("tbscId", tbscId);return this;};
	 	/** and tbsc_id is null */
 		public M tbscIdNull(){if(this.get("tbscIdNot")==null)this.put("tbscIdNot", "");this.put("tbscId", null);return this;};
 		/** not .... */
 		public M tbscIdNot(){this.put("tbscIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbscHost(Object tbscHost){this.put("tbscHost", tbscHost);return this;};
	 	/** and tbsc_host is null */
 		public M tbscHostNull(){if(this.get("tbscHostNot")==null)this.put("tbscHostNot", "");this.put("tbscHost", null);return this;};
 		/** not .... */
 		public M tbscHostNot(){this.put("tbscHostNot", "not");return this;};
		/** 客户名称 [非空]       **/
		public M tbscName(Object tbscName){this.put("tbscName", tbscName);return this;};
	 	/** and tbsc_name is null */
 		public M tbscNameNull(){if(this.get("tbscNameNot")==null)this.put("tbscNameNot", "");this.put("tbscName", null);return this;};
 		/** not .... */
 		public M tbscNameNot(){this.put("tbscNameNot", "not");return this;};
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public M tbscBattle(Object tbscBattle){this.put("tbscBattle", tbscBattle);return this;};
	 	/** and tbsc_battle is null */
 		public M tbscBattleNull(){if(this.get("tbscBattleNot")==null)this.put("tbscBattleNot", "");this.put("tbscBattle", null);return this;};
 		/** not .... */
 		public M tbscBattleNot(){this.put("tbscBattleNot", "not");return this;};
		public M tbscBattle$on(TbSaleBattle.M value){
			this.put("TbSaleBattle", value);
			this.put("tbscBattle$on", value);
			return this;
		};	
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public M tbscEvent(Object tbscEvent){this.put("tbscEvent", tbscEvent);return this;};
	 	/** and tbsc_event is null */
 		public M tbscEventNull(){if(this.get("tbscEventNot")==null)this.put("tbscEventNot", "");this.put("tbscEvent", null);return this;};
 		/** not .... */
 		public M tbscEventNot(){this.put("tbscEventNot", "not");return this;};
		public M tbscEvent$on(CsSaleEvent.M value){
			this.put("CsSaleEvent", value);
			this.put("tbscEvent$on", value);
			return this;
		};	
		/** 所属组织 [非空] [TbCustomGroup]      **/
		public M tbscGroup(Object tbscGroup){this.put("tbscGroup", tbscGroup);return this;};
	 	/** and tbsc_group is null */
 		public M tbscGroupNull(){if(this.get("tbscGroupNot")==null)this.put("tbscGroupNot", "");this.put("tbscGroup", null);return this;};
 		/** not .... */
 		public M tbscGroupNot(){this.put("tbscGroupNot", "not");return this;};
		public M tbscGroup$on(TbCustomGroup.M value){
			this.put("TbCustomGroup", value);
			this.put("tbscGroup$on", value);
			return this;
		};	
		/** 联系手机 [非空]       **/
		public M tbscMobile(Object tbscMobile){this.put("tbscMobile", tbscMobile);return this;};
	 	/** and tbsc_mobile is null */
 		public M tbscMobileNull(){if(this.get("tbscMobileNot")==null)this.put("tbscMobileNot", "");this.put("tbscMobile", null);return this;};
 		/** not .... */
 		public M tbscMobileNot(){this.put("tbscMobileNot", "not");return this;};
		/** 联系电话        **/
		public M tbscNumber(Object tbscNumber){this.put("tbscNumber", tbscNumber);return this;};
	 	/** and tbsc_number is null */
 		public M tbscNumberNull(){if(this.get("tbscNumberNot")==null)this.put("tbscNumberNot", "");this.put("tbscNumber", null);return this;};
 		/** not .... */
 		public M tbscNumberNot(){this.put("tbscNumberNot", "not");return this;};
		/** 联系邮箱        **/
		public M tbscEmail(Object tbscEmail){this.put("tbscEmail", tbscEmail);return this;};
	 	/** and tbsc_email is null */
 		public M tbscEmailNull(){if(this.get("tbscEmailNot")==null)this.put("tbscEmailNot", "");this.put("tbscEmail", null);return this;};
 		/** not .... */
 		public M tbscEmailNot(){this.put("tbscEmailNot", "not");return this;};
		/** 联系QQ        **/
		public M tbscQq(Object tbscQq){this.put("tbscQq", tbscQq);return this;};
	 	/** and tbsc_qq is null */
 		public M tbscQqNull(){if(this.get("tbscQqNot")==null)this.put("tbscQqNot", "");this.put("tbscQq", null);return this;};
 		/** not .... */
 		public M tbscQqNot(){this.put("tbscQqNot", "not");return this;};
		/** 其它联系方式        **/
		public M tbscOthrer(Object tbscOthrer){this.put("tbscOthrer", tbscOthrer);return this;};
	 	/** and tbsc_othrer is null */
 		public M tbscOthrerNull(){if(this.get("tbscOthrerNot")==null)this.put("tbscOthrerNot", "");this.put("tbscOthrer", null);return this;};
 		/** not .... */
 		public M tbscOthrerNot(){this.put("tbscOthrerNot", "not");return this;};
		/** 业务方向 [非空]    描述将会和此客户将来会有什么业务方面的合作   **/
		public M tbscProfile(Object tbscProfile){this.put("tbscProfile", tbscProfile);return this;};
	 	/** and tbsc_profile is null */
 		public M tbscProfileNull(){if(this.get("tbscProfileNot")==null)this.put("tbscProfileNot", "");this.put("tbscProfile", null);return this;};
 		/** not .... */
 		public M tbscProfileNot(){this.put("tbscProfileNot", "not");return this;};
		/** 会员级别 [非空]   0:普通客户 1:重要客户 2:集团大客户 3:政府大客户     **/
		public M tbscLevel(Object tbscLevel){this.put("tbscLevel", tbscLevel);return this;};
	 	/** and tbsc_level is null */
 		public M tbscLevelNull(){if(this.get("tbscLevelNot")==null)this.put("tbscLevelNot", "");this.put("tbscLevel", null);return this;};
 		/** not .... */
 		public M tbscLevelNot(){this.put("tbscLevelNot", "not");return this;};
		/** 意向阶段 [非空]   0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效     **/
		public M tbscFormal(Object tbscFormal){this.put("tbscFormal", tbscFormal);return this;};
	 	/** and tbsc_formal is null */
 		public M tbscFormalNull(){if(this.get("tbscFormalNot")==null)this.put("tbscFormalNot", "");this.put("tbscFormal", null);return this;};
 		/** not .... */
 		public M tbscFormalNot(){this.put("tbscFormalNot", "not");return this;};
		/** 服务阶段 [非空]       **/
		public M tbscServer(Object tbscServer){this.put("tbscServer", tbscServer);return this;};
	 	/** and tbsc_server is null */
 		public M tbscServerNull(){if(this.get("tbscServerNot")==null)this.put("tbscServerNot", "");this.put("tbscServer", null);return this;};
 		/** not .... */
 		public M tbscServerNot(){this.put("tbscServerNot", "not");return this;};
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbscOnwer(Object tbscOnwer){this.put("tbscOnwer", tbscOnwer);return this;};
	 	/** and tbsc_onwer is null */
 		public M tbscOnwerNull(){if(this.get("tbscOnwerNot")==null)this.put("tbscOnwerNot", "");this.put("tbscOnwer", null);return this;};
 		/** not .... */
 		public M tbscOnwerNot(){this.put("tbscOnwerNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbscAdder(Object tbscAdder){this.put("tbscAdder", tbscAdder);return this;};
	 	/** and tbsc_adder is null */
 		public M tbscAdderNull(){if(this.get("tbscAdderNot")==null)this.put("tbscAdderNot", "");this.put("tbscAdder", null);return this;};
 		/** not .... */
 		public M tbscAdderNot(){this.put("tbscAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbscEditor(Object tbscEditor){this.put("tbscEditor", tbscEditor);return this;};
	 	/** and tbsc_editor is null */
 		public M tbscEditorNull(){if(this.get("tbscEditorNot")==null)this.put("tbscEditorNot", "");this.put("tbscEditor", null);return this;};
 		/** not .... */
 		public M tbscEditorNot(){this.put("tbscEditorNot", "not");return this;};
		/** 备注        **/
		public M tbscRemark(Object tbscRemark){this.put("tbscRemark", tbscRemark);return this;};
	 	/** and tbsc_remark is null */
 		public M tbscRemarkNull(){if(this.get("tbscRemarkNot")==null)this.put("tbscRemarkNot", "");this.put("tbscRemark", null);return this;};
 		/** not .... */
 		public M tbscRemarkNot(){this.put("tbscRemarkNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M tbscMemo(Object tbscMemo){this.put("tbscMemo", tbscMemo);return this;};
	 	/** and tbsc_memo is null */
 		public M tbscMemoNull(){if(this.get("tbscMemoNot")==null)this.put("tbscMemoNot", "");this.put("tbscMemo", null);return this;};
 		/** not .... */
 		public M tbscMemoNot(){this.put("tbscMemoNot", "not");return this;};
		/** 程序数据        **/
		public M tbscData(Object tbscData){this.put("tbscData", tbscData);return this;};
	 	/** and tbsc_data is null */
 		public M tbscDataNull(){if(this.get("tbscDataNot")==null)this.put("tbscDataNot", "");this.put("tbscData", null);return this;};
 		/** not .... */
 		public M tbscDataNot(){this.put("tbscDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M tbscUpdateTime(Object tbscUpdateTime){this.put("tbscUpdateTime", tbscUpdateTime);return this;};
	 	/** and tbsc_update_time is null */
 		public M tbscUpdateTimeNull(){if(this.get("tbscUpdateTimeNot")==null)this.put("tbscUpdateTimeNot", "");this.put("tbscUpdateTime", null);return this;};
 		/** not .... */
 		public M tbscUpdateTimeNot(){this.put("tbscUpdateTimeNot", "not");return this;};
 		/** and tbsc_update_time >= ? */
 		public M tbscUpdateTimeStart(Object start){this.put("tbscUpdateTimeStart", start);return this;};			
 		/** and tbsc_update_time <= ? */
 		public M tbscUpdateTimeEnd(Object end){this.put("tbscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbscAddTime(Object tbscAddTime){this.put("tbscAddTime", tbscAddTime);return this;};
	 	/** and tbsc_add_time is null */
 		public M tbscAddTimeNull(){if(this.get("tbscAddTimeNot")==null)this.put("tbscAddTimeNot", "");this.put("tbscAddTime", null);return this;};
 		/** not .... */
 		public M tbscAddTimeNot(){this.put("tbscAddTimeNot", "not");return this;};
 		/** and tbsc_add_time >= ? */
 		public M tbscAddTimeStart(Object start){this.put("tbscAddTimeStart", start);return this;};			
 		/** and tbsc_add_time <= ? */
 		public M tbscAddTimeEnd(Object end){this.put("tbscAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M tbscStatus(Object tbscStatus){this.put("tbscStatus", tbscStatus);return this;};
	 	/** and tbsc_status is null */
 		public M tbscStatusNull(){if(this.get("tbscStatusNot")==null)this.put("tbscStatusNot", "");this.put("tbscStatus", null);return this;};
 		/** not .... */
 		public M tbscStatusNot(){this.put("tbscStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有客户资料 **/
		public @api List<TbSaleCustom> list(Integer size){
			return getTbSaleCustomList(this,size);
		}
		/** 获取客户资料分页 **/
		public @api Page<TbSaleCustom> page(int page,int size){
			return getTbSaleCustomPage(page, size , this);
		}
		/** 根据查询条件取客户资料 **/
		public @api TbSaleCustom get(){
			return getTbSaleCustom(this);
		}
		/** 获取客户资料数 **/
		public @api Long count(){
			return getTbSaleCustomCount(this);
		}
		/** 获取客户资料表达式 **/
		public @api <T> T eval(String strEval){
			return getTbSaleCustomEval(strEval,this);
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
			updateTbSaleCustom(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbscId="tbscId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbscHost="tbscHost";
		/** 客户名称 [非空]       **/
		public final static @type(String.class) @like String tbscName="tbscName";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static @type(Long.class)  String tbscBattle="tbscBattle";
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public final static @type(Long.class)  String tbscEvent="tbscEvent";
		/** 所属组织 [非空] [TbCustomGroup]      **/
		public final static @type(Long.class)  String tbscGroup="tbscGroup";
		/** 联系手机 [非空]       **/
		public final static @type(String.class) @like String tbscMobile="tbscMobile";
		/** 联系电话        **/
		public final static @type(String.class) @like String tbscNumber="tbscNumber";
		/** 联系邮箱        **/
		public final static @type(String.class) @like String tbscEmail="tbscEmail";
		/** 联系QQ        **/
		public final static @type(String.class) @like String tbscQq="tbscQq";
		/** 其它联系方式        **/
		public final static @type(String.class) @like String tbscOthrer="tbscOthrer";
		/** 业务方向 [非空]    描述将会和此客户将来会有什么业务方面的合作   **/
		public final static @type(String.class) @like String tbscProfile="tbscProfile";
		/** 会员级别 [非空]   0:普通客户 1:重要客户 2:集团大客户 3:政府大客户     **/
		public final static @type(Short.class)  String tbscLevel="tbscLevel";
		/** 意向阶段 [非空]   0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效     **/
		public final static @type(Short.class)  String tbscFormal="tbscFormal";
		/** 服务阶段 [非空]       **/
		public final static @type(Short.class)  String tbscServer="tbscServer";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(String.class)  String tbscOnwer="tbscOnwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbscAdder="tbscAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbscEditor="tbscEditor";
		/** 备注        **/
		public final static @type(String.class) @like String tbscRemark="tbscRemark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class) @like String tbscMemo="tbscMemo";
		/** 程序数据        **/
		public final static @type(String.class) @like String tbscData="tbscData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbscUpdateTime="tbscUpdateTime";
	 	/** and tbsc_update_time >= ? */
 		public final static @type(Date.class) String tbscUpdateTimeStart="tbscUpdateTimeStart";
 		/** and tbsc_update_time <= ? */
 		public final static @type(Date.class) String tbscUpdateTimeEnd="tbscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbscAddTime="tbscAddTime";
	 	/** and tbsc_add_time >= ? */
 		public final static @type(Date.class) String tbscAddTimeStart="tbscAddTimeStart";
 		/** and tbsc_add_time <= ? */
 		public final static @type(Date.class) String tbscAddTimeEnd="tbscAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String tbscStatus="tbscStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbscId="tbsc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbscHost="tbsc_host";
		/** 客户名称 [非空]       **/
		public final static String tbscName="tbsc_name";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static String tbscBattle="tbsc_battle";
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public final static String tbscEvent="tbsc_event";
		/** 所属组织 [非空] [TbCustomGroup]      **/
		public final static String tbscGroup="tbsc_group";
		/** 联系手机 [非空]       **/
		public final static String tbscMobile="tbsc_mobile";
		/** 联系电话        **/
		public final static String tbscNumber="tbsc_number";
		/** 联系邮箱        **/
		public final static String tbscEmail="tbsc_email";
		/** 联系QQ        **/
		public final static String tbscQq="tbsc_qq";
		/** 其它联系方式        **/
		public final static String tbscOthrer="tbsc_othrer";
		/** 业务方向 [非空]    描述将会和此客户将来会有什么业务方面的合作   **/
		public final static String tbscProfile="tbsc_profile";
		/** 会员级别 [非空]   0:普通客户 1:重要客户 2:集团大客户 3:政府大客户     **/
		public final static String tbscLevel="tbsc_level";
		/** 意向阶段 [非空]   0:暂无意向 1:意向一般 2:意向强烈 3:售前跟踪 4:合同执行 5:售后服务 6:合同期满 7:失败 8:搁置 9:无效     **/
		public final static String tbscFormal="tbsc_formal";
		/** 服务阶段 [非空]       **/
		public final static String tbscServer="tbsc_server";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbscOnwer="tbsc_onwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbscAdder="tbsc_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbscEditor="tbsc_editor";
		/** 备注        **/
		public final static String tbscRemark="tbsc_remark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String tbscMemo="tbsc_memo";
		/** 程序数据        **/
		public final static String tbscData="tbsc_data";
		/** 修改时间 [非空]       **/
		public final static String tbscUpdateTime="tbsc_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbscAddTime="tbsc_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String tbscStatus="tbsc_status";
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
				$.Set(name,TbSaleCustom.getTbSaleCustom(params));
			else
				$.Set(name,TbSaleCustom.getTbSaleCustomList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取客户资料数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbSaleCustom) $.GetRequest("TbSaleCustom$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbSaleCustom.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbSaleCustom.getTbSaleCustom(params);
			else
				value = TbSaleCustom.getTbSaleCustomList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbSaleCustom.Get($.add(TbSaleCustom.F.tbscId,param));
		else if(!$.empty(param.toString()))
			value = TbSaleCustom.get(Long.valueOf(param.toString()));
		$.SetRequest("TbSaleCustom$"+param.hashCode(), value);
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
	public void mergeSet(TbSaleCustom old){
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