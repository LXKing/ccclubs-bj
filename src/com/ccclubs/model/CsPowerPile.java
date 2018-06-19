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

@namespace("object/powerpile")
public @caption("充电桩") @table("cs_power_pile") class CsPowerPile implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cspp_id")   @primary  @note("  ") Long csppId;// 主键 非空     
	private @caption("城市") @column("cspp_host")    @relate("$csppHost") @RelateClass(SrvHost.class)  @note("  ") Long csppHost;// 非空     
 	private SrvHost $csppHost;//关联对象[运营城市]
	private @caption("标识码") @column("cspp_no")    @note("  ") String csppNo;// 非空     
	private @caption("序号") @column("cspp_index")    @note("  标识所在网点的序号") Integer csppIndex;//  标识所在网点的序号   
	private @caption("所在网点") @column("cspp_outlets")    @relate("$csppOutlets") @RelateClass(CsOutlets.class)  @note(" {csppHost}:host  ") Long csppOutlets;// 非空 {csppHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
 	private CsOutlets $csppOutlets;//关联对象[网点]
	private @caption("正在充电") @column("cspp_charge")    @note("  ") Boolean csppCharge;//     
	private @caption("负责人") @column("cspp_person")    @note("  ") String csppPerson;//     
	private @caption("联系电话") @column("cspp_tel")    @note("  ") String csppTel;//     
	private @caption("电桩图片") @column("cspp_image")  @hidden   @note("  ") String csppImage;//     
	private @caption("位置信息") @column("cspp_location")    @note("  ") String csppLocation;// 非空     
	private @caption("备注信息") @column("cspp_remark")  @hidden   @note("  ") String csppRemark;//     
	private @caption("修改时间") @column("cspp_update_time")    @note("  ") Date csppUpdateTime;// 非空     
	private @caption("添加时间") @column("cspp_add_time")    @note("  ") Date csppAddTime;// 非空     
	private @caption("状态") @column("cspp_status")    @note(" 1:正常 2:预设 3:已坏 0:无效  ") Short csppStatus;// 非空 1:正常 2:预设 3:已坏 0:无效     
	
	//默认构造函数
	public CsPowerPile(){
	
	}
	
	//主键构造函数
	public CsPowerPile(Long id){
		this.csppId = id;
	}
	
	/**所有字段构造函数 CsPowerPile(csppHost,csppNo,csppIndex,csppOutlets,csppCharge,csppPerson,csppTel,csppImage,csppLocation,csppRemark,csppUpdateTime,csppAddTime,csppStatus)
	 CsPowerPile(
	 	$.getLong("csppHost")//城市 [非空]
	 	,$.getString("csppNo")//标识码 [非空]
	 	,$.getInteger("csppIndex")//序号
	 	,$.getLong("csppOutlets")//所在网点 [非空]
	 	,$.getBoolean("csppCharge")//正在充电
	 	,$.getString("csppPerson")//负责人
	 	,$.getString("csppTel")//联系电话
	 	,$.getString("csppImage")//电桩图片
	 	,$.getString("csppLocation")//位置信息 [非空]
	 	,$.getString("csppRemark")//备注信息
	 	,$.getDate("csppUpdateTime")//修改时间 [非空]
	 	,$.getDate("csppAddTime")//添加时间 [非空]
	 	,$.getShort("csppStatus")//状态 [非空]
	 )
	**/
	public CsPowerPile(Long csppHost,String csppNo,Integer csppIndex,Long csppOutlets,Boolean csppCharge,String csppPerson,String csppTel,String csppImage,String csppLocation,String csppRemark,Date csppUpdateTime,Date csppAddTime,Short csppStatus){
		this.csppHost=csppHost;
		this.csppNo=csppNo;
		this.csppIndex=csppIndex;
		this.csppOutlets=csppOutlets;
		this.csppCharge=csppCharge;
		this.csppPerson=csppPerson;
		this.csppTel=csppTel;
		this.csppImage=csppImage;
		this.csppLocation=csppLocation;
		this.csppRemark=csppRemark;
		this.csppUpdateTime=csppUpdateTime;
		this.csppAddTime=csppAddTime;
		this.csppStatus=csppStatus;
	}
	
	//设置非空字段
	public CsPowerPile setNotNull(Long csppHost,String csppNo,Long csppOutlets,String csppLocation,Date csppUpdateTime,Date csppAddTime,Short csppStatus){
		this.csppHost=csppHost;
		this.csppNo=csppNo;
		this.csppOutlets=csppOutlets;
		this.csppLocation=csppLocation;
		this.csppUpdateTime=csppUpdateTime;
		this.csppAddTime=csppAddTime;
		this.csppStatus=csppStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPowerPile csppId(Long csppId){
		this.csppId = csppId;
		this.setSeted(F.csppId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsPowerPile csppHost(Long csppHost){
		this.csppHost = csppHost;
		this.setSeted(F.csppHost);
		return this;
	}
	/** 标识码 [非空]       **/
	public CsPowerPile csppNo(String csppNo){
		this.csppNo = csppNo;
		this.setSeted(F.csppNo);
		return this;
	}
	/** 序号     标识所在网点的序号   **/
	public CsPowerPile csppIndex(Integer csppIndex){
		this.csppIndex = csppIndex;
		this.setSeted(F.csppIndex);
		return this;
	}
	/** 所在网点 [非空] [CsOutlets]  {csppHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
	public CsPowerPile csppOutlets(Long csppOutlets){
		this.csppOutlets = csppOutlets;
		this.setSeted(F.csppOutlets);
		return this;
	}
	/** 正在充电        **/
	public CsPowerPile csppCharge(Boolean csppCharge){
		this.csppCharge = csppCharge;
		this.setSeted(F.csppCharge);
		return this;
	}
	/** 负责人        **/
	public CsPowerPile csppPerson(String csppPerson){
		this.csppPerson = csppPerson;
		this.setSeted(F.csppPerson);
		return this;
	}
	/** 联系电话        **/
	public CsPowerPile csppTel(String csppTel){
		this.csppTel = csppTel;
		this.setSeted(F.csppTel);
		return this;
	}
	/** 电桩图片        **/
	public CsPowerPile csppImage(String csppImage){
		this.csppImage = csppImage;
		this.setSeted(F.csppImage);
		return this;
	}
	/** 位置信息 [非空]       **/
	public CsPowerPile csppLocation(String csppLocation){
		this.csppLocation = csppLocation;
		this.setSeted(F.csppLocation);
		return this;
	}
	/** 备注信息        **/
	public CsPowerPile csppRemark(String csppRemark){
		this.csppRemark = csppRemark;
		this.setSeted(F.csppRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsPowerPile csppUpdateTime(Date csppUpdateTime){
		this.csppUpdateTime = csppUpdateTime;
		this.setSeted(F.csppUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsPowerPile csppAddTime(Date csppAddTime){
		this.csppAddTime = csppAddTime;
		this.setSeted(F.csppAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 2:预设 3:已坏 0:无效     **/
	public CsPowerPile csppStatus(Short csppStatus){
		this.csppStatus = csppStatus;
		this.setSeted(F.csppStatus);
		return this;
	}
	
	
	//克隆对象
	public CsPowerPile clone(){
		CsPowerPile clone = new CsPowerPile();
		clone.csppHost=this.csppHost;
		clone.csppNo=this.csppNo;
		clone.csppOutlets=this.csppOutlets;
		clone.csppLocation=this.csppLocation;
		clone.csppUpdateTime=this.csppUpdateTime;
		clone.csppAddTime=this.csppAddTime;
		clone.csppStatus=this.csppStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public static @api CsPowerPile get(Long id){		
		return getCsPowerPileById(id);
	}
	/**
	 * 获取所有充电桩
	 * @return
	 */
	public static @api List<CsPowerPile> list(Map params,Integer size){
		return getCsPowerPileList(params,size);
	}
	/**
	 * 获取充电桩分页
	 * @return
	 */
	public static @api Page<CsPowerPile> page(int page,int size,Map params){
		return getCsPowerPilePage(page, size , params);
	}
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public static @api CsPowerPile Get(Map params){
		return getCsPowerPile(params);
	}
	/**
	 * 获取充电桩数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPowerPileCount(params);
	}
	/**
	 * 获取充电桩数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPowerPileEval(eval,params);
	}
	
	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public static @api CsPowerPile getCsPowerPileById(Long id){		
		CsPowerPile csPowerPile = (CsPowerPile) $.GetRequest("CsPowerPile$"+id);
		if(csPowerPile!=null)
			return csPowerPile;
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");		
		return csPowerPileService.getCsPowerPileById(id);
	}
	
	
	/**
	 * 根据ID取充电桩的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPowerPile.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPowerPile csPowerPile = get(id);
		if(csPowerPile!=null){
			String strValue = csPowerPile.getCsppNo$();
			if(!"CsppNo".equals("CsppNo"))
				strValue+="("+csPowerPile.getCsppNo$()+")";
			MemCache.setValue(CsPowerPile.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsppNo$();
		if(!"CsppNo".equals("CsppNo"))
			keyValue+="("+this.getCsppNo$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有充电桩
	 * @return
	 */
	public static @api List<CsPowerPile> getCsPowerPileList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		return csPowerPileService.getCsPowerPileList(params, size);
	}
	
	/**
	 * 获取充电桩分页
	 * @return
	 */
	public static @api Page<CsPowerPile> getCsPowerPilePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		return csPowerPileService.getCsPowerPilePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public static @api CsPowerPile getCsPowerPile(Map params){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		return csPowerPileService.getCsPowerPile(params);
	}
	
	/**
	 * 获取充电桩数
	 * @return
	 */
	public static @api Long getCsPowerPileCount(Map params){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		return csPowerPileService.getCsPowerPileCount(params);
	}
		
		
	/**
	 * 获取充电桩自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPowerPileEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		return csPowerPileService.getCsPowerPileEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPowerPile(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		csPowerPileService.updateCsPowerPileByConfirm(set, where);
	}
	
	
	/**
	 * 保存充电桩对象
	 * @param params
	 * @return
	 */
	public CsPowerPile save(){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		if(this.getCsppId()!=null)
			csPowerPileService.updateCsPowerPile(this);
		else
			return csPowerPileService.saveCsPowerPile(this);
		return this;
	}
	
	
	/**
	 * 更新充电桩对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		csPowerPileService.updateCsPowerPile$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		if($.equals($.config("logic_delete"),"true"))
			csPowerPileService.removeCsPowerPileById(this.getCsppId());
		else
			csPowerPileService.deleteCsPowerPileById(this.getCsppId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		return csPowerPileService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsppId(){
		return this.csppId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsppId$(){
		String strValue="";
		 strValue=$.str(this.getCsppId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsppId(Long csppId){
		this.csppId = csppId;
		this.setSeted(F.csppId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsppHost(){
		return this.csppHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsppHost$(){
		String strValue="";
		if(this.getCsppHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsppHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsppHost(Long csppHost){
		this.csppHost = csppHost;
		this.setSeted(F.csppHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csppHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsppHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsppHost()!=null){
 			srvHost = SrvHost.get(this.getCsppHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsppHost(), srvHost);
	 	return srvHost;
	}
	/*******************************标识码**********************************/	
	/**
	* 标识码 [非空]      
	**/
	public String getCsppNo(){
		return this.csppNo;
	}
	/**
	* 获取标识码格式化(toString)
	**/
	public String getCsppNo$(){
		String strValue="";
		 strValue=$.str(this.getCsppNo());
	 	 return strValue;
	}
	/**
	* 标识码 [非空]      
	**/
	public void setCsppNo(String csppNo){
		this.csppNo = csppNo;
		this.setSeted(F.csppNo);
	}
	/*******************************序号**********************************/	
	/**
	* 序号     标识所在网点的序号  
	**/
	public Integer getCsppIndex(){
		return this.csppIndex;
	}
	/**
	* 获取序号格式化(toString)
	**/
	public String getCsppIndex$(){
		String strValue="";
		 strValue=$.str(this.getCsppIndex());
	 	 return strValue;
	}
	/**
	* 序号     标识所在网点的序号  
	**/
	public void setCsppIndex(Integer csppIndex){
		this.csppIndex = csppIndex;
		this.setSeted(F.csppIndex);
	}
	/*******************************所在网点**********************************/	
	/**
	* 所在网点 [非空] [CsOutlets]  {csppHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public Long getCsppOutlets(){
		return this.csppOutlets;
	}
	/**
	* 获取所在网点格式化(toString)
	**/
	public String getCsppOutlets$(){
		String strValue="";
		if(this.getCsppOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsppOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 所在网点 [非空] [CsOutlets]  {csppHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public void setCsppOutlets(Long csppOutlets){
		this.csppOutlets = csppOutlets;
		this.setSeted(F.csppOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csppOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsppOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsppOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCsppOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsppOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************正在充电**********************************/	
	/**
	* 正在充电       
	**/
	public Boolean getCsppCharge(){
		return this.csppCharge;
	}
	/**
	* 获取正在充电格式化(toString)
	**/
	public String getCsppCharge$(){
		String strValue="";
		  strValue=$.str((this.getCsppCharge()!=null && this.getCsppCharge())?"是":"否");			
	 	 return strValue;
	}
	/**
	* 正在充电       
	**/
	public void setCsppCharge(Boolean csppCharge){
		this.csppCharge = csppCharge;
		this.setSeted(F.csppCharge);
	}
	/*******************************负责人**********************************/	
	/**
	* 负责人       
	**/
	public String getCsppPerson(){
		return this.csppPerson;
	}
	/**
	* 获取负责人格式化(toString)
	**/
	public String getCsppPerson$(){
		String strValue="";
		 strValue=$.str(this.getCsppPerson());
	 	 return strValue;
	}
	/**
	* 负责人       
	**/
	public void setCsppPerson(String csppPerson){
		this.csppPerson = csppPerson;
		this.setSeted(F.csppPerson);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话       
	**/
	public String getCsppTel(){
		return this.csppTel;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getCsppTel$(){
		String strValue="";
		 strValue=$.str(this.getCsppTel());
	 	 return strValue;
	}
	/**
	* 联系电话       
	**/
	public void setCsppTel(String csppTel){
		this.csppTel = csppTel;
		this.setSeted(F.csppTel);
	}
	/*******************************电桩图片**********************************/	
	/**
	* 电桩图片       
	**/
	public String getCsppImage(){
		return this.csppImage;
	}
	/**
	* 获取电桩图片格式化(toString)
	**/
	public String getCsppImage$(){
		String strValue="";
		 strValue=$.str(this.getCsppImage());
	 	 return strValue;
	}
	/**
	* 电桩图片       
	**/
	public void setCsppImage(String csppImage){
		this.csppImage = csppImage;
		this.setSeted(F.csppImage);
	}
	/*******************************位置信息**********************************/	
	/**
	* 位置信息 [非空]      
	**/
	public String getCsppLocation(){
		return this.csppLocation;
	}
	/**
	* 获取位置信息格式化(toString)
	**/
	public String getCsppLocation$(){
		String strValue="";
		 strValue=$.str(this.getCsppLocation());
	 	 return strValue;
	}
	/**
	* 位置信息 [非空]      
	**/
	public void setCsppLocation(String csppLocation){
		this.csppLocation = csppLocation;
		this.setSeted(F.csppLocation);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsppRemark(){
		return this.csppRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsppRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsppRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsppRemark(String csppRemark){
		this.csppRemark = csppRemark;
		this.setSeted(F.csppRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsppUpdateTime(){
		return this.csppUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsppUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsppUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsppUpdateTime(Date csppUpdateTime){
		this.csppUpdateTime = csppUpdateTime;
		this.setSeted(F.csppUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsppAddTime(){
		return this.csppAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsppAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsppAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsppAddTime(Date csppAddTime){
		this.csppAddTime = csppAddTime;
		this.setSeted(F.csppAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 2:预设 3:已坏 0:无效    
	**/
	public Short getCsppStatus(){
		return this.csppStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsppStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsppStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsppStatus()),"2"))
			strValue=$.str("预设");		 
		 if($.equals($.str(this.getCsppStatus()),"3"))
			strValue=$.str("已坏");		 
		 if($.equals($.str(this.getCsppStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 2:预设 3:已坏 0:无效    
	**/
	public void setCsppStatus(Short csppStatus){
		this.csppStatus = csppStatus;
		this.setSeted(F.csppStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPowerPile.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPowerPile.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPowerPile.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPowerPile.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPowerPile.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPowerPile.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPowerPile.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPowerPile.this);
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
		public M csppId(Object csppId){this.put("csppId", csppId);return this;};
	 	/** and cspp_id is null */
 		public M csppIdNull(){if(this.get("csppIdNot")==null)this.put("csppIdNot", "");this.put("csppId", null);return this;};
 		/** not .... */
 		public M csppIdNot(){this.put("csppIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csppHost(Object csppHost){this.put("csppHost", csppHost);return this;};
	 	/** and cspp_host is null */
 		public M csppHostNull(){if(this.get("csppHostNot")==null)this.put("csppHostNot", "");this.put("csppHost", null);return this;};
 		/** not .... */
 		public M csppHostNot(){this.put("csppHostNot", "not");return this;};
		/** 标识码 [非空]       **/
		public M csppNo(Object csppNo){this.put("csppNo", csppNo);return this;};
	 	/** and cspp_no is null */
 		public M csppNoNull(){if(this.get("csppNoNot")==null)this.put("csppNoNot", "");this.put("csppNo", null);return this;};
 		/** not .... */
 		public M csppNoNot(){this.put("csppNoNot", "not");return this;};
		/** 序号     标识所在网点的序号   **/
		public M csppIndex(Object csppIndex){this.put("csppIndex", csppIndex);return this;};
	 	/** and cspp_index is null */
 		public M csppIndexNull(){if(this.get("csppIndexNot")==null)this.put("csppIndexNot", "");this.put("csppIndex", null);return this;};
 		/** not .... */
 		public M csppIndexNot(){this.put("csppIndexNot", "not");return this;};
		/** and cspp_index >= ? */
		public M csppIndexMin(Object min){this.put("csppIndexMin", min);return this;};
		/** and cspp_index <= ? */
		public M csppIndexMax(Object max){this.put("csppIndexMax", max);return this;};
		/** 所在网点 [非空] [CsOutlets]  {csppHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public M csppOutlets(Object csppOutlets){this.put("csppOutlets", csppOutlets);return this;};
	 	/** and cspp_outlets is null */
 		public M csppOutletsNull(){if(this.get("csppOutletsNot")==null)this.put("csppOutletsNot", "");this.put("csppOutlets", null);return this;};
 		/** not .... */
 		public M csppOutletsNot(){this.put("csppOutletsNot", "not");return this;};
		public M csppOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csppOutlets$on", value);
			return this;
		};	
		/** 正在充电        **/
		public M csppCharge(Object csppCharge){this.put("csppCharge", csppCharge);return this;};
	 	/** and cspp_charge is null */
 		public M csppChargeNull(){if(this.get("csppChargeNot")==null)this.put("csppChargeNot", "");this.put("csppCharge", null);return this;};
 		/** not .... */
 		public M csppChargeNot(){this.put("csppChargeNot", "not");return this;};
		/** 负责人        **/
		public M csppPerson(Object csppPerson){this.put("csppPerson", csppPerson);return this;};
	 	/** and cspp_person is null */
 		public M csppPersonNull(){if(this.get("csppPersonNot")==null)this.put("csppPersonNot", "");this.put("csppPerson", null);return this;};
 		/** not .... */
 		public M csppPersonNot(){this.put("csppPersonNot", "not");return this;};
		/** 联系电话        **/
		public M csppTel(Object csppTel){this.put("csppTel", csppTel);return this;};
	 	/** and cspp_tel is null */
 		public M csppTelNull(){if(this.get("csppTelNot")==null)this.put("csppTelNot", "");this.put("csppTel", null);return this;};
 		/** not .... */
 		public M csppTelNot(){this.put("csppTelNot", "not");return this;};
		/** 电桩图片        **/
		public M csppImage(Object csppImage){this.put("csppImage", csppImage);return this;};
	 	/** and cspp_image is null */
 		public M csppImageNull(){if(this.get("csppImageNot")==null)this.put("csppImageNot", "");this.put("csppImage", null);return this;};
 		/** not .... */
 		public M csppImageNot(){this.put("csppImageNot", "not");return this;};
		/** 位置信息 [非空]       **/
		public M csppLocation(Object csppLocation){this.put("csppLocation", csppLocation);return this;};
	 	/** and cspp_location is null */
 		public M csppLocationNull(){if(this.get("csppLocationNot")==null)this.put("csppLocationNot", "");this.put("csppLocation", null);return this;};
 		/** not .... */
 		public M csppLocationNot(){this.put("csppLocationNot", "not");return this;};
		/** 备注信息        **/
		public M csppRemark(Object csppRemark){this.put("csppRemark", csppRemark);return this;};
	 	/** and cspp_remark is null */
 		public M csppRemarkNull(){if(this.get("csppRemarkNot")==null)this.put("csppRemarkNot", "");this.put("csppRemark", null);return this;};
 		/** not .... */
 		public M csppRemarkNot(){this.put("csppRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csppUpdateTime(Object csppUpdateTime){this.put("csppUpdateTime", csppUpdateTime);return this;};
	 	/** and cspp_update_time is null */
 		public M csppUpdateTimeNull(){if(this.get("csppUpdateTimeNot")==null)this.put("csppUpdateTimeNot", "");this.put("csppUpdateTime", null);return this;};
 		/** not .... */
 		public M csppUpdateTimeNot(){this.put("csppUpdateTimeNot", "not");return this;};
 		/** and cspp_update_time >= ? */
 		public M csppUpdateTimeStart(Object start){this.put("csppUpdateTimeStart", start);return this;};			
 		/** and cspp_update_time <= ? */
 		public M csppUpdateTimeEnd(Object end){this.put("csppUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csppAddTime(Object csppAddTime){this.put("csppAddTime", csppAddTime);return this;};
	 	/** and cspp_add_time is null */
 		public M csppAddTimeNull(){if(this.get("csppAddTimeNot")==null)this.put("csppAddTimeNot", "");this.put("csppAddTime", null);return this;};
 		/** not .... */
 		public M csppAddTimeNot(){this.put("csppAddTimeNot", "not");return this;};
 		/** and cspp_add_time >= ? */
 		public M csppAddTimeStart(Object start){this.put("csppAddTimeStart", start);return this;};			
 		/** and cspp_add_time <= ? */
 		public M csppAddTimeEnd(Object end){this.put("csppAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 2:预设 3:已坏 0:无效     **/
		public M csppStatus(Object csppStatus){this.put("csppStatus", csppStatus);return this;};
	 	/** and cspp_status is null */
 		public M csppStatusNull(){if(this.get("csppStatusNot")==null)this.put("csppStatusNot", "");this.put("csppStatus", null);return this;};
 		/** not .... */
 		public M csppStatusNot(){this.put("csppStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有充电桩 **/
		public @api List<CsPowerPile> list(Integer size){
			return getCsPowerPileList(this,size);
		}
		/** 获取充电桩分页 **/
		public @api Page<CsPowerPile> page(int page,int size){
			return getCsPowerPilePage(page, size , this);
		}
		/** 根据查询条件取充电桩 **/
		public @api CsPowerPile get(){
			return getCsPowerPile(this);
		}
		/** 获取充电桩数 **/
		public @api Long count(){
			return getCsPowerPileCount(this);
		}
		/** 获取充电桩表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPowerPileEval(strEval,this);
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
			updateCsPowerPile(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csppId="csppId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csppHost="csppHost";
		/** 标识码 [非空]       **/
		public final static @type(String.class) @like String csppNo="csppNo";
		/** 序号     标识所在网点的序号   **/
		public final static @type(Integer.class)  String csppIndex="csppIndex";
		/** and cspp_index >= ? */
		public final static @type(Integer.class) String csppIndexMin="csppIndexMin";
		/** and cspp_index <= ? */
		public final static @type(Integer.class) String csppIndexMax="csppIndexMax";
		/** 所在网点 [非空] [CsOutlets]  {csppHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static @type(Long.class)  String csppOutlets="csppOutlets";
		/** 正在充电        **/
		public final static @type(Boolean.class)  String csppCharge="csppCharge";
		/** 负责人        **/
		public final static @type(String.class) @like String csppPerson="csppPerson";
		/** 联系电话        **/
		public final static @type(String.class) @like String csppTel="csppTel";
		/** 电桩图片        **/
		public final static @type(String.class)  String csppImage="csppImage";
		/** 位置信息 [非空]       **/
		public final static @type(String.class) @like String csppLocation="csppLocation";
		/** 备注信息        **/
		public final static @type(String.class) @like String csppRemark="csppRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csppUpdateTime="csppUpdateTime";
	 	/** and cspp_update_time >= ? */
 		public final static @type(Date.class) String csppUpdateTimeStart="csppUpdateTimeStart";
 		/** and cspp_update_time <= ? */
 		public final static @type(Date.class) String csppUpdateTimeEnd="csppUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csppAddTime="csppAddTime";
	 	/** and cspp_add_time >= ? */
 		public final static @type(Date.class) String csppAddTimeStart="csppAddTimeStart";
 		/** and cspp_add_time <= ? */
 		public final static @type(Date.class) String csppAddTimeEnd="csppAddTimeEnd";
		/** 状态 [非空]   1:正常 2:预设 3:已坏 0:无效     **/
		public final static @type(Short.class)  String csppStatus="csppStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csppId="cspp_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csppHost="cspp_host";
		/** 标识码 [非空]       **/
		public final static String csppNo="cspp_no";
		/** 序号     标识所在网点的序号   **/
		public final static String csppIndex="cspp_index";
		/** 所在网点 [非空] [CsOutlets]  {csppHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static String csppOutlets="cspp_outlets";
		/** 正在充电        **/
		public final static String csppCharge="cspp_charge";
		/** 负责人        **/
		public final static String csppPerson="cspp_person";
		/** 联系电话        **/
		public final static String csppTel="cspp_tel";
		/** 电桩图片        **/
		public final static String csppImage="cspp_image";
		/** 位置信息 [非空]       **/
		public final static String csppLocation="cspp_location";
		/** 备注信息        **/
		public final static String csppRemark="cspp_remark";
		/** 修改时间 [非空]       **/
		public final static String csppUpdateTime="cspp_update_time";
		/** 添加时间 [非空]       **/
		public final static String csppAddTime="cspp_add_time";
		/** 状态 [非空]   1:正常 2:预设 3:已坏 0:无效     **/
		public final static String csppStatus="cspp_status";
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
				$.Set(name,CsPowerPile.getCsPowerPile(params));
			else
				$.Set(name,CsPowerPile.getCsPowerPileList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取充电桩数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPowerPile) $.GetRequest("CsPowerPile$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPowerPile.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPowerPile.getCsPowerPile(params);
			else
				value = CsPowerPile.getCsPowerPileList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPowerPile.Get($.add(CsPowerPile.F.csppId,param));
		else if(!$.empty(param.toString()))
			value = CsPowerPile.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPowerPile$"+param.hashCode(), value);
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
	public void mergeSet(CsPowerPile old){
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