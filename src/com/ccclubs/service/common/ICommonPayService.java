package com.ccclubs.service.common;

import java.util.Map;

/**
 * App支付服务
 * 
 * @author qsxiaogang
 * 
 */
public interface ICommonPayService {
	
	/**
	 * 获得已签名的支付串
	 * 
	 * @param member
	 *            会员
	 * @param money
	 *            金额
	 * @param noticeUrl
	 *            异步通知页面 ，url地址后面不要带任何参数
	 * @param flag
	 *            请求appid
	 * @return 符合支付宝参数规范的订单信息payinfo
	 */
	public String executeGetAppAlipayString(Long member, Double money, String noticeUrl, String flag, String out_trade_no) throws Exception;

	/**
	 * 获得已签名的支付串
	 * 
	 * @param member
	 *            会员
	 * @param money
	 *            金额
	 * @param noticeUrl
	 *            异步通知页面 ，url地址后面不要带任何参数
	 * @param flag
	 *            请求appid
	 * @return 符合支付宝参数规范的订单信息payinfo
	 */
	public String executeGetAppAlipayString(Long member, Double money, String noticeUrl, String flag) throws Exception;

	/**
	 * 根据回传字符串，处理充值返回
	 * 
	 * @param params
	 *            待处理参数集
	 * @param detail
	 *            待验签字符串
	 * @param syn
	 *            true：同步返回，false：异步返回
	 * @return true/false
	 */
	public Boolean executeSynAppAlipayCallback(Map<String, String> params, String detail, Boolean syn);
}
