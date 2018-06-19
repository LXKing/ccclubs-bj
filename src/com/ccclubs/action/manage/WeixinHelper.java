package com.ccclubs.action.manage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AliTrustRiskIdentify;
import com.alipay.api.domain.ZhimaRiskDetail;
import com.alipay.api.request.AlipayMobilePublicMessagePushRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTrustUserRiskidentifyGetRequest;
import com.alipay.api.request.AlipayTrustUserScoreGetRequest;
import com.alipay.api.request.AlipayTrustUserTokenGetRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipayMobilePublicMessagePushResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayTrustUserRiskidentifyGetResponse;
import com.alipay.api.response.AlipayTrustUserScoreGetResponse;
import com.alipay.api.response.AlipayTrustUserTokenGetResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import com.ccclubs.action.weixin.WeixinTokenHelper;
import com.ccclubs.action.weixin.alipay.constants.AlipayServiceEnvConstants;
import com.ccclubs.action.weixin.alipay.factory.AlipayAPIClientFactory;
import com.ccclubs.action.weixin.alipay.model.AliTrustUserInfo;
import com.ccclubs.action.weixin.alipay.util.CoderUtil;
import com.ccclubs.action.weixin.alipay.util.DisplayUtil;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRemote;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.From;
import com.ccclubs.util.DateUtil;
import com.ccclubs.util.UUIDGenerator;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

public class WeixinHelper {


	static DefaultJRedisClient<String, Object> jr = null;
	
