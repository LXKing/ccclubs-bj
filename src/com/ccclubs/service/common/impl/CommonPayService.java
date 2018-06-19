package com.ccclubs.service.common.impl;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alipay.config.AlipayConfig;
import com.ccclubs.util.AlipayNotify;
import com.ccclubs.util.RSA;
import com.ccclubs.util.SignUtils;
import com.ccclubs.action.app.official.util.CouponBuyUtil;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonPayService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

/**
 * 支付宝支付接口
 * @author uiu
 *
 */
public class CommonPayService implements ICommonPayService {
	ICsMemberDao				csMemberDao;
	ICsAlipayRecordService		csAlipayRecordService;
	ICommonMoneyService			commonMoneyService;
	// 商户PID
	public static final String	PARTNER		= AlipayConfig.partner;
	// 商户收款账号
	public static final String	SELLER		= AlipayConfig.seller_email;
	// 商户私钥，pkcs8格式
	public static final String	RSA_PRIVATE	= AlipayConfig.RSA_PRIVATE;
	// 支付宝公钥
	public static final String	RSA_PUBLIC	= AlipayConfig.RSA_PUBLIC;

	public String executeGetAppAlipayString(Long member, Double money, String noticeUrl, String flag) throws Exception{
		return executeGetAppAlipayString(member, money, noticeUrl, flag, null);
	}
	
	/**
	 * 获得已签名的支付串
	 * 
	 * @param member
	 *            会员
	 * @param money
	 *            金额，0.01
	 * @param noticeUrl
	 *            异步通知页面 ，url地址后面不要带任何参数
	 * @param flag
	 *            请求appid
	 * @return 符合支付宝参数规范的订单信息payinfo
	 */
	public String executeGetAppAlipayString(Long member, Double money, String noticeUrl, String flag, String out_trade_no) throws Exception{
		// TODO Auto-generated method stub
		// 拼装payinfo
		String payinfo = "";

			if (member == null || member < 1l) {
				return payinfo;
			}
			// 商户订单号
			if(StringUtils.isEmpty(out_trade_no)){
				out_trade_no = $.date(new Date(), "yyyyMMddHHmmssSSS") + member;
			}
			String orderInfo = getOrderInfo(out_trade_no, noticeUrl, money);
			String sign = SignUtils.sign(orderInfo, RSA_PRIVATE);

			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");

			// 完整的符合支付宝参数规范的订单信息
			payinfo = orderInfo + "&sign=\"" + sign + "\"&" + "sign_type=\"RSA\"";

			// /////////////////////////////////////保存充值记录到数据库///////////////////////////////////////////
			CsMember csMember = csMemberDao.getCsMemberById(member);
			CsAlipayRecord csAlipayRecord = new CsAlipayRecord();
			csAlipayRecord.setNotNull(csMember.getCsmHost(), (short)0,out_trade_no, member, csMember.getCsmName(), money, new Date(), new Date(), (short) 0);
			csAlipayRecord.csarRefund(0d).csarPayMethod(flag);
			csAlipayRecord.save();
			// ////////////////////////////////////////////////////////////////////////////////
		return payinfo;
	}

