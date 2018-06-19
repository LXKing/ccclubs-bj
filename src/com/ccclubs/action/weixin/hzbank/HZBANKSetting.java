package com.ccclubs.action.weixin.hzbank;

import java.io.File;

/**
 * 杭州银行参数工具类
 * 
 * @create 2012-12-25
 * @version V1.0.0
 * @author wangheng
 * 
 */
public class HZBANKSetting {

	public static boolean	isAPIInitialize	= false;	// 是否初始化

	public static String	MerchantID;				// 商户号(不)

	public static String	MerchantCertDN;			// 商户证书DN(不)

	public static boolean	EnableLog;					// 日志开关（true:表示写日志,false:
														// 表示不写日志）

	public static String	LogPath;					// 日志文件存放目录
														// (格式：C:/hzbankjava/log/)

	public static String	MerchantCertFile;			// 商户证书文件及路径(格式：C:\hzbankjava\HZBANKCert\7461100111.pfx)

	public static String	MerchantCertFilePath;		// 商户证书文件路径
														// (格式：C:\hzbankjava\HZBANKCert)

	public static String	MerchantCertFileName;		// 商户证书文件名称
														// (格式：7461100111.pfx)

	public static String	MerchantCertPassword;		// 商户证书密码 (格式：000000)

	public static String	RootCertFile;				// 根证书文件
														// (格式：C:/hzbankjava/HZBANKCert/TESTCA.cer)

	public static String	BankDN;					// 银行证书DN (格式：7571110030)

	public HZBANKSetting() {
	}

	/**
	 * 初始化 杭州银行签名所用参数
	 * 
	 * @param enableLog
	 *            日志开关
	 * @param logPath
	 *            日志文件存放目录
	 * @param merchantCertFile
	 *            商户证书文件路径
	 * @param merchantCertPassword
	 *            商户证书密码
	 * @param rootCertFile
	 *            根证书文件路径
	 * @param bankDN
	 *            银行证书DN
	 * @return boolean
	 */
	public int initialize(boolean enableLog, String logPath, String merchantCertFile, String merchantCertPassword, String rootCertFile, String bankDN) {

		// *******************初始化参数*****************************
		if (!isAPIInitialize) {
			EnableLog = enableLog;

			if (EnableLog) {
				if (logPath == null || logPath.trim().length() == 0) {
					System.out.println("日志开关设置为true,日志文件存放目录设置错误，设置参数不能为空");
					return -1;
				} else {
					try {
						new File(logPath).mkdirs();
						LogPath = new File(logPath).getAbsolutePath();
					} catch (Exception xcp) {
						System.out.println("日志开关设置为true,日志文件存放目录设置错误，请确定路径是否配置正确");
						return -1;
					}
				}
			}

			if (merchantCertFile == null || merchantCertFile.trim().length() == 0) {
				System.out.println("商户证书文件路径设置错误，设置参数不能为空");
				return -1;
			} else {
				if (!new File(merchantCertFile).isFile()) {
					System.out.println("商户证书文件路径设置错误，请确定路径及文件名是否配置正确");
					return -1;
				}
			}
			MerchantCertFile = new File(merchantCertFile).getAbsolutePath();
			MerchantCertFilePath = new File(merchantCertFile).getParent();
			MerchantCertFileName = new File(merchantCertFile).getName();

			if (merchantCertPassword == null || merchantCertPassword.trim().length() == 0) {
				System.out.println("商户证书密码设置错误，设置参数不能为空");
				return -1;
			}
			MerchantCertPassword = merchantCertPassword;

			if (rootCertFile == null || rootCertFile.trim().length() == 0) {
				System.out.println("根证书文件路径设置错误，设置参数不能为空");
				return -1;
			} else {
				if (!new File(rootCertFile).isFile()) {
					System.out.println("根证书文件路径设置错误，请确定路径及文件名是否配置正确");
					return -1;
				}
			}
			RootCertFile = rootCertFile;

			if (bankDN == null || bankDN.trim().length() == 0) {
				System.out.println("银行证书DN设置错误，设置参数不能为空");
				return -1;
			}
			BankDN = bankDN;

			System.out.println("日志开关：" + HZBANKSetting.EnableLog);
			System.out.println("日志文件存放目录：" + HZBANKSetting.LogPath);
			System.out.println("商户证书文件路径：" + HZBANKSetting.MerchantCertFilePath);
			System.out.println("商户证书文件名称：" + HZBANKSetting.MerchantCertFileName);
			System.out.println("商户证书密码 ：" + HZBANKSetting.MerchantCertPassword);
			System.out.println("根证书文件：" + HZBANKSetting.RootCertFile);
			System.out.println("银行证书DN：" + HZBANKSetting.BankDN);

			isAPIInitialize = true;
		}
		// *******************初始化签名*****************************
		if (isAPIInitialize = true) {
			try {
				NetSignServer ns = new NetSignServer();
				System.out.println("商户号：" + HZBANKSetting.MerchantID);
				System.out.println("商户证书DN：" + HZBANKSetting.MerchantCertDN);
				System.out.println("签名控件初始化成功!");
			} catch (Exception xcp) {
			}
			if (NetSignServer.isAPIPropertySetted == false) {
				System.out.println("签名控件初始化失败");
				return -1;
			}
		}

		return 0;
	}

}