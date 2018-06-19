package com.alipay.config;

import com.ccclubs.service.common.impl.CommonPayService;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//=====================================  车纷享商户支付宝配置开始  ========================================================
//	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
//	public static String partner = "2088901415496881";
//	// 商户的私钥
//	public static String key = "9s5rztn35ftb7jn7oiszob77w8ngs3fb";
//
//	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//	
//
//	// 调试用，创建TXT日志文件夹路径
//	public static String log_path = "D:\\TEMP\\ccclubs\\";
//
//	// 字符编码格式 目前支持 gbk 或 utf-8
//	public static String input_charset = "utf-8";
//	
//	// 签名方式 不需修改
//	public static String sign_type = "MD5";
//	
//	
//	//签约支付宝账号或卖家支付宝帐户
//	public static String seller_email = "carclubs@163.com";
	//=====================================  车纷享商户支付宝配置结束  ========================================================
	
	
	
	//=====================================  北京出行商户支付宝配置开始  ========================================================
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public final static String partner = "2088611785385684";
	// 商户的私钥
	public final static String key = "80w4iur3uldw924gah46iz8veh3tnyso";

	// 调试用，创建TXT日志文件夹路径
	public final static String log_path = "D:\\TEMP\\ccclubs\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public final static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public final static String sign_type = "MD5";
	
	//签约支付宝账号或卖家支付宝帐户
	public final static String seller_email = "bjxdysl@126.com";
	
	public final static String  RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOOkVGrRKSFHDJtCGkVGUZ3NqXv27dD6XDevlSwmjakZPHu8vfHzqbu7/bvwFjuhWn4kqCqrbP3KVdj0OlZSjHxAQVGvxbYVn04rrdaZJOde2VqxJT2uaCCLR4Z3vjc/QdkJxWjsClwDxjeOxiASK4LB78gNNqPjmTbYVaszWw+9AgMBAAECgYEAq5jiMar9cCJYtGh92R6Kw4OWcOXQmyk+fpOSxTdCrediGnxKSqq8ldG+EP+IIuccid4kDhYvEJiHYgWoPA356vucC83IUP2FbMMAUlp1kmvoB962/XIR2NumUf2Bfkw0D6s+5q4G8DFEgWZYK5tWOtkiNGrbDI6UN2/8/VeOwZkCQQD1lcku3Cff2Yjmz9Z8+oKOawV2fSOaDZ3aYonoc96LZrK9S0iwxs4N4KzvnTEjhRMtOO9TeMgRXdG4b5Cx/9uvAkEA7Uu+A3dnJi7ZZ2o9CSCdtGI/7WY3pxm11E/3E/4+uMGftKcN1KcBgdZlc8IE/+gvDukABSmy2ryfgPHWyM4KUwJBANtdjHNbHBMMEQl7JTBF5FCqsBqC9UYqW5Iy6tTEi0kh1w7XLHsbWftzcYYndzkvKU3/yWdaP/ujPi3tGealkOkCQAkurSOlMzCfD3qY+dOol3Gre53MHsswi4Czt3Pmmra7h1qOPFLGQ6CqkzJvh5H/ghKpoX6RpoIgWmu67/zFPFECQFcwaSVjfhNfUqwsTtsZHXok/W+vwL3eLzlVmxMhpm2D2JUNvPO3oUX78nmyZq2jA218N/UwJJcox+Q/Asrj48A=";
	
	public final static String  RSA_PUBLIC	= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	//=====================================  北京出行商户支付宝配置结束  ========================================================

}
