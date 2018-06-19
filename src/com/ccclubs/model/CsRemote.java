package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@namespace("control/remote")
public @caption("远程控制") @table("cs_remote") class CsRemote implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csr_id")   @primary   @note("  ") Long csrId;
	private @caption("城市") @column("csr_host")    @relate("$csrHost") @RelateClass(SrvHost.class)  @note("  ") Long csrHost;
	private SrvHost $csrHost;//关联对象[城市]
	private @caption("车牌号码") @column("csr_number")      @note("  ") String csrNumber;
	private @caption("控制车辆") @column("csr_car")    @relate("$csrCar") @RelateClass(CsCar.class)  @note("  ") Long csrCar;
	private CsCar $csrCar;//关联对象[控制车辆]
	private @caption("数据方式") @column("csr_way")      @note(" 0:网关 1:短信 断线的车辆建议用短信方式") Short csrWay;
	private @caption("控制类型") @column("csr_type")      @note(" 0:远程开门 1:远程关门 2:允许启动 3:断油电 4:初始化 5:同步时钟 6:远程取车 7:远程还车 8:远程升级 9:远程重启 10:GPS远程开 11:GPS远程关 12:读取RFID序列号 13:设置GPS坐标 14:查询手机号 15:刷卡器开 16:刷卡器关 17:远程鸣笛  ") Short csrType;
	private @caption("控制串码") @column("csr_code")      @note("  ") String csrCode;
	private @caption("操作人") @column("csr_editor")    @relate("$csrEditor") @RelateClass(SrvUser.class)  @note("  ") Long csrEditor;
	private SrvUser $csrEditor;//关联对象[操作人]
	private @caption("备注") @column("csr_remark")      @note("  ") String csrRemark;
	private @caption("操作成功时间") @column("csr_update_time")      @note("  ") Date csrUpdateTime;
	private @caption("添加时间") @column("csr_add_time")      @note("  ") Date csrAddTime;
	private @caption("数据发送状态") @column("csr_state")      @note(" 0:未发送 1:已发送 2:组装中  ") Short csrState;
	private @caption("操作结果") @column("csr_status")      @note(" 0:未知 1:操作成功 2:操作失败  ") Short csrStatus;
	private @caption("控制原因") @column("csr_case")    @relate("$csrCase") @RelateClass(SrvProperty.class)  @note("查询REMOTE_CASE") Long csrCase;
	private SrvProperty $csrCase;//关联对象[控制原因]
	
	//默认构造函数
	public CsRemote(){
	
	}
	
	//主键构造函数
	public CsRemote(Long id){
		this.csrId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsRemote(Long csrHost,String csrNumber,Long csrCar,Short csrWay,Short csrType,String csrCode,Long csrEditor,String csrRemark,Date csrUpdateTime,Date csrAddTime,Short csrState,Short csrStatus,Long csrCase){
		this.csrHost=csrHost;
		this.csrNumber=csrNumber;
		this.csrCar=csrCar;
		this.csrWay=csrWay;
		this.csrType=csrType;
		this.csrCode=csrCode;
		this.csrEditor=csrEditor;
		this.csrRemark=csrRemark;
		this.csrUpdateTime=csrUpdateTime;
		this.csrAddTime=csrAddTime;
		this.csrState=csrState;
		this.csrStatus=csrStatus;
		this.csrCase=csrCase;
	}
	
	//设置非空字段
	public CsRemote setNotNull(){
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsRemote csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsRemote csrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
		return this;
	}
	/** 车牌号码 [非空]       **/
	public CsRemote csrNumber(String csrNumber){
		this.csrNumber = csrNumber;
		this.setSeted(F.csrNumber);
		return this;
	}
	/** 控制车辆 [非空]       **/
	public CsRemote csrCar(Long csrCar){
		this.csrCar = csrCar;
		this.setSeted(F.csrCar);
		return this;
	}
	/** 数据方式 [非空]       **/
	public CsRemote csrWay(Short csrWay){
		this.csrWay = csrWay;
		this.setSeted(F.csrWay);
		return this;
	}
	/** 控制类型 [非空]       **/
	public CsRemote csrType(Short csrType){
		this.csrType = csrType;
		this.setSeted(F.csrType);
		return this;
	}
	/** 控制串码 [非空]       **/
	public CsRemote csrCode(String csrCode){
		this.csrCode = csrCode;
		this.setSeted(F.csrCode);
		return this;
	}
	/** 操作人 [非空]       **/
	public CsRemote csrEditor(Long csrEditor){
		this.csrEditor = csrEditor;
		this.setSeted(F.csrEditor);
		return this;
	}
	/** 备注 [非空]       **/
	public CsRemote csrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
		return this;
	}
	/** 操作成功时间 [非空]       **/
	public CsRemote csrUpdateTime(Date csrUpdateTime){
		this.csrUpdateTime = csrUpdateTime;
		this.setSeted(F.csrUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsRemote csrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
		return this;
	}
	/** 数据发送状态 [非空]       **/
	public CsRemote csrState(Short csrState){
		this.csrState = csrState;
		this.setSeted(F.csrState);
		return this;
	}
	/** 操作结果 [非空]       **/
	public CsRemote csrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
		return this;
	}
	/** 控制原因 [非空]       **/
	public CsRemote csrCase(Long csrCase){
		this.csrCase = csrCase;
		this.setSeted(F.csrCase);
		return this;
	}
	
	//克隆对象
	public CsRemote clone(){
		CsRemote clone = new CsRemote();
		clone.csrId=this.csrId;
		clone.csrHost=this.csrHost;
		clone.csrNumber=this.csrNumber;
		clone.csrCar=this.csrCar;
		clone.csrWay=this.csrWay;
		clone.csrType=this.csrType;
		clone.csrCode=this.csrCode;
		clone.csrEditor=this.csrEditor;
		clone.csrRemark=this.csrRemark;
		clone.csrUpdateTime=this.csrUpdateTime;
		clone.csrAddTime=this.csrAddTime;
		clone.csrState=this.csrState;
		clone.csrStatus=this.csrStatus;
		clone.csrCase=this.csrCase;
		return clone;
	}
	
	
	/**
	 * 根据ID取远程控制
	 * @param id
	 * @return
	 */
	public static @api CsRemote get(Long id){		
		return getCsRemoteById(id);
	}
	/**
	 * 获取所有远程控制
	 * @return
	 */
	public static @api List<CsRemote> list(Map params,Integer size){
		return getCsRemoteList(params,size);
	}
	/**
	 * 获取远程控制分页
	 * @return
	 */
	public static @api Page<CsRemote> page(int page,int size,Map params){
		return getCsRemotePage(page, size , params);
	}
	/**
	 * 根据查询条件取远程控制
	 * @param params
	 * @return
	 */
	public static @api CsRemote Get(Map params){
		return getCsRemote(params);
	}
	/**
	 * 获取远程控制数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRemoteCount(params);
	}
	/**
	 * 获取远程控制数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRemoteEval(eval,params);
	}
	
	/**
	 * 根据ID取远程控制
	 * @param id
	 * @return
	 */
	public static @api CsRemote getCsRemoteById(Long id){		
		CsRemote csRemote = (CsRemote) $.GetRequest("CsRemote$"+id);
		if(csRemote!=null)
			return csRemote;
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");		
		return csRemoteService.getCsRemoteById(id);
	}
	
	
	/**
	 * 根据ID取远程控制的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){//当前对象不支持键值缓存
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrNumber$();
		if(!"CsrNumber".equals("CsrNumber"))
			keyValue+="("+this.getCsrNumber$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有远程控制
	 * @return
	 */
	public static @api List<CsRemote> getCsRemoteList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		return csRemoteService.getCsRemoteList(params, size);
	}
	
	/**
	 * 获取远程控制分页
	 * @return
	 */
	public static @api Page<CsRemote> getCsRemotePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		return csRemoteService.getCsRemotePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取远程控制
	 * @param params
	 * @return
	 */
	public static @api CsRemote getCsRemote(Map params){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		return csRemoteService.getCsRemote(params);
	}
	
	/**
	 * 获取远程控制数
	 * @return
	 */
	public static @api Long getCsRemoteCount(Map params){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		return csRemoteService.getCsRemoteCount(params);
	}
		
		
	/**
	 * 获取远程控制自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRemoteEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		return csRemoteService.getCsRemoteEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRemote(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		csRemoteService.updateCsRemoteByConfirm(set, where);
	}
	
	
	/**
	 * 保存远程控制对象
	 * @param params
	 * @return
	 */
	public CsRemote save(){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		if(this.getCsrId()!=null)
			csRemoteService.updateCsRemote(this);
		else
			return csRemoteService.saveCsRemote(this);
		return this;
	}
	
	
	/**
	 * 更新远程控制对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		csRemoteService.updateCsRemote$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		csRemoteService.deleteCsRemoteById(this.getCsrId());
}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRemoteService csRemoteService = $.GetSpringBean("csRemoteService");
		return csRemoteService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrId(){
		return this.csrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrId$(){
		String strValue="";
		 strValue=$.str(this.getCsrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCsrHost(){
		return this.csrHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsrHost$(){
		String strValue="";
		if(this.getCsrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCsrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$csrHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsrHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsrHost()!=null){
			srvHost = SrvHost.get(this.getCsrHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsrHost(), srvHost);
	 	return srvHost;
	}
	/*******************************车牌号码**********************************/	
	/**
	* 车牌号码 [非空]      
	**/
	public String getCsrNumber(){
		return this.csrNumber;
	}
	/**
	* 获取车牌号码格式化(toString)
	**/
	public String getCsrNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsrNumber());
	 	 return strValue;
	}
	/**
	* 车牌号码 [非空]     
	**/
	public void setCsrNumber(String csrNumber){
		this.csrNumber = csrNumber;
		this.setSeted(F.csrNumber);
	}
	/*******************************控制车辆**********************************/	
	/**
	* 控制车辆 [非空]      
	**/
	public Long getCsrCar(){
		return this.csrCar;
	}
	/**
	* 获取控制车辆格式化(toString)
	**/
	public String getCsrCar$(){
		String strValue="";
		if(this.getCsrCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsrCar()));
		}			
	 	 return strValue;
	}
	/**
	* 控制车辆 [非空]     
	**/
	public void setCsrCar(Long csrCar){
		this.csrCar = csrCar;
		this.setSeted(F.csrCar);
	}
	/**
	* 获取关联对象[控制车辆]
	**/	 			
 	public CsCar get$csrCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsrCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsrCar()!=null){
			csCar = CsCar.get(this.getCsrCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsrCar(), csCar);
	 	return csCar;
	}
	/*******************************数据方式**********************************/	
	/**
	* 数据方式 [非空]      
	**/
	public Short getCsrWay(){
		return this.csrWay;
	}
	/**
	* 获取数据方式格式化(toString)
	**/
	public String getCsrWay$(){
		String strValue="";
		 if($.equals($.str(this.getCsrWay()),"1"))
			 strValue=$.str("短信");
		 if($.equals($.str(this.getCsrWay()),"0"))
			 strValue=$.str("网关");
	 	 return strValue;
	}
	/**
	* 数据方式 [非空]     
	**/
	public void setCsrWay(Short csrWay){
		this.csrWay = csrWay;
		this.setSeted(F.csrWay);
	}
	/*******************************控制类型**********************************/	
	/**
	* 控制类型 [非空]      
	**/
	public Short getCsrType(){
		return this.csrType;
	}
	/**
	* 获取控制类型格式化(toString)
	**/
	public String getCsrType$(){
		String strValue="";
		 if($.equals($.str(this.getCsrType()),"17"))
			 strValue=$.str("远程鸣笛");
		 if($.equals($.str(this.getCsrType()),"15"))
			 strValue=$.str("刷卡器开");
		 if($.equals($.str(this.getCsrType()),"16"))
			 strValue=$.str("刷卡器关");
		 if($.equals($.str(this.getCsrType()),"13"))
			 strValue=$.str("设置GPS坐标");
		 if($.equals($.str(this.getCsrType()),"14"))
			 strValue=$.str("查询手机号");
		 if($.equals($.str(this.getCsrType()),"11"))
			 strValue=$.str("GPS远程关");
		 if($.equals($.str(this.getCsrType()),"12"))
			 strValue=$.str("读取RFID序列号");
		 if($.equals($.str(this.getCsrType()),"3"))
			 strValue=$.str("断油电");
		 if($.equals($.str(this.getCsrType()),"2"))
			 strValue=$.str("允许启动");
		 if($.equals($.str(this.getCsrType()),"10"))
			 strValue=$.str("GPS远程开");
		 if($.equals($.str(this.getCsrType()),"1"))
			 strValue=$.str("远程关门");
		 if($.equals($.str(this.getCsrType()),"0"))
			 strValue=$.str("远程开门");
		 if($.equals($.str(this.getCsrType()),"7"))
			 strValue=$.str("远程还车");
		 if($.equals($.str(this.getCsrType()),"6"))
			 strValue=$.str("远程取车");
		 if($.equals($.str(this.getCsrType()),"5"))
			 strValue=$.str("同步时钟");
		 if($.equals($.str(this.getCsrType()),"4"))
			 strValue=$.str("初始化");
		 if($.equals($.str(this.getCsrType()),"9"))
			 strValue=$.str("远程重启");
		 if($.equals($.str(this.getCsrType()),"8"))
			 strValue=$.str("远程升级");
	 	 return strValue;
	}
	/**
	* 控制类型 [非空]     
	**/
	public void setCsrType(Short csrType){
		this.csrType = csrType;
		this.setSeted(F.csrType);
	}
	/*******************************控制串码**********************************/	
	/**
	* 控制串码 [非空]      
	**/
	public String getCsrCode(){
		return this.csrCode;
	}
	/**
	* 获取控制串码格式化(toString)
	**/
	public String getCsrCode$(){
		String strValue="";
		 strValue=$.str(this.getCsrCode());
	 	 return strValue;
	}
	/**
	* 控制串码 [非空]     
	**/
	public void setCsrCode(String csrCode){
		this.csrCode = csrCode;
		this.setSeted(F.csrCode);
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空]      
	**/
	public Long getCsrEditor(){
		return this.csrEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCsrEditor$(){
		String strValue="";
		if(this.getCsrEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空]     
	**/
	public void setCsrEditor(Long csrEditor){
		this.csrEditor = csrEditor;
		this.setSeted(F.csrEditor);
	}
	/**
	* 获取关联对象[操作人]
	**/	 			
 	public SrvUser get$csrEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrEditor()!=null){
			srvUser = SrvUser.get(this.getCsrEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注 [非空]      
	**/
	public String getCsrRemark(){
		return this.csrRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemark());
	 	 return strValue;
	}
	/**
	* 备注 [非空]     
	**/
	public void setCsrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
	}
	/*******************************操作成功时间**********************************/	
	/**
	* 操作成功时间 [非空]      
	**/
	public Date getCsrUpdateTime(){
		return this.csrUpdateTime;
	}
	/**
	* 获取操作成功时间格式化(toString)
	**/
	public String getCsrUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 操作成功时间 [非空]     
	**/
	public void setCsrUpdateTime(Date csrUpdateTime){
		this.csrUpdateTime = csrUpdateTime;
		this.setSeted(F.csrUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsrAddTime(){
		return this.csrAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCsrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
	}
	/*******************************数据发送状态**********************************/	
	/**
	* 数据发送状态 [非空]      
	**/
	public Short getCsrState(){
		return this.csrState;
	}
	/**
	* 获取数据发送状态格式化(toString)
	**/
	public String getCsrState$(){
		String strValue="";
		 if($.equals($.str(this.getCsrState()),"2"))
			 strValue=$.str("组装中");
		 if($.equals($.str(this.getCsrState()),"1"))
			 strValue=$.str("已发送");
		 if($.equals($.str(this.getCsrState()),"0"))
			 strValue=$.str("未发送");
	 	 return strValue;
	}
	/**
	* 数据发送状态 [非空]     
	**/
	public void setCsrState(Short csrState){
		this.csrState = csrState;
		this.setSeted(F.csrState);
	}
	/*******************************操作结果**********************************/	
	/**
	* 操作结果 [非空]      
	**/
	public Short getCsrStatus(){
		return this.csrStatus;
	}
	/**
	* 获取操作结果格式化(toString)
	**/
	public String getCsrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsrStatus()),"2"))
			 strValue=$.str("操作失败");
		 if($.equals($.str(this.getCsrStatus()),"1"))
			 strValue=$.str("操作成功");
		 if($.equals($.str(this.getCsrStatus()),"0"))
			 strValue=$.str("未知");
	 	 return strValue;
	}
	/**
	* 操作结果 [非空]     
	**/
	public void setCsrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
	}
	/*******************************控制原因**********************************/	
	/**
	* 控制原因 [非空]      
	**/
	public Long getCsrCase(){
		return this.csrCase;
	}
	/**
	* 获取控制原因格式化(toString)
	**/
	public String getCsrCase$(){
		String strValue="";
		if(this.getCsrCase()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsrCase()));
		}			
	 	 return strValue;
	}
	/**
	* 控制原因 [非空]     
	**/
	public void setCsrCase(Long csrCase){
		this.csrCase = csrCase;
		this.setSeted(F.csrCase);
	}
	/**
	* 获取关联对象[控制原因]
	**/	 			
 	public SrvProperty get$csrCase(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsrCase());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsrCase()!=null){
			srvProperty = SrvProperty.get(this.getCsrCase());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsrCase(), srvProperty);
	 	return srvProperty;
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRemote.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRemote.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRemote.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRemote.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRemote.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRemote.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRemote.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRemote.this);
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
		public M csrId(Object csrId){this.put("csrId", csrId);return this;};
		/** and csr_id is null */
		public M csrIdNull(){if(this.get("csrIdNot")==null)this.put("csrIdNot", "");this.put("csrId", null);return this;};
		/** not .... */
		public M csrIdNot(){this.put("csrIdNot", "not");return this;};
		/** and csr_id >= ? */
		public M csrIdMin(Object min){this.put("csrIdMin", min);return this;};
		/** and csr_id <= ? */
		public M csrIdMax(Object max){this.put("csrIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M csrHost(Object csrHost){this.put("csrHost", csrHost);return this;};
		/** and csr_host is null */
		public M csrHostNull(){if(this.get("csrHostNot")==null)this.put("csrHostNot", "");this.put("csrHost", null);return this;};
		/** not .... */
		public M csrHostNot(){this.put("csrHostNot", "not");return this;};
		/** and csr_host >= ? */
		public M csrHostMin(Object min){this.put("csrHostMin", min);return this;};
		/** and csr_host <= ? */
		public M csrHostMax(Object max){this.put("csrHostMax", max);return this;};
		/** 车牌号码 [非空]       **/
		public M csrNumber(Object csrNumber){this.put("csrNumber", csrNumber);return this;};
		/** and csr_number is null */
		public M csrNumberNull(){if(this.get("csrNumberNot")==null)this.put("csrNumberNot", "");this.put("csrNumber", null);return this;};
		/** not .... */
		public M csrNumberNot(){this.put("csrNumberNot", "not");return this;};
		/** 控制车辆 [非空]       **/
		public M csrCar(Object csrCar){this.put("csrCar", csrCar);return this;};
		/** and csr_car is null */
		public M csrCarNull(){if(this.get("csrCarNot")==null)this.put("csrCarNot", "");this.put("csrCar", null);return this;};
		/** not .... */
		public M csrCarNot(){this.put("csrCarNot", "not");return this;};
		public M csrCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csrCar$on", value);
			return this;
		};	
		/** and csr_car >= ? */
		public M csrCarMin(Object min){this.put("csrCarMin", min);return this;};
		/** and csr_car <= ? */
		public M csrCarMax(Object max){this.put("csrCarMax", max);return this;};
		/** 数据方式 [非空]       **/
		public M csrWay(Object csrWay){this.put("csrWay", csrWay);return this;};
		/** and csr_way is null */
		public M csrWayNull(){if(this.get("csrWayNot")==null)this.put("csrWayNot", "");this.put("csrWay", null);return this;};
		/** not .... */
		public M csrWayNot(){this.put("csrWayNot", "not");return this;};
		/** and csr_way >= ? */
		public M csrWayMin(Object min){this.put("csrWayMin", min);return this;};
		/** and csr_way <= ? */
		public M csrWayMax(Object max){this.put("csrWayMax", max);return this;};
		/** 控制类型 [非空]       **/
		public M csrType(Object csrType){this.put("csrType", csrType);return this;};
		/** and csr_type is null */
		public M csrTypeNull(){if(this.get("csrTypeNot")==null)this.put("csrTypeNot", "");this.put("csrType", null);return this;};
		/** not .... */
		public M csrTypeNot(){this.put("csrTypeNot", "not");return this;};
		/** and csr_type >= ? */
		public M csrTypeMin(Object min){this.put("csrTypeMin", min);return this;};
		/** and csr_type <= ? */
		public M csrTypeMax(Object max){this.put("csrTypeMax", max);return this;};
		/** 控制串码 [非空]       **/
		public M csrCode(Object csrCode){this.put("csrCode", csrCode);return this;};
		/** and csr_code is null */
		public M csrCodeNull(){if(this.get("csrCodeNot")==null)this.put("csrCodeNot", "");this.put("csrCode", null);return this;};
		/** not .... */
		public M csrCodeNot(){this.put("csrCodeNot", "not");return this;};
		/** 操作人 [非空]       **/
		public M csrEditor(Object csrEditor){this.put("csrEditor", csrEditor);return this;};
		/** and csr_editor is null */
		public M csrEditorNull(){if(this.get("csrEditorNot")==null)this.put("csrEditorNot", "");this.put("csrEditor", null);return this;};
		/** not .... */
		public M csrEditorNot(){this.put("csrEditorNot", "not");return this;};
		public M csrEditor$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("csrEditor$on", value);
			return this;
		};	
		/** and csr_editor >= ? */
		public M csrEditorMin(Object min){this.put("csrEditorMin", min);return this;};
		/** and csr_editor <= ? */
		public M csrEditorMax(Object max){this.put("csrEditorMax", max);return this;};
		/** 备注 [非空]       **/
		public M csrRemark(Object csrRemark){this.put("csrRemark", csrRemark);return this;};
		/** and csr_remark is null */
		public M csrRemarkNull(){if(this.get("csrRemarkNot")==null)this.put("csrRemarkNot", "");this.put("csrRemark", null);return this;};
		/** not .... */
		public M csrRemarkNot(){this.put("csrRemarkNot", "not");return this;};
		/** 操作成功时间 [非空]       **/
		public M csrUpdateTime(Object csrUpdateTime){this.put("csrUpdateTime", csrUpdateTime);return this;};
		/** and csr_update_time is null */
		public M csrUpdateTimeNull(){if(this.get("csrUpdateTimeNot")==null)this.put("csrUpdateTimeNot", "");this.put("csrUpdateTime", null);return this;};
		/** not .... */
		public M csrUpdateTimeNot(){this.put("csrUpdateTimeNot", "not");return this;};
		/** and csr_update_time >= ? */
 		public M csrUpdateTimeStart(Object start){this.put("csrUpdateTimeStart", start);return this;};			
 		/** and csr_update_time <= ? */
 		public M csrUpdateTimeEnd(Object end){this.put("csrUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csrAddTime(Object csrAddTime){this.put("csrAddTime", csrAddTime);return this;};
		/** and csr_add_time is null */
		public M csrAddTimeNull(){if(this.get("csrAddTimeNot")==null)this.put("csrAddTimeNot", "");this.put("csrAddTime", null);return this;};
		/** not .... */
		public M csrAddTimeNot(){this.put("csrAddTimeNot", "not");return this;};
		/** and csr_add_time >= ? */
 		public M csrAddTimeStart(Object start){this.put("csrAddTimeStart", start);return this;};			
 		/** and csr_add_time <= ? */
 		public M csrAddTimeEnd(Object end){this.put("csrAddTimeEnd", end);return this;};
		/** 数据发送状态 [非空]       **/
		public M csrState(Object csrState){this.put("csrState", csrState);return this;};
		/** and csr_state is null */
		public M csrStateNull(){if(this.get("csrStateNot")==null)this.put("csrStateNot", "");this.put("csrState", null);return this;};
		/** not .... */
		public M csrStateNot(){this.put("csrStateNot", "not");return this;};
		/** and csr_state >= ? */
		public M csrStateMin(Object min){this.put("csrStateMin", min);return this;};
		/** and csr_state <= ? */
		public M csrStateMax(Object max){this.put("csrStateMax", max);return this;};
		/** 操作结果 [非空]       **/
		public M csrStatus(Object csrStatus){this.put("csrStatus", csrStatus);return this;};
		/** and csr_status is null */
		public M csrStatusNull(){if(this.get("csrStatusNot")==null)this.put("csrStatusNot", "");this.put("csrStatus", null);return this;};
		/** not .... */
		public M csrStatusNot(){this.put("csrStatusNot", "not");return this;};
		/** and csr_status >= ? */
		public M csrStatusMin(Object min){this.put("csrStatusMin", min);return this;};
		/** and csr_status <= ? */
		public M csrStatusMax(Object max){this.put("csrStatusMax", max);return this;};
		/** 控制原因 [非空]       **/
		public M csrCase(Object csrCase){this.put("csrCase", csrCase);return this;};
		/** and csr_case is null */
		public M csrCaseNull(){if(this.get("csrCaseNot")==null)this.put("csrCaseNot", "");this.put("csrCase", null);return this;};
		/** not .... */
		public M csrCaseNot(){this.put("csrCaseNot", "not");return this;};
		public M csrCase$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csrCase$on", value);
			return this;
		};	
		/** and csr_case >= ? */
		public M csrCaseMin(Object min){this.put("csrCaseMin", min);return this;};
		/** and csr_case <= ? */
		public M csrCaseMax(Object max){this.put("csrCaseMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有远程控制 **/
		public @api List<CsRemote> list(Integer size){
			return getCsRemoteList(this,size);
		}
		/** 获取远程控制分页 **/
		public @api Page<CsRemote> page(int page,int size){
			return getCsRemotePage(page, size , this);
		}
		/** 根据查询条件取远程控制 **/
		public @api CsRemote get(){
			return getCsRemote(this);
		}
		/** 获取远程控制数 **/
		public @api Long count(){
			return getCsRemoteCount(this);
		}
		/** 获取远程控制表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRemoteEval(strEval,this);
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
			updateCsRemote(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String csrHost="csrHost";
		/** 车牌号码 [非空]       **/
		public final static @type(String.class)  String csrNumber="csrNumber";
		/** 控制车辆 [非空]       **/
		public final static @type(Long.class)  String csrCar="csrCar";
		/** 数据方式 [非空]       **/
		public final static @type(Short.class)  String csrWay="csrWay";
		/** 控制类型 [非空]       **/
		public final static @type(Short.class)  String csrType="csrType";
		/** 控制串码 [非空]       **/
		public final static @type(String.class)  String csrCode="csrCode";
		/** 操作人 [非空]       **/
		public final static @type(Long.class)  String csrEditor="csrEditor";
		/** 备注 [非空]       **/
		public final static @type(String.class)  String csrRemark="csrRemark";
		/** 操作成功时间 [非空]       **/
		public final static @type(Date.class)  String csrUpdateTime="csrUpdateTime";
		/** and csr_update_time >= ? */
 		public final static @type(Date.class) String csrUpdateTimeStart="csrUpdateTimeStart";
 		/** and csr_update_time <= ? */
 		public final static @type(Date.class) String csrUpdateTimeEnd="csrUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csrAddTime="csrAddTime";
		/** and csr_add_time >= ? */
 		public final static @type(Date.class) String csrAddTimeStart="csrAddTimeStart";
 		/** and csr_add_time <= ? */
 		public final static @type(Date.class) String csrAddTimeEnd="csrAddTimeEnd";
		/** 数据发送状态 [非空]       **/
		public final static @type(Short.class)  String csrState="csrState";
		/** 操作结果 [非空]       **/
		public final static @type(Short.class)  String csrStatus="csrStatus";
		/** 控制原因 [非空]       **/
		public final static @type(Long.class)  String csrCase="csrCase";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 城市 [非空]       **/
		public final static String csrHost="csr_host";
		/** 车牌号码 [非空]       **/
		public final static String csrNumber="csr_number";
		/** 控制车辆 [非空]       **/
		public final static String csrCar="csr_car";
		/** 数据方式 [非空]       **/
		public final static String csrWay="csr_way";
		/** 控制类型 [非空]       **/
		public final static String csrType="csr_type";
		/** 控制串码 [非空]       **/
		public final static String csrCode="csr_code";
		/** 操作人 [非空]       **/
		public final static String csrEditor="csr_editor";
		/** 备注 [非空]       **/
		public final static String csrRemark="csr_remark";
		/** 操作成功时间 [非空]       **/
		public final static String csrUpdateTime="csr_update_time";
		/** 添加时间 [非空]       **/
		public final static String csrAddTime="csr_add_time";
		/** 数据发送状态 [非空]       **/
		public final static String csrState="csr_state";
		/** 操作结果 [非空]       **/
		public final static String csrStatus="csr_status";
		/** 控制原因 [非空]       **/
		public final static String csrCase="csr_case";
		
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
				$.Set(name,CsRemote.getCsRemote(params));
			else
				$.Set(name,CsRemote.getCsRemoteList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取远程控制数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRemote) $.GetRequest("CsRemote$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRemote.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRemote.getCsRemote(params);
			else
				value = CsRemote.getCsRemoteList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRemote.Get($.add(CsRemote.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsRemote.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRemote$"+param.hashCode(), value);
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
	public void mergeSet(CsRemote old){
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