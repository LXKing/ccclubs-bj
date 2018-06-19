package com.ccclubs.action.m.admin;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.action.weixin.WeixinTokenHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOutsideQr;
import com.ccclubs.model.CsRemote;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsOutsideQrService;
import com.ccclubs.service.admin.ICsRemoteService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.ccclubs.service.admin.ISrvUserService;
import com.lazy3q.web.helper.$;

/**
 * 微信扫描二维码，接收结果
 * 
 * @author qsxiaogang http://m.ccclubs.com/weixin/admin/scan.html?key=zja5fx36
 */
public class ScanAction {
	/**
	 * <li>1、获取 openid（先取session里的openid，如果没有跳转到获取openid的授权）</li>
	 * <li>2、通过 openid 在 SrvUserExp 表 通过字段 sue_wx_openid 查询对应后台用户信息</li>
	 * <li>3、如果没有后台用户信息，需要让微信用户绑定后台帐号</li>
	 * <li>4、显示当前车牌号，车辆信息（所在城市，网点）车辆位置 </li>
	 * <li>5、获取我的位置（在地图上查看我的位置）</li>
	 * <li>6、点击发送“巡检签到”</li>
	 * 
	 * 
	 * <li>同一个人的事务，必须完结，如果有未完成的，不能选择，必须结束</li>
	 * <li>param key（车牌号）</li>
	 * 
	 * @return
	 */
	// 操作
	private static String	sApp		= "wxoutsideqr";
	private static String	downloadUrl	= "http://photo.ccclubs.com/down.do";

