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

@namespace("permissions/host")
public @caption("运营城市") @table("srv_host") class SrvHost implements java.io.Serializable
{
	private static final long serialVersionUID = 1l;
	private @caption("编号") @column("sh_id")   @primary  @note("  ") Long shId;// 主键 非空     
	private @caption("城市名称") @column("sh_name")    @note("  ") String shName;// 非空     
	private @caption("运营商") @column("sh_oper")    @relate("$shOper") @RelateClass(CsOper.class)  @note("  ") Long shOper;// 非空     
 	private CsOper $shOper;//关联对象[运营商]
	private @caption("标识") @column("sh_flag")    @note("  ") String shFlag;//     
	private @caption("默认计费方式") @column("sh_fee_type")    @relate("$shFeeType") @RelateClass(CsUserType.class)  @note("  ") Long shFeeType;// 非空     
 	private CsUserType $shFeeType;//关联对象[计费方式]
	private @caption("上班时间") @column("sh_on_hour")    @note("  ") Double shOnHour;// 非空     
	private @caption("下班时间") @column("sh_off_hour")    @note("  ") Double shOffHour;// 非空     
	private @caption("坐标位置") @column("sh_location")    @note("  ") String shLocation;//     
	private @caption("运营状态") @column("sh_state")    @note(" 0:下线 1:上线  ") Short shState;// 非空 0:下线 1:上线     
	private @caption("有效状态") @column("sh_status")    @note(" 1:正常 0:无效  ") Short shStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public SrvHost(){
	
	}
	
	//主键构造函数
	public SrvHost(Long id){
		this.shId = id;
	}
	
	/**所有字段构造函数 SrvHost(shName,shOper,shFlag,shFeeType,shOnHour,shOffHour,shLocation,shState,shStatus)
	 SrvHost(
	 	$.getString("shName")//城市名称 [非空]
	 	,$.getLong("shOper")//运营商 [非空]
	 	,$.getString("shFlag")//标识
	 	,$.getLong("shFeeType")//默认计费方式 [非空]
	 	,$.getDouble("shOnHour")//上班时间 [非空]
	 	,$.getDouble("shOffHour")//下班时间 [非空]
	 	,$.getString("shLocation")//坐标位置
	 	,$.getShort("shState")//运营状态 [非空]
	 	,$.getShort("shStatus")//有效状态 [非空]
	 )
	**/
	public SrvHost(String shName,Long shOper,String shFlag,Long shFeeType,Double shOnHour,Double shOffHour,String shLocation,Short shState,Short shStatus){
		this.shName=shName;
		this.shOper=shOper;
		this.shFlag=shFlag;
		this.shFeeType=shFeeType;
		this.shOnHour=shOnHour;
		this.shOffHour=shOffHour;
		this.shLocation=shLocation;
		this.shState=shState;
		this.shStatus=shStatus;
	}
	
	//设置非空字段
	public SrvHost setNotNull(String shName,Long shOper,Long shFeeType,Double shOnHour,Double shOffHour,Short shState,Short shStatus){
		this.shName=shName;
		this.shOper=shOper;
		this.shFeeType=shFeeType;
		this.shOnHour=shOnHour;
		this.shOffHour=shOffHour;
		this.shState=shState;
		this.shStatus=shStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvHost shId(Long shId){
		this.shId = shId;
		this.setSeted(F.shId);
		return this;
	}
	/** 城市名称 [非空]       **/
	public SrvHost shName(String shName){
		this.shName = shName;
		this.setSeted(F.shName);
		return this;
	}
	/** 运营商 [非空] [CsOper]      **/
	public SrvHost shOper(Long shOper){
		this.shOper = shOper;
		this.setSeted(F.shOper);
		return this;
	}
	/** 标识        **/
	public SrvHost shFlag(String shFlag){
		this.shFlag = shFlag;
		this.setSeted(F.shFlag);
		return this;
	}
	/** 默认计费方式 [非空] [CsUserType]      **/
	public SrvHost shFeeType(Long shFeeType){
		this.shFeeType = shFeeType;
		this.setSeted(F.shFeeType);
		return this;
	}
	/** 上班时间 [非空]       **/
	public SrvHost shOnHour(Double shOnHour){
		this.shOnHour = shOnHour;
		this.setSeted(F.shOnHour);
		return this;
	}
	/** 下班时间 [非空]       **/
	public SrvHost shOffHour(Double shOffHour){
		this.shOffHour = shOffHour;
		this.setSeted(F.shOffHour);
		return this;
	}
	/** 坐标位置        **/
	public SrvHost shLocation(String shLocation){
		this.shLocation = shLocation;
		this.setSeted(F.shLocation);
		return this;
	}
	/** 运营状态 [非空]   0:下线 1:上线     **/
	public SrvHost shState(Short shState){
		this.shState = shState;
		this.setSeted(F.shState);
		return this;
	}
	/** 有效状态 [非空]   1:正常 0:无效     **/
	public SrvHost shStatus(Short shStatus){
		this.shStatus = shStatus;
		this.setSeted(F.shStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvHost clone(){
		SrvHost clone = new SrvHost();
		clone.shName=this.shName;
		clone.shOper=this.shOper;
		clone.shFeeType=this.shFeeType;
		clone.shOnHour=this.shOnHour;
		clone.shOffHour=this.shOffHour;
		clone.shState=this.shState;
		clone.shStatus=this.shStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取运营城市
	 * @param id
	 * @return
	 */
	public static @api SrvHost get(Long id){		
		return getSrvHostById(id);
	}
	/**
	 * 获取所有运营城市
	 * @return
	 */
	public static @api List<SrvHost> list(Map params,Integer size){
		return getSrvHostList(params,size);
	}
	/**
	 * 获取运营城市分页
	 * @return
	 */
	public static @api Page<SrvHost> page(int page,int size,Map params){
		return getSrvHostPage(page, size , params);
	}
	/**
	 * 根据查询条件取运营城市
	 * @param params
	 * @return
	 */
	public static @api SrvHost Get(Map params){
		return getSrvHost(params);
	}
	/**
	 * 获取运营城市数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvHostCount(params);
	}
	/**
	 * 获取运营城市数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvHostEval(eval,params);
	}
	
	/**
	 * 根据ID取运营城市
	 * @param id
	 * @return
	 */
	public static @api SrvHost getSrvHostById(Long id){		
		SrvHost srvHost = (SrvHost) $.GetRequest("SrvHost$"+id);
		if(srvHost!=null)
			return srvHost;
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");		
		return srvHostService.getSrvHostById(id);
	}
	
	
	/**
	 * 根据ID取运营城市的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvHost.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvHost srvHost = get(id);
		if(srvHost!=null){
			String strValue = srvHost.getShName$();
			if(!"ShName".equals("ShName"))
				strValue+="("+srvHost.getShName$()+")";
			if(!"ShName".equals("ShOper"))
				strValue+="("+srvHost.getShOper$()+")";
			MemCache.setValue(SrvHost.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getShName$();
		if(!"ShName".equals("ShName"))
			keyValue+="("+this.getShName$()+")";
		if(!"ShName".equals("ShOper"))
			keyValue+="("+this.getShOper$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有运营城市
	 * @return
	 */
	public static @api List<SrvHost> getSrvHostList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		return srvHostService.getSrvHostList(params, size);
	}
	
	/**
	 * 获取运营城市分页
	 * @return
	 */
	public static @api Page<SrvHost> getSrvHostPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		return srvHostService.getSrvHostPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取运营城市
	 * @param params
	 * @return
	 */
	public static @api SrvHost getSrvHost(Map params){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		return srvHostService.getSrvHost(params);
	}
	
	/**
	 * 获取运营城市数
	 * @return
	 */
	public static @api Long getSrvHostCount(Map params){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		return srvHostService.getSrvHostCount(params);
	}
		
		
	/**
	 * 获取运营城市自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvHostEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		return srvHostService.getSrvHostEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvHost(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		srvHostService.updateSrvHostByConfirm(set, where);
	}
	
	
	/**
	 * 保存运营城市对象
	 * @param params
	 * @return
	 */
	public SrvHost save(){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		if(this.getShId()!=null)
			srvHostService.updateSrvHost(this);
		else
			return srvHostService.saveSrvHost(this);
		return this;
	}
	
	
	/**
	 * 更新运营城市对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		srvHostService.updateSrvHost$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		if($.equals($.config("logic_delete"),"true"))
			srvHostService.removeSrvHostById(this.getShId());
		else
			srvHostService.deleteSrvHostById(this.getShId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvHostService srvHostService = $.GetSpringBean("srvHostService");
		return srvHostService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getShId(){
		return this.shId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getShId$(){
		String strValue="";
		 strValue=$.str(this.getShId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setShId(Long shId){
		this.shId = shId;
		this.setSeted(F.shId);
	}
	/*******************************城市名称**********************************/	
	/**
	* 城市名称 [非空]      
	**/
	public String getShName(){
		return this.shName;
	}
	/**
	* 获取城市名称格式化(toString)
	**/
	public String getShName$(){
		String strValue="";
		 strValue=$.str(this.getShName());
	 	 return strValue;
	}
	/**
	* 城市名称 [非空]      
	**/
	public void setShName(String shName){
		this.shName = shName;
		this.setSeted(F.shName);
	}
	/*******************************运营商**********************************/	
	/**
	* 运营商 [非空] [CsOper]     
	**/
	public Long getShOper(){
		return this.shOper;
	}
	/**
	* 获取运营商格式化(toString)
	**/
	public String getShOper$(){
		String strValue="";
		if(this.getShOper()!=null){
				strValue+=$.str(CsOper.getKeyValue(this.getShOper()));
		}			
	 	 return strValue;
	}
	/**
	* 运营商 [非空] [CsOper]     
	**/
	public void setShOper(Long shOper){
		this.shOper = shOper;
		this.setSeted(F.shOper);
	}
	/**
	* 获取关联对象[运营商]
	**/	 			
 	public CsOper get$shOper(){
 		com.ccclubs.model.CsOper csOper = (com.ccclubs.model.CsOper) $.GetRequest("CsOper$"+this.getShOper());
 		if(csOper!=null)
			return csOper;
		if(this.getShOper()!=null){
 			csOper = CsOper.get(this.getShOper());
 		}
 		$.SetRequest("CsOper$"+this.getShOper(), csOper);
	 	return csOper;
	}
	/*******************************标识**********************************/	
	/**
	* 标识       
	**/
	public String getShFlag(){
		return this.shFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getShFlag$(){
		String strValue="";
		 strValue=$.str(this.getShFlag());
	 	 return strValue;
	}
	/**
	* 标识       
	**/
	public void setShFlag(String shFlag){
		this.shFlag = shFlag;
		this.setSeted(F.shFlag);
	}
	/*******************************默认计费方式**********************************/	
	/**
	* 默认计费方式 [非空] [CsUserType]     
	**/
	public Long getShFeeType(){
		return this.shFeeType;
	}
	/**
	* 获取默认计费方式格式化(toString)
	**/
	public String getShFeeType$(){
		String strValue="";
		if(this.getShFeeType()!=null){
				strValue+=$.str(CsUserType.getKeyValue(this.getShFeeType()));
		}			
	 	 return strValue;
	}
	/**
	* 默认计费方式 [非空] [CsUserType]     
	**/
	public void setShFeeType(Long shFeeType){
		this.shFeeType = shFeeType;
		this.setSeted(F.shFeeType);
	}
	/**
	* 获取关联对象[计费方式]
	**/	 			
 	public CsUserType get$shFeeType(){
 		com.ccclubs.model.CsUserType csUserType = (com.ccclubs.model.CsUserType) $.GetRequest("CsUserType$"+this.getShFeeType());
 		if(csUserType!=null)
			return csUserType;
		if(this.getShFeeType()!=null){
 			csUserType = CsUserType.get(this.getShFeeType());
 		}
 		$.SetRequest("CsUserType$"+this.getShFeeType(), csUserType);
	 	return csUserType;
	}
	/*******************************上班时间**********************************/	
	/**
	* 上班时间 [非空]      
	**/
	public Double getShOnHour(){
		return this.shOnHour;
	}
	/**
	* 获取上班时间格式化(toString)
	**/
	public String getShOnHour$(){
		String strValue="";
		 strValue=$.str(this.getShOnHour());	
	 	 return strValue;
	}
	/**
	* 上班时间 [非空]      
	**/
	public void setShOnHour(Double shOnHour){
		this.shOnHour = shOnHour;
		this.setSeted(F.shOnHour);
	}
	/*******************************下班时间**********************************/	
	/**
	* 下班时间 [非空]      
	**/
	public Double getShOffHour(){
		return this.shOffHour;
	}
	/**
	* 获取下班时间格式化(toString)
	**/
	public String getShOffHour$(){
		String strValue="";
		 strValue=$.str(this.getShOffHour());	
	 	 return strValue;
	}
	/**
	* 下班时间 [非空]      
	**/
	public void setShOffHour(Double shOffHour){
		this.shOffHour = shOffHour;
		this.setSeted(F.shOffHour);
	}
	/*******************************坐标位置**********************************/	
	/**
	* 坐标位置       
	**/
	public String getShLocation(){
		return this.shLocation;
	}
	/**
	* 获取坐标位置格式化(toString)
	**/
	public String getShLocation$(){
		String strValue="";
		 strValue=$.str(this.getShLocation());
	 	 return strValue;
	}
	/**
	* 坐标位置       
	**/
	public void setShLocation(String shLocation){
		this.shLocation = shLocation;
		this.setSeted(F.shLocation);
	}
	/*******************************运营状态**********************************/	
	/**
	* 运营状态 [非空]   0:下线 1:上线    
	**/
	public Short getShState(){
		return this.shState;
	}
	/**
	* 获取运营状态格式化(toString)
	**/
	public String getShState$(){
		String strValue="";
		 if($.equals($.str(this.getShState()),"0"))
			strValue=$.str("下线");		 
		 if($.equals($.str(this.getShState()),"1"))
			strValue=$.str("上线");		 
	 	 return strValue;
	}
	/**
	* 运营状态 [非空]   0:下线 1:上线    
	**/
	public void setShState(Short shState){
		this.shState = shState;
		this.setSeted(F.shState);
	}
	/*******************************有效状态**********************************/	
	/**
	* 有效状态 [非空]   1:正常 0:无效    
	**/
	public Short getShStatus(){
		return this.shStatus;
	}
	/**
	* 获取有效状态格式化(toString)
	**/
	public String getShStatus$(){
		String strValue="";
		 if($.equals($.str(this.getShStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getShStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 有效状态 [非空]   1:正常 0:无效    
	**/
	public void setShStatus(Short shStatus){
		this.shStatus = shStatus;
		this.setSeted(F.shStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	
	public static int HangZhou = 1;
	public static int BeiJing =  2;
	
	/**
	 * 是否杭州
	 * @param hostId
	 * @return
	 */
	public static boolean isHangZhou(long hostId){
		return hostId == 1;
	}
	
	/**
	 * 是否北京
	 * @param hostId
	 * @return
	 */
	public static boolean isBeiJing(long hostId){
		return hostId == 2;
	}
	
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvHost.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvHost.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvHost.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvHost.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvHost.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvHost.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvHost.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvHost.this);
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
		public M shId(Object shId){this.put("shId", shId);return this;};
	 	/** and sh_id is null */
 		public M shIdNull(){if(this.get("shIdNot")==null)this.put("shIdNot", "");this.put("shId", null);return this;};
 		/** not .... */
 		public M shIdNot(){this.put("shIdNot", "not");return this;};
		/** 城市名称 [非空]       **/
		public M shName(Object shName){this.put("shName", shName);return this;};
	 	/** and sh_name is null */
 		public M shNameNull(){if(this.get("shNameNot")==null)this.put("shNameNot", "");this.put("shName", null);return this;};
 		/** not .... */
 		public M shNameNot(){this.put("shNameNot", "not");return this;};
		/** 运营商 [非空] [CsOper]      **/
		public M shOper(Object shOper){this.put("shOper", shOper);return this;};
	 	/** and sh_oper is null */
 		public M shOperNull(){if(this.get("shOperNot")==null)this.put("shOperNot", "");this.put("shOper", null);return this;};
 		/** not .... */
 		public M shOperNot(){this.put("shOperNot", "not");return this;};
		public M shOper$on(CsOper.M value){
			this.put("CsOper", value);
			this.put("shOper$on", value);
			return this;
		};	
		/** 标识        **/
		public M shFlag(Object shFlag){this.put("shFlag", shFlag);return this;};
	 	/** and sh_flag is null */
 		public M shFlagNull(){if(this.get("shFlagNot")==null)this.put("shFlagNot", "");this.put("shFlag", null);return this;};
 		/** not .... */
 		public M shFlagNot(){this.put("shFlagNot", "not");return this;};
		/** 默认计费方式 [非空] [CsUserType]      **/
		public M shFeeType(Object shFeeType){this.put("shFeeType", shFeeType);return this;};
	 	/** and sh_fee_type is null */
 		public M shFeeTypeNull(){if(this.get("shFeeTypeNot")==null)this.put("shFeeTypeNot", "");this.put("shFeeType", null);return this;};
 		/** not .... */
 		public M shFeeTypeNot(){this.put("shFeeTypeNot", "not");return this;};
		public M shFeeType$on(CsUserType.M value){
			this.put("CsUserType", value);
			this.put("shFeeType$on", value);
			return this;
		};	
		/** 上班时间 [非空]       **/
		public M shOnHour(Object shOnHour){this.put("shOnHour", shOnHour);return this;};
	 	/** and sh_on_hour is null */
 		public M shOnHourNull(){if(this.get("shOnHourNot")==null)this.put("shOnHourNot", "");this.put("shOnHour", null);return this;};
 		/** not .... */
 		public M shOnHourNot(){this.put("shOnHourNot", "not");return this;};
		/** and sh_on_hour >= ? */
		public M shOnHourMin(Object min){this.put("shOnHourMin", min);return this;};
		/** and sh_on_hour <= ? */
		public M shOnHourMax(Object max){this.put("shOnHourMax", max);return this;};
		/** 下班时间 [非空]       **/
		public M shOffHour(Object shOffHour){this.put("shOffHour", shOffHour);return this;};
	 	/** and sh_off_hour is null */
 		public M shOffHourNull(){if(this.get("shOffHourNot")==null)this.put("shOffHourNot", "");this.put("shOffHour", null);return this;};
 		/** not .... */
 		public M shOffHourNot(){this.put("shOffHourNot", "not");return this;};
		/** and sh_off_hour >= ? */
		public M shOffHourMin(Object min){this.put("shOffHourMin", min);return this;};
		/** and sh_off_hour <= ? */
		public M shOffHourMax(Object max){this.put("shOffHourMax", max);return this;};
		/** 坐标位置        **/
		public M shLocation(Object shLocation){this.put("shLocation", shLocation);return this;};
	 	/** and sh_location is null */
 		public M shLocationNull(){if(this.get("shLocationNot")==null)this.put("shLocationNot", "");this.put("shLocation", null);return this;};
 		/** not .... */
 		public M shLocationNot(){this.put("shLocationNot", "not");return this;};
		/** 运营状态 [非空]   0:下线 1:上线     **/
		public M shState(Object shState){this.put("shState", shState);return this;};
	 	/** and sh_state is null */
 		public M shStateNull(){if(this.get("shStateNot")==null)this.put("shStateNot", "");this.put("shState", null);return this;};
 		/** not .... */
 		public M shStateNot(){this.put("shStateNot", "not");return this;};
		/** 有效状态 [非空]   1:正常 0:无效     **/
		public M shStatus(Object shStatus){this.put("shStatus", shStatus);return this;};
	 	/** and sh_status is null */
 		public M shStatusNull(){if(this.get("shStatusNot")==null)this.put("shStatusNot", "");this.put("shStatus", null);return this;};
 		/** not .... */
 		public M shStatusNot(){this.put("shStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有运营城市 **/
		public @api List<SrvHost> list(Integer size){
			return getSrvHostList(this,size);
		}
		/** 获取运营城市分页 **/
		public @api Page<SrvHost> page(int page,int size){
			return getSrvHostPage(page, size , this);
		}
		/** 根据查询条件取运营城市 **/
		public @api SrvHost get(){
			return getSrvHost(this);
		}
		/** 获取运营城市数 **/
		public @api Long count(){
			return getSrvHostCount(this);
		}
		/** 获取运营城市表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvHostEval(strEval,this);
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
			updateSrvHost(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String shId="shId";
		/** 城市名称 [非空]       **/
		public final static @type(String.class) @like String shName="shName";
		/** 运营商 [非空] [CsOper]      **/
		public final static @type(Long.class)  String shOper="shOper";
		/** 标识        **/
		public final static @type(String.class) @like String shFlag="shFlag";
		/** 默认计费方式 [非空] [CsUserType]      **/
		public final static @type(Long.class)  String shFeeType="shFeeType";
		/** 上班时间 [非空]       **/
		public final static @type(Double.class)  String shOnHour="shOnHour";
		/** and sh_on_hour >= ? */
		public final static @type(Double.class) String shOnHourMin="shOnHourMin";
		/** and sh_on_hour <= ? */
		public final static @type(Double.class) String shOnHourMax="shOnHourMax";
		/** 下班时间 [非空]       **/
		public final static @type(Double.class)  String shOffHour="shOffHour";
		/** and sh_off_hour >= ? */
		public final static @type(Double.class) String shOffHourMin="shOffHourMin";
		/** and sh_off_hour <= ? */
		public final static @type(Double.class) String shOffHourMax="shOffHourMax";
		/** 坐标位置        **/
		public final static @type(String.class) @like String shLocation="shLocation";
		/** 运营状态 [非空]   0:下线 1:上线     **/
		public final static @type(Short.class)  String shState="shState";
		/** 有效状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String shStatus="shStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String shId="sh_id";
		/** 城市名称 [非空]       **/
		public final static String shName="sh_name";
		/** 运营商 [非空] [CsOper]      **/
		public final static String shOper="sh_oper";
		/** 标识        **/
		public final static String shFlag="sh_flag";
		/** 默认计费方式 [非空] [CsUserType]      **/
		public final static String shFeeType="sh_fee_type";
		/** 上班时间 [非空]       **/
		public final static String shOnHour="sh_on_hour";
		/** 下班时间 [非空]       **/
		public final static String shOffHour="sh_off_hour";
		/** 坐标位置        **/
		public final static String shLocation="sh_location";
		/** 运营状态 [非空]   0:下线 1:上线     **/
		public final static String shState="sh_state";
		/** 有效状态 [非空]   1:正常 0:无效     **/
		public final static String shStatus="sh_status";
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
				$.Set(name,SrvHost.getSrvHost(params));
			else
				$.Set(name,SrvHost.getSrvHostList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取运营城市数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvHost.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvHost.getSrvHost(params);
			else
				value = SrvHost.getSrvHostList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvHost.Get($.add(SrvHost.F.shId,param));
		else if(!$.empty(param.toString()))
			value = SrvHost.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvHost$"+param.hashCode(), value);
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
	public void mergeSet(SrvHost old){
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