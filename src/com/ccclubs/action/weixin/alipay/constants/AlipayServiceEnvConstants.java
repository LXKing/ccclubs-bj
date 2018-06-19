/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.ccclubs.action.weixin.alipay.constants;

import com.lazy3q.web.helper.$;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

    /**支付宝公钥-从支付宝服务窗获取*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    /**签名编码-视支付宝服务窗要求*/
    public static final String SIGN_CHARSET      = "GBK";

    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "GBK";

    /**签名类型-视支付宝服务窗要求*/
    public static final String SIGN_TYPE         = "RSA";

    /** 服务窗appId  */
    //注：该appId必须设为开发者自己的服务窗id
    public static final String APP_ID            = "2013121000002320";

    //开发者请使用openssl生成的密钥替换此处  请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
    //注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
    public static final String PRIVATE_KEY       = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKkuYCiNm05CMBw6UpW8DoCFm9v0SGD+sart0tgj57q4yGUme9RldKhSkLnoXH4YpwYKLAv69/LJu8lfDWaQ2MGVTQMAZSdoDHddzlsErSCRmPxRVHnG1PCrXvn55uN2dmRFX47HLKEQZYSA5JLfdKCxib9KnjNiuZtayNqVloiZAgMBAAECgYBMx8ZOQU4dqz5IYVO/rvRp8bk1jt0WYCDj8UFCZLEzdc9E0L1cPJtAFRCnvsPTWn7a59zrpv/GiTWXg3MCfHRSUdpP0EGeUb3klj6zdWo8SJQiW5Gur/qzBAMxCbM1yY68RDTdrPa8P5Fb+Zw8pCvHx5wiawzILJKHwyWjEYoCnQJBANeff7UfMLGfKhAyWKRz0atTpg33sedvIG41uNAuVCHGCCUx0BohdeDqJezSza0BEqV/rfags1SMOkc4BCcUdEsCQQDI3I2pITB66naOPvphAEn6VJA7+irZJUlKixloaaVmvfXmal/ABMfJ2Cy+Zat5xBMMdjlOa3QdRJtXJ1SRFQArAkEArwNtGswIMAOqlp7myASh6Ffq7mdf5SOTxSChs4gHDaq5Lcdyy+Lg1P/4bHLmMkgupFowD4B8TmHLY8cZx8aYZQJBALvd1b2X5lFA1OxMqUBq1caGq3T9knARONC482W+Uk6bY9BENBRUJE18nLTkQRMdujdQqF7Zj/wD/Ery5VzhSIsCQDXzpO2BE4m0v54rrUEoXUp2xmoUokuMJsNnSIOA3RLyKqh+Ln++V5UpRupcMlfF3aYUk0gNlXcGcjN1eYZyd54=";

    //注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
    public static final String PUBLIC_KEY        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpLmAojZtOQjAcOlKVvA6AhZvb9Ehg/rGq7dLYI+e6uMhlJnvUZXSoUpC56Fx+GKcGCiwL+vfyybvJXw1mkNjBlU0DAGUnaAx3Xc5bBK0gkZj8UVR5xtTwq175+ebjdnZkRV+OxyyhEGWEgOSS33SgsYm/Sp4zYrmbWsjalZaImQIDAQAB";

    //该公钥为芝麻信用公钥
    public static final String ZHIMA_PUBLIC_KEY  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzs+HR9X0FTJrDyq+ytDsC3HP3sMrA3H5bw+KiD13bRiAyYnCmEQzpT8IMaz0oaPzZQ6QMXDptePVaVnzfXOKxBnwjDWzg0GBAaURRMDliAXOkG2YNohY8831VCACk1RSOBLvTOu4Excf6ltSLcbfH0t6MRtReLU3RJLgi+M5l3QIDAQAB";
    
    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "https://openapi.alipay.com/gateway.do";

    /**授权访问令牌的授权类型*/
    public static final String GRANT_TYPE        = "authorization_code";
    
    /**芝麻分*/
    public static final int ZHIMA_SCORE          = 650;
    
    public static final String REDIRECT_URL      = $.config("weixin.domain");
}
