package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.lang.namespace;
import com.lazy3q.lang.note;
import com.lazy3q.lang.primary;
import com.lazy3q.lang.relate;
import com.lazy3q.lang.table;
import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.tags.BodyTag;
import com.lazy3q.web.util.Page;

@namespace("oa/cooperate/problem")
public @caption("问题故障报修") @table("tb_problem") class TbProblem implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbp_id")   @primary   @note("  ") Long tbpId;
	private @caption("城市") @column("tbp_host")    @relate("$tbpHost") @RelateClass(SrvHost.class)  @note("  ") Long tbpHost;
	private SrvHost $tbpHost;//关联对象[城市]
	private @caption("问题描述") @column("tbp_profile")      @note("  ") String tbpProfile;
	private @caption("问题类型") @column("tbp_type")      @note(" 0:服务 1:产品 2:系统 3:设备 4:车辆 5:网点 6:电桩  ") Short tbpType;
	private @caption("反馈会员") @column("tbp_member")    @relate("$tbpMember") @RelateClass(CsMember.class)  @note("  ") Long tbpMember;
	private CsMember $tbpMember;//关联对象[反馈会员]
	private @caption("所属订单") @column("tbp_order")    @relate("$tbpOrder") @RelateClass(CsOrder.class)  @note("  ") Long tbpOrder;
	private CsOrder $tbpOrder;//关联对象[所属订单]
	private @caption("关联对象") @column("tbp_relate")      @note("  ") String tbpRelate;
	private @caption("添加人员") @column("tbp_adder")    @relate("$tbpAdder") @RelateClass(SrvUser.class)  @note("  ") Long tbpAdder;
	private SrvUser $tbpAdder;//关联对象[添加人员]
	private @caption("修改人员") @column("tbp_editor")    @relate("$tbpEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbpEditor;
	private SrvUser $tbpEditor;//关联对象[修改人员]
	private @caption("任务指派") @column("tbp_dealer")    @relate("$tbpDealer") @RelateClass(SrvUser.class)  @note("  ") Long tbpDealer;
	private SrvUser $tbpDealer;//关联对象[任务指派]
	private @caption("结案描述") @column("tbp_result")      @note("  ") String tbpResult;
	private @caption("备注") @column("tbp_remark")      @note("  ") String tbpRemark;
	private @caption("备忘信息") @column("tbp_memo")      @note(" textarea:content text:sign  ") String tbpMemo;
	private @caption("程序数据") @column("tbp_data")      @note("  ") String tbpData;
	private @caption("修改时间") @column("tbp_update_time")      @note("  ") Date tbpUpdateTime;
	private @caption("添加时间") @column("tbp_add_time")      @note("  ") Date tbpAddTime;
	private @caption("状态") @column("tbp_status")      @note(" 0:待处理 1:正在处理 2:已处理  ") Short tbpStatus;
	private @caption("所在网点") @column("tbp_outlets")    @relate("$tbpOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long tbpOutlets;
	private CsOutlets $tbpOutlets;//关联对象[所在网点]
	private @caption("位置信息") @column("tbp_special_data")      @note("  ") String tbpSpecialData;
	
	//默认构造函数
	public TbProblem(){
	
	}
	
	//主键构造函数
	public TbProblem(Long id){
		this.tbpId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public TbProblem(Long tbpHost,String tbpProfile,Short tbpType,Long tbpMember,Long tbpOrder,String tbpRelate,Long tbpAdder,Long tbpEditor,Long tbpDealer,String tbpResult,String tbpRemark,String tbpMemo,String tbpData,Date tbpUpdateTime,Date tbpAddTime,Short tbpStatus,Long tbpOutlets,String tbpSpecialData){
		this.tbpHost=tbpHost;
		this.tbpProfile=tbpProfile;
		this.tbpType=tbpType;
		this.tbpMember=tbpMember;
		this.tbpOrder=tbpOrder;
		this.tbpRelate=tbpRelate;
		this.tbpAdder=tbpAdder;
		this.tbpEditor=tbpEditor;
		this.tbpDealer=tbpDealer;
		this.tbpResult=tbpResult;
		this.tbpRemark=tbpRemark;
		this.tbpMemo=tbpMemo;
		this.tbpData=tbpData;
		this.tbpUpdateTime=tbpUpdateTime;
		this.tbpAddTime=tbpAddTime;
		this.tbpStatus=tbpStatus;
		this.tbpOutlets=tbpOutlets;
		this.tbpSpecialData=tbpSpecialData;
	}
	
	//设置非空字段
	public TbProblem setNotNull(Long tbpHost,String tbpProfile,Short tbpType,Long tbpAdder,Long tbpEditor,Long tbpDealer,Date tbpUpdateTime,Date tbpAddTime,Short tbpStatus){
		this.tbpHost=tbpHost;
		this.tbpProfile=tbpProfile;
		this.tbpType=tbpType;
		this.tbpAdder=tbpAdder;
		this.tbpEditor=tbpEditor;
		this.tbpDealer=tbpDealer;
		this.tbpUpdateTime=tbpUpdateTime;
		this.tbpAddTime=tbpAddTime;
		this.tbpStatus=tbpStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public TbProblem tbpId(Long tbpId){
		this.tbpId = tbpId;
		this.setSeted(F.tbpId);
		return this;
	}
	/** 城市 [非空]       **/
	public TbProblem tbpHost(Long tbpHost){
		this.tbpHost = tbpHost;
		this.setSeted(F.tbpHost);
		return this;
	}
	/** 问题描述 [非空]       **/
	public TbProblem tbpProfile(String tbpProfile){
		this.tbpProfile = tbpProfile;
		this.setSeted(F.tbpProfile);
		return this;
	}
	/** 问题类型 [非空]       **/
	public TbProblem tbpType(Short tbpType){
		this.tbpType = tbpType;
		this.setSeted(F.tbpType);
		return this;
	}
	/** 反馈会员 [非空]       **/
	public TbProblem tbpMember(Long tbpMember){
		this.tbpMember = tbpMember;
		this.setSeted(F.tbpMember);
		return this;
	}
	/** 所属订单 [非空]       **/
	public TbProblem tbpOrder(Long tbpOrder){
		this.tbpOrder = tbpOrder;
		this.setSeted(F.tbpOrder);
		return this;
	}
	/** 关联对象 [非空]       **/
	public TbProblem tbpRelate(String tbpRelate){
		this.tbpRelate = tbpRelate;
		this.setSeted(F.tbpRelate);
		return this;
	}
	/** 添加人员 [非空]       **/
	public TbProblem tbpAdder(Long tbpAdder){
		this.tbpAdder = tbpAdder;
		this.setSeted(F.tbpAdder);
		return this;
	}
	/** 修改人员 [非空]       **/
	public TbProblem tbpEditor(Long tbpEditor){
		this.tbpEditor = tbpEditor;
		this.setSeted(F.tbpEditor);
		return this;
	}
	/** 任务指派 [非空]       **/
	public TbProblem tbpDealer(Long tbpDealer){
		this.tbpDealer = tbpDealer;
		this.setSeted(F.tbpDealer);
		return this;
	}
	/** 结案描述 [非空]       **/
	public TbProblem tbpResult(String tbpResult){
		this.tbpResult = tbpResult;
		this.setSeted(F.tbpResult);
		return this;
	}
	/** 备注 [非空]       **/
	public TbProblem tbpRemark(String tbpRemark){
		this.tbpRemark = tbpRemark;
		this.setSeted(F.tbpRemark);
		return this;
	}
	/** 备忘信息 [非空]       **/
	public TbProblem tbpMemo(String tbpMemo){
		this.tbpMemo = tbpMemo;
		this.setSeted(F.tbpMemo);
		return this;
	}
	/** 程序数据 [非空]       **/
	public TbProblem tbpData(String tbpData){
		this.tbpData = tbpData;
		this.setSeted(F.tbpData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbProblem tbpUpdateTime(Date tbpUpdateTime){
		this.tbpUpdateTime = tbpUpdateTime;
		this.setSeted(F.tbpUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbProblem tbpAddTime(Date tbpAddTime){
		this.tbpAddTime = tbpAddTime;
		this.setSeted(F.tbpAddTime);
		return this;
	}
	/** 状态 [非空]       **/
	public TbProblem tbpStatus(Short tbpStatus){
		this.tbpStatus = tbpStatus;
		this.setSeted(F.tbpStatus);
		return this;
	}
	/** 所在网点 [可空]       **/
	public TbProblem tbpOutlets(Long tbpOutlets){
		this.tbpOutlets = tbpOutlets;
		this.setSeted(F.tbpOutlets);
		return this;
	}
	/** 位置信息 [可空]       **/
	public TbProblem tbpSpecialData(String tbpSpecialData){
		this.tbpSpecialData = tbpSpecialData;
		this.setSeted(F.tbpSpecialData);
		return this;
	}
	
	//克隆对象
	public TbProblem clone(){
		TbProblem clone = new TbProblem();
		clone.tbpId=this.tbpId;
		clone.tbpHost=this.tbpHost;
		clone.tbpProfile=this.tbpProfile;
		clone.tbpType=this.tbpType;
		clone.tbpMember=this.tbpMember;
		clone.tbpOrder=this.tbpOrder;
		clone.tbpRelate=this.tbpRelate;
		clone.tbpAdder=this.tbpAdder;
		clone.tbpEditor=this.tbpEditor;
		clone.tbpDealer=this.tbpDealer;
		clone.tbpResult=this.tbpResult;
		clone.tbpRemark=this.tbpRemark;
		clone.tbpMemo=this.tbpMemo;
		clone.tbpData=this.tbpData;
		clone.tbpUpdateTime=this.tbpUpdateTime;
		clone.tbpAddTime=this.tbpAddTime;
		clone.tbpStatus=this.tbpStatus;
		clone.tbpOutlets=this.tbpOutlets;
		clone.tbpSpecialData=this.tbpSpecialData;
		return clone;
	}
	
	
	/**
	 * 根据ID取问题故障报修
	 * @param id
	 * @return
	 */
	public static @api TbProblem get(Long id){		
		return getTbProblemById(id);
	}
	/**
	 * 获取所有问题故障报修
	 * @return
	 */
	public static @api List<TbProblem> list(Map params,Integer size){
		return getTbProblemList(params,size);
	}
	/**
	 * 获取问题故障报修分页
	 * @return
	 */
	public static @api Page<TbProblem> page(int page,int size,Map params){
		return getTbProblemPage(page, size , params);
	}
	/**
	 * 根据查询条件取问题故障报修
	 * @param params
	 * @return
	 */
	public static @api TbProblem Get(Map params){
		return getTbProblem(params);
	}
	/**
	 * 获取问题故障报修数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbProblemCount(params);
	}
	/**
	 * 获取问题故障报修数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbProblemEval(eval,params);
	}
	
	/**
	 * 根据ID取问题故障报修
	 * @param id
	 * @return
	 */
	public static @api TbProblem getTbProblemById(Long id){		
		TbProblem tbProblem = (TbProblem) $.GetRequest("TbProblem$"+id);
		if(tbProblem!=null)
			return tbProblem;
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");		
		return tbProblemService.getTbProblemById(id);
	}
	
	
	/**
	 * 根据ID取问题故障报修的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbProblem.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbProblem tbProblem = get(id);
		if(tbProblem!=null){
			String strValue = tbProblem.getTbpProfile$();
			if(!"TbpProfile".equals("TbpProfile"))
				strValue+="("+tbProblem.getTbpProfile$()+")";
			MemCache.setValue(TbProblem.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbpProfile$();
		if(!"TbpProfile".equals("TbpProfile"))
			keyValue+="("+this.getTbpProfile$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有问题故障报修
	 * @return
	 */
	public static @api List<TbProblem> getTbProblemList(Map params,Integer size){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		return tbProblemService.getTbProblemList(params, size);
	}
	
	/**
	 * 获取问题故障报修分页
	 * @return
	 */
	public static @api Page<TbProblem> getTbProblemPage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		return tbProblemService.getTbProblemPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取问题故障报修
	 * @param params
	 * @return
	 */
	public static @api TbProblem getTbProblem(Map params){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		return tbProblemService.getTbProblem(params);
	}
	
	/**
	 * 获取问题故障报修数
	 * @return
	 */
	public static @api Long getTbProblemCount(Map params){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		return tbProblemService.getTbProblemCount(params);
	}
		
		
	/**
	 * 获取问题故障报修自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbProblemEval(String eval,Map params){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		return tbProblemService.getTbProblemEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbProblem(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		tbProblemService.updateTbProblemByConfirm(set, where);
	}
	
	
	/**
	 * 保存问题故障报修对象
	 * @param params
	 * @return
	 */
	public TbProblem save(){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		if(this.getTbpId()!=null)
			tbProblemService.updateTbProblem(this);
		else
			return tbProblemService.saveTbProblem(this);
		return this;
	}
	
	
	/**
	 * 更新问题故障报修对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		tbProblemService.updateTbProblem$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		if($.equals($.config("logic_delete"),"true"))
			tbProblemService.removeTbProblemById(this.getTbpId());
		else
			tbProblemService.deleteTbProblemById(this.getTbpId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
		return tbProblemService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbpId(){
		return this.tbpId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbpId$(){
		String strValue="";
		 strValue=$.str(this.getTbpId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setTbpId(Long tbpId){
		this.tbpId = tbpId;
		this.setSeted(F.tbpId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getTbpHost(){
		return this.tbpHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbpHost$(){
		String strValue="";
		if(this.getTbpHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbpHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setTbpHost(Long tbpHost){
		this.tbpHost = tbpHost;
		this.setSeted(F.tbpHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$tbpHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbpHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbpHost()!=null){
			srvHost = SrvHost.get(this.getTbpHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbpHost(), srvHost);
	 	return srvHost;
	}
	/*******************************问题描述**********************************/	
	/**
	* 问题描述 [非空]      
	**/
	public String getTbpProfile(){
		return this.tbpProfile;
	}
	/**
	* 获取问题描述格式化(toString)
	**/
	public String getTbpProfile$(){
		String strValue="";
		 strValue=$.str(this.getTbpProfile());
	 	 return strValue;
	}
	/**
	* 问题描述 [非空]     
	**/
	public void setTbpProfile(String tbpProfile){
		this.tbpProfile = tbpProfile;
		this.setSeted(F.tbpProfile);
	}
	/*******************************问题类型**********************************/	
	/**
	* 问题类型 [非空]      
	**/
	public Short getTbpType(){
		return this.tbpType;
	}
	/**
	* 获取问题类型格式化(toString)
	**/
	public String getTbpType$(){
		String strValue="";
		 if($.equals($.str(this.getTbpType()),"3"))
			 strValue=$.str("设备");
		 if($.equals($.str(this.getTbpType()),"2"))
			 strValue=$.str("系统");
		 if($.equals($.str(this.getTbpType()),"1"))
			 strValue=$.str("产品");
		 if($.equals($.str(this.getTbpType()),"0"))
			 strValue=$.str("服务");
		 if($.equals($.str(this.getTbpType()),"6"))
			 strValue=$.str("电桩");
		 if($.equals($.str(this.getTbpType()),"5"))
			 strValue=$.str("网点");
		 if($.equals($.str(this.getTbpType()),"4"))
			 strValue=$.str("车辆");
	 	 return strValue;
	}
	/**
	* 问题类型 [非空]     
	**/
	public void setTbpType(Short tbpType){
		this.tbpType = tbpType;
		this.setSeted(F.tbpType);
	}
	/*******************************反馈会员**********************************/	
	/**
	* 反馈会员 [非空]      
	**/
	public Long getTbpMember(){
		return this.tbpMember;
	}
	/**
	* 获取反馈会员格式化(toString)
	**/
	public String getTbpMember$(){
		String strValue="";
		if(this.getTbpMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getTbpMember()));
		}			
	 	 return strValue;
	}
	/**
	* 反馈会员 [非空]     
	**/
	public void setTbpMember(Long tbpMember){
		this.tbpMember = tbpMember;
		this.setSeted(F.tbpMember);
	}
	/**
	* 获取关联对象[反馈会员]
	**/	 			
 	public CsMember get$tbpMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getTbpMember());
 		if(csMember!=null)
			return csMember;
		if(this.getTbpMember()!=null){
			csMember = CsMember.get(this.getTbpMember());
 		}
 		$.SetRequest("CsMember$"+this.getTbpMember(), csMember);
	 	return csMember;
	}
	/*******************************所属订单**********************************/	
	/**
	* 所属订单 [非空]      
	**/
	public Long getTbpOrder(){
		return this.tbpOrder;
	}
	/**
	* 获取所属订单格式化(toString)
	**/
	public String getTbpOrder$(){
		String strValue="";
		if(this.getTbpOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getTbpOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属订单 [非空]     
	**/
	public void setTbpOrder(Long tbpOrder){
		this.tbpOrder = tbpOrder;
		this.setSeted(F.tbpOrder);
	}
	/**
	* 获取关联对象[所属订单]
	**/	 			
 	public CsOrder get$tbpOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getTbpOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getTbpOrder()!=null){
			csOrder = CsOrder.get(this.getTbpOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getTbpOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************关联对象**********************************/	
	/**
	* 关联对象 [非空]      
	**/
	public String getTbpRelate(){
		return this.tbpRelate;
	}
	/**
	* 获取关联对象格式化(toString)
	**/
	public String getTbpRelate$(){
		String strValue="";
		 strValue=$.str(this.getTbpRelate());
	 	 return strValue;
	}
	/**
	* 关联对象 [非空]     
	**/
	public void setTbpRelate(String tbpRelate){
		this.tbpRelate = tbpRelate;
		this.setSeted(F.tbpRelate);
	}
	/*******************************添加人员**********************************/	
	/**
	* 添加人员 [非空]      
	**/
	public Long getTbpAdder(){
		return this.tbpAdder;
	}
	/**
	* 获取添加人员格式化(toString)
	**/
	public String getTbpAdder$(){
		String strValue="";
		if(this.getTbpAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbpAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人员 [非空]     
	**/
	public void setTbpAdder(Long tbpAdder){
		this.tbpAdder = tbpAdder;
		this.setSeted(F.tbpAdder);
	}
	/**
	* 获取关联对象[添加人员]
	**/	 			
 	public SrvUser get$tbpAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbpAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbpAdder()!=null){
			srvUser = SrvUser.get(this.getTbpAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getTbpAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人员**********************************/	
	/**
	* 修改人员 [非空]      
	**/
	public Long getTbpEditor(){
		return this.tbpEditor;
	}
	/**
	* 获取修改人员格式化(toString)
	**/
	public String getTbpEditor$(){
		String strValue="";
		if(this.getTbpEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbpEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人员 [非空]     
	**/
	public void setTbpEditor(Long tbpEditor){
		this.tbpEditor = tbpEditor;
		this.setSeted(F.tbpEditor);
	}
	/**
	* 获取关联对象[修改人员]
	**/	 			
 	public SrvUser get$tbpEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbpEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbpEditor()!=null){
			srvUser = SrvUser.get(this.getTbpEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbpEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************任务指派**********************************/	
	/**
	* 任务指派 [非空]      
	**/
	public Long getTbpDealer(){
		return this.tbpDealer;
	}
	/**
	* 获取任务指派格式化(toString)
	**/
	public String getTbpDealer$(){
		String strValue="";
		if(this.getTbpDealer()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbpDealer()));
		}			
	 	 return strValue;
	}
	/**
	* 任务指派 [非空]     
	**/
	public void setTbpDealer(Long tbpDealer){
		this.tbpDealer = tbpDealer;
		this.setSeted(F.tbpDealer);
	}
	/**
	* 获取关联对象[任务指派]
	**/	 			
 	public SrvUser get$tbpDealer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbpDealer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbpDealer()!=null){
			srvUser = SrvUser.get(this.getTbpDealer());
 		}
 		$.SetRequest("SrvUser$"+this.getTbpDealer(), srvUser);
	 	return srvUser;
	}
	/*******************************结案描述**********************************/	
	/**
	* 结案描述 [非空]      
	**/
	public String getTbpResult(){
		return this.tbpResult;
	}
	/**
	* 获取结案描述格式化(toString)
	**/
	public String getTbpResult$(){
		String strValue="";
		 strValue=$.str(this.getTbpResult());
	 	 return strValue;
	}
	/**
	* 结案描述 [非空]     
	**/
	public void setTbpResult(String tbpResult){
		this.tbpResult = tbpResult;
		this.setSeted(F.tbpResult);
	}
	/*******************************备注**********************************/	
	/**
	* 备注 [非空]      
	**/
	public String getTbpRemark(){
		return this.tbpRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getTbpRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbpRemark());
	 	 return strValue;
	}
	/**
	* 备注 [非空]     
	**/
	public void setTbpRemark(String tbpRemark){
		this.tbpRemark = tbpRemark;
		this.setSeted(F.tbpRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息 [非空]      
	**/
	public String getTbpMemo(){
		return this.tbpMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getTbpMemo$(){
		String strValue="";
		 strValue=$.str(this.getTbpMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息 [非空]     
	**/
	public void setTbpMemo(String tbpMemo){
		this.tbpMemo = tbpMemo;
		this.setSeted(F.tbpMemo);
	}
	/**
	* 备忘信息对应内部类
	**/
	class TbpMemo{
		String content;//备忘内容
		String sign;//签名
		public void setContent(String content){
			this.content=content;
		}
		public String getContent(){
			return this.content;
		}
		public void setSign(String sign){
			this.sign=sign;
		}
		public String getSign(){
			return this.sign;
		}
	}
	/**
	* 获取备忘信息格式化列表
	**/ 			
 	public List<TbpMemo> get$tbpMemo(){
 		List<TbpMemo> array = new ArrayList();
 		List<Map> list = $.eval(this.tbpMemo);
 		for(Map map:list){
 			TbpMemo tbpMemo = new TbpMemo();
 			tbpMemo.setContent($.parseString(map.get("content")));
 			tbpMemo.setSign($.parseString(map.get("sign")));
 			array.add(tbpMemo);
 		}
 		return array;
 	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据 [非空]      
	**/
	public String getTbpData(){
		return this.tbpData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getTbpData$(){
		String strValue="";
		 strValue=$.str(this.getTbpData());
	 	 return strValue;
	}
	/**
	* 程序数据 [非空]     
	**/
	public void setTbpData(String tbpData){
		this.tbpData = tbpData;
		this.setSeted(F.tbpData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbpUpdateTime(){
		return this.tbpUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbpUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbpUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setTbpUpdateTime(Date tbpUpdateTime){
		this.tbpUpdateTime = tbpUpdateTime;
		this.setSeted(F.tbpUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbpAddTime(){
		return this.tbpAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbpAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbpAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setTbpAddTime(Date tbpAddTime){
		this.tbpAddTime = tbpAddTime;
		this.setSeted(F.tbpAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getTbpStatus(){
		return this.tbpStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbpStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbpStatus()),"2"))
			 strValue=$.str("已处理");
		 if($.equals($.str(this.getTbpStatus()),"1"))
			 strValue=$.str("正在处理");
		 if($.equals($.str(this.getTbpStatus()),"0"))
			 strValue=$.str("待处理");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setTbpStatus(Short tbpStatus){
		this.tbpStatus = tbpStatus;
		this.setSeted(F.tbpStatus);
	}
	/*******************************所在网点**********************************/	
	/**
	* 所在网点 [可空]      
	**/
	public Long getTbpOutlets(){
		return this.tbpOutlets;
	}
	/**
	* 获取所在网点格式化(toString)
	**/
	public String getTbpOutlets$(){
		String strValue="";
		if(this.getTbpOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getTbpOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 所在网点 [可空]     
	**/
	public void setTbpOutlets(Long tbpOutlets){
		this.tbpOutlets = tbpOutlets;
		this.setSeted(F.tbpOutlets);
	}
	/**
	* 获取关联对象[所在网点]
	**/	 			
 	public CsOutlets get$tbpOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getTbpOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getTbpOutlets()!=null){
			csOutlets = CsOutlets.get(this.getTbpOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getTbpOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************位置信息**********************************/	
	/**
	* 位置信息 [可空]      
	**/
	public String getTbpSpecialData(){
		return this.tbpSpecialData;
	}
	/**
	* 获取位置信息格式化(toString)
	**/
	public String getTbpSpecialData$(){
		String strValue="";
		 strValue=$.str(this.getTbpSpecialData());
	 	 return strValue;
	}
	/**
	* 位置信息 [可空]     
	**/
	public void setTbpSpecialData(String tbpSpecialData){
		this.tbpSpecialData = tbpSpecialData;
		this.setSeted(F.tbpSpecialData);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbProblem.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbProblem.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbProblem.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbProblem.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbProblem.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbProblem.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbProblem.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbProblem.this);
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
		public M tbpId(Object tbpId){this.put("tbpId", tbpId);return this;};
		/** and tbp_id is null */
		public M tbpIdNull(){if(this.get("tbpIdNot")==null)this.put("tbpIdNot", "");this.put("tbpId", null);return this;};
		/** not .... */
		public M tbpIdNot(){this.put("tbpIdNot", "not");return this;};
		/** and tbp_id >= ? */
		public M tbpIdMin(Object min){this.put("tbpIdMin", min);return this;};
		/** and tbp_id <= ? */
		public M tbpIdMax(Object max){this.put("tbpIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M tbpHost(Object tbpHost){this.put("tbpHost", tbpHost);return this;};
		/** and tbp_host is null */
		public M tbpHostNull(){if(this.get("tbpHostNot")==null)this.put("tbpHostNot", "");this.put("tbpHost", null);return this;};
		/** not .... */
		public M tbpHostNot(){this.put("tbpHostNot", "not");return this;};
		/** and tbp_host >= ? */
		public M tbpHostMin(Object min){this.put("tbpHostMin", min);return this;};
		/** and tbp_host <= ? */
		public M tbpHostMax(Object max){this.put("tbpHostMax", max);return this;};
		/** 问题描述 [非空]       **/
		public M tbpProfile(Object tbpProfile){this.put("tbpProfile", tbpProfile);return this;};
		/** and tbp_profile is null */
		public M tbpProfileNull(){if(this.get("tbpProfileNot")==null)this.put("tbpProfileNot", "");this.put("tbpProfile", null);return this;};
		/** not .... */
		public M tbpProfileNot(){this.put("tbpProfileNot", "not");return this;};
		/** 问题类型 [非空]       **/
		public M tbpType(Object tbpType){this.put("tbpType", tbpType);return this;};
		/** and tbp_type is null */
		public M tbpTypeNull(){if(this.get("tbpTypeNot")==null)this.put("tbpTypeNot", "");this.put("tbpType", null);return this;};
		/** not .... */
		public M tbpTypeNot(){this.put("tbpTypeNot", "not");return this;};
		/** and tbp_type >= ? */
		public M tbpTypeMin(Object min){this.put("tbpTypeMin", min);return this;};
		/** and tbp_type <= ? */
		public M tbpTypeMax(Object max){this.put("tbpTypeMax", max);return this;};
		/** 反馈会员 [非空]       **/
		public M tbpMember(Object tbpMember){this.put("tbpMember", tbpMember);return this;};
		/** and tbp_member is null */
		public M tbpMemberNull(){if(this.get("tbpMemberNot")==null)this.put("tbpMemberNot", "");this.put("tbpMember", null);return this;};
		/** not .... */
		public M tbpMemberNot(){this.put("tbpMemberNot", "not");return this;};
		public M tbpMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("tbpMember$on", value);
			return this;
		};	
		/** and tbp_member >= ? */
		public M tbpMemberMin(Object min){this.put("tbpMemberMin", min);return this;};
		/** and tbp_member <= ? */
		public M tbpMemberMax(Object max){this.put("tbpMemberMax", max);return this;};
		/** 所属订单 [非空]       **/
		public M tbpOrder(Object tbpOrder){this.put("tbpOrder", tbpOrder);return this;};
		/** and tbp_order is null */
		public M tbpOrderNull(){if(this.get("tbpOrderNot")==null)this.put("tbpOrderNot", "");this.put("tbpOrder", null);return this;};
		/** not .... */
		public M tbpOrderNot(){this.put("tbpOrderNot", "not");return this;};
		public M tbpOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("tbpOrder$on", value);
			return this;
		};	
		/** and tbp_order >= ? */
		public M tbpOrderMin(Object min){this.put("tbpOrderMin", min);return this;};
		/** and tbp_order <= ? */
		public M tbpOrderMax(Object max){this.put("tbpOrderMax", max);return this;};
		/** 关联对象 [非空]       **/
		public M tbpRelate(Object tbpRelate){this.put("tbpRelate", tbpRelate);return this;};
		/** and tbp_relate is null */
		public M tbpRelateNull(){if(this.get("tbpRelateNot")==null)this.put("tbpRelateNot", "");this.put("tbpRelate", null);return this;};
		/** not .... */
		public M tbpRelateNot(){this.put("tbpRelateNot", "not");return this;};
		/** 添加人员 [非空]       **/
		public M tbpAdder(Object tbpAdder){this.put("tbpAdder", tbpAdder);return this;};
		/** and tbp_adder is null */
		public M tbpAdderNull(){if(this.get("tbpAdderNot")==null)this.put("tbpAdderNot", "");this.put("tbpAdder", null);return this;};
		/** not .... */
		public M tbpAdderNot(){this.put("tbpAdderNot", "not");return this;};
		public M tbpAdder$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("tbpAdder$on", value);
			return this;
		};	
		/** and tbp_adder >= ? */
		public M tbpAdderMin(Object min){this.put("tbpAdderMin", min);return this;};
		/** and tbp_adder <= ? */
		public M tbpAdderMax(Object max){this.put("tbpAdderMax", max);return this;};
		/** 修改人员 [非空]       **/
		public M tbpEditor(Object tbpEditor){this.put("tbpEditor", tbpEditor);return this;};
		/** and tbp_editor is null */
		public M tbpEditorNull(){if(this.get("tbpEditorNot")==null)this.put("tbpEditorNot", "");this.put("tbpEditor", null);return this;};
		/** not .... */
		public M tbpEditorNot(){this.put("tbpEditorNot", "not");return this;};
		public M tbpEditor$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("tbpEditor$on", value);
			return this;
		};	
		/** and tbp_editor >= ? */
		public M tbpEditorMin(Object min){this.put("tbpEditorMin", min);return this;};
		/** and tbp_editor <= ? */
		public M tbpEditorMax(Object max){this.put("tbpEditorMax", max);return this;};
		/** 任务指派 [非空]       **/
		public M tbpDealer(Object tbpDealer){this.put("tbpDealer", tbpDealer);return this;};
		/** and tbp_dealer is null */
		public M tbpDealerNull(){if(this.get("tbpDealerNot")==null)this.put("tbpDealerNot", "");this.put("tbpDealer", null);return this;};
		/** not .... */
		public M tbpDealerNot(){this.put("tbpDealerNot", "not");return this;};
		public M tbpDealer$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("tbpDealer$on", value);
			return this;
		};	
		/** and tbp_dealer >= ? */
		public M tbpDealerMin(Object min){this.put("tbpDealerMin", min);return this;};
		/** and tbp_dealer <= ? */
		public M tbpDealerMax(Object max){this.put("tbpDealerMax", max);return this;};
		/** 结案描述 [非空]       **/
		public M tbpResult(Object tbpResult){this.put("tbpResult", tbpResult);return this;};
		/** and tbp_result is null */
		public M tbpResultNull(){if(this.get("tbpResultNot")==null)this.put("tbpResultNot", "");this.put("tbpResult", null);return this;};
		/** not .... */
		public M tbpResultNot(){this.put("tbpResultNot", "not");return this;};
		/** 备注 [非空]       **/
		public M tbpRemark(Object tbpRemark){this.put("tbpRemark", tbpRemark);return this;};
		/** and tbp_remark is null */
		public M tbpRemarkNull(){if(this.get("tbpRemarkNot")==null)this.put("tbpRemarkNot", "");this.put("tbpRemark", null);return this;};
		/** not .... */
		public M tbpRemarkNot(){this.put("tbpRemarkNot", "not");return this;};
		/** 备忘信息 [非空]       **/
		public M tbpMemo(Object tbpMemo){this.put("tbpMemo", tbpMemo);return this;};
		/** and tbp_memo is null */
		public M tbpMemoNull(){if(this.get("tbpMemoNot")==null)this.put("tbpMemoNot", "");this.put("tbpMemo", null);return this;};
		/** not .... */
		public M tbpMemoNot(){this.put("tbpMemoNot", "not");return this;};
		/** 程序数据 [非空]       **/
		public M tbpData(Object tbpData){this.put("tbpData", tbpData);return this;};
		/** and tbp_data is null */
		public M tbpDataNull(){if(this.get("tbpDataNot")==null)this.put("tbpDataNot", "");this.put("tbpData", null);return this;};
		/** not .... */
		public M tbpDataNot(){this.put("tbpDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M tbpUpdateTime(Object tbpUpdateTime){this.put("tbpUpdateTime", tbpUpdateTime);return this;};
		/** and tbp_update_time is null */
		public M tbpUpdateTimeNull(){if(this.get("tbpUpdateTimeNot")==null)this.put("tbpUpdateTimeNot", "");this.put("tbpUpdateTime", null);return this;};
		/** not .... */
		public M tbpUpdateTimeNot(){this.put("tbpUpdateTimeNot", "not");return this;};
		/** and tbp_update_time >= ? */
 		public M tbpUpdateTimeStart(Object start){this.put("tbpUpdateTimeStart", start);return this;};			
 		/** and tbp_update_time <= ? */
 		public M tbpUpdateTimeEnd(Object end){this.put("tbpUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbpAddTime(Object tbpAddTime){this.put("tbpAddTime", tbpAddTime);return this;};
		/** and tbp_add_time is null */
		public M tbpAddTimeNull(){if(this.get("tbpAddTimeNot")==null)this.put("tbpAddTimeNot", "");this.put("tbpAddTime", null);return this;};
		/** not .... */
		public M tbpAddTimeNot(){this.put("tbpAddTimeNot", "not");return this;};
		/** and tbp_add_time >= ? */
 		public M tbpAddTimeStart(Object start){this.put("tbpAddTimeStart", start);return this;};			
 		/** and tbp_add_time <= ? */
 		public M tbpAddTimeEnd(Object end){this.put("tbpAddTimeEnd", end);return this;};
		/** 状态 [非空]       **/
		public M tbpStatus(Object tbpStatus){this.put("tbpStatus", tbpStatus);return this;};
		/** and tbp_status is null */
		public M tbpStatusNull(){if(this.get("tbpStatusNot")==null)this.put("tbpStatusNot", "");this.put("tbpStatus", null);return this;};
		/** not .... */
		public M tbpStatusNot(){this.put("tbpStatusNot", "not");return this;};
		/** and tbp_status >= ? */
		public M tbpStatusMin(Object min){this.put("tbpStatusMin", min);return this;};
		/** and tbp_status <= ? */
		public M tbpStatusMax(Object max){this.put("tbpStatusMax", max);return this;};
		/** 所在网点 [可空]       **/
		public M tbpOutlets(Object tbpOutlets){this.put("tbpOutlets", tbpOutlets);return this;};
		/** and tbp_outlets is null */
		public M tbpOutletsNull(){if(this.get("tbpOutletsNot")==null)this.put("tbpOutletsNot", "");this.put("tbpOutlets", null);return this;};
		/** not .... */
		public M tbpOutletsNot(){this.put("tbpOutletsNot", "not");return this;};
		public M tbpOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("tbpOutlets$on", value);
			return this;
		};	
		/** and tbp_outlets >= ? */
		public M tbpOutletsMin(Object min){this.put("tbpOutletsMin", min);return this;};
		/** and tbp_outlets <= ? */
		public M tbpOutletsMax(Object max){this.put("tbpOutletsMax", max);return this;};
		/** 位置信息 [可空]       **/
		public M tbpSpecialData(Object tbpSpecialData){this.put("tbpSpecialData", tbpSpecialData);return this;};
		/** and tbp_special_data is null */
		public M tbpSpecialDataNull(){if(this.get("tbpSpecialDataNot")==null)this.put("tbpSpecialDataNot", "");this.put("tbpSpecialData", null);return this;};
		/** not .... */
		public M tbpSpecialDataNot(){this.put("tbpSpecialDataNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有问题故障报修 **/
		public @api List<TbProblem> list(Integer size){
			return getTbProblemList(this,size);
		}
		/** 获取问题故障报修分页 **/
		public @api Page<TbProblem> page(int page,int size){
			return getTbProblemPage(page, size , this);
		}
		/** 根据查询条件取问题故障报修 **/
		public @api TbProblem get(){
			return getTbProblem(this);
		}
		/** 获取问题故障报修数 **/
		public @api Long count(){
			return getTbProblemCount(this);
		}
		/** 获取问题故障报修表达式 **/
		public @api <T> T eval(String strEval){
			return getTbProblemEval(strEval,this);
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
			updateTbProblem(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbpId="tbpId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String tbpHost="tbpHost";
		/** 问题描述 [非空]       **/
		public final static @type(String.class)  String tbpProfile="tbpProfile";
		/** 问题类型 [非空]       **/
		public final static @type(Short.class)  String tbpType="tbpType";
		/** 反馈会员 [非空]       **/
		public final static @type(Long.class)  String tbpMember="tbpMember";
		/** 所属订单 [非空]       **/
		public final static @type(Long.class)  String tbpOrder="tbpOrder";
		/** 关联对象 [非空]       **/
		public final static @type(String.class)  String tbpRelate="tbpRelate";
		/** 添加人员 [非空]       **/
		public final static @type(Long.class)  String tbpAdder="tbpAdder";
		/** 修改人员 [非空]       **/
		public final static @type(Long.class)  String tbpEditor="tbpEditor";
		/** 任务指派 [非空]       **/
		public final static @type(Long.class)  String tbpDealer="tbpDealer";
		/** 结案描述 [非空]       **/
		public final static @type(String.class)  String tbpResult="tbpResult";
		/** 备注 [非空]       **/
		public final static @type(String.class)  String tbpRemark="tbpRemark";
		/** 备忘信息 [非空]       **/
		public final static @type(String.class)  String tbpMemo="tbpMemo";
		/** 程序数据 [非空]       **/
		public final static @type(String.class)  String tbpData="tbpData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbpUpdateTime="tbpUpdateTime";
		/** and tbp_update_time >= ? */
 		public final static @type(Date.class) String tbpUpdateTimeStart="tbpUpdateTimeStart";
 		/** and tbp_update_time <= ? */
 		public final static @type(Date.class) String tbpUpdateTimeEnd="tbpUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbpAddTime="tbpAddTime";
		/** and tbp_add_time >= ? */
 		public final static @type(Date.class) String tbpAddTimeStart="tbpAddTimeStart";
 		/** and tbp_add_time <= ? */
 		public final static @type(Date.class) String tbpAddTimeEnd="tbpAddTimeEnd";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String tbpStatus="tbpStatus";
		/** 所在网点 [可空]       **/
		public final static @type(Long.class)  String tbpOutlets="tbpOutlets";
		/** 位置信息 [可空]       **/
		public final static @type(String.class)  String tbpSpecialData="tbpSpecialData";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbpId="tbp_id";
		/** 城市 [非空]       **/
		public final static String tbpHost="tbp_host";
		/** 问题描述 [非空]       **/
		public final static String tbpProfile="tbp_profile";
		/** 问题类型 [非空]       **/
		public final static String tbpType="tbp_type";
		/** 反馈会员 [非空]       **/
		public final static String tbpMember="tbp_member";
		/** 所属订单 [非空]       **/
		public final static String tbpOrder="tbp_order";
		/** 关联对象 [非空]       **/
		public final static String tbpRelate="tbp_relate";
		/** 添加人员 [非空]       **/
		public final static String tbpAdder="tbp_adder";
		/** 修改人员 [非空]       **/
		public final static String tbpEditor="tbp_editor";
		/** 任务指派 [非空]       **/
		public final static String tbpDealer="tbp_dealer";
		/** 结案描述 [非空]       **/
		public final static String tbpResult="tbp_result";
		/** 备注 [非空]       **/
		public final static String tbpRemark="tbp_remark";
		/** 备忘信息 [非空]       **/
		public final static String tbpMemo="tbp_memo";
		/** 程序数据 [非空]       **/
		public final static String tbpData="tbp_data";
		/** 修改时间 [非空]       **/
		public final static String tbpUpdateTime="tbp_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbpAddTime="tbp_add_time";
		/** 状态 [非空]       **/
		public final static String tbpStatus="tbp_status";
		/** 所在网点 [可空]       **/
		public final static String tbpOutlets="tbp_outlets";
		/** 位置信息 [可空]       **/
		public final static String tbpSpecialData="tbp_special_data";
		
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
				$.Set(name,TbProblem.getTbProblem(params));
			else
				$.Set(name,TbProblem.getTbProblemList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取问题故障报修数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbProblem) $.GetRequest("TbProblem$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbProblem.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbProblem.getTbProblem(params);
			else
				value = TbProblem.getTbProblemList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbProblem.Get($.add(TbProblem.F.tbpId,param));
		else if(!$.empty(param.toString()))
			value = TbProblem.get(Long.valueOf(param.toString()));
		$.SetRequest("TbProblem$"+param.hashCode(), value);
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
	public void mergeSet(TbProblem old){
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