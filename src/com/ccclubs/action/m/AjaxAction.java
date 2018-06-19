package com.ccclubs.action.m;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;

import com.ccclubs.action.m.util.MOrderUtil;
import com.ccclubs.action.m.util.OrderTime;
import com.ccclubs.action.m.util.ResponseEnvelope;
import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPlace;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ISrvHostService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.util.DateUtil;
import com.ccclubs.util.UploadUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.WebHelper;

public class AjaxAction {
	private ICsMemberService	csMemberService;
	private ICsCarModelService	csCarModelService;
	private ISrvHostService		srvHostService;
	private static String		downloadUrl	= "http://photo.ccclubs.com/down.do";

	/**
	 * 
	 * 寄送地址三级联动 
	 * 
	**/
	public String provinceajax(){
		List<CsPlace> csPlacelist=CsPlace.getCsPlaceList(($.add(CsPlace.F.cspParent, "0")), -1);
		return $.SendAjax((csPlacelist), "UTF-8");
	}
	public String cityajax(){
		List<CsPlace> csPlacelist=CsPlace.getCsPlaceList(($.add(CsPlace.F.cspParent, $.getString("province_val"))), -1);
		return $.SendAjax((csPlacelist), "UTF-8");
	}
	public String areaajax(){
		List<CsPlace> csPlacelist=CsPlace.getCsPlaceList(($.add(CsPlace.F.cspParent, $.getString("area_val"))), -1);
		return $.SendAjax((csPlacelist), "UTF-8");
	}
	/**
	 * ajax_model获取车型列表
	 * 
	 * @return
	 */
	public String model() {
		String host = $.getString("host");
		if(host==null||host==""){
			host = SystemHelper.getSrvHost().getShId()+"";
		}
		String like_sql = " cscm_hosts LIKE '%#" + host + "#%'";
		List<CsCarModel> list = csCarModelService.getCsCarModelList($.add(CsCarModel.F.cscmStatus, "1").add("definex", like_sql), -1);
		$.SetRequest("list", list);
		return "model";
	}

	/**
	 * ajax_city获取城市列表
	 * 
	 * @return
	 */
	public String city() {
		List<SrvHost> list = srvHostService.getSrvHostList($.add(SrvHost.F.shState, 1).add("asc", SrvHost.C.shId), -1);
		$.SetRequest("list", list);
		return "city";
	}

	/**
	 * 获得当前地域信息
	 * 
	 * @return
	 */
	public String getHost() {
		return $.SendAjax(SystemHelper.getSrvHost(), "UTF-8");
	}
	
	/**
	 * 获得当前地域信息
	 * 
	 * @return
	 */
	public String getmHost() {
		return $.SendAjax(SystemHelper.getSrvHostByClientIp(), "UTF-8");
	}

