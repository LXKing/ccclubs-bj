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

@namespace("sale/contract")
public @caption("签约合同") @table("tb_sale_contract") class TbSaleContract implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbsc_id")   @primary  @note("  ") Long tbscId;// 主键 非空     
	private @caption("城市") @column("tbsc_host")    @relate("$tbscHost") @RelateClass(SrvHost.class)  @note("  ") Long tbscHost;// 非空     
 	private SrvHost $tbscHost;//关联对象[运营城市]
	private @caption("合同名称") @column("tbsc_name")    @note("  ") String tbscName;// 非空     
	private @caption("合同编号") @column("tbsc_no")    @note("  ") String tbscNo;// 非空     
	private @caption("所属战役") @column("tbsc_battle")    @note("  ") Long tbscBattle;// 非空     
	private @caption("所属活动") @column("tbsc_event")    @relate("$tbscEvent") @RelateClass(CsSaleEvent.class)  @note("  ") Long tbscEvent;// 非空     
 	private CsSaleEvent $tbscEvent;//关联对象[销售活动]
	private @caption("所属机会") @column("tbsc_opport")    @relate("$tbscOpport") @RelateClass(CsSaleOpport.class)  @note("  ") Long tbscOpport;// 非空     
 	private CsSaleOpport $tbscOpport;//关联对象[销售机会]
	private @caption("关联报价") @column("tbsc_quote")    @relate("$tbscQuote") @RelateClass(TbSaleQuote.class)  @note("  ") Long tbscQuote;// 非空     
 	private TbSaleQuote $tbscQuote;//关联对象[跟单报价]
	private @caption("签约组织") @column("tbsc_group")    @relate("$tbscGroup") @RelateClass(TbCustomGroup.class)  @note("  ") Long tbscGroup;// 非空     
 	private TbCustomGroup $tbscGroup;//关联对象[客户组织]
	private @caption("签约客户") @column("tbsc_custom")    @relate("$tbscCustom") @RelateClass(TbSaleCustom.class)  @note("  ") Long tbscCustom;// 非空     
 	private TbSaleCustom $tbscCustom;//关联对象[客户资料]
	private @caption("我方人员") @column("tbsc_signer")    @relate("$tbscSigner") @RelateClass(SrvUser.class)  @note("  ") Long tbscSigner;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbscSigner;//关联对象[用户]
	private @caption("签约日期") @column("tbsc_sign_date")    @note("  ") Date tbscSignDate;// 非空     
	private @caption("合同开始") @column("tbsc_begin")    @note("  ") Date tbscBegin;// 非空     
	private @caption("合同结束") @column("tbsc_end")    @note("  ") Date tbscEnd;//     
	private @caption("签约金额") @column("tbsc_money")    @note("  ") Double tbscMoney;// 非空     
	private @caption("毛估利润") @column("tbsc_profit")    @note("  ") Double tbscProfit;// 非空     
	private @caption("资料文件") @column("tbsc_file")    @note("  ") String tbscFile;//     
	private @caption("所有人") @column("tbsc_onwer")    @relate("$tbscOnwer") @RelateClass(SrvUser.class)  @note("  ") String tbscOnwer;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbscOnwer;//关联对象[用户]
	private @caption("添加人") @column("tbsc_adder")    @relate("$tbscAdder") @RelateClass(SrvUser.class)  @note("  ") Long tbscAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbscAdder;//关联对象[用户]
	private @caption("修改人") @column("tbsc_editor")    @relate("$tbscEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbscEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbscEditor;//关联对象[用户]
	private @caption("备注") @column("tbsc_remark")    @note("  ") String tbscRemark;//     
	private @caption("备忘信息") @column("tbsc_memo")    @note(" textarea:content text:sign  ") String tbscMemo;// textarea:content text:sign     
	private @caption("程序数据") @column("tbsc_data")    @note("  ") String tbscData;//     
	private @caption("修改时间") @column("tbsc_update_time")    @note("  ") Date tbscUpdateTime;// 非空     
	private @caption("添加时间") @column("tbsc_add_time")    @note("  ") Date tbscAddTime;// 非空     
	private @caption("状态") @column("tbsc_status")    @note(" 1:正常 0:无效  ") Short tbscStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public TbSaleContract(){
	
	}
	
	//主键构造函数
	public TbSaleContract(Long id){
		this.tbscId = id;
	}
	
	/**所有字段构造函数 TbSaleContract(tbscHost,tbscName,tbscNo,tbscBattle,tbscEvent,tbscOpport,tbscQuote,tbscGroup,tbscCustom,tbscSigner,tbscSignDate,tbscBegin,tbscEnd,tbscMoney,tbscProfit,tbscFile,tbscOnwer,tbscAdder,tbscEditor,tbscRemark,tbscMemo,tbscData,tbscUpdateTime,tbscAddTime,tbscStatus)
	 TbSaleContract(
	 	$.getLong("tbscHost")//城市 [非空]
	 	,$.getString("tbscName")//合同名称 [非空]
	 	,$.getString("tbscNo")//合同编号 [非空]
	 	,$.getLong("tbscBattle")//所属战役 [非空]
	 	,$.getLong("tbscEvent")//所属活动 [非空]
	 	,$.getLong("tbscOpport")//所属机会 [非空]
	 	,$.getLong("tbscQuote")//关联报价 [非空]
	 	,$.getLong("tbscGroup")//签约组织 [非空]
	 	,$.getLong("tbscCustom")//签约客户 [非空]
	 	,$.getLong("tbscSigner")//我方人员 [非空]
	 	,$.getDate("tbscSignDate")//签约日期 [非空]
	 	,$.getDate("tbscBegin")//合同开始 [非空]
	 	,$.getDate("tbscEnd")//合同结束
	 	,$.getDouble("tbscMoney")//签约金额 [非空]
	 	,$.getDouble("tbscProfit")//毛估利润 [非空]
	 	,$.getString("tbscFile")//资料文件
	 	,$.getString("tbscOnwer")//所有人 [非空]
	 	,$.getLong("tbscAdder")//添加人 [非空]
	 	,$.getLong("tbscEditor")//修改人 [非空]
	 	,$.getString("tbscRemark")//备注
	 	,$.getString("tbscMemo")//备忘信息
	 	,$.getString("tbscData")//程序数据
	 	,$.getDate("tbscUpdateTime")//修改时间 [非空]
	 	,$.getDate("tbscAddTime")//添加时间 [非空]
	 	,$.getShort("tbscStatus")//状态 [非空]
	 )
	**/
	public TbSaleContract(Long tbscHost,String tbscName,String tbscNo,Long tbscBattle,Long tbscEvent,Long tbscOpport,Long tbscQuote,Long tbscGroup,Long tbscCustom,Long tbscSigner,Date tbscSignDate,Date tbscBegin,Date tbscEnd,Double tbscMoney,Double tbscProfit,String tbscFile,String tbscOnwer,Long tbscAdder,Long tbscEditor,String tbscRemark,String tbscMemo,String tbscData,Date tbscUpdateTime,Date tbscAddTime,Short tbscStatus){
		this.tbscHost=tbscHost;
		this.tbscName=tbscName;
		this.tbscNo=tbscNo;
		this.tbscBattle=tbscBattle;
		this.tbscEvent=tbscEvent;
		this.tbscOpport=tbscOpport;
		this.tbscQuote=tbscQuote;
		this.tbscGroup=tbscGroup;
		this.tbscCustom=tbscCustom;
		this.tbscSigner=tbscSigner;
		this.tbscSignDate=tbscSignDate;
		this.tbscBegin=tbscBegin;
		this.tbscEnd=tbscEnd;
		this.tbscMoney=tbscMoney;
		this.tbscProfit=tbscProfit;
		this.tbscFile=tbscFile;
		this.tbscOnwer=tbscOnwer;
		this.tbscAdder=tbscAdder;
		this.tbscEditor=tbscEditor;
		this.tbscRemark=tbscRemark;
		this.tbscMemo=tbscMemo;
		this.tbscData=tbscData;
		this.tbscUpdateTime=tbscUpdateTime;
		this.tbscAddTime=tbscAddTime;
		this.tbscStatus=tbscStatus;
	}
	
	//设置非空字段
	public TbSaleContract setNotNull(Long tbscHost,String tbscName,String tbscNo,Long tbscBattle,Long tbscEvent,Long tbscOpport,Long tbscQuote,Long tbscGroup,Long tbscCustom,Long tbscSigner,Date tbscSignDate,Date tbscBegin,Double tbscMoney,Double tbscProfit,String tbscOnwer,Long tbscAdder,Long tbscEditor,Date tbscUpdateTime,Date tbscAddTime,Short tbscStatus){
		this.tbscHost=tbscHost;
		this.tbscName=tbscName;
		this.tbscNo=tbscNo;
		this.tbscBattle=tbscBattle;
		this.tbscEvent=tbscEvent;
		this.tbscOpport=tbscOpport;
		this.tbscQuote=tbscQuote;
		this.tbscGroup=tbscGroup;
		this.tbscCustom=tbscCustom;
		this.tbscSigner=tbscSigner;
		this.tbscSignDate=tbscSignDate;
		this.tbscBegin=tbscBegin;
		this.tbscMoney=tbscMoney;
		this.tbscProfit=tbscProfit;
		this.tbscOnwer=tbscOnwer;
		this.tbscAdder=tbscAdder;
		this.tbscEditor=tbscEditor;
		this.tbscUpdateTime=tbscUpdateTime;
		this.tbscAddTime=tbscAddTime;
		this.tbscStatus=tbscStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbSaleContract tbscId(Long tbscId){
		this.tbscId = tbscId;
		this.setSeted(F.tbscId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbSaleContract tbscHost(Long tbscHost){
		this.tbscHost = tbscHost;
		this.setSeted(F.tbscHost);
		return this;
	}
	/** 合同名称 [非空]       **/
	public TbSaleContract tbscName(String tbscName){
		this.tbscName = tbscName;
		this.setSeted(F.tbscName);
		return this;
	}
	/** 合同编号 [非空]       **/
	public TbSaleContract tbscNo(String tbscNo){
		this.tbscNo = tbscNo;
		this.setSeted(F.tbscNo);
		return this;
	}
	/** 所属战役 [非空]       **/
	public TbSaleContract tbscBattle(Long tbscBattle){
		this.tbscBattle = tbscBattle;
		this.setSeted(F.tbscBattle);
		return this;
	}
	/** 所属活动 [非空] [CsSaleEvent]      **/
	public TbSaleContract tbscEvent(Long tbscEvent){
		this.tbscEvent = tbscEvent;
		this.setSeted(F.tbscEvent);
		return this;
	}
	/** 所属机会 [非空] [CsSaleOpport]      **/
	public TbSaleContract tbscOpport(Long tbscOpport){
		this.tbscOpport = tbscOpport;
		this.setSeted(F.tbscOpport);
		return this;
	}
	/** 关联报价 [非空] [TbSaleQuote]      **/
	public TbSaleContract tbscQuote(Long tbscQuote){
		this.tbscQuote = tbscQuote;
		this.setSeted(F.tbscQuote);
		return this;
	}
	/** 签约组织 [非空] [TbCustomGroup]      **/
	public TbSaleContract tbscGroup(Long tbscGroup){
		this.tbscGroup = tbscGroup;
		this.setSeted(F.tbscGroup);
		return this;
	}
	/** 签约客户 [非空] [TbSaleCustom]      **/
	public TbSaleContract tbscCustom(Long tbscCustom){
		this.tbscCustom = tbscCustom;
		this.setSeted(F.tbscCustom);
		return this;
	}
	/** 我方人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleContract tbscSigner(Long tbscSigner){
		this.tbscSigner = tbscSigner;
		this.setSeted(F.tbscSigner);
		return this;
	}
	/** 签约日期 [非空]       **/
	public TbSaleContract tbscSignDate(Date tbscSignDate){
		this.tbscSignDate = tbscSignDate;
		this.setSeted(F.tbscSignDate);
		return this;
	}
	/** 合同开始 [非空]       **/
	public TbSaleContract tbscBegin(Date tbscBegin){
		this.tbscBegin = tbscBegin;
		this.setSeted(F.tbscBegin);
		return this;
	}
	/** 合同结束        **/
	public TbSaleContract tbscEnd(Date tbscEnd){
		this.tbscEnd = tbscEnd;
		this.setSeted(F.tbscEnd);
		return this;
	}
	/** 签约金额 [非空]       **/
	public TbSaleContract tbscMoney(Double tbscMoney){
		this.tbscMoney = tbscMoney;
		this.setSeted(F.tbscMoney);
		return this;
	}
	/** 毛估利润 [非空]       **/
	public TbSaleContract tbscProfit(Double tbscProfit){
		this.tbscProfit = tbscProfit;
		this.setSeted(F.tbscProfit);
		return this;
	}
	/** 资料文件        **/
	public TbSaleContract tbscFile(String tbscFile){
		this.tbscFile = tbscFile;
		this.setSeted(F.tbscFile);
		return this;
	}
	/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleContract tbscOnwer(String tbscOnwer){
		this.tbscOnwer = tbscOnwer;
		this.setSeted(F.tbscOnwer);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleContract tbscAdder(Long tbscAdder){
		this.tbscAdder = tbscAdder;
		this.setSeted(F.tbscAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbSaleContract tbscEditor(Long tbscEditor){
		this.tbscEditor = tbscEditor;
		this.setSeted(F.tbscEditor);
		return this;
	}
	/** 备注        **/
	public TbSaleContract tbscRemark(String tbscRemark){
		this.tbscRemark = tbscRemark;
		this.setSeted(F.tbscRemark);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public TbSaleContract tbscMemo(String tbscMemo){
		this.tbscMemo = tbscMemo;
		this.setSeted(F.tbscMemo);
		return this;
	}
	/** 程序数据        **/
	public TbSaleContract tbscData(String tbscData){
		this.tbscData = tbscData;
		this.setSeted(F.tbscData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbSaleContract tbscUpdateTime(Date tbscUpdateTime){
		this.tbscUpdateTime = tbscUpdateTime;
		this.setSeted(F.tbscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbSaleContract tbscAddTime(Date tbscAddTime){
		this.tbscAddTime = tbscAddTime;
		this.setSeted(F.tbscAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public TbSaleContract tbscStatus(Short tbscStatus){
		this.tbscStatus = tbscStatus;
		this.setSeted(F.tbscStatus);
		return this;
	}
	
	
	//克隆对象
	public TbSaleContract clone(){
		TbSaleContract clone = new TbSaleContract();
		clone.tbscHost=this.tbscHost;
		clone.tbscName=this.tbscName;
		clone.tbscNo=this.tbscNo;
		clone.tbscBattle=this.tbscBattle;
		clone.tbscEvent=this.tbscEvent;
		clone.tbscOpport=this.tbscOpport;
		clone.tbscQuote=this.tbscQuote;
		clone.tbscGroup=this.tbscGroup;
		clone.tbscCustom=this.tbscCustom;
		clone.tbscSigner=this.tbscSigner;
		clone.tbscSignDate=this.tbscSignDate;
		clone.tbscBegin=this.tbscBegin;
		clone.tbscMoney=this.tbscMoney;
		clone.tbscProfit=this.tbscProfit;
		clone.tbscOnwer=this.tbscOnwer;
		clone.tbscAdder=this.tbscAdder;
		clone.tbscEditor=this.tbscEditor;
		clone.tbscUpdateTime=this.tbscUpdateTime;
		clone.tbscAddTime=this.tbscAddTime;
		clone.tbscStatus=this.tbscStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取签约合同
	 * @param id
	 * @return
	 */
	public static @api TbSaleContract get(Long id){		
		return getTbSaleContractById(id);
	}
	/**
	 * 获取所有签约合同
	 * @return
	 */
	public static @api List<TbSaleContract> list(Map params,Integer size){
		return getTbSaleContractList(params,size);
	}
	/**
	 * 获取签约合同分页
	 * @return
	 */
	public static @api Page<TbSaleContract> page(int page,int size,Map params){
		return getTbSaleContractPage(page, size , params);
	}
	/**
	 * 根据查询条件取签约合同
	 * @param params
	 * @return
	 */
	public static @api TbSaleContract Get(Map params){
		return getTbSaleContract(params);
	}
	/**
	 * 获取签约合同数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbSaleContractCount(params);
	}
	/**
	 * 获取签约合同数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbSaleContractEval(eval,params);
	}
	
	/**
	 * 根据ID取签约合同
	 * @param id
	 * @return
	 */
	public static @api TbSaleContract getTbSaleContractById(Long id){		
		TbSaleContract tbSaleContract = (TbSaleContract) $.GetRequest("TbSaleContract$"+id);
		if(tbSaleContract!=null)
			return tbSaleContract;
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");		
		return tbSaleContractService.getTbSaleContractById(id);
	}
	
	
	/**
	 * 根据ID取签约合同的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbSaleContract.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbSaleContract tbSaleContract = get(id);
		if(tbSaleContract!=null){
			String strValue = tbSaleContract.getTbscName$();
			if(!"TbscName".equals("TbscName"))
				strValue+="("+tbSaleContract.getTbscName$()+")";
			if(!"TbscName".equals("TbscNo"))
				strValue+="("+tbSaleContract.getTbscNo$()+")";
			MemCache.setValue(TbSaleContract.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbscName$();
		if(!"TbscName".equals("TbscName"))
			keyValue+="("+this.getTbscName$()+")";
		if(!"TbscName".equals("TbscNo"))
			keyValue+="("+this.getTbscNo$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有签约合同
	 * @return
	 */
	public static @api List<TbSaleContract> getTbSaleContractList(Map params,Integer size){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		return tbSaleContractService.getTbSaleContractList(params, size);
	}
	
	/**
	 * 获取签约合同分页
	 * @return
	 */
	public static @api Page<TbSaleContract> getTbSaleContractPage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		return tbSaleContractService.getTbSaleContractPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取签约合同
	 * @param params
	 * @return
	 */
	public static @api TbSaleContract getTbSaleContract(Map params){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		return tbSaleContractService.getTbSaleContract(params);
	}
	
	/**
	 * 获取签约合同数
	 * @return
	 */
	public static @api Long getTbSaleContractCount(Map params){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		return tbSaleContractService.getTbSaleContractCount(params);
	}
		
		
	/**
	 * 获取签约合同自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbSaleContractEval(String eval,Map params){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		return tbSaleContractService.getTbSaleContractEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbSaleContract(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		tbSaleContractService.updateTbSaleContractByConfirm(set, where);
	}
	
	
	/**
	 * 保存签约合同对象
	 * @param params
	 * @return
	 */
	public TbSaleContract save(){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		if(this.getTbscId()!=null)
			tbSaleContractService.updateTbSaleContract(this);
		else
			return tbSaleContractService.saveTbSaleContract(this);
		return this;
	}
	
	
	/**
	 * 更新签约合同对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		tbSaleContractService.updateTbSaleContract$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		if($.equals($.config("logic_delete"),"true"))
			tbSaleContractService.removeTbSaleContractById(this.getTbscId());
		else
			tbSaleContractService.deleteTbSaleContractById(this.getTbscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbSaleContractService tbSaleContractService = $.GetSpringBean("tbSaleContractService");
		return tbSaleContractService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbscId(){
		return this.tbscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbscId$(){
		String strValue="";
		 strValue=$.str(this.getTbscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbscId(Long tbscId){
		this.tbscId = tbscId;
		this.setSeted(F.tbscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbscHost(){
		return this.tbscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbscHost$(){
		String strValue="";
		if(this.getTbscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbscHost(Long tbscHost){
		this.tbscHost = tbscHost;
		this.setSeted(F.tbscHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbscHost()!=null){
 			srvHost = SrvHost.get(this.getTbscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************合同名称**********************************/	
	/**
	* 合同名称 [非空]      
	**/
	public String getTbscName(){
		return this.tbscName;
	}
	/**
	* 获取合同名称格式化(toString)
	**/
	public String getTbscName$(){
		String strValue="";
		 strValue=$.str(this.getTbscName());
	 	 return strValue;
	}
	/**
	* 合同名称 [非空]      
	**/
	public void setTbscName(String tbscName){
		this.tbscName = tbscName;
		this.setSeted(F.tbscName);
	}
	/*******************************合同编号**********************************/	
	/**
	* 合同编号 [非空]      
	**/
	public String getTbscNo(){
		return this.tbscNo;
	}
	/**
	* 获取合同编号格式化(toString)
	**/
	public String getTbscNo$(){
		String strValue="";
		 strValue=$.str(this.getTbscNo());
	 	 return strValue;
	}
	/**
	* 合同编号 [非空]      
	**/
	public void setTbscNo(String tbscNo){
		this.tbscNo = tbscNo;
		this.setSeted(F.tbscNo);
	}
	/*******************************所属战役**********************************/	
	/**
	* 所属战役 [非空]      
	**/
	public Long getTbscBattle(){
		return this.tbscBattle;
	}
	/**
	* 获取所属战役格式化(toString)
	**/
	public String getTbscBattle$(){
		String strValue="";
		 strValue=$.str(this.getTbscBattle());	
	 	 return strValue;
	}
	/**
	* 所属战役 [非空]      
	**/
	public void setTbscBattle(Long tbscBattle){
		this.tbscBattle = tbscBattle;
		this.setSeted(F.tbscBattle);
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动 [非空] [CsSaleEvent]     
	**/
	public Long getTbscEvent(){
		return this.tbscEvent;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getTbscEvent$(){
		String strValue="";
		if(this.getTbscEvent()!=null){
				strValue+=$.str(CsSaleEvent.getKeyValue(this.getTbscEvent()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动 [非空] [CsSaleEvent]     
	**/
	public void setTbscEvent(Long tbscEvent){
		this.tbscEvent = tbscEvent;
		this.setSeted(F.tbscEvent);
	}
	/**
	* 获取关联对象[销售活动]
	**/	 			
 	public CsSaleEvent get$tbscEvent(){
 		com.ccclubs.model.CsSaleEvent csSaleEvent = (com.ccclubs.model.CsSaleEvent) $.GetRequest("CsSaleEvent$"+this.getTbscEvent());
 		if(csSaleEvent!=null)
			return csSaleEvent;
		if(this.getTbscEvent()!=null){
 			csSaleEvent = CsSaleEvent.get(this.getTbscEvent());
 		}
 		$.SetRequest("CsSaleEvent$"+this.getTbscEvent(), csSaleEvent);
	 	return csSaleEvent;
	}
	/*******************************所属机会**********************************/	
	/**
	* 所属机会 [非空] [CsSaleOpport]     
	**/
	public Long getTbscOpport(){
		return this.tbscOpport;
	}
	/**
	* 获取所属机会格式化(toString)
	**/
	public String getTbscOpport$(){
		String strValue="";
		if(this.getTbscOpport()!=null){
				strValue+=$.str(CsSaleOpport.getKeyValue(this.getTbscOpport()));
		}			
	 	 return strValue;
	}
	/**
	* 所属机会 [非空] [CsSaleOpport]     
	**/
	public void setTbscOpport(Long tbscOpport){
		this.tbscOpport = tbscOpport;
		this.setSeted(F.tbscOpport);
	}
	/**
	* 获取关联对象[销售机会]
	**/	 			
 	public CsSaleOpport get$tbscOpport(){
 		com.ccclubs.model.CsSaleOpport csSaleOpport = (com.ccclubs.model.CsSaleOpport) $.GetRequest("CsSaleOpport$"+this.getTbscOpport());
 		if(csSaleOpport!=null)
			return csSaleOpport;
		if(this.getTbscOpport()!=null){
 			csSaleOpport = CsSaleOpport.get(this.getTbscOpport());
 		}
 		$.SetRequest("CsSaleOpport$"+this.getTbscOpport(), csSaleOpport);
	 	return csSaleOpport;
	}
	/*******************************关联报价**********************************/	
	/**
	* 关联报价 [非空] [TbSaleQuote]     
	**/
	public Long getTbscQuote(){
		return this.tbscQuote;
	}
	/**
	* 获取关联报价格式化(toString)
	**/
	public String getTbscQuote$(){
		String strValue="";
		if(this.getTbscQuote()!=null){
				strValue+=$.str(TbSaleQuote.getKeyValue(this.getTbscQuote()));
		}			
	 	 return strValue;
	}
	/**
	* 关联报价 [非空] [TbSaleQuote]     
	**/
	public void setTbscQuote(Long tbscQuote){
		this.tbscQuote = tbscQuote;
		this.setSeted(F.tbscQuote);
	}
	/**
	* 获取关联对象[跟单报价]
	**/	 			
 	public TbSaleQuote get$tbscQuote(){
 		com.ccclubs.model.TbSaleQuote tbSaleQuote = (com.ccclubs.model.TbSaleQuote) $.GetRequest("TbSaleQuote$"+this.getTbscQuote());
 		if(tbSaleQuote!=null)
			return tbSaleQuote;
		if(this.getTbscQuote()!=null){
 			tbSaleQuote = TbSaleQuote.get(this.getTbscQuote());
 		}
 		$.SetRequest("TbSaleQuote$"+this.getTbscQuote(), tbSaleQuote);
	 	return tbSaleQuote;
	}
	/*******************************签约组织**********************************/	
	/**
	* 签约组织 [非空] [TbCustomGroup]     
	**/
	public Long getTbscGroup(){
		return this.tbscGroup;
	}
	/**
	* 获取签约组织格式化(toString)
	**/
	public String getTbscGroup$(){
		String strValue="";
		if(this.getTbscGroup()!=null){
				strValue+=$.str(TbCustomGroup.getKeyValue(this.getTbscGroup()));
		}			
	 	 return strValue;
	}
	/**
	* 签约组织 [非空] [TbCustomGroup]     
	**/
	public void setTbscGroup(Long tbscGroup){
		this.tbscGroup = tbscGroup;
		this.setSeted(F.tbscGroup);
	}
	/**
	* 获取关联对象[客户组织]
	**/	 			
 	public TbCustomGroup get$tbscGroup(){
 		com.ccclubs.model.TbCustomGroup tbCustomGroup = (com.ccclubs.model.TbCustomGroup) $.GetRequest("TbCustomGroup$"+this.getTbscGroup());
 		if(tbCustomGroup!=null)
			return tbCustomGroup;
		if(this.getTbscGroup()!=null){
 			tbCustomGroup = TbCustomGroup.get(this.getTbscGroup());
 		}
 		$.SetRequest("TbCustomGroup$"+this.getTbscGroup(), tbCustomGroup);
	 	return tbCustomGroup;
	}
	/*******************************签约客户**********************************/	
	/**
	* 签约客户 [非空] [TbSaleCustom]     
	**/
	public Long getTbscCustom(){
		return this.tbscCustom;
	}
	/**
	* 获取签约客户格式化(toString)
	**/
	public String getTbscCustom$(){
		String strValue="";
		if(this.getTbscCustom()!=null){
				strValue+=$.str(TbSaleCustom.getKeyValue(this.getTbscCustom()));
		}			
	 	 return strValue;
	}
	/**
	* 签约客户 [非空] [TbSaleCustom]     
	**/
	public void setTbscCustom(Long tbscCustom){
		this.tbscCustom = tbscCustom;
		this.setSeted(F.tbscCustom);
	}
	/**
	* 获取关联对象[客户资料]
	**/	 			
 	public TbSaleCustom get$tbscCustom(){
 		com.ccclubs.model.TbSaleCustom tbSaleCustom = (com.ccclubs.model.TbSaleCustom) $.GetRequest("TbSaleCustom$"+this.getTbscCustom());
 		if(tbSaleCustom!=null)
			return tbSaleCustom;
		if(this.getTbscCustom()!=null){
 			tbSaleCustom = TbSaleCustom.get(this.getTbscCustom());
 		}
 		$.SetRequest("TbSaleCustom$"+this.getTbscCustom(), tbSaleCustom);
	 	return tbSaleCustom;
	}
	/*******************************我方人员**********************************/	
	/**
	* 我方人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbscSigner(){
		return this.tbscSigner;
	}
	/**
	* 获取我方人员格式化(toString)
	**/
	public String getTbscSigner$(){
		String strValue="";
		if(this.getTbscSigner()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbscSigner()));
		}			
	 	 return strValue;
	}
	/**
	* 我方人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbscSigner(Long tbscSigner){
		this.tbscSigner = tbscSigner;
		this.setSeted(F.tbscSigner);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbscSigner(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbscSigner());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbscSigner()!=null){
 			srvUser = SrvUser.get(this.getTbscSigner());
 		}
 		$.SetRequest("SrvUser$"+this.getTbscSigner(), srvUser);
	 	return srvUser;
	}
	/*******************************签约日期**********************************/	
	/**
	* 签约日期 [非空]      
	**/
	public Date getTbscSignDate(){
		return this.tbscSignDate;
	}
	/**
	* 获取签约日期格式化(toString)
	**/
	public String getTbscSignDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbscSignDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 签约日期 [非空]      
	**/
	public void setTbscSignDate(Date tbscSignDate){
		this.tbscSignDate = tbscSignDate;
		this.setSeted(F.tbscSignDate);
	}
	/*******************************合同开始**********************************/	
	/**
	* 合同开始 [非空]      
	**/
	public Date getTbscBegin(){
		return this.tbscBegin;
	}
	/**
	* 获取合同开始格式化(toString)
	**/
	public String getTbscBegin$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbscBegin(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 合同开始 [非空]      
	**/
	public void setTbscBegin(Date tbscBegin){
		this.tbscBegin = tbscBegin;
		this.setSeted(F.tbscBegin);
	}
	/*******************************合同结束**********************************/	
	/**
	* 合同结束       
	**/
	public Date getTbscEnd(){
		return this.tbscEnd;
	}
	/**
	* 获取合同结束格式化(toString)
	**/
	public String getTbscEnd$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbscEnd(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 合同结束       
	**/
	public void setTbscEnd(Date tbscEnd){
		this.tbscEnd = tbscEnd;
		this.setSeted(F.tbscEnd);
	}
	/*******************************签约金额**********************************/	
	/**
	* 签约金额 [非空]      
	**/
	public Double getTbscMoney(){
		return this.tbscMoney;
	}
	/**
	* 获取签约金额格式化(toString)
	**/
	public String getTbscMoney$(){
		String strValue="";
		 strValue=$.str(this.getTbscMoney());	
	 	 return strValue;
	}
	/**
	* 签约金额 [非空]      
	**/
	public void setTbscMoney(Double tbscMoney){
		this.tbscMoney = tbscMoney;
		this.setSeted(F.tbscMoney);
	}
	/*******************************毛估利润**********************************/	
	/**
	* 毛估利润 [非空]      
	**/
	public Double getTbscProfit(){
		return this.tbscProfit;
	}
	/**
	* 获取毛估利润格式化(toString)
	**/
	public String getTbscProfit$(){
		String strValue="";
		 strValue=$.str(this.getTbscProfit());	
	 	 return strValue;
	}
	/**
	* 毛估利润 [非空]      
	**/
	public void setTbscProfit(Double tbscProfit){
		this.tbscProfit = tbscProfit;
		this.setSeted(F.tbscProfit);
	}
	/*******************************资料文件**********************************/	
	/**
	* 资料文件       
	**/
	public String getTbscFile(){
		return this.tbscFile;
	}
	/**
	* 获取资料文件格式化(toString)
	**/
	public String getTbscFile$(){
		String strValue="";
		 strValue=$.str(this.getTbscFile());
	 	 return strValue;
	}
	/**
	* 资料文件       
	**/
	public void setTbscFile(String tbscFile){
		this.tbscFile = tbscFile;
		this.setSeted(F.tbscFile);
	}
	/*******************************所有人**********************************/	
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public String getTbscOnwer(){
		return this.tbscOnwer;
	}
	/**
	* 获取所有人格式化(toString)
	**/
	public String getTbscOnwer$(){
		String strValue="";
			com.ccclubs.model.SrvUser srvUser = com.ccclubs.model.SrvUser.getSrvUser($.add("suFamily",this.getTbscOnwer()));
		if(srvUser!=null)
			strValue+="["+$.str(srvUser.getSuRealName())+"]";
	 	 return strValue;
	}
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbscOnwer(String tbscOnwer){
		this.tbscOnwer = tbscOnwer;
		this.setSeted(F.tbscOnwer);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbscOnwer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbscOnwer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbscOnwer()!=null){
 			srvUser = SrvUser.Get($.add(SrvUser.F.suId,this.getTbscOnwer()));
 		}
 		$.SetRequest("SrvUser$"+this.getTbscOnwer(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbscAdder(){
		return this.tbscAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getTbscAdder$(){
		String strValue="";
		if(this.getTbscAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbscAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbscAdder(Long tbscAdder){
		this.tbscAdder = tbscAdder;
		this.setSeted(F.tbscAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbscAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbscAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbscAdder()!=null){
 			srvUser = SrvUser.get(this.getTbscAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getTbscAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbscEditor(){
		return this.tbscEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getTbscEditor$(){
		String strValue="";
		if(this.getTbscEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbscEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbscEditor(Long tbscEditor){
		this.tbscEditor = tbscEditor;
		this.setSeted(F.tbscEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbscEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbscEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbscEditor()!=null){
 			srvUser = SrvUser.get(this.getTbscEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbscEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getTbscRemark(){
		return this.tbscRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getTbscRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbscRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setTbscRemark(String tbscRemark){
		this.tbscRemark = tbscRemark;
		this.setSeted(F.tbscRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getTbscMemo(){
		return this.tbscMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getTbscMemo$(){
		String strValue="";
		 strValue=$.str(this.getTbscMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setTbscMemo(String tbscMemo){
		this.tbscMemo = tbscMemo;
		this.setSeted(F.tbscMemo);
	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据       
	**/
	public String getTbscData(){
		return this.tbscData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getTbscData$(){
		String strValue="";
		 strValue=$.str(this.getTbscData());
	 	 return strValue;
	}
	/**
	* 程序数据       
	**/
	public void setTbscData(String tbscData){
		this.tbscData = tbscData;
		this.setSeted(F.tbscData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbscUpdateTime(){
		return this.tbscUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setTbscUpdateTime(Date tbscUpdateTime){
		this.tbscUpdateTime = tbscUpdateTime;
		this.setSeted(F.tbscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbscAddTime(){
		return this.tbscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setTbscAddTime(Date tbscAddTime){
		this.tbscAddTime = tbscAddTime;
		this.setSeted(F.tbscAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getTbscStatus(){
		return this.tbscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbscStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getTbscStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setTbscStatus(Short tbscStatus){
		this.tbscStatus = tbscStatus;
		this.setSeted(F.tbscStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbSaleContract.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleContract.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleContract.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbSaleContract.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbSaleContract.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbSaleContract.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbSaleContract.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbSaleContract.this);
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
		public M tbscId(Object tbscId){this.put("tbscId", tbscId);return this;};
	 	/** and tbsc_id is null */
 		public M tbscIdNull(){if(this.get("tbscIdNot")==null)this.put("tbscIdNot", "");this.put("tbscId", null);return this;};
 		/** not .... */
 		public M tbscIdNot(){this.put("tbscIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbscHost(Object tbscHost){this.put("tbscHost", tbscHost);return this;};
	 	/** and tbsc_host is null */
 		public M tbscHostNull(){if(this.get("tbscHostNot")==null)this.put("tbscHostNot", "");this.put("tbscHost", null);return this;};
 		/** not .... */
 		public M tbscHostNot(){this.put("tbscHostNot", "not");return this;};
		/** 合同名称 [非空]       **/
		public M tbscName(Object tbscName){this.put("tbscName", tbscName);return this;};
	 	/** and tbsc_name is null */
 		public M tbscNameNull(){if(this.get("tbscNameNot")==null)this.put("tbscNameNot", "");this.put("tbscName", null);return this;};
 		/** not .... */
 		public M tbscNameNot(){this.put("tbscNameNot", "not");return this;};
		/** 合同编号 [非空]       **/
		public M tbscNo(Object tbscNo){this.put("tbscNo", tbscNo);return this;};
	 	/** and tbsc_no is null */
 		public M tbscNoNull(){if(this.get("tbscNoNot")==null)this.put("tbscNoNot", "");this.put("tbscNo", null);return this;};
 		/** not .... */
 		public M tbscNoNot(){this.put("tbscNoNot", "not");return this;};
		/** 所属战役 [非空]       **/
		public M tbscBattle(Object tbscBattle){this.put("tbscBattle", tbscBattle);return this;};
	 	/** and tbsc_battle is null */
 		public M tbscBattleNull(){if(this.get("tbscBattleNot")==null)this.put("tbscBattleNot", "");this.put("tbscBattle", null);return this;};
 		/** not .... */
 		public M tbscBattleNot(){this.put("tbscBattleNot", "not");return this;};
		/** 所属活动 [非空] [CsSaleEvent]      **/
		public M tbscEvent(Object tbscEvent){this.put("tbscEvent", tbscEvent);return this;};
	 	/** and tbsc_event is null */
 		public M tbscEventNull(){if(this.get("tbscEventNot")==null)this.put("tbscEventNot", "");this.put("tbscEvent", null);return this;};
 		/** not .... */
 		public M tbscEventNot(){this.put("tbscEventNot", "not");return this;};
		public M tbscEvent$on(CsSaleEvent.M value){
			this.put("CsSaleEvent", value);
			this.put("tbscEvent$on", value);
			return this;
		};	
		/** 所属机会 [非空] [CsSaleOpport]      **/
		public M tbscOpport(Object tbscOpport){this.put("tbscOpport", tbscOpport);return this;};
	 	/** and tbsc_opport is null */
 		public M tbscOpportNull(){if(this.get("tbscOpportNot")==null)this.put("tbscOpportNot", "");this.put("tbscOpport", null);return this;};
 		/** not .... */
 		public M tbscOpportNot(){this.put("tbscOpportNot", "not");return this;};
		public M tbscOpport$on(CsSaleOpport.M value){
			this.put("CsSaleOpport", value);
			this.put("tbscOpport$on", value);
			return this;
		};	
		/** 关联报价 [非空] [TbSaleQuote]      **/
		public M tbscQuote(Object tbscQuote){this.put("tbscQuote", tbscQuote);return this;};
	 	/** and tbsc_quote is null */
 		public M tbscQuoteNull(){if(this.get("tbscQuoteNot")==null)this.put("tbscQuoteNot", "");this.put("tbscQuote", null);return this;};
 		/** not .... */
 		public M tbscQuoteNot(){this.put("tbscQuoteNot", "not");return this;};
		public M tbscQuote$on(TbSaleQuote.M value){
			this.put("TbSaleQuote", value);
			this.put("tbscQuote$on", value);
			return this;
		};	
		/** 签约组织 [非空] [TbCustomGroup]      **/
		public M tbscGroup(Object tbscGroup){this.put("tbscGroup", tbscGroup);return this;};
	 	/** and tbsc_group is null */
 		public M tbscGroupNull(){if(this.get("tbscGroupNot")==null)this.put("tbscGroupNot", "");this.put("tbscGroup", null);return this;};
 		/** not .... */
 		public M tbscGroupNot(){this.put("tbscGroupNot", "not");return this;};
		public M tbscGroup$on(TbCustomGroup.M value){
			this.put("TbCustomGroup", value);
			this.put("tbscGroup$on", value);
			return this;
		};	
		/** 签约客户 [非空] [TbSaleCustom]      **/
		public M tbscCustom(Object tbscCustom){this.put("tbscCustom", tbscCustom);return this;};
	 	/** and tbsc_custom is null */
 		public M tbscCustomNull(){if(this.get("tbscCustomNot")==null)this.put("tbscCustomNot", "");this.put("tbscCustom", null);return this;};
 		/** not .... */
 		public M tbscCustomNot(){this.put("tbscCustomNot", "not");return this;};
		public M tbscCustom$on(TbSaleCustom.M value){
			this.put("TbSaleCustom", value);
			this.put("tbscCustom$on", value);
			return this;
		};	
		/** 我方人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbscSigner(Object tbscSigner){this.put("tbscSigner", tbscSigner);return this;};
	 	/** and tbsc_signer is null */
 		public M tbscSignerNull(){if(this.get("tbscSignerNot")==null)this.put("tbscSignerNot", "");this.put("tbscSigner", null);return this;};
 		/** not .... */
 		public M tbscSignerNot(){this.put("tbscSignerNot", "not");return this;};
		/** 签约日期 [非空]       **/
		public M tbscSignDate(Object tbscSignDate){this.put("tbscSignDate", tbscSignDate);return this;};
	 	/** and tbsc_sign_date is null */
 		public M tbscSignDateNull(){if(this.get("tbscSignDateNot")==null)this.put("tbscSignDateNot", "");this.put("tbscSignDate", null);return this;};
 		/** not .... */
 		public M tbscSignDateNot(){this.put("tbscSignDateNot", "not");return this;};
 		/** and tbsc_sign_date >= ? */
 		public M tbscSignDateStart(Object start){this.put("tbscSignDateStart", start);return this;};			
 		/** and tbsc_sign_date <= ? */
 		public M tbscSignDateEnd(Object end){this.put("tbscSignDateEnd", end);return this;};
		/** 合同开始 [非空]       **/
		public M tbscBegin(Object tbscBegin){this.put("tbscBegin", tbscBegin);return this;};
	 	/** and tbsc_begin is null */
 		public M tbscBeginNull(){if(this.get("tbscBeginNot")==null)this.put("tbscBeginNot", "");this.put("tbscBegin", null);return this;};
 		/** not .... */
 		public M tbscBeginNot(){this.put("tbscBeginNot", "not");return this;};
 		/** and tbsc_begin >= ? */
 		public M tbscBeginStart(Object start){this.put("tbscBeginStart", start);return this;};			
 		/** and tbsc_begin <= ? */
 		public M tbscBeginEnd(Object end){this.put("tbscBeginEnd", end);return this;};
		/** 合同结束        **/
		public M tbscEnd(Object tbscEnd){this.put("tbscEnd", tbscEnd);return this;};
	 	/** and tbsc_end is null */
 		public M tbscEndNull(){if(this.get("tbscEndNot")==null)this.put("tbscEndNot", "");this.put("tbscEnd", null);return this;};
 		/** not .... */
 		public M tbscEndNot(){this.put("tbscEndNot", "not");return this;};
 		/** and tbsc_end >= ? */
 		public M tbscEndStart(Object start){this.put("tbscEndStart", start);return this;};			
 		/** and tbsc_end <= ? */
 		public M tbscEndEnd(Object end){this.put("tbscEndEnd", end);return this;};
		/** 签约金额 [非空]       **/
		public M tbscMoney(Object tbscMoney){this.put("tbscMoney", tbscMoney);return this;};
	 	/** and tbsc_money is null */
 		public M tbscMoneyNull(){if(this.get("tbscMoneyNot")==null)this.put("tbscMoneyNot", "");this.put("tbscMoney", null);return this;};
 		/** not .... */
 		public M tbscMoneyNot(){this.put("tbscMoneyNot", "not");return this;};
		/** and tbsc_money >= ? */
		public M tbscMoneyMin(Object min){this.put("tbscMoneyMin", min);return this;};
		/** and tbsc_money <= ? */
		public M tbscMoneyMax(Object max){this.put("tbscMoneyMax", max);return this;};
		/** 毛估利润 [非空]       **/
		public M tbscProfit(Object tbscProfit){this.put("tbscProfit", tbscProfit);return this;};
	 	/** and tbsc_profit is null */
 		public M tbscProfitNull(){if(this.get("tbscProfitNot")==null)this.put("tbscProfitNot", "");this.put("tbscProfit", null);return this;};
 		/** not .... */
 		public M tbscProfitNot(){this.put("tbscProfitNot", "not");return this;};
		/** and tbsc_profit >= ? */
		public M tbscProfitMin(Object min){this.put("tbscProfitMin", min);return this;};
		/** and tbsc_profit <= ? */
		public M tbscProfitMax(Object max){this.put("tbscProfitMax", max);return this;};
		/** 资料文件        **/
		public M tbscFile(Object tbscFile){this.put("tbscFile", tbscFile);return this;};
	 	/** and tbsc_file is null */
 		public M tbscFileNull(){if(this.get("tbscFileNot")==null)this.put("tbscFileNot", "");this.put("tbscFile", null);return this;};
 		/** not .... */
 		public M tbscFileNot(){this.put("tbscFileNot", "not");return this;};
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbscOnwer(Object tbscOnwer){this.put("tbscOnwer", tbscOnwer);return this;};
	 	/** and tbsc_onwer is null */
 		public M tbscOnwerNull(){if(this.get("tbscOnwerNot")==null)this.put("tbscOnwerNot", "");this.put("tbscOnwer", null);return this;};
 		/** not .... */
 		public M tbscOnwerNot(){this.put("tbscOnwerNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbscAdder(Object tbscAdder){this.put("tbscAdder", tbscAdder);return this;};
	 	/** and tbsc_adder is null */
 		public M tbscAdderNull(){if(this.get("tbscAdderNot")==null)this.put("tbscAdderNot", "");this.put("tbscAdder", null);return this;};
 		/** not .... */
 		public M tbscAdderNot(){this.put("tbscAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbscEditor(Object tbscEditor){this.put("tbscEditor", tbscEditor);return this;};
	 	/** and tbsc_editor is null */
 		public M tbscEditorNull(){if(this.get("tbscEditorNot")==null)this.put("tbscEditorNot", "");this.put("tbscEditor", null);return this;};
 		/** not .... */
 		public M tbscEditorNot(){this.put("tbscEditorNot", "not");return this;};
		/** 备注        **/
		public M tbscRemark(Object tbscRemark){this.put("tbscRemark", tbscRemark);return this;};
	 	/** and tbsc_remark is null */
 		public M tbscRemarkNull(){if(this.get("tbscRemarkNot")==null)this.put("tbscRemarkNot", "");this.put("tbscRemark", null);return this;};
 		/** not .... */
 		public M tbscRemarkNot(){this.put("tbscRemarkNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M tbscMemo(Object tbscMemo){this.put("tbscMemo", tbscMemo);return this;};
	 	/** and tbsc_memo is null */
 		public M tbscMemoNull(){if(this.get("tbscMemoNot")==null)this.put("tbscMemoNot", "");this.put("tbscMemo", null);return this;};
 		/** not .... */
 		public M tbscMemoNot(){this.put("tbscMemoNot", "not");return this;};
		/** 程序数据        **/
		public M tbscData(Object tbscData){this.put("tbscData", tbscData);return this;};
	 	/** and tbsc_data is null */
 		public M tbscDataNull(){if(this.get("tbscDataNot")==null)this.put("tbscDataNot", "");this.put("tbscData", null);return this;};
 		/** not .... */
 		public M tbscDataNot(){this.put("tbscDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M tbscUpdateTime(Object tbscUpdateTime){this.put("tbscUpdateTime", tbscUpdateTime);return this;};
	 	/** and tbsc_update_time is null */
 		public M tbscUpdateTimeNull(){if(this.get("tbscUpdateTimeNot")==null)this.put("tbscUpdateTimeNot", "");this.put("tbscUpdateTime", null);return this;};
 		/** not .... */
 		public M tbscUpdateTimeNot(){this.put("tbscUpdateTimeNot", "not");return this;};
 		/** and tbsc_update_time >= ? */
 		public M tbscUpdateTimeStart(Object start){this.put("tbscUpdateTimeStart", start);return this;};			
 		/** and tbsc_update_time <= ? */
 		public M tbscUpdateTimeEnd(Object end){this.put("tbscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbscAddTime(Object tbscAddTime){this.put("tbscAddTime", tbscAddTime);return this;};
	 	/** and tbsc_add_time is null */
 		public M tbscAddTimeNull(){if(this.get("tbscAddTimeNot")==null)this.put("tbscAddTimeNot", "");this.put("tbscAddTime", null);return this;};
 		/** not .... */
 		public M tbscAddTimeNot(){this.put("tbscAddTimeNot", "not");return this;};
 		/** and tbsc_add_time >= ? */
 		public M tbscAddTimeStart(Object start){this.put("tbscAddTimeStart", start);return this;};			
 		/** and tbsc_add_time <= ? */
 		public M tbscAddTimeEnd(Object end){this.put("tbscAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M tbscStatus(Object tbscStatus){this.put("tbscStatus", tbscStatus);return this;};
	 	/** and tbsc_status is null */
 		public M tbscStatusNull(){if(this.get("tbscStatusNot")==null)this.put("tbscStatusNot", "");this.put("tbscStatus", null);return this;};
 		/** not .... */
 		public M tbscStatusNot(){this.put("tbscStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有签约合同 **/
		public @api List<TbSaleContract> list(Integer size){
			return getTbSaleContractList(this,size);
		}
		/** 获取签约合同分页 **/
		public @api Page<TbSaleContract> page(int page,int size){
			return getTbSaleContractPage(page, size , this);
		}
		/** 根据查询条件取签约合同 **/
		public @api TbSaleContract get(){
			return getTbSaleContract(this);
		}
		/** 获取签约合同数 **/
		public @api Long count(){
			return getTbSaleContractCount(this);
		}
		/** 获取签约合同表达式 **/
		public @api <T> T eval(String strEval){
			return getTbSaleContractEval(strEval,this);
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
			updateTbSaleContract(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbscId="tbscId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbscHost="tbscHost";
		/** 合同名称 [非空]       **/
		public final static @type(String.class) @like String tbscName="tbscName";
		/** 合同编号 [非空]       **/
		public final static @type(String.class) @like String tbscNo="tbscNo";
		/** 所属战役 [非空]       **/
		public final static @type(Long.class)  String tbscBattle="tbscBattle";
		/** 所属活动 [非空] [CsSaleEvent]      **/
		public final static @type(Long.class)  String tbscEvent="tbscEvent";
		/** 所属机会 [非空] [CsSaleOpport]      **/
		public final static @type(Long.class)  String tbscOpport="tbscOpport";
		/** 关联报价 [非空] [TbSaleQuote]      **/
		public final static @type(Long.class)  String tbscQuote="tbscQuote";
		/** 签约组织 [非空] [TbCustomGroup]      **/
		public final static @type(Long.class)  String tbscGroup="tbscGroup";
		/** 签约客户 [非空] [TbSaleCustom]      **/
		public final static @type(Long.class)  String tbscCustom="tbscCustom";
		/** 我方人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbscSigner="tbscSigner";
		/** 签约日期 [非空]       **/
		public final static @type(Date.class)  String tbscSignDate="tbscSignDate";
	 	/** and tbsc_sign_date >= ? */
 		public final static @type(Date.class) String tbscSignDateStart="tbscSignDateStart";
 		/** and tbsc_sign_date <= ? */
 		public final static @type(Date.class) String tbscSignDateEnd="tbscSignDateEnd";
		/** 合同开始 [非空]       **/
		public final static @type(Date.class)  String tbscBegin="tbscBegin";
	 	/** and tbsc_begin >= ? */
 		public final static @type(Date.class) String tbscBeginStart="tbscBeginStart";
 		/** and tbsc_begin <= ? */
 		public final static @type(Date.class) String tbscBeginEnd="tbscBeginEnd";
		/** 合同结束        **/
		public final static @type(Date.class)  String tbscEnd="tbscEnd";
	 	/** and tbsc_end >= ? */
 		public final static @type(Date.class) String tbscEndStart="tbscEndStart";
 		/** and tbsc_end <= ? */
 		public final static @type(Date.class) String tbscEndEnd="tbscEndEnd";
		/** 签约金额 [非空]       **/
		public final static @type(Double.class)  String tbscMoney="tbscMoney";
		/** and tbsc_money >= ? */
		public final static @type(Double.class) String tbscMoneyMin="tbscMoneyMin";
		/** and tbsc_money <= ? */
		public final static @type(Double.class) String tbscMoneyMax="tbscMoneyMax";
		/** 毛估利润 [非空]       **/
		public final static @type(Double.class)  String tbscProfit="tbscProfit";
		/** and tbsc_profit >= ? */
		public final static @type(Double.class) String tbscProfitMin="tbscProfitMin";
		/** and tbsc_profit <= ? */
		public final static @type(Double.class) String tbscProfitMax="tbscProfitMax";
		/** 资料文件        **/
		public final static @type(String.class) @like String tbscFile="tbscFile";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(String.class)  String tbscOnwer="tbscOnwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbscAdder="tbscAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbscEditor="tbscEditor";
		/** 备注        **/
		public final static @type(String.class) @like String tbscRemark="tbscRemark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class) @like String tbscMemo="tbscMemo";
		/** 程序数据        **/
		public final static @type(String.class) @like String tbscData="tbscData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbscUpdateTime="tbscUpdateTime";
	 	/** and tbsc_update_time >= ? */
 		public final static @type(Date.class) String tbscUpdateTimeStart="tbscUpdateTimeStart";
 		/** and tbsc_update_time <= ? */
 		public final static @type(Date.class) String tbscUpdateTimeEnd="tbscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbscAddTime="tbscAddTime";
	 	/** and tbsc_add_time >= ? */
 		public final static @type(Date.class) String tbscAddTimeStart="tbscAddTimeStart";
 		/** and tbsc_add_time <= ? */
 		public final static @type(Date.class) String tbscAddTimeEnd="tbscAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String tbscStatus="tbscStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbscId="tbsc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbscHost="tbsc_host";
		/** 合同名称 [非空]       **/
		public final static String tbscName="tbsc_name";
		/** 合同编号 [非空]       **/
		public final static String tbscNo="tbsc_no";
		/** 所属战役 [非空]       **/
		public final static String tbscBattle="tbsc_battle";
		/** 所属活动 [非空] [CsSaleEvent]      **/
		public final static String tbscEvent="tbsc_event";
		/** 所属机会 [非空] [CsSaleOpport]      **/
		public final static String tbscOpport="tbsc_opport";
		/** 关联报价 [非空] [TbSaleQuote]      **/
		public final static String tbscQuote="tbsc_quote";
		/** 签约组织 [非空] [TbCustomGroup]      **/
		public final static String tbscGroup="tbsc_group";
		/** 签约客户 [非空] [TbSaleCustom]      **/
		public final static String tbscCustom="tbsc_custom";
		/** 我方人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbscSigner="tbsc_signer";
		/** 签约日期 [非空]       **/
		public final static String tbscSignDate="tbsc_sign_date";
		/** 合同开始 [非空]       **/
		public final static String tbscBegin="tbsc_begin";
		/** 合同结束        **/
		public final static String tbscEnd="tbsc_end";
		/** 签约金额 [非空]       **/
		public final static String tbscMoney="tbsc_money";
		/** 毛估利润 [非空]       **/
		public final static String tbscProfit="tbsc_profit";
		/** 资料文件        **/
		public final static String tbscFile="tbsc_file";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbscOnwer="tbsc_onwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbscAdder="tbsc_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbscEditor="tbsc_editor";
		/** 备注        **/
		public final static String tbscRemark="tbsc_remark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String tbscMemo="tbsc_memo";
		/** 程序数据        **/
		public final static String tbscData="tbsc_data";
		/** 修改时间 [非空]       **/
		public final static String tbscUpdateTime="tbsc_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbscAddTime="tbsc_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String tbscStatus="tbsc_status";
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
				$.Set(name,TbSaleContract.getTbSaleContract(params));
			else
				$.Set(name,TbSaleContract.getTbSaleContractList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取签约合同数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbSaleContract) $.GetRequest("TbSaleContract$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbSaleContract.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbSaleContract.getTbSaleContract(params);
			else
				value = TbSaleContract.getTbSaleContractList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbSaleContract.Get($.add(TbSaleContract.F.tbscId,param));
		else if(!$.empty(param.toString()))
			value = TbSaleContract.get(Long.valueOf(param.toString()));
		$.SetRequest("TbSaleContract$"+param.hashCode(), value);
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
	public void mergeSet(TbSaleContract old){
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