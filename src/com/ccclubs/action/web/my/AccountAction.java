package com.ccclubs.action.web.my;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsIntegralRecord;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class AccountAction {
	ISrvPropertyService srvPropertyService;
	ICsMemberService csMemberService;
	ICsMemberInfoService csMemberInfoService;
	ICsRecordService csRecordService;
	ICommonMoneyService commonMoneyService;

	/**
	 * 个人中心 帐户管理页面
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
		$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));
		// 此处不从Session中获取当前用户，而是通过id查询出用户，怕当前用户的部分信息更改了，部分字段值不一致
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		// 会员的扩展信息
		CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
		if (csMemberInfo == null) {
			// TODO：企业用户是否也需要去上传驾照资料
			return "account-step2";
		}
		if ($.empty(csMemberInfo.getCsmiAddress()) && member.getCsmEvcard() == null) {
			// 没拿到卡，并且未完善邮寄地址
			return "account-step3";
		}
		if ($.empty(csMemberInfo.getCsmiDriverImage())) {
			// 驾驶证照片没完善，或真实姓名没填写，之前注册的。
			return "account-step4";
		}
		$.SetRequest("memberInfo", csMemberInfo);
		// 设置可选区域
//		SrvProperty root = srvPropertyService.getSrvProperty(Lazy.add(SrvProperty.F.spFlag, Constant.AREA_ROOT_TYPE_VALUE));
//		List<SrvProperty> areas = srvPropertyService.getSrvPropertyList(Lazy.add(SrvProperty.F.spParent, root.getSpId()), -1);
//		Lazy.SetRequest("areas", areas);
		
		ICsAreaService csAreaService = Lazy.GetSpringBean("csAreaService");
		List<CsArea> areas = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, csMemberInfo.getCsmiHost()).add("desc", CsArea.C.csaLevel), -1);
		Lazy.SetRequest("areas", areas);
		
		// 默认网点不为空
		CsOutlets defalutCsOutlets = member.get$csmOutlets();
		Long csoOutlets = 0l;
		Long csoArea = 0l;
		if (defalutCsOutlets != null) {
			csoArea = defalutCsOutlets.getCsoArea();
			csoOutlets = defalutCsOutlets.getCsoId();
		}
		Lazy.SetRequest("csoOutlets", csoOutlets);
		Lazy.SetRequest("csoArea", csoArea);
		int base = Constant.VERY_BASIC_INFO + Constant.VERY_CONTACT_INFO + Constant.VERY_HEADER + Constant.VERY_MOBILE;
		int sum = Constant.VERY_MOBILE;
		// 如果有发放过完善资料信息
		Boolean isBasicInfo = isBasicInfo(member.getCsmId());
		Lazy.SetRequest("isBasicInfo", isBasicInfo);
		if (isBasicInfo)
			sum += Constant.VERY_BASIC_INFO;
		// 如果有发放过紧急联系人信息
		Boolean isContactInfo = isContactInfo(member.getCsmId());
		Lazy.SetRequest("isContactInfo", isContactInfo);
		if (isContactInfo)
			sum += Constant.VERY_CONTACT_INFO;
		// 如果有发放头像积分
		Boolean isHeaderInfo = isHeadInfo(member.getCsmId());
		if (isHeaderInfo)
			sum += Constant.VERY_HEADER;
		Lazy.SetRequest("sum", sum);
		Lazy.SetRequest("base", base);
		return "account";
	}

	/**
	 * 修改头像
	 * 
	 * @return
	 */
	public String setheader() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		return "setheader";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String reset() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		return "resetpass";
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	public String resetstep2() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		return "resetpass-step2";
	}

	/**
	 * 修改密码成功页面
	 * 
	 * @return
	 */
	public String resetsucc() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		return "resetpass-succ";
	}

	/**
	 * 验证当前密码，验证码
	 * 
	 * @return
	 */
	public String verify() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");
			String txtPass = $.getString("txtPass", "");
			String strValidCode = $.getString("txtCode", "");
			if ($.empty(txtPass)) {
				// $.SetTips("未输入手机号码，不能修改密码");
				return $.SendHtml("102", "UTF-8");
			}
			if ($.empty(strValidCode)) {
				// $.SetTips("未输入验证码，不能修改密码");
				return $.SendHtml("103", "UTF-8");
			}
			if (!$.equals(strValidCode.toLowerCase(), ((String) $.getSession(Constant.VERIFY_CODE)).toLowerCase())) {
				// $.SetTips("注册码输入错误");
				return $.SendHtml("104", "UTF-8");
			}
			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			// ----------------------------------------------------------验证通过，进入到修改密码页面
			if (!$.md5(RSAUtil.decrypt(txtPass)).equals(member.getCsmPassword().toUpperCase())) {
				// 你输入的密码不正确，请重新输入。
				return $.SendHtml("105", "UTF-8");
			}
			// 清空验证码session
			$.setSession(Constant.VERIFY_CODE, null);
			// $.SetTips("-验证通过，进入到修改密码页面");
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	public String resetPwd() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");
			String csmPassword = RSAUtil.decrypt($.getString("txtPassWord", ""));
			String rePwd = RSAUtil.decrypt($.getString("txtRePassWord", ""));
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("106", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("107", "UTF-8");
			}
			// 当前重置密码用户
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			// ----------------------------------------------------------验证通过，重置密码
			csMemberService.updateCsMember$NotNull(new CsMember().csmPassword($.md5(csmPassword)).csmId(csMember.getCsmId()));
			// $.SetTips("重置密码成功，进入到成功页面");
			// 清空当前手机号session
			$.setSession(Constant.VERIFY_MOBILE, null);

			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 是否发放 完善紧急联系人信息 积分奖励，true：发放过，false：没发放过
	 * 
	 * @return
	 */
	private Boolean isContactInfo(Long memberid) {
		return CsIntegralRecord.Get(
			$.add(CsIntegralRecord.F.csrMember,memberid)
			.add(CsIntegralRecord.F.csrType, IntegralType.完善紧急联系人信息.name())
		)!=null;
	}

	/**
	 * 是否发放 完善过个人基本信息 积分奖励，true：发放过，false：没发放过
	 * 
	 * @return
	 */
	private Boolean isBasicInfo(Long memberid) {
		return CsIntegralRecord.Get(
				$.add(CsIntegralRecord.F.csrMember,memberid)
				.add(CsIntegralRecord.F.csrType, IntegralType.完善个人基本信息.name())
			)!=null;
	}

	/**
	 * 是否发放过上传头像信息
	 * 
	 * @param memberid
	 * @return
	 */
	private Boolean isHeadInfo(Long memberid) {
		return CsIntegralRecord.Get(
				$.add(CsIntegralRecord.F.csrMember,memberid)
				.add(CsIntegralRecord.F.csrType, IntegralType.上传头像.name())
			)!=null;
	}

	/**
	 * 提交一些选填信息 ，默认取车网点，邮寄地址
	 * 
	 * @return
	 */
	public String basicInfo() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");
			//信息不完整，需要判断memberinfo 是否为空
			CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
			if (csMemberInfo == null) {
				return $.SendHtml("102", "UTF-8");
			}
			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());

			String csmiAddress = $.getString("csmiAddress", "");
			Long csmOutlets = $.getLong("csmOutlets", 0l);
			// 设置扩展信息：寄送地址，联系人，联系电话，关系，更新时间
			csMemberInfoService.updateCsMemberInfo$NotNull(new CsMemberInfo().csmiAddress(csmiAddress).csmiUpdateTime(new Date()).csmiId(member.getCsmInfo()));
			// 保存默认取车网点
			if (!csmOutlets.equals(0l)) {
				member.setCsmOutlets(csmOutlets);
				// 重新设置当前登录用户信息
				SystemHelper.setLogin(member);
				csMemberService.updateCsMember$NotNull(new CsMember().csmOutlets(csmOutlets).csmId(member.getCsmId()));
				if (!isBasicInfo(member.getCsmId())) {
					// 首次完善基本信息，送200积分
					commonMoneyService.updateIntegral(member.getCsmId(), Constant.VERY_BASIC_INFO * 1.0, IntegralType.完善个人基本信息, CommonMessage.MSG0304.replace("{point}", String
							.valueOf(Constant.VERY_BASIC_INFO)), null);
					// $.SetTips("更新头像成功");
					CsMember newMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
					SystemHelper.setLogin(newMember);
					return $.SendHtml("110", "UTF-8");
				}
			}
			// $.SetTips("注册成功，请登录");
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 提交一些选填信息 ，联系人，联系电话，关系
	 * 
	 * @return
	 */
	public String contackInfo() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");

			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			String csmiPerson = $.getString("csmiPerson");
			String csmiContact = $.getString("csmiContact");
			String csmiRelation = $.getString("csmiRelation");
			//信息不完整，需要判断memberinfo 是否为空
			CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
			if (csMemberInfo == null) {
				return $.SendHtml("104", "UTF-8");
			}
			if (!$.empty(csmiPerson)) {
				if ($.equals(csmiPerson, member.getCsmName())) {
					// $.SetTips("输入联系人姓名不能与自己姓名相同");
					return $.SendHtml("103", "UTF-8");
				}
			}
			if (!$.empty(csmiContact)) {
				if (!SystemHelper.isMobile(csmiContact)) {
					// $.SetTips("输入联系人手机号格式不对");
					return $.SendHtml("101", "UTF-8");
				}
				if ($.equals(csmiContact, member.getCsmMobile())) {
					// $.SetTips("输入联系人手机号不能与自己手机号相同");
					return $.SendHtml("102", "UTF-8");
				}
			}
			// 设置扩展信息：寄送地址，联系人，联系电话，关系，更新时间
			csMemberInfoService.updateCsMemberInfo$NotNull(new CsMemberInfo().csmiId(member.getCsmInfo()).csmiPerson(csmiPerson).csmiContact(csmiContact).csmiRelation(csmiRelation).csmiUpdateTime(
					new Date()));

			if (!isContactInfo(member.getCsmId())) {
				// 首次完善基本信息，送200积分
				commonMoneyService.updateIntegral(member.getCsmId(), Constant.VERY_CONTACT_INFO * 1.0, IntegralType.完善紧急联系人信息, CommonMessage.MSG0305.replace("{point}", String
						.valueOf(Constant.VERY_CONTACT_INFO)), null);
				// $.SetTips("更新头像成功");
				CsMember newMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
				SystemHelper.setLogin(newMember);
				return $.SendHtml("110", "UTF-8");
			}
			// $.SetTips("注册成功，请登录");
			// 兑换成功，更新当前 session 信息
			CsMember newMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			SystemHelper.setLogin(newMember);
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 修改手机号
	 * 
	 * @return
	 */
	public String rebindPhone() {
		try {
			if (!SystemHelper.isLogin())
				// 需要登录
				return $.SendHtml("101", "UTF-8");

			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			String mobile = $.getString("mobile");

			if ($.empty(mobile)) {
				// $.SetTips("输入手机号不能为空");
				return $.SendHtml("102", "UTF-8");
			}
			if (!SystemHelper.isMobile(mobile)) {
				// $.SetTips("输入手机号格式不正确");
				return $.SendHtml("103", "UTF-8");
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsMember.F.csmMobile, mobile);
			params.put("definex", CsMember.C.csmId + " <> " + member.getCsmId());
			CsMember rebindMember = csMemberService.getCsMember(params);
			if (rebindMember != null) {
				// $.SetTips("输入手机号重复，需要重新输入");
				return $.SendHtml("104", "UTF-8");
			}
			// 保存手机号
			member.setCsmMobile(mobile);
			csMemberService.updateCsMember$NotNull(new CsMember().csmMobile(mobile).csmId(member.getCsmId()));
			// 重新设置当前登录用户信息
			SystemHelper.setLogin(member);
			// $.SetTips("注册成功，请登录");
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 修改驾驶证照片
	 * 
	 * @return
	 */
	public String changelic() {
		try {
			if (!SystemHelper.isLogin())
				// 需要登录
				return SystemHelper.gotoLogin();

			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			// 会员的扩展信息
			CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
			if (csMemberInfo == null) {
				// TODO：企业用户是否也需要去上传驾照资料
				return "account-step2";
			}
			if ($.empty(csMemberInfo.getCsmiAddress()) && member.getCsmEvcard() == null) {
				// 没拿到卡，并且未完善邮寄地址
				return "account-step3";
			}
			$.SetRequest("memberInfo", csMemberInfo);
			return "changelic";
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 修改头像
	 * 
	 * @return
	 */
	public String userpicUpload() {
		try {
			if (!SystemHelper.isLogin())
				// 需要登录
				return $.SendHtml("101", "UTF-8");

			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			String userpic = $.getString("userpic", "");

			if ($.empty(userpic))
				// $.SetTips("上传图片地址不能为空");
				return $.SendHtml("102", "UTF-8");

			// 保存用户头像
			member.setCsmHeader(userpic);
			csMemberService.updateCsMember$NotNull(new CsMember().csmHeader(userpic).csmId(member.getCsmId()).csmUpdateTime(new Date()));
			// 重新设置当前登录用户信息
			SystemHelper.setLogin(member);
			// 如果没有发放过头像上传积分
			if (!isHeadInfo(member.getCsmId())) {
				// 上传头像成功，送100积分
				commonMoneyService.updateIntegral(member.getCsmId(), Constant.VERY_HEADER * 1.0, IntegralType.上传头像, CommonMessage.MSG0303.replace("{point}", String
						.valueOf(Constant.VERY_HEADER)), null);
				// $.SetTips("更新头像成功");
				// 兑换成功，更新当前 session 信息
				CsMember newMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
				SystemHelper.setLogin(newMember);
				return $.SendHtml("110", "UTF-8");
			} else {
				// $.SetTips("更新头像成功");
				return $.SendHtml("100", "UTF-8");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 确认 修改的手机号 是否存在
	 * 
	 * @return
	 */
	public String checkPhone() {
		try {
			if (!SystemHelper.isLogin())
				// 需要登录
				return $.SendHtml("101", "UTF-8");
			String strMobile = $.getString("txtMobile");

			Map<String, Object> params = new HashMap<String, Object>();
			if ($.empty(strMobile) || !SystemHelper.isMobile(strMobile))
				return $.SendHtml("false", "UTF-8");

			CsMember member = SystemHelper.getLogin();
			params.put(CsMember.F.csmMobile, strMobile);
			params.put("definex", CsMember.C.csmId + " <> " + member.getCsmId());

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
	 * 驾驶证信息 ，真实姓名，驾驶证号，驾驶证照片地址
	 * 
	 * @return
	 */
	public String driverInfo() {
		try {
			if (!SystemHelper.isLogin())
				// 需要登录
				return $.SendHtml("101", "UTF-8");
			final String csmiName = $.getString("txtRealname");
			final String csmiDriverNum = $.getString("txtLicnum");
			final String csmiDriverImage = $.getString("txtLicnumPic");
			final String csmiCertifyImage = $.getString("idCardPic");
			if ($.empty(csmiName)) {
				// $.SetTips("未输入真实姓名，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if ($.empty(csmiDriverNum)) {
				// $.SetTips("未输入驾驶证号码，不能注册");
				return $.SendHtml("103", "UTF-8");
			}
			if ($.empty(csmiDriverImage)) {
				// $.SetTips("未上传证件照片，不能注册");
				return $.SendHtml("104", "UTF-8");
			}
			if ($.empty(csmiCertifyImage)) {
				// $.SetTips("未上传证件照片，不能注册");
				return $.SendHtml("107", "UTF-8");
			}
			// 会员的扩展信息
			CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
			if (csMemberInfo == null) {
				// 没有扩展信息，刷新页面，跳转到注册第2步
				return $.SendHtml("105", "UTF-8");
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsMemberInfo.F.csmiDriverNum, csmiDriverNum);
			params.put("definex", CsMemberInfo.C.csmiId + " <> " + csMemberInfo.getCsmiId());
			CsMemberInfo existMemberInfo = csMemberInfoService.getCsMemberInfo(params);
			if (existMemberInfo != null)// $.SetTips("驾驶证号码已存在，不能注册");
				return $.SendHtml("106", "UTF-8");

			csMemberInfoService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					// TODO Auto-generated method stub
					// 更新会员的扩展信息
					CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));

					CsMemberInfo newMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, SystemHelper.getLoginId()));
					// 设置扩展信息：关联的会员帐号，驾照照片路径，驾照号码，真实姓名，更新时间
					newMemberInfo.csmiId(csMemberInfo.getCsmiId()).csmiCertifyImage(csmiCertifyImage).csmiDriverImage(csmiDriverImage).csmiDriverNum(csmiDriverNum).csmiName(csmiName).csmiUpdateTime(new Date());
					csMemberInfoService.updateCsMemberInfo$NotNull(newMemberInfo);

					CsMember csMember = new CsMember().csmId(SystemHelper.getLoginId()).csmInfo(csMemberInfo.getCsmiId());
					// 比较修改的内容，决定会员的审核状态,如果任意其中一个被修改过，则更新其对应的状态
					if (!$.equals(csmiName, csMemberInfo.getCsmiName()))
						csMember.setCsmVReal((short) 2);
					if (!$.equals(csmiCertifyImage, csMemberInfo.getCsmiCertifyImage()) || !$.equals(csmiDriverImage, csMemberInfo.getCsmiDriverImage()) || !$.equals(csmiDriverNum, csMemberInfo.getCsmiDriverNum()))
						csMember.csmVDrive((short) 2);

					// 真实姓名同步到会员表中
					csMember.setCsmName(csmiName);
					csMember.setCsmUpdateTime(new Date());

					csMemberService.updateCsMember$NotNull(csMember);

					csMember = csMemberService.getCsMemberById(csMember.getCsmId());
					SystemHelper.setLogin(csMember);
					
					com.ccclubs.helper.EventHelper.postEvent(
							csMember.getCsmHost(), 
							com.ccclubs.helper.EventHelper.EventType.会员上传资料图片,
							"会员"+csMember.getCsmMobile()+"上传了驾证图片，请及时审核", 
							csMember.getCsmId(),CsMember.class);
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

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
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

	public ICsRecordService getCsRecordService() {
		return csRecordService;
	}

	public void setCsRecordService(ICsRecordService csRecordService) {
		this.csRecordService = csRecordService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}
}
