package com.ccclubs.action.manage;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.SrvHost;
import com.lazy3q.web.helper.$;

public class RegisterAction {
	/*
	 * 内部帮助注册
	 */
	@SuppressWarnings("static-access")
	public String register(){
		try {
			String tel=$.getString("membertel");
			String password=$.getString("memberpwd");
			String bercard=$.getString("membercard");
			String mberdig=$.getString("memberdig");
			String cardImg=$.getString("cardImg");
			String digImg=$.getString("digImg");
			if(!tel.equals("")&&tel!=null&&!tel.equals("undefined")){
				if(!password.equals("")&&password!=null&&!password.equals("undefined")){
					if(!bercard.equals("")&&bercard!=null&&!bercard.equals("undefined")){
						if(!mberdig.equals("")&&mberdig!=null&&!mberdig.equals("undefined")){
							if(!cardImg.equals("")&&cardImg!=null&&!cardImg.equals("undefined")){
								if(!digImg.equals("")&&digImg!=null&&!digImg.equals("undefined")){
									//获取区域列表	
									Long host = SYSTEM.getDefaultHost();
									if(host==null)
										host = SrvHost.Get($.add(SrvHost.F.shFlag, "杭州")).getShId();
									CsMember csMember = new CsMember();
									CsMemberInfo csMemberInfo=new CsMemberInfo();
									if(csMember.where().csmMobile(tel).get()==null||csMember.where().csmMobile(tel).get().equals("")){
										//存储基本信息
										csMember.setNotNull(host, "", $.md5(password),  0d, 0d, 0d, 0, 0, 1d, new Date(), (short) 1);
										csMember.setCsmMobile(tel);
										csMember.save();
										//存储身份证和驾驶证
										Long id=csMember.where().add(csMember.getCsmName(), tel).get().getCsmId();
										csMemberInfo.setNotNull(host,id, new Date(), new Date(),(short)1);
										csMemberInfo.setCsmiCertifyNum(bercard);
										csMemberInfo.setCsmiCertifyImage(cardImg);
										csMemberInfo.setCsmiCertifyType((short) 1);
										csMemberInfo.setCsmiDriverNum(mberdig);
										csMemberInfo.setCsmiDriverImage(digImg);
										csMemberInfo.save();
										$.SetTips("注册成功！");
										return $.Redirect("mgregister_registras.html");
									}else{
										$.SetTips("电话号码已经被注册过！");
										return $.Redirect("mgregister_registra.html");
									}
								}else{$.SetTips("驾驶证图片不能为空！");return $.Redirect("mgregister_registra.html");}
							}else{$.SetTips("身份证图片不能为空！");return $.Redirect("mgregister_registra.html");}
						}else{$.SetTips("驾驶证号码不能为空！");return $.Redirect("mgregister_registra.html");}
					}else{$.SetTips("身份证号码不能为空！");return $.Redirect("mgregister_registra.html");}
				}else{$.SetTips("密码不能为空！");return $.Redirect("mgregister_registra.html");}
			}else{$.SetTips("用户名不能为空！");return $.Redirect("mgregister_registra.html");}
		} catch (Exception e) {
			e.printStackTrace();
			$.SetTips("系统错误请联系管理员！");return $.Redirect("mgregister_registra.html");
		}
	}
	/*
	 * 获取微信的安全参数
	 * @return
	 */
	public String registra(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, String> ret = WeixinTokenHelper.getSign(WeixinTokenHelper.getCompleteUrl(request));
			$.SetRequest("map", ret);
			$.SetRequest("csMember", WeixinHelper.getCsMember());
			return "user_registra";
		} catch (Exception e) {
			e.printStackTrace();
			return "系统错误！";
		}
	}
	/*
	 * 获取微信的安全参数
	 * @return
	 */
	public String registras(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, String> ret = WeixinTokenHelper.getSign(WeixinTokenHelper.getCompleteUrl(request));
			$.SetRequest("map", ret);
			$.SetRequest("csMember", WeixinHelper.getCsMember());
			return "user_registras";
		} catch (Exception e) {
			e.printStackTrace();
			return "系统错误！";
		}
	}
	/*
	 * 绑定会员卡
	 * 
	 * @return
	 */
	public String bindMembercard(){
		try {
			int user_number=Integer.valueOf($.getString("user_number")).intValue();
			String card_number=$.getString("card_number");
			if(card_number!=null&&!card_number.equals("")&&card_number!="undefined"){
				System.out.println("编号："+user_number+"卡号："+card_number);
				CsEvCard csEvCard=CsEvCard.where().csecNumber(card_number).get();
				if(csEvCard!=null&&!csEvCard.equals("")){
					System.out.println(csEvCard.getCsecId());
					CsMember csMember=CsMember.where().csmEvcard(csEvCard.getCsecId()).get();
					if(csMember==null||csMember.equals("")){
						CsMember csmember=CsMember.where().csmId((long) user_number).get();
						if(csmember.getCsmEvcard()==null||csmember.getCsmEvcard().equals("")){
							csmember.setCsmEvcard(csEvCard.getCsecId());
							csmember.update();
							$.SetTips("卡号绑定成功！");
							return $.Redirect("mgregister_registras.html");
						}else{
							$.SetTips("用户已经绑定过！");
							return $.Redirect("bind_card.html");
						}
					}else{
						$.SetTips("卡号已经被绑定！");
						return $.Redirect("bind_card.html");
					}
				}else{
					$.SetTips("该卡号不存在！");
					return $.Redirect("bind_card.html");
				}
			}
			return $.Redirect("bind_card.html");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			$.SetTips("系统错误请重试！");
			return $.Redirect("bind_card.html");
		}
	}
	/*
	 * 修改密码
	 * 
	 * @return
	 * 
	 */
	public String changepassword(){
		try {
			int userid = Integer.valueOf($.getString("userid")).intValue();
			String oldpassword=$.getString("oldpassword");
			String newpassword=$.getString("newpassword");
			String newpasswords=$.getString("newpasswords");
			CsMember csmember=CsMember.where().csmEvcard((long) userid).get();
			if(oldpassword!=null&&oldpassword!="undefined"&&!oldpassword.equals("")){
				if(newpassword!=null&&newpassword!="undefined"&&!newpassword.equals("")){
					if(newpasswords!=null&&newpasswords!="undefined"&&!newpasswords.equals("")){
						if($.md5(oldpassword).equalsIgnoreCase(csmember.getCsmPassword())){
							if(newpassword.equals(newpasswords)){
								csmember.setCsmPassword($.md5(newpasswords));
								csmember.update();
								$.SetTips("修改密码成功！");
								return $.Redirect("mgregister_registras.html");
							}else{
								$.SetTips("两次密码不一致！");
								return $.Redirect("change_password.html");
							}
						}else{
							$.SetTips("原密码不正确!");
							return $.Redirect("change_password.html");
						}
					}else{
						$.SetTips("新密码不能为空!");
						return $.Redirect("change_password.html");
					}
				}else{
					$.SetTips("新密码不能为空!");
					return $.Redirect("change_password.html");
				}
			}else{
				$.SetTips("旧密码不能为空!");
				return $.Redirect("change_password.html");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			$.SetTips("系统错误请联系管理员!");
			return $.Redirect("change_password.html");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
