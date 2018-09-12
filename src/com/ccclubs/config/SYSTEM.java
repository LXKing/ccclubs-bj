package com.ccclubs.config;

import java.util.List;

import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsArgument;
import com.ccclubs.model.CsConfig;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRecordSubject;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsArgumentService;
import com.lazy3q.sql.redis.RedisSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class SYSTEM {

	// 产品标识
	public static final String	TIMEOUT	= "TIMEOUT";
	public static final String	MILEAGE	= "MILEAGE";
	public static final String	KILOM	= "KILOM";
	public static final String	RENT	= "RENT";
	public static final String	INSURE	= "INSURE";	// 按里程免责
	public static final String	SECURE	= "SECURE";	// 按小时免责
	public static final String	DRIVE	= "DRIVE";
	public static final String	MARGING	= "MARGING";
	public static final String	MARGIN	= "MARGIN";
	
	//redis数据源名称
	public static final RedisSupport REDIS = RedisSupport.getRedis("ccclubs.redis.0");

	// 规则名称
	public enum RuleName {
		工作日白天小时, 工作日晚上小时, 双休日白天小时, 双休日晚上小时, 节假日白天小时, 节假日晚上小时, 工作日一天, 工作日白天包段, 
		工作日晚上包段, 双休日一天, 双休日白天包段, 双休日晚上包段, 节假日一天, 节假日白天包段, 节假日晚上包段, 
		每次,每天, 每小时, 每分钟, 每公里, 晚上包段, 白天小时, 晚上小时, 白天包段, 夜租一, 夜租二, 夜租三
	}

	public static final Long	SECOND					= 1000l;
	public static final Long	MINUTE					= SECOND * 60l;
	public static final Long	HOUR					= MINUTE * 60l;
	public static final Long	DAY						= HOUR * 24;
	public static final long	MONTH					= DAY * 31;
	public static final long	YEAR					= DAY * 365;

	// 2000年的毫秒数
	public static final long	MACHINE_TIME			= DateHelper.getDate(2000, 1, 1, 0, 0, 0).getTime();

	//长租计费类型
	public static final String	USER_TYPE_ENTERPRISE	= "USER_TYPE_ENTERPRISE";												// 企业会员标识

	/**
	 * 短信校验码 session 标识
	 */
	public static final String	REGIST_VAILD_CODE		= "REGIST_VAILD_CODE";
	public static final String	REGIST_VAILD_LAST		= "REGIST_VAILD_LAST";

	public static final String	ccclubs_web_login		= "ccclubs_web_login";													// 登录用户在session中的标识
	public static final String	ccclubs_web_login_id	= "ccclubs_web_login_id";												// 登录用户ID在session中的标识
	public static final String	ccclubs_web_host		= "ccclubs_web_host";													// 登录用户ID在session中的标识
	public static final String	ccclubs_web_openid		= "ccclubs_web_openid";
	
	public static final String  ccclubs_alipay_cert     = "ccclubs_alipay_cert";
	public static final String  ccclubs_alipay_addr     = "ccclubs_alipay_addr";
	public static final String  ccclubs_alipay_name     = "ccclubs_alipay_name";
	
	public static final String	IP_FETCH_URL			= "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

	public static final String	UTF8					= "UTF-8";
	public static final String	ERROR_TIPS				= "系统繁忙，请稍候再试";

	public static final String	data_source				= "ccclubs_system";													// 数据源配置标识

	public static final Object	APP_KEYWORD				= "APP通信密钥";															// APP通信密钥

	public static final String	ORDER_SET_START			= "ORDER_SET_START";													// 订单开始时间
	public static final String	ORDER_SET_FINISH		= "ORDER_SET_FINISH";													// 订单结束时间

	
	public static final String PRIVATE_KEY = "08afb98e33f26ab1ae7dc7ca27fbd956";
	
	
	public enum IntegralType {
		加积分,
		减积分,
		订单消费, 
		订单取消, 
		违章委托, 
		消费得积分, 
		事故扣款, 
		支付宝充值, 
		余额退款, 
		套餐订购, 
		注册送积分, 
		积分兑换, 
		手机认证, 
		上传头像, 
		完善个人基本信息, 
		完善紧急联系人信息, 
		邀请好友, 
		购买套餐, 
		购买现金券, 
		抽奖花费积分, 
		抽奖得积分, 
		抽奖得现金券, 
		抽奖得免费小时, 
		注册送现金券, 
		支付宝钱包注册送现金券, 
		兑换现金券, 
		芝麻信用还款,
		现金充值
	}
	
	
	public enum RecordType{
		支付宝充值(false,false,false,true,false,false,false),
		招行充值(false,false,false,true,false,false,false),
		银联在线充值(false,false,false,true,false,false,false),
		现金充值(false,false,false,true,false,false,false),
		POS机充值(false,false,false,true,false,false,false),
		余额退款(true,false,false,false,true,false,false),
		信用卡入帐(false,false,false,true,false,false,false),
		信用卡支付(true,true,false,false,true,false,false),
		购买现金券(false,false,false,false,true,false,false),
		赠送现金券(false,false,false,true,false,false,false),
		现金券充值(false,false,false,true,false,false,false),
		兑换现金券(false,false,false,true,false,false,false),
		现金券转入(false,false,false,true,false,false,false),
		现金券转出(false,false,false,false,true,false,false),
		//套餐订购(true,false,false,false,true,false,false),
		购买套餐(true,false,false,false,true,false,false),
		套餐返还(true,false,true,true,false,true,false),
		订单消费(true,true,false,false,true,false,true),
		订单取消(true,true,false,false,true,false,true),
		订单退款(true,false,true,true,false,true,true),
		订单补扣(true,true,false,false,true,true,true),
		订单补偿(true,false,true,true,false,true,true),
		专车订单(true,false,false,false,true,false,true),
		违章代办(true,true,false,false,true,false,false),
		违章返还(true,false,true,true,false,true,false),
		事故扣款(true,true,false,false,true,false,false),
		事故返还(true,false,true,true,false,true,false),
		会员卡工本费(true,true,false,false,true,true,false),
		自助加油补偿(false,false,true,true,false,true,false),
		停车代付补偿(false,false,true,true,false,true,false),
		停车费补扣(false,true,false,false,true,true,false),
		陪驾费用(true,true,false,false,true,true,false),
		人工服务费(true,true,false,false,true,true,false),
		车辆调度费(true,true,false,false,true,true,false),
		异地还车费(true,true,false,false,true,true,false),
		机场服务费(true,true,false,false,true,true,false),
		罚金罚款(true,true,false,false,true,true,false),
		误扣返还(true,false,true,true,false,true,false);
		Boolean income=false;
		Boolean operin=false;
		Boolean operout=false;
		Boolean userin=false;
		Boolean userout=false;
		Boolean artif=false;//是否人工操作项
		Boolean order=false;//是否订单项
		RecordType(Boolean income,Boolean operin,Boolean operout,Boolean userin,Boolean userout,Boolean artif,Boolean order){
			this.income=income;
			this.operin=operin;
			this.operout=operout;
			this.userin=userin;
			this.userout=userout;
			this.artif=artif;
			this.order=order;
		}
		public Boolean getIncome() {
			return income;
		}
		public void setIncome(Boolean income) {
			this.income = income;
		}
		public Boolean getOperin() {
			return operin;
		}
		public void setOperin(Boolean operin) {
			this.operin = operin;
		}
		public Boolean getOperout() {
			return operout;
		}
		public void setOperout(Boolean operout) {
			this.operout = operout;
		}
		public Boolean getUserin() {
			return userin;
		}
		public void setUserin(Boolean userin) {
			this.userin = userin;
		}
		public Boolean getUserout() {
			return userout;
		}
		public void setUserout(Boolean userout) {
			this.userout = userout;
		}
		public Boolean getArtif() {
			return artif;
		}
		public void setArtif(Boolean artif) {
			this.artif = artif;
		}
		public Boolean getOrder() {
			return order;
		}
		public void setOrder(Boolean order) {
			this.order = order;
		}
	}
	
	static{
		for(RecordType recordType:RecordType.values()){
			CsRecordSubject csRecordSubject = CsRecordSubject.where().csrsName(recordType.name()).get();
			if(csRecordSubject==null){
				csRecordSubject =new CsRecordSubject(
					recordType.name()//科目名称 [非空]
				 	,recordType.getIncome()//系统收入项 [非空]
				 	,recordType.getOperin()//商家收入项 [非空]
				 	,recordType.getOperout()//商家支出项 [非空]
				 	,recordType.getUserin()//会员收入项 [非空]
				 	,recordType.getUserout()//会员支出项 [非空]
				 	,recordType.getArtif()//人工操作项 [非空]
				 	,recordType.getOrder()//订单相关项 [非空]
				 	,false//是否开票项 [非空]
				 ).save();
			}
		}
	}
	
	public enum GrowRecordType {
		订单消费(false,true), 
		电动车租用(false,true), 
		还车超时(false,false), 
		交通事故(false,false), 
		违章处理超时(false,false);
		Boolean artif=false;//是否人工操作项
		Boolean order=false;//是否订单项
		GrowRecordType(Boolean artif,Boolean order){
			this.artif=artif;
			this.order=order;
		}
		public Boolean getArtif() {
			return artif;
		}
		public void setArtif(Boolean artif) {
			this.artif = artif;
		}
		public Boolean getOrder() {
			return order;
		}
		public void setOrder(Boolean order) {
			this.order = order;
		}
	}

	public static final String	NEW_YEAR_PACK_2013_			= "NEW_YEAR_PACK_2013_";		// 2013春节套餐标识前缀
	public static final String	NEW_YEAR_PACK_2013_KY		= "NEW_YEAR_PACK_2013_KY";		// 别克2013春节套餐标识前缀
	public static final String	NEW_YEAR_PACK_2013_MG		= "NEW_YEAR_PACK_2013_MG";		// 名爵2013春节套餐标识前缀
	public static final String	NEW_YEAR_PACK_2013_SMART	= "NEW_YEAR_PACK_2013_SMART";	// SMART2013春节套餐标识前缀
	
	

	/**
	 * 根据名称、标识、默认值获取变量
	 * 
	 * @param name
	 * @param flag
	 * @param defaultValue
	 * @return
	 */
	public static CsConfig getHostConfig(Long host) {
		// 获取配置,利用SrvHost缓存以用SrvHost与CsConfig一对一的特点缓存CsConfig到SrvHost中
		SrvHost srvHost = SrvHost.get(host);
		Long configid = (Long) srvHost.getValues().get("configid");
		CsConfig csConfig = null;
		if (configid != null) {
			csConfig = CsConfig.get(configid);
		} else {
			csConfig = CsConfig.Get($.add(CsConfig.F.cscHost, host));
			if (csConfig != null)
				srvHost.setValues("configid", csConfig.getCscId());
		}
		if (csConfig == null)
			return new CsConfig();
		return csConfig;
	}

	public static String	NotRevenueIds	= null;	// 非营收会员IDS

	/**
	 * 获取非营收会员IDS
	 * @return
	 */
	public static String getNotRevenueIdsEx() {
		List<CsMember> members = CsMember.list($.add(CsMember.F.csmNotRevenue, 1), 1024);
		String ids = "0";
		for (CsMember member : members)
			ids += "," + member.getCsmId();
		if (!$.equals(ids, "0"))
			NotRevenueIds = ids;
		else {
			throw new RuntimeException("请通知管理员,未找到员工用车帐号列表");
		}
		return ids;
	}

	/**
	 * 根据名称、标识、默认值获取变量
	 * 
	 * @param name
	 * @param flag
	 * @param defaultValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getArgumentValue(String name, String flag, Object defaultValue) {
		ICsArgumentService csArgumentService = Lazy.GetSpringBean("csArgumentService");
		CsArgument csArgument = csArgumentService.getCsArgument($.add("csaFlag", flag));
		if (csArgument == null)
			csArgument = csArgumentService.saveCsArgument(new CsArgument().csaName(name).csaFlag(flag).csaValue(defaultValue.toString()));
		try {
			if (defaultValue.getClass().getSimpleName().equals("String"))
				return (T) csArgument.getCsaValue();
			else
				return (T) defaultValue.getClass().getMethod("valueOf", String.class).invoke(null, csArgument.getCsaValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) defaultValue;
	}

	/**
	 * 获取默认的域 这儿是给后台管理系统用的哦！！！！！！！！！！！！！！！！！！！！！！！！
	 * 
	 * @return
	 */
	public static Long getDefaultHost() {
		Long user_login_host = null;
		Long session_like_host = null;
		Long defaultHostId = null;
		Long configDefaultHost = null;
		try {
			session_like_host = $.getSession("session_like_host");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if(!$.empty(LoginHelper.getLogin().getSuHost()) && session_like_host!=null){
				String strHosts = LoginHelper.getLogin().getSuHost();
				for(String sId:strHosts.split(",")){
					if($.trim(sId).equals(session_like_host.toString()))
						user_login_host = session_like_host;
				}
			}else if(!$.empty(LoginHelper.getLogin().getSuHost())){
				user_login_host = Long.parseLong(LoginHelper.getLogin().getSuHost().split(",")[0]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (user_login_host == null && session_like_host == null) {
				SrvHost defaultSrvHost = SrvHost.getSrvHost($.add(SrvHost.F.shStatus, 1).add("asc", SrvHost.C.shId));
				if(defaultSrvHost!=null)
					$.setSession("session_like_host", defaultHostId = defaultSrvHost.getShId());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (!$.empty($.config("default.host")))
				configDefaultHost = Long.parseLong($.config("default.host"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return $.or(user_login_host, session_like_host, defaultHostId, configDefaultHost);
	}

}