	protected static DefaultJRedisClient<String, Object> getRedis(){
		if(jr == null){
			jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_WEIXIN);
		}
		return jr;
	}

	/**
	 * 获得当前用户的openid
	 * 
	 * @return
	 */
	public static String getOpenId() {
		return $.getSession(SYSTEM.ccclubs_web_openid);
	}

	/**
	 * 设置当前用户的openid
	 * 
	 * @return
	 */
	public static void setOpenId(String openid) {
		$.setSession(SYSTEM.ccclubs_web_openid, openid);
	}

	/**
	 * 返回当前 openid 对应的 CsMember
	 * <p>
	 * 并设置当前的CsMember session
	 * </p>
	 * 
	 * @return
	 */
	public static CsMember getCsMember() {
		
		CsMember user = null;
		String openid = getOpenId();
		if (!isCorrectOpenId()) {
			return user;
		}
		if (SystemHelper.getLogin() != null) {
			user = SystemHelper.getLogin();
			return user;
		}
		ICsMemberService csMemberService = LZ.GetSpringBean("csMemberService");

		try {
			Object userId = getRedis().get(openid);
			if (userId != null && !$.empty(userId.toString())) {
				// 根据用户 id 获取对应用户
				user = csMemberService.getCsMember($.add(CsMember.F.csmId, userId).add(CsMember.F.csmStatus, 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 记录一些东西
				String classPath = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wx.txt";
				WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + e.getMessage(), "UTF-8");
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			user = csMemberService.getCsMember($.add("definex", " csm_status = 1 and ( csm_weixin_flag = '" + openid + "'" + " or csm_alipay_flag = '" + openid + "' )"));
		}

		if (user != null) {
			$.setSession(SYSTEM.ccclubs_web_login, user);
			$.setSession(SYSTEM.ccclubs_web_login_id, user.getCsmId());
		}
		return user;
	}

	/**
	 * 强制刷新当前用户的登录信息，用户余额，现金券，积分等即时性较强
	 */
	public static void refreshLoginSession() {
		CsMember user = null;
		String openid = getOpenId();
		if (isCorrectOpenId()) {
			ICsMemberService csMemberService = LZ.GetSpringBean("csMemberService");
			try {
				Object userId = getRedis().get(openid);
				if (!$.empty(userId.toString())) {
					// 根据用户 id 获取对应用户
					user = csMemberService.getCsMember($.add(CsMember.F.csmId, userId).add(CsMember.F.csmStatus, 1));
				}
			} catch (Exception e) {
				user = csMemberService.getCsMember($.add("definex", " csm_status = 1 and ( csm_weixin_flag = '" + openid + "'" + " or csm_alipay_flag = '" + openid + "' )"));
			}
			if (user != null) {
				$.setSession(SYSTEM.ccclubs_web_login, user);
				$.setSession(SYSTEM.ccclubs_web_login_id, user.getCsmId());
			}
		}
	}

	/**
	 * 判断当前 openid 的正确性
	 * 
	 * @return
	 */
	public static Boolean isCorrectOpenId() {
		String from = getOpenId();
		if ($.empty(from)) {
			// $.SetTips("非微信入口，请使用微信链接");
			return false;
		}
		// 微信openid长度为固定长度28，支付宝openid长度为固定长度16
		if (from.length() == 28 || from.length() == 16 || from.length() == 66) {
			return true;
		}
		return false;
	}

	/**
	 * 判断当前 openid 属于哪个平台(注册) 微信平台是2，支付宝平台是4
	 * 
	 * @return
	 */
	public static From switchRegPlatform(String openid) {
		String from = getOpenId();
		if ($.empty(from)) {
			// $.SetTips("非微信入口，请使用微信链接");
			return From.NULL;
		}
		switch (from.length()) {
			case 28:
				return From.微信;
			case 16:
			case 66:
				return From.支付宝;
			default:
				return From.NULL;
		}
	}

	/**
	 * 判断当前 openid 属于哪个平台(预订) 微信平台是2，支付宝平台是5
	 * 
	 * @return
	 */
	public static From switchOrderPlatform(String openid) {
		String from = getOpenId();
		if ($.empty(from)) {
			// $.SetTips("非微信入口，请使用微信链接");
			return From.NULL;
		}
		switch (from.length()) {
			case 28:
				return From.NULL;
			case 16:
			case 66:
				return From.支付宝;
			default:
				return From.NULL;
		}
	}

	/**
	 * 更新当前用户的登录信息，主要是最后登录时间，登录IP，总登录次数
	 */
	public static void updateUserLogin(CsMember user) {
		if (user == null) {
			return;
		}
		ICsMemberService csMemberService = LZ.GetSpringBean("csMemberService");
		HttpServletRequest request = ServletActionContext.getRequest();
		// 更新最后登录IP，最后登录时间，登录次数 +1
		if (user.getCsmLoginS() == null)
			csMemberService.updateCsMember$NotNull(new CsMember().csmLastIp(request.getRemoteAddr()).csmLastTime(new Date()).csmLoginS(1).csmId(user.getCsmId()));
		else {
			csMemberService.updateCsMember$NotNull(new CsMember().csmLastIp(request.getRemoteAddr()).csmLastTime(new Date()).csmLoginS(user.getCsmLoginS() + 1).csmId(user.getCsmId()));
		}
	}

	/**
	 * 跳转到登录页面或者403页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String gotoLogin() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String context = request.getContextPath();// 上下文路径
			String strMethod = request.getMethod().toUpperCase();// 请求方式
			String strSrc = request.getHeader("Referer") == null ? "" : request.getHeader("Referer");// 源地址

			// 目标地址
			String strDest = context + request.getServletPath();// 目标地址
			String strQueryString = request.getQueryString();// 目标地址参数

			if (strDest != null && !strDest.equals("") && strQueryString != null && !strQueryString.trim().equals("")) {
				strDest += "?" + strQueryString;
			}

			String strDirect = null;// 登录后转跳到的地址

			if (strMethod.equals("GET")) {
				strDirect = strDest;
				if (strDirect == null)
					strDirect = strSrc;
			} else if (strMethod.equals("POST")) {
				strDirect = strSrc;
				if (strDirect == null)
					strDirect = strDest;
			}

			// 当前的openid不存在
			if (!isCorrectOpenId()) {
				String strAgent = request.getHeader("user-agent");// 来源类型
				if (strAgent.toLowerCase().contains("micromessenger")) {
					// 微信授权登录
					String fetchURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinTokenHelper.WEIXIN_APP_ID + "&response_type=code&scope=snsapi_base&redirect_uri=";

					String baseURL = $.GetRequest("basePath");

					// String flag = $.getString("flag", "");
					// 请求微信授权地址，传递 appid，response_type，scope，redirect_uri 等参数
					response.sendRedirect(fetchURL + java.net.URLEncoder.encode(baseURL + "m/oauth2_weixin.html?redirect=" + strDirect, "UTF-8") + "&state=STATE#wechat_redirect");

				} else {
					// 支付宝授权登录 
					String authUrl = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=" + AlipayServiceEnvConstants.APP_ID + "&auth_skip=false" + "&scope=auth_userinfo&redirect_uri="
							+ URLEncoder.encode($.GetRequest("basePath") + "m/oauth2_alipay.html?redirect=" + strDirect, "UTF-8");
					response.sendRedirect(authUrl);
				}
			} else {
				// 是否已经绑定到车纷享会员，跳登录页面
				response.sendRedirect($.GetRequest("basePath") + "m/login.html?redirect=" + strDirect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 以覆盖方式写文件
	 * 
	 * @param fileName
	 * @param content
	 * @param encode
	 */
	public static void writeFile(String fileName, String content, String encode) {
		if (encode == null)
			encode = "UTF-8";
		try {
			fileName = fileName.replaceAll("\\\\", "/");
			fileName = fileName.replaceAll("//", "/");

			String filePath = fileName.substring(0, fileName.lastIndexOf("/"));

			if (!new File(filePath).exists())
				new File(filePath).mkdirs();

			FileOutputStream fos = new FileOutputStream(fileName);
			Writer out = new OutputStreamWriter(fos, encode);
			out.write(content);
			out.close();
		} catch (IOException ex) {
			System.out.println("写文件[" + fileName + "]到[" + fileName + "]时出错:" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 追加到文件末尾，并且换行
	 * 
	 * @param fileName
	 * @param content
	 * @param encode
	 */
	public static void writeAppendToFile(String fileName, String content, String encode) {
		if (encode == null)
			encode = "UTF-8";
		FileWriter writer = null;
		try {
			fileName = fileName.replaceAll("\\\\", "/");
			fileName = fileName.replaceAll("//", "/");

			String filePath = fileName.substring(0, fileName.lastIndexOf("/"));

			if (!new File(filePath).exists())
				new File(filePath).mkdirs();

			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter(fileName, true);
			writer.write(content + "\r\n");
		} catch (IOException ex) {
			System.out.println("写文件[" + fileName + "]到[" + fileName + "]时出错:" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 支付宝大商户接口，发送消息到指定的userId，userId不能为空，否则为群发
	 */
	public static void sendMsgFromUserId(String userId, String userName, CsOrder csOrder, String alipyUserId) {
		if (userId != null && userId.length() != 0) {
			ICsCarService carService = $.GetSpringBean("csCarService");
			String carNo = carService.getCsCarById(csOrder.getCsoCar()).getCscCarNo();// 车牌号码
			String desc = "尊敬的" + userName + "用户：<br>您最新订单" + csOrder.getCsoId$() + "已成功结算，请尽快完成订单支付。<br><br>车牌号码：" + carNo + "<br>还车时间：" + new DateUtil().dateToString(csOrder.getCsoRetTime(), null) + "<br>结算金额：" + csOrder.getCsoPayNeed() + "元<br>付款状态：未付款<br><br>点此完成付款。<br>"
					+ "及时付款有助于您积累良好信用，否则会影响您下次租车及免押金额度。" + "<br>若对订单结算有异议，请致电车纷享客服。";
			String url = $.GetRequest("basePath") + "account_orderdetail.html?order=" + csOrder.getCsoId();

			AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json");
			// 菜单内容
			String biz_content = "<XML><ToUserId><![CDATA[" + userId + "]]></ToUserId><AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID
					+ "]]></AppId><AgreementId><![CDATA[]]></AgreementId><CreateTime>1381547619675</CreateTime><MsgType><![CDATA[image-text]]></MsgType><ArticleCount>1</ArticleCount>" + "<Articles><Item><Title><![CDATA[订单结算]]></Title><Desc><![CDATA[" + desc
					+ "]]></Desc><ImageUrl><![CDATA[]]></ImageUrl><Url><![CDATA[" + url + "]]></Url></Item></Articles><Push><![CDATA[false]]></Push></XML>";
			// 创建菜单
			AlipayMobilePublicMessagePushRequest req = new AlipayMobilePublicMessagePushRequest();
			req.setBizContent(biz_content);
			AlipayMobilePublicMessagePushResponse res = null;
			try {
				res = client.execute(req);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}

			System.out.println(res.getBody());
		}
	}

	/**
	 * 支付宝大商户接口，发送消息到指定的userId，userId不能为空，否则为群发
	 */
	public static void sendCancelMsgFromUserId(String userId, String userName, CsOrder csOrder, String alipyUserId, Double cancelPayment) {
		if (userId != null && userId.length() != 0) {
			ICsCarService carService = $.GetSpringBean("csCarService");
			String carNo = carService.getCsCarById(csOrder.getCsoCar()).getCscCarNo();// 车牌号码
			String desc = "尊敬的" + userName + "用户：<br>您最新订单" + csOrder.getCsoId$() + "已成功取消，请尽快完成订单支付。<br><br>车牌号码：" + carNo + "<br>取消结算金额：" + new DecimalFormat("#####0.00").format(cancelPayment) + "元<br>付款状态：未付款<br><br>点此完成付款。<br>" + "及时付款有助于您积累良好信用，否则会影响您下次租车及免押金额度。" + "<br>若对订单结算有异议，请致电车纷享客服。";
			String url = $.GetRequest("basePath") + "account_orderdetail.html?order=" + csOrder.getCsoId();

			AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json");
			// 菜单内容
			String biz_content = "<XML><ToUserId><![CDATA[" + userId + "]]></ToUserId><AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID
					+ "]]></AppId><AgreementId><![CDATA[]]></AgreementId><CreateTime>1381547619675</CreateTime><MsgType><![CDATA[image-text]]></MsgType><ArticleCount>1</ArticleCount>" + "<Articles><Item><Title><![CDATA[订单结算]]></Title><Desc><![CDATA[" + desc
					+ "]]></Desc><ImageUrl><![CDATA[]]></ImageUrl><Url><![CDATA[" + url + "]]></Url></Item></Articles><Push><![CDATA[false]]></Push></XML>";
			// 创建菜单
			AlipayMobilePublicMessagePushRequest req = new AlipayMobilePublicMessagePushRequest();
			req.setBizContent(biz_content);
			AlipayMobilePublicMessagePushResponse res = null;
			try {
				res = client.execute(req);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}

			System.out.println(res.getBody());
		}
	}

	/**
	 * 远程开关门
	 * 
	 * @param carId
	 * @param conType
	 *            控制类型 [非空] 0:远程开门 1:远程关门 2:允许启动 3:断油电 4:初始化 5:同步时钟 6:远程取车
	 *            7:远程还车 8:远程升级 9:远程重启 10:GPS远程开 11:GPS远程关 12:读取RFID序列号
	 *            13:设置GPS坐标 14:查询手机号 15:刷卡器开 16:刷卡器关 17:远程鸣笛
	 * @param memberId
	 */
	public static void remoteController(long carId, String conType, long memberId) {
		ICsCarService carService = $.GetSpringBean("csCarService");
		CsCar csCar = carService.getCsCarById(carId);
		CsRemote csRemote = new CsRemote();
		csRemote.setCsrNumber(csCar.getCscNumber());
		csRemote.setCsrCar(csCar.getCscId());
		csRemote.setCsrEditor(memberId);
		csRemote.setCsrAddTime(new Date());
		csRemote.setCsrState((short) 0);
		csRemote.setCsrStatus((short) 0);
		csRemote.setCsrType(new Short(conType));
		csRemote.setCsrHost(csCar.getCscHost());
		csRemote.setCsrRemark("微网站客户端, memberId:[" + memberId + "]执行远程操作");

		com.ccclubs.service.common.impl.RemoteService.dealRemoteCommend(csRemote);
		// csRemote=csRemote.save();
	}

	/**
	 * 判断该会员是否授权过
	 * 
	 * @param csMember
	 * @return
	 */
	public static boolean isZhimaAuthToken(CsMember csMember) {
		boolean s = false;
		try {
			ICsCreditCardService creditService = $.GetSpringBean("csCreditCardService");
			CsCreditCard creditCard = creditService.getCsCreditCard($.add(CsCreditCard.F.csccMember, csMember.getCsmId()));
			if (creditCard == null) {
				return false;
			} else {
				String csccData = creditCard.getCsccData();
				if (csccData != null && !csccData.equals("")) {
					s = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return s;
	}

	/**
	 * 获取授权url（需要短信验证）
	 * 
	 * @param csMember
	 * @param paramstr
	 * @return
	 */
	public static String getAlipayZhimaAuthUrl(CsMember csMember, String paramstr) {
		Long id = csMember.getCsmId();
		ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		CsMemberInfo memberInfo = csMemberInfoService.getCsMemberInfo($.add("csmiMemberId", id));

		String name = csMember.getCsmName();
		String cert_no = memberInfo.getCsmiDriverNum();
		String mobile = csMember.getCsmMobile$();
		String is_checked = "T"; // F
		String charset = "gbk";
		String view = "web"; // web|mobile|window
		String state = paramstr;
		long timestamp = System.currentTimeMillis();
		String param = String.format("cert_no=%s&name=%s&mobile=%s&is_checked=%s&timestamp=%d", cert_no, name, mobile, is_checked, timestamp);
		String privateKey = AlipayServiceEnvConstants.PRIVATE_KEY;
		byte[] array;
		String url = "";
		try {
			array = CoderUtil.encryptByPrivateKey(param.getBytes(charset), privateKey);
			String params = CoderUtil.byteArrayToBase64(array);
			url = String.format("https://xy.alipay.com/auth/authorize.htm?appid=%s&params=%s&charset=%s&view=%s&state=%s", AlipayServiceEnvConstants.APP_ID, URLEncoder.encode(params, "gbk"), charset, view, URLEncoder.encode(state, "gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 获取授权url（不需要短信验证）
	 * 
	 * @param csMember
	 * @param paramstr
	 * @return
	 */
	public static String getWindowAlipayZhimaAuthUrl(CsMember csMember, String paramstr) {
		Long id = csMember.getCsmId();
		ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		CsMemberInfo memberInfo = csMemberInfoService.getCsMemberInfo($.add("csmiMemberId", id));
		if (memberInfo == null) {
			return $.Redirect("weixin/account.html");
		}

		String name = csMember.getCsmName();
		String cert_no = memberInfo.getCsmiDriverNum();
		String mobile = csMember.getCsmMobile$();
		String user_id = csMember.getCsmAlipayFlag();
		String is_checked = "T"; // F
		String charset = "gbk";
		String view = "window"; // web|mobile|window
		String state = paramstr;
		long timestamp = System.currentTimeMillis();
		String param = String.format("cert_no=%s&name=%s&user_id=%s&mobile=%s&is_checked=%s&timestamp=%d", cert_no, name, user_id, mobile, is_checked, timestamp);
		String privateKey = AlipayServiceEnvConstants.PRIVATE_KEY;
		byte[] array;
		String url = "";
		try {
			array = CoderUtil.encryptByPrivateKey(param.getBytes(charset), privateKey);
			String params = CoderUtil.byteArrayToBase64(array);
			url = String.format("https://xy.alipay.com/auth/authorize.htm?appid=%s&params=%s&charset=%s&view=%s&state=%s", AlipayServiceEnvConstants.APP_ID, URLEncoder.encode(params, "gbk"), charset, view, URLEncoder.encode(state, "gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 获取芝麻token
	 * 
	 * @param csMember
	 * @return
	 */
	public static String getAlipayZhimaToken(CsMember csMember) {
		Long id = csMember.getCsmId();
		ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		CsMemberInfo memberInfo = csMemberInfoService.getCsMemberInfo($.add("csmiMemberId", id));

		String token = "";
		try {
			AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json");
			Field field = client.getClass().getDeclaredField("connectTimeout");
			field.setAccessible(true);
			field.set(client, 10000);
			AlipayTrustUserTokenGetRequest request = new AlipayTrustUserTokenGetRequest();
			AliTrustUserInfo user = new AliTrustUserInfo();
			user.setCertNo(memberInfo.getCsmiDriverNum());
			user.setName(csMember.getCsmName());
			String requsetInfo = JSONObject.fromObject(user).toString();
			request.setAliTrustUserInfo(JSONObject.fromObject(user).toString());
			AlipayTrustUserTokenGetResponse response = client.execute(request);
			if (!response.isSuccess()) {
				// 请求发生异常
				DisplayUtil.displayErrorCode(response);
				if (response.getCode().equals("40003")) {// 未征得用户授权
					// 重新授权
					String url = getWindowAlipayZhimaAuthUrl(csMember, "first&redirect=account_credit");
					return $.Redirect(url);
				}
				// 记录一些东西
				String classPath = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_zhima.txt";
				String errorInfo = requsetInfo + ";ErrorCode:" + response.getCode() + "Msg:" + response.getMsg() + "SubCode=" + response.getSubCode() + "SubMsg=" + response.getSubMsg();
				WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + errorInfo, "UTF-8");
			} else {
				// 请求返回正常结果
				token = response.getAccessToken();
				System.out.println("查询成功：授权令牌=" + token);
				// 记录一些东西
				String classPath = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_zhima.txt";
				WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + requsetInfo + ";查询成功：授权令牌=" + token, "UTF-8");
				// 更新信用卡
				ICsCreditCardService cardService = $.GetSpringBean("csCreditCardService");
				CsCreditCard card = cardService.getCsCreditCard($.add(CsCreditCard.F.csccMember, csMember.getCsmId()));
				if (card != null) {
					updateCreditCard(csMember.getCsmId(), "0", card.getCsccStatus(), card.getCsccNo(), token, null);
				} else {
					updateCreditCard(csMember.getCsmId(), "0", null, null, token, null);
				}
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 是否拥有免押金资格，根据回传的芝麻分判断
	 * 
	 * @param token
	 * @return
	 */
	public static boolean getAlipayZhimaScore(CsMember csMember, String token) {
		boolean s = false;
		try {
			AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json");
			AlipayTrustUserScoreGetRequest request = new AlipayTrustUserScoreGetRequest();
			AlipayTrustUserScoreGetResponse response = client.execute(request, token);
			if (!response.isSuccess()) {
				// 请求发生异常
				DisplayUtil.displayErrorCode(response);
			} else {
				// 请求返回正常结果
				Long score = response.getAliTrustScore().getScore();
				System.out.println("查询芝麻分成功：" + score); // zhimaBcd0b9b6733b24740b37ec56907f4cX26
				if (score > AlipayServiceEnvConstants.ZHIMA_SCORE) {
					// 黑名单会员，信用卡不可用
					ICsCreditCardService cardService = $.GetSpringBean("csCreditCardService");
					CsCreditCard card = cardService.getCsCreditCard($.add(CsCreditCard.F.csccMember, csMember.getCsmId()));
					if (card != null) {
						if (!card.getCsccStatus().toString().equals("1")) {
							return false;
						}
					}

					// 更新信用卡
					if (card != null) {
						updateCreditCard(csMember.getCsmId(), "1", card.getCsccStatus(), card.getCsccNo(), token, score+"");
					} else {
						updateCreditCard(csMember.getCsmId(), "1", null, null, token, score+"");
					}
					s = true;
				} else {
					updateCreditCard(csMember.getCsmId(), "0", null, null, token, score+"");
				}
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 更新车纷享信用卡信息
	 * 
	 * @param csmId
	 * @param result
	 */
	private static void updateCreditCard(Long csmId, String result, Short csccStatus, String cardNo, String token, String score) {
		CsCreditCard creditCard = new CsCreditCard();
		creditCard.setCsccMember(csmId);
		creditCard.setCsccType(new Short("3"));
		creditCard.setCsccAddTime(new Date());
		creditCard.setCsccUpdateTime(new Date());
		if(score!=null){
			creditCard.setCsccScope(Integer.parseInt(score));
		}
		// 是否在信用卡表已有数据
		ICsCreditCardService cs_Credit_CardService = $.GetSpringBean("csCreditCardService");
		CsCreditCard cc = cs_Credit_CardService.getCsCreditCard($.add("csccMember", csmId));
		if (result.equals("1")) {
			// 插入车纷享信用卡
			creditCard.setCsccLimit(2000d);
			if (cc == null) {
				// 只有新增的信用卡状态打1，其余情况不做更新
				creditCard.setCsccStatus(new Short("1"));
				creditCard.setCsccNo(UUIDGenerator.getUUID());
				creditCard.setCsccData(token);
				cs_Credit_CardService.saveCsCreditCard(creditCard);
			} else {
				creditCard.setCsccId(cc.getCsccId());
				creditCard.setCsccStatus(csccStatus);
				creditCard.setCsccNo(cardNo);
				creditCard.setCsccData(token);
				cs_Credit_CardService.updateCsCreditCard(creditCard);
			}
		} else {
			creditCard.setCsccLimit(0d);
			if (cc == null) {
				creditCard.setCsccStatus(new Short("1"));
				creditCard.setCsccNo(UUIDGenerator.getUUID());
				creditCard.setCsccData(token);
				cs_Credit_CardService.saveCsCreditCard(creditCard);
			} else {
				creditCard.setCsccId(cc.getCsccId());
				creditCard.setCsccStatus(csccStatus);
				creditCard.setCsccNo(cardNo);
				creditCard.setCsccData(token);
				cs_Credit_CardService.updateCsCreditCard(creditCard);
			}
		}
	}

	/**
	 * 支付宝auth2接口，免登
	 * 
	 * @param auth_code
	 * @return alipay_user_id 支付宝userId<br>
	 *         user_status 用户状态（Q/T/B/W）。
	 *         Q代表快速注册用户;T代表已认证用户;B代表被冻结账户;W代表已注册，未激活的账户<br>
	 *         real_name 姓名<br>
	 *         cert_no 证件号码<br>
	 *         mobile 手机号码<br>
	 *         is_certified 是否通过实名认证。T是通过 F是没有实名认证<br>
	 *         is_id_auth T为是身份证认证，F为非身份证认证。<br>
	 *         cert_type_value
	 *         0:身份证;1:护照;2:军官证;3:士兵证;4:回乡证;5:临时身份证;6:户口簿;7:警官证;8:台胞证;9:营业执照;10其它证件<br>
	 *         address 详细地址
	 */
	public static HashMap<String, String> getAlipayAuthUserInfo(String auth_code) {
		HashMap<String, String> map = new HashMap<String, String>();
		String alipay_user_id = "";
		try {
			AlipayClient alipayClient = AlipayAPIClientFactory.getAlipayClient();

			// 使用auth_code换取接口访问令牌及用户OpenId
			AlipaySystemOauthTokenRequest authReq = new AlipaySystemOauthTokenRequest();
			authReq.setGrantType("authorization_code");// 授权类型，固定为authorization_code
			authReq.setCode(auth_code);
			AlipaySystemOauthTokenResponse authResponse = alipayClient.execute(authReq);
			System.out.println("使用auth_code换取接口访问令牌:" + authResponse.getAccessToken());
			// 成功获得authToken
			if (null != authResponse && authResponse.isSuccess()) {
				String access_token = authResponse.getAccessToken();
				alipay_user_id = authResponse.getAlipayUserId();
				System.out.println("alipay_user_id:" + alipay_user_id);
				// 账户信息共享接口
				AlipayUserUserinfoShareRequest userinfoShareRequest = new AlipayUserUserinfoShareRequest();
				AlipayUserUserinfoShareResponse userinfoShareResponse = alipayClient.execute(userinfoShareRequest, access_token);
				// 成功获得用户信息
				if (null != userinfoShareResponse && userinfoShareResponse.isSuccess()) {
					String user_status = userinfoShareResponse.getUserStatus();// 用户状态（Q/T/B/W）。
					// Q代表快速注册用户;T代表已认证用户;B代表被冻结账户;W代表已注册，未激活的账户
					String real_name = userinfoShareResponse.getRealName();
					String cert_no = userinfoShareResponse.getCertNo();
					String mobile = userinfoShareResponse.getMobile();
					String is_certified = userinfoShareResponse.getIsCertified();// 是否通过实名认证。T是通过
					// F是没有实名认证
					String is_id_auth = userinfoShareResponse.getIsIdAuth();// T为是身份证认证，F为非身份证认证。
					String cert_type_value = userinfoShareResponse.getCertTypeValue();// 0:身份证;1:护照;2:军官证;3:士兵证;4:回乡证;5:临时身份证;6:户口簿;7:警官证;8:台胞证;9:营业执照;10其它证件
					String address = userinfoShareResponse.getProvince() + userinfoShareResponse.getCity() + userinfoShareResponse.getArea() + userinfoShareResponse.getAddress();

					map.put("user_status", user_status);
					map.put("real_name", real_name);
					map.put("cert_no", cert_no);
					map.put("mobile", mobile);
					map.put("is_certified", is_certified);
					map.put("is_id_auth", is_id_auth);
					map.put("cert_type_value", cert_type_value);
					map.put("address", address);
				}
			}

			map.put("alipay_user_id", alipay_user_id);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
		}
		return map;
	}
	
	/**
	 * 风险名单接口
	 * @param card
	 * @return
	 * 
     *   +-------------------------------+
     *   | 名单类型编码  |含义           |
     *   +---------------+---------------+ 
     *   |S001           |蚂蚁微贷       |
     *   +---------------+---------------+
     *   |S002           |法院执行老赖   |
     *   +---------------+---------------+
     *   |S004           |外部金融机构   |
     *   +---------------+---------------+
     *   |S005           |租车行业       |
     *   +---------------+---------------+
     *   |S006           |酒店行业       |
     *   +---------------+---------------+
     *   |S007           |阿里风控       |
     *   +---------------+---------------+
     *   |S090           |特殊类型       |
     *   +---------------+---------------+
     *   
     *   +---------------+---------------+
     *   |风险类型编码   |含义           |
     *   +---------------+---------------+
     *   |R001           |借贷逾期       |
     *   +---------------+---------------+
     *   |R010           |法院执行老赖   |
     *   +---------------+---------------+
     *   |R016           |逾期未支付     |
     *   +---------------+---------------+
     *   |R017           |超期未还车     |
     *   +---------------+---------------+
     *   |R018           |逾期未支付     |
     *   +---------------+---------------+
     *   |R019           |虚假交易       |
     *   +---------------+---------------+
     *   |R020           |恶意评价       |
     *   +---------------+---------------+
     *   |R090           |30天内多次逾期 |
     *   +---------------+---------------+
     *   
     *   +-----------+--------------+
     *   |风险代码   |含义          |
     *   +-----------+--------------+
     *   |R00101     |逾期1-30天    |
     *   +-----------+--------------+
     *   |R00102     |逾期31-60天   |
     *   +-----------+--------------+
     *   |R00103     |逾期61-90天   |
     *   +-----------+--------------+
     *   |R00104     |逾期91-120天  |
     *   +-----------+--------------+
     *   |R00105     |逾期121-150天 |
     *   +-----------+--------------+
     *   |R00106     |逾期151-180天 |
     *   +-----------+--------------+
     *   |R00107     |逾期180天以上 |
     *   +-----------+--------------+
     *   |R01001     |法院执行老赖  |
     *   +-----------+--------------+
     *   |R01601     |逾期1-7天     |
     *   +-----------+--------------+
     *   |R01602     |逾期8-14天    |
     *   +-----------+--------------+
     *   |R01603     |逾期15-30天   |
     *   +-----------+--------------+
     *   |R01604     |逾期31天以上  |
     *   +-----------+--------------+
     *   |R01701     |超期1-7天     |
     *   +-----------+--------------+
     *   |R01702     |超期8-14天    |
     *   +-----------+--------------+
     *   |R01703     |超期15-30天   |
     *   +-----------+--------------+
     *   |R01704     |超期31天以上  |
     *   +-----------+--------------+
     *   |R01801     |逾期1-7天     |
     *   +-----------+--------------+
     *   |R01802     |逾期8-14天    |
     *   +-----------+--------------+
     *   |R01803     |逾期15-30天   |
     *   +-----------+--------------+
     *   |R01804     |逾期31天以上  |
     *   +-----------+--------------+
     *   |R01804     |逾期31天以上  |
     *   +-----------+--------------+
     *   |R01901     |虚假交易      |
     *   +-----------+--------------+
     *   |R02001     |恶意评价      |
     *   +-----------+--------------+
     *   |R09001     |30天内多次逾期|
     *   +-----------+--------------+
     *   
     *
	 */
	public static boolean getAlipayRisk(CsCreditCard card) {
		boolean risk = false;
		try{
			if(card.getCsccData()!=null||card.getCsccData().length()!=0){
				AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json");
				// 创建芝麻信用风险名单请求对象，请求类与接口名保持一致
		        AlipayTrustUserRiskidentifyGetRequest request = new AlipayTrustUserRiskidentifyGetRequest();
		        /**
		         * 设置风险名单类型编码
		         * 
		         * 1)设置一种:S001或者S002或者S003，表示只查询响应类型的风险名单
		         * 2)设置多种：S001,S002或者S001,S003或者S001,S002,S003，表示查询多种风险名单
		         * 3)不设置：不需要赋type参数，表示查询所有类型
		         */
		        request.setType("");
		        // 创建芝麻信用风险名单响应对象，响应类与接口名保持一致
		        AlipayTrustUserRiskidentifyGetResponse response = client.execute(request, card.getCsccData()); //第二个参数是token
		        if (!response.isSuccess()) {
		        	// 记录一些东西
					String classPath = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_zhima.txt";
					String errorInfo = card.getCsccData() + ";ErrorCode:" + response.getCode() + "Msg:" + response.getMsg() + "SubCode=" + response.getSubCode() +
							"SubMsg="+ response.getSubMsg();
					WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + errorInfo, "UTF-8");
		            // 请求发生异常
		            DisplayUtil.displayErrorCode(response);
		        } else {
		        	// 请求返回正常结果
		            AliTrustRiskIdentify identify = response.getAliTrustRiskIdentify();
	            	ICsCreditCardService cardservice = $.GetSpringBean("csCreditCardService");
		            if ("F".equals(identify.getIsRisk())) {
		            	risk = false;
		            	String remark = card.getCsccRemark();
		            	if(card.getCsccStatus().equals(new Short("2"))){
		            		card.setCsccStatus(new Short("1"));
			            	if(card.getCsccScope()==null||card.getCsccScope()==0){
				            	card.setCsccScope(0);
			            	}
			            	card.setCsccRemark("此用户已从芝麻信用风险名单中解除，由系统于" + new DateUtil().dateToString(new Date(), null) + "添加。" + remark);
			            	cardservice.updateCsCreditCard(card);
			            	// 记录一些东西
							String classPath = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_zhima.txt";
							String riskInfo = card.getCsccData() + ";" + "NO RISK";
							WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + riskInfo, "UTF-8");
		            	}
		            } else if ("T".equals(identify.getIsRisk())) {
		            	risk = true;
		            	card.setCsccLimit(0d);
		            	card.setCsccStatus(new Short("2"));
		            	if(card.getCsccScope()==null){
			            	card.setCsccScope(0);
		            	}
		            	//风险名单处理
		            	String remark = card.getCsccRemark();
		                List<ZhimaRiskDetail> details = identify.getDetails();
		                for (ZhimaRiskDetail d : details) {
		                	remark += "名单类型编码:" + getTypeStr(d.getType()) + ",风险类型编码:" + getRiskTypeStr(d.getRiskType()) + ",风险代码:" + getRiskCodeStr(d.getRiskCode());
		                	if(d.getStatus()!=null){
		                		remark += ",用户对该条记录存在异议:" + d.getStatus();
		                	}
		                	if(d.getStatement()!=null){
		                		remark += ",用户对核查过的负面记录仍保留意见:" + d.getStatement();
		                	}
		                	remark += ";";
		                }
		            	card.setCsccRemark("此用户处于芝麻信用风险名单中，加黑名单，由系统于" + new DateUtil().dateToString(new Date(), null) + "添加。" + remark);
		            	cardservice.updateCsCreditCard(card);
		            	// 记录一些东西
						String classPath = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_zhima.txt";
						String riskInfo = card.getCsccData() + ";" + remark;
						WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + riskInfo, "UTF-8");
		            }
		        }
			}
		}catch(AlipayApiException e){
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return risk;
	}
	private static String getTypeStr(String key){
		Map<String,String> map = new HashMap<String,String>();
		map.put("S001", "蚂蚁微贷");
		map.put("S002", "法院执行老赖");
		map.put("S003", "金融（支付类）");
		map.put("S004", "外部金融机构");
		map.put("S005", "租车行业");
		map.put("S006", "酒店行业");
		map.put("S007", "阿里风控");
		map.put("S090", "特殊类型");
		if(map.get(key)==null){
			return key;
		}else{
			return map.get(key);
		}
	}
	private static String getRiskTypeStr(String key){
		Map<String,String> map = new HashMap<String,String>();
		map.put("R001", "借贷逾期");
		map.put("R010", "法院执行老赖");
		map.put("R011", "盗卡者/盗卡者同伙");
		map.put("R012", "欺诈者/欺诈同伙");
		map.put("R013", "盗用操作/盗用者同伙");
		map.put("R014", "盗用支出/盗用者同伙");
		map.put("R015", "骗赔");
		map.put("R016", "逾期未支付");
		map.put("R017", "超期未还车");
		map.put("R018", "逾期未支付");
		map.put("R019", "虚假交易");
		map.put("R020", "恶意评价");
		map.put("R031", "身份冒用");
		map.put("R090", "30天内多次逾期");
		if(map.get(key)==null){
			return key;
		}else{
			return map.get(key);
		}
	}
	private static String getRiskCodeStr(String key){
		Map<String,String> map = new HashMap<String,String>();
		map.put("R00101", "逾期1-30天");
		map.put("R00102", "逾期31-60天");
		map.put("R00103", "逾期61-90天");
		map.put("R00104", "逾期91-120天");
		map.put("R00105", "逾期121-150天");
		map.put("R00106", "逾期151-180天");
		map.put("R00107", "逾期180天以上");
		map.put("R01001", "法院执行老赖");
		map.put("R01601", "逾期1-7天");
		map.put("R01602", "逾期8-14天");
		map.put("R01603", "逾期15-30天");
		map.put("R01604", "逾期31天以上");
		map.put("R01701", "超期1-7天");
		map.put("R01702", "超期8-14天");
		map.put("R01703", "超期15-30天");
		map.put("R01704", "超期31天以上");
		map.put("R01801", "逾期1-7天");
		map.put("R01802", "逾期8-14天");
		map.put("R01803", "逾期15-30天");
		map.put("R01804", "逾期31天以上");
		map.put("R01901", "虚假交易");
		map.put("R02001", "恶意评价");
		map.put("R09001", "30天内多次逾期");
		if(map.get(key)==null){
			return key;
		}else{
			return map.get(key);
		}
	}
	
	public static void main(String[] args) throws AlipayApiException {
		AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json");
        AlipayTrustUserRiskidentifyGetRequest request = new AlipayTrustUserRiskidentifyGetRequest();
        request.setType("");
        AlipayTrustUserRiskidentifyGetResponse response = client.execute(request, "zhimaBbed993a10c2a491f901f48a731eeaF60"); //第二个参数是token
        if (!response.isSuccess()) {
            DisplayUtil.displayErrorCode(response);
        } else {
            AliTrustRiskIdentify identify = response.getAliTrustRiskIdentify();
            if ("F".equals(identify.getIsRisk())) {
            } else if ("T".equals(identify.getIsRisk())) {
            	String remark = "";
                List<ZhimaRiskDetail> details = identify.getDetails();
                for (ZhimaRiskDetail d : details) {
                	remark += "名单类型编码:" + getTypeStr(d.getType()) + ",风险类型编码:" + getRiskTypeStr(d.getRiskType()) + ",风险代码:" + getRiskCodeStr(d.getRiskCode());
                	if(d.getStatus()!=null){
                		remark += ",用户对该条记录存在异议:" + d.getStatus();
                	}
                	if(d.getStatement()!=null){
                		remark += ",用户对核查过的负面记录仍保留意见:" + d.getStatement();
                	}
                	remark += ";";
                }
                System.out.println("此用户处于芝麻信用风险名单中，加黑名单，由系统于" + new DateUtil().dateToString(new Date(), null) + "添加。" + remark);
            }
        }
	}
}
