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

@namespace("sale/battle")
public @caption("销售战役") @table("tb_sale_battle") class TbSaleBattle implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbsb_id")   @primary  @note("  ") Long tbsbId;// 主键 非空     
	private @caption("城市") @column("tbsb_host")    @relate("$tbsbHost") @RelateClass(SrvHost.class)  @note("  ") Long tbsbHost;// 非空     
 	private SrvHost $tbsbHost;//关联对象[运营城市]
	private @caption("战役标题") @column("tbsb_title")    @note("  ") String tbsbTitle;// 非空     
	private @caption("战役描述") @column("tbsb_detail")    @note("  ") String tbsbDetail;//     
	private @caption("战役负责人") @column("tbsb_header")    @relate("$tbsbHeader") @RelateClass(SrvUser.class)  @note("  ") Long tbsbHeader;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbsbHeader;//关联对象[用户]
	private @caption("开始时间") @column("tbsb_start")    @note("  ") Date tbsbStart;// 非空     
	private @caption("结束时间") @column("tbsb_finish")    @note("  ") Date tbsbFinish;// 非空     
	private @caption("添加人") @column("tbsb_adder")    @relate("$tbsbAdder") @RelateClass(SrvUser.class)  @note("  ") Long tbsbAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbsbAdder;//关联对象[用户]
	private @caption("修改人") @column("tbsb_editor")    @relate("$tbsbEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbsbEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbsbEditor;//关联对象[用户]
	private @caption("备注") @column("tbsb_remark")    @note("  ") String tbsbRemark;//     
	private @caption("修改时间") @column("tbsb_update_time")    @note("  ") Date tbsbUpdateTime;// 非空     
	private @caption("添加时间") @column("tbsb_add_time")    @note("  ") Date tbsbAddTime;// 非空     
	private @caption("状态") @column("tbsb_status")    @note(" 0:策划中 1:执行中 2:已完成 3:已放弃  ") Short tbsbStatus;// 非空 0:策划中 1:执行中 2:已完成 3:已放弃     
	
	//默认构造函数
	public TbSaleBattle(){
	
	}
	
	//主键构造函数
	public TbSaleBattle(Long id){
		this.tbsbId = id;
	}
	
	/**所有字段构造函数 TbSaleBattle(tbsbHost,tbsbTitle,tbsbDetail,tbsbHeader,tbsbStart,tbsbFinish,tbsbAdder,tbsbEditor,tbsbRemark,tbsbUpdateTime,tbsbAddTime,tbsbStatus)
	 TbSaleBattle(
	 	$.getLong("tbsbHost")//城市 [非空]
	 	,$.getString("tbsbTitle")//战役标题 [非空]
	 	,$.getString("tbsbDetail")//战役描述
	 	,$.getLong("tbsbHeader")//战役负责人
	 	,$.getDate("tbsbStart")//开始时间 [非空]
	 	,$.getDate("tbsbFinish")//结束时间 [非空]
	 	,$.getLong("tbsbAdder")//添加人 [非空]
	 	,$.getLong("tbsbEditor")//修改人 [非空]
	 	,$.getString("tbsbRemark")//备注
	 	,$.getDate("tbsbUpdateTime")//修改时间 [非空]
	 	,$.getDate("tbsbAddTime")//添加时间 [非空]
	 	,$.getShort("tbsbStatus")//状态 [非空]
	 )
	**/
	public TbSaleBattle(Long tbsbHost,String tbsbTitle,String tbsbDetail,Long tbsbHeader,Date tbsbStart,Date tbsbFinish,Long tbsbAdder,Long tbsbEditor,String tbsbRemark,Date tbsbUpdateTime,Date tbsbAddTime,Short tbsbStatus){
		this.tbsbHost=tbsbHost;
		this.tbsbTitle=tbsbTitle;
		this.tbsbDetail=tbsbDetail;
		this.tbsbHeader=tbsbHeader;
		this.tbsbStart=tbsbStart;
		this.tbsbFinish=tbsbFinish;
		this.tbsbAdder=tbsbAdder;
		this.tbsbEditor=tbsbEditor;
		this.tbsbRemark=tbsbRemark;
		this.tbsbUpdateTime=tbsbUpdateTime;
		this.tbsbAddTime=tbsbAddTime;
		this.tbsbStatus=tbsbStatus;
	}
	
	//设置非空字段
	public TbSaleBattle setNotNull(Long tbsbHost,String tbsbTitle,Date tbsbStart,Date tbsbFinish,Long tbsbAdder,Long tbsbEditor,Date tbsbUpdateTime,Date tbsbAddTime,Short tbsbStatus){
		this.tbsbHost=tbsbHost;
		this.tbsbTitle=tbsbTitle;
		this.tbsbStart=tbsbStart;
		this.tbsbFinish=tbsbFinish;
		this.tbsbAdder=tbsbAdder;
		this.tbsbEditor=tbsbEditor;
		this.tbsbUpdateTime=tbsbUpdateTime;
		this.tbsbAddTime=tbsbAddTime;
		this.tbsbStatus=tbsbStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbSaleBattle tbsbId(Long tbsbId){
		this.tbsbId = tbsbId;
		this.setSeted(F.tbsbId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbSaleBattle tbsbHost(Long tbsbHost){
		this.tbsbHost = tbsbHost;
		this.setSeted(F.tbsbHost);
		return this;
	}
	/** 战役标题 [非空]       **/
	public TbSaleBattle tbsbTitle(String tbsbTitle){
		this.tbsbTitle = tbsbTitle;
		this.setSeted(F.tbsbTitle);
		return this;
	}
	/** 战役描述        **/
	public TbSaleBattle tbsbDetail(String tbsbDetail){
		this.tbsbDetail = tbsbDetail;
		this.setSeted(F.tbsbDetail);
		return this;
	}
	/** 战役负责人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleBattle tbsbHeader(Long tbsbHeader){
		this.tbsbHeader = tbsbHeader;
		this.setSeted(F.tbsbHeader);
		return this;
	}
	/** 开始时间 [非空]       **/
	public TbSaleBattle tbsbStart(Date tbsbStart){
		this.tbsbStart = tbsbStart;
		this.setSeted(F.tbsbStart);
		return this;
	}
	/** 结束时间 [非空]       **/
	public TbSaleBattle tbsbFinish(Date tbsbFinish){
		this.tbsbFinish = tbsbFinish;
		this.setSeted(F.tbsbFinish);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleBattle tbsbAdder(Long tbsbAdder){
		this.tbsbAdder = tbsbAdder;
		this.setSeted(F.tbsbAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleBattle tbsbEditor(Long tbsbEditor){
		this.tbsbEditor = tbsbEditor;
		this.setSeted(F.tbsbEditor);
		return this;
	}
	/** 备注        **/
	public TbSaleBattle tbsbRemark(String tbsbRemark){
		this.tbsbRemark = tbsbRemark;
		this.setSeted(F.tbsbRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbSaleBattle tbsbUpdateTime(Date tbsbUpdateTime){
		this.tbsbUpdateTime = tbsbUpdateTime;
		this.setSeted(F.tbsbUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbSaleBattle tbsbAddTime(Date tbsbAddTime){
		this.tbsbAddTime = tbsbAddTime;
		this.setSeted(F.tbsbAddTime);
		return this;
	}
	/** 状态 [非空]   0:策划中 1:执行中 2:已完成 3:已放弃     **/
	public TbSaleBattle tbsbStatus(Short tbsbStatus){
		this.tbsbStatus = tbsbStatus;
		this.setSeted(F.tbsbStatus);
		return this;
	}
	
	
	//克隆对象
	public TbSaleBattle clone(){
		TbSaleBattle clone = new TbSaleBattle();
		clone.tbsbHost=this.tbsbHost;
		clone.tbsbTitle=this.tbsbTitle;
		clone.tbsbStart=this.tbsbStart;
		clone.tbsbFinish=this.tbsbFinish;
		clone.tbsbAdder=this.tbsbAdder;
		clone.tbsbEditor=this.tbsbEditor;
		clone.tbsbUpdateTime=this.tbsbUpdateTime;
		clone.tbsbAddTime=this.tbsbAddTime;
		clone.tbsbStatus=this.tbsbStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取销售战役
	 * @param id
	 * @return
	 */
	public static @api TbSaleBattle get(Long id){		
		return getTbSaleBattleById(id);
	}
	/**
	 * 获取所有销售战役
	 * @return
	 */
	public static @api List<TbSaleBattle> list(Map params,Integer size){
		return getTbSaleBattleList(params,size);
	}
	/**
	 * 获取销售战役分页
	 * @return
	 */
	public static @api Page<TbSaleBattle> page(int page,int size,Map params){
		return getTbSaleBattlePage(page, size , params);
	}
	/**
	 * 根据查询条件取销售战役
	 * @param params
	 * @return
	 */
	public static @api TbSaleBattle Get(Map params){
		return getTbSaleBattle(params);
	}
	/**
	 * 获取销售战役数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbSaleBattleCount(params);
	}
	/**
	 * 获取销售战役数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbSaleBattleEval(eval,params);
	}
	
	/**
	 * 根据ID取销售战役
	 * @param id
	 * @return
	 */
	public static @api TbSaleBattle getTbSaleBattleById(Long id){		
		TbSaleBattle tbSaleBattle = (TbSaleBattle) $.GetRequest("TbSaleBattle$"+id);
		if(tbSaleBattle!=null)
			return tbSaleBattle;
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");		
		return tbSaleBattleService.getTbSaleBattleById(id);
	}
	
	
	/**
	 * 根据ID取销售战役的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbSaleBattle.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbSaleBattle tbSaleBattle = get(id);
		if(tbSaleBattle!=null){
			String strValue = tbSaleBattle.getTbsbTitle$();
			if(!"TbsbTitle".equals("TbsbTitle"))
				strValue+="("+tbSaleBattle.getTbsbTitle$()+")";
			MemCache.setValue(TbSaleBattle.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbsbTitle$();
		if(!"TbsbTitle".equals("TbsbTitle"))
			keyValue+="("+this.getTbsbTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有销售战役
	 * @return
	 */
	public static @api List<TbSaleBattle> getTbSaleBattleList(Map params,Integer size){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		return tbSaleBattleService.getTbSaleBattleList(params, size);
	}
	
	/**
	 * 获取销售战役分页
	 * @return
	 */
	public static @api Page<TbSaleBattle> getTbSaleBattlePage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		return tbSaleBattleService.getTbSaleBattlePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取销售战役
	 * @param params
	 * @return
	 */
	public static @api TbSaleBattle getTbSaleBattle(Map params){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		return tbSaleBattleService.getTbSaleBattle(params);
	}
	
	/**
	 * 获取销售战役数
	 * @return
	 */
	public static @api Long getTbSaleBattleCount(Map params){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		return tbSaleBattleService.getTbSaleBattleCount(params);
	}
		
		
	/**
	 * 获取销售战役自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbSaleBattleEval(String eval,Map params){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		return tbSaleBattleService.getTbSaleBattleEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbSaleBattle(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		tbSaleBattleService.updateTbSaleBattleByConfirm(set, where);
	}
	
	
	/**
	 * 保存销售战役对象
	 * @param params
	 * @return
	 */
	public TbSaleBattle save(){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		if(this.getTbsbId()!=null)
			tbSaleBattleService.updateTbSaleBattle(this);
		else
			return tbSaleBattleService.saveTbSaleBattle(this);
		return this;
	}
	
	
	/**
	 * 更新销售战役对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		tbSaleBattleService.updateTbSaleBattle$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		if($.equals($.config("logic_delete"),"true"))
			tbSaleBattleService.removeTbSaleBattleById(this.getTbsbId());
		else
			tbSaleBattleService.deleteTbSaleBattleById(this.getTbsbId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbSaleBattleService tbSaleBattleService = $.GetSpringBean("tbSaleBattleService");
		return tbSaleBattleService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbsbId(){
		return this.tbsbId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbsbId$(){
		String strValue="";
		 strValue=$.str(this.getTbsbId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbsbId(Long tbsbId){
		this.tbsbId = tbsbId;
		this.setSeted(F.tbsbId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbsbHost(){
		return this.tbsbHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbsbHost$(){
		String strValue="";
		if(this.getTbsbHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbsbHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbsbHost(Long tbsbHost){
		this.tbsbHost = tbsbHost;
		this.setSeted(F.tbsbHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbsbHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbsbHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbsbHost()!=null){
 			srvHost = SrvHost.get(this.getTbsbHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbsbHost(), srvHost);
	 	return srvHost;
	}
	/*******************************战役标题**********************************/	
	/**
	* 战役标题 [非空]      
	**/
	public String getTbsbTitle(){
		return this.tbsbTitle;
	}
	/**
	* 获取战役标题格式化(toString)
	**/
	public String getTbsbTitle$(){
		String strValue="";
		 strValue=$.str(this.getTbsbTitle());
	 	 return strValue;
	}
	/**
	* 战役标题 [非空]      
	**/
	public void setTbsbTitle(String tbsbTitle){
		this.tbsbTitle = tbsbTitle;
		this.setSeted(F.tbsbTitle);
	}
	/*******************************战役描述**********************************/	
	/**
	* 战役描述       
	**/
	public String getTbsbDetail(){
		return this.tbsbDetail;
	}
	/**
	* 获取战役描述格式化(toString)
	**/
	public String getTbsbDetail$(){
		String strValue="";
		 strValue=$.str(this.getTbsbDetail());
	 	 return strValue;
	}
	/**
	* 战役描述       
	**/
	public void setTbsbDetail(String tbsbDetail){
		this.tbsbDetail = tbsbDetail;
		this.setSeted(F.tbsbDetail);
	}
	/*******************************战役负责人**********************************/	
	/**
	* 战役负责人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbsbHeader(){
		return this.tbsbHeader;
	}
	/**
	* 获取战役负责人格式化(toString)
	**/
	public String getTbsbHeader$(){
		String strValue="";
		if(this.getTbsbHeader()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbsbHeader()));
		}			
	 	 return strValue;
	}
	/**
	* 战役负责人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbsbHeader(Long tbsbHeader){
		this.tbsbHeader = tbsbHeader;
		this.setSeted(F.tbsbHeader);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbsbHeader(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbsbHeader());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbsbHeader()!=null){
 			srvUser = SrvUser.get(this.getTbsbHeader());
 		}
 		$.SetRequest("SrvUser$"+this.getTbsbHeader(), srvUser);
	 	return srvUser;
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间 [非空]      
	**/
	public Date getTbsbStart(){
		return this.tbsbStart;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getTbsbStart$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbsbStart(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 开始时间 [非空]      
	**/
	public void setTbsbStart(Date tbsbStart){
		this.tbsbStart = tbsbStart;
		this.setSeted(F.tbsbStart);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间 [非空]      
	**/
	public Date getTbsbFinish(){
		return this.tbsbFinish;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getTbsbFinish$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbsbFinish(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 结束时间 [非空]      
	**/
	public void setTbsbFinish(Date tbsbFinish){
		this.tbsbFinish = tbsbFinish;
		this.setSeted(F.tbsbFinish);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbsbAdder(){
		return this.tbsbAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getTbsbAdder$(){
		String strValue="";
		if(this.getTbsbAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbsbAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbsbAdder(Long tbsbAdder){
		this.tbsbAdder = tbsbAdder;
		this.setSeted(F.tbsbAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbsbAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbsbAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbsbAdder()!=null){
 			srvUser = SrvUser.get(this.getTbsbAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getTbsbAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbsbEditor(){
		return this.tbsbEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getTbsbEditor$(){
		String strValue="";
		if(this.getTbsbEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbsbEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbsbEditor(Long tbsbEditor){
		this.tbsbEditor = tbsbEditor;
		this.setSeted(F.tbsbEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbsbEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbsbEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbsbEditor()!=null){
 			srvUser = SrvUser.get(this.getTbsbEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbsbEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getTbsbRemark(){
		return this.tbsbRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getTbsbRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbsbRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setTbsbRemark(String tbsbRemark){
		this.tbsbRemark = tbsbRemark;
		this.setSeted(F.tbsbRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbsbUpdateTime(){
		return this.tbsbUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbsbUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbsbUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setTbsbUpdateTime(Date tbsbUpdateTime){
		this.tbsbUpdateTime = tbsbUpdateTime;
		this.setSeted(F.tbsbUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbsbAddTime(){
		return this.tbsbAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbsbAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbsbAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setTbsbAddTime(Date tbsbAddTime){
		this.tbsbAddTime = tbsbAddTime;
		this.setSeted(F.tbsbAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:策划中 1:执行中 2:已完成 3:已放弃    
	**/
	public Short getTbsbStatus(){
		return this.tbsbStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbsbStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbsbStatus()),"0"))
			strValue=$.str("策划中");		 
		 if($.equals($.str(this.getTbsbStatus()),"1"))
			strValue=$.str("执行中");		 
		 if($.equals($.str(this.getTbsbStatus()),"2"))
			strValue=$.str("已完成");		 
		 if($.equals($.str(this.getTbsbStatus()),"3"))
			strValue=$.str("已放弃");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:策划中 1:执行中 2:已完成 3:已放弃    
	**/
	public void setTbsbStatus(Short tbsbStatus){
		this.tbsbStatus = tbsbStatus;
		this.setSeted(F.tbsbStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbSaleBattle.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleBattle.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleBattle.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbSaleBattle.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbSaleBattle.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleBattle.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleBattle.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbSaleBattle.this);
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
		public M tbsbId(Object tbsbId){this.put("tbsbId", tbsbId);return this;};
	 	/** and tbsb_id is null */
 		public M tbsbIdNull(){if(this.get("tbsbIdNot")==null)this.put("tbsbIdNot", "");this.put("tbsbId", null);return this;};
 		/** not .... */
 		public M tbsbIdNot(){this.put("tbsbIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbsbHost(Object tbsbHost){this.put("tbsbHost", tbsbHost);return this;};
	 	/** and tbsb_host is null */
 		public M tbsbHostNull(){if(this.get("tbsbHostNot")==null)this.put("tbsbHostNot", "");this.put("tbsbHost", null);return this;};
 		/** not .... */
 		public M tbsbHostNot(){this.put("tbsbHostNot", "not");return this;};
		/** 战役标题 [非空]       **/
		public M tbsbTitle(Object tbsbTitle){this.put("tbsbTitle", tbsbTitle);return this;};
	 	/** and tbsb_title is null */
 		public M tbsbTitleNull(){if(this.get("tbsbTitleNot")==null)this.put("tbsbTitleNot", "");this.put("tbsbTitle", null);return this;};
 		/** not .... */
 		public M tbsbTitleNot(){this.put("tbsbTitleNot", "not");return this;};
		/** 战役描述        **/
		public M tbsbDetail(Object tbsbDetail){this.put("tbsbDetail", tbsbDetail);return this;};
	 	/** and tbsb_detail is null */
 		public M tbsbDetailNull(){if(this.get("tbsbDetailNot")==null)this.put("tbsbDetailNot", "");this.put("tbsbDetail", null);return this;};
 		/** not .... */
 		public M tbsbDetailNot(){this.put("tbsbDetailNot", "not");return this;};
		/** 战役负责人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbsbHeader(Object tbsbHeader){this.put("tbsbHeader", tbsbHeader);return this;};
	 	/** and tbsb_header is null */
 		public M tbsbHeaderNull(){if(this.get("tbsbHeaderNot")==null)this.put("tbsbHeaderNot", "");this.put("tbsbHeader", null);return this;};
 		/** not .... */
 		public M tbsbHeaderNot(){this.put("tbsbHeaderNot", "not");return this;};
		/** 开始时间 [非空]       **/
		public M tbsbStart(Object tbsbStart){this.put("tbsbStart", tbsbStart);return this;};
	 	/** and tbsb_start is null */
 		public M tbsbStartNull(){if(this.get("tbsbStartNot")==null)this.put("tbsbStartNot", "");this.put("tbsbStart", null);return this;};
 		/** not .... */
 		public M tbsbStartNot(){this.put("tbsbStartNot", "not");return this;};
 		/** and tbsb_start >= ? */
 		public M tbsbStartStart(Object start){this.put("tbsbStartStart", start);return this;};			
 		/** and tbsb_start <= ? */
 		public M tbsbStartEnd(Object end){this.put("tbsbStartEnd", end);return this;};
		/** 结束时间 [非空]       **/
		public M tbsbFinish(Object tbsbFinish){this.put("tbsbFinish", tbsbFinish);return this;};
	 	/** and tbsb_finish is null */
 		public M tbsbFinishNull(){if(this.get("tbsbFinishNot")==null)this.put("tbsbFinishNot", "");this.put("tbsbFinish", null);return this;};
 		/** not .... */
 		public M tbsbFinishNot(){this.put("tbsbFinishNot", "not");return this;};
 		/** and tbsb_finish >= ? */
 		public M tbsbFinishStart(Object start){this.put("tbsbFinishStart", start);return this;};			
 		/** and tbsb_finish <= ? */
 		public M tbsbFinishEnd(Object end){this.put("tbsbFinishEnd", end);return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbsbAdder(Object tbsbAdder){this.put("tbsbAdder", tbsbAdder);return this;};
	 	/** and tbsb_adder is null */
 		public M tbsbAdderNull(){if(this.get("tbsbAdderNot")==null)this.put("tbsbAdderNot", "");this.put("tbsbAdder", null);return this;};
 		/** not .... */
 		public M tbsbAdderNot(){this.put("tbsbAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbsbEditor(Object tbsbEditor){this.put("tbsbEditor", tbsbEditor);return this;};
	 	/** and tbsb_editor is null */
 		public M tbsbEditorNull(){if(this.get("tbsbEditorNot")==null)this.put("tbsbEditorNot", "");this.put("tbsbEditor", null);return this;};
 		/** not .... */
 		public M tbsbEditorNot(){this.put("tbsbEditorNot", "not");return this;};
		/** 备注        **/
		public M tbsbRemark(Object tbsbRemark){this.put("tbsbRemark", tbsbRemark);return this;};
	 	/** and tbsb_remark is null */
 		public M tbsbRemarkNull(){if(this.get("tbsbRemarkNot")==null)this.put("tbsbRemarkNot", "");this.put("tbsbRemark", null);return this;};
 		/** not .... */
 		public M tbsbRemarkNot(){this.put("tbsbRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M tbsbUpdateTime(Object tbsbUpdateTime){this.put("tbsbUpdateTime", tbsbUpdateTime);return this;};
	 	/** and tbsb_update_time is null */
 		public M tbsbUpdateTimeNull(){if(this.get("tbsbUpdateTimeNot")==null)this.put("tbsbUpdateTimeNot", "");this.put("tbsbUpdateTime", null);return this;};
 		/** not .... */
 		public M tbsbUpdateTimeNot(){this.put("tbsbUpdateTimeNot", "not");return this;};
 		/** and tbsb_update_time >= ? */
 		public M tbsbUpdateTimeStart(Object start){this.put("tbsbUpdateTimeStart", start);return this;};			
 		/** and tbsb_update_time <= ? */
 		public M tbsbUpdateTimeEnd(Object end){this.put("tbsbUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbsbAddTime(Object tbsbAddTime){this.put("tbsbAddTime", tbsbAddTime);return this;};
	 	/** and tbsb_add_time is null */
 		public M tbsbAddTimeNull(){if(this.get("tbsbAddTimeNot")==null)this.put("tbsbAddTimeNot", "");this.put("tbsbAddTime", null);return this;};
 		/** not .... */
 		public M tbsbAddTimeNot(){this.put("tbsbAddTimeNot", "not");return this;};
 		/** and tbsb_add_time >= ? */
 		public M tbsbAddTimeStart(Object start){this.put("tbsbAddTimeStart", start);return this;};			
 		/** and tbsb_add_time <= ? */
 		public M tbsbAddTimeEnd(Object end){this.put("tbsbAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:策划中 1:执行中 2:已完成 3:已放弃     **/
		public M tbsbStatus(Object tbsbStatus){this.put("tbsbStatus", tbsbStatus);return this;};
	 	/** and tbsb_status is null */
 		public M tbsbStatusNull(){if(this.get("tbsbStatusNot")==null)this.put("tbsbStatusNot", "");this.put("tbsbStatus", null);return this;};
 		/** not .... */
 		public M tbsbStatusNot(){this.put("tbsbStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有销售战役 **/
		public @api List<TbSaleBattle> list(Integer size){
			return getTbSaleBattleList(this,size);
		}
		/** 获取销售战役分页 **/
		public @api Page<TbSaleBattle> page(int page,int size){
			return getTbSaleBattlePage(page, size , this);
		}
		/** 根据查询条件取销售战役 **/
		public @api TbSaleBattle get(){
			return getTbSaleBattle(this);
		}
		/** 获取销售战役数 **/
		public @api Long count(){
			return getTbSaleBattleCount(this);
		}
		/** 获取销售战役表达式 **/
		public @api <T> T eval(String strEval){
			return getTbSaleBattleEval(strEval,this);
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
			updateTbSaleBattle(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbsbId="tbsbId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbsbHost="tbsbHost";
		/** 战役标题 [非空]       **/
		public final static @type(String.class) @like String tbsbTitle="tbsbTitle";
		/** 战役描述        **/
		public final static @type(String.class) @like String tbsbDetail="tbsbDetail";
		/** 战役负责人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbsbHeader="tbsbHeader";
		/** 开始时间 [非空]       **/
		public final static @type(Date.class)  String tbsbStart="tbsbStart";
	 	/** and tbsb_start >= ? */
 		public final static @type(Date.class) String tbsbStartStart="tbsbStartStart";
 		/** and tbsb_start <= ? */
 		public final static @type(Date.class) String tbsbStartEnd="tbsbStartEnd";
		/** 结束时间 [非空]       **/
		public final static @type(Date.class)  String tbsbFinish="tbsbFinish";
	 	/** and tbsb_finish >= ? */
 		public final static @type(Date.class) String tbsbFinishStart="tbsbFinishStart";
 		/** and tbsb_finish <= ? */
 		public final static @type(Date.class) String tbsbFinishEnd="tbsbFinishEnd";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbsbAdder="tbsbAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbsbEditor="tbsbEditor";
		/** 备注        **/
		public final static @type(String.class) @like String tbsbRemark="tbsbRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbsbUpdateTime="tbsbUpdateTime";
	 	/** and tbsb_update_time >= ? */
 		public final static @type(Date.class) String tbsbUpdateTimeStart="tbsbUpdateTimeStart";
 		/** and tbsb_update_time <= ? */
 		public final static @type(Date.class) String tbsbUpdateTimeEnd="tbsbUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbsbAddTime="tbsbAddTime";
	 	/** and tbsb_add_time >= ? */
 		public final static @type(Date.class) String tbsbAddTimeStart="tbsbAddTimeStart";
 		/** and tbsb_add_time <= ? */
 		public final static @type(Date.class) String tbsbAddTimeEnd="tbsbAddTimeEnd";
		/** 状态 [非空]   0:策划中 1:执行中 2:已完成 3:已放弃     **/
		public final static @type(Short.class)  String tbsbStatus="tbsbStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbsbId="tbsb_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbsbHost="tbsb_host";
		/** 战役标题 [非空]       **/
		public final static String tbsbTitle="tbsb_title";
		/** 战役描述        **/
		public final static String tbsbDetail="tbsb_detail";
		/** 战役负责人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbsbHeader="tbsb_header";
		/** 开始时间 [非空]       **/
		public final static String tbsbStart="tbsb_start";
		/** 结束时间 [非空]       **/
		public final static String tbsbFinish="tbsb_finish";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbsbAdder="tbsb_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbsbEditor="tbsb_editor";
		/** 备注        **/
		public final static String tbsbRemark="tbsb_remark";
		/** 修改时间 [非空]       **/
		public final static String tbsbUpdateTime="tbsb_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbsbAddTime="tbsb_add_time";
		/** 状态 [非空]   0:策划中 1:执行中 2:已完成 3:已放弃     **/
		public final static String tbsbStatus="tbsb_status";
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
				$.Set(name,TbSaleBattle.getTbSaleBattle(params));
			else
				$.Set(name,TbSaleBattle.getTbSaleBattleList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取销售战役数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbSaleBattle) $.GetRequest("TbSaleBattle$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbSaleBattle.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbSaleBattle.getTbSaleBattle(params);
			else
				value = TbSaleBattle.getTbSaleBattleList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbSaleBattle.Get($.add(TbSaleBattle.F.tbsbId,param));
		else if(!$.empty(param.toString()))
			value = TbSaleBattle.get(Long.valueOf(param.toString()));
		$.SetRequest("TbSaleBattle$"+param.hashCode(), value);
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
	public void mergeSet(TbSaleBattle old){
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