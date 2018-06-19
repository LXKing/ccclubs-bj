package com.ccclubs.action.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WidgetHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberGroup;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvHostService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.ccclubs.util.IPAddressUtils;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class RegisterAction {
	ICommonOrderService		commonOrderService;
	ICsMemberService		csMemberService;
	ICsMemberInfoService	csMemberInfoService;
	ICommonMoneyService		commonMoneyService;

	public String execute() {
		// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
		$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));
		
		ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put(SrvHost.F.shStatus, "1");
		params.put(SrvHost.F.shState, "1");
		List<SrvHost> hostList = srvHostService.getSrvHostList(params, Integer.MAX_VALUE);
		$.setRequest("hostList", hostList);
		
		SrvHost host = SystemHelper.getSrvHostByClientIp();
		if(host  != null){
			$.setRequest("selHostId", host.getShId());
		}else{
			//杭州
			$.setRequest("selHostId", 1);
		}
		return "register";
	}

	public String step2() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		
//		CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
//		$.setRequest("regHostId", $.getString("regHostId") == null ? csMemberInfo.getCsmiHost() : $.getString("regHostId"));
		CsMember csMember = SystemHelper.getLoginId()==null?null:CsMember.get(SystemHelper.getLoginId());
		$.setRequest("regHostId",$.getString("regHostId"));
		if(csMember!=null)
			$.setRequest("regHostId",csMember.getCsmHost());
		return "step2";
	}

	public String step3() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		// 会员的扩展信息
		CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
		if (csMemberInfo == null) {
			// 如果没有会员的扩展信息，转到step2
			return "register-step2";
		}
		Date date = WidgetHelper.getWorkDay(3);
		Lazy.SetRequest("maxdate", date);
		$.setRequest("regHostId", csMemberInfo.getCsmiHost());

		return "step3";
	}

	/**
	 * 提交注册基本信息 ，手机号，密码，验证码，校验码
	 * 
	 * @return
	 */
	public String basicInfo() {
		try {
			String csmMobile = $.getString("txtMobile", "");
			String csmPassword = RSAUtil.decrypt($.getString("txtPassWord", ""));
			String rePwd = RSAUtil.decrypt($.getString("txtRePassWord", ""));
			String strValidCode = $.getString("txtCode", "");
			final String txtHost = $.getString("txtHost", "1");
			
			 String strValidMobCode = $.getString("txtMobCode", "");
			if ($.empty(csmMobile)) {
				// $.SetTips("未输入手机号码，不能注册");
				return $.SendHtml("101", "UTF-8");
			}
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if ($.empty(strValidCode)) {
				// $.SetTips("未输入验证码，不能注册");
				return $.SendHtml("103", "UTF-8");
			}
			 if ($.empty(strValidMobCode)) {
			 // $.SetTips("未输入短信校验码，不能注册");
			 return $.SendHtml("104", "UTF-8");
			 }

			if (!SystemHelper.isMobile(csmMobile)) {
				// $.SetTips("手机号码格式错误");
				return $.SendHtml("105", "UTF-8");
			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("106", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("107", "UTF-8");
			}
			if (csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile)) != null) {
				// $.SetTips("手机号码已被注册");
				return $.SendHtml("108", "UTF-8");
			}
			if (!$.equals(strValidCode.toLowerCase(), ((String) $.getSession(Constant.VERIFY_CODE)).toLowerCase())) {
				// $.SetTips("注册码输入错误");
				return $.SendHtml("109", "UTF-8");
			}
			
//			HttpServletRequest request = ServletActionContext.getRequest();
//			if(CsMember.count($.add("csmRegistIp", request.getRemoteAddr()))>3){
//				//同一IP注册超过5个
//				return $.SendHtml("9999", "UTF-8");
//			}
			
			 if (!$.equals(strValidMobCode, (String)$.getSession(SYSTEM.REGIST_VAILD_CODE))){
				 // $.SetTips("短信校验码输入错误");
				 return $.SendHtml("110", "UTF-8");
			 }
			// ---------------------------------------------------------通过验证，提交注册
			final String finalPassword = csmPassword;
			final String finalMobile = csmMobile;
			csMemberService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					String username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
					// 用户名去重
					while (csMemberService.getCsMember($.add(CsMember.F.csmUsername, username)) != null) {
						username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
					}
					HttpServletRequest request = ServletActionContext.getRequest();

					CsMember csMember = new CsMember().setNotNull(SystemHelper.getSrvHost().getShId(),username, $.md5(finalPassword), 0d, 0d, 0d, 0, 0, 1d, new Date(), (short) 1);
					csMember.setCsmMobile(finalMobile);
					csMember.setCsmHost(Long.valueOf(txtHost));
					csMember.setCsmIsVip((short) 0);
					csMember.csmVDrive((short) 0).csmVEmail((short) 0).csmVMobile((short) 1).csmVReal((short) 0);
					csMember.csmLoginS(0);
					csMember.csmFrom((short) 1);

					csMember.csmRegistIp(request.getRemoteAddr());
					csMember = csMemberService.saveCsMember(csMember);
					// 手机认证成功，送100积分
					// commonMoneyService.updateMoney(csMember.getCsmId(),
					// MoneyType.Integral, Constant.VERY_MOBILE * 1.0,
					// RecordType.手机认证, CommonMessage.MSG0302.replace("{point}",
					// String
					// .valueOf(Constant.VERY_MOBILE)), null, null);
					// 注册成功，设置登录 session
					SystemHelper.setLogin(csMember);
					// $.SetTips("注册成功，请登录");
					// 清楚验证码，短信校验码session
					$.setSession(Constant.VERIFY_CODE, null);
					$.setSession(SYSTEM.REGIST_VAILD_CODE, null);

					return null;
				}
			});
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 驾驶证信息 ，真实姓名，驾驶证号，驾驶证照片地址
	 * 
	 * @return
	 */
	public String driverInfo() {
		try {
			if (!SystemHelper.isLogin())
				return SystemHelper.gotoLogin();
			final String csmiName = $.getString("txtRealname");
			final String csmiDriverNum = $.getString("txtLicnum");
			final String csmiDriverImage = $.getString("txtLicnumPic");
			final String csmiCertifyImage = $.getString("idCardPic");
			String regHostId = $.getString("regHostId");
			if(regHostId == null || regHostId.length()<=0){
				CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
				if(csMember!=null){
					regHostId = csMember.getCsmHost()+"";
				}else{
					regHostId = SystemHelper.getSrvHost().getShId()+"";
				}
			}
			
			final Long hostId = Long.valueOf(regHostId);
			if ($.empty(csmiName)) {
				// $.SetTips("未输入真实姓名，不能注册");
				return $.SendHtml("101", "UTF-8");
			}
			if ($.empty(csmiDriverNum)) {
				// $.SetTips("未输入驾驶证号码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if ($.empty(csmiDriverImage)) {
				// $.SetTips("未上传证件照片，不能注册");
				return $.SendHtml("103", "UTF-8");
			}
			if ($.empty(csmiCertifyImage)) {
				// $.SetTips("未上传证件照片，不能注册");
				return $.SendHtml("105", "UTF-8");
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsMemberInfo.F.csmiDriverNum, csmiDriverNum);
			CsMemberInfo existMemberInfo = csMemberInfoService.getCsMemberInfo(params);
			if (existMemberInfo != null)// $.SetTips("驾驶证号码已存在，不能注册");
				return $.SendHtml("104", "UTF-8");

			csMemberInfoService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					// TODO Auto-generated method stub
					// 更新会员的扩展信息
					CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
					if (csMemberInfo == null) {
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(hostId,SystemHelper.getLoginId(), new Date(), new Date(), (short) 1);
						csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
					}

					CsMemberInfo oldMemberInfo = csMemberInfoService.getCsMemberInfoById(csMemberInfo.getCsmiId());
					// 设置扩展信息：关联的会员帐号，驾照照片路径，驾照号码，真实姓名，更新时间
					csMemberInfo.csmiMemberId(SystemHelper.getLoginId()).csmiCertifyImage(csmiCertifyImage).csmiDriverImage(csmiDriverImage).csmiDriverNum(csmiDriverNum).csmiName(csmiName).csmiUpdateTime(new Date());
					
					Date birthday=SystemHelper.getBirthday(csmiDriverNum);
					if (birthday!=null) {
						csMemberInfo.csmiBirthday(birthday);
					}
					csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);

					CsMember csMember = new CsMember().csmId(SystemHelper.getLoginId()).csmInfo(csMemberInfo.getCsmiId()).csmUpdateTime(new Date());
					// 比较修改的内容，决定会员的审核状态,如果任意其中一个被修改过，则更新其对应的状态
					if (!$.equals(csmiName, oldMemberInfo.getCsmiName()))
						csMember.setCsmVReal((short) 2);
					if (!$.equals(csmiDriverImage, oldMemberInfo.getCsmiDriverImage())|| !$.equals(csmiCertifyImage, oldMemberInfo.getCsmiCertifyImage()) || !$.equals(csmiDriverNum, oldMemberInfo.getCsmiDriverNum()))
						csMember.setCsmVDrive((short) 2);

					// 真实姓名同步到会员表中
					csMember.setCsmName(csMemberInfo.getCsmiName());

					csMemberService.updateCsMember$NotNull(csMember);

					csMember = csMemberService.getCsMemberById(csMember.getCsmId());
					SystemHelper.setLogin(csMember);
					// 获取Cookie
					String cookieValue = $.getCookieValue("inviteCount");

					// 是否有人邀请
					Long uid = $.getLong("uid", 0l);
					CsMember inviteMember = csMemberService.getCsMemberById(uid);
					if (inviteMember != null) {
						// 更新邀请信息
						CsMember csMemberInvite = new CsMember();
						csMemberInvite.setCsmId(csMember.getCsmId());
						csMemberInvite.setCsmRefer(inviteMember.getCsmId());
						csMemberInvite.setCsmReferType((short) 1);
						csMemberInvite.setCsmFrom((short) 1);
						csMemberService.updateCsMember$NotNull(csMemberInvite);
						if (cookieValue == null || !cookieValue.equals("only")) {
							// 发放邀请积分
							commonMoneyService.updateIntegral(inviteMember.getCsmId(), Constant.VERY_INVITATION * 1.0, IntegralType.邀请好友, CommonMessage.MSG0306.replace("{point}", String.valueOf(Constant.VERY_INVITATION)).replace("{mobile}",
									SystemHelper.mobileConvert2Star(csMember.getCsmMobile())), null);
							// 发放邀请积分
							commonMoneyService.updateIntegral(csMember.getCsmId(), Constant.VERY_INVITATION_PASSIVE * 1.0, IntegralType.邀请好友, CommonMessage.MSG0312.replace("{point}", String.valueOf(Constant.VERY_INVITATION_PASSIVE)).replace("{mobile}",
									SystemHelper.mobileConvert2Star(inviteMember.getCsmMobile())), null);
							$.setCookie("/", "inviteCount", "only");
						}
					}
					// $.SetTips("真实姓名，驾驶证号，驾驶证照片地址等信息完善成功");
					return null;
				}
			});
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 更新邮寄地址
	 * 
	 * @return
	 */
	public String postAddress() {
		try {
			if (!SystemHelper.isLogin())
				return SystemHelper.gotoLogin();
			String csmiAddress = $.getString("txtPostadd");
			if ($.empty(csmiAddress)) {
				// $.SetTips("未输入邮寄地址，不能注册");
				return $.SendHtml("101", "UTF-8");
			}
			// 更新会员的扩展信息
			CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
			if (csMemberInfo == null) {
				// 如果没有会员的扩展信息，转到step2
				return $.SendHtml("102", "UTF-8");
			}
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			// 设置扩展信息：关联的会员帐号，邮寄地址，更新时间
			CsMemberInfo newCsMemberInfo = new CsMemberInfo();
			newCsMemberInfo.csmiId(csMember.getCsmInfo()).csmiAddress(csmiAddress).csmiUpdateTime(new Date());
			csMemberInfoService.updateCsMemberInfo$NotNull(newCsMemberInfo);

			// $.SetTips("邮寄地址信息完善成功");
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}

	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsMemberInfoService getCsMemberInfoService() {
		return csMemberInfoService;
	}

	public void setCsMemberInfoService(ICsMemberInfoService csMemberInfoService) {
		this.csMemberInfoService = csMemberInfoService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}
}
