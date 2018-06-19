package com.ccclubs.action.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsComplain;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsMessage;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsComplainService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMessageService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsUpdateService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvHostService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class AjaxAction {

	ICsOutletsService		csOutletsService;
	ICsMemberService		csMemberService;
	ICsMemberInfoService	csMemberInfoService;
	ICsPriceService			csPriceService;
	ICsGoodsService			csGoodsService;
	ICsCarModelService		csCarModelService;
	ICsUserTypeService		csUserTypeService;
	ICsCarService			csCarService;

	/**
	 * 
	 * 用户建议和意见
	 * 状态：
	 * 1表示类型问题
	 * 2表示可以提交
	 * 3表示每天每个会员最多提交3次
	 * 4没有登录请登录
	 * 
	 */
	public String feedback_ajax(){
		ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		CsComplain csComplain=new CsComplain();
		Map<String,Object> datamap = new HashMap<String, Object>();
		String feedback_textarea =  $.getString("feedback_textarea");
		String feedback_type =  $.getString("feedback_type");
		String feedback_state="";
		String feedback_title="";
		Long number=csComplainService.getCsComplainCount($.add(CsComplain.F.cscMember, SystemHelper.getLoginId()).add(CsComplain.F.cscAddTimeStart, $.DateExp("D{1,0}").getStart()));
		if(feedback_type.equals("1")){
			feedback_title="我的意见";
		}else if(feedback_type.equals("2")){
			feedback_title="我的建议";
		}else{
			feedback_state="1";
		}
		if(SystemHelper.getLoginId()!=null){
				if(number<3){
					csComplain.setNotNull(SystemHelper.getLogin().getCsmHost(), feedback_title, feedback_textarea, new Short(feedback_type), 
							SystemHelper.getLoginId(), new Date(), new Date(),new Short("0"), new Short("1") );
					csComplainService.saveCsComplain(csComplain);//数据存储
					datamap.put("hostList", $.getString("feedback_textarea"));
					feedback_state="2";
				}else{
					feedback_state="3";
				}
		}else{
			feedback_state="4";
		}
		return $.SendAjax((feedback_state), "UTF-8");
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
	 * 获得运营中的地域信息列表
	 * 
	 * @return
	 */
	public String getHosts() {
		ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
		List<SrvHost> srvHostList = srvHostService.getSrvHostList($.add(SrvHost.F.shState, 1).add(SrvHost.F.shStatus, 1), -1);
		Map<String,Object> datamap = new HashMap<String, Object>();
		datamap.put("hostList", srvHostList);
		SrvHost current = null;
		SrvHost tmp = SystemHelper.getSrvHost();
		if(tmp!=null && srvHostList!=null){
			for(SrvHost host : srvHostList){
				if(host.getShId().equals(tmp.getShId())){
					current = tmp;
				}
			}
		}
		datamap.put("current", current);
		return $.SendAjax($.json(datamap), "UTF-8");
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
					// 清除当前定单信息
					BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
					baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
					baseOrderInfo.setCsoHost(srvHost.getShId());
					$.setSession(Constant.BASE_ORDER_INFO, baseOrderInfo);
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
	 * 依据区域，获得该区域下的所有网点，传入参数为 int 类型 area
	 * 
	 * @return 网点 UTF-8 编码 Json 数据
	 */
	public String outletsByArea() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
		int area = $.getInteger("area", -1);
		int cana = $.getInteger("cana", -1);
		int canb = $.getInteger("canb", -1);
		// 随机20个网点
		if (area == -1) {
			// 按照能预订的网点排前面
			params.put(CsOutlets.F.csoStatus, "1");
			params.put(CsOutlets.F.csoCanOrder, "1");
			if (cana != -1) {
				params.put(CsOutlets.F.csoCanA, cana);
			}
			if (canb != -1) {
				params.put(CsOutlets.F.csoCanB, canb);
			}
			params.put("desc", CsOutlets.C.csoCanOrder + " desc," + CsOutlets.C.csoPriority);
			List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, 20);
			return Lazy.SendAjax(outlets, SYSTEM.UTF8);
		} else {
			params.put(CsOutlets.F.csoArea, area);
			params.put(CsOutlets.F.csoStatus, "1");
			params.put(CsOutlets.F.csoCanOrder, "1");
			if (cana != -1) {
				params.put(CsOutlets.F.csoCanA, cana);
			}
			if (canb != -1) {
				params.put(CsOutlets.F.csoCanB, canb);
			}
			// 按照能预订的网点排前面
			params.put("desc", CsOutlets.C.csoCanOrder + " desc," + CsOutlets.C.csoPriority + " desc, " + CsOutlets.C.csoAddTime);
			List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, -1);
			return Lazy.SendAjax(outlets, SYSTEM.UTF8);
		}
	}

	/**
	 * 依据区域，获得该区域下的所有网点，传入参数为 int 类型 area
	 * 
	 * @return 网点 UTF-8 编码 Json 数据
	 */
	public String outletsByAreaCanorder() {
		Map<String, Object> params = new HashMap<String, Object>();
		int area = $.getInteger("area", -1);
		params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
		params.put(CsOutlets.F.csoArea, area);
		params.put(CsOutlets.F.csoCanOrder, 1);
		// 按照能预订的网点排前面
		params.put("desc", CsOutlets.C.csoPriority + " desc, " + CsOutlets.C.csoAddTime);
		List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, -1);
		return Lazy.SendAjax(outlets, SYSTEM.UTF8);
	}

	/**
	 * 网点分布会用到
	 * 
	 * @param csoCanOrder
	 *            1：能预定，0：不能预定
	 * @return
	 */
	public String outlets() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
		params.put(CsOutlets.F.csoCanOrder, Lazy.getInteger(CsOutlets.F.csoCanOrder));
		List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, -1);
		return Lazy.SendAjax(outlets, SYSTEM.UTF8);
	}

	// TODO: 加油站信息
	/**
	 * 依据网点，工作日/双休日/节假日，会员类型 输出价格
	 * 
	 * @return
	 */
	public String getPrices() {
		Map<String, String> paramsGoods = new HashMap<String, String>();
		String cspGoods = $.getString("cspGoods");
		paramsGoods.put("definex", CsGoods.C.csgName + " like '%" + cspGoods + "%'");
		List<CsGoods> listGoods = csGoodsService.getCsGoodsList(paramsGoods, -1);
		// TODO：价格列表没有
		String ids = "";
		for (CsGoods csGood : listGoods) {
			ids = ids + csGood.getCsgId() + ",";
		}
		ids = ids.substring(0, ids.length() - 1);

		List<PricesJsonFormat> pricesJsonList = new ArrayList<PricesJsonFormat>();
		ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		List<CsCarModel> listCarModel = SystemHelper.getHostModels(SystemHelper.getSrvHost().getShId());//csCarModelService.getCsCarModelList($.add("definex", CsCarModel.C.cscmHosts + " like '%#" + SystemHelper.getSrvHost().getShId() + "#%'").add(CsCarModel.F.cscmStatus, 1).add("desc", CsCarModel.C.cscmType), -1);

		for (CsCarModel csCarModel : listCarModel) {
			PricesJsonFormat pricesJsonFormat = new PricesJsonFormat();
			pricesJsonFormat.vehicleTypeId = csCarModel.getCscmId();
			pricesJsonFormat.vehicleTypeName = csCarModel.getCscmName();

			Map<String, Object> params = new HashMap<String, Object>();
			// 设置网点
			params.put(CsPrice.F.cspOutets, $.getInteger("cspOutets"));
			// 设置会员类型
			params.put(CsPrice.F.cspUserType, $.getInteger("cspUserType"));
			params.put(CsPrice.F.cspModel, csCarModel.getCscmId());
			params.put("desc", CsPrice.C.cspGoods);
			// 自定义的条件查询
			params.put("definex", CsPrice.C.cspPrice + " > 0  and " + CsPrice.C.cspGoods + " in (" + ids + ")");
			pricesJsonFormat.prices = csPriceService.getCsPriceList(params, -1);
			pricesJsonList.add(pricesJsonFormat);
		}
		return Lazy.SendAjax(pricesJsonList, SYSTEM.UTF8);
	}

	/**
	 * 分时租价格展示模板 忽略“节假日”条件
	 * 
	 * @return
	 */
	public String getPriceWithTime() {
		List<PricesJsonFormat> pricesJsonList = new ArrayList<PricesJsonFormat>();
		ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		List<CsCarModel> listCarModel = SystemHelper.getHostModels(SystemHelper.getSrvHost().getShId());//csCarModelService.getCsCarModelList($.add("definex", CsCarModel.C.cscmHosts + " like '%#" + SystemHelper.getSrvHost().getShId() + "#%'").add(CsCarModel.F.cscmStatus, 1).add("desc", CsCarModel.C.cscmType), -1);

		for (CsCarModel csCarModel : listCarModel) {
			PricesJsonFormat pricesJsonFormat = new PricesJsonFormat();
			pricesJsonFormat.vehicleTypeId = csCarModel.getCscmId();
			pricesJsonFormat.vehicleTypeName = csCarModel.getCscmName();

			pricesJsonFormat.prices = csPriceService.getCsPriceList($.add(CsPrice.F.cspUserType, $.getInteger("cspUserType")).add(CsPrice.F.cspOutets, $.getInteger("cspOutets")).add(CsPrice.F.cspModel, csCarModel.getCscmId()).add("desc", CsPrice.C.cspGoods), -1);
			pricesJsonList.add(pricesJsonFormat);
		}

		return Lazy.SendAjax(pricesJsonList, SYSTEM.UTF8);
	}

	/**
	 * 
	 * 异步验证注册页面验证码是否正确
	 * 
	 * @return true:正确，false：不正确
	 */
	public String checkCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		// 不区分大小写
		String strRequestSyscode = $.getString("code", "").toLowerCase();
		String strSessionSyscode = ((String) session.getAttribute(Constant.VERIFY_CODE)).toLowerCase();
		if (strRequestSyscode == null || !strRequestSyscode.equals(strSessionSyscode)) {
			return $.SendHtml("false", "UTF-8");
		} else {
			return $.SendHtml("true", "UTF-8");
		}
	}

	/**
	 * 
	 * 异步提交EVnet反馈
	 * 
	 * @return true:正确，false：不正确
	 */
	public String evnetFeedback() {
		String callback = $.getString("callback", "");
		String content = $.getString("content", "");
		String title = $.getString("title", "");
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			// 不区分大小写
			String strRequestSyscode = $.getString("code", "").toLowerCase();
			String strSessionSyscode = ((String) session.getAttribute(Constant.VERIFY_CODE)).toLowerCase();
			if (strRequestSyscode == null || !strRequestSyscode.equals(strSessionSyscode)) {
				return $.SendHtml(callback + "({success:false,message:'验证码错误'});", "UTF-8");
			} else {
				ICsMessageService csMessageService = $.getBean("csMessageService");
				if (true)
					throw new RuntimeException("消息对象已改。。。");
				// CsMessage csMessage = new
				// CsMessage().setNotNull(SystemHelper.getSrvHost().getShId(),
				// title, content, 0l, 0l, new Date(), new Date(), (short) 0,
				// (short) 1, (short) 0);
				// csMessageService.saveCsMessage(csMessage);
				return $.SendHtml(callback + "({success:true,message:'提交成功'});", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml(callback + "({success:false,message:'提交失败'});", "UTF-8");
		}
	}

	/**
	 * 
	 * 异步验证注册页面手机校验码是否正确
	 * 
	 * @return true:正确，false：不正确
	 */
	public String checkMobileCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		// 4位数字
		String strRequestSyscode = $.getString("mobcode", "");
		String strSessionSyscode = ((String) session.getAttribute(SYSTEM.REGIST_VAILD_CODE));
		if (strRequestSyscode == null || !strRequestSyscode.equals(strSessionSyscode)) {
			return $.SendHtml("false", "UTF-8");
		} else {
			return $.SendHtml("true", "UTF-8");
		}
	}

	/**
	 * 
	 * 异步验证修改密码页面，当前密码是否正确
	 * 
	 * @return true:正确，false：不正确 ，101：需要登录
	 */
	public String checkPwd() {
		if (!SystemHelper.isLogin())
			return $.SendHtml("101", "UTF-8");
		String strPass = $.getString("pass", "");
		if (strPass.length() < 6)
			return $.SendHtml("false", "UTF-8");
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());

		try {
			if (!$.md5(RSAUtil.decrypt(strPass)).equals(member.getCsmPassword().toUpperCase())) {
				// 你输入的密码不正确，请重新输入。
				return $.SendHtml("false", "UTF-8");
			}
			return $.SendHtml("true", "UTF-8");
		} catch (Exception e) {
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
			String strEmail = $.getString("txtEmail", "");
			String strUsername = $.getString("txtUserName", "");
			String strMobile = $.getString("txtMobile", "");
			Map<String, Object> params = new HashMap<String, Object>();
			if (!$.empty(strEmail) && SystemHelper.isEmail(strEmail))
				params.put(CsMember.F.csmEmail, strEmail);
			else if (!$.empty(strUsername) && SystemHelper.isUserName(strUsername))
				params.put(CsMember.F.csmUsername, strUsername);
			else if (!$.empty(strMobile) && SystemHelper.isMobile(strMobile))
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
	 * 查看驾驶证号码是否存在
	 * 
	 * @return
	 */
	public String licnumExist() {
		try {
			String strLicnum = $.getString("txtLicnum", "");
			Map<String, Object> params = new HashMap<String, Object>();
			if (!$.empty(strLicnum))
				params.put(CsMemberInfo.F.csmiDriverNum, strLicnum);
			else
				return $.SendHtml("false", "UTF-8");
			CsMemberInfo existMemberInfo = csMemberInfoService.getCsMemberInfo(params);
			if (existMemberInfo != null)
				return $.SendHtml("used", "UTF-8");
			else
				return $.SendHtml("true", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("false", "UTF-8");
		}
	}

	/**
	 * 发送短信
	 * 
	 * @return
	 */
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
			// 验证短信发送频率
			Date last = $.getSession(SYSTEM.REGIST_VAILD_LAST);
			if (last == null) {
				Logger.getRootLogger().warn("调用/ajax_sms.html发送短信之前必须在session中添加一个标识为SYSTEM.REGIST_VAILD_LAST的时间");
				// 不允许使用该接口发送短信
				return $.SendHtml("false", "UTF-8");
			}
			if ((new Date().getTime() - last.getTime()) < SYSTEM.MINUTE)
				// 两次注册短信验证码发送时间间隔不能小于一分钟
				return $.SendHtml("102", "UTF-8");
			String strCode = $.zerofill($.rand(9999), 4);
			// System.out.printf("短信校验码：" + strCode);
			Boolean bOk = false;
			if (type == 0) {
				bOk = UtilHelper.sendTemplateSMS($.getLong("host"), "REGIST_CODE", mobile, CommonMessage.MSG0200, SMSType.代码类短信, $.add("code", strCode));
			} else {
				bOk = UtilHelper.sendTemplateSMS($.getLong("host"), "FORGOT_PWD", mobile, CommonMessage.MSG0201, SMSType.提示类短信, $.add("code", strCode));
			}
			// Boolean bOk = true;
			if (bOk == true) {
				$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date());
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
	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsPriceService getCsPriceService() {
		return csPriceService;
	}

	public void setCsPriceService(ICsPriceService csPriceService) {
		this.csPriceService = csPriceService;
	}

	public ICsGoodsService getCsGoodsService() {
		return csGoodsService;
	}

	public void setCsGoodsService(ICsGoodsService csGoodsService) {
		this.csGoodsService = csGoodsService;
	}

	public ICsCarModelService getCsCarModelService() {
		return csCarModelService;
	}

	public void setCsCarModelService(ICsCarModelService csCarModelService) {
		this.csCarModelService = csCarModelService;
	}

	public ICsUserTypeService getCsUserTypeService() {
		return csUserTypeService;
	}

	public void setCsUserTypeService(ICsUserTypeService csUserTypeService) {
		this.csUserTypeService = csUserTypeService;
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

	public ICsMemberInfoService getCsMemberInfoService() {
		return csMemberInfoService;
	}

	public void setCsMemberInfoService(ICsMemberInfoService csMemberInfoService) {
		this.csMemberInfoService = csMemberInfoService;
	}

}
