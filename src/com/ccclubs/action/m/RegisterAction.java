package com.ccclubs.action.m;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.weixin.WeixinTokenHelper;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberGroup;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.From;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;

public class RegisterAction {
	
	final DefaultJRedisClient<String, Object> jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_WEIXIN);
	
	
	public Boolean isCorrectOpenId(){
		return WeixinHelper.isCorrectOpenId();
	}
	
	public String execute(){
		if (WeixinHelper.getCsMember() != null) {
			return "success";
		}
		String flag = $.getString("flag", "");
		if (!$.empty(flag)) {
			$.setSession("cooperation", flag.toUpperCase());
		}
		// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
		$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));
		List<SrvHost> hosts = SrvHost.where().shState((short)1).list(-1);
		$.setRequest("hosts", hosts);
		SrvHost defaultHost = SystemHelper.getSrvHostByClientIp();
		if(defaultHost==null)
			defaultHost = SrvHost.where().shFlag("杭州").get();
		$.setRequest("defaultHost", defaultHost);
		return "register";
	}
	
	/**
	 * 提交注册
	 * @return
	 */
	public String saveAccount(){
		try {
			//验证城市
			Long host  = $.getLong("host");
			if(host==null){
				$.SetTips("未选择城市");
				return "register";
			}
			SrvHost defaultHost = SystemHelper.getSrvHostByClientIp();
			if(defaultHost==null)
				defaultHost = SrvHost.where().shFlag("杭州").get();
			$.setRequest("defaultHost", defaultHost);
			//验证手机号码
			String mobile = $.getString("mobile");
			if(!SystemHelper.isMobile(mobile)){
				$.SetTips("手机号码输入格式错误");
				return "register";
			}
			if(CsMember.where().csmMobile(mobile).get()!=null){
				$.SetTips("手机号码已被注册");
				return "register";
			}
			//验证校验码
			String strValidMobCode = $.getString("checkcode");
			if (!$.equals(strValidMobCode, (String) $.getSession(SYSTEM.REGIST_VAILD_CODE))) {
				$.SetTips("短信校验码输入错误");
				return "register";
			}
			//验证密码
			String csmPassword = $.getString("txtPassWord", "");
			String rePwd = $.getString("txtRePassWord", "");
			if ($.empty(csmPassword)) {
				$.SetTips("未输入密码，不能注册");
				return "register";
			}
			if (csmPassword.length() < 6) {
				$.SetTips("密码设置有误，请输入6到20位有效密码");
				return "register";
			}
			if (!$.equals(csmPassword, rePwd)) {
				$.SetTips("两次密码输入不一致");
				return "register";
			}
			//保存用户信息
			final Long finalHost = host;
			final String finalPassword = csmPassword;
			final String finalMobile = mobile;
			final String from = WeixinHelper.getOpenId();
			CsMember.execute(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					String username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
					HttpServletRequest request = ServletActionContext.getRequest();
	
					CsMember csMember = new CsMember().setNotNull((finalHost), username, $.md5(finalPassword), 0d, 0d, 0d, 0, 0, 1d, new Date(), (short) 1);
					csMember.csmMobile(finalMobile);
					csMember.csmIsVip((short) 0);
					From src = WeixinHelper.switchRegPlatform(from);
					int src_from = 0;
					if(src.equals(From.微信)){
						csMember.csmFrom((short) 2);
						src_from = 2;
					}else if(src.equals(From.支付宝)){
						csMember.csmFrom((short) 4);
						src_from = 4;
					}
					switch (src_from) {
						case 4:
							csMember.csmAlipayFlag(from);
							break;
						case 2:
							csMember.csmWeixinFlag(from);
							break;
					}
					csMember.csmVDrive((short) 0).csmVEmail((short) 0).csmVMobile((short) 1).csmVReal((short) 0);
					csMember.csmLoginS(0);
					csMember.csmRegistIp(request.getRemoteAddr());
	
					//合作接入页面，需要设置组别 cooperation，如：FM93等
					String flag = $.getSession("cooperation", "");
					if (!$.empty(flag)) {
						CsMemberGroup csMemberGroup = CsMemberGroup.where().csmgFlag(flag.toUpperCase()).get();
						if (csMemberGroup != null)
							csMember.csmGroup(csMemberGroup.getCsmgId());
						$.setSession("cooperation", null);
					}
					csMember = csMember.save();
					
					// 设置 cache
					try {
						jr.set(from, csMember.getCsmId().toString());
					} catch (Exception e) {
						e.printStackTrace();
						// 记录一些东西
						try {
							String path = this.getClass().getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wx.txt";
							WeixinHelper.writeAppendToFile(path, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + e.getMessage(), "UTF-8");
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
					// 设置登录 session
					$.setSession(SYSTEM.ccclubs_web_login, csMember);
					$.setSession(SYSTEM.ccclubs_web_login_id, csMember.getCsmId());
					// 清除验证码，短信校验码session
					$.setSession(SYSTEM.REGIST_VAILD_CODE, null);
					return null;
				}
			});
			$.setSession("注册转跳到输入身份证", true);
			//return "register_identity";//通过转跳模式进入上传身份证页面
			return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
			return "register";
		}
	}
	
	/**
	 * 输入身份证
	 * @return
	 */
	public String identity(){
		if (WeixinHelper.getCsMember()==null)
			return WeixinHelper.gotoLogin();
		if($.getSession("注册转跳到输入身份证")!=null){
			$.removeSession("注册转跳到输入身份证");
			$.setRequest("regist_to_identity", true);//设置一次性标识
		}
		if($.equals($.config("debug"), "true")){
			$.setRequest("txtVerPic", "https://www.baidu.com/img/bd_logo1.png");
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> ret = WeixinTokenHelper.getSign(WeixinTokenHelper.getCompleteUrl(request));
		$.SetRequest("map", ret);
		$.SetRequest("csMember", WeixinHelper.getCsMember());
		return "register_identity";
	}
	
	/**
	 * 保存身份证
	 * @return
	 */
	public String saveIdentity(){
		try {
			if (WeixinHelper.getCsMember()==null)
				return WeixinHelper.gotoLogin();
			
			final CsMember csMember = WeixinHelper.getCsMember();
			
			//判断姓名
			final String txtName = $.getString("txtName", "");
			if($.empty(txtName)){
				$.SetTips("请输入真实姓名");
				return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
			}
			if(txtName.length()<2 && txtName.length()>16){
				$.SetTips("真实姓名在2到16个字之间");
				return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
			}
			
			//判断证件号码
			final String txtCertifyNum = $.getString("txtCertifyNum", "");
			if($.empty(txtCertifyNum)){
				$.SetTips("请输入证件号码");
				return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
			}
			
			//判断是否上传照片
			final String txtVerPic = $.getString("txtVerPic", "");		
			if($.empty(txtVerPic)){
				$.SetTips("未上传证件照片");
				return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
			}
			
			//证件类型
			final Short identityType = $.getShort("identityType");
			
			CsMemberInfo.execute(new Function(){
				@Override
				public <T> T execute(Object... object) {
					CsMemberInfo csMemberInfo = CsMemberInfo.where().csmiMemberId(csMember.getCsmId()).get();
					if (csMemberInfo == null) {//没有就创建一个
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(csMember.getCsmHost(),csMember.getCsmId(), new Date(), new Date(), (short) 1);
						csMemberInfo.setCsmiMemberId(csMember.getCsmId());
						csMemberInfo = csMemberInfo.save();
					}
					new CsMemberInfo(csMemberInfo.getCsmiId())
					.csmiName(txtName)
					.csmiCertifyType(identityType)
					.csmiCertifyImage(txtVerPic)
					.csmiCertifyNum(txtCertifyNum)
					.update();
					new CsMember(csMember.getCsmId()).csmName(txtName).csmInfo(csMemberInfo.getCsmiId()).csmVReal((short)2).update();
					
					WeixinHelper.refreshLoginSession();
					
					return null;
				}
			});
			return $.Redirect($.GetRequest("basePath") + "m/register_driver.html");
		} catch (Exception ex) {
			ex.printStackTrace(); 
			$.SetTips("系统错误,请稍候再试");
			return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
		}
	}
	
	/**
	 * 输入驾驶证
	 * @return
	 */
	public String driver(){
		if (WeixinHelper.getCsMember()==null)
			return WeixinHelper.gotoLogin();
		if($.equals($.config("debug"), "true")){//因为本地无法调用微信上传组件，先放入一个模拟图片
			$.setRequest("txtLicPic", "https://www.baidu.com/img/bd_logo1.png");
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> ret = WeixinTokenHelper.getSign(WeixinTokenHelper.getCompleteUrl(request));
		$.SetRequest("map", ret);
		$.SetRequest("csMember", WeixinHelper.getCsMember());
		return "register_driver";
	}
	
	/**
	 * 保存驾驶证
	 * @return
	 */
	public String saveDriver(){
		try {
			if (WeixinHelper.getCsMember()==null)
				return WeixinHelper.gotoLogin();
			
			final CsMember csMember = WeixinHelper.getCsMember();
			
			//判断证件号码
			final String txtLicnum = $.getString("txtLicnum", "");
			if($.empty(txtLicnum)){
				$.SetTips("请输入证件号码");
				return $.Redirect($.GetRequest("basePath") + "m/register_driver.html");
			}
			
			//判断是否上传照片
			final String txtLicPic = $.getString("txtLicPic", "");		
			if($.empty(txtLicPic)){
				$.SetTips("未上传证件照片");
				return $.Redirect($.GetRequest("basePath") + "m/register_driver.html");
			}
			
			CsMemberInfo.execute(new Function(){
				@Override
				public <T> T execute(Object... object) {
					CsMemberInfo csMemberInfo = CsMemberInfo.where().csmiMemberId(csMember.getCsmId()).get();
					if (csMemberInfo == null) {//没有就创建一个
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(SystemHelper.getSrvHost().getShId(),csMember.getCsmId(), new Date(), new Date(), (short) 1);
						csMemberInfo.setCsmiMemberId(csMember.getCsmId());
						csMemberInfo = csMemberInfo.save();
					}
					new CsMemberInfo(csMemberInfo.getCsmiId())
					.csmiDriverImage(txtLicPic)
					.csmiDriverNum(txtLicnum)
					.update();
					new CsMember(csMember.getCsmId()).csmVDrive((short)2).update();
					
					WeixinHelper.refreshLoginSession();
					
					return null;
				}
			});
			return "address";
		} catch (Exception ex) {
			ex.printStackTrace(); 
			$.SetTips("系统错误,请稍候再试");
			return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
		}
	}
	
	/**
	 * 输入地址
	 * @return
	 */
	public String address(){
		if (WeixinHelper.getCsMember()==null)
			return WeixinHelper.gotoLogin();
		return "register_address";
	}
	
	/**
	 * 保存地址
	 * @return
	 */
	public String saveAddress(){
		try {
			if (WeixinHelper.getCsMember()==null)
				return WeixinHelper.gotoLogin();
			
			final CsMember csMember = WeixinHelper.getCsMember();
			
			//判断是否输入地址
			final String txtAddress = $.getString("txtAddress", "");
			final String txtContact = $.getString("txtContact", "");
			final String txtPhone = $.getString("txtPhone", "");
			if($.empty(txtAddress)||$.empty(txtContact)||$.empty(txtPhone)){
				$.SetTips("寄送地址");
				return "register_address";
			}
			
			CsMemberInfo.execute(new Function(){
				@Override
				public <T> T execute(Object... object) {
					CsMemberInfo csMemberInfo = CsMemberInfo.where().csmiMemberId(csMember.getCsmId()).get();
					if (csMemberInfo == null) {//没有就创建一个
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(SystemHelper.getSrvHost().getShId(),csMember.getCsmId(), new Date(), new Date(), (short) 1);
						csMemberInfo.setCsmiMemberId(csMember.getCsmId());
						csMemberInfo = csMemberInfo.save();
					}
					new CsMemberInfo(csMemberInfo.getCsmiId())
					.csmiAddress(txtAddress)
					.csmiPerson(txtContact)
					.csmiContact(txtPhone)
					.update();
					return null;
				}
			});
			return $.Redirect($.GetRequest("basePath") + "m/home/index.html");
		} catch (Exception ex) {
			ex.printStackTrace(); 
			$.SetTips("系统错误,请稍候再试");
			return "register_address";
		}
	}

	@SuppressWarnings("unused")
	public String saveDriver2() {
		try {
			final String txtName = $.getString("txtName", "");
			final String txtLicnum = $.getString("txtLicnum", "");
			final String from = $.getString("from", "");
			final String txtLicPic = $.getString("txtLicPic", "");
			final String txtVerPic = $.getString("txtVerPic", "");
			final String txtaddress = $.getString("txtaddress", "");
			// String strValidMobCode = $.getString("txtMobCode", "");
			final CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}

			if ($.empty(txtName)) {
				// $.SetTips("未输入姓名，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if ($.empty(txtLicnum)) {
				// $.SetTips("未输入驾驶证号，不能注册");
				return $.SendHtml("103", "UTF-8");
			}
			if ($.empty(txtLicPic)) {
				// $.SetTips("未输入驾驶证号，不能注册");
				return $.SendHtml("104", "UTF-8");
			}
			if ($.empty(txtVerPic)) {
				// $.SetTips("未输入驾驶证号，不能注册");
				return $.SendHtml("105", "UTF-8");
			}
			// ---------------------------------------------------------通过验证，提交注册
			final ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
			csMemberService.executeTransaction(new Function() {
				ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
				@Override
				public <T> T execute(Object... arg0) {
					// 更新会员的扩展信息
					// 更新会员的扩展信息
					CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, csMember.getCsmId()));
					if (csMemberInfo == null) {
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(SystemHelper.getSrvHost().getShId(),csMember.getCsmId(), new Date(), new Date(), (short) 1);
						csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
					}
					CsMemberInfo oldMemberInfo = csMemberInfoService.getCsMemberInfoById(csMemberInfo.getCsmiId());
					// 设置扩展信息：关联的会员帐号，驾照照片路径，驾照号码，真实姓名，更新时间
					csMemberInfo.csmiMemberId(csMember.getCsmId()).csmiDriverImage(txtLicPic).csmiDriverNum(txtLicnum).csmiName(txtName).csmiCertifyImage(txtVerPic).csmiUpdateTime(new Date());
					Date birthday = SystemHelper.getBirthday(txtLicnum);
					if (birthday != null) {
						csMemberInfo.csmiBirthday(birthday);
					}
					csMemberInfo.csmiAddress(txtaddress);
					csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
					// CsMember csMember = new
					// CsMember().csmId(SystemHelper.getLoginId()).csmInfo(csMemberInfo.getCsmiId()).csmUpdateTime(new
					// Date());
					// 比较修改的内容，决定会员的审核状态,如果任意其中一个被修改过，则更新其对应的状态
					if (!$.equals(txtName, oldMemberInfo.getCsmiName()))
						csMember.setCsmVReal((short) 2);
					if (!$.equals(txtLicPic, oldMemberInfo.getCsmiDriverImage()) || !$.equals(txtLicnum, oldMemberInfo.getCsmiDriverNum()))
						csMember.setCsmVDrive((short) 2);

					// 真实姓名同步到会员表中
					csMember.setCsmName(csMemberInfo.getCsmiName());
					csMember.setCsmInfo(csMemberInfo.getCsmiId());
					csMember.setCsmUpdateTime(new Date());
					csMemberService.updateCsMember$NotNull(csMember);
					// 注册成功
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
	
	public String forgot(){
		// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
		$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));
		return "forgot";
	}
	
	public DefaultJRedisClient<String, Object> getJr() {
		return jr;
	}
}
