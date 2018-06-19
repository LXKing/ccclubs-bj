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

@namespace("oa/outside/affirm")
public @caption("异借确认") @table("tb_ab_affirm") class TbAbAffirm implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbaa_id")   @primary  @note("  ") Long tbaaId;// 主键 非空     
	private @caption("城市") @column("tbaa_host")    @relate("$tbaaHost") @RelateClass(SrvHost.class)  @note("  ") Long tbaaHost;// 非空     
 	private SrvHost $tbaaHost;//关联对象[运营城市]
	private @caption("所属车辆") @column("tbaa_car")    @relate("$tbaaCar") @RelateClass(CsCar.class)  @note("  ") Long tbaaCar;// 非空     
 	private CsCar $tbaaCar;//关联对象[车辆]
	private @caption("所属订单") @column("tbaa_order")    @relate("$tbaaOrder") @RelateClass(CsOrder.class)  @note("  ") Long tbaaOrder;//     
 	private CsOrder $tbaaOrder;//关联对象[系统订单]
	private @caption("借出网点") @column("tbaa_out")    @relate("$tbaaOut") @RelateClass(CsOutlets.class)  @note("  ") Long tbaaOut;//     
 	private CsOutlets $tbaaOut;//关联对象[网点]
	private @caption("预计还入网点") @column("tbaa_ret")    @relate("$tbaaRet") @RelateClass(CsOutlets.class)  @note("  ") Long tbaaRet;//     
 	private CsOutlets $tbaaRet;//关联对象[网点]
	private @caption("实际还入网点") @column("tbaa_real_ret")    @relate("$tbaaRealRet") @RelateClass(CsOutlets.class)  @note("  ") Long tbaaRealRet;//     
 	private CsOutlets $tbaaRealRet;//关联对象[网点]
	private @caption("预计还入时间") @column("tbaa_time")    @note("  ") Date tbaaTime;//     
	private @caption("实际还入时间") @column("tbaa_real_time")    @note("  ") Date tbaaRealTime;//     
	private @caption("确认人") @column("tbaa_confirm")    @relate("$tbaaConfirm") @RelateClass(SrvUser.class)  @note("  ") Long tbaaConfirm;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbaaConfirm;//关联对象[用户]
	private @caption("添加人") @column("tbaa_adder")    @relate("$tbaaAdder") @RelateClass(SrvUser.class)  @note("  ") Long tbaaAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbaaAdder;//关联对象[用户]
	private @caption("修改人") @column("tbaa_editor")    @relate("$tbaaEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbaaEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbaaEditor;//关联对象[用户]
	private @caption("确认时间") @column("tbaa_confirm_time")    @note("  ") Date tbaaConfirmTime;//     
	private @caption("修改时间") @column("tbaa_update_time")    @note("  ") Date tbaaUpdateTime;// 非空     
	private @caption("添加时间") @column("tbaa_add_time")    @note("  ") Date tbaaAddTime;// 非空     
	private @caption("备注") @column("tbaa_remark")    @note("  ") String tbaaRemark;//     
	private @caption("状态") @column("tbaa_status")    @note(" 0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认") Short tbaaStatus;// 非空 0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认   
	
	//默认构造函数
	public TbAbAffirm(){
	
	}
	
	//主键构造函数
	public TbAbAffirm(Long id){
		this.tbaaId = id;
	}
	
	/**所有字段构造函数 TbAbAffirm(tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm,tbaaAdder,tbaaEditor,tbaaConfirmTime,tbaaUpdateTime,tbaaAddTime,tbaaRemark,tbaaStatus)
	 TbAbAffirm(
	 	$.getLong("tbaaHost")//城市 [非空]
	 	,$.getLong("tbaaCar")//所属车辆 [非空]
	 	,$.getLong("tbaaOrder")//所属订单
	 	,$.getLong("tbaaOut")//借出网点
	 	,$.getLong("tbaaRet")//预计还入网点
	 	,$.getLong("tbaaRealRet")//实际还入网点
	 	,$.getDate("tbaaTime")//预计还入时间
	 	,$.getDate("tbaaRealTime")//实际还入时间
	 	,$.getLong("tbaaConfirm")//确认人
	 	,$.getLong("tbaaAdder")//添加人 [非空]
	 	,$.getLong("tbaaEditor")//修改人 [非空]
	 	,$.getDate("tbaaConfirmTime")//确认时间
	 	,$.getDate("tbaaUpdateTime")//修改时间 [非空]
	 	,$.getDate("tbaaAddTime")//添加时间 [非空]
	 	,$.getString("tbaaRemark")//备注
	 	,$.getShort("tbaaStatus")//状态 [非空]
	 )
	**/
	public TbAbAffirm(Long tbaaHost,Long tbaaCar,Long tbaaOrder,Long tbaaOut,Long tbaaRet,Long tbaaRealRet,Date tbaaTime,Date tbaaRealTime,Long tbaaConfirm,Long tbaaAdder,Long tbaaEditor,Date tbaaConfirmTime,Date tbaaUpdateTime,Date tbaaAddTime,String tbaaRemark,Short tbaaStatus){
		this.tbaaHost=tbaaHost;
		this.tbaaCar=tbaaCar;
		this.tbaaOrder=tbaaOrder;
		this.tbaaOut=tbaaOut;
		this.tbaaRet=tbaaRet;
		this.tbaaRealRet=tbaaRealRet;
		this.tbaaTime=tbaaTime;
		this.tbaaRealTime=tbaaRealTime;
		this.tbaaConfirm=tbaaConfirm;
		this.tbaaAdder=tbaaAdder;
		this.tbaaEditor=tbaaEditor;
		this.tbaaConfirmTime=tbaaConfirmTime;
		this.tbaaUpdateTime=tbaaUpdateTime;
		this.tbaaAddTime=tbaaAddTime;
		this.tbaaRemark=tbaaRemark;
		this.tbaaStatus=tbaaStatus;
	}
	
	//设置非空字段
	public TbAbAffirm setNotNull(Long tbaaHost,Long tbaaCar,Long tbaaAdder,Long tbaaEditor,Date tbaaUpdateTime,Date tbaaAddTime,Short tbaaStatus){
		this.tbaaHost=tbaaHost;
		this.tbaaCar=tbaaCar;
		this.tbaaAdder=tbaaAdder;
		this.tbaaEditor=tbaaEditor;
		this.tbaaUpdateTime=tbaaUpdateTime;
		this.tbaaAddTime=tbaaAddTime;
		this.tbaaStatus=tbaaStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbAbAffirm tbaaId(Long tbaaId){
		this.tbaaId = tbaaId;
		this.setSeted(F.tbaaId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbAbAffirm tbaaHost(Long tbaaHost){
		this.tbaaHost = tbaaHost;
		this.setSeted(F.tbaaHost);
		return this;
	}
	/** 所属车辆 [非空] [CsCar]      **/
	public TbAbAffirm tbaaCar(Long tbaaCar){
		this.tbaaCar = tbaaCar;
		this.setSeted(F.tbaaCar);
		return this;
	}
	/** 所属订单  [CsOrder]      **/
	public TbAbAffirm tbaaOrder(Long tbaaOrder){
		this.tbaaOrder = tbaaOrder;
		this.setSeted(F.tbaaOrder);
		return this;
	}
	/** 借出网点  [CsOutlets]      **/
	public TbAbAffirm tbaaOut(Long tbaaOut){
		this.tbaaOut = tbaaOut;
		this.setSeted(F.tbaaOut);
		return this;
	}
	/** 预计还入网点  [CsOutlets]      **/
	public TbAbAffirm tbaaRet(Long tbaaRet){
		this.tbaaRet = tbaaRet;
		this.setSeted(F.tbaaRet);
		return this;
	}
	/** 实际还入网点  [CsOutlets]      **/
	public TbAbAffirm tbaaRealRet(Long tbaaRealRet){
		this.tbaaRealRet = tbaaRealRet;
		this.setSeted(F.tbaaRealRet);
		return this;
	}
	/** 预计还入时间        **/
	public TbAbAffirm tbaaTime(Date tbaaTime){
		this.tbaaTime = tbaaTime;
		this.setSeted(F.tbaaTime);
		return this;
	}
	/** 实际还入时间        **/
	public TbAbAffirm tbaaRealTime(Date tbaaRealTime){
		this.tbaaRealTime = tbaaRealTime;
		this.setSeted(F.tbaaRealTime);
		return this;
	}
	/** 确认人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbAbAffirm tbaaConfirm(Long tbaaConfirm){
		this.tbaaConfirm = tbaaConfirm;
		this.setSeted(F.tbaaConfirm);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbAbAffirm tbaaAdder(Long tbaaAdder){
		this.tbaaAdder = tbaaAdder;
		this.setSeted(F.tbaaAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbAbAffirm tbaaEditor(Long tbaaEditor){
		this.tbaaEditor = tbaaEditor;
		this.setSeted(F.tbaaEditor);
		return this;
	}
	/** 确认时间        **/
	public TbAbAffirm tbaaConfirmTime(Date tbaaConfirmTime){
		this.tbaaConfirmTime = tbaaConfirmTime;
		this.setSeted(F.tbaaConfirmTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbAbAffirm tbaaUpdateTime(Date tbaaUpdateTime){
		this.tbaaUpdateTime = tbaaUpdateTime;
		this.setSeted(F.tbaaUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbAbAffirm tbaaAddTime(Date tbaaAddTime){
		this.tbaaAddTime = tbaaAddTime;
		this.setSeted(F.tbaaAddTime);
		return this;
	}
	/** 备注        **/
	public TbAbAffirm tbaaRemark(String tbaaRemark){
		this.tbaaRemark = tbaaRemark;
		this.setSeted(F.tbaaRemark);
		return this;
	}
	/** 状态 [非空]   0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认   **/
	public TbAbAffirm tbaaStatus(Short tbaaStatus){
		this.tbaaStatus = tbaaStatus;
		this.setSeted(F.tbaaStatus);
		return this;
	}
	
	
	//克隆对象
	public TbAbAffirm clone(){
		TbAbAffirm clone = new TbAbAffirm();
		clone.tbaaHost=this.tbaaHost;
		clone.tbaaCar=this.tbaaCar;
		clone.tbaaAdder=this.tbaaAdder;
		clone.tbaaEditor=this.tbaaEditor;
		clone.tbaaUpdateTime=this.tbaaUpdateTime;
		clone.tbaaAddTime=this.tbaaAddTime;
		clone.tbaaStatus=this.tbaaStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取异借确认
	 * @param id
	 * @return
	 */
	public static @api TbAbAffirm get(Long id){		
		return getTbAbAffirmById(id);
	}
	/**
	 * 获取所有异借确认
	 * @return
	 */
	public static @api List<TbAbAffirm> list(Map params,Integer size){
		return getTbAbAffirmList(params,size);
	}
	/**
	 * 获取异借确认分页
	 * @return
	 */
	public static @api Page<TbAbAffirm> page(int page,int size,Map params){
		return getTbAbAffirmPage(page, size , params);
	}
	/**
	 * 根据查询条件取异借确认
	 * @param params
	 * @return
	 */
	public static @api TbAbAffirm Get(Map params){
		return getTbAbAffirm(params);
	}
	/**
	 * 获取异借确认数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbAbAffirmCount(params);
	}
	/**
	 * 获取异借确认数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbAbAffirmEval(eval,params);
	}
	
	/**
	 * 根据ID取异借确认
	 * @param id
	 * @return
	 */
	public static @api TbAbAffirm getTbAbAffirmById(Long id){		
		TbAbAffirm tbAbAffirm = (TbAbAffirm) $.GetRequest("TbAbAffirm$"+id);
		if(tbAbAffirm!=null)
			return tbAbAffirm;
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");		
		return tbAbAffirmService.getTbAbAffirmById(id);
	}
	
	
	/**
	 * 根据ID取异借确认的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbAbAffirm.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbAbAffirm tbAbAffirm = get(id);
		if(tbAbAffirm!=null){
			String strValue = tbAbAffirm.getTbaaId$();
			if(!"TbaaId".equals("TbaaId"))
				strValue+="("+tbAbAffirm.getTbaaId$()+")";
			if(!"TbaaId".equals("TbaaStatus"))
				strValue+="("+tbAbAffirm.getTbaaStatus$()+")";
			MemCache.setValue(TbAbAffirm.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbaaId$();
		if(!"TbaaId".equals("TbaaId"))
			keyValue+="("+this.getTbaaId$()+")";
		if(!"TbaaId".equals("TbaaStatus"))
			keyValue+="("+this.getTbaaStatus$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有异借确认
	 * @return
	 */
	public static @api List<TbAbAffirm> getTbAbAffirmList(Map params,Integer size){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		return tbAbAffirmService.getTbAbAffirmList(params, size);
	}
	
	/**
	 * 获取异借确认分页
	 * @return
	 */
	public static @api Page<TbAbAffirm> getTbAbAffirmPage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		return tbAbAffirmService.getTbAbAffirmPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取异借确认
	 * @param params
	 * @return
	 */
	public static @api TbAbAffirm getTbAbAffirm(Map params){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		return tbAbAffirmService.getTbAbAffirm(params);
	}
	
	/**
	 * 获取异借确认数
	 * @return
	 */
	public static @api Long getTbAbAffirmCount(Map params){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		return tbAbAffirmService.getTbAbAffirmCount(params);
	}
		
		
	/**
	 * 获取异借确认自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbAbAffirmEval(String eval,Map params){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		return tbAbAffirmService.getTbAbAffirmEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbAbAffirm(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		tbAbAffirmService.updateTbAbAffirmByConfirm(set, where);
	}
	
	
	/**
	 * 保存异借确认对象
	 * @param params
	 * @return
	 */
	public TbAbAffirm save(){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		if(this.getTbaaId()!=null)
			tbAbAffirmService.updateTbAbAffirm(this);
		else
			return tbAbAffirmService.saveTbAbAffirm(this);
		return this;
	}
	
	
	/**
	 * 更新异借确认对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		tbAbAffirmService.updateTbAbAffirm$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		if($.equals($.config("logic_delete"),"true"))
			tbAbAffirmService.removeTbAbAffirmById(this.getTbaaId());
		else
			tbAbAffirmService.deleteTbAbAffirmById(this.getTbaaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbAbAffirmService tbAbAffirmService = $.GetSpringBean("tbAbAffirmService");
		return tbAbAffirmService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbaaId(){
		return this.tbaaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbaaId$(){
		String strValue="";
		 strValue=$.str(this.getTbaaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbaaId(Long tbaaId){
		this.tbaaId = tbaaId;
		this.setSeted(F.tbaaId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbaaHost(){
		return this.tbaaHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbaaHost$(){
		String strValue="";
		if(this.getTbaaHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbaaHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbaaHost(Long tbaaHost){
		this.tbaaHost = tbaaHost;
		this.setSeted(F.tbaaHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbaaHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbaaHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbaaHost()!=null){
 			srvHost = SrvHost.get(this.getTbaaHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbaaHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属车辆**********************************/	
	/**
	* 所属车辆 [非空] [CsCar]     
	**/
	public Long getTbaaCar(){
		return this.tbaaCar;
	}
	/**
	* 获取所属车辆格式化(toString)
	**/
	public String getTbaaCar$(){
		String strValue="";
		if(this.getTbaaCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getTbaaCar()));
		}			
	 	 return strValue;
	}
	/**
	* 所属车辆 [非空] [CsCar]     
	**/
	public void setTbaaCar(Long tbaaCar){
		this.tbaaCar = tbaaCar;
		this.setSeted(F.tbaaCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$tbaaCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getTbaaCar());
 		if(csCar!=null)
			return csCar;
		if(this.getTbaaCar()!=null){
 			csCar = CsCar.get(this.getTbaaCar());
 		}
 		$.SetRequest("CsCar$"+this.getTbaaCar(), csCar);
	 	return csCar;
	}
	/*******************************所属订单**********************************/	
	/**
	* 所属订单  [CsOrder]     
	**/
	public Long getTbaaOrder(){
		return this.tbaaOrder;
	}
	/**
	* 获取所属订单格式化(toString)
	**/
	public String getTbaaOrder$(){
		String strValue="";
		if(this.getTbaaOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getTbaaOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属订单  [CsOrder]     
	**/
	public void setTbaaOrder(Long tbaaOrder){
		this.tbaaOrder = tbaaOrder;
		this.setSeted(F.tbaaOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$tbaaOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getTbaaOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getTbaaOrder()!=null){
 			csOrder = CsOrder.get(this.getTbaaOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getTbaaOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************借出网点**********************************/	
	/**
	* 借出网点  [CsOutlets]     
	**/
	public Long getTbaaOut(){
		return this.tbaaOut;
	}
	/**
	* 获取借出网点格式化(toString)
	**/
	public String getTbaaOut$(){
		String strValue="";
		if(this.getTbaaOut()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getTbaaOut()));
		}			
	 	 return strValue;
	}
	/**
	* 借出网点  [CsOutlets]     
	**/
	public void setTbaaOut(Long tbaaOut){
		this.tbaaOut = tbaaOut;
		this.setSeted(F.tbaaOut);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$tbaaOut(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getTbaaOut());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getTbaaOut()!=null){
 			csOutlets = CsOutlets.get(this.getTbaaOut());
 		}
 		$.SetRequest("CsOutlets$"+this.getTbaaOut(), csOutlets);
	 	return csOutlets;
	}
	/*******************************预计还入网点**********************************/	
	/**
	* 预计还入网点  [CsOutlets]     
	**/
	public Long getTbaaRet(){
		return this.tbaaRet;
	}
	/**
	* 获取预计还入网点格式化(toString)
	**/
	public String getTbaaRet$(){
		String strValue="";
		if(this.getTbaaRet()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getTbaaRet()));
		}			
	 	 return strValue;
	}
	/**
	* 预计还入网点  [CsOutlets]     
	**/
	public void setTbaaRet(Long tbaaRet){
		this.tbaaRet = tbaaRet;
		this.setSeted(F.tbaaRet);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$tbaaRet(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getTbaaRet());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getTbaaRet()!=null){
 			csOutlets = CsOutlets.get(this.getTbaaRet());
 		}
 		$.SetRequest("CsOutlets$"+this.getTbaaRet(), csOutlets);
	 	return csOutlets;
	}
	/*******************************实际还入网点**********************************/	
	/**
	* 实际还入网点  [CsOutlets]     
	**/
	public Long getTbaaRealRet(){
		return this.tbaaRealRet;
	}
	/**
	* 获取实际还入网点格式化(toString)
	**/
	public String getTbaaRealRet$(){
		String strValue="";
		if(this.getTbaaRealRet()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getTbaaRealRet()));
		}			
	 	 return strValue;
	}
	/**
	* 实际还入网点  [CsOutlets]     
	**/
	public void setTbaaRealRet(Long tbaaRealRet){
		this.tbaaRealRet = tbaaRealRet;
		this.setSeted(F.tbaaRealRet);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$tbaaRealRet(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getTbaaRealRet());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getTbaaRealRet()!=null){
 			csOutlets = CsOutlets.get(this.getTbaaRealRet());
 		}
 		$.SetRequest("CsOutlets$"+this.getTbaaRealRet(), csOutlets);
	 	return csOutlets;
	}
	/*******************************预计还入时间**********************************/	
	/**
	* 预计还入时间       
	**/
	public Date getTbaaTime(){
		return this.tbaaTime;
	}
	/**
	* 获取预计还入时间格式化(toString)
	**/
	public String getTbaaTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbaaTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 预计还入时间       
	**/
	public void setTbaaTime(Date tbaaTime){
		this.tbaaTime = tbaaTime;
		this.setSeted(F.tbaaTime);
	}
	/*******************************实际还入时间**********************************/	
	/**
	* 实际还入时间       
	**/
	public Date getTbaaRealTime(){
		return this.tbaaRealTime;
	}
	/**
	* 获取实际还入时间格式化(toString)
	**/
	public String getTbaaRealTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbaaRealTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 实际还入时间       
	**/
	public void setTbaaRealTime(Date tbaaRealTime){
		this.tbaaRealTime = tbaaRealTime;
		this.setSeted(F.tbaaRealTime);
	}
	/*******************************确认人**********************************/	
	/**
	* 确认人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbaaConfirm(){
		return this.tbaaConfirm;
	}
	/**
	* 获取确认人格式化(toString)
	**/
	public String getTbaaConfirm$(){
		String strValue="";
		if(this.getTbaaConfirm()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbaaConfirm()));
		}			
	 	 return strValue;
	}
	/**
	* 确认人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbaaConfirm(Long tbaaConfirm){
		this.tbaaConfirm = tbaaConfirm;
		this.setSeted(F.tbaaConfirm);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbaaConfirm(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbaaConfirm());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbaaConfirm()!=null){
 			srvUser = SrvUser.get(this.getTbaaConfirm());
 		}
 		$.SetRequest("SrvUser$"+this.getTbaaConfirm(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbaaAdder(){
		return this.tbaaAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getTbaaAdder$(){
		String strValue="";
		if(this.getTbaaAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbaaAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbaaAdder(Long tbaaAdder){
		this.tbaaAdder = tbaaAdder;
		this.setSeted(F.tbaaAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbaaAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbaaAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbaaAdder()!=null){
 			srvUser = SrvUser.get(this.getTbaaAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getTbaaAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbaaEditor(){
		return this.tbaaEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getTbaaEditor$(){
		String strValue="";
		if(this.getTbaaEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbaaEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbaaEditor(Long tbaaEditor){
		this.tbaaEditor = tbaaEditor;
		this.setSeted(F.tbaaEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbaaEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbaaEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbaaEditor()!=null){
 			srvUser = SrvUser.get(this.getTbaaEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbaaEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************确认时间**********************************/	
	/**
	* 确认时间       
	**/
	public Date getTbaaConfirmTime(){
		return this.tbaaConfirmTime;
	}
	/**
	* 获取确认时间格式化(toString)
	**/
	public String getTbaaConfirmTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbaaConfirmTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 确认时间       
	**/
	public void setTbaaConfirmTime(Date tbaaConfirmTime){
		this.tbaaConfirmTime = tbaaConfirmTime;
		this.setSeted(F.tbaaConfirmTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbaaUpdateTime(){
		return this.tbaaUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbaaUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbaaUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setTbaaUpdateTime(Date tbaaUpdateTime){
		this.tbaaUpdateTime = tbaaUpdateTime;
		this.setSeted(F.tbaaUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbaaAddTime(){
		return this.tbaaAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbaaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbaaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setTbaaAddTime(Date tbaaAddTime){
		this.tbaaAddTime = tbaaAddTime;
		this.setSeted(F.tbaaAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getTbaaRemark(){
		return this.tbaaRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getTbaaRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbaaRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setTbaaRemark(String tbaaRemark){
		this.tbaaRemark = tbaaRemark;
		this.setSeted(F.tbaaRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认  
	**/
	public Short getTbaaStatus(){
		return this.tbaaStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbaaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbaaStatus()),"0"))
			strValue=$.str("待确认");		 
		 if($.equals($.str(this.getTbaaStatus()),"1"))
			strValue=$.str("未确认");		 
		 if($.equals($.str(this.getTbaaStatus()),"2"))
			strValue=$.str("已确认");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认  
	**/
	public void setTbaaStatus(Short tbaaStatus){
		this.tbaaStatus = tbaaStatus;
		this.setSeted(F.tbaaStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbAbAffirm.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbAbAffirm.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbAbAffirm.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbAbAffirm.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbAbAffirm.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbAbAffirm.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbAbAffirm.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbAbAffirm.this);
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
		public M tbaaId(Object tbaaId){this.put("tbaaId", tbaaId);return this;};
	 	/** and tbaa_id is null */
 		public M tbaaIdNull(){if(this.get("tbaaIdNot")==null)this.put("tbaaIdNot", "");this.put("tbaaId", null);return this;};
 		/** not .... */
 		public M tbaaIdNot(){this.put("tbaaIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbaaHost(Object tbaaHost){this.put("tbaaHost", tbaaHost);return this;};
	 	/** and tbaa_host is null */
 		public M tbaaHostNull(){if(this.get("tbaaHostNot")==null)this.put("tbaaHostNot", "");this.put("tbaaHost", null);return this;};
 		/** not .... */
 		public M tbaaHostNot(){this.put("tbaaHostNot", "not");return this;};
		/** 所属车辆 [非空] [CsCar]      **/
		public M tbaaCar(Object tbaaCar){this.put("tbaaCar", tbaaCar);return this;};
	 	/** and tbaa_car is null */
 		public M tbaaCarNull(){if(this.get("tbaaCarNot")==null)this.put("tbaaCarNot", "");this.put("tbaaCar", null);return this;};
 		/** not .... */
 		public M tbaaCarNot(){this.put("tbaaCarNot", "not");return this;};
		public M tbaaCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("tbaaCar$on", value);
			return this;
		};	
		/** 所属订单  [CsOrder]      **/
		public M tbaaOrder(Object tbaaOrder){this.put("tbaaOrder", tbaaOrder);return this;};
	 	/** and tbaa_order is null */
 		public M tbaaOrderNull(){if(this.get("tbaaOrderNot")==null)this.put("tbaaOrderNot", "");this.put("tbaaOrder", null);return this;};
 		/** not .... */
 		public M tbaaOrderNot(){this.put("tbaaOrderNot", "not");return this;};
		public M tbaaOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("tbaaOrder$on", value);
			return this;
		};	
		/** 借出网点  [CsOutlets]      **/
		public M tbaaOut(Object tbaaOut){this.put("tbaaOut", tbaaOut);return this;};
	 	/** and tbaa_out is null */
 		public M tbaaOutNull(){if(this.get("tbaaOutNot")==null)this.put("tbaaOutNot", "");this.put("tbaaOut", null);return this;};
 		/** not .... */
 		public M tbaaOutNot(){this.put("tbaaOutNot", "not");return this;};
		public M tbaaOut$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("tbaaOut$on", value);
			return this;
		};	
		/** 预计还入网点  [CsOutlets]      **/
		public M tbaaRet(Object tbaaRet){this.put("tbaaRet", tbaaRet);return this;};
	 	/** and tbaa_ret is null */
 		public M tbaaRetNull(){if(this.get("tbaaRetNot")==null)this.put("tbaaRetNot", "");this.put("tbaaRet", null);return this;};
 		/** not .... */
 		public M tbaaRetNot(){this.put("tbaaRetNot", "not");return this;};
		public M tbaaRet$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("tbaaRet$on", value);
			return this;
		};	
		/** 实际还入网点  [CsOutlets]      **/
		public M tbaaRealRet(Object tbaaRealRet){this.put("tbaaRealRet", tbaaRealRet);return this;};
	 	/** and tbaa_real_ret is null */
 		public M tbaaRealRetNull(){if(this.get("tbaaRealRetNot")==null)this.put("tbaaRealRetNot", "");this.put("tbaaRealRet", null);return this;};
 		/** not .... */
 		public M tbaaRealRetNot(){this.put("tbaaRealRetNot", "not");return this;};
		public M tbaaRealRet$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("tbaaRealRet$on", value);
			return this;
		};	
		/** 预计还入时间        **/
		public M tbaaTime(Object tbaaTime){this.put("tbaaTime", tbaaTime);return this;};
	 	/** and tbaa_time is null */
 		public M tbaaTimeNull(){if(this.get("tbaaTimeNot")==null)this.put("tbaaTimeNot", "");this.put("tbaaTime", null);return this;};
 		/** not .... */
 		public M tbaaTimeNot(){this.put("tbaaTimeNot", "not");return this;};
 		/** and tbaa_time >= ? */
 		public M tbaaTimeStart(Object start){this.put("tbaaTimeStart", start);return this;};			
 		/** and tbaa_time <= ? */
 		public M tbaaTimeEnd(Object end){this.put("tbaaTimeEnd", end);return this;};
		/** 实际还入时间        **/
		public M tbaaRealTime(Object tbaaRealTime){this.put("tbaaRealTime", tbaaRealTime);return this;};
	 	/** and tbaa_real_time is null */
 		public M tbaaRealTimeNull(){if(this.get("tbaaRealTimeNot")==null)this.put("tbaaRealTimeNot", "");this.put("tbaaRealTime", null);return this;};
 		/** not .... */
 		public M tbaaRealTimeNot(){this.put("tbaaRealTimeNot", "not");return this;};
 		/** and tbaa_real_time >= ? */
 		public M tbaaRealTimeStart(Object start){this.put("tbaaRealTimeStart", start);return this;};			
 		/** and tbaa_real_time <= ? */
 		public M tbaaRealTimeEnd(Object end){this.put("tbaaRealTimeEnd", end);return this;};
		/** 确认人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbaaConfirm(Object tbaaConfirm){this.put("tbaaConfirm", tbaaConfirm);return this;};
	 	/** and tbaa_confirm is null */
 		public M tbaaConfirmNull(){if(this.get("tbaaConfirmNot")==null)this.put("tbaaConfirmNot", "");this.put("tbaaConfirm", null);return this;};
 		/** not .... */
 		public M tbaaConfirmNot(){this.put("tbaaConfirmNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbaaAdder(Object tbaaAdder){this.put("tbaaAdder", tbaaAdder);return this;};
	 	/** and tbaa_adder is null */
 		public M tbaaAdderNull(){if(this.get("tbaaAdderNot")==null)this.put("tbaaAdderNot", "");this.put("tbaaAdder", null);return this;};
 		/** not .... */
 		public M tbaaAdderNot(){this.put("tbaaAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbaaEditor(Object tbaaEditor){this.put("tbaaEditor", tbaaEditor);return this;};
	 	/** and tbaa_editor is null */
 		public M tbaaEditorNull(){if(this.get("tbaaEditorNot")==null)this.put("tbaaEditorNot", "");this.put("tbaaEditor", null);return this;};
 		/** not .... */
 		public M tbaaEditorNot(){this.put("tbaaEditorNot", "not");return this;};
		/** 确认时间        **/
		public M tbaaConfirmTime(Object tbaaConfirmTime){this.put("tbaaConfirmTime", tbaaConfirmTime);return this;};
	 	/** and tbaa_confirm_time is null */
 		public M tbaaConfirmTimeNull(){if(this.get("tbaaConfirmTimeNot")==null)this.put("tbaaConfirmTimeNot", "");this.put("tbaaConfirmTime", null);return this;};
 		/** not .... */
 		public M tbaaConfirmTimeNot(){this.put("tbaaConfirmTimeNot", "not");return this;};
 		/** and tbaa_confirm_time >= ? */
 		public M tbaaConfirmTimeStart(Object start){this.put("tbaaConfirmTimeStart", start);return this;};			
 		/** and tbaa_confirm_time <= ? */
 		public M tbaaConfirmTimeEnd(Object end){this.put("tbaaConfirmTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M tbaaUpdateTime(Object tbaaUpdateTime){this.put("tbaaUpdateTime", tbaaUpdateTime);return this;};
	 	/** and tbaa_update_time is null */
 		public M tbaaUpdateTimeNull(){if(this.get("tbaaUpdateTimeNot")==null)this.put("tbaaUpdateTimeNot", "");this.put("tbaaUpdateTime", null);return this;};
 		/** not .... */
 		public M tbaaUpdateTimeNot(){this.put("tbaaUpdateTimeNot", "not");return this;};
 		/** and tbaa_update_time >= ? */
 		public M tbaaUpdateTimeStart(Object start){this.put("tbaaUpdateTimeStart", start);return this;};			
 		/** and tbaa_update_time <= ? */
 		public M tbaaUpdateTimeEnd(Object end){this.put("tbaaUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbaaAddTime(Object tbaaAddTime){this.put("tbaaAddTime", tbaaAddTime);return this;};
	 	/** and tbaa_add_time is null */
 		public M tbaaAddTimeNull(){if(this.get("tbaaAddTimeNot")==null)this.put("tbaaAddTimeNot", "");this.put("tbaaAddTime", null);return this;};
 		/** not .... */
 		public M tbaaAddTimeNot(){this.put("tbaaAddTimeNot", "not");return this;};
 		/** and tbaa_add_time >= ? */
 		public M tbaaAddTimeStart(Object start){this.put("tbaaAddTimeStart", start);return this;};			
 		/** and tbaa_add_time <= ? */
 		public M tbaaAddTimeEnd(Object end){this.put("tbaaAddTimeEnd", end);return this;};
		/** 备注        **/
		public M tbaaRemark(Object tbaaRemark){this.put("tbaaRemark", tbaaRemark);return this;};
	 	/** and tbaa_remark is null */
 		public M tbaaRemarkNull(){if(this.get("tbaaRemarkNot")==null)this.put("tbaaRemarkNot", "");this.put("tbaaRemark", null);return this;};
 		/** not .... */
 		public M tbaaRemarkNot(){this.put("tbaaRemarkNot", "not");return this;};
		/** 状态 [非空]   0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认   **/
		public M tbaaStatus(Object tbaaStatus){this.put("tbaaStatus", tbaaStatus);return this;};
	 	/** and tbaa_status is null */
 		public M tbaaStatusNull(){if(this.get("tbaaStatusNot")==null)this.put("tbaaStatusNot", "");this.put("tbaaStatus", null);return this;};
 		/** not .... */
 		public M tbaaStatusNot(){this.put("tbaaStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有异借确认 **/
		public @api List<TbAbAffirm> list(Integer size){
			return getTbAbAffirmList(this,size);
		}
		/** 获取异借确认分页 **/
		public @api Page<TbAbAffirm> page(int page,int size){
			return getTbAbAffirmPage(page, size , this);
		}
		/** 根据查询条件取异借确认 **/
		public @api TbAbAffirm get(){
			return getTbAbAffirm(this);
		}
		/** 获取异借确认数 **/
		public @api Long count(){
			return getTbAbAffirmCount(this);
		}
		/** 获取异借确认表达式 **/
		public @api <T> T eval(String strEval){
			return getTbAbAffirmEval(strEval,this);
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
			updateTbAbAffirm(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbaaId="tbaaId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbaaHost="tbaaHost";
		/** 所属车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String tbaaCar="tbaaCar";
		/** 所属订单  [CsOrder]      **/
		public final static @type(Long.class)  String tbaaOrder="tbaaOrder";
		/** 借出网点  [CsOutlets]      **/
		public final static @type(Long.class)  String tbaaOut="tbaaOut";
		/** 预计还入网点  [CsOutlets]      **/
		public final static @type(Long.class)  String tbaaRet="tbaaRet";
		/** 实际还入网点  [CsOutlets]      **/
		public final static @type(Long.class)  String tbaaRealRet="tbaaRealRet";
		/** 预计还入时间        **/
		public final static @type(Date.class)  String tbaaTime="tbaaTime";
	 	/** and tbaa_time >= ? */
 		public final static @type(Date.class) String tbaaTimeStart="tbaaTimeStart";
 		/** and tbaa_time <= ? */
 		public final static @type(Date.class) String tbaaTimeEnd="tbaaTimeEnd";
		/** 实际还入时间        **/
		public final static @type(Date.class)  String tbaaRealTime="tbaaRealTime";
	 	/** and tbaa_real_time >= ? */
 		public final static @type(Date.class) String tbaaRealTimeStart="tbaaRealTimeStart";
 		/** and tbaa_real_time <= ? */
 		public final static @type(Date.class) String tbaaRealTimeEnd="tbaaRealTimeEnd";
		/** 确认人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbaaConfirm="tbaaConfirm";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbaaAdder="tbaaAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbaaEditor="tbaaEditor";
		/** 确认时间        **/
		public final static @type(Date.class)  String tbaaConfirmTime="tbaaConfirmTime";
	 	/** and tbaa_confirm_time >= ? */
 		public final static @type(Date.class) String tbaaConfirmTimeStart="tbaaConfirmTimeStart";
 		/** and tbaa_confirm_time <= ? */
 		public final static @type(Date.class) String tbaaConfirmTimeEnd="tbaaConfirmTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbaaUpdateTime="tbaaUpdateTime";
	 	/** and tbaa_update_time >= ? */
 		public final static @type(Date.class) String tbaaUpdateTimeStart="tbaaUpdateTimeStart";
 		/** and tbaa_update_time <= ? */
 		public final static @type(Date.class) String tbaaUpdateTimeEnd="tbaaUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbaaAddTime="tbaaAddTime";
	 	/** and tbaa_add_time >= ? */
 		public final static @type(Date.class) String tbaaAddTimeStart="tbaaAddTimeStart";
 		/** and tbaa_add_time <= ? */
 		public final static @type(Date.class) String tbaaAddTimeEnd="tbaaAddTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String tbaaRemark="tbaaRemark";
		/** 状态 [非空]   0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认   **/
		public final static @type(Short.class)  String tbaaStatus="tbaaStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbaaId="tbaa_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbaaHost="tbaa_host";
		/** 所属车辆 [非空] [CsCar]      **/
		public final static String tbaaCar="tbaa_car";
		/** 所属订单  [CsOrder]      **/
		public final static String tbaaOrder="tbaa_order";
		/** 借出网点  [CsOutlets]      **/
		public final static String tbaaOut="tbaa_out";
		/** 预计还入网点  [CsOutlets]      **/
		public final static String tbaaRet="tbaa_ret";
		/** 实际还入网点  [CsOutlets]      **/
		public final static String tbaaRealRet="tbaa_real_ret";
		/** 预计还入时间        **/
		public final static String tbaaTime="tbaa_time";
		/** 实际还入时间        **/
		public final static String tbaaRealTime="tbaa_real_time";
		/** 确认人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbaaConfirm="tbaa_confirm";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbaaAdder="tbaa_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbaaEditor="tbaa_editor";
		/** 确认时间        **/
		public final static String tbaaConfirmTime="tbaa_confirm_time";
		/** 修改时间 [非空]       **/
		public final static String tbaaUpdateTime="tbaa_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbaaAddTime="tbaa_add_time";
		/** 备注        **/
		public final static String tbaaRemark="tbaa_remark";
		/** 状态 [非空]   0:待确认 1:未确认 2:已确认  未确认表示订单已完成未确认   **/
		public final static String tbaaStatus="tbaa_status";
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
				$.Set(name,TbAbAffirm.getTbAbAffirm(params));
			else
				$.Set(name,TbAbAffirm.getTbAbAffirmList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取异借确认数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbAbAffirm) $.GetRequest("TbAbAffirm$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbAbAffirm.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbAbAffirm.getTbAbAffirm(params);
			else
				value = TbAbAffirm.getTbAbAffirmList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbAbAffirm.Get($.add(TbAbAffirm.F.tbaaId,param));
		else if(!$.empty(param.toString()))
			value = TbAbAffirm.get(Long.valueOf(param.toString()));
		$.SetRequest("TbAbAffirm$"+param.hashCode(), value);
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
	public void mergeSet(TbAbAffirm old){
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