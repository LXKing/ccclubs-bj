package com.ccclubs.action.weixin.alipay.config;

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
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088901415496881";
	
	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
	public static String key = "9s5rztn35ftb7jn7oiszob77w8ngs3fb";
	
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKmWT3VR4Z9VHKPttgDaDivpZAc77l2LYQH2aPLllptSGlSKSur4wCFDd1SJUA/vKGr39jKY0w5anNe7qz7maDNwDT+qI4nAw1ChvXpcQcOoWNc1IktJZ4jzqcjcvE4hI+iECOyfuP3Evk+rhQEmhrLYSpBmCjaCmo7ZtkiM6oNPAgMBAAECgYABMLjmDekTgTluPSDl6KHo1P6KvnTx2ySXKu+ZpNrlxDHDHiVgeNaAZUTVJxGUywzxk0P7Hu98Tk6DhSGyk1Nv5hCFOgPP93a2iLvCl5B/mbNcrICCDS7ZHfoGPK15dl15pQuZeHkAkKaycAfaMem+Tn/N1+/BLltUKNGCyvYtYQJBANQ5+WX/AGAI15dE9aOo58ueGLN/EGdPC06Hu/hG+CVYWG6S1sDMgFCi2wwq8G5hgKXKxjM0XONzdnnBNDrVrWUCQQDMkOLoS4DgWaOlBSMmLniwyQFdXMzdJJE4iFTZfQycqiqB2NvzzvkxvD2tqPc/+I1U8SEpCqd5BoeY5/BwtOyjAkATVEhrysLs/EEujOBWjJ64A+k6B/ulfSSJfYFYRpZ5SEedopAetUGGlp6eyBZn50eBUiulJELFkJGc20T3itilAkEApDGrH17zG6B3fE3qT4w/bU55XPHOsbXXJjQvPr5jcCAZrE1fBovbcrMwIoKbqmobLtG4lxOpJrg6AF4ynJRr1wJALgSImoyFyfTUgSqVUjLWcGqxotlsAregSxFd+6nHnnZnY3OHgg+1aecY9P/MQnL8qOnJ3J2kEzHqZKOAPrhr2w==";

    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAvWABwwQUH0MLC7yrfKuxGk9/Lc7RWhXh8Arq BY2Jf3wAFyUDmBpzy+NUn7JVkKWnGwObW9UUpquvXohn4bQFTnf7ry1zButc0mZhUCWSbZcuTExM E85yBPbP8CjsqabEfwwbKW5+W1vZ7fiBE1Xj48VoRJ6L5Z5KXOs7jkpdHwIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\TEMP\\ccclubs\\";

	// 字符编码格式 目前支持  utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "MD5";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA
	
	//签约支付宝账号或卖家支付宝帐户
	public static String seller_email = "carclubs@163.com";

	public static String subject = "车纷享订单支付";
	
	//订单业务类型
	public static String product_code = "TB_QR_OFFLINE";
}