	public String execute() {
		// 取session
		String carNo = $.getString("key", "");
		if (!$.empty(carNo)) {
			$.setSession("carNo", carNo);
		} else {
			carNo = $.getSession("carNo") == null ? "" : $.getSession("carNo").toString();
		}

		if ($.empty(WeixinHelper.getOpenId())) {
			// 微信授权登录
			$.Redirect($.GetRequest("basePath") + "weixin/oauth2.html?redirect=weixin/admin/scan.html?key=" + carNo);
			return null;
		}
		// 扩展用户信息
		ISrvUserExpService srvUserExpService = $.getBean("srvUserExpService");
		SrvUserExp srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueWxOpenid, WeixinHelper.getOpenId()));
		if (srvUserExp == null) {
			$.Redirect($.GetRequest("basePath") + "weixin/index.html");
			return null;
		}
		$.SetRequest("srvUserExp", srvUserExp);
		// 查询车辆信息
		ICsCarService csCarService = $.getBean("csCarService");
		CsCar carInfo = csCarService.getCsCar($.add(CsCar.F.cscNumber, carNo.toUpperCase()));
		if (carInfo == null) {
			$.Redirect($.GetRequest("basePath") + "weixin/403.html");
			return null;
		}
		$.SetRequest("carInfo", carInfo);
		// 查找未完结的作业
		ICsOutsideQrService csOutsideQrService = $.getBean("csOutsideQrService");
		CsOutsideQr csOutsideQr = csOutsideQrService.getCsOutsideQr($.add(CsOutsideQr.F.csoqCar, carInfo.getCscId()).add(CsOutsideQr.F.csoqAdder, srvUserExp.getSueUser()).add("definex", CsOutsideQr.C.csoqEndTime + " is null "));
		$.SetRequest("csOutsideQr", csOutsideQr);
		// 设置作业类型
		$.SetRequest("types", SystemHelper.getPropertys("作业类型", "outsideqr_type"));

		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> ret = WeixinTokenHelper.getSign(WeixinTokenHelper.getCompleteUrl(request));
		$.SetRequest("map", ret);
		return "scan";
		// return $.SendHtml("scan.html", "UTF-8");
	}

	/**
	 * 提交签到信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String qiandao() {
		try {
			Double lng = $.getDouble("lng", 0d);
			Double lat = $.getDouble("lat", 0d);
			Long userid = $.getLong("userid", 0l);
			Long qrid = $.getLong("qrid", 0l);
			Long car = $.getLong("car", 0l);
			Long type = $.getLong("type", 0l);
			String images = $.getString("images", "");
			// 查询车辆信息
			ICsCarService csCarService = $.getBean("csCarService");
			CsCar carInfo = csCarService.getCsCar($.add(CsCar.F.cscId, car));
			if (carInfo == null) {
				// 你还没有车辆信息！
				return $.SendHtml("101", "UTF-8");
			}
			if (lng.equals(0d) || lat.equals(0d)) {
				// 你还没有位置信息！
				return $.SendHtml("102", "UTF-8");
			}
			if (userid.equals(0l)) {
				// 你还没有用户信息！
				return $.SendHtml("103", "UTF-8");
			}
			// 查找未完结的作业
			ICsOutsideQrService csOutsideQrService = $.getBean("csOutsideQrService");
			CsOutsideQr csOutsideQr = csOutsideQrService.getCsOutsideQr($.add(CsOutsideQr.F.csoqId, qrid));

			// 车辆当前状态信息
			ICsStateService csStateService = $.getBean("csStateService");
			if (csOutsideQr != null) {
				// 更新
				CsState csState = csStateService.getCsState($.add(CsState.F.cssNumber, carInfo.getCscNumber()));
				if (csState == null) {
					// 当前车辆状态信息没有
					return $.SendHtml("201", "UTF-8");
				}
				// 如果时间相差大于设置的阈值
				if (Math.abs(System.currentTimeMillis() - csState.getCssAddTime().getTime()) > 12 * 60 * 1000l) {
					// 当前车辆已掉线
					return $.SendHtml("202", "UTF-8");
				}
				if (csOutsideQr.getCsoqEndTime() == null) {
					Date endTime = new Date();
					Long timeLong = endTime.getTime() - csOutsideQr.getCsoqAddTime().getTime();
					CsOutsideQr csOutsideQrUpdate = new CsOutsideQr();
					csOutsideQrUpdate.setCsoqId(csOutsideQr.getCsoqId());
					csOutsideQrUpdate.setCsoqEndTime(endTime);
					csOutsideQrUpdate.setCsoqCarPosEnd(csState.getCssLongitude() + "," + csState.getCssLatitude());
					csOutsideQrUpdate.setCsoqUsePosEnd(lng + "," + lat);
					csOutsideQrUpdate.setCsoqDistanceEnd(getShortestDistance(lng, lat, Double.parseDouble(csState.getCssLongitude()), Double.parseDouble(csState.getCssLatitude())));
					csOutsideQrUpdate.setCsoqTimeLong((int) (timeLong / 1000 / 60));

					// 设置图片
					if (!$.empty(images)) {
						String[] imageArr = images.split(",");
						// 下载图片
						String imgUrls = "";
						for (int i = 0; i < imageArr.length; i++) {

							if ($.empty(imgUrls)) {
								Map mapResult = $.eval($.post(downloadUrl, $.Map().add("url", WeixinTokenHelper.WEIXIN_DOWNLOAD_MEDIA_URL.replace("{token}", WeixinTokenHelper.getToken()).replace("{media}", imageArr[i])).add("app", sApp), "UTF-8"));
								if (mapResult.get("code").toString().equals("200")) {
									imgUrls = imgUrls + mapResult.get("url");
								}
							} else {
								Map mapResult = $.eval($.post(downloadUrl, $.Map().add("url", WeixinTokenHelper.WEIXIN_DOWNLOAD_MEDIA_URL.replace("{token}", WeixinTokenHelper.getToken()).replace("{media}", imageArr[i])).add("app", sApp), "UTF-8"));
								if (mapResult.get("code").toString().equals("200")) {
									imgUrls = imgUrls + "," + mapResult.get("url");
								}
							}
						}
						if ($.empty(csOutsideQr.getCsoqImages())) {
							csOutsideQrUpdate.setCsoqImages(imgUrls);
						} else {
							csOutsideQrUpdate.setCsoqImages(csOutsideQr.getCsoqImages() + "," + imgUrls);
						}
					}

					csOutsideQrUpdate.update();
					return $.SendHtml("100", "UTF-8");
				} else {
					// 更新成功！
					return $.SendHtml("100", "UTF-8");
				}
			} else {
				// 添加
				CsState csState = csStateService.getCsState($.add(CsState.F.cssNumber, carInfo.getCscNumber()));
				if (csState == null) {
					// 当前车辆状态信息没有
					return $.SendHtml("201", "UTF-8");
				}
				// 如果时间相差大于设置的阈值，则进行时间校正，发送时间同步
				if (Math.abs(System.currentTimeMillis() - csState.getCssAddTime().getTime()) > 20 * 60 * 1000l) {
					// 当前车辆已掉线
					return $.SendHtml("202", "UTF-8");
				}
				CsOutsideQr csOutsideQrNew = new CsOutsideQr();
				csOutsideQrNew.setNotNull(carInfo.getCscHost(), userid, carInfo.getCscId(), type, lng + "," + lat, csState.getCssLongitude() + "," + csState.getCssLatitude(), getShortestDistance(lng, lat, Double.parseDouble(csState.getCssLongitude()), Double.parseDouble(csState.getCssLatitude())),
						new Date(), (short) 1);
				// 设置图片
				if (!$.empty(images)) {
					String[] imageArr = images.split(",");
					// 下载图片
					String imgUrls = "";
					for (int i = 0; i < imageArr.length; i++) {
						if ($.empty(imgUrls)) {
							Map mapResult = $.eval($.post(downloadUrl, $.Map().add("url", WeixinTokenHelper.WEIXIN_DOWNLOAD_MEDIA_URL.replace("{token}", WeixinTokenHelper.getToken()).replace("{media}", imageArr[i])).add("app", sApp), "UTF-8"));
							if (mapResult.get("code").toString().equals("200")) {
								imgUrls = imgUrls + mapResult.get("url");
							}
						} else {
							Map mapResult = $.eval($.post(downloadUrl, $.Map().add("url", WeixinTokenHelper.WEIXIN_DOWNLOAD_MEDIA_URL.replace("{token}", WeixinTokenHelper.getToken()).replace("{media}", imageArr[i])).add("app", sApp), "UTF-8"));
							if (mapResult.get("code").toString().equals("200")) {
								imgUrls = imgUrls + "," + mapResult.get("url");
							}
						}
					}
					csOutsideQrNew.setCsoqImages(imgUrls);
				}
				csOutsideQrService.saveCsOutsideQr(csOutsideQrNew);
				return $.SendHtml("100", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * ajax 方式提交远程控制
	 */
	public String remote() {
		try {
			if ($.empty(WeixinHelper.getOpenId())) {
				// 微信授权登录
				$.Redirect($.GetRequest("basePath") + "weixin/oauth2.html?redirect=weixin/admin/index.html");
				return null;
			}
			// 扩展用户信息
			ISrvUserExpService srvUserExpService = $.getBean("srvUserExpService");
			SrvUserExp srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueWxOpenid, WeixinHelper.getOpenId()));
			if (srvUserExp == null) {
				return $.SendAjax($.add("success", false).add("message", "用户未授权"), "UTF-8");
			}

			String number = $.getString("number");
			Short type = $.getShort("type");

			if (type == null)
				return $.SendAjax($.add("success", false).add("message", "请选择远程控制类型"), "UTF-8");
			if ($.empty(number))
				return $.SendAjax($.add("success", false).add("message", "请输入车牌号码"), "UTF-8");
			if (number.length() < 5)
				return $.SendAjax($.add("success", false).add("message", "车牌号码不能小于5位"), "UTF-8");

			ICsCarService csCarService = $.GetSpringBean("csCarService");
			CsCar csCar = csCarService.getCsCar($.add("definex", "csc_number like '%" + number + "%'"));

			if (csCar == null)
				return $.SendAjax($.add("success", false).add("message", "车牌号码错误"), "UTF-8");

			ICsStateService csStateService = $.getBean("csStateService");
			CsState csState = csStateService.getCsState($.add(CsState.F.cssNumber, csCar.getCscNumber()));
			if (csState == null) {
				// 当前车辆状态信息没有
				return $.SendAjax($.add("success", false).add("message", "当前车辆状态信息没有"), "UTF-8");
			}
			// 如果时间相差大于设置的阈值，则进行时间校正，发送时间同步
			if (Math.abs(System.currentTimeMillis() - csState.getCssAddTime().getTime()) > 20 * 60 * 1000l) {
				// 当前车辆已掉线
				return $.SendAjax($.add("success", false).add("message", "当前车辆已掉线"), "UTF-8");
			}

			ICsRemoteService csRemoteService = $.getBean("csRemoteService");

			CsRemote csRemote = new CsRemote();
			csRemote.setCsrNumber(csCar.getCscNumber());
			csRemote.setCsrCar(csCar.getCscId());
			csRemote.setCsrHost(csCar.getCscHost());
			csRemote.setCsrEditor(srvUserExp.get$sueUser().getSuId());
			csRemote.setCsrAddTime(new Date());
			csRemote.setCsrState((short) 2);
			csRemote.setCsrStatus((short) 0);
			csRemote.setCsrType((short) type);
			csRemote.setCsrRemark(srvUserExp.get$sueUser().getSuRealName() + "执行车纷享微信远程操作");
			csRemote = csRemoteService.saveCsRemote(csRemote);

			com.ccclubs.service.common.impl.RemoteService.dealRemoteCommend(csRemote);

			String[] typeDesc = new String[] { "远程开门", "远程关门", "允许启动" };
			return $.SendAjax($.add("success", true).add("message", csCar.getCscCarNo() + typeDesc[type] + "成功"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("success", false).add("message", "系统繁忙，请联系管理员"), "UTF-8");
		}
	}

	/**
	 * 绑定后台帐号页面
	 * 
	 * @return
	 * @throws IOException
	 */
	public String bind() throws IOException {
		$.trace("bind");
		// 查询是否有扩展信息
		if ($.empty(WeixinHelper.getOpenId())) {
			// 微信授权登录
			$.Redirect($.GetRequest("basePath") + "weixin/oauth2.html?redirect=weixin/admin/scan_bind.html");
		}
		ISrvUserExpService srvUserExpService = $.getBean("srvUserExpService");
		SrvUserExp srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueWxOpenid, WeixinHelper.getOpenId()));
		if (srvUserExp != null) {
			$.Redirect($.GetRequest("basePath") + "weixin/admin/scan.html?key=" + $.getSession("carNo"));
		}
		return "bind";
	}

	/**
	 * 帐号绑定提交
	 * 
	 * @return
	 */
	public String bindSubmit() {
		try {
			ISrvUserService srvUserService = $.getBean("srvUserService");

			String from = WeixinHelper.getOpenId();
			// $.SetRequest("from", from);
			String strUsername = $.getString("username");
			String strPassword = $.getString("password");

			if (!WeixinHelper.isCorrectOpenId()) {
				// 你还没有输入帐号
				return $.SendHtml("107", "UTF-8");
			}
			if (SystemHelper.isNullOrEmpty(strUsername)) {
				// 你还没有输入帐号
				return $.SendHtml("102", "UTF-8");
			}
			if (SystemHelper.isNullOrEmpty(strPassword)) {
				// 你还没有输入密码！
				return $.SendHtml("103", "UTF-8");
			}
			SrvUser user = null;

			// 帐号方式登录
			user = srvUserService.getSrvUserByUsername(strUsername);

			if (user == null) {
				// 你输入的帐号不正确，请重新输入
				return $.SendHtml("105", "UTF-8");
			}
			if (user.getSuStatus() != 1) {
				// 该帐户已经失效，请联系车纷享
				return $.SendHtml("106", "UTF-8");
			}

			if (!strPassword.toUpperCase().equals(user.getSuPassword().toUpperCase())) {
				// 你输入的密码不正确，请重新输入。
				return $.SendHtml("105", "UTF-8");
			}

			HttpServletRequest request = ServletActionContext.getRequest();
			user.setSuLastTime(new java.util.Date());
			user.setSuLastIp(request.getRemoteAddr());
			srvUserService.updateLastIp(user);

			// 扩展用户信息
			ISrvUserExpService srvUserExpService = $.getBean("srvUserExpService");
			SrvUserExp srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueWxOpenid, WeixinHelper.getOpenId()));

			if (srvUserExp != null) {
				srvUserExp.setSueWxOpenid(from);
				srvUserExp.update();
			} else {
				SrvUserExp newSrvUserExp = new SrvUserExp();
				newSrvUserExp.setSueWxOpenid(from);
				newSrvUserExp.setSueUser(user.getSuId());
				srvUserExpService.saveSrvUserExp(newSrvUserExp);
			}
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			return $.SendHtml("9999", "UTF-8");
		}

	}

	/**
	 * 计算两点间的距离
	 */
	private final double	EARTH_RADIUS	= 6378.137; // 地球半径

	private double rad(double d) {
		return d * Math.PI / 180.0; // 计算弧长
	}

	// lng1 第一个点经度，lat1第一点纬度；lng2第二点经度，lat2第二点纬度
	private int getShortestDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = s * 1000; // 换算成米
		return SystemHelper.toInt(s);

	}
}
