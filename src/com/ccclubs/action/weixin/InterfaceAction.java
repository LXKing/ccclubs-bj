package com.ccclubs.action.weixin;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayMobilePublicMenuUpdateRequest;
import com.alipay.api.request.AlipayMobilePublicMessageCustomSendRequest;
import com.alipay.api.response.AlipayMobilePublicMenuUpdateResponse;
import com.alipay.api.response.AlipayMobilePublicMessageCustomSendResponse;
import com.ccclubs.action.weixin.alipay.constants.AlipayServiceEnvConstants;
import com.ccclubs.action.weixin.alipay.constants.AlipayServiceEventConstants;
import com.ccclubs.action.weixin.alipay.factory.AlipayAPIClientFactory;
import com.ccclubs.action.weixin.alipay.util.AlipayMsgBuildUtil;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.model.CsWxMenu;
import com.ccclubs.model.CsWxMo;
import com.ccclubs.model.CsWxOrder;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsWxContentService;
import com.ccclubs.service.admin.ICsWxMenuService;
import com.ccclubs.service.admin.ICsWxMoService;
import com.ccclubs.service.admin.ICsWxOrderService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.lazy3q.web.helper.$;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

public class InterfaceAction {

	static String					wx_remote_account	= $.config("wx_remote_account");

	ISrvPropertyService				srvPropertyService;
	ICsWxOrderService				csWxOrderService;
	ICsWxContentService				csWxContentService;
	ICsWxMenuService				csWxMenuService;

	static Date						lastOrderTime		= null;							// 最后订单时间
	@SuppressWarnings( { "unchecked", "rawtypes" })
	static Map<String, CsWxOrder>	accurates			= new HashMap();					// 精确匹配指令配置
	@SuppressWarnings( { "unchecked", "rawtypes" })
	static List<CsWxOrder>			fuzzys				= new ArrayList();					// 模糊指令配置

	/**
	 * 根据用户会员发送内容获取指令配置
	 * 
	 * @param string
	 * @return
	 */
	private CsWxOrder getOrder(String content) {
		CsWxOrder order = csWxOrderService.getCsWxOrder($.add("desc", "cswo_time"));// 获取最后一条被修改过的指令配置
		if (order == null)
			return null;
		// TODO：未实现 如果最后修改指令的时间有变化，则重新读取指令集配置
		if (lastOrderTime == null || order.getCswoTime().getTime() != lastOrderTime.getTime()) {
			$.trace("修改指令的时间有变化，则重新读取指令集配置");
			accurates.clear();
			fuzzys.clear();
			List<CsWxOrder> list = csWxOrderService.getCsWxOrderList($.add("cswoStatus", 1), -1);
			for (CsWxOrder csWxOrder : list) {
				$.trace(csWxOrder.getCswoName(), csWxOrder.getCswoType().intValue() == 0 ? "精确匹配" : "模糊匹配");
				if (csWxOrder.getCswoType().intValue() == 0)// 精确匹配
					accurates.put(csWxOrder.getCswoName(), csWxOrder);
				else
					fuzzys.add(csWxOrder);
			}
			lastOrderTime = order.getCswoTime();
		}
		$.trace(content, accurates.get(content));
		if (accurates.get(content) != null)// 如果发送的内容在精确匹配指令中，则返回
			return accurates.get(content);
		for (CsWxOrder csWxOrder : fuzzys) {// 模糊指令匹配
			boolean bMatches = Pattern.compile(csWxOrder.getCswoName()).matcher(content).find();
			if (bMatches)
				return csWxOrder;
		}
		return accurates.get("*");
	}

