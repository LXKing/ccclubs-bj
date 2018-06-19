package com.ccclubs.service.common;


/**
 * 招商银行支付接口
 * @author uiu
 */
public interface ICMBPayService {
	
	/**
	 * 根据会员ID，金额，通知页面获取招商银行充值页面地址
	 * @param member	[非空]
	 * @param money [非空]
	 * @param noticeUrl 充值完成后回跳到的页面地址	[可为空]
	 * @param flag	来源标识
	 * @param tradNo 交易号
	 * @return
	 */
	public String executeCMBPayMobileUrl(Long member, Double money, String callbackUrl, String flag, String out_trade_no);
	
	/**
	 * 根据会员ID，金额，通知页面获取招商银行充值页面地址
	 * @param member	[非空]
	 * @param money [非空]
	 * @param noticeUrl 充值完成后回跳到的页面地址	[可为空]
	 * @param flag	来源标识
	 * @return
	 */
	public String executeCMBPayMobileUrl(Long member, Double money, String callbackUrl, String flag);

	/**
	 * 接收从招行回调通知
	 * @return
	 */
	public Long updateCMBPayResponse();
	
	/**
	 * 招商银行退款
	 * @param csarTradeNo
	 */
	public Boolean executeCMBRefund(final String csarTradeNo);
	
}
