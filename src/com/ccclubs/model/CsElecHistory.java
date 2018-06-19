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

@namespace("control/elechistory")
public @caption("电桩历史数据") @table("cs_elec_history") class CsElecHistory implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("cseh_id")   @primary  @note("  ") Long csehId;// 主键 外键 非空     
	private @caption("设备ID") @column("cseh_did")    @note("  ") String csehDid;// 非空     
	private @caption("所属电桩") @column("cseh_power_pile")    @relate("$csehPowerPile") @RelateClass(CsPowerPile.class)  @note("  ") Long csehPowerPile;//     
 	private CsPowerPile $csehPowerPile;//关联对象[充电桩]
	private @caption("状态编码") @column("cseh_status_code")    @note("  ") String csehStatusCode;//     
	private @caption("状态描述") @column("cseh_status_text")    @note("  ") String csehStatusText;//     
	private @caption("充电量") @column("cseh_ele_amount")    @note("  ") String csehEleAmount;//     
	private @caption("充电时长") @column("cseh_time_long")    @note("  ") String csehTimeLong;//     
	private @caption("错误码") @column("cseh_error_code")    @note("  ") String csehErrorCode;//     
	private @caption("数据时间") @column("cseh_collect_time")    @note("  ") Date csehCollectTime;//     
	private @caption("添加时间") @column("cseh_add_time")    @note("  ") Date csehAddTime;//     
	private @caption("接口数据") @column("cseh_data")    @note("  ") String csehData;//     
	
	//默认构造函数
	public CsElecHistory(){
	
	}
	
	//主键构造函数
	public CsElecHistory(Long id){
		this.csehId = id;
	}
	
	/**所有字段构造函数 CsElecHistory(csehDid,csehPowerPile,csehStatusCode,csehStatusText,csehEleAmount,csehTimeLong,csehErrorCode,csehCollectTime,csehAddTime,csehData)
	 CsElecHistory(
	 	$.getString("csehDid")//设备ID [非空]
	 	,$.getLong("csehPowerPile")//所属电桩
	 	,$.getString("csehStatusCode")//状态编码
	 	,$.getString("csehStatusText")//状态描述
	 	,$.getString("csehEleAmount")//充电量
	 	,$.getString("csehTimeLong")//充电时长
	 	,$.getString("csehErrorCode")//错误码
	 	,$.getDate("csehCollectTime")//数据时间
	 	,$.getDate("csehAddTime")//添加时间
	 	,$.getString("csehData")//接口数据
	 )
	**/
	public CsElecHistory(String csehDid,Long csehPowerPile,String csehStatusCode,String csehStatusText,String csehEleAmount,String csehTimeLong,String csehErrorCode,Date csehCollectTime,Date csehAddTime,String csehData){
		this.csehDid=csehDid;
		this.csehPowerPile=csehPowerPile;
		this.csehStatusCode=csehStatusCode;
		this.csehStatusText=csehStatusText;
		this.csehEleAmount=csehEleAmount;
		this.csehTimeLong=csehTimeLong;
		this.csehErrorCode=csehErrorCode;
		this.csehCollectTime=csehCollectTime;
		this.csehAddTime=csehAddTime;
		this.csehData=csehData;
	}
	
	//设置非空字段
	public CsElecHistory setNotNull(String csehDid){
		this.csehDid=csehDid;
		return this;
	}
	/** 编号 [非空]       **/
	public CsElecHistory csehId(Long csehId){
		this.csehId = csehId;
		this.setSeted(F.csehId);
		return this;
	}
	/** 设备ID [非空]       **/
	public CsElecHistory csehDid(String csehDid){
		this.csehDid = csehDid;
		this.setSeted(F.csehDid);
		return this;
	}
	/** 所属电桩  [CsPowerPile]      **/
	public CsElecHistory csehPowerPile(Long csehPowerPile){
		this.csehPowerPile = csehPowerPile;
		this.setSeted(F.csehPowerPile);
		return this;
	}
	/** 状态编码        **/
	public CsElecHistory csehStatusCode(String csehStatusCode){
		this.csehStatusCode = csehStatusCode;
		this.setSeted(F.csehStatusCode);
		return this;
	}
	/** 状态描述        **/
	public CsElecHistory csehStatusText(String csehStatusText){
		this.csehStatusText = csehStatusText;
		this.setSeted(F.csehStatusText);
		return this;
	}
	/** 充电量        **/
	public CsElecHistory csehEleAmount(String csehEleAmount){
		this.csehEleAmount = csehEleAmount;
		this.setSeted(F.csehEleAmount);
		return this;
	}
	/** 充电时长        **/
	public CsElecHistory csehTimeLong(String csehTimeLong){
		this.csehTimeLong = csehTimeLong;
		this.setSeted(F.csehTimeLong);
		return this;
	}
	/** 错误码        **/
	public CsElecHistory csehErrorCode(String csehErrorCode){
		this.csehErrorCode = csehErrorCode;
		this.setSeted(F.csehErrorCode);
		return this;
	}
	/** 数据时间        **/
	public CsElecHistory csehCollectTime(Date csehCollectTime){
		this.csehCollectTime = csehCollectTime;
		this.setSeted(F.csehCollectTime);
		return this;
	}
	/** 添加时间        **/
	public CsElecHistory csehAddTime(Date csehAddTime){
		this.csehAddTime = csehAddTime;
		this.setSeted(F.csehAddTime);
		return this;
	}
	/** 接口数据        **/
	public CsElecHistory csehData(String csehData){
		this.csehData = csehData;
		this.setSeted(F.csehData);
		return this;
	}
	
	
	//克隆对象
	public CsElecHistory clone(){
		CsElecHistory clone = new CsElecHistory();
		clone.csehDid=this.csehDid;
		return clone;
	}
	
	
	/**
	 * 根据ID取电桩历史数据
	 * @param id
	 * @return
	 */
	public static @api CsElecHistory get(Long id){		
		return getCsElecHistoryById(id);
	}
	/**
	 * 获取所有电桩历史数据
	 * @return
	 */
	public static @api List<CsElecHistory> list(Map params,Integer size){
		return getCsElecHistoryList(params,size);
	}
	/**
	 * 获取电桩历史数据分页
	 * @return
	 */
	public static @api Page<CsElecHistory> page(int page,int size,Map params){
		return getCsElecHistoryPage(page, size , params);
	}
	/**
	 * 根据查询条件取电桩历史数据
	 * @param params
	 * @return
	 */
	public static @api CsElecHistory Get(Map params){
		return getCsElecHistory(params);
	}
	/**
	 * 获取电桩历史数据数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsElecHistoryCount(params);
	}
	/**
	 * 获取电桩历史数据数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsElecHistoryEval(eval,params);
	}
	
	/**
	 * 根据ID取电桩历史数据
	 * @param id
	 * @return
	 */
	public static @api CsElecHistory getCsElecHistoryById(Long id){		
		CsElecHistory csElecHistory = (CsElecHistory) $.GetRequest("CsElecHistory$"+id);
		if(csElecHistory!=null)
			return csElecHistory;
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");		
		return csElecHistoryService.getCsElecHistoryById(id);
	}
	
	
	/**
	 * 根据ID取电桩历史数据的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		//当前对象不支持键值缓存
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsehDid$();
		if(!"CsehDid".equals("CsehDid"))
			keyValue+="("+this.getCsehDid$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有电桩历史数据
	 * @return
	 */
	public static @api List<CsElecHistory> getCsElecHistoryList(Map params,Integer size){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		return csElecHistoryService.getCsElecHistoryList(params, size);
	}
	
	/**
	 * 获取电桩历史数据分页
	 * @return
	 */
	public static @api Page<CsElecHistory> getCsElecHistoryPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		return csElecHistoryService.getCsElecHistoryPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取电桩历史数据
	 * @param params
	 * @return
	 */
	public static @api CsElecHistory getCsElecHistory(Map params){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		return csElecHistoryService.getCsElecHistory(params);
	}
	
	/**
	 * 获取电桩历史数据数
	 * @return
	 */
	public static @api Long getCsElecHistoryCount(Map params){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		return csElecHistoryService.getCsElecHistoryCount(params);
	}
		
		
	/**
	 * 获取电桩历史数据自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsElecHistoryEval(String eval,Map params){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		return csElecHistoryService.getCsElecHistoryEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsElecHistory(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		csElecHistoryService.updateCsElecHistoryByConfirm(set, where);
	}
	
	
	/**
	 * 保存电桩历史数据对象
	 * @param params
	 * @return
	 */
	public CsElecHistory save(){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		if(this.getCsehId()!=null)
			csElecHistoryService.updateCsElecHistory(this);
		else
			return csElecHistoryService.saveCsElecHistory(this);
		return this;
	}
	
	
	/**
	 * 更新电桩历史数据对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		csElecHistoryService.updateCsElecHistory$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
			csElecHistoryService.deleteCsElecHistoryById(this.getCsehId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsElecHistoryService csElecHistoryService = $.GetSpringBean("csElecHistoryService");
		return csElecHistoryService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsehId(){
		return this.csehId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsehId$(){
		String strValue="";
		 strValue=$.str(this.getCsehId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsehId(Long csehId){
		this.csehId = csehId;
		this.setSeted(F.csehId);
	}
	/*******************************设备ID**********************************/	
	/**
	* 设备ID [非空]      
	**/
	public String getCsehDid(){
		return this.csehDid;
	}
	/**
	* 获取设备ID格式化(toString)
	**/
	public String getCsehDid$(){
		String strValue="";
		 strValue=$.str(this.getCsehDid());
	 	 return strValue;
	}
	/**
	* 设备ID [非空]      
	**/
	public void setCsehDid(String csehDid){
		this.csehDid = csehDid;
		this.setSeted(F.csehDid);
	}
	/*******************************所属电桩**********************************/	
	/**
	* 所属电桩  [CsPowerPile]     
	**/
	public Long getCsehPowerPile(){
		return this.csehPowerPile;
	}
	/**
	* 获取所属电桩格式化(toString)
	**/
	public String getCsehPowerPile$(){
		String strValue="";
		if(this.getCsehPowerPile()!=null){
				strValue+=$.str(CsPowerPile.getKeyValue(this.getCsehPowerPile()));
		}			
	 	 return strValue;
	}
	/**
	* 所属电桩  [CsPowerPile]     
	**/
	public void setCsehPowerPile(Long csehPowerPile){
		this.csehPowerPile = csehPowerPile;
		this.setSeted(F.csehPowerPile);
	}
	/**
	* 获取关联对象[充电桩]
	**/	 			
 	public CsPowerPile get$csehPowerPile(){
 		com.ccclubs.model.CsPowerPile csPowerPile = (com.ccclubs.model.CsPowerPile) $.GetRequest("CsPowerPile$"+this.getCsehPowerPile());
 		if(csPowerPile!=null)
			return csPowerPile;
		if(this.getCsehPowerPile()!=null){
 			csPowerPile = CsPowerPile.get(this.getCsehPowerPile());
 		}
 		$.SetRequest("CsPowerPile$"+this.getCsehPowerPile(), csPowerPile);
	 	return csPowerPile;
	}
	/*******************************状态编码**********************************/	
	/**
	* 状态编码       
	**/
	public String getCsehStatusCode(){
		return this.csehStatusCode;
	}
	/**
	* 获取状态编码格式化(toString)
	**/
	public String getCsehStatusCode$(){
		String strValue="";
		 strValue=$.str(this.getCsehStatusCode());
	 	 return strValue;
	}
	/**
	* 状态编码       
	**/
	public void setCsehStatusCode(String csehStatusCode){
		this.csehStatusCode = csehStatusCode;
		this.setSeted(F.csehStatusCode);
	}
	/*******************************状态描述**********************************/	
	/**
	* 状态描述       
	**/
	public String getCsehStatusText(){
		return this.csehStatusText;
	}
	/**
	* 获取状态描述格式化(toString)
	**/
	public String getCsehStatusText$(){
		String strValue="";
		 strValue=$.str(this.getCsehStatusText());
	 	 return strValue;
	}
	/**
	* 状态描述       
	**/
	public void setCsehStatusText(String csehStatusText){
		this.csehStatusText = csehStatusText;
		this.setSeted(F.csehStatusText);
	}
	/*******************************充电量**********************************/	
	/**
	* 充电量       
	**/
	public String getCsehEleAmount(){
		return this.csehEleAmount;
	}
	/**
	* 获取充电量格式化(toString)
	**/
	public String getCsehEleAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsehEleAmount());
	 	 return strValue;
	}
	/**
	* 充电量       
	**/
	public void setCsehEleAmount(String csehEleAmount){
		this.csehEleAmount = csehEleAmount;
		this.setSeted(F.csehEleAmount);
	}
	/*******************************充电时长**********************************/	
	/**
	* 充电时长       
	**/
	public String getCsehTimeLong(){
		return this.csehTimeLong;
	}
	/**
	* 获取充电时长格式化(toString)
	**/
	public String getCsehTimeLong$(){
		String strValue="";
		 strValue=$.str(this.getCsehTimeLong());
	 	 return strValue;
	}
	/**
	* 充电时长       
	**/
	public void setCsehTimeLong(String csehTimeLong){
		this.csehTimeLong = csehTimeLong;
		this.setSeted(F.csehTimeLong);
	}
	/*******************************错误码**********************************/	
	/**
	* 错误码       
	**/
	public String getCsehErrorCode(){
		return this.csehErrorCode;
	}
	/**
	* 获取错误码格式化(toString)
	**/
	public String getCsehErrorCode$(){
		String strValue="";
		 strValue=$.str(this.getCsehErrorCode());
	 	 return strValue;
	}
	/**
	* 错误码       
	**/
	public void setCsehErrorCode(String csehErrorCode){
		this.csehErrorCode = csehErrorCode;
		this.setSeted(F.csehErrorCode);
	}
	/*******************************数据时间**********************************/	
	/**
	* 数据时间       
	**/
	public Date getCsehCollectTime(){
		return this.csehCollectTime;
	}
	/**
	* 获取数据时间格式化(toString)
	**/
	public String getCsehCollectTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsehCollectTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 数据时间       
	**/
	public void setCsehCollectTime(Date csehCollectTime){
		this.csehCollectTime = csehCollectTime;
		this.setSeted(F.csehCollectTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间       
	**/
	public Date getCsehAddTime(){
		return this.csehAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsehAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsehAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间       
	**/
	public void setCsehAddTime(Date csehAddTime){
		this.csehAddTime = csehAddTime;
		this.setSeted(F.csehAddTime);
	}
	/*******************************接口数据**********************************/	
	/**
	* 接口数据       
	**/
	public String getCsehData(){
		return this.csehData;
	}
	/**
	* 获取接口数据格式化(toString)
	**/
	public String getCsehData$(){
		String strValue="";
		 strValue=$.str(this.getCsehData());
	 	 return strValue;
	}
	/**
	* 接口数据       
	**/
	public void setCsehData(String csehData){
		this.csehData = csehData;
		this.setSeted(F.csehData);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/**
	 * 解析状态编码
	 * @param statusCode
	 * @return 返回状态编码说明
	 */
	public static String getStatusCodeText(String statusCode){
		if(statusCode!=null && statusCode.length()>0){
			int code = Integer.valueOf(statusCode);
			String text = "";
			switch (code) {
			case 20000:
					text ="枪插入充电桩 ";
				break;
			case 20003:
					text ="充电桩急停按钮按下 ";
				break;
			case 20010:
					text ="接地错误 ";
				break;
			case 20020:
					text ="过压/欠压错误";
				break;
			case 20030:
					text ="过流错误";
				break;
			case 20031:
					text ="漏电错误";
				break;
			case 20040:
					text ="车子出错";
				break;
			case 20050:
					text ="充电桩枪连接到车身";
				break;
			case 20051:
					text ="充电桩枪拔出车身";
				break;
			case 20060:
					text ="设备自检错误";
				break;
			case 20070:
					text ="内部错误/继电器错误";
				break;
			case 20080:
					text ="电子锁-解锁";
				break;
			case 20081:
					text ="电子锁-上锁";
				break;
			case 20090:
					text ="继电器-开";
				break;
			case 20091:
					text ="继电器-关";
				break;
			case 20100:
					text ="充电完成";
				break;
			case 20101:
					text ="车身已准备可充电";
				break;
			case 20102:
					text ="车身未准备可充电";
				break;
			default:
					text = "状态未上报";
				break;
			}
			return text;
		}
		return null;
	}
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsElecHistory.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsElecHistory.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsElecHistory.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsElecHistory.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsElecHistory.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsElecHistory.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsElecHistory.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsElecHistory.this);
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
		public M csehId(Object csehId){this.put("csehId", csehId);return this;};
	 	/** and cseh_id is null */
 		public M csehIdNull(){if(this.get("csehIdNot")==null)this.put("csehIdNot", "");this.put("csehId", null);return this;};
 		/** not .... */
 		public M csehIdNot(){this.put("csehIdNot", "not");return this;};
		/** 设备ID [非空]       **/
		public M csehDid(Object csehDid){this.put("csehDid", csehDid);return this;};
	 	/** and cseh_did is null */
 		public M csehDidNull(){if(this.get("csehDidNot")==null)this.put("csehDidNot", "");this.put("csehDid", null);return this;};
 		/** not .... */
 		public M csehDidNot(){this.put("csehDidNot", "not");return this;};
		/** 所属电桩  [CsPowerPile]      **/
		public M csehPowerPile(Object csehPowerPile){this.put("csehPowerPile", csehPowerPile);return this;};
	 	/** and cseh_power_pile is null */
 		public M csehPowerPileNull(){if(this.get("csehPowerPileNot")==null)this.put("csehPowerPileNot", "");this.put("csehPowerPile", null);return this;};
 		/** not .... */
 		public M csehPowerPileNot(){this.put("csehPowerPileNot", "not");return this;};
		/** 状态编码        **/
		public M csehStatusCode(Object csehStatusCode){this.put("csehStatusCode", csehStatusCode);return this;};
	 	/** and cseh_status_code is null */
 		public M csehStatusCodeNull(){if(this.get("csehStatusCodeNot")==null)this.put("csehStatusCodeNot", "");this.put("csehStatusCode", null);return this;};
 		/** not .... */
 		public M csehStatusCodeNot(){this.put("csehStatusCodeNot", "not");return this;};
		/** 状态描述        **/
		public M csehStatusText(Object csehStatusText){this.put("csehStatusText", csehStatusText);return this;};
	 	/** and cseh_status_text is null */
 		public M csehStatusTextNull(){if(this.get("csehStatusTextNot")==null)this.put("csehStatusTextNot", "");this.put("csehStatusText", null);return this;};
 		/** not .... */
 		public M csehStatusTextNot(){this.put("csehStatusTextNot", "not");return this;};
		/** 充电量        **/
		public M csehEleAmount(Object csehEleAmount){this.put("csehEleAmount", csehEleAmount);return this;};
	 	/** and cseh_ele_amount is null */
 		public M csehEleAmountNull(){if(this.get("csehEleAmountNot")==null)this.put("csehEleAmountNot", "");this.put("csehEleAmount", null);return this;};
 		/** not .... */
 		public M csehEleAmountNot(){this.put("csehEleAmountNot", "not");return this;};
		/** 充电时长        **/
		public M csehTimeLong(Object csehTimeLong){this.put("csehTimeLong", csehTimeLong);return this;};
	 	/** and cseh_time_long is null */
 		public M csehTimeLongNull(){if(this.get("csehTimeLongNot")==null)this.put("csehTimeLongNot", "");this.put("csehTimeLong", null);return this;};
 		/** not .... */
 		public M csehTimeLongNot(){this.put("csehTimeLongNot", "not");return this;};
		/** 错误码        **/
		public M csehErrorCode(Object csehErrorCode){this.put("csehErrorCode", csehErrorCode);return this;};
	 	/** and cseh_error_code is null */
 		public M csehErrorCodeNull(){if(this.get("csehErrorCodeNot")==null)this.put("csehErrorCodeNot", "");this.put("csehErrorCode", null);return this;};
 		/** not .... */
 		public M csehErrorCodeNot(){this.put("csehErrorCodeNot", "not");return this;};
		/** 数据时间        **/
		public M csehCollectTime(Object csehCollectTime){this.put("csehCollectTime", csehCollectTime);return this;};
	 	/** and cseh_collect_time is null */
 		public M csehCollectTimeNull(){if(this.get("csehCollectTimeNot")==null)this.put("csehCollectTimeNot", "");this.put("csehCollectTime", null);return this;};
 		/** not .... */
 		public M csehCollectTimeNot(){this.put("csehCollectTimeNot", "not");return this;};
 		/** and cseh_collect_time >= ? */
 		public M csehCollectTimeStart(Object start){this.put("csehCollectTimeStart", start);return this;};			
 		/** and cseh_collect_time <= ? */
 		public M csehCollectTimeEnd(Object end){this.put("csehCollectTimeEnd", end);return this;};
		/** 添加时间        **/
		public M csehAddTime(Object csehAddTime){this.put("csehAddTime", csehAddTime);return this;};
	 	/** and cseh_add_time is null */
 		public M csehAddTimeNull(){if(this.get("csehAddTimeNot")==null)this.put("csehAddTimeNot", "");this.put("csehAddTime", null);return this;};
 		/** not .... */
 		public M csehAddTimeNot(){this.put("csehAddTimeNot", "not");return this;};
 		/** and cseh_add_time >= ? */
 		public M csehAddTimeStart(Object start){this.put("csehAddTimeStart", start);return this;};			
 		/** and cseh_add_time <= ? */
 		public M csehAddTimeEnd(Object end){this.put("csehAddTimeEnd", end);return this;};
		/** 接口数据        **/
		public M csehData(Object csehData){this.put("csehData", csehData);return this;};
	 	/** and cseh_data is null */
 		public M csehDataNull(){if(this.get("csehDataNot")==null)this.put("csehDataNot", "");this.put("csehData", null);return this;};
 		/** not .... */
 		public M csehDataNot(){this.put("csehDataNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有电桩历史数据 **/
		public @api List<CsElecHistory> list(Integer size){
			return getCsElecHistoryList(this,size);
		}
		/** 获取电桩历史数据分页 **/
		public @api Page<CsElecHistory> page(int page,int size){
			return getCsElecHistoryPage(page, size , this);
		}
		/** 根据查询条件取电桩历史数据 **/
		public @api CsElecHistory get(){
			return getCsElecHistory(this);
		}
		/** 获取电桩历史数据数 **/
		public @api Long count(){
			return getCsElecHistoryCount(this);
		}
		/** 获取电桩历史数据表达式 **/
		public @api <T> T eval(String strEval){
			return getCsElecHistoryEval(strEval,this);
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
			updateCsElecHistory(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csehId="csehId";
		/** 设备ID [非空]       **/
		public final static @type(String.class) @like String csehDid="csehDid";
		/** 所属电桩  [CsPowerPile]      **/
		public final static @type(Long.class)  String csehPowerPile="csehPowerPile";
		/** 状态编码        **/
		public final static @type(String.class) @like String csehStatusCode="csehStatusCode";
		/** 状态描述        **/
		public final static @type(String.class) @like String csehStatusText="csehStatusText";
		/** 充电量        **/
		public final static @type(String.class) @like String csehEleAmount="csehEleAmount";
		/** 充电时长        **/
		public final static @type(String.class) @like String csehTimeLong="csehTimeLong";
		/** 错误码        **/
		public final static @type(String.class) @like String csehErrorCode="csehErrorCode";
		/** 数据时间        **/
		public final static @type(Date.class)  String csehCollectTime="csehCollectTime";
	 	/** and cseh_collect_time >= ? */
 		public final static @type(Date.class) String csehCollectTimeStart="csehCollectTimeStart";
 		/** and cseh_collect_time <= ? */
 		public final static @type(Date.class) String csehCollectTimeEnd="csehCollectTimeEnd";
		/** 添加时间        **/
		public final static @type(Date.class)  String csehAddTime="csehAddTime";
	 	/** and cseh_add_time >= ? */
 		public final static @type(Date.class) String csehAddTimeStart="csehAddTimeStart";
 		/** and cseh_add_time <= ? */
 		public final static @type(Date.class) String csehAddTimeEnd="csehAddTimeEnd";
		/** 接口数据        **/
		public final static @type(String.class) @like String csehData="csehData";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csehId="cseh_id";
		/** 设备ID [非空]       **/
		public final static String csehDid="cseh_did";
		/** 所属电桩  [CsPowerPile]      **/
		public final static String csehPowerPile="cseh_power_pile";
		/** 状态编码        **/
		public final static String csehStatusCode="cseh_status_code";
		/** 状态描述        **/
		public final static String csehStatusText="cseh_status_text";
		/** 充电量        **/
		public final static String csehEleAmount="cseh_ele_amount";
		/** 充电时长        **/
		public final static String csehTimeLong="cseh_time_long";
		/** 错误码        **/
		public final static String csehErrorCode="cseh_error_code";
		/** 数据时间        **/
		public final static String csehCollectTime="cseh_collect_time";
		/** 添加时间        **/
		public final static String csehAddTime="cseh_add_time";
		/** 接口数据        **/
		public final static String csehData="cseh_data";
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
				$.Set(name,CsElecHistory.getCsElecHistory(params));
			else
				$.Set(name,CsElecHistory.getCsElecHistoryList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取电桩历史数据数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsElecHistory) $.GetRequest("CsElecHistory$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsElecHistory.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsElecHistory.getCsElecHistory(params);
			else
				value = CsElecHistory.getCsElecHistoryList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsElecHistory.Get($.add(CsElecHistory.F.csehId,param));
		else if(!$.empty(param.toString()))
			value = CsElecHistory.get(Long.valueOf(param.toString()));
		$.SetRequest("CsElecHistory$"+param.hashCode(), value);
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
	public void mergeSet(CsElecHistory old){
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