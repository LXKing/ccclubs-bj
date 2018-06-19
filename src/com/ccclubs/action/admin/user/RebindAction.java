package com.ccclubs.action.admin.user;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsEvCardService;
import com.ccclubs.service.admin.ICsMemberService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.util.Ctrl;

public class RebindAction {
	
	ICsMemberService csMemberService;
	CsMember csMember;
	
	String cardno;
	String rfid;
	
	public String execute(){	
		try{
			//为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
			$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime()-SYSTEM.MINUTE));
			
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//查询条件传递到转跳
			$.SetRequest("entrypoint", $.getString("entrypoint"));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	public String submit(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csMember);//进入之前的预处理	
			$.SetRequest("controller",controller);

			//多个写数据动作合并事务过程
			String strError = csMemberService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){					
					csMember = csMemberService.getCsMember($.add("csmMobile", csMember.getCsmMobile()));					
					if(csMember==null)
						return (T)"会员不存在，请重新输入";
					
					ICsEvCardService csEvCardService=$.getBean("csEvCardService");
					CsEvCard csEvCard = csEvCardService.getCsEvCard($.add("definex","csec_number like '%"+cardno+"%'" ));
					if(csEvCard==null){
						if($.empty(rfid))
							return (T)"请输入卡片识别码";
						if(csEvCardService.getCsEvCard($.add(CsEvCard.F.csecRfid, rfid))!=null){
							return (T)"卡片识别码已存在";
						}
						if(rfid.length()!=8){
							return (T)"卡片识别码必须是八位";
						}
						csEvCard = csEvCardService.saveCsEvCard(
							new CsEvCard()
							.csecAddTime(new Date())
							.csecFlag((short)1)
							.csecNumber(cardno)
							.csecRfid(rfid.toUpperCase())
							.csecStatus((short)1)
						);						
					}else if(csMemberService.getCsMember($.add("csmEvcard", csEvCard.getCsecId()))!=null){						
						return (T)"EV卡号已经被其它会员绑定，请重新输入";
					}
					
					csMemberService.updateCsMemberByConfirm(
						$.add(CsMember.F.csmEvcard, csEvCard.getCsecId()),
						$.add(CsMember.F.csmId, csMember.getCsmId()).add("confirm", 1));
										
					//记录操作日志
					LoggerHelper.writeLog(CsMember.class,"update","绑定了会员卡["+csMember.getCsmName()+"]",(Long)$.getSession("ccclubs_login_id"), csMember);
										
					return null;
				}
			});
			
			
			if($.getSession("EXTFIX")!=null){
				if($.empty(strError)){
					$.SetTips("会员卡绑定成功");
					return $.Redirect("rebind.do");
				}else{
					$.SetTips(strError);
					return execute();
				}
			}else{
				if($.empty(strError)){
					$.SetTips("会员卡绑定成功,你可以继续绑定下一个");
					String redirectUrl = ControllerHelper.getValue(controller, "redirect", null);//自定义转跳地址
					if(!$.empty(redirectUrl))return $.Redirect(redirectUrl);
					
					if($.getString("submiter","").indexOf("保存")>-1){
						return $.Redirect("rebind.do");
					}	
				}else{
					$.SetTips(strError);
				}
							
			}
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		if($.getSession("EXTFIX")!=null){
			return execute();
		}else{
			return $.Redirect($.xeh($.getString("entrypoint")));
		}
	}	
	
	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}
	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
	public CsMember getCsMember() {
		return csMember;
	}

	public void setCsMember(CsMember csMember) {
		this.csMember = csMember;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	

}
