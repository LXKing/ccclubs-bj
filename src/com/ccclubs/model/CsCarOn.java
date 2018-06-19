package com.ccclubs.model;

import java.lang.reflect.Field;
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

@namespace("object/car/caron")
public @caption("车辆上线记录") @table("cs_car_on") class CsCarOn implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csco_id")   @primary   @note("  ") Long cscoId;
	private @caption("城市") @column("csco_host")    @relate("$cscoHost") @RelateClass(SrvHost.class)  @note("  ") Long cscoHost;
	private SrvHost $cscoHost;//关联对象[城市]
	private @caption("所属车辆") @column("csco_car")    @relate("$cscoCar") @RelateClass(CsCar.class)  @note("  ") Long cscoCar;
	private CsCar $cscoCar;//关联对象[所属车辆]
	private @caption("上线操作人") @column("csco_on_user")    @relate("$cscoOnUser") @RelateClass(SrvUser.class)  @note("  ") Long cscoOnUser;
	private SrvUser $cscoOnUser;//关联对象[上线操作人]
	private @caption("下线操作人") @column("csco_off_user")    @relate("$cscoOffUser") @RelateClass(SrvUser.class)  @note("  ") Long cscoOffUser;
	private SrvUser $cscoOffUser;//关联对象[下线操作人]
	private @caption("上线时间") @column("csco_on_time")      @note("  ") Date cscoOnTime;
	private @caption("下线时间") @column("csco_off_time")      @note("  ") Date cscoOffTime;
	private @caption("运营时长") @column("csco_long")      @note("  ") Long cscoLong;
	private @caption("下线备注") @column("csco_remark")      @note("  ") String cscoRemark;
	private @caption("状态") @column("csco_status")      @note(" 0:上线中 1:已完成  ") Short cscoStatus;
	private @caption("上线备注") @column("csco_on_remark")      @note("") String cscoOnRemark;
	private @caption("下线标签") @column("csco_property")    @relate("$cscoProperty") @RelateClass(SrvProperty.class)  @note("") Long cscoProperty;
	private SrvProperty $cscoProperty;//关联对象[下线标签]
	private @caption("图片") @column("csco_image")      @note("") String cscoImage;
	
	//默认构造函数
	public CsCarOn(){
	
	}
	
	//主键构造函数
	public CsCarOn(Long id){
		this.cscoId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCarOn(Long cscoHost,Long cscoCar,Long cscoOnUser,Long cscoOffUser,Date cscoOnTime,Date cscoOffTime,Long cscoLong,String cscoRemark,Short cscoStatus,String cscoOnRemark,Long cscoProperty,String cscoImage){
		this.cscoHost=cscoHost;
		this.cscoCar=cscoCar;
		this.cscoOnUser=cscoOnUser;
		this.cscoOffUser=cscoOffUser;
		this.cscoOnTime=cscoOnTime;
		this.cscoOffTime=cscoOffTime;
		this.cscoLong=cscoLong;
		this.cscoRemark=cscoRemark;
		this.cscoStatus=cscoStatus;
		this.cscoOnRemark=cscoOnRemark;
		this.cscoProperty=cscoProperty;
		this.cscoImage=cscoImage;
	}
	
	public CsCarOn(Long cscoHost,Long cscoCar,Long cscoOnUser,Long cscoOffUser,Date cscoOnTime,Date cscoOffTime,Long cscoLong,String cscoRemark,Short cscoStatus,String cscoOnRemark){
		this.cscoHost=cscoHost;
		this.cscoCar=cscoCar;
		this.cscoOnUser=cscoOnUser;
		this.cscoOffUser=cscoOffUser;
		this.cscoOnTime=cscoOnTime;
		this.cscoOffTime=cscoOffTime;
		this.cscoLong=cscoLong;
		this.cscoRemark=cscoRemark;
		this.cscoStatus=cscoStatus;
		this.cscoOnRemark = cscoOnRemark;
	}
	
	//设置非空字段
	public CsCarOn setNotNull(){
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsCarOn cscoId(Long cscoId){
		this.cscoId = cscoId;
		this.setSeted(F.cscoId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsCarOn cscoHost(Long cscoHost){
		this.cscoHost = cscoHost;
		this.setSeted(F.cscoHost);
		return this;
	}
	/** 所属车辆 [非空]       **/
	public CsCarOn cscoCar(Long cscoCar){
		this.cscoCar = cscoCar;
		this.setSeted(F.cscoCar);
		return this;
	}
	/** 上线操作人 [非空]       **/
	public CsCarOn cscoOnUser(Long cscoOnUser){
		this.cscoOnUser = cscoOnUser;
		this.setSeted(F.cscoOnUser);
		return this;
	}
	/** 下线操作人 [非空]       **/
	public CsCarOn cscoOffUser(Long cscoOffUser){
		this.cscoOffUser = cscoOffUser;
		this.setSeted(F.cscoOffUser);
		return this;
	}
	/** 上线时间 [非空]       **/
	public CsCarOn cscoOnTime(Date cscoOnTime){
		this.cscoOnTime = cscoOnTime;
		this.setSeted(F.cscoOnTime);
		return this;
	}
	/** 下线时间 [非空]       **/
	public CsCarOn cscoOffTime(Date cscoOffTime){
		this.cscoOffTime = cscoOffTime;
		this.setSeted(F.cscoOffTime);
		return this;
	}
	/** 运营时长 [非空]       **/
	public CsCarOn cscoLong(Long cscoLong){
		this.cscoLong = cscoLong;
		this.setSeted(F.cscoLong);
		return this;
	}
	/** 下线备注 [非空]       **/
	public CsCarOn cscoRemark(String cscoRemark){
		this.cscoRemark = cscoRemark;
		this.setSeted(F.cscoRemark);
		return this;
	}
	/** 状态 [非空]       **/
	public CsCarOn cscoStatus(Short cscoStatus){
		this.cscoStatus = cscoStatus;
		this.setSeted(F.cscoStatus);
		return this;
	}
	/** 上线备注 [非空]       **/
	public CsCarOn cscoOnRemark(String cscoOnRemark){
		this.cscoOnRemark = cscoOnRemark;
		this.setSeted(F.cscoOnRemark);
		return this;
	}
	/** 下线标签 [非空]       **/
	public CsCarOn cscoProperty(Long cscoProperty){
		this.cscoProperty = cscoProperty;
		this.setSeted(F.cscoProperty);
		return this;
	}
	/** 图片 [非空]       **/
	public CsCarOn cscoImage(String cscoImage){
		this.cscoImage = cscoImage;
		this.setSeted(F.cscoImage);
		return this;
	}
	
	//克隆对象
	public CsCarOn clone(){
		CsCarOn clone = new CsCarOn();
		clone.cscoId=this.cscoId;
		clone.cscoHost=this.cscoHost;
		clone.cscoCar=this.cscoCar;
		clone.cscoOnUser=this.cscoOnUser;
		clone.cscoOffUser=this.cscoOffUser;
		clone.cscoOnTime=this.cscoOnTime;
		clone.cscoOffTime=this.cscoOffTime;
		clone.cscoLong=this.cscoLong;
		clone.cscoRemark=this.cscoRemark;
		clone.cscoStatus=this.cscoStatus;
		clone.cscoOnRemark=this.cscoOnRemark;
		clone.cscoProperty=this.cscoProperty;
		clone.cscoImage=this.cscoImage;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆上线记录
	 * @param id
	 * @return
	 */
	public static @api CsCarOn get(Long id){		
		return getCsCarOnById(id);
	}
	/**
	 * 获取所有车辆上线记录
	 * @return
	 */
	public static @api List<CsCarOn> list(Map params,Integer size){
		return getCsCarOnList(params,size);
	}
	/**
	 * 获取车辆上线记录分页
	 * @return
	 */
	public static @api Page<CsCarOn> page(int page,int size,Map params){
		return getCsCarOnPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆上线记录
	 * @param params
	 * @return
	 */
	public static @api CsCarOn Get(Map params){
		return getCsCarOn(params);
	}
	/**
	 * 获取车辆上线记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCarOnCount(params);
	}
	/**
	 * 获取车辆上线记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCarOnEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆上线记录
	 * @param id
	 * @return
	 */
	public static @api CsCarOn getCsCarOnById(Long id){		
		CsCarOn csCarOn = (CsCarOn) $.GetRequest("CsCarOn$"+id);
		if(csCarOn!=null)
			return csCarOn;
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");		
		return csCarOnService.getCsCarOnById(id);
	}
	
	
	/**
	 * 根据ID取车辆上线记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCarOn.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCarOn csCarOn = get(id);
		if(csCarOn!=null){
			String strValue = csCarOn.getCscoCar$();
			if(!"CscoCar".equals("CscoCar"))
				strValue+="("+csCarOn.getCscoCar$()+")";
			MemCache.setValue(CsCarOn.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscoCar$();
		if(!"CscoCar".equals("CscoCar"))
			keyValue+="("+this.getCscoCar$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆上线记录
	 * @return
	 */
	public static @api List<CsCarOn> getCsCarOnList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		return csCarOnService.getCsCarOnList(params, size);
	}
	
	/**
	 * 获取车辆上线记录分页
	 * @return
	 */
	public static @api Page<CsCarOn> getCsCarOnPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		return csCarOnService.getCsCarOnPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆上线记录
	 * @param params
	 * @return
	 */
	public static @api CsCarOn getCsCarOn(Map params){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		return csCarOnService.getCsCarOn(params);
	}
	
	/**
	 * 获取车辆上线记录数
	 * @return
	 */
	public static @api Long getCsCarOnCount(Map params){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		return csCarOnService.getCsCarOnCount(params);
	}
		
		
	/**
	 * 获取车辆上线记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCarOnEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		return csCarOnService.getCsCarOnEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCarOn(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		csCarOnService.updateCsCarOnByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆上线记录对象
	 * @param params
	 * @return
	 */
	public CsCarOn save(){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		if(this.getCscoId()!=null)
			csCarOnService.updateCsCarOn(this);
		else
			return csCarOnService.saveCsCarOn(this);
		return this;
	}
	
	
	/**
	 * 更新车辆上线记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		csCarOnService.updateCsCarOn$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		if($.equals($.config("logic_delete"),"true"))
			csCarOnService.removeCsCarOnById(this.getCscoId());
		else
			csCarOnService.deleteCsCarOnById(this.getCscoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		return csCarOnService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscoId(){
		return this.cscoId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscoId$(){
		String strValue="";
		 strValue=$.str(this.getCscoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscoId(Long cscoId){
		this.cscoId = cscoId;
		this.setSeted(F.cscoId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCscoHost(){
		return this.cscoHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscoHost$(){
		String strValue="";
		if(this.getCscoHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscoHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCscoHost(Long cscoHost){
		this.cscoHost = cscoHost;
		this.setSeted(F.cscoHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$cscoHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscoHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscoHost()!=null){
			srvHost = SrvHost.get(this.getCscoHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscoHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属车辆**********************************/	
	/**
	* 所属车辆 [非空]      
	**/
	public Long getCscoCar(){
		return this.cscoCar;
	}
	/**
	* 获取所属车辆格式化(toString)
	**/
	public String getCscoCar$(){
		String strValue="";
		if(this.getCscoCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCscoCar()));
		}			
	 	 return strValue;
	}
	/**
	* 所属车辆 [非空]     
	**/
	public void setCscoCar(Long cscoCar){
		this.cscoCar = cscoCar;
		this.setSeted(F.cscoCar);
	}
	/**
	* 获取关联对象[所属车辆]
	**/	 			
 	public CsCar get$cscoCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCscoCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCscoCar()!=null){
			csCar = CsCar.get(this.getCscoCar());
 		}
 		$.SetRequest("CsCar$"+this.getCscoCar(), csCar);
	 	return csCar;
	}
	/*******************************上线操作人**********************************/	
	/**
	* 上线操作人 [非空]      
	**/
	public Long getCscoOnUser(){
		return this.cscoOnUser;
	}
	/**
	* 获取上线操作人格式化(toString)
	**/
	public String getCscoOnUser$(){
		String strValue="";
		if(this.getCscoOnUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscoOnUser()));
		}			
	 	 return strValue;
	}
	/**
	* 上线操作人 [非空]     
	**/
	public void setCscoOnUser(Long cscoOnUser){
		this.cscoOnUser = cscoOnUser;
		this.setSeted(F.cscoOnUser);
	}
	/**
	* 获取关联对象[上线操作人]
	**/	 			
 	public SrvUser get$cscoOnUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscoOnUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscoOnUser()!=null){
			srvUser = SrvUser.get(this.getCscoOnUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCscoOnUser(), srvUser);
	 	return srvUser;
	}
	/*******************************下线操作人**********************************/	
	/**
	* 下线操作人 [非空]      
	**/
	public Long getCscoOffUser(){
		return this.cscoOffUser;
	}
	/**
	* 获取下线操作人格式化(toString)
	**/
	public String getCscoOffUser$(){
		String strValue="";
		if(this.getCscoOffUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscoOffUser()));
		}			
	 	 return strValue;
	}
	/**
	* 下线操作人 [非空]     
	**/
	public void setCscoOffUser(Long cscoOffUser){
		this.cscoOffUser = cscoOffUser;
		this.setSeted(F.cscoOffUser);
	}
	/**
	* 获取关联对象[下线操作人]
	**/	 			
 	public SrvUser get$cscoOffUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscoOffUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscoOffUser()!=null){
			srvUser = SrvUser.get(this.getCscoOffUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCscoOffUser(), srvUser);
	 	return srvUser;
	}
	/*******************************上线时间**********************************/	
	/**
	* 上线时间 [非空]      
	**/
	public Date getCscoOnTime(){
		return this.cscoOnTime;
	}
	/**
	* 获取上线时间格式化(toString)
	**/
	public String getCscoOnTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscoOnTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 上线时间 [非空]     
	**/
	public void setCscoOnTime(Date cscoOnTime){
		this.cscoOnTime = cscoOnTime;
		this.setSeted(F.cscoOnTime);
	}
	/*******************************下线时间**********************************/	
	/**
	* 下线时间 [非空]      
	**/
	public Date getCscoOffTime(){
		return this.cscoOffTime;
	}
	/**
	* 获取下线时间格式化(toString)
	**/
	public String getCscoOffTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscoOffTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 下线时间 [非空]     
	**/
	public void setCscoOffTime(Date cscoOffTime){
		this.cscoOffTime = cscoOffTime;
		this.setSeted(F.cscoOffTime);
	}
	/*******************************运营时长**********************************/	
	/**
	* 运营时长 [非空]      
	**/
	public Long getCscoLong(){
		return this.cscoLong;
	}
	/**
	* 获取运营时长格式化(toString)
	**/
	public String getCscoLong$(){
		String strValue="";
		 strValue=$.str(this.getCscoLong());
	 	 return strValue;
	}
	/**
	* 运营时长 [非空]     
	**/
	public void setCscoLong(Long cscoLong){
		this.cscoLong = cscoLong;
		this.setSeted(F.cscoLong);
	}
	/*******************************下线备注**********************************/	
	/**
	* 下线备注 [非空]      
	**/
	public String getCscoRemark(){
		return this.cscoRemark;
	}
	/**
	* 获取下线备注格式化(toString)
	**/
	public String getCscoRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscoRemark());
	 	 return strValue;
	}
	/**
	* 下线备注 [非空]     
	**/
	public void setCscoRemark(String cscoRemark){
		this.cscoRemark = cscoRemark;
		this.setSeted(F.cscoRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCscoStatus(){
		return this.cscoStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscoStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscoStatus()),"1"))
			 strValue=$.str("已完成");
		 if($.equals($.str(this.getCscoStatus()),"0"))
			 strValue=$.str("上线中");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCscoStatus(Short cscoStatus){
		this.cscoStatus = cscoStatus;
		this.setSeted(F.cscoStatus);
	}
	/*******************************上线备注**********************************/	
	/**
	* 上线备注 [非空]      
	**/
	public String getCscoOnRemark(){
		return this.cscoOnRemark;
	}
	/**
	* 获取上线备注格式化(toString)
	**/
	public String getCscoOnRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscoOnRemark());
	 	 return strValue;
	}
	/**
	* 上线备注 [非空]     
	**/
	public void setCscoOnRemark(String cscoOnRemark){
		this.cscoOnRemark = cscoOnRemark;
		this.setSeted(F.cscoOnRemark);
	}
	/*******************************下线标签**********************************/	
	/**
	* 下线标签 [非空]      
	**/
	public Long getCscoProperty(){
		return this.cscoProperty;
	}
	/**
	* 获取下线标签格式化(toString)
	**/
	public String getCscoProperty$(){
		String strValue="";
		if(this.getCscoProperty()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCscoProperty()));
		}			
	 	 return strValue;
	}
	/**
	* 下线标签 [非空]     
	**/
	public void setCscoProperty(Long cscoProperty){
		this.cscoProperty = cscoProperty;
		this.setSeted(F.cscoProperty);
	}
	/**
	* 获取关联对象[下线标签]
	**/	 			
 	public SrvProperty get$cscoProperty(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCscoProperty());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCscoProperty()!=null){
			srvProperty = SrvProperty.get(this.getCscoProperty());
 		}
 		$.SetRequest("SrvProperty$"+this.getCscoProperty(), srvProperty);
	 	return srvProperty;
	}
	/*******************************图片**********************************/	
	/**
	* 图片 [非空]      
	**/
	public String getCscoImage(){
		return this.cscoImage;
	}
	/**
	* 获取图片格式化(toString)
	**/
	public String getCscoImage$(){
		String strValue="";
		 strValue=$.str(this.getCscoImage());
	 	 return strValue;
	}
	/**
	* 图片 [非空]     
	**/
	public void setCscoImage(String cscoImage){
		this.cscoImage = cscoImage;
		this.setSeted(F.cscoImage);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCarOn.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarOn.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarOn.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCarOn.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCarOn.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarOn.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarOn.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCarOn.this);
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
		public M cscoId(Object cscoId){this.put("cscoId", cscoId);return this;};
		/** and csco_id is null */
		public M cscoIdNull(){if(this.get("cscoIdNot")==null)this.put("cscoIdNot", "");this.put("cscoId", null);return this;};
		/** not .... */
		public M cscoIdNot(){this.put("cscoIdNot", "not");return this;};
		/** and csco_id >= ? */
		public M cscoIdMin(Object min){this.put("cscoIdMin", min);return this;};
		/** and csco_id <= ? */
		public M cscoIdMax(Object max){this.put("cscoIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M cscoHost(Object cscoHost){this.put("cscoHost", cscoHost);return this;};
		/** and csco_host is null */
		public M cscoHostNull(){if(this.get("cscoHostNot")==null)this.put("cscoHostNot", "");this.put("cscoHost", null);return this;};
		/** not .... */
		public M cscoHostNot(){this.put("cscoHostNot", "not");return this;};
		/** and csco_host >= ? */
		public M cscoHostMin(Object min){this.put("cscoHostMin", min);return this;};
		/** and csco_host <= ? */
		public M cscoHostMax(Object max){this.put("cscoHostMax", max);return this;};
		/** 所属车辆 [非空]       **/
		public M cscoCar(Object cscoCar){this.put("cscoCar", cscoCar);return this;};
		/** and csco_car is null */
		public M cscoCarNull(){if(this.get("cscoCarNot")==null)this.put("cscoCarNot", "");this.put("cscoCar", null);return this;};
		/** not .... */
		public M cscoCarNot(){this.put("cscoCarNot", "not");return this;};
		public M cscoCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cscoCar$on", value);
			return this;
		};	
		/** and csco_car >= ? */
		public M cscoCarMin(Object min){this.put("cscoCarMin", min);return this;};
		/** and csco_car <= ? */
		public M cscoCarMax(Object max){this.put("cscoCarMax", max);return this;};
		/** 上线操作人 [非空]       **/
		public M cscoOnUser(Object cscoOnUser){this.put("cscoOnUser", cscoOnUser);return this;};
		/** and csco_on_user is null */
		public M cscoOnUserNull(){if(this.get("cscoOnUserNot")==null)this.put("cscoOnUserNot", "");this.put("cscoOnUser", null);return this;};
		/** not .... */
		public M cscoOnUserNot(){this.put("cscoOnUserNot", "not");return this;};
		public M cscoOnUser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("cscoOnUser$on", value);
			return this;
		};	
		/** and csco_on_user >= ? */
		public M cscoOnUserMin(Object min){this.put("cscoOnUserMin", min);return this;};
		/** and csco_on_user <= ? */
		public M cscoOnUserMax(Object max){this.put("cscoOnUserMax", max);return this;};
		/** 下线操作人 [非空]       **/
		public M cscoOffUser(Object cscoOffUser){this.put("cscoOffUser", cscoOffUser);return this;};
		/** and csco_off_user is null */
		public M cscoOffUserNull(){if(this.get("cscoOffUserNot")==null)this.put("cscoOffUserNot", "");this.put("cscoOffUser", null);return this;};
		/** not .... */
		public M cscoOffUserNot(){this.put("cscoOffUserNot", "not");return this;};
		public M cscoOffUser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("cscoOffUser$on", value);
			return this;
		};	
		/** and csco_off_user >= ? */
		public M cscoOffUserMin(Object min){this.put("cscoOffUserMin", min);return this;};
		/** and csco_off_user <= ? */
		public M cscoOffUserMax(Object max){this.put("cscoOffUserMax", max);return this;};
		/** 上线时间 [非空]       **/
		public M cscoOnTime(Object cscoOnTime){this.put("cscoOnTime", cscoOnTime);return this;};
		/** and csco_on_time is null */
		public M cscoOnTimeNull(){if(this.get("cscoOnTimeNot")==null)this.put("cscoOnTimeNot", "");this.put("cscoOnTime", null);return this;};
		/** not .... */
		public M cscoOnTimeNot(){this.put("cscoOnTimeNot", "not");return this;};
		/** and csco_on_time >= ? */
 		public M cscoOnTimeStart(Object start){this.put("cscoOnTimeStart", start);return this;};			
 		/** and csco_on_time <= ? */
 		public M cscoOnTimeEnd(Object end){this.put("cscoOnTimeEnd", end);return this;};
		/** 下线时间 [非空]       **/
		public M cscoOffTime(Object cscoOffTime){this.put("cscoOffTime", cscoOffTime);return this;};
		/** and csco_off_time is null */
		public M cscoOffTimeNull(){if(this.get("cscoOffTimeNot")==null)this.put("cscoOffTimeNot", "");this.put("cscoOffTime", null);return this;};
		/** not .... */
		public M cscoOffTimeNot(){this.put("cscoOffTimeNot", "not");return this;};
		/** and csco_off_time >= ? */
 		public M cscoOffTimeStart(Object start){this.put("cscoOffTimeStart", start);return this;};			
 		/** and csco_off_time <= ? */
 		public M cscoOffTimeEnd(Object end){this.put("cscoOffTimeEnd", end);return this;};
		/** 运营时长 [非空]       **/
		public M cscoLong(Object cscoLong){this.put("cscoLong", cscoLong);return this;};
		/** and csco_long is null */
		public M cscoLongNull(){if(this.get("cscoLongNot")==null)this.put("cscoLongNot", "");this.put("cscoLong", null);return this;};
		/** not .... */
		public M cscoLongNot(){this.put("cscoLongNot", "not");return this;};
		/** and csco_long >= ? */
		public M cscoLongMin(Object min){this.put("cscoLongMin", min);return this;};
		/** and csco_long <= ? */
		public M cscoLongMax(Object max){this.put("cscoLongMax", max);return this;};
		/** 下线备注 [非空]       **/
		public M cscoRemark(Object cscoRemark){this.put("cscoRemark", cscoRemark);return this;};
		/** and csco_remark is null */
		public M cscoRemarkNull(){if(this.get("cscoRemarkNot")==null)this.put("cscoRemarkNot", "");this.put("cscoRemark", null);return this;};
		/** not .... */
		public M cscoRemarkNot(){this.put("cscoRemarkNot", "not");return this;};
		/** 状态 [非空]       **/
		public M cscoStatus(Object cscoStatus){this.put("cscoStatus", cscoStatus);return this;};
		/** and csco_status is null */
		public M cscoStatusNull(){if(this.get("cscoStatusNot")==null)this.put("cscoStatusNot", "");this.put("cscoStatus", null);return this;};
		/** not .... */
		public M cscoStatusNot(){this.put("cscoStatusNot", "not");return this;};
		/** and csco_status >= ? */
		public M cscoStatusMin(Object min){this.put("cscoStatusMin", min);return this;};
		/** and csco_status <= ? */
		public M cscoStatusMax(Object max){this.put("cscoStatusMax", max);return this;};
		/** 上线备注 [非空]       **/
		public M cscoOnRemark(Object cscoOnRemark){this.put("cscoOnRemark", cscoOnRemark);return this;};
		/** and csco_on_remark is null */
		public M cscoOnRemarkNull(){if(this.get("cscoOnRemarkNot")==null)this.put("cscoOnRemarkNot", "");this.put("cscoOnRemark", null);return this;};
		/** not .... */
		public M cscoOnRemarkNot(){this.put("cscoOnRemarkNot", "not");return this;};
		/** 下线标签 [非空]       **/
		public M cscoProperty(Object cscoProperty){this.put("cscoProperty", cscoProperty);return this;};
		/** and csco_property is null */
		public M cscoPropertyNull(){if(this.get("cscoPropertyNot")==null)this.put("cscoPropertyNot", "");this.put("cscoProperty", null);return this;};
		/** not .... */
		public M cscoPropertyNot(){this.put("cscoPropertyNot", "not");return this;};
		public M cscoProperty$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cscoProperty$on", value);
			return this;
		};	
		/** and csco_property >= ? */
		public M cscoPropertyMin(Object min){this.put("cscoPropertyMin", min);return this;};
		/** and csco_property <= ? */
		public M cscoPropertyMax(Object max){this.put("cscoPropertyMax", max);return this;};
		/** 图片 [非空]       **/
		public M cscoImage(Object cscoImage){this.put("cscoImage", cscoImage);return this;};
		/** and csco_image is null */
		public M cscoImageNull(){if(this.get("cscoImageNot")==null)this.put("cscoImageNot", "");this.put("cscoImage", null);return this;};
		/** not .... */
		public M cscoImageNot(){this.put("cscoImageNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆上线记录 **/
		public @api List<CsCarOn> list(Integer size){
			return getCsCarOnList(this,size);
		}
		/** 获取车辆上线记录分页 **/
		public @api Page<CsCarOn> page(int page,int size){
			return getCsCarOnPage(page, size , this);
		}
		/** 根据查询条件取车辆上线记录 **/
		public @api CsCarOn get(){
			return getCsCarOn(this);
		}
		/** 获取车辆上线记录数 **/
		public @api Long count(){
			return getCsCarOnCount(this);
		}
		/** 获取车辆上线记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCarOnEval(strEval,this);
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
			updateCsCarOn(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscoId="cscoId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String cscoHost="cscoHost";
		/** 所属车辆 [非空]       **/
		public final static @type(Long.class)  String cscoCar="cscoCar";
		/** 上线操作人 [非空]       **/
		public final static @type(Long.class)  String cscoOnUser="cscoOnUser";
		/** 下线操作人 [非空]       **/
		public final static @type(Long.class)  String cscoOffUser="cscoOffUser";
		/** 上线时间 [非空]       **/
		public final static @type(Date.class)  String cscoOnTime="cscoOnTime";
		/** and csco_on_time >= ? */
 		public final static @type(Date.class) String cscoOnTimeStart="cscoOnTimeStart";
 		/** and csco_on_time <= ? */
 		public final static @type(Date.class) String cscoOnTimeEnd="cscoOnTimeEnd";
		/** 下线时间 [非空]       **/
		public final static @type(Date.class)  String cscoOffTime="cscoOffTime";
		/** and csco_off_time >= ? */
 		public final static @type(Date.class) String cscoOffTimeStart="cscoOffTimeStart";
 		/** and csco_off_time <= ? */
 		public final static @type(Date.class) String cscoOffTimeEnd="cscoOffTimeEnd";
		/** 运营时长 [非空]       **/
		public final static @type(Long.class)  String cscoLong="cscoLong";
		/** 下线备注 [非空]       **/
		public final static @type(String.class)  String cscoRemark="cscoRemark";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String cscoStatus="cscoStatus";
		/** 上线备注 [非空]       **/
		public final static @type(String.class)  String cscoOnRemark="cscoOnRemark";
		/** 下线标签 [非空]       **/
		public final static @type(Long.class)  String cscoProperty="cscoProperty";
		/** 图片 [非空]       **/
		public final static @type(String.class)  String cscoImage="cscoImage";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscoId="csco_id";
		/** 城市 [非空]       **/
		public final static String cscoHost="csco_host";
		/** 所属车辆 [非空]       **/
		public final static String cscoCar="csco_car";
		/** 上线操作人 [非空]       **/
		public final static String cscoOnUser="csco_on_user";
		/** 下线操作人 [非空]       **/
		public final static String cscoOffUser="csco_off_user";
		/** 上线时间 [非空]       **/
		public final static String cscoOnTime="csco_on_time";
		/** 下线时间 [非空]       **/
		public final static String cscoOffTime="csco_off_time";
		/** 运营时长 [非空]       **/
		public final static String cscoLong="csco_long";
		/** 下线备注 [非空]       **/
		public final static String cscoRemark="csco_remark";
		/** 状态 [非空]       **/
		public final static String cscoStatus="csco_status";
		/** 上线备注 [非空]       **/
		public final static String cscoOnRemark="csco_on_remark";
		/** 下线标签 [非空]       **/
		public final static String cscoProperty="csco_property";
		/** 图片 [非空]       **/
		public final static String cscoImage="csco_image";
		
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
				$.Set(name,CsCarOn.getCsCarOn(params));
			else
				$.Set(name,CsCarOn.getCsCarOnList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆上线记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCarOn) $.GetRequest("CsCarOn$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCarOn.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCarOn.getCsCarOn(params);
			else
				value = CsCarOn.getCsCarOnList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCarOn.Get($.add(CsCarOn.F.cscoId,param));
		else if(!$.empty(param.toString()))
			value = CsCarOn.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCarOn$"+param.hashCode(), value);
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
	public void mergeSet(CsCarOn old){
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