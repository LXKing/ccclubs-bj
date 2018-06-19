package com.ccclubs.action.thread.im;

import com.ccclubs.action.weixin.WeixinTokenHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.WeixinHelper.WxTemplateMsgType;
import com.ccclubs.model.CsEvent;
import com.ccclubs.model.CsEventType;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsViolat;
import com.ccclubs.model.CsWxMt;
import com.ccclubs.service.admin.ICsEventService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsViolatService;
import com.lazy3q.web.helper.$;

/**
 * 微信发送信息
 * 
 * @author qsxiaogang
 * 
 */
public class MicromessengerSendMessage implements ISendMessage {

	@Override
	public int getMask() {
		return 1;
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
			WxTemplateMsgType type = WxTemplateMsgType.valueOf(csWxMt.getCswmMsgFlag());
			ICsOrderService csOrderService = $.getBean("csOrderService");

			switch (type) {
				case 订单续订:
					if (!checkWxMt(csWxMt)) {
						return 0;
					}
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderContinueId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderContinue = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderContinueId));
					if (csOrderContinue == null) {
						return 0;
					}
					MicromessageJsonFormat mjfOrderContinue = getCsorderContinueJsonFormat(csWxMt, csOrderContinue, csWxMt.get$cswmMember());
					String resultOrderContinue = $.post(WeixinTokenHelper.WEIXIN_TEMPLATE_URL.replace("{token}", WeixinTokenHelper.getToken()), $.json(mjfOrderContinue), "utf-8");
					$.trace(resultOrderContinue);
					result = 1;
					break;
				case 订单取消:
					if (!checkWxMt(csWxMt)) {
						return 0;
					}
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderCancelId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderCancel = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderCancelId));
					if (csOrderCancel == null) {
						return 0;
					}
					MicromessageJsonFormat mjfOrderCancel = getCsorderCancelJsonFormat(csWxMt, csOrderCancel, csWxMt.get$cswmMember());
					String resultOrderCancel = $.post(WeixinTokenHelper.WEIXIN_TEMPLATE_URL.replace("{token}", WeixinTokenHelper.getToken()), $.json(mjfOrderCancel), "utf-8");
					$.trace(resultOrderCancel);
					result = 1;
					break;
				case 订单结算:
					if (!checkWxMt(csWxMt)) {
						return 0;
					}
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderFinishId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderFinish = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderFinishId));
					if (csOrderFinish == null || csOrderFinish.getCsoRetTime() == null) {
						return 0;
					}
					MicromessageJsonFormat mjfOrderFinish = getCsorderFinishJsonFormat(csWxMt, csOrderFinish, csWxMt.get$cswmMember());
					String resultOrderFinish = $.post(WeixinTokenHelper.WEIXIN_TEMPLATE_URL.replace("{token}", WeixinTokenHelper.getToken()), $.json(mjfOrderFinish), "utf-8");
					$.trace(resultOrderFinish);
					result = 1;
					break;
				case 订单预订:
					if (!checkWxMt(csWxMt)) {
						return 0;
					}
					if (!csWxMt.getCswmRelate().startsWith(CsOrder.class.getSimpleName())) {
						return 0;
					}
					String orderPreId = csWxMt.getCswmRelate().split("@")[1];
					CsOrder csOrderPre = csOrderService.getCsOrder($.add(CsOrder.F.csoId, orderPreId));
					if (csOrderPre == null) {
						return 0;
					}
					MicromessageJsonFormat mjfOrderPre = getCsorderPreJsonFormat(csWxMt, csOrderPre, csWxMt.get$cswmMember());
					String resultOrderPre = $.post(WeixinTokenHelper.WEIXIN_TEMPLATE_URL.replace("{token}", WeixinTokenHelper.getToken()), $.json(mjfOrderPre), "utf-8");
					$.trace(resultOrderPre);
					result = 1;
					break;
				case 违章通知:
					if (!checkWxMt(csWxMt)) {
						return 0;
					}
					if (!csWxMt.getCswmRelate().startsWith(CsViolat.class.getSimpleName())) {
						return 0;
					}
					ICsViolatService csViolatService = $.getBean("csViolatService");
					String violatId = csWxMt.getCswmRelate().split("@")[1];
					CsViolat csViolat = csViolatService.getCsViolat($.add(CsViolat.F.csvId, violatId));
					if (csViolat == null || csViolat.getCsvCutoffTime() == null) {
						return 0;
					}
					MicromessageJsonFormat mjfViolat = getViolatJsonFormat(csWxMt, csViolat, csWxMt.get$cswmMember());
					String resultViolat = $.post(WeixinTokenHelper.WEIXIN_TEMPLATE_URL.replace("{token}", WeixinTokenHelper.getToken()), $.json(mjfViolat), "utf-8");
					$.trace("resultViolat --- " + resultViolat);
					result = 1;
					break;
				case 系统报警:
					// 根据微信openid发送报警信息
					if (!csWxMt.getCswmRelate().startsWith(CsEvent.class.getSimpleName())) {
						return 0;
					}
					ICsEventService csEventService = $.getBean("csEventService");
					String eventId = csWxMt.getCswmRelate().split("@")[1];
					CsEvent csEvent = csEventService.getCsEvent($.add(CsEvent.F.cseId, eventId));
					if (csEvent == null || csEvent.getCseAddTime() == null) {
						return 0;
					}
					MicromessageJsonFormat mjfEvent = getEventJsonFormat(csWxMt, csEvent);
					String resultEvent = $.post(WeixinTokenHelper.WEIXIN_TEMPLATE_URL.replace("{token}", WeixinTokenHelper.getToken()), $.json(mjfEvent), "utf-8");
					$.trace("resultEvent --- " + resultEvent);
					result = 1;
					break;
				case 一般通知:
					MicromessageJsonFormat mjfOrdinary = getEventJsonFormat(csWxMt);
					String resultArdinary = $.post(WeixinTokenHelper.WEIXIN_TEMPLATE_URL.replace("{token}", WeixinTokenHelper.getToken()), $.json(mjfOrdinary), "utf-8");
					$.trace("resultArdinary --- " + resultArdinary);
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

	/**
	 * 检查CsWxMt
	 * 
	 * @param csWxMt
	 * @return
	 */
	private Boolean checkWxMt(CsWxMt csWxMt) {
		// 模板信息必须有关联会员，关联对象
		if (csWxMt.getCswmMember() == null || csWxMt.getCswmRelate() == null) {
			return false;
		}
		CsMember member = csWxMt.get$cswmMember();
		if (member == null || $.empty(member.getCsmWeixinFlag())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取事件报警信息
	 * @param csWxMt
	 * @return
	 */
	private MicromessageJsonFormat getEventJsonFormat(CsWxMt csWxMt) {
		MicromessageJsonFormat mjf = new MicromessageJsonFormat();
		mjf.data.add("first", $.add("value", csWxMt.getCswmContent()).add("color", "#173177"));
		mjf.data.add("keyword1", $.add("value", "北京公务出行").add("color", "#173177"));
		mjf.data.add("keyword2", $.add("value", csWxMt.getCswmAddTime$()).add("color", "#173177"));
		mjf.data.add("keyword3", $.add("value", csWxMt.getCswmMsgFlag()).add("color", "#173177"));
		mjf.data.add("remark", $.add("value", "").add("color", "#173177"));
		mjf.template_id = "zicRDzA66CXC77-NZk3xvLghcHoreSR8HbalSNAeXsc";
		mjf.touser = csWxMt.getCswmOpenid();
		return mjf;
	}

	/**
	 * 获取事件报警信息
	 * @param csWxMt
	 * @param csEvent
	 * @return
	 */
	private MicromessageJsonFormat getEventJsonFormat(CsWxMt csWxMt, CsEvent csEvent) {
		MicromessageJsonFormat mjf = new MicromessageJsonFormat();
		CsEventType csEventType = csEvent.get$cseType();
		mjf.data.add("first", $.add("value", csEvent.getCseContent()).add("color", "#173177"));
		mjf.data.add("keyword1", $.add("value", csEventType.getCsetName()).add("color", "#173177"));
		mjf.data.add("keyword2", $.add("value", csEvent.getCseAddTime$()).add("color", "#173177"));
		mjf.data.add("keyword3", $.add("value", csEventType.getCsetLevel$()).add("color", "#173177"));
		mjf.data.add("remark", $.add("value", csEvent.getCseContent()).add("color", "#173177"));
		mjf.template_id = "zicRDzA66CXC77-NZk3xvLghcHoreSR8HbalSNAeXsc";
		mjf.touser = csWxMt.getCswmOpenid();
		return mjf;
	}

	/**
	 * 违章信息发送
	 * 
	 * @param csWxMt
	 * @param csViolat
	 * @return
	 */
	private MicromessageJsonFormat getViolatJsonFormat(CsWxMt csWxMt, CsViolat csViolat, CsMember csMember) {
		MicromessageJsonFormat mjf = new MicromessageJsonFormat();
		mjf.data.add("first", $.add("value", "尊敬的 " + csMember.getCsmName() + " ，您在租车期间产生了违章需要处理。").add("color", "#173177"));
		mjf.data.add("keynote1", $.add("value", csViolat.getCsvDecipt()).add("color", "#173177"));
		mjf.data.add("keynote2", $.add("value", $.date(csViolat.getCsvHappenTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("keynote3", $.add("value", "扣 " + csViolat.getCsvScore() + " 分，罚款 " + $.digit(csViolat.getCsvMoney(), 2) + " 元").add("color", "#173177"));
		mjf.data.add("remark", $.add("value", "车牌：" + csViolat.get$csvCar().getCscCarNo() + "\n\n请在 " + $.date(csViolat.getCsvCutoffTime(), "yyyy年M月d日 HH:mm") + " 前完成违章信息处理，以免影响您的后续租车。").add("color", "#173177"));
		mjf.template_id = "NDQmPm87fIGmvd0bDl9WXRHQ_SitN0IhiPqqK4c31_U";
		mjf.touser = csMember.getCsmWeixinFlag();
		return mjf;
	}

	/**
	 * 订单预订信息发送
	 * 
	 * @param csWxMt
	 * @param csOrder
	 * @param csMember
	 * @return
	 */
	private MicromessageJsonFormat getCsorderPreJsonFormat(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember) {
		MicromessageJsonFormat mjf = new MicromessageJsonFormat();
		mjf.data.add("first", $.add("value", "尊敬的 " + csMember.getCsmName() + " ，您预定的车辆信息：").add("color", "#173177"));
		mjf.data.add("orderNo", $.add("value", csOrder.getCsoId()).add("color", "#173177"));
		mjf.data.add("takeCarAt", $.add("value", $.date(csOrder.getCsoStartTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("returnCarAt", $.add("value", $.date(csOrder.getCsoFinishTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("lease", $.add("value", SystemHelper.getTimeDesc(csOrder.getCsoStartTime(), csOrder.getCsoFinishTime())).add("color", "#173177"));
		mjf.data.add("carType", $.add("value", csOrder.get$csoModel().getCscmName()).add("color", "#173177"));
		mjf.data.add("remark", $.add("value", "车牌号：" + csOrder.get$csoCar().getCscCarNo() + "\n授权码：" + csOrder.getCsoCode() + "\n取车网点：" + csOrder.get$csoOutlets().getCsoName() + "\n\n客服热线：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899")).add("color", "#173177"));

		mjf.template_id = "B6bcXrjpZWDszg1z4hhCSkq2pnhNOlnAvursNG2d-eg";
		// 用车页面
		mjf.url = "http://m.ccclubs.com/m/home/order_operate.html?orderid=" + csOrder.getCsoId();
		mjf.touser = csMember.getCsmWeixinFlag();
		return mjf;
	}

	/**
	 * 订单完成信息发送
	 * 
	 * @param csWxMt
	 * @param csOrder
	 * @param csMember
	 * @return
	 */
	private MicromessageJsonFormat getCsorderFinishJsonFormat(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember) {
		MicromessageJsonFormat mjf = new MicromessageJsonFormat();
		mjf.data.add("first", $.add("value", "尊敬的 " + csMember.getCsmName() + " ，您的订单已完成。").add("color", "#173177"));
		mjf.data.add("orderNo", $.add("value", csOrder.getCsoId()).add("color", "#173177"));
		mjf.data.add("orderStatus", $.add("value", "已完成").add("color", "#173177"));
		mjf.data.add("takeCarAt", $.add("value", $.date(csOrder.getCsoStartTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("returnCarAt", $.add("value", $.date(csOrder.getCsoRetTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("carType", $.add("value", csOrder.get$csoModel().getCscmName()).add("color", "#173177"));
		mjf.data.add("remark", $.add("value", "车牌号：" + csOrder.get$csoCar().getCscCarNo() + "\n\n如有任何疑问，请拨打：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899")).add("color", "#173177"));

		mjf.template_id = "6B1AMvigf5U0mrk-LtgLmDcsi14W7_kJRR8Y-mJgn5o";
		// 订单详情页面
		mjf.url = "http://m.ccclubs.com/m/home/order_operate.html?orderid=" + csOrder.getCsoId();
		mjf.touser = csMember.getCsmWeixinFlag();
		return mjf;
	}

	/**
	 * 订单取消信息发送
	 * 
	 * @param csWxMt
	 * @param csOrder
	 * @param csMember
	 * @return
	 */
	private MicromessageJsonFormat getCsorderCancelJsonFormat(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember) {
		MicromessageJsonFormat mjf = new MicromessageJsonFormat();
		mjf.data.add("first", $.add("value", "尊敬的 " + csMember.getCsmName() + " ，您的订单已取消。").add("color", "#173177"));
		mjf.data.add("orderNo", $.add("value", csOrder.getCsoId()).add("color", "#173177"));
		mjf.data.add("orderStatus", $.add("value", "已取消").add("color", "#173177"));
		mjf.data.add("takeCarAt", $.add("value", $.date(csOrder.getCsoStartTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("returnCarAt", $.add("value", $.date(csOrder.getCsoFinishTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("carType", $.add("value", csOrder.get$csoModel().getCscmName()).add("color", "#173177"));
		// TODO 取消手续费
		mjf.data.add("remark", $.add("value", "车牌号：" + csOrder.get$csoCar().getCscCarNo() + "\n订单取消时间：" + $.date(csOrder.getCsoEndTime(), "yyyy年M月d日 HH:mm") + "手续费：" + $.digit(csOrder.getCsoPayReal(), 2) + " 元\n\n如有任何疑问，请拨打：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899")).add(
				"color", "#173177"));

		mjf.template_id = "6B1AMvigf5U0mrk-LtgLmDcsi14W7_kJRR8Y-mJgn5o";
		// 订单详情页面
		mjf.url = "http://m.ccclubs.com/m/home/order_operate.html?orderid=" + csOrder.getCsoId();
		mjf.touser = csMember.getCsmWeixinFlag();
		return mjf;
	}

	/**
	 * 续订成功
	 * 
	 * @param csWxMt
	 * @param csOrder
	 * @param csMember
	 * @return
	 */
	private MicromessageJsonFormat getCsorderContinueJsonFormat(CsWxMt csWxMt, CsOrder csOrder, CsMember csMember) {
		MicromessageJsonFormat mjf = new MicromessageJsonFormat();
		mjf.data.add("first", $.add("value", "尊敬的 " + csMember.getCsmName() + " ，您的订单续订成功。").add("color", "#173177"));
		mjf.data.add("orderNo", $.add("value", csOrder.getCsoId()).add("color", "#173177"));
		mjf.data.add("orderStatus", $.add("value", "已续订").add("color", "#173177"));
		mjf.data.add("takeCarAt", $.add("value", $.date(csOrder.getCsoStartTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("returnCarAt", $.add("value", $.date(csOrder.getCsoFinishTime(), "yyyy年M月d日 HH:mm")).add("color", "#173177"));
		mjf.data.add("carType", $.add("value", csOrder.get$csoModel().getCscmName()).add("color", "#173177"));
		mjf.data.add("remark", $.add("value", "车牌号：" + csOrder.get$csoCar().getCscCarNo() + "\n\n如有任何疑问，请拨打：" + (csOrder.getCsoHost() == 1 ? "0571-88190338" : "400-8080-899")).add("color", "#173177"));

		mjf.template_id = "6B1AMvigf5U0mrk-LtgLmDcsi14W7_kJRR8Y-mJgn5o";
		// 订单详情页面
		mjf.url = "http://m.ccclubs.com/m/home/order_operate.html?orderid=" + csOrder.getCsoId();
		mjf.touser = csMember.getCsmWeixinFlag();
		return mjf;
	}
}
