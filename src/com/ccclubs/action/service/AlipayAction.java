package com.ccclubs.action.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alipay.util.AlipayNotify;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsAlipayRefund;
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsAlipayRefundService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

/**
 * 会员充值
 * 
 * @author uiu
 */
public class AlipayAction {

	ICsMemberService		csMemberService;
	ICsAlipayRecordService	csAlipayRecordService;
	ICsAlipayRefundService	csAlipayRefundService;
	ICsRefundService		csRefundService;
	ICommonMoneyService		commonMoneyService;

	/**
	 * 支付宝退款通知返回
	 * 
	 * @return
	 */
	public String refundNotify() {
		try {
			HttpServletRequest request = $.getServletRequest();
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			Logger.getLogger("alipay").debug($.getQueryString("UTF-8"));

			String classPath = AlipayAction.class.getResource("/").toURI().getPath() + "rechargeTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_alipay.txt";
			WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + $.getQueryString("UTF-8"), "UTF-8");
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 批次号

			String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8");

			// 批量退款数据中转账成功的笔数
			String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"), "UTF-8");

			// 批量退款数据中的详细信息
			String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			if (AlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
				// 如果通知返回，无论成不成功，先把会员的余额扣除
				// 根据退款批次号找到相关的退款会员
				CsAlipayRefund oneOfAlipayRefund = csAlipayRefundService.getCsAlipayRefund($.add("csarTradeNo", batch_no));

				int successNum = Integer.parseInt(success_num);

				// 找到对应的退款申请，如果全部成功，则更新为成功，否则更新为失败
				Long refundCount = csAlipayRefundService.getCsAlipayRefundCount($.add("csarTradeNo", batch_no));
				if (refundCount.intValue() == successNum) {
					csRefundService.updateCsRefund$NotNull(new CsRefund().csrId(oneOfAlipayRefund.getCsarRefund()).csrStatus((short) 2));
				} else {
					csRefundService.updateCsRefund$NotNull(new CsRefund().csrId(oneOfAlipayRefund.getCsarRefund()).csrStatus((short) 3));
				}

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (successNum > 0) {
					String[] transactions = result_details.split("#");
					for (int i = 0; i < transactions.length; i++) {
						String[] transaction = transactions[i].split("\\^|\\$");
						// 先根据交易号取充值记录
						CsAlipayRecord csAlipayRecord = csAlipayRecordService.getCsAlipayRecord($.add("csarAlipayNo", transaction[0]));
						// 再根据充值记录ID和退款批次号取退款记录
						CsAlipayRefund csAlipayRefund = csAlipayRefundService.getCsAlipayRefund($.add("csarRecord", csAlipayRecord.getCsarId()).add("csarTradeNo", batch_no));

						// 交易成功才进行下一步业务操作
						if (transaction[2].toLowerCase().equals("success")) {
							if(transaction.length>=7){
								if(! (transaction[6].toLowerCase().equals("success"))){
									Logger.getLogger("alipay").debug("退款放弃处理，参数："+transactions[i]);
									continue;
								}
							}
							
							// 2015-06-17 未成功的退款才处理
							if (csAlipayRefund.getCsarStatus() != 1) {
								double ammount = Double.parseDouble(transaction[1]);
								// 扣除会员对应余额
								String remark = "";
								if ((csAlipayRecord.getCsarPayMethod()!=null && (csAlipayRecord.getCsarPayMethod().equals("wap01")) || csAlipayRecord.getCsarPayMethod().equals("directPay"))) {
									remark = CommonMessage.MSG0310.replace("{trade}", csAlipayRecord.getCsarAlipayNo()).replace("{money}", LZ.digit(ammount, 2));
								} else if (csAlipayRecord.getCsarPayMethod().equals("HZBANK")) {
									remark = CommonMessage.MSG0311.replace("{bank}", SystemHelper.convertBank("HZCBB2C")).replace("{money}", LZ.digit(ammount, 2));
								} else {
									remark = CommonMessage.MSG0311.replace("{bank}", SystemHelper.convertBank($.or(csAlipayRecord.getCsarBlank(), ""))).replace("{money}", LZ.digit(ammount, 2));
								}
								commonMoneyService.updateMoney(csAlipayRefund.getCsarMember(), MoneyType.Money, -Math.abs(ammount), RecordType.余额退款, remark, null, csAlipayRefund.getCsarRefund(), CsAlipayRefund.class);
								// 对应的支付宝充值记录上修改已退金额数
								csAlipayRecordService.updateCsAlipayRecordByConfirm($.add("definex", "csar_refund=csar_refund+" + ammount), $.add("csarId", csAlipayRecord.getCsarId()).add("confirm", 1));
								// 对应的支付宝退款记录修改为成功状态
								csAlipayRefundService.updateCsAlipayRefund$NotNull(new CsAlipayRefund().csarUpdateTime(new Date()).csarId(csAlipayRefund.getCsarId()).csarStatus((short) 1));
								// 对应的退款记录修改已退金额数
								csRefundService.updateCsRefundByConfirm($.add("definex", "csr_amount=csr_amount+" + ammount), $.add("csrId", oneOfAlipayRefund.getCsarRefund()).add("confirm", 1));
							}
						} else {
							// 对应的退款记录修改为失败状态
							csAlipayRefundService.updateCsAlipayRefund$NotNull(new CsAlipayRefund().csarUpdateTime(new Date()).csarId(csAlipayRefund.getCsarId()).csarStatus((short) 2));
						}
					}

				}else{
					csAlipayRefundService.updateCsAlipayRefund$NotNull(new CsAlipayRefund().csarUpdateTime(new Date()).csarId(oneOfAlipayRefund.getCsarId()).csarStatus((short) 2));
				}
				// 判断是否在商户网站中已经做过了这次通知返回的处理
				// 如果没有做过处理，那么执行商户的业务程序
				// 如果有做过处理，那么不执行商户的业务程序

				return $.SendHtml("success", SYSTEM.UTF8);// 请不要修改或删除
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				return $.SendHtml("fail", SYSTEM.UTF8);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("fail", SYSTEM.UTF8);
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsAlipayRecordService getCsAlipayRecordService() {
		return csAlipayRecordService;
	}

	public void setCsAlipayRecordService(ICsAlipayRecordService csAlipayRecordService) {
		this.csAlipayRecordService = csAlipayRecordService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICsAlipayRefundService getCsAlipayRefundService() {
		return csAlipayRefundService;
	}

	public void setCsAlipayRefundService(ICsAlipayRefundService csAlipayRefundService) {
		this.csAlipayRefundService = csAlipayRefundService;
	}

	public ICsRefundService getCsRefundService() {
		return csRefundService;
	}

	public void setCsRefundService(ICsRefundService csRefundService) {
		this.csRefundService = csRefundService;
	}

}
