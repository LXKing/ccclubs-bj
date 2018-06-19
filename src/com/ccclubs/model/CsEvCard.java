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

@namespace("object/evcard")
public @caption("会员卡") @table("cs_ev_card") class CsEvCard implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csec_id")   @primary  @note("  ") Long csecId;// 主键 非空     
	private @caption("城市") @column("csec_host")    @relate("$csecHost") @RelateClass(SrvHost.class)  @note("  ") Long csecHost;// 非空     
 	private SrvHost $csecHost;//关联对象[运营城市]
	private @caption("卡号") @column("csec_number")    @note("  ") String csecNumber;// 非空     
	private @caption("机器号") @column("csec_rfid")    @note("  ") String csecRfid;// 非空     
	private @caption("备注") @column("csec_remark")    @note("  ") String csecRemark;//     
	private @caption("添加时间") @column("csec_add_time")    @note("  ") Date csecAddTime;// 非空     
	private @caption("已绑定？") @column("csec_flag")  @hidden   @note(" 0:未绑定 1:已绑定  ") Short csecFlag;// 非空 0:未绑定 1:已绑定     
	private @caption("状态") @column("csec_status")    @note(" 1:正常 2:挂失 0:无效  ") Short csecStatus;// 非空 1:正常 2:挂失 0:无效     
	
	//默认构造函数
	public CsEvCard(){
	
	}
	
	//主键构造函数
	public CsEvCard(Long id){
		this.csecId = id;
	}
	
	/**所有字段构造函数 CsEvCard(csecHost,csecNumber,csecRfid,csecRemark,csecAddTime,csecFlag,csecStatus)
	 CsEvCard(
	 	$.getLong("csecHost")//城市 [非空]
	 	,$.getString("csecNumber")//卡号 [非空]
	 	,$.getString("csecRfid")//机器号 [非空]
	 	,$.getString("csecRemark")//备注
	 	,$.getDate("csecAddTime")//添加时间 [非空]
	 	,$.getShort("csecFlag")//已绑定？ [非空]
	 	,$.getShort("csecStatus")//状态 [非空]
	 )
	**/
	public CsEvCard(Long csecHost,String csecNumber,String csecRfid,String csecRemark,Date csecAddTime,Short csecFlag,Short csecStatus){
		this.csecHost=csecHost;
		this.csecNumber=csecNumber;
		this.csecRfid=csecRfid;
		this.csecRemark=csecRemark;
		this.csecAddTime=csecAddTime;
		this.csecFlag=csecFlag;
		this.csecStatus=csecStatus;
	}
	
	//设置非空字段
	public CsEvCard setNotNull(Long csecHost,String csecNumber,String csecRfid,Date csecAddTime,Short csecFlag,Short csecStatus){
		this.csecHost=csecHost;
		this.csecNumber=csecNumber;
		this.csecRfid=csecRfid;
		this.csecAddTime=csecAddTime;
		this.csecFlag=csecFlag;
		this.csecStatus=csecStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsEvCard csecId(Long csecId){
		this.csecId = csecId;
		this.setSeted(F.csecId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsEvCard csecHost(Long csecHost){
		this.csecHost = csecHost;
		this.setSeted(F.csecHost);
		return this;
	}
	/** 卡号 [非空]       **/
	public CsEvCard csecNumber(String csecNumber){
		this.csecNumber = csecNumber;
		this.setSeted(F.csecNumber);
		return this;
	}
	/** 机器号 [非空]       **/
	public CsEvCard csecRfid(String csecRfid){
		this.csecRfid = csecRfid;
		this.setSeted(F.csecRfid);
		return this;
	}
	/** 备注        **/
	public CsEvCard csecRemark(String csecRemark){
		this.csecRemark = csecRemark;
		this.setSeted(F.csecRemark);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsEvCard csecAddTime(Date csecAddTime){
		this.csecAddTime = csecAddTime;
		this.setSeted(F.csecAddTime);
		return this;
	}
	/** 已绑定？ [非空]   0:未绑定 1:已绑定     **/
	public CsEvCard csecFlag(Short csecFlag){
		this.csecFlag = csecFlag;
		this.setSeted(F.csecFlag);
		return this;
	}
	/** 状态 [非空]   1:正常 2:挂失 0:无效     **/
	public CsEvCard csecStatus(Short csecStatus){
		this.csecStatus = csecStatus;
		this.setSeted(F.csecStatus);
		return this;
	}
	
	
	//克隆对象
	public CsEvCard clone(){
		CsEvCard clone = new CsEvCard();
		clone.csecHost=this.csecHost;
		clone.csecNumber=this.csecNumber;
		clone.csecRfid=this.csecRfid;
		clone.csecAddTime=this.csecAddTime;
		clone.csecFlag=this.csecFlag;
		clone.csecStatus=this.csecStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员卡
	 * @param id
	 * @return
	 */
	public static @api CsEvCard get(Long id){		
		return getCsEvCardById(id);
	}
	/**
	 * 获取所有会员卡
	 * @return
	 */
	public static @api List<CsEvCard> list(Map params,Integer size){
		return getCsEvCardList(params,size);
	}
	/**
	 * 获取会员卡分页
	 * @return
	 */
	public static @api Page<CsEvCard> page(int page,int size,Map params){
		return getCsEvCardPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员卡
	 * @param params
	 * @return
	 */
	public static @api CsEvCard Get(Map params){
		return getCsEvCard(params);
	}
	/**
	 * 获取会员卡数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsEvCardCount(params);
	}
	/**
	 * 获取会员卡数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsEvCardEval(eval,params);
	}
	
	/**
	 * 根据ID取会员卡
	 * @param id
	 * @return
	 */
	public static @api CsEvCard getCsEvCardById(Long id){		
		CsEvCard csEvCard = (CsEvCard) $.GetRequest("CsEvCard$"+id);
		if(csEvCard!=null)
			return csEvCard;
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");		
		return csEvCardService.getCsEvCardById(id);
	}
	
	
	/**
	 * 根据ID取会员卡的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsEvCard.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsEvCard csEvCard = get(id);
		if(csEvCard!=null){
			String strValue = csEvCard.getCsecNumber$();
			if(!"CsecNumber".equals("CsecNumber"))
				strValue+="("+csEvCard.getCsecNumber$()+")";
			MemCache.setValue(CsEvCard.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsecNumber$();
		if(!"CsecNumber".equals("CsecNumber"))
			keyValue+="("+this.getCsecNumber$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员卡
	 * @return
	 */
	public static @api List<CsEvCard> getCsEvCardList(Map params,Integer size){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		return csEvCardService.getCsEvCardList(params, size);
	}
	
	/**
	 * 获取会员卡分页
	 * @return
	 */
	public static @api Page<CsEvCard> getCsEvCardPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		return csEvCardService.getCsEvCardPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员卡
	 * @param params
	 * @return
	 */
	public static @api CsEvCard getCsEvCard(Map params){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		return csEvCardService.getCsEvCard(params);
	}
	
	/**
	 * 获取会员卡数
	 * @return
	 */
	public static @api Long getCsEvCardCount(Map params){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		return csEvCardService.getCsEvCardCount(params);
	}
		
		
	/**
	 * 获取会员卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsEvCardEval(String eval,Map params){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		return csEvCardService.getCsEvCardEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsEvCard(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		csEvCardService.updateCsEvCardByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员卡对象
	 * @param params
	 * @return
	 */
	public CsEvCard save(){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		if(this.getCsecId()!=null)
			csEvCardService.updateCsEvCard(this);
		else
			return csEvCardService.saveCsEvCard(this);
		return this;
	}
	
	
	/**
	 * 更新会员卡对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		csEvCardService.updateCsEvCard$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		if($.equals($.config("logic_delete"),"true"))
			csEvCardService.removeCsEvCardById(this.getCsecId());
		else
			csEvCardService.deleteCsEvCardById(this.getCsecId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		return csEvCardService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsecId(){
		return this.csecId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsecId$(){
		String strValue="";
		 strValue=$.str(this.getCsecId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsecId(Long csecId){
		this.csecId = csecId;
		this.setSeted(F.csecId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsecHost(){
		return this.csecHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsecHost$(){
		String strValue="";
		if(this.getCsecHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsecHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsecHost(Long csecHost){
		this.csecHost = csecHost;
		this.setSeted(F.csecHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csecHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsecHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsecHost()!=null){
 			srvHost = SrvHost.get(this.getCsecHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsecHost(), srvHost);
	 	return srvHost;
	}
	/*******************************卡号**********************************/	
	/**
	* 卡号 [非空]      
	**/
	public String getCsecNumber(){
		return this.csecNumber;
	}
	/**
	* 获取卡号格式化(toString)
	**/
	public String getCsecNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsecNumber());
	 	 return strValue;
	}
	/**
	* 卡号 [非空]      
	**/
	public void setCsecNumber(String csecNumber){
		this.csecNumber = csecNumber;
		this.setSeted(F.csecNumber);
	}
	/*******************************机器号**********************************/	
	/**
	* 机器号 [非空]      
	**/
	public String getCsecRfid(){
		return this.csecRfid;
	}
	/**
	* 获取机器号格式化(toString)
	**/
	public String getCsecRfid$(){
		String strValue="";
		 strValue=$.str(this.getCsecRfid());
	 	 return strValue;
	}
	/**
	* 机器号 [非空]      
	**/
	public void setCsecRfid(String csecRfid){
		this.csecRfid = csecRfid;
		this.setSeted(F.csecRfid);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsecRemark(){
		return this.csecRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsecRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsecRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsecRemark(String csecRemark){
		this.csecRemark = csecRemark;
		this.setSeted(F.csecRemark);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsecAddTime(){
		return this.csecAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsecAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsecAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsecAddTime(Date csecAddTime){
		this.csecAddTime = csecAddTime;
		this.setSeted(F.csecAddTime);
	}
	/*******************************已绑定？**********************************/	
	/**
	* 已绑定？ [非空]   0:未绑定 1:已绑定    
	**/
	public Short getCsecFlag(){
		return this.csecFlag;
	}
	/**
	* 获取已绑定？格式化(toString)
	**/
	public String getCsecFlag$(){
		String strValue="";
		 if($.equals($.str(this.getCsecFlag()),"0"))
			strValue=$.str("未绑定");		 
		 if($.equals($.str(this.getCsecFlag()),"1"))
			strValue=$.str("已绑定");		 
	 	 return strValue;
	}
	/**
	* 已绑定？ [非空]   0:未绑定 1:已绑定    
	**/
	public void setCsecFlag(Short csecFlag){
		this.csecFlag = csecFlag;
		this.setSeted(F.csecFlag);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 2:挂失 0:无效    
	**/
	public Short getCsecStatus(){
		return this.csecStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsecStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsecStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsecStatus()),"2"))
			strValue=$.str("挂失");		 
		 if($.equals($.str(this.getCsecStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 2:挂失 0:无效    
	**/
	public void setCsecStatus(Short csecStatus){
		this.csecStatus = csecStatus;
		this.setSeted(F.csecStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsEvCard.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEvCard.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEvCard.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsEvCard.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsEvCard.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEvCard.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEvCard.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsEvCard.this);
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
		public M csecId(Object csecId){this.put("csecId", csecId);return this;};
	 	/** and csec_id is null */
 		public M csecIdNull(){if(this.get("csecIdNot")==null)this.put("csecIdNot", "");this.put("csecId", null);return this;};
 		/** not .... */
 		public M csecIdNot(){this.put("csecIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csecHost(Object csecHost){this.put("csecHost", csecHost);return this;};
	 	/** and csec_host is null */
 		public M csecHostNull(){if(this.get("csecHostNot")==null)this.put("csecHostNot", "");this.put("csecHost", null);return this;};
 		/** not .... */
 		public M csecHostNot(){this.put("csecHostNot", "not");return this;};
		/** 卡号 [非空]       **/
		public M csecNumber(Object csecNumber){this.put("csecNumber", csecNumber);return this;};
	 	/** and csec_number is null */
 		public M csecNumberNull(){if(this.get("csecNumberNot")==null)this.put("csecNumberNot", "");this.put("csecNumber", null);return this;};
 		/** not .... */
 		public M csecNumberNot(){this.put("csecNumberNot", "not");return this;};
		/** 机器号 [非空]       **/
		public M csecRfid(Object csecRfid){this.put("csecRfid", csecRfid);return this;};
	 	/** and csec_rfid is null */
 		public M csecRfidNull(){if(this.get("csecRfidNot")==null)this.put("csecRfidNot", "");this.put("csecRfid", null);return this;};
 		/** not .... */
 		public M csecRfidNot(){this.put("csecRfidNot", "not");return this;};
		/** 备注        **/
		public M csecRemark(Object csecRemark){this.put("csecRemark", csecRemark);return this;};
	 	/** and csec_remark is null */
 		public M csecRemarkNull(){if(this.get("csecRemarkNot")==null)this.put("csecRemarkNot", "");this.put("csecRemark", null);return this;};
 		/** not .... */
 		public M csecRemarkNot(){this.put("csecRemarkNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csecAddTime(Object csecAddTime){this.put("csecAddTime", csecAddTime);return this;};
	 	/** and csec_add_time is null */
 		public M csecAddTimeNull(){if(this.get("csecAddTimeNot")==null)this.put("csecAddTimeNot", "");this.put("csecAddTime", null);return this;};
 		/** not .... */
 		public M csecAddTimeNot(){this.put("csecAddTimeNot", "not");return this;};
 		/** and csec_add_time >= ? */
 		public M csecAddTimeStart(Object start){this.put("csecAddTimeStart", start);return this;};			
 		/** and csec_add_time <= ? */
 		public M csecAddTimeEnd(Object end){this.put("csecAddTimeEnd", end);return this;};
		/** 已绑定？ [非空]   0:未绑定 1:已绑定     **/
		public M csecFlag(Object csecFlag){this.put("csecFlag", csecFlag);return this;};
	 	/** and csec_flag is null */
 		public M csecFlagNull(){if(this.get("csecFlagNot")==null)this.put("csecFlagNot", "");this.put("csecFlag", null);return this;};
 		/** not .... */
 		public M csecFlagNot(){this.put("csecFlagNot", "not");return this;};
		/** 状态 [非空]   1:正常 2:挂失 0:无效     **/
		public M csecStatus(Object csecStatus){this.put("csecStatus", csecStatus);return this;};
	 	/** and csec_status is null */
 		public M csecStatusNull(){if(this.get("csecStatusNot")==null)this.put("csecStatusNot", "");this.put("csecStatus", null);return this;};
 		/** not .... */
 		public M csecStatusNot(){this.put("csecStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员卡 **/
		public @api List<CsEvCard> list(Integer size){
			return getCsEvCardList(this,size);
		}
		/** 获取会员卡分页 **/
		public @api Page<CsEvCard> page(int page,int size){
			return getCsEvCardPage(page, size , this);
		}
		/** 根据查询条件取会员卡 **/
		public @api CsEvCard get(){
			return getCsEvCard(this);
		}
		/** 获取会员卡数 **/
		public @api Long count(){
			return getCsEvCardCount(this);
		}
		/** 获取会员卡表达式 **/
		public @api <T> T eval(String strEval){
			return getCsEvCardEval(strEval,this);
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
			updateCsEvCard(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csecId="csecId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csecHost="csecHost";
		/** 卡号 [非空]       **/
		public final static @type(String.class) @like String csecNumber="csecNumber";
		/** 机器号 [非空]       **/
		public final static @type(String.class) @like String csecRfid="csecRfid";
		/** 备注        **/
		public final static @type(String.class) @like String csecRemark="csecRemark";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csecAddTime="csecAddTime";
	 	/** and csec_add_time >= ? */
 		public final static @type(Date.class) String csecAddTimeStart="csecAddTimeStart";
 		/** and csec_add_time <= ? */
 		public final static @type(Date.class) String csecAddTimeEnd="csecAddTimeEnd";
		/** 已绑定？ [非空]   0:未绑定 1:已绑定     **/
		public final static @type(Short.class)  String csecFlag="csecFlag";
		/** 状态 [非空]   1:正常 2:挂失 0:无效     **/
		public final static @type(Short.class)  String csecStatus="csecStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csecId="csec_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csecHost="csec_host";
		/** 卡号 [非空]       **/
		public final static String csecNumber="csec_number";
		/** 机器号 [非空]       **/
		public final static String csecRfid="csec_rfid";
		/** 备注        **/
		public final static String csecRemark="csec_remark";
		/** 添加时间 [非空]       **/
		public final static String csecAddTime="csec_add_time";
		/** 已绑定？ [非空]   0:未绑定 1:已绑定     **/
		public final static String csecFlag="csec_flag";
		/** 状态 [非空]   1:正常 2:挂失 0:无效     **/
		public final static String csecStatus="csec_status";
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
				$.Set(name,CsEvCard.getCsEvCard(params));
			else
				$.Set(name,CsEvCard.getCsEvCardList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员卡数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsEvCard) $.GetRequest("CsEvCard$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsEvCard.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsEvCard.getCsEvCard(params);
			else
				value = CsEvCard.getCsEvCardList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsEvCard.Get($.add(CsEvCard.F.csecId,param));
		else if(!$.empty(param.toString()))
			value = CsEvCard.get(Long.valueOf(param.toString()));
		$.SetRequest("CsEvCard$"+param.hashCode(), value);
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
	public void mergeSet(CsEvCard old){
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