	/**
	 * 根据回传字符串，处理充值返回
	 * 
	 * @param params
	 *            待处理参数集
	 * @param detail
	 *            待验签字符串， &sign_type 前面的字符串
	 * @param syn
	 *            true：同步返回，false：异步返回
	 * @return true/false
	 */
	public Boolean executeSynAppAlipayCallback(Map<String, String> params, String detail, Boolean syn) {
		try {
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = params.get("out_trade_no");

			// 支付宝交易号
			String trade_no = "";
			if (params.get("trade_no") != null) {
				trade_no = params.get("trade_no");
			}

			// 交易状态
			String trade_status = "";
			if (params.get("trade_status") != null) {
				trade_status = params.get("trade_status");
			} else if (params.get("success") != null) {
				trade_status = params.get("success");
			}

			// 支付人支付宝帐号
			String buyer_email = "";
			if (params.get("buyer_email") != null) {
				buyer_email = params.get("buyer_email");
			}
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (syn) {
				Boolean issign = RSA.verify(detail, params.get("sign").replace(" ", "+"), CommonPayService.RSA_PUBLIC, "utf-8");
				if (issign) {
					/** **************本地业务处理******************* */
					updatePaySuccess(out_trade_no, trade_no, buyer_email);
					/** **************本地业务处理******************* */
					return true;
				} else {
					return false;
				}
			} else {
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
					} else if (trade_status.equals("true")) {
						/** **************本地业务处理******************* */
						updatePaySuccess(out_trade_no, trade_no, buyer_email);
						/** **************本地业务处理******************* */
					}

					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					return true; // 请不要修改或删除
					// ////////////////////////////////////////////////////////////////////////////////////////
				} else {// 验证失败
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return false;
		}
	}

	/**
	 * 更新支付结果成功
	 */
	private void updatePaySuccess(final String out_trade_no, final String ali_trade_no, final String buyer_email) {
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
					CsAlipayRecord updateRecord = new CsAlipayRecord()
							.csarStatus((short) 1)// 状态为成功
							.csarBuyerAccount(buyer_email)// 支付宝帐号
							.csarId(csAlipayRecord.getCsarId())// 原充值记录ID
							.csarUpdateTime(new Date());// 更新时间为当前时间
					if(!$.empty(ali_trade_no)){
						updateRecord.csarAlipayNo(ali_trade_no);// 支付宝交易号
					}
							
					csAlipayRecordService.updateCsAlipayRecord$NotNull(updateRecord);
					// 修改会员余额帐户
					String remark = "";
					remark = CommonMessage.MSG0309.replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));

					commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarAmount(), RecordType.支付宝充值, remark, null, csAlipayRecord.getCsarId(), CsAlipayRecord.class);
					
					//================================充值宝贝后续处理===============================================
					if(csAlipayRecord.getCsarBuyItem()!=null){
						final ICsItemService csItemService = $.GetSpringBean("csItemService");
						final CsItem item = csItemService.getCsItem($.add(CsItem.F.csiId, csAlipayRecord.getCsarBuyItem()));
						if(item ==null){
							Logger.getRootLogger().error("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]充值宝贝找不到记录 - " + $.json(csAlipayRecord));
							return null;
						}
						commonMoneyService.chargeCoupon(csAlipayRecord.getCsarMember(), item, csAlipayRecord.getCsarBuyCount());
					}else{
						Logger.getRootLogger().error("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]未知的宝贝类型 - " + $.json(csAlipayRecord));
					}
					//================================充值宝贝后续处理===============================================

					return null;
				} else if (!$.empty(ali_trade_no)) {
					Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "] - 更新支付宝帐号");
					// 修改支付宝帐号
					csAlipayRecordService.updateCsAlipayRecord$NotNull(new CsAlipayRecord().csarAlipayNo(ali_trade_no)// 支付宝交易号
							.csarBuyerAccount(buyer_email)// 支付宝帐号
							.csarId(csAlipayRecord.getCsarId())// 原充值记录ID
							.csarUpdateTime(new Date())// 更新时间为当前时间
							);
					return null;
				} else {// 放弃处理
					Logger.getRootLogger().error("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]放弃处理 - " + $.json(csAlipayRecord));
					return null;
				}
			}
		});
	}

	private String getOrderInfo(String tradeNo, String noticeUrl, Double money) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + tradeNo + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + "北京出行" + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + "北京出行" + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + money.toString() + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + noticeUrl + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		// orderInfo += "&return_url=\"1111\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	public ICsMemberDao getCsMemberDao() {
		return csMemberDao;
	}

	public void setCsMemberDao(ICsMemberDao csMemberDao) {
		this.csMemberDao = csMemberDao;
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

}
