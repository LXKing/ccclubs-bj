package com.ccclubs.action.thread.im;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayMobilePublicMessagePushRequest;
import com.alipay.api.response.AlipayMobilePublicMessagePushResponse;
import com.ccclubs.action.weixin.alipay.constants.AlipayServiceEnvConstants;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.WeixinHelper.WxTemplateMsgType;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsViolat;
import com.ccclubs.model.CsWxMt;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsViolatService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

public class AlipaySendMessage implements ISendMessage {

	@Override
	public int getMask() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int sendNewsMessage(CsWxMt csWxMt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendPicMessage(CsWxMt csWxMt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendTemplateMessage(CsWxMt csWxMt) {
		int result = 0;
		try {
			// 模板信息必须有关联会员，关联对象
			if (csWxMt.getCswmMember() == null || csWxMt.getCswmRelate() == null) {
				return 0;
			}
			CsMember member = csWxMt.get$cswmMember();
			if (member == null || $.empty(member.getCsmAlipayFlag())) {
				return 0;
			}
			WxTemplateMsgType type = WxTemplateMsgType.valueOf(csWxMt.getCswmMsgFlag());
			ICsOrderService csOrderService = $.getBean("csOrderService");

			switch (type) {
				case 订单续订:
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderContinueId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderContinue = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderContinueId));
					if (csOrderContinue == null) {
						return 0;
					}
					sendContinueOrderMsg(csWxMt, csOrderContinue, member);
					result = 1;
					break;
				case 订单取消:
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderCancelId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderCancel = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderCancelId));
					if (csOrderCancel == null) {
						return 0;
					}
					getCancelOrderMsg(csWxMt, csOrderCancel, member);
					result = 1;
					break;
				case 订单结算:
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderFinishId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderFinish = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderFinishId));
					if (csOrderFinish == null || csOrderFinish.getCsoRetTime() == null) {
						return 0;
					}
					getFinishOrderMsg(csWxMt, csOrderFinish, member);
					result = 1;
					break;
				case 订单预订:
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderPreId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderPre = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderPreId));
					if (csOrderPre == null) {
						return 0;
					}
					getPreOrderMsg(csWxMt, csOrderPre, member);
					result = 1;
					break;
				case 违章通知:
					if (!csWxMt.getCswmRelate().startsWith(CsViolat.class.getSimpleName())) {
						return 0;
					}
					ICsViolatService csViolatService = $.getBean("csViolatService");
					String violatId = csWxMt.getCswmRelate().split("@")[1];
					CsViolat csViolat = csViolatService.getCsViolat($.add(CsViolat.F.csvId, violatId));
					if (csViolat == null || csViolat.getCsvCutoffTime() == null) {
						return 0;
					}
					sendViolatMsg(csWxMt, csViolat, member);
					result = 1;
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int sendTextMessage(CsWxMt csWxMt) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void sendViolatMsg(CsWxMt csWxMt, CsViolat csViolat, CsMember csMember){
		String alipayuserid = csMember.getCsmAlipayFlag();
		if (alipayuserid != null && alipayuserid.length() != 0) {
			String url = "http://m.ccclubs.com/m/home/account_violat.html";
			String desc = "尊敬的 " + csMember.getCsmName() + " ，您在租车期间产生了违章需要处理。<br>违章地点：" + csViolat.getCsvDecipt()
					+ "<br>违章时间：" + new DateUtil().dateToString(csViolat.getCsvHappenTime(), null) + "<br>罚款："
					+ "扣 " + csViolat.getCsvScore() + " 分，罚款 " + $.digit(csViolat.getCsvMoney(), 2) + " 元<br>车牌："
					+ csViolat.get$csvCar().getCscCarNo() + "<br><br>请在 " + new DateUtil().dateToString(csViolat.getCsvCutoffTime(), null) + " 前完成违章信息处理，以免影响您的后续租车。";
			// 菜单内容
			String biz_content = "<XML><ToUserId><![CDATA[" + alipayuserid + "]]></ToUserId><AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID
					+ "]]></AppId><AgreementId><![CDATA[]]></AgreementId><CreateTime>1381547619675</CreateTime><MsgType><![CDATA[image-text]]></MsgType><ArticleCount>1</ArticleCount>"
					+ "<Articles><Item><Title><![CDATA[违章通知]]></Title><Desc><![CDATA[" + desc
					+ "]]></Desc><ImageUrl><![CDATA[]]></ImageUrl><Url><![CDATA[" + url + "]]></Url></Item></Articles><Push><![CDATA[false]]></Push></XML>";
			sendAlipay(biz_content);
		}
	}
	
	private void sendContinueOrderMsg(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember){
		String alipayuserid = csMember.getCsmAlipayFlag();
		if (alipayuserid != null && alipayuserid.length() != 0) {
			String url = "http://m.ccclubs.com/m/home/order_operate.html?orderid=" + csOrder.getCsoId();
			String desc = "尊敬的 " + csMember.getCsmName() + " ，您的订单续订成功<br>订单号：" + csOrder.getCsoId() + "<br>订单状态：已续订<br>取车时间："
					+ new DateUtil().dateToString(csOrder.getCsoStartTime(), null) + "<br>还车时间：" + new DateUtil().dateToString(csOrder.getCsoFinishTime(), null)
					+ "<br>车型：" + csOrder.get$csoModel().getCscmName() + "<br>车牌号：" + csOrder.get$csoCar().getCscCarNo()
					+ "<br><br>如有任何疑问，请拨打：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899");
			// 菜单内容
			String biz_content = "<XML><ToUserId><![CDATA[" + alipayuserid + "]]></ToUserId><AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID
					+ "]]></AppId><AgreementId><![CDATA[]]></AgreementId><CreateTime>1381547619675</CreateTime><MsgType><![CDATA[image-text]]></MsgType><ArticleCount>1</ArticleCount>"
					+ "<Articles><Item><Title><![CDATA[订单续订]]></Title><Desc><![CDATA[" + desc
					+ "]]></Desc><ImageUrl><![CDATA[]]></ImageUrl><Url><![CDATA[" + url + "]]></Url></Item></Articles><Push><![CDATA[false]]></Push></XML>";
			sendAlipay(biz_content);
		}
	}
	
	private void getCancelOrderMsg(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember){
		String alipayuserid = csMember.getCsmAlipayFlag();
		if (alipayuserid != null && alipayuserid.length() != 0) {
			String url = "http://m.ccclubs.com/m/home/order_detail.html?orderid=" + csOrder.getCsoId();
			String desc = "尊敬的 " + csMember.getCsmName() + " ，您的订单已取消<br>订单状态：已取消<br>取车时间："
					+ new DateUtil().dateToString(csOrder.getCsoStartTime(), null) + "<br>还车时间：" + new DateUtil().dateToString(csOrder.getCsoFinishTime(), null)
					+ "<br>车型：" + csOrder.get$csoModel().getCscmName() + "<br>车牌号：" + csOrder.get$csoCar().getCscCarNo()
					+ "<br>订单取消时间：" + new DateUtil().dateToString(csOrder.getCsoEndTime(), null)
					+ "<br>手续费：" + $.digit(csOrder.getCsoPayReal(), 2) + " 元<br><br>如有任何疑问，请拨打：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899");
			// 菜单内容
			String biz_content = "<XML><ToUserId><![CDATA[" + alipayuserid + "]]></ToUserId><AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID
					+ "]]></AppId><AgreementId><![CDATA[]]></AgreementId><CreateTime>1381547619675</CreateTime><MsgType><![CDATA[image-text]]></MsgType><ArticleCount>1</ArticleCount>"
					+ "<Articles><Item><Title><![CDATA[订单取消]]></Title><Desc><![CDATA[" + desc
					+ "]]></Desc><ImageUrl><![CDATA[]]></ImageUrl><Url><![CDATA[" + url + "]]></Url></Item></Articles><Push><![CDATA[false]]></Push></XML>";
			sendAlipay(biz_content);
		}
	}
	
	private void getFinishOrderMsg(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember){
		String alipayuserid = csMember.getCsmAlipayFlag();
		if (alipayuserid != null && alipayuserid.length() != 0) {
			String url = "http://m.ccclubs.com/m/home/order_detail.html?orderid=" + csOrder.getCsoId();
			String desc = "尊敬的 " + csMember.getCsmName() + " ，您的订单已完成<br>订单号：" + csOrder.getCsoId()
					+ "<br>订单状态：已完成<br>取车时间：" + new DateUtil().dateToString(csOrder.getCsoStartTime(), null)
					+ "<br>还车时间：" + new DateUtil().dateToString(csOrder.getCsoRetTime(), null) + "<br>车型：" + csOrder.get$csoModel().getCscmName()
					+ "<br>车牌号：" + csOrder.get$csoCar().getCscCarNo() + "<br><br>如有任何疑问，请拨打：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899");
			// 菜单内容
			String biz_content = "<XML><ToUserId><![CDATA[" + alipayuserid + "]]></ToUserId><AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID
					+ "]]></AppId><AgreementId><![CDATA[]]></AgreementId><CreateTime>1381547619675</CreateTime><MsgType><![CDATA[image-text]]></MsgType><ArticleCount>1</ArticleCount>"
					+ "<Articles><Item><Title><![CDATA[订单结算]]></Title><Desc><![CDATA[" + desc
					+ "]]></Desc><ImageUrl><![CDATA[]]></ImageUrl><Url><![CDATA[" + url + "]]></Url></Item></Articles><Push><![CDATA[false]]></Push></XML>";
			sendAlipay(biz_content);
		}
	}
	
	private void getPreOrderMsg(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember){
		String alipayuserid = csMember.getCsmAlipayFlag();
		if (alipayuserid != null && alipayuserid.length() != 0) {
			String url = "http://m.ccclubs.com/m/home/order_detail.html?orderid=" + csOrder.getCsoId();
			String desc = "尊敬的 " + csMember.getCsmName() + " ，您预定的车辆信息：<br>订单号：" + csOrder.getCsoId()
					+ "<br>取车时间：" + new DateUtil().dateToString(csOrder.getCsoStartTime(), null) + "<br>还车时间："
					+ new DateUtil().dateToString(csOrder.getCsoFinishTime(), null) + "<br>租期：" + SystemHelper.getTimeDesc(csOrder.getCsoStartTime(), csOrder.getCsoFinishTime())
					+ "<br>车型：" + csOrder.get$csoModel().getCscmName() + "<br>车牌号：" + csOrder.get$csoCar().getCscCarNo()
					+ "<br>授权码：" + csOrder.getCsoCode() + "<br>取车网点：" + csOrder.get$csoOutlets().getCsoName() + "<br><br>如有任何疑问，请拨打：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899");
			// 菜单内容
			String biz_content = "<XML><ToUserId><![CDATA[" + alipayuserid + "]]></ToUserId><AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID
					+ "]]></AppId><AgreementId><![CDATA[]]></AgreementId><CreateTime>1381547619675</CreateTime><MsgType><![CDATA[image-text]]></MsgType><ArticleCount>1</ArticleCount>"
					+ "<Articles><Item><Title><![CDATA[订单预定]]></Title><Desc><![CDATA[" + desc
					+ "]]></Desc><ImageUrl><![CDATA[]]></ImageUrl><Url><![CDATA[" + url + "]]></Url></Item></Articles><Push><![CDATA[false]]></Push></XML>";
			sendAlipay(biz_content);
		}
	}
	
	private void sendAlipay(String biz_content){
		// 发送菜单
		AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json");
		AlipayMobilePublicMessagePushRequest req = new AlipayMobilePublicMessagePushRequest();
		req.setBizContent(biz_content);
		AlipayMobilePublicMessagePushResponse res = null;
		try {
			res = client.execute(req);
			res.isSuccess();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
}
