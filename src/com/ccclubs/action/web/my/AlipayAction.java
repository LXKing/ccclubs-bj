package com.ccclubs.action.web.my;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.ccclubs.action.weixin.AccountAction;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsAlipayRefund;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsAlipayRefundService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
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
	 * 会员充值
	 * 
	 * @return
	 */
	public String execute() {
		try {
			if (!SystemHelper.isLogin())
				return SystemHelper.gotoLogin();

			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			$.SetRequest("amount", $.getDouble("amount", 0d));
			$.SetRequest("member", member);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips(SYSTEM.ERROR_TIPS);
		}
		return "index";
	}

	/**
	 * 提交充值
	 * 
	 * @return
	 */
	public String submit() {
		try {
			if (!SystemHelper.isLogin())
				return SystemHelper.gotoLogin();

			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());

			if (member.get$csmInfo() == null || $.empty(member.get$csmInfo().getCsmiName())) {
				$.SetTips("请完善您的个人资料后再充值");
				return execute();
			}

			// 支付类型
			String payment_type = "1";
			// 必填，不能修改
			// 服务器异步通知页面路径
			String notify_url = $.config("domain") + "my/alipay-notice.html";
			// 需http://格式的完整路径，不能加?id=123这类自定义参数

			// 页面跳转同步通知页面路径
			String return_url = $.config("domain") + "my/alipay-callback.html";
			// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

			// 卖家支付宝帐户
			String seller_email = AlipayConfig.seller_email;
			// 必填

			// 商户订单号
			String out_trade_no = $.date(new Date(), "yyyyMMddHHmmssSSS") + SystemHelper.getLoginId();
			// 商户网站订单系统中唯一订单号，必填

			// 订单名称
			String subject = "车纷享自助租车";
			// 必填

			// 付款金额
			String total_fee = $.getDouble("alimoney").toString();
			// 必填

			// 订单描述
			String body = "车纷享自助租车，方便、快捷";

			String channelToken = $.getString("channelToken");// 页面传过来的支付方式

			// 默认支付方式
			String paymethod = channelToken.equals("directPay") ? "directPay" : "bankPay";
			// 必填
			// 默认网银
			String defaultbank = channelToken.equals("directPay") ? "" : channelToken;
			// 必填，银行简码请参考接口技术文档

			// 商品展示地址
			String show_url = $.config("domain");
			// 需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html

			// 防钓鱼时间戳
			String anti_phishing_key = "";
			// 若要使用请调用类文件submit中的query_timestamp函数

			// 客户端的IP地址
			String exter_invoke_ip = "";
			// 非局域网的外网IP地址，如：221.0.0.1

			// /////////////////////////////////////保存充值记录到数据库///////////////////////////////////////////
			CsAlipayRecord csAlipayRecord = new CsAlipayRecord();
			csAlipayRecord.setNotNull(SystemHelper.getSrvHost().getShId(),(short)0, out_trade_no, SystemHelper.getLoginId(), member.get$csmInfo().getCsmiName(), $.getDouble("alimoney"), new Date(), new Date(), (short) 0);
			csAlipayRecord.csarRefund(0d).csarBlank(defaultbank).csarPayMethod(paymethod);
			csAlipayRecordService.saveCsAlipayRecord(csAlipayRecord);
			// ////////////////////////////////////////////////////////////////////////////////

			// 把请求参数打包成数组,
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("seller_email", seller_email);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("paymethod", paymethod);
			sParaTemp.put("defaultbank", defaultbank);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");

			return $.SendHtml(sHtmlText, SYSTEM.UTF8);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips(SYSTEM.ERROR_TIPS);
			return execute();
		}
	}

	/**
	 * 支付宝充值通知-服务器异步通知页面
	 */
	public String notice() {
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

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 支付人支付宝帐号
			String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					/** **************暂时不处理此情况******************* */
					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					/** **************本地业务处理******************* */
					updatePaySuccess(out_trade_no, trade_no, buyer_email);
					/** **************本地业务处理******************* */
					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				}

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				return $.SendHtml("success", SYSTEM.UTF8); // 请不要修改或删除
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

	/**
	 * 支付宝充值同步返回
	 */
	public String callback() {
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

			Logger.getLogger("alipay").info($.getQueryString("UTF-8"));

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 支付人支付宝帐号
			String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");

			// 获取总金额
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			// 计算得出通知验证结果
			boolean verify_result = AlipayNotify.verify(params);

			if (verify_result) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					/** **************本地业务处理******************* */
					updatePaySuccess(out_trade_no, trade_no, buyer_email);
					/** **************本地业务处理******************* */
				}

				// 该页面可做页面美工编辑
				$.SetRequest("success", true);// out.println("验证成功<br />");
				// 打印充值金额
				$.SetRequest("amount", total_fee);
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {
				// 该页面可做页面美工编辑
				$.SetRequest("success", false);// out.println("验证失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetRequest("success", false);
		}
		return "callback";
	}

	/**
	 * 更新支付结果成功
	 */
	public void updatePaySuccess(final String out_trade_no, final String ali_trade_no, final String buyer_email) {
		csAlipayRecordService.executeTransaction(new Function() {
			@Override
			public <T> T execute(Object... arg0) {
				CsAlipayRecord csAlipayRecord = csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
				if (csAlipayRecord == null) {
					Logger.getRootLogger().error("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]错误 - 无法找到相关的充值记录 ");
					return null;
				} else if (csAlipayRecord.getCsarStatus().shortValue() == 0) {
					Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]");
					// 修改充值记录为已成功
					csAlipayRecordService.updateCsAlipayRecord$NotNull(new CsAlipayRecord().csarAlipayNo(ali_trade_no)// 支付宝交易号
							.csarStatus((short) 1)// 状态为成功
							.csarBuyerAccount(buyer_email)// 支付宝帐号
							.csarId(csAlipayRecord.getCsarId())// 原充值记录ID
							.csarUpdateTime(new Date())// 更新时间为当前时间
							);
					// 修改会员余额帐户
					String remark = "";
					if (csAlipayRecord.getCsarPayMethod().equals("directPay")) {
						remark = CommonMessage.MSG0309.replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
					} else {
						remark = CommonMessage.MSG0313.replace("{bank}", SystemHelper.convertBank(csAlipayRecord.getCsarBlank())).replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
					}
					commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarAmount(), RecordType.支付宝充值, remark, null, csAlipayRecord.getCsarId(), CsAlipayRecord.class);

					return null;
				} else {// 放弃处理
					Logger.getRootLogger().error("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]放弃处理 - " + $.json(csAlipayRecord));
					return null;
				}
			}
		});
	}

	/**
	 * 支付宝退款通知返回
	 * 
	 * @return
	 */
	public String Notify() {
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
						if (transaction[2].toLowerCase().equals("success") && transaction[6].toLowerCase().equals("success")) {
							// 2015-06-17 未成功的退款才处理
							if (csAlipayRefund.getCsarStatus() != 1) {
								double ammount = Double.parseDouble(transaction[1]);
								// 扣除会员对应余额
								String remark = "";
								if (csAlipayRecord.getCsarPayMethod().equals("wap01") || csAlipayRecord.getCsarPayMethod().equals("directPay")) {
									remark = CommonMessage.MSG0310.replace("{trade}", csAlipayRecord.getCsarAlipayNo()).replace("{money}", LZ.digit(ammount, 2));
								} else if (csAlipayRecord.getCsarPayMethod().equals("HZBANK")) {
									remark = CommonMessage.MSG0311.replace("{bank}", SystemHelper.convertBank("HZCBB2C")).replace("{money}", LZ.digit(ammount, 2));
								} else {
									remark = CommonMessage.MSG0311.replace("{bank}", SystemHelper.convertBank(csAlipayRecord.getCsarBlank())).replace("{money}", LZ.digit(ammount, 2));
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
