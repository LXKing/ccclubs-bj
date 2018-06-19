package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.CsSpecialCar.M;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("user/coin")
public @caption("红包") @table("cs_coin") class CsCoin implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csc_id")   @primary   @note("  ") Long cscId;
	private @caption("城市") @column("csc_host")    @relate("$cscHost") @RelateClass(SrvHost.class)  @note("  ") Long cscHost;
	private SrvHost $cscHost;//关联对象[城市]
	private @caption("所属会员") @column("csc_member")    @relate("$cscMember") @RelateClass(CsMember.class)  @note("  ") Long cscMember;
	private CsMember $cscMember;//关联对象[所属会员]
	private @caption("充值金额") @column("csc_count")      @note("  ") Double cscCount;
	private @caption("可用金额") @column("csc_remain")      @note("  ") Double cscRemain;
	private @caption("原始有效期") @column("csc_validity")      @note(" 1:1个月 2:2个月 3:3个月 4:4个月 5:5个月 6:半年 12:1年 0:自定义  ") Short cscValidity;
	private @caption("程序标识") @column("csc_flag")      @note("  ") String cscFlag;
	private @caption("结束时间") @column("csc_end")      @note("  ") Date cscEnd;
	private @caption("序列号") @column("csc_serial")     @hidden @note("  ") String cscSerial;
	private @caption("充值人员") @column("csc_editor")    @relate("$cscEditor") @RelateClass(SrvUser.class)  @note("  ") Long cscEditor;
	private SrvUser $cscEditor;//关联对象[充值人员]
	private @caption("修改时间") @column("csc_update_time")      @note("  ") Date cscUpdateTime;
	private @caption("添加时间") @column("csc_add_time")      @note("  ") Date cscAddTime;
	private @caption("备注信息") @column("csc_remark")      @note("  ") String cscRemark;
	private @caption("状态") @column("csc_status")      @note(" 1:正常 2:已过期 3:用完  ") Short cscStatus;
	private @caption("红包来源") @column("csc_coin_source")  @hidden    @relate("$cscCoinSource") @RelateClass(CsCoinSource.class)  @note("") Long cscCoinSource;
	private CsCoinSource $cscCoinSource;//关联对象[红包来源]
	
	//默认构造函数
	public CsCoin(){
	
	}
	
	//主键构造函数
	public CsCoin(Long id){
		this.cscId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCoin(Long cscHost,Long cscMember,Double cscCount,Double cscRemain,Short cscValidity,String cscFlag,Date cscEnd,String cscSerial,Long cscEditor,Date cscUpdateTime,Date cscAddTime,String cscRemark,Short cscStatus,Long cscCoinSource){
		this.cscHost=cscHost;
		this.cscMember=cscMember;
		this.cscCount=cscCount;
		this.cscRemain=cscRemain;
		this.cscValidity=cscValidity;
		this.cscFlag=cscFlag;
		this.cscEnd=cscEnd;
		this.cscSerial=cscSerial;
		this.cscEditor=cscEditor;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscRemark=cscRemark;
		this.cscStatus=cscStatus;
		this.cscCoinSource=cscCoinSource;
	}
	
	//设置非空字段
	public CsCoin setNotNull(Long cscCoinSource){
		this.cscCoinSource=cscCoinSource;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsCoin cscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsCoin cscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
		return this;
	}
	/** 所属会员 [非空]       **/
	public CsCoin cscMember(Long cscMember){
		this.cscMember = cscMember;
		this.setSeted(F.cscMember);
		return this;
	}
	/** 充值币数 [非空]       **/
	public CsCoin cscCount(Double cscCount){
		this.cscCount = cscCount;
		this.setSeted(F.cscCount);
		return this;
	}
	/** 可用币数 [非空]       **/
	public CsCoin cscRemain(Double cscRemain){
		this.cscRemain = cscRemain;
		this.setSeted(F.cscRemain);
		return this;
	}
	/** 有效期 [非空]       **/
	public CsCoin cscValidity(Short cscValidity){
		this.cscValidity = cscValidity;
		this.setSeted(F.cscValidity);
		return this;
	}
	/** 程序标识 [非空]       **/
	public CsCoin cscFlag(String cscFlag){
		this.cscFlag = cscFlag;
		this.setSeted(F.cscFlag);
		return this;
	}
	/** 结束时间 [非空]       **/
	public CsCoin cscEnd(Date cscEnd){
		this.cscEnd = cscEnd;
		this.setSeted(F.cscEnd);
		return this;
	}
	/** 序列号 [非空]       **/
	public CsCoin cscSerial(String cscSerial){
		this.cscSerial = cscSerial;
		this.setSeted(F.cscSerial);
		return this;
	}
	/** 充值人员 [非空]       **/
	public CsCoin cscEditor(Long cscEditor){
		this.cscEditor = cscEditor;
		this.setSeted(F.cscEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCoin cscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCoin cscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
		return this;
	}
	/** 备注信息 [非空]       **/
	public CsCoin cscRemark(String cscRemark){
		this.cscRemark = cscRemark;
		this.setSeted(F.cscRemark);
		return this;
	}
	/** 状态 [非空]       **/
	public CsCoin cscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
		return this;
	}
	/** 红包来源 [可空]       **/
	public CsCoin cscCoinSource(Long cscCoinSource){
		this.cscCoinSource = cscCoinSource;
		this.setSeted(F.cscCoinSource);
		return this;
	}
	
	//克隆对象
	public CsCoin clone(){
		CsCoin clone = new CsCoin();
		clone.cscId=this.cscId;
		clone.cscHost=this.cscHost;
		clone.cscMember=this.cscMember;
		clone.cscCount=this.cscCount;
		clone.cscRemain=this.cscRemain;
		clone.cscValidity=this.cscValidity;
		clone.cscFlag=this.cscFlag;
		clone.cscEnd=this.cscEnd;
		clone.cscSerial=this.cscSerial;
		clone.cscEditor=this.cscEditor;
		clone.cscUpdateTime=this.cscUpdateTime;
		clone.cscAddTime=this.cscAddTime;
		clone.cscRemark=this.cscRemark;
		clone.cscStatus=this.cscStatus;
		clone.cscCoinSource=this.cscCoinSource;
		return clone;
	}
	
	
	/**
	 * 根据ID取红包
	 * @param id
	 * @return
	 */
	public static @api CsCoin get(Long id){		
		return getCsCoinById(id);
	}
	/**
	 * 获取所有红包
	 * @return
	 */
	public static @api List<CsCoin> list(Map params,Integer size){
		return getCsCoinList(params,size);
	}
	/**
	 * 获取红包分页
	 * @return
	 */
	public static @api Page<CsCoin> page(int page,int size,Map params){
		return getCsCoinPage(page, size , params);
	}
	/**
	 * 根据查询条件取红包
	 * @param params
	 * @return
	 */
	public static @api CsCoin Get(Map params){
		return getCsCoin(params);
	}
	/**
	 * 获取红包数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCoinCount(params);
	}
	/**
	 * 获取红包数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCoinEval(eval,params);
	}
	
	/**
	 * 根据ID取红包
	 * @param id
	 * @return
	 */
	public static @api CsCoin getCsCoinById(Long id){		
		CsCoin csCoin = (CsCoin) $.GetRequest("CsCoin$"+id);
		if(csCoin!=null)
			return csCoin;
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");		
		return csCoinService.getCsCoinById(id);
	}
	
	
	/**
	 * 根据ID取红包的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCoin.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCoin csCoin = get(id);
		if(csCoin!=null){
			String strValue = csCoin.getCscCoinSource$();
			if(!"CscId".equals("CscId"))
				strValue+="("+csCoin.getCscId$()+")";
			MemCache.setValue(CsCoin.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscCoinSource$();
		if(!"CscId".equals("CscId"))
			keyValue+="("+this.getCscCoinSource$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有红包
	 * @return
	 */
	public static @api List<CsCoin> getCsCoinList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		return csCoinService.getCsCoinList(params, size);
	}
	
	/**
	 * 获取红包分页
	 * @return
	 */
	public static @api Page<CsCoin> getCsCoinPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		return csCoinService.getCsCoinPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取红包
	 * @param params
	 * @return
	 */
	public static @api CsCoin getCsCoin(Map params){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		return csCoinService.getCsCoin(params);
	}
	
	/**
	 * 获取红包数
	 * @return
	 */
	public static @api Long getCsCoinCount(Map params){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		return csCoinService.getCsCoinCount(params);
	}
		
		
	/**
	 * 获取红包自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCoinEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		return csCoinService.getCsCoinEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCoin(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		csCoinService.updateCsCoinByConfirm(set, where);
	}
	
	
	/**
	 * 保存红包对象
	 * @param params
	 * @return
	 */
	public CsCoin save(){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		if(this.getCscId()!=null)
			csCoinService.updateCsCoin(this);
		else
			return csCoinService.saveCsCoin(this);
		return this;
	}
	
	
	/**
	 * 更新红包对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		csCoinService.updateCsCoin$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		if($.equals($.config("logic_delete"),"true"))
			csCoinService.removeCsCoinById(this.getCscId());
		else
			csCoinService.deleteCsCoinById(this.getCscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		return csCoinService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscId(){
		return this.cscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscId$(){
		String strValue="";
		 strValue=$.str(this.getCscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCscHost(){
		return this.cscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscHost$(){
		String strValue="";
		if(this.getCscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$cscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscHost()!=null){
			srvHost = SrvHost.get(this.getCscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空]      
	**/
	public Long getCscMember(){
		return this.cscMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCscMember$(){
		String strValue="";
		if(this.getCscMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCscMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空]     
	**/
	public void setCscMember(Long cscMember){
		this.cscMember = cscMember;
		this.setSeted(F.cscMember);
	}
	/**
	* 获取关联对象[所属会员]
	**/	 			
 	public CsMember get$cscMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCscMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCscMember()!=null){
			csMember = CsMember.get(this.getCscMember());
 		}
 		$.SetRequest("CsMember$"+this.getCscMember(), csMember);
	 	return csMember;
	}
	/*******************************充值币数**********************************/	
	/**
	* 充值币数 [非空]      
	**/
	public Double getCscCount(){
		return this.cscCount;
	}
	/**
	* 获取充值币数格式化(toString)
	**/
	public String getCscCount$(){
		String strValue="";
		 strValue=$.str(this.getCscCount());
	 	 return strValue;
	}
	/**
	* 充值币数 [非空]     
	**/
	public void setCscCount(Double cscCount){
		this.cscCount = cscCount;
		this.setSeted(F.cscCount);
	}
	/*******************************可用币数**********************************/	
	/**
	* 可用币数 [非空]      
	**/
	public Double getCscRemain(){
		return this.cscRemain;
	}
	/**
	* 获取可用币数格式化(toString)
	**/
	public String getCscRemain$(){
		String strValue="";
		 strValue=$.str(this.getCscRemain());
	 	 return strValue;
	}
	/**
	* 可用币数 [非空]     
	**/
	public void setCscRemain(Double cscRemain){
		this.cscRemain = cscRemain;
		this.setSeted(F.cscRemain);
	}
	/*******************************有效期**********************************/	
	/**
	* 有效期 [非空]      
	**/
	public Short getCscValidity(){
		return this.cscValidity;
	}
	/**
	* 获取有效期格式化(toString)
	**/
	public String getCscValidity$(){
		String strValue="";
		 if($.equals($.str(this.getCscValidity()),"5"))
			 strValue=$.str("5个月");
		 if($.equals($.str(this.getCscValidity()),"4"))
			 strValue=$.str("4个月");
		 if($.equals($.str(this.getCscValidity()),"3"))
			 strValue=$.str("3个月");
		 if($.equals($.str(this.getCscValidity()),"2"))
			 strValue=$.str("2个月");
		 if($.equals($.str(this.getCscValidity()),"1"))
			 strValue=$.str("1个月");
		 if($.equals($.str(this.getCscValidity()),"0"))
			 strValue=$.str("自定义");
		 if($.equals($.str(this.getCscValidity()),"6"))
			 strValue=$.str("半年");
		 if($.equals($.str(this.getCscValidity()),"12"))
			 strValue=$.str("1年");
	 	 return strValue;
	}
	/**
	* 有效期 [非空]     
	**/
	public void setCscValidity(Short cscValidity){
		this.cscValidity = cscValidity;
		this.setSeted(F.cscValidity);
	}
	/*******************************程序标识**********************************/	
	/**
	* 程序标识 [非空]      
	**/
	public String getCscFlag(){
		return this.cscFlag;
	}
	/**
	* 获取程序标识格式化(toString)
	**/
	public String getCscFlag$(){
		String strValue="";
		 strValue=$.str(this.getCscFlag());
	 	 return strValue;
	}
	/**
	* 程序标识 [非空]     
	**/
	public void setCscFlag(String cscFlag){
		this.cscFlag = cscFlag;
		this.setSeted(F.cscFlag);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间 [非空]      
	**/
	public Date getCscEnd(){
		return this.cscEnd;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCscEnd$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscEnd(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间 [非空]     
	**/
	public void setCscEnd(Date cscEnd){
		this.cscEnd = cscEnd;
		this.setSeted(F.cscEnd);
	}
	/*******************************序列号**********************************/	
	/**
	* 序列号 [非空]      
	**/
	public String getCscSerial(){
		return this.cscSerial;
	}
	/**
	* 获取序列号格式化(toString)
	**/
	public String getCscSerial$(){
		String strValue="";
		 strValue=$.str(this.getCscSerial());
	 	 return strValue;
	}
	/**
	* 序列号 [非空]     
	**/
	public void setCscSerial(String cscSerial){
		this.cscSerial = cscSerial;
		this.setSeted(F.cscSerial);
	}
	/*******************************充值人员**********************************/	
	/**
	* 充值人员 [非空]      
	**/
	public Long getCscEditor(){
		return this.cscEditor;
	}
	/**
	* 获取充值人员格式化(toString)
	**/
	public String getCscEditor$(){
		String strValue="";
		if(this.getCscEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 充值人员 [非空]     
	**/
	public void setCscEditor(Long cscEditor){
		this.cscEditor = cscEditor;
		this.setSeted(F.cscEditor);
	}
	/**
	* 获取关联对象[充值人员]
	**/	 			
 	public SrvUser get$cscEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscEditor()!=null){
			srvUser = SrvUser.get(this.getCscEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCscEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscUpdateTime(){
		return this.cscUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscAddTime(){
		return this.cscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息 [非空]      
	**/
	public String getCscRemark(){
		return this.cscRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCscRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscRemark());
	 	 return strValue;
	}
	/**
	* 备注信息 [非空]     
	**/
	public void setCscRemark(String cscRemark){
		this.cscRemark = cscRemark;
		this.setSeted(F.cscRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCscStatus(){
		return this.cscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscStatus$(){
		String strValue="";
		 strValue=$.str(this.getCscStatus());
		 if($.equals($.str(this.getCscStatus()),"1"))
				strValue=$.str("正常");		 
			 if($.equals($.str(this.getCscStatus()),"2"))
				strValue=$.str("已过期");		 
			 if($.equals($.str(this.getCscStatus()),"3"))
				strValue=$.str("用完");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
	}
	/*******************************红包来源**********************************/	
	/**
	* 红包来源 [可空]      
	**/
	public Long getCscCoinSource(){
		return this.cscCoinSource;
	}
	/**
	* 获取红包来源格式化(toString)
	**/
	public String getCscCoinSource$(){
		String strValue="";
		if(this.getCscCoinSource()!=null){
				strValue+=$.str(CsCoinSource.getKeyValue(this.getCscCoinSource()));
		}			
	 	 return strValue;
	}
	/**
	* 红包来源 [可空]     
	**/
	public void setCscCoinSource(Long cscCoinSource){
		this.cscCoinSource = cscCoinSource;
		this.setSeted(F.cscCoinSource);
	}
	/**
	* 获取关联对象[红包来源]
	**/	 			
 	public CsCoinSource get$cscCoinSource(){
 		com.ccclubs.model.CsCoinSource csCoinSource = (com.ccclubs.model.CsCoinSource) $.GetRequest("CsCoinSource$"+this.getCscCoinSource());
 		if(csCoinSource!=null)
			return csCoinSource;
		if(this.getCscCoinSource()!=null){
			csCoinSource = CsCoinSource.get(this.getCscCoinSource());
 		}
 		$.SetRequest("CsCoinSource$"+this.getCscCoinSource(), csCoinSource);
	 	return csCoinSource;
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCoin.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCoin.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCoin.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCoin.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCoin.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCoin.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCoin.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCoin.this);
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
		public M cscId(Object cscId){this.put("cscId", cscId);return this;};
		/** and csc_id is null */
		public M cscIdNull(){if(this.get("cscIdNot")==null)this.put("cscIdNot", "");this.put("cscId", null);return this;};
		/** not .... */
		public M cscIdNot(){this.put("cscIdNot", "not");return this;};
		/** and csc_id >= ? */
		public M cscIdMin(Object min){this.put("cscIdMin", min);return this;};
		/** and csc_id <= ? */
		public M cscIdMax(Object max){this.put("cscIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M cscHost(Object cscHost){this.put("cscHost", cscHost);return this;};
		/** and csc_host is null */
		public M cscHostNull(){if(this.get("cscHostNot")==null)this.put("cscHostNot", "");this.put("cscHost", null);return this;};
		/** not .... */
		public M cscHostNot(){this.put("cscHostNot", "not");return this;};
		/** and csc_host >= ? */
		public M cscHostMin(Object min){this.put("cscHostMin", min);return this;};
		/** and csc_host <= ? */
		public M cscHostMax(Object max){this.put("cscHostMax", max);return this;};
		/** 所属会员 [非空]       **/
		public M cscMember(Object cscMember){this.put("cscMember", cscMember);return this;};
		/** and csc_member is null */
		public M cscMemberNull(){if(this.get("cscMemberNot")==null)this.put("cscMemberNot", "");this.put("cscMember", null);return this;};
		/** not .... */
		public M cscMemberNot(){this.put("cscMemberNot", "not");return this;};
		public M cscMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cscMember$on", value);
			return this;
		};	
		/** and csc_member >= ? */
		public M cscMemberMin(Object min){this.put("cscMemberMin", min);return this;};
		/** and csc_member <= ? */
		public M cscMemberMax(Object max){this.put("cscMemberMax", max);return this;};
		/** 充值币数 [非空]       **/
		public M cscCount(Object cscCount){this.put("cscCount", cscCount);return this;};
		/** and csc_count is null */
		public M cscCountNull(){if(this.get("cscCountNot")==null)this.put("cscCountNot", "");this.put("cscCount", null);return this;};
		/** not .... */
		public M cscCountNot(){this.put("cscCountNot", "not");return this;};
		/** and csc_count >= ? */
		public M cscCountMin(Object min){this.put("cscCountMin", min);return this;};
		/** and csc_count <= ? */
		public M cscCountMax(Object max){this.put("cscCountMax", max);return this;};
		/** 可用币数 [非空]       **/
		public M cscRemain(Object cscRemain){this.put("cscRemain", cscRemain);return this;};
		/** and csc_remain is null */
		public M cscRemainNull(){if(this.get("cscRemainNot")==null)this.put("cscRemainNot", "");this.put("cscRemain", null);return this;};
		/** not .... */
		public M cscRemainNot(){this.put("cscRemainNot", "not");return this;};
		/** and csc_remain >= ? */
		public M cscRemainMin(Object min){this.put("cscRemainMin", min);return this;};
		/** and csc_remain <= ? */
		public M cscRemainMax(Object max){this.put("cscRemainMax", max);return this;};
		/** 有效期 [非空]       **/
		public M cscValidity(Object cscValidity){this.put("cscValidity", cscValidity);return this;};
		/** and csc_validity is null */
		public M cscValidityNull(){if(this.get("cscValidityNot")==null)this.put("cscValidityNot", "");this.put("cscValidity", null);return this;};
		/** not .... */
		public M cscValidityNot(){this.put("cscValidityNot", "not");return this;};
		/** and csc_validity >= ? */
		public M cscValidityMin(Object min){this.put("cscValidityMin", min);return this;};
		/** and csc_validity <= ? */
		public M cscValidityMax(Object max){this.put("cscValidityMax", max);return this;};
		/** 程序标识 [非空]       **/
		public M cscFlag(Object cscFlag){this.put("cscFlag", cscFlag);return this;};
		/** and csc_flag is null */
		public M cscFlagNull(){if(this.get("cscFlagNot")==null)this.put("cscFlagNot", "");this.put("cscFlag", null);return this;};
		/** not .... */
		public M cscFlagNot(){this.put("cscFlagNot", "not");return this;};
		/** 结束时间 [非空]       **/
		public M cscEnd(Object cscEnd){this.put("cscEnd", cscEnd);return this;};
		/** and csc_end is null */
		public M cscEndNull(){if(this.get("cscEndNot")==null)this.put("cscEndNot", "");this.put("cscEnd", null);return this;};
		/** not .... */
		public M cscEndNot(){this.put("cscEndNot", "not");return this;};
		/** and csc_end >= ? */
 		public M cscEndStart(Object start){this.put("cscEndStart", start);return this;};			
 		/** and csc_end <= ? */
 		public M cscEndEnd(Object end){this.put("cscEndEnd", end);return this;};
		/** 序列号 [非空]       **/
		public M cscSerial(Object cscSerial){this.put("cscSerial", cscSerial);return this;};
		/** and csc_serial is null */
		public M cscSerialNull(){if(this.get("cscSerialNot")==null)this.put("cscSerialNot", "");this.put("cscSerial", null);return this;};
		/** not .... */
		public M cscSerialNot(){this.put("cscSerialNot", "not");return this;};
		/** 充值人员 [非空]       **/
		public M cscEditor(Object cscEditor){this.put("cscEditor", cscEditor);return this;};
		/** and csc_editor is null */
		public M cscEditorNull(){if(this.get("cscEditorNot")==null)this.put("cscEditorNot", "");this.put("cscEditor", null);return this;};
		/** not .... */
		public M cscEditorNot(){this.put("cscEditorNot", "not");return this;};
		public M cscEditor$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("cscEditor$on", value);
			return this;
		};	
		/** and csc_editor >= ? */
		public M cscEditorMin(Object min){this.put("cscEditorMin", min);return this;};
		/** and csc_editor <= ? */
		public M cscEditorMax(Object max){this.put("cscEditorMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M cscUpdateTime(Object cscUpdateTime){this.put("cscUpdateTime", cscUpdateTime);return this;};
		/** and csc_update_time is null */
		public M cscUpdateTimeNull(){if(this.get("cscUpdateTimeNot")==null)this.put("cscUpdateTimeNot", "");this.put("cscUpdateTime", null);return this;};
		/** not .... */
		public M cscUpdateTimeNot(){this.put("cscUpdateTimeNot", "not");return this;};
		/** and csc_update_time >= ? */
 		public M cscUpdateTimeStart(Object start){this.put("cscUpdateTimeStart", start);return this;};			
 		/** and csc_update_time <= ? */
 		public M cscUpdateTimeEnd(Object end){this.put("cscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscAddTime(Object cscAddTime){this.put("cscAddTime", cscAddTime);return this;};
		/** and csc_add_time is null */
		public M cscAddTimeNull(){if(this.get("cscAddTimeNot")==null)this.put("cscAddTimeNot", "");this.put("cscAddTime", null);return this;};
		/** not .... */
		public M cscAddTimeNot(){this.put("cscAddTimeNot", "not");return this;};
		/** and csc_add_time >= ? */
 		public M cscAddTimeStart(Object start){this.put("cscAddTimeStart", start);return this;};			
 		/** and csc_add_time <= ? */
 		public M cscAddTimeEnd(Object end){this.put("cscAddTimeEnd", end);return this;};
		/** 备注信息 [非空]       **/
		public M cscRemark(Object cscRemark){this.put("cscRemark", cscRemark);return this;};
		/** and csc_remark is null */
		public M cscRemarkNull(){if(this.get("cscRemarkNot")==null)this.put("cscRemarkNot", "");this.put("cscRemark", null);return this;};
		/** not .... */
		public M cscRemarkNot(){this.put("cscRemarkNot", "not");return this;};
		/** 状态 [非空]       **/
		public M cscStatus(Object cscStatus){this.put("cscStatus", cscStatus);return this;};
		/** and csc_status is null */
		public M cscStatusNull(){if(this.get("cscStatusNot")==null)this.put("cscStatusNot", "");this.put("cscStatus", null);return this;};
		/** not .... */
		public M cscStatusNot(){this.put("cscStatusNot", "not");return this;};
		/** and csc_status >= ? */
		public M cscStatusMin(Object min){this.put("cscStatusMin", min);return this;};
		/** and csc_status <= ? */
		public M cscStatusMax(Object max){this.put("cscStatusMax", max);return this;};
		/** 红包来源 [可空]       **/
		public M cscCoinSource(Object cscCoinSource){this.put("cscCoinSource", cscCoinSource);return this;};
		/** and csc_coin_source is null */
		public M cscCoinSourceNull(){if(this.get("cscCoinSourceNot")==null)this.put("cscCoinSourceNot", "");this.put("cscCoinSource", null);return this;};
		/** not .... */
		public M cscCoinSourceNot(){this.put("cscCoinSourceNot", "not");return this;};
		public M cscCoinSource$on(CsCoinSource.M value){
			this.put("CsCoinSource", value);
			this.put("cscCoinSource$on", value);
			return this;
		};	
		/** and csc_coin_source >= ? */
		public M cscCoinSourceMin(Object min){this.put("cscCoinSourceMin", min);return this;};
		/** and csc_coin_source <= ? */
		public M cscCoinSourceMax(Object max){this.put("cscCoinSourceMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有红包 **/
		public @api List<CsCoin> list(Integer size){
			return getCsCoinList(this,size);
		}
		/** 获取红包分页 **/
		public @api Page<CsCoin> page(int page,int size){
			return getCsCoinPage(page, size , this);
		}
		/** 根据查询条件取红包 **/
		public @api CsCoin get(){
			return getCsCoin(this);
		}
		/** 获取红包数 **/
		public @api Long count(){
			return getCsCoinCount(this);
		}
		/** 获取红包表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCoinEval(strEval,this);
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
			updateCsCoin(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String cscHost="cscHost";
		/** 所属会员 [非空]       **/
		public final static @type(Long.class)  String cscMember="cscMember";
		/** 充值币数 [非空]       **/
		public final static @type(Double.class)  String cscCount="cscCount";
		/** 可用币数 [非空]       **/
		public final static @type(Double.class)  String cscRemain="cscRemain";
		/** 有效期 [非空]       **/
		public final static @type(Short.class)  String cscValidity="cscValidity";
		/** 程序标识 [非空]       **/
		public final static @type(String.class)  String cscFlag="cscFlag";
		/** 结束时间 [非空]       **/
		public final static @type(Date.class)  String cscEnd="cscEnd";
		/** and csc_end >= ? */
 		public final static @type(Date.class) String cscEndStart="cscEndStart";
 		/** and csc_end <= ? */
 		public final static @type(Date.class) String cscEndEnd="cscEndEnd";
		/** 序列号 [非空]       **/
		public final static @type(String.class)  String cscSerial="cscSerial";
		/** 充值人员 [非空]       **/
		public final static @type(Long.class)  String cscEditor="cscEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscUpdateTime="cscUpdateTime";
		/** and csc_update_time >= ? */
 		public final static @type(Date.class) String cscUpdateTimeStart="cscUpdateTimeStart";
 		/** and csc_update_time <= ? */
 		public final static @type(Date.class) String cscUpdateTimeEnd="cscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscAddTime="cscAddTime";
		/** and csc_add_time >= ? */
 		public final static @type(Date.class) String cscAddTimeStart="cscAddTimeStart";
 		/** and csc_add_time <= ? */
 		public final static @type(Date.class) String cscAddTimeEnd="cscAddTimeEnd";
		/** 备注信息 [非空]       **/
		public final static @type(String.class)  String cscRemark="cscRemark";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String cscStatus="cscStatus";
		/** 红包来源 [可空]       **/
		public final static @type(Long.class)  String cscCoinSource="cscCoinSource";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscId="csc_id";
		/** 城市 [非空]       **/
		public final static String cscHost="csc_host";
		/** 所属会员 [非空]       **/
		public final static String cscMember="csc_member";
		/** 充值币数 [非空]       **/
		public final static String cscCount="csc_count";
		/** 可用币数 [非空]       **/
		public final static String cscRemain="csc_remain";
		/** 有效期 [非空]       **/
		public final static String cscValidity="csc_validity";
		/** 程序标识 [非空]       **/
		public final static String cscFlag="csc_flag";
		/** 结束时间 [非空]       **/
		public final static String cscEnd="csc_end";
		/** 序列号 [非空]       **/
		public final static String cscSerial="csc_serial";
		/** 充值人员 [非空]       **/
		public final static String cscEditor="csc_editor";
		/** 修改时间 [非空]       **/
		public final static String cscUpdateTime="csc_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscAddTime="csc_add_time";
		/** 备注信息 [非空]       **/
		public final static String cscRemark="csc_remark";
		/** 状态 [非空]       **/
		public final static String cscStatus="csc_status";
		/** 红包来源 [可空]       **/
		public final static String cscCoinSource="csc_coin_source";
		
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
				$.Set(name,CsCoin.getCsCoin(params));
			else
				$.Set(name,CsCoin.getCsCoinList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取红包数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCoin) $.GetRequest("CsCoin$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCoin.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCoin.getCsCoin(params);
			else
				value = CsCoin.getCsCoinList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCoin.Get($.add(CsCoin.F.cscId,param));
		else if(!$.empty(param.toString()))
			value = CsCoin.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCoin$"+param.hashCode(), value);
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
	public void mergeSet(CsCoin old){
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