	/**
	 * 菜单同步
	 * 
	 * @return
	 */
	public String synchro() {
		try {
			String strMessage = $.json($.add("微信", synchroWXMenu()).add("支付宝", synchroALMenu()).toString());
			return $.SendJsonp($.getString("callback"), $.add("message", strMessage), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendJsonp($.getString("callback"), $.add("message", "系统繁忙，请联系管理员"), "UTF-8");
		}
	}

	/* ###########################################微信的东西############################################## */
	/**
	 * 微信接口签入验证
	 */
	public String checkWXToken() throws Exception {
		String signature = $.getString("signature");
		String timestamp = $.getString("timestamp");
		String nonce = $.getString("nonce");
		String token = "513387369";
		String[] tmpArr = new String[] { token, timestamp, nonce };
		// 按照字母【A-Z】顺序排序
		Arrays.sort(tmpArr);
		String tmpStr = "";
		for (String s : tmpArr)
			tmpStr += s;
		byte[] bytes = tmpStr.getBytes();
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(bytes);
		String result = getFormattedText(md.digest());
		$.trace("微信token验证", result, signature);
		if ($.equals(result, signature))
			return $.SendHtml($.getString("echostr"), "UTF-8");
		else
			return null;
	}

	/**
	 * 微信入口 http://weixin.ccclubs.com/weixin/interface.htm
	 * http://weixin.ccclubs.com/weixin/index.htm chefenxiang evnet8819
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String execute() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String strMethod = request.getMethod().toUpperCase();// 请求方式
			if (strMethod.equals("GET"))
				return checkWXToken();
			if ($.empty(wx_remote_account))
				throw new ErrorException("UN_SET_WX_ACCOUNT", "未配置远程控制开发者微信号", "未配置远程控制开发者微信号");

			Xml requestXml = new Xml();
			InputStream input = ServletActionContext.getRequest().getInputStream();

			requestXml.Parse(input);
			Item requestRoot = requestXml.getRoot();

			Logger.getLogger("weixin").info(requestXml.toString());
			$.trace(requestXml.toString());
			// log
			String classPath = InterfaceAction.class.getResource("/").toURI().getPath() + "weixinTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wxMessage.txt";
			WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + requestXml.toString(), "UTF-8");

			String FromUserName = requestRoot.getCData("FromUserName");
			String CreateTime = requestRoot.getValue("CreateTime");
			String MsgType = requestRoot.getCData("MsgType");// 消息类型:text,image
			String Content = requestRoot.getCData("Content");// 文本消息内容
			String PicUrl = requestRoot.getCData("PicUrl");// 图片链接
			String Event = requestRoot.getCData("Event");// 事件
			String EventKey = requestRoot.getCData("EventKey");// 事件
			String Latitude = requestRoot.getValue("Latitude");
			String Longitude = requestRoot.getValue("Longitude");
			$.setSession("Latitude", Latitude);
			$.setSession("Longitude", Longitude);
			
			// add 2015-03-12 10:41 by qsxiaogang
			// 设置openid session
			if ($.empty(WeixinHelper.getOpenId())) {
				WeixinHelper.setOpenId(FromUserName);
				// 更新当前用户的登录信息
				WeixinHelper.updateUserLogin(WeixinHelper.getCsMember());
			}

			ICsWxMoService csWxMoService = $.getBean("csWxMoService");
			CsWxMo csWxMo = new CsWxMo();
			csWxMo.setCswmPlatform(Short.valueOf((short) 1));
			csWxMo.setCswmOpenid(FromUserName);
			csWxMo.setCswmHost(Long.valueOf(0L));
			csWxMo.setCswmMember(Long.valueOf(0L));

			csWxMo.setCswmMsgType(Short.valueOf((short) 0));
			csWxMo.setCswmContentType(Short.valueOf((short) 0));
			csWxMo.setCswmContent(Content);
			csWxMo.setCswmAddTime(new Date());
			csWxMo.setCswmMask(Integer.valueOf(0));
			csWxMo.setCswmStatus(Short.valueOf((short) 1));

			if (WeixinHelper.getCsMember() != null) {
				csWxMo.setCswmHost(WeixinHelper.getCsMember().getCsmHost());
				csWxMo.setCswmMember(WeixinHelper.getCsMember().getCsmId());
			}

			CsWxOrder csWxOrder = null;
			if ($.equals(MsgType, "text"))
				csWxOrder = this.getOrder(Content);
			else if ($.equals(MsgType, "event") && $.equals(Event, "CLICK")) {
				csWxOrder = this.getOrder(EventKey);
				csWxMo.setCswmMsgType(Short.valueOf((short) 1));
				csWxMo.setCswmContentType(Short.valueOf((short) 4));
			} else if ($.equals(MsgType, "event")) {
				csWxOrder = this.getOrder(Event);
				csWxMo.setCswmMsgType(Short.valueOf((short) 1));
				csWxMo.setCswmContentType(Short.valueOf((short) 3));
			} else
				return null;
			
			final CsWxMo finalCsWxMo = csWxMo;
			new Thread(){
				public void run(){
					finalCsWxMo.save();
				}
			}.start();
			

			if (csWxOrder == null)
				csWxOrder = csWxOrderService.saveCsWxOrder(new CsWxOrder().setNotNull("*", (short) 0, (short) 0, new Date(), (short) 1).cswoContent("您好欢迎来到车纷享"));

			if (csWxOrder.getCswoName().equals("*"))// 发送默认内容
				return sendWxDefault(FromUserName);

			if (csWxOrder.getCswoRtype().intValue() == 0)// 如果指令回复为文本内容
				return $.SendHtml(this.createWXTextXml(csWxOrder.getCswoContent(), FromUserName).toString().replace("&amp;", "&"), "UTF-8");
			else if (csWxOrder.getCswoRtype().intValue() == 1) {// 回复内容为图文模式
				List<CsWxContent> contents = csWxContentService.getCsWxContentList($.add("cswcOrder", csWxOrder.getCswoId()), 10);
				if (contents.size() == 0) {
					new ErrorException("UN_SET_WX_CONTENTS", "未配置微信回复内容", "微信指令[" + csWxOrder.getCswoName() + "]回复类型为图文模式，但是未配置其图文内容");
					return sendWxDefault(FromUserName);
				} else
					return $.SendHtml(this.createWXRichXml(contents, FromUserName).toString().replace("&amp;", "&"), "UTF-8");
			} else if (csWxOrder.getCswoRtype().intValue() == 2) {// 预定义过程
				SrvProperty srvProperty = srvPropertyService.getSrvPropertyById(csWxOrder.getCswoClass());
				if (srvProperty == null) {
					new ErrorException("UN_SET_WX_SCRIPT", "未配置微信预定义过程", "微信指令[" + csWxOrder.getCswoName() + "]回复类型为预定义过程，但是未配置其预定义过程");
					return sendWxDefault(FromUserName);
				} else {
					String sClassName = srvProperty.getSpValue();
					IWXScript wxScript = (IWXScript) Class.forName(sClassName).newInstance();
					if (wxScript.getReturnType() == IWXScript.ReturnType.TEXT) {
						return $.SendHtml(this.createWXTextXml(wxScript.getContent(FromUserName, Content), FromUserName).toString().replace("&amp;", "&"), "UTF-8");
					} else {
						return $.SendHtml(this.createWXRichXml(wxScript.getContents(FromUserName, Content), FromUserName).toString().replace("&amp;", "&"), "UTF-8");
					}
				}
			} else
				return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return null;
		}
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	static Map<String, Boolean>	defaults	= new HashMap();	// 未匹配到内容时发送默认内容，改为一天只发一次
	static Integer				defaultDay	= -1;

	@SuppressWarnings( { "deprecation", "unused" })
	private String sendWxDefault(String FromUserName) {
		if (new Date().getDate() != defaultDay.intValue()) {
			defaultDay = new Date().getDate();
			defaults.clear();
		}
		if (defaults.get(FromUserName) != null)
			return $.SendHtml(createTransferCustomerServiceXml(FromUserName).toString(), "UTF-8");
		defaults.put(FromUserName, true);
		CsWxOrder csWxOrder = this.getOrder("*");
		// 发送多客服信息
		return $.SendHtml(createTransferCustomerServiceXml(FromUserName).toString(), "UTF-8");
		// return $.SendHtml(this.createWXTextXml(csWxOrder.getCswoContent(),
		// FromUserName).toString().replace("&amp;", "&"), "UTF-8");
	}

	/**
	 * 同步菜单到微信
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	 * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
	 * 
	 * @return
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" })
	private String synchroWXMenu() throws Exception {
		String strAccessToken = $.http("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WeixinTokenHelper.WEIXIN_APP_ID + "&secret=" + WeixinTokenHelper.WEIXIN_APP_SECRET);
		Map mapAccessToken = $.eval(strAccessToken);

		Map menuMap = new HashMap();
		List<Map> buttons = new ArrayList();
		menuMap.put("button", buttons);
		List<CsWxMenu> roots = csWxMenuService.getCsWxMenuList($.add("cswmParent", 0).add("desc", "cswm_level"), -1);
		for (CsWxMenu csWxMenu : roots) {
			if (!$.idin(csWxMenu.getCswmSupport(), 0))
				continue;
			Map buttonMap = new HashMap();
			buttonMap.put("name", csWxMenu.getCswmName());
			if (csWxMenu.getCswmType().intValue() == 0) {
				buttonMap.put("type", "view");
				buttonMap.put("url", csWxMenu.getCswmUrlKey());
			} else {
				buttonMap.put("type", "click");
				buttonMap.put("key", csWxMenu.getCswmUrlKey());
			}
			List<CsWxMenu> childs = csWxMenuService.getCsWxMenuList($.add("cswmParent", csWxMenu.getCswmId()).add("desc", "cswm_level"), -1);
			if (!childs.isEmpty()) {
				List<Map> childbuttons = new ArrayList();
				buttonMap.put("sub_button", childbuttons);
				for (CsWxMenu childWxMenu : childs) {
					if (!$.idin(childWxMenu.getCswmSupport(), 0))
						continue;
					Map childButtonMap = new HashMap();
					childButtonMap.put("name", childWxMenu.getCswmName());
					if (childWxMenu.getCswmType().intValue() == 0) {
						childButtonMap.put("type", "view");
						childButtonMap.put("url", childWxMenu.getCswmUrlKey());
					} else {
						childButtonMap.put("type", "click");
						childButtonMap.put("key", childWxMenu.getCswmUrlKey());
					}
					childbuttons.add(childButtonMap);
				}
			}
			buttons.add(buttonMap);
		}

		String menu = $.json(menuMap);
		$.trace("提交", menu);
		String strCreateMenu = $.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + mapAccessToken.get("access_token"), menu, "UTF-8");
		$.trace("返回", strCreateMenu);

		Map mapCreateMenu = $.eval(strCreateMenu);

		return (String) mapCreateMenu.get("errmsg");
	}

	/**
	 * 
	 * @param csWxOrder
	 * @param fromUserName
	 * @return
	 */
	private Xml createWXRichXml(List<CsWxContent> contents, String openId) {
		Xml xml = new Xml();
		Item root = new Item("xml");
		xml.setRoot(root);
		root.addChild(new Item("ToUserName").setCData(openId));
		root.addChild(new Item("FromUserName").setCData(wx_remote_account));
		root.addChild(new Item("CreateTime").setCData("" + new Date().getTime()));
		root.addChild(new Item("MsgType").setCData("news"));
		root.addChild(new Item("ArticleCount").setValue("" + contents.size()));

		Item Articles = new Item("Articles");
		root.addChild(Articles);

		for (CsWxContent csWxContent : contents) {
			Item item = new Item("item");
			Articles.addChild(item);
			item.addChild(new Item("Title").setCData(csWxContent.getCswcTitle()));
			if (!$.empty(csWxContent.getCswcContnet()))
				item.addChild(new Item("Description").setCData(csWxContent.getCswcContnet()));
			if (!$.empty(csWxContent.getCswcImage()))
				item.addChild(new Item("PicUrl").setCData(csWxContent.getCswcImage()));
			if (!$.empty(csWxContent.getCswcThumb()))
				item.addChild(new Item("PicUrl").setCData(csWxContent.getCswcThumb()));
			if (!$.empty(csWxContent.getCswcLink())) {
				String strLink = csWxContent.getCswcLink();
				// strLink += (strLink.indexOf("?") > -1 ? "&" : "?") + "from="
				// + openId;
				item.addChild(new Item("Url").setCData(strLink));
			}
		}

		$.trace(xml.toString().replace("&amp;", "&"));

		return xml;
	}

	/**
	 * 返回文字内容
	 * 
	 * @param content
	 * @param openId
	 * @return
	 */
	public Xml createWXTextXml(String content, String openId) {
		Xml xml = new Xml();
		Item root = new Item("xml");
		xml.setRoot(root);
		root.addChild(new Item("ToUserName").setValue(openId));
		root.addChild(new Item("FromUserName").setValue(wx_remote_account));
		root.addChild(new Item("CreateTime").setValue("" + new Date().getTime()));
		root.addChild(new Item("MsgType").setValue("text"));
		root.addChild(new Item("Content").setValue(content.replace("{from}", openId)));

		$.trace(xml.toString().replace("&amp;", "&"));

		return xml;
	}

	/**
	 * 多客服系统信息
	 * 
	 * @param content
	 * @param openId
	 * @return
	 */
	public Xml createTransferCustomerServiceXml(String openId) {
		Xml xml = new Xml();
		Item root = new Item("xml");
		xml.setRoot(root);
		root.addChild(new Item("ToUserName").setCData(openId));
		root.addChild(new Item("FromUserName").setCData(wx_remote_account));
		root.addChild(new Item("CreateTime").setCData("" + new Date().getTime()));
		root.addChild(new Item("MsgType").setCData("transfer_customer_service"));

		$.trace(xml.toString());

		return xml;
	}

	private static final char[]	HEX_DIGITS	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	/* ###########################################微信的东西############################################## */

	/* ###########################################支付宝的东西############################################## */

	static String	alipay_app_id		= AlipayServiceEnvConstants.APP_ID;
	static String	alipay_app_key		= AlipayServiceEnvConstants.PUBLIC_KEY;
	static String	alipay_app_account	= "carclubs@163.com";
	// 私钥
	static String	rsa_private_key		= AlipayServiceEnvConstants.PRIVATE_KEY;

	/**
	 * 支付宝网关校验请求
	 */
	public String checkALToken() throws Exception {
		String strSign = "<success>true</success><biz_content>" + alipay_app_key + "</biz_content>";

		strSign = AlipaySignature.encryptAndSign(strSign, alipay_app_key, rsa_private_key, AlipayServiceEnvConstants.CHARSET, false, true);
		return $.SendHtml(strSign.toString(), AlipayServiceEnvConstants.CHARSET);
	}

	/**
	 * 支付宝客户端回调网关入口 http://weixin.ccclubs.com/weixin/interface-alipay.htm
	 * https://fuwu.alipay.com/platform/messageinfo.htm carclubs@163.com
	 * chelizi01
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String alipay() {
		try {
			if ($.empty(rsa_private_key)) {
				$.trace("未配置rsa加密私钥", "获取办法：https://openhome.alipay.com/doc/docIndex.htm?url=https://openhome.alipay.com/doc/viewKbDoc.htm?key=236615_428849&type=info");
				return null;
			}
			String xmlString = $.getString("biz_content");
			$.trace("支付宝公众信息接入。。。。", xmlString);

			// log
			// String classPath =
			// InterfaceAction.class.getResource("/").toURI().getPath() +
			// "alipayTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") +
			// "_alipayMessage.txt";
			// WeixinHelper.writeAppendToFile(classPath,
			// DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " +
			// xmlString, "UTF-8");

			Xml requestXml = new Xml();
			if (xmlString.startsWith("<XML>")) {
				requestXml.Parse("<?xml version=\"1.0\" encoding=\"gbk\"?>" + xmlString);
			} else {
				requestXml.Parse(xmlString);
			}

			Item requestRoot = requestXml.getRoot();

			Logger.getLogger("weixin").info(requestXml.toString());

			String FromUserName = requestRoot.getCData("FromUserId");
			String CreateTime = requestRoot.getValue("CreateTime");
			String MsgType = requestRoot.getCData("MsgType");// 消息类型:event
			String Event = requestRoot.getCData("EventType");// 事件类型。follow：关注消息；unfollow：取消关注；click：自定义菜单点击事件。
			String EventKey = requestRoot.getCData("ActionParam");// 事件内容
			String Content = EventKey;

			// 支付宝网关校验请求
			if ($.equals(Event, "verifygw")) {
				return checkALToken();
			}

			// add 2015-04-03 11:06 by qsxiaogang
			// 设置openid session
			// ############################################################
			if ($.empty(WeixinHelper.getOpenId())) {
				WeixinHelper.setOpenId(FromUserName);
				// 更新当前用户的登录信息
				WeixinHelper.updateUserLogin(WeixinHelper.getCsMember());
			}
			
			ICsWxMoService csWxMoService = $.getBean("csWxMoService");
			CsWxMo csWxMo = new CsWxMo();
			csWxMo.setCswmPlatform(Short.valueOf((short) 2));
			csWxMo.setCswmOpenid(FromUserName);
			csWxMo.setCswmHost(Long.valueOf(0L));
			csWxMo.setCswmMember(Long.valueOf(0L));

			csWxMo.setCswmMsgType(Short.valueOf((short) 0));
			csWxMo.setCswmContentType(Short.valueOf((short) 0));
			csWxMo.setCswmContent(Content);
			csWxMo.setCswmAddTime(new Date());
			csWxMo.setCswmMask(Integer.valueOf(0));
			csWxMo.setCswmStatus(Short.valueOf((short) 1));

			CsWxOrder csWxOrder = null;
			if ($.equals(MsgType, "event") && $.equals(Event, "click")) {
				csWxOrder = this.getOrder(EventKey);
				csWxMo.setCswmMsgType(Short.valueOf((short) 1));
				csWxMo.setCswmContentType(Short.valueOf((short) 4));
			} else if ($.equals(MsgType, "event")) {
				if ($.equals(Event, AlipayServiceEventConstants.ENTER_EVENT)) {
					// 支付宝钱包【出行】进入事件
					String actionParam = requestRoot.getCData("ActionParam");
					$.trace("--------" + actionParam);
					if (actionParam.indexOf("sceneId") != -1) {
						String sceneId = actionParam.substring(actionParam.indexOf("sceneId") + 10, 50);
						$.trace("--------" + sceneId);
						if (sceneId.equals(AlipayServiceEventConstants.SCENE_ID)) {
							// 推送异步单发消息
							String requestMsg = AlipayMsgBuildUtil.buildSingleImgTextMsg(FromUserName);
							AlipayClient alipayClient = AlipayAPIClientFactory.getAlipayClient();
							AlipayMobilePublicMessageCustomSendRequest request = new AlipayMobilePublicMessageCustomSendRequest();
							request.setBizContent(requestMsg);
							AlipayMobilePublicMessageCustomSendResponse response = alipayClient.execute(request);
						}
					}
				}
				csWxOrder = this.getOrder(Event);
				csWxMo.setCswmMsgType(Short.valueOf((short) 1));
				csWxMo.setCswmContentType(Short.valueOf((short) 3));
			} else {
				return null;
			}

			final CsWxMo finalCsWxMo = csWxMo;
			new Thread(){
				public void run(){
					finalCsWxMo.save();
				}
			}.start();
			
			if (csWxOrder == null)
				csWxOrder = csWxOrderService.saveCsWxOrder(new CsWxOrder().setNotNull("*", (short) 0, (short) 0, new Date(), (short) 1).cswoContent("您好欢迎来到车纷享"));

			if (csWxOrder.getCswoName().equals("*"))// 发送默认内容
				return sendAlipayDefault(FromUserName);

			if (csWxOrder.getCswoRtype().intValue() == 0)// 如果指令回复为文本内容
				return $.SendHtml(this.createAlipayTextXml(csWxOrder.getCswoContent(), FromUserName), $.GBK);
			else if (csWxOrder.getCswoRtype().intValue() == 1) {// 回复内容为图文模式
				List<CsWxContent> contents = csWxContentService.getCsWxContentList($.add("cswcOrder", csWxOrder.getCswoId()), 10);
				if (contents.size() == 0) {
					new ErrorException("UN_SET_WX_CONTENTS", "未配置微信回复内容", "微信指令[" + csWxOrder.getCswoName() + "]回复类型为图文模式，但是未配置其图文内容");
					return sendAlipayDefault(FromUserName);
				} else
					return $.SendHtml(this.createAlipayRichXml(contents, FromUserName), $.GBK);
			} else if (csWxOrder.getCswoRtype().intValue() == 2) {// 预定义过程
				SrvProperty srvProperty = srvPropertyService.getSrvPropertyById(csWxOrder.getCswoClass());
				if (srvProperty == null) {
					new ErrorException("UN_SET_WX_SCRIPT", "未配置微信预定义过程", "微信指令[" + csWxOrder.getCswoName() + "]回复类型为预定义过程，但是未配置其预定义过程");
					return sendAlipayDefault(FromUserName);
				} else {
					String sClassName = srvProperty.getSpValue();
					IWXScript wxScript = (IWXScript) Class.forName(sClassName).newInstance();
					if (wxScript.getReturnType() == IWXScript.ReturnType.TEXT) {
						return $.SendHtml(this.createAlipayTextXml(wxScript.getContent(FromUserName, Content), FromUserName), $.GBK);
					} else {
						return $.SendHtml(this.createAlipayRichXml(wxScript.getContents(FromUserName, Content), FromUserName), $.GBK);
					}
				}
			} else
				return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return null;
		}
	}

	/**
	 * 同步菜单到支付宝
	 * 
	 * @return
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" })
	private String synchroALMenu() throws Exception {

		Map menuMap = new HashMap();
		List<Map> buttons = new ArrayList();
		menuMap.put("button", buttons);
		List<CsWxMenu> roots = csWxMenuService.getCsWxMenuList($.add("cswmParent", 0).add("desc", "cswm_level"), -1);
		for (CsWxMenu csWxMenu : roots) {
			if (!$.idin(csWxMenu.getCswmSupport(), 1))
				continue;
			Map buttonMap = new HashMap();
			buttonMap.put("name", csWxMenu.getCswmName());
			if (csWxMenu.getCswmType().intValue() == 0) {
				buttonMap.put("actionType", "link");
				buttonMap.put("actionParam", csWxMenu.getCswmUrlKey());
			} else {
				buttonMap.put("actionType", "out");
				buttonMap.put("actionParam", csWxMenu.getCswmUrlKey());
			}
			List<CsWxMenu> childs = csWxMenuService.getCsWxMenuList($.add("cswmParent", csWxMenu.getCswmId()).add("desc", "cswm_level"), -1);
			if (!childs.isEmpty()) {
				List<Map> childbuttons = new ArrayList();
				buttonMap.put("subButton", childbuttons);
				for (CsWxMenu childWxMenu : childs) {
					if (!$.idin(childWxMenu.getCswmSupport(), 1))
						continue;
					Map childButtonMap = new HashMap();
					childButtonMap.put("name", childWxMenu.getCswmName());
					if (childWxMenu.getCswmType().intValue() == 0) {
						childButtonMap.put("actionType", "link");
						childButtonMap.put("actionParam", childWxMenu.getCswmUrlKey());
					} else {
						childButtonMap.put("actionType", "out");
						childButtonMap.put("actionParam", childWxMenu.getCswmUrlKey());
					}
					childbuttons.add(childButtonMap);
				}
			}
			buttons.add(buttonMap);
		}

		// 请求client对象, 构造方法参数分别是：
		// 1、请求支付宝网关URL地址
		// 2、app_id
		// 3、机构或开发者自己的RSA私钥
		AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipay_app_id, rsa_private_key, "json", "GBK");
		// 封装好的接口请求对象，接口不同请求对象也不同
		AlipayMobilePublicMenuUpdateRequest request = new AlipayMobilePublicMenuUpdateRequest();
		// 传入接口中规定的业务参数
		request.setBizContent($.json(menuMap));
		// 封装好的接口响应对象，接口不同响应对象也不同
		AlipayMobilePublicMenuUpdateResponse response = null;
		try { // 执行业务请求
			response = client.execute(request);
		} catch (AlipayApiException e) { // 业务调用异常处理
			e.printStackTrace();
		}
		// 响应结果
		return response == null ? "错误" : response.getMsg();
	}

	@SuppressWarnings("deprecation")
	private String sendAlipayDefault(String FromUserName) {
		if (new Date().getDate() != defaultDay.intValue()) {
			defaultDay = new Date().getDate();
			defaults.clear();
		}
		if (defaults.get(FromUserName) != null)
			return null;
		defaults.put(FromUserName, true);
		CsWxOrder csWxOrder = this.getOrder("*");
		return $.SendHtml(this.createAlipayTextXml(csWxOrder.getCswoContent(), FromUserName), $.GBK);
	}

	/*
	 * 返回文字内容
	 * 
	 * @param content @param openId @return
	 */
	public String createAlipayTextXml(String content, String openId) {
		Xml xml = new Xml();
		Item root = new Item("XML");
		xml.setRoot(root);

		String strLink = "";

		// 把内容中链接的{from}替换为接收人
		content = content.replace("{from}", openId);
		// 提取内容中的链接
		Pattern pattern = Pattern.compile("http://[^'^\\\"^>]+");
		Matcher matcher = pattern.matcher(content);
		if (matcher.find())
			strLink = matcher.group();
		// 去除内容中的链接
		content = content.replaceAll("<[^>]+>", "");

		root.addChild(new Item("AppId").setCData(alipay_app_id));
		root.addChild(new Item("ToUserId").setCData(openId));
		root.addChild(new Item("CreateTime").setValue("" + new Date().getTime()));
		root.addChild(new Item("MsgType").setCData("image-text"));

		root.addChild(new Item("ArticleCount").setValue("1"));

		Item Articles = new Item("Articles");
		root.addChild(Articles);
		Item Item = new Item("Item");
		Articles.addChild(Item);

		Item.addChild(new Item("Title").setCData(""));
		Item.addChild(new Item("Desc").setCData(content));
		Item.addChild(new Item("ImageUrl").setCData(""));
		Item.addChild(new Item("Url").setCData(strLink));

		$.trace(root.toString().replace("&amp;", "&"));

		return root.toString().replace("&amp;", "&");
	}

	/**
	 * 
	 * @param csWxOrder
	 * @param fromUserName
	 * @return
	 */
	private String createAlipayRichXml(List<CsWxContent> contents, String openId) {
		Xml xml = new Xml();
		Item root = new Item("XML");
		xml.setRoot(root);

		root.addChild(new Item("AppId").setCData(alipay_app_id));
		root.addChild(new Item("ToUserId").setCData(openId));
		root.addChild(new Item("CreateTime").setValue("" + new Date().getTime()));
		root.addChild(new Item("MsgType").setCData("image-text"));

		root.addChild(new Item("ArticleCount").setValue("" + contents.size()));

		Item Articles = new Item("Articles");
		root.addChild(Articles);

		for (CsWxContent csWxContent : contents) {
			Item item = new Item("Item");
			Articles.addChild(item);
			item.addChild(new Item("Title").setCData(csWxContent.getCswcTitle()));
			if (!$.empty(csWxContent.getCswcContnet()))
				item.addChild(new Item("Desc").setCData(csWxContent.getCswcContnet()));
			if (!$.empty(csWxContent.getCswcImage()))
				item.addChild(new Item("ImageUrl").setCData(csWxContent.getCswcImage()));
			if (!$.empty(csWxContent.getCswcThumb()))
				item.addChild(new Item("ImageUrl").setCData(csWxContent.getCswcThumb()));
			if (!$.empty(csWxContent.getCswcLink())) {
				String strLink = csWxContent.getCswcLink();
				// strLink += (strLink.indexOf("?") > -1 ? "&" : "?") + "from="
				// + openId;
				item.addChild(new Item("Url").setCData(strLink));
			}
		}

		$.trace(root.toString().replace("&amp;", "&"));

		return root.toString().replace("&amp;", "&");
	}

	/* ###########################################支付宝的东西############################################## */

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}

	public ICsWxOrderService getCsWxOrderService() {
		return csWxOrderService;
	}

	public void setCsWxOrderService(ICsWxOrderService csWxOrderService) {
		this.csWxOrderService = csWxOrderService;
	}

	public ICsWxContentService getCsWxContentService() {
		return csWxContentService;
	}

	public void setCsWxContentService(ICsWxContentService csWxContentService) {
		this.csWxContentService = csWxContentService;
	}

	public ICsWxMenuService getCsWxMenuService() {
		return csWxMenuService;
	}

	public void setCsWxMenuService(ICsWxMenuService csWxMenuService) {
		this.csWxMenuService = csWxMenuService;
	}
}