	/**
	 * 根据地域id主动设置地域信息
	 */
	public String setHost() {
		try {
			Long hostId = $.getLong("host");
			ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
			SrvHost srvHost = srvHostService.getSrvHost($.add(SrvHost.F.shId, hostId));
			if (srvHost != null) {
				SystemHelper.setSrvHost(srvHost);
				// 重置预订信息里的，区域，网点，车型信息
				if (SystemHelper.getBaseOrderInf() != null) {
					BaseOrderInfo baseOrderInfo = SystemHelper.getBaseOrderInf();
					baseOrderInfo.setCsoArea(-1l);
					baseOrderInfo.setCsoOutlets(-1l);
					baseOrderInfo.setCsoModel(-1l);
					SystemHelper.setBaseOrderInfo(baseOrderInfo);
				}
				// BaseOrderInfo baseOrderInfo =
				return $.SendHtml("true", "UTF-8");
			} else {
				return $.SendHtml("false", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("false", "UTF-8");
		}

	}

	/**
	 * 微信照片上传
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String uploadWeixinPic() {
		String media = $.getString("media", "");
		String sApp = $.getString("sApp", "");
		int type = $.getInteger("type", 0);
		String photo = $.getString("photo", "");

		// debug
		// type = 1;
		// photo =
		// "data:image/png;base64,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";
		// debug
		if (type == 0) {
			if ($.empty(media)) {
				return $.SendAjax($.Map().add("code", 201).add("message", "未指定下载 media id"), "UTF-8");
			}
			Map mapResult = $.eval($.post(downloadUrl, $.Map().add("url", WeixinTokenHelper.WEIXIN_DOWNLOAD_MEDIA_URL.replace("{token}", WeixinTokenHelper.getToken()).replace("{media}", media)).add("app", sApp), "UTF-8"));
			return $.SendAjax(mapResult, "UTF-8");
		} else {
			String fileName = "";
			try {
				BASE64Decoder decoder = new BASE64Decoder();
				// Base64解码，去掉传过来的头信息
				byte[] bytes;
				if (photo.contains(",")) {
					bytes = decoder.decodeBuffer(photo.split(",")[1]);
				} else {
					bytes = decoder.decodeBuffer(photo);
				}

				for (int i = 0; i < bytes.length; ++i) {
					if (bytes[i] < 0) {// 调整异常数据
						bytes[i] += 256;
					}
				}
				fileName = new SimpleDateFormat("yyyyMMddHHmmssS" + WebHelper.rand(999)).format(new Date()) + ".jpg";

				// 生成jpeg图片 
				// TODO:判断当前目录是否存在，不存在需要创建对应文件夹
				OutputStream out = new FileOutputStream($.config("upload.forder") + fileName);

				out.write(bytes);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String result = UploadUtil.getInstance().uploadFile(new File($.config("upload.forder") + fileName), "file", fileName, "http://pic.ccclubs.com/commonUpload.do", $.Map().add("app", sApp));

			if ($.empty(result)) {
				return $.SendAjax($.Map().add("code", 201).add("message", "系统繁忙，请稍候再试"), "UTF-8");
			}
			// 删除临时文件
			if (!$.empty(fileName)) {
				File f = new File($.config("upload.forder") + fileName);
				f.delete();
			}
			return WebHelper.SendAjax(result, "UTF-8");
		}
	}

	/**
	 * 发送短信
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sms() {
		try {
			String mobile = $.getString("mobile", "");
			int type = $.getInteger("type", 0);
			if ($.empty(mobile))
				// 手机号码不能为空
				return $.SendHtml("101", "UTF-8");
			if (!SystemHelper.isMobile(mobile))
				// 手机号码格式错误
				return $.SendHtml("101", "UTF-8");
			if (CsMember.where().csmMobile(mobile).get() != null)
				// 手机号码已存在
				return $.SendHtml("103", "UTF-8");
			// 验证短信发送频率
			Date last = $.getSession(SYSTEM.REGIST_VAILD_LAST);
			if (last != null && (new Date().getTime() - last.getTime()) < SYSTEM.MINUTE)
				// 两次注册短信验证码发送时间间隔不能小于一分钟
				return $.SendHtml("102", "UTF-8");
			String strCode = $.zerofill($.rand(9999), 4);
			// System.out.printf("短信校验码：" + strCode);
			Boolean bOk = false;
			if (type == 0) {
				bOk = UtilHelper.sendTemplateSMS($.getLong("host"), "REGIST_CODE", mobile, CommonMessage.MSG0200, SMSType.代码类短信, $.add("code", strCode));
			} else {
				bOk = UtilHelper.sendTemplateSMS(CsMember.where().csmMobile(mobile).get().getCsmHost(), "FORGOT_PWD", mobile, CommonMessage.MSG0201, SMSType.提示类短信, $.add("code", strCode));
			}
			$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date());
			// Boolean bOk = true;
			if (bOk == true) {
				$.setSession(SYSTEM.REGIST_VAILD_CODE, strCode);
				return $.SendHtml("success", "UTF-8");
			} else
				// 验证码发送失败，请联系管理员
				return $.SendHtml("false", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统繁忙，请稍候再试
			return $.SendHtml("false", "UTF-8");
		}
	}

	/**
	 * 判断会员是否已经存在
	 * 
	 * @return false：格式不正确，验证未通过 true：格式正确，可以使用 used：已存在
	 * 
	 */
	public String exist() {
		try {
			String strMobile = $.getString("txtMobile", "");
			Map<String, Object> params = new HashMap<String, Object>();
			if (!$.empty(strMobile) && SystemHelper.isMobile(strMobile))
				params.put(CsMember.F.csmMobile, strMobile);
			else
				return $.SendHtml("false", "UTF-8");

			CsMember existMember = csMemberService.getCsMember(params);
			if (existMember != null)
				return $.SendHtml("used", "UTF-8");
			else
				return $.SendHtml("true", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("false", "UTF-8");
		}
	}

	/**
	 * 可用时间段
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String order() {
		DateUtil du = new DateUtil();
		MOrderUtil morder = new MOrderUtil();
		ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		String dateCondition = du.dateToString(calendar.getTime(), "yyyy-MM-dd");
		String carid = $.getString("carid");
		
		List<String> listday = new ArrayList<String>();
		
		//开始时间为今天的凌晨
		//Date begin = calendar.getTime();
		//订单列表
		//List<CsOrder> orders = CsOrder.where().csoFinishTimeStart(begin).csoCar(carid).add("definex", "cso_status not in (3,4,7)").add("asc", "cso_start_time").list(-1);
		//ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
		//限行列表
		/*List<Restriction> restrs = commonOrderService.getRestrictions($.getLong("carid"),7);
		Map<String,Boolean> restring = new HashMap<String,Boolean>();
		for(Restriction r:restrs)restring.put($.date(r.getStart(), "yyyy-MM-dd"), true);
		
		for (int i = 0; i < 7; i++) {
			int restriction = -1;
			if (i != 0) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			Date start = calendar.getTime();
			String dayofweek = morder.getWeekOfDate(start);
			String dayview = month + "月" + day + "日  " + dayofweek;
			dateCondition = SystemHelper.formatDate(start, "yyyy-MM-dd");
			//根据车辆取当天所有的占用的订单
			listday.add(dayview);
			List<OrderTime> listtime = morder.usableTime(orders, dateCondition);

			// 限行时间
			if (restring.get(dateCondition)!=null) {
				restriction = 1;
			}
			$.SetRequest("restriction_" + i, restriction);
			$.SetRequest("listtime_" + i, listtime);
			$.SetRequest("full_" + i, listtime.isEmpty()?"usable":"full");
		}*/
		
		try {
			for (int i = 0; i < 7; i++) {
				String is_full = "usable";
				int restriction = -1;
				if (i != 0) {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				Date start = calendar.getTime();
				String dayofweek = morder.getWeekOfDate(start);
				String dayview = month + "月" + day + "日  " + dayofweek;
				dateCondition = SystemHelper.formatDate(start, "yyyy-MM-dd");
				//根据车辆取当天所有的占用的订单
				List<CsOrder> orders = csOrderService.getCsOrderList($.add(CsOrder.F.csoCar, carid).add("definex",
						CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" + dateCondition + " 23:59' and if(ISNULL(cso_ret_time), cso_finish_time  > '" + dateCondition + "' , cso_ret_time >'" + dateCondition + "')").add("asc", "cso_start_time"), -1);
				if (orders.size() != 0) {
					boolean s = morder.isInDates(du.StringtoDate(dateCondition + " 00:00:00", null), orders.get(0).getCsoStartTime(), orders.get(0).getCsoFinishTime());
					boolean s_end = morder.isInDates(du.StringtoDate(dateCondition + " 23:59:59", null), orders.get(0).getCsoStartTime(), orders.get(0).getCsoFinishTime());
					// 全天订单已满
					if (s && s_end) {
						is_full = "full";
					}
				}
				listday.add(dayview);
				List<OrderTime> listtime = morder.usableTime(orders, dateCondition);

				// 限行时间
				Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateCondition);
				Date tempDateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateCondition + " 23:59");
				ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
				List<Restriction> restrictions = commonOrderService.getRestrictions(Long.parseLong(carid), tempDate, tempDateEnd);
				if (restrictions.size() > 0) {
					restriction = 1;
				}

				$.SetRequest("restriction_" + i, restriction);
				$.SetRequest("listtime_" + i, listtime);
				$.SetRequest("full_" + i, is_full);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		$.SetRequest("listday", listday);
		$.SetRequest("carid", carid);
		return "order";
	}

	/**
	 * 
	 * 异步验证修改密码页面，当前密码是否正确
	 * 
	 * @return true:正确，false：不正确 ，101：需要登录
	 */
	public String checkPwd() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		String strPass = $.getString("pass", "");
		if (strPass.length() < 6)
			return $.SendHtml("false", "UTF-8");

		try {
			if (!$.md5(RSAUtil.decrypt(strPass)).equals(csMember.getCsmPassword().toUpperCase())) {
				// 你输入的密码不正确，请重新输入。
				return $.SendHtml("false", "UTF-8");
			}
			return $.SendHtml("true", "UTF-8");
		} catch (Exception e) {
			return $.SendHtml("false", "UTF-8");
		}
	}
	
	public String indexArea(){
		ResponseEnvelope<CsArea> re = new ResponseEnvelope<CsArea>();
		Long host = $.getLong("csHost", 0l);
		//区域
		ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		List<CsArea> list = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, host).add(CsArea.F.csaStatus, 1), -1);
		if(!list.isEmpty()){
			re.setList(list);
			re.setState(true);
		}else{
			re.setState(false);
		}
		return $.SendAjax(re, "utf-8");
	}
	/**
	 * 根据区域选城市
	 * @return
	 */
	public String cityOfRegion(){
		ResponseEnvelope<CsOutlets> re = new ResponseEnvelope<CsOutlets>();
		String region = $.getString("region","");
		List<CsOutlets> list = new ArrayList<CsOutlets>();
		ICsOutletsService outletsService = $.GetSpringBean("csOutletsService");
		if(region.length()>0){
			list = outletsService.getCsOutletsList($.add(CsOutlets.F.csoArea, region), -1);
			re.setList(list);
			re.setState(true);
		}else{
			re.setState(false);
		}
		return $.SendAjax(re, "utf-8");
	}

	public ICsCarModelService getCsCarModelService() {
		return csCarModelService;
	}

	public void setCsCarModelService(ICsCarModelService csCarModelService) {
		this.csCarModelService = csCarModelService;
	}

	public ISrvHostService getSrvHostService() {
		return srvHostService;
	}

	public void setSrvHostService(ISrvHostService srvHostService) {
		this.srvHostService = srvHostService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
